package com.mjkonceptz.howtouseactivityresultapitoparseinformationbetween3activities_beginners_kotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProcessInputActivity : AppCompatActivity() {

    /* TODO: Define Global Keys/Tokens Here: */
    companion object{

        const val EXTRA_DISPLAY_NAME = "displayName"
        const val EXTRA_DISPLAY_SUM = "displaySum"

    }// companion object

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_process_input)

        /*TODO: Get Result Forwarded From Activity B(InputActivity).*/
        val sName = intent.getStringExtra(InputActivity.EXTRA_NAME)
        val sum = intent.getIntExtra(InputActivity.EXTRA_SUM, 0)

        /* TODO: Define Needed Views Here: */
        val txtDisplayName = findViewById<TextView>(R.id.txtDisplayName)
        val txtDisplaySumResult = findViewById<TextView>(R.id.txtDisplaySumResult)
        val btnSendResult = findViewById<Button>(R.id.btnSendResult)

        /* TODO: Display information in this Activity before parsing
            or forwarding the needed result to Activity A-(MainActivity)*/

        txtDisplayName.text = "Name: $sName"
        txtDisplaySumResult.text = "The Sum of  Num1 and Num2: $sum"

        /*TODO: Use the Result From Activity B (InputActivity) To Display
            Some Information In Activity C(ProcessInput).*/
        btnSendResult.setOnClickListener {
            val resultIntent = Intent(this, MainActivity::class.java ).apply {
                putExtra(EXTRA_DISPLAY_NAME, sName)
                putExtra(EXTRA_DISPLAY_SUM, sum)
            }//apply
            setResult(RESULT_OK, resultIntent)
            finish()

        }//btnSendResult_setOnClickListener

    }//onCreate

}//end_ProcessActivity

