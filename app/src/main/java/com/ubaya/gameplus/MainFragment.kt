package com.ubaya.gameplus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    var number1:Int = 0
    var number2:Int = 0
    var trueAnswer:Int = 0
    var score:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateQuestion()

        buttonSubmit.setOnClickListener {
            val playerAnswer = if (editAnswer.text.toString() == "") 0 else editAnswer.text.toString().toInt()
            if (playerAnswer == trueAnswer) {
                score += 5
                textPlayerScore.text = "Score : $score"
                generateQuestion()
            }
            else {
                val action = MainFragmentDirections.actionResultFragment(score)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    private fun randomNumber():Int = (0..100).random()

    private fun generateQuestion() {
        number1 = randomNumber()
        number2 = randomNumber()
        trueAnswer = number1 + number2

        textNum1.text = number1.toString()
        textNum2.text = number2.toString()
        editAnswer.text!!.clear()
    }
}