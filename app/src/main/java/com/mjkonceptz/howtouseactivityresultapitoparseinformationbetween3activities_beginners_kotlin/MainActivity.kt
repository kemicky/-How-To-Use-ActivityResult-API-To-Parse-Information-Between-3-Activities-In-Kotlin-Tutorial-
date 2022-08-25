package com.mjkonceptz.howtouseactivityresultapitoparseinformationbetween3activities_beginners_kotlin

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

      /*TODO: Define Global View Variables Here: */
      private lateinit var txtShowName : TextView
      private lateinit var txtShowResult: TextView

      /* TODO: Define Output Variables Here:*/
      private var name: String = ""
      private var answer: Int  = 0

    companion object{
         /* TODO: Define Constants To Access ActivityResult API.*/
        const val SHARED_PREFS = "sharedPrefs"
        const val KEY_NAME = "keyName"
        const val KEY_ANSWER = "keyAnswer"

    }//end_companion object

    /*TODO: View LifeCycle: onCreate()*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* TODO: Define & Initialize Needed Views Here: */
        this.txtShowName = findViewById(R.id.txtShowName)
        this.txtShowResult = findViewById(R.id.txtShowResult)
        val btnGetResult = findViewById<Button>(R.id.btnGetResult)


        /* TODO: Request Activity B(InputProcess) Launch  & Forward Input Result Here: */
        btnGetResult.setOnClickListener {
            startForResult.launch(Intent(this, InputActivity::class.java))
        }//btnGetResult

        /* TODO: Call The Activity To Dispatch & Display the Result.*/
        displayNameAnswer()

    }//onCreate

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {

        /*TODO:  Access Incoming Result Intent  & Use try-catch For Additional
           Validation Handling Of The Result Here: */

            val intent = result.data
            try {
                val sName = intent?.getStringExtra(ProcessInputActivity.EXTRA_DISPLAY_NAME)
                val sum  = intent?.getIntExtra(ProcessInputActivity.EXTRA_DISPLAY_SUM, 0)

                /*TODO: Retrieve and update the new input here by calling
                    the updateNameAndAnswer Method Here: */
                if (sum != null) {
                    updateNameAndAnswer(sName.toString(), sum)
                }//

            }catch(ex:Exception){
                ex.printStackTrace()
            }//try_catch


        }//if_Result_OK
    }//registerForActivityResult()

    /* TODO: Set The View To Display The Result Here: */
    @SuppressLint("SetTextI18n")
    private fun displayNameAnswer(){
        val prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        name = prefs.getString(KEY_NAME, "").toString()
        answer = prefs.getInt(KEY_ANSWER, 0)

        txtShowName.text = "Name: $name"
        txtShowResult.text = "Input Sum Result: $answer"

    }//end_displayNameAnswer()

    /* TODO: Set The View To Update  & Save The New Result Here: */
    @SuppressLint("SetTextI18n")
    private fun updateNameAndAnswer(newName:String, newAnswer:Int){
        name = newName
        answer = newAnswer

       this.txtShowName.text = "Name: $name"
       this.txtShowResult.text = "Input Sum Result: $answer"

        /*TODO: Store and Save the incoming result data here by using the SharedPreferences Method.*/
        val prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(KEY_NAME, name)
        editor.putInt(KEY_ANSWER, answer)
        editor.apply()
    }//end_updateNameAndAnswer()

}//end_MainActivity

