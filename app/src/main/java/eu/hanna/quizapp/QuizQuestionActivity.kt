package eu.hanna.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.widget.TintableCompoundDrawablesView

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var progressBar : ProgressBar
    lateinit var tv_progress : TextView
    lateinit var tv_question : TextView
    lateinit var iv_image : ImageView
    lateinit var tv_option_one : TextView
    lateinit var tv_option_two : TextView
    lateinit var tv_option_three : TextView
    lateinit var tv_option_four : TextView
    lateinit var btn_submit : Button

    private var mCurrentPosition: Int = 1 // Default and the first question position
    private var mQuestionList: ArrayList<Question>?= null
    private var mSelectedOptionPosition: Int = 0 // To know which option the user selected
    private var mCorrectAnswers: Int = 0         // To count the numbers of correct answer
    // (STEP 3: Create a variable for getting the name from intent.)
    private var mUserName:String?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        progressBar = findViewById(R.id.progressBar)
        tv_progress = findViewById(R.id.tv_progress)
        tv_question = findViewById(R.id.tv_question)
        iv_image = findViewById(R.id.iv_image)
        tv_option_one = findViewById(R.id.tv_option_one)
        tv_option_two = findViewById(R.id.tv_option_two)
        tv_option_three = findViewById(R.id.tv_option_three)
        tv_option_four = findViewById(R.id.tv_option_four)
        btn_submit = findViewById(R.id.btn_submit)

        mQuestionList = Constants.getQuestions()
      //  Toast.makeText(this,"${questionList.size}",Toast.LENGTH_SHORT).show()
        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)

        //  (STEP 4: Get the NAME from intent and assign it the variable.)
        mUserName = intent.getStringExtra(Constants.USER_NAME)

    }
    /**
     * A function for the setting the question to UI components
     */
    private fun setQuestion () {
      // mCurrentPosition = 1
       val question = mQuestionList!!.get(mCurrentPosition - 1) // Getting the question from the list with the help of current position.

        defaultOptionView()

        if (mCurrentPosition == mQuestionList!!.size) {
            btn_submit.text = "FINISH"
        } else {
            btn_submit.text = "SUBMIT"
        }
        progressBar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition" + "/" +progressBar.max

        // Set the text accordingly to the question
        tv_question.text = question!!.question
        iv_image.setImageResource(question.image)
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
    }
    /**
     * A function for answer view which is used to highlight the answer is right or wrong
     */
    private fun defaultOptionView() {
        val options = ArrayList<TextView>()
        options.add(0,tv_option_one)
        options.add(1,tv_option_two)
        options.add(2,tv_option_three)
        options.add (3, tv_option_four)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> {
                selectedOptionView(tv_option_one,1)
            }
            R.id.tv_option_two -> {
                selectedOptionView(tv_option_two,2)
            }
            R.id.tv_option_three -> {
                selectedOptionView(tv_option_three,3)
            }
            R.id.tv_option_four -> {
                selectedOptionView(tv_option_four,4)
            }
            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0){
                    mCurrentPosition++

                    // To reset the screen,question, images and answers
                    when {
                        mCurrentPosition <= mQuestionList!!.size ->{
                            setQuestion()
                        } else -> {
                        // Toast.makeText(this,"You have successfully completed the quiz!",Toast.LENGTH_SHORT).show()
                        // (STEP 5: Now remove the toast message and launch the result screen which we have created and also pass the user name and score details to it.)
                            val intent = Intent (this,ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME,mUserName)
                        intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswers)
                        intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionList!!.size)
                        startActivity(intent)
                        finish()
                    }
                    }
                } else {
                    // to get the question at the current position
                    val question = mQuestionList?.get(mCurrentPosition - 1)

                    // This is to check if the answer is wrong
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition,R.drawable.wrong_option_border)
                    } else {
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_border)
                    if (mCurrentPosition == mQuestionList!!.size) {
                        btn_submit.text = "FINISH"
                    } else {
                        btn_submit.text = "Go To NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }

    }

    /**
     * A function to set the view of selected option view
     */
    private fun selectedOptionView (tv:TextView,selectedOptionNum: Int) {
        defaultOptionView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD_ITALIC)
        tv.background = ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)
    }
    /**
     * A function for answer view which is used to highlight the answer is correct or not
     */
    private fun answerView (answer:Int,drawablesView: Int){
        when(answer){
            1->{
                tv_option_one.background = ContextCompat.getDrawable(this,drawablesView)
            }
            2 -> {
                tv_option_two.background = ContextCompat.getDrawable(this,drawablesView)
            }
            3 -> {
                tv_option_three.background = ContextCompat.getDrawable(this,drawablesView)
            }
            4 -> {
                tv_option_four.background = ContextCompat.getDrawable(this,drawablesView)
            }
        }
    }

}