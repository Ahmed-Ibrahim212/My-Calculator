package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    //showing the digit of each button
    fun onDigit(view: View) {
        textField.append((view as Button).text)
        lastNumeric = true
    }

    //clearning each value
    fun onClear(view: View) {
        textField.text = ""
        lastNumeric = false
        lastDot = false
    }

    //decimal adding
    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            textField.append(".")
            lastNumeric = false
            lastDot = true

        }
    }

    //adding function on the operator
    fun onOperator(view: View) {
        if (lastNumeric && !isOperatorAdded(textField.text.toString())){
            textField.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    fun onEqual(view: View){
        if (lastNumeric){
            var tvValue = textField.text.toString()
            var prefix = ""

            try {

                if (tvValue.startsWith("-")){
                    tvValue = tvValue.substring(1)
                }

                //checking for  substraction
                if (tvValue.contains("-")){
                    val tvSplit = tvValue.split("-")

                    var one = tvSplit[0]
                    var two = tvSplit[1]

                    if (!prefix.isEmpty()){
                        one = prefix + one

                    }
                    textField.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())

                //checking for addition
                }else if(tvValue.contains("+")){
                    val tvSplit = tvValue.split("+")
                    var one = tvSplit[0]
                    var two = tvSplit[1]

                    if (!prefix.isEmpty()){
                        one = prefix + one
                    }
                    textField.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                //checking the function of division
                }else if (tvValue.contains("/")){
                    val tvSplit = tvValue.split("/")
                    var one = tvSplit[0]
                    var two = tvSplit[1]

                    if (!prefix.isEmpty()){
                        one= prefix + one
                    }
                    textField.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())

                //checking for the multiplication
                }else if (tvValue.contains("*")){
                    val tvSplit  = tvValue.split("*")
                    var one = tvSplit[0]
                    var two = tvSplit[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    textField.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }

        }
    }
    fun removeZeroAfterDot(result: String): String{
        var value = result
        if (result.contains("0"))
            value  = result.substring(0,result.length - 2)
        return value
    }

    private fun isOperatorAdded(value: String) : Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("+")
                    || value.contains("-") || value.contains("*")
        }
    }

}

