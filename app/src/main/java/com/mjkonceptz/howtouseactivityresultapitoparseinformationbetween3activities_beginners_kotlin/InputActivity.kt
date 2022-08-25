package com.mjkonceptz.howtouseactivityresultapitoparseinformationbetween3activities_beginners_kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class InputActivity : AppCompatActivity() {

    /* TODO: Define Global Keys/Tokens Here: */
    companion object{

        const val EXTRA_NAME = "inputName"
        const val EXTRA_SUM = "extraSum"

    }// companion object

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        /* TODO: Call calculateSum() Function Here: */
         calculateSum()

    }//onCreate

    private fun calculateSum(){

        /* TODO: Define Needed Views & Variables Here: */
        val btnGetResult = findViewById<Button>(R.id.btnGetResult)
        val txtNameInput = findViewById<EditText>(R.id.txtNameInput)
        val txtNum1 = findViewById<EditText>(R.id.txtNum1)
        val txtNum2 = findViewById<EditText>(R.id.txtNum2)

        btnGetResult.setOnClickListener {

            /* TODO: Process, Access User's Input & Call the Companion Objects Here: */
            val sName = txtNameInput.text.toString()
            val num1 =  txtNum1.toInt()
            val num2 =  txtNum2.toInt()

            /* TODO: Define And Calculate Sum of The 2 Numbers Here: */
            val sum = num1 + num2

            /*TODO: Start a new Intent to forward the result sum and user name to Activity C-ProcessInputActivity.*/
            val intentCalculate = Intent(this,ProcessInputActivity::class.java).apply{
                putExtra(EXTRA_NAME, sName)
                putExtra(EXTRA_SUM, sum)
                //TODO: SetFlags Forward Result Here:
                this.flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT
            }//apply()
            startActivity(intentCalculate)
            finish()

        }// btnGetResult_setClickListener

    }//calculateSum()

}//end_class_InputActivity

/* TODO: Extension Function To Convert User's Input To Int Here: */
fun EditText.toInt(): Int {
    return this.text.toString().toInt()
}// extension_function
