package eu.hanna.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    lateinit var btn_start : Button
    lateinit var et_name : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // To hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN  // to get the UI fullscreen
        setContentView(R.layout.activity_main)
        //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN) // to get the UI fullscreen

        et_name = findViewById(R.id.et_name)
        btn_start = findViewById(R.id.btn_start)
        btn_start.setOnClickListener {
            if (et_name.text.toString().isEmpty()) {
                Toast.makeText(this,"Please Enter Name",Toast.LENGTH_SHORT).show()
            } else  {
                val intent = Intent(this,QuizQuestionActivity::class.java)
                //  (STEP 2: Pass the name through intent using the constant variable which we have created.)
                intent.putExtra(Constants.USER_NAME, et_name.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}