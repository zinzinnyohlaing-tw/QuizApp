package eu.hanna.quizapp

object Constants {

    //  (STEP 1: Create a constant variables which we required in the result screen.)
    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS:String = "total_questions"
    const val CORRECT_ANSWERS:String = "correct_answer"
    fun getQuestions() : ArrayList<Question> {
        val questionList = ArrayList<Question>()
        // for 1
        val question1 = Question(1,
            "What's the fruit name?",
            R.drawable.kiwi_1,
            "Apple","Orange",
        "Kiwi","Dragon Fruit",
        3)
        questionList.add(question1)

        val question2 = Question(2,
            "What's the fruit name?",
            R.drawable.orange_2,
            "Apple","Orange",
            "Kiwi","Dragon Fruit",
            2)
        questionList.add(question2)

        val question3 = Question(3,
            "What's the fruit name?",
            R.drawable.dragon_3,
            "Apple","Orange",
            "Kiwi","Dragon Fruit",
            4)
        questionList.add(question3)

        val question4 = Question(4,
            "What's the fruit name?",
            R.drawable.avogado_4,
            "Avogado","Orange",
            "Kiwi","Dragon Fruit",
            1)
        questionList.add(question4)

        val question5 = Question(5,
            "What's the fruit name?",
            R.drawable.strawberry_5,
            "Apple","Strawberry",
            "Kiwi","Dragon Fruit",
            2)
        questionList.add(question5)

        val question6 = Question(1,
            "What's the fruit name?",
            R.drawable.pineapple_6,
            "Apple","Orange",
            "Kiwi","Pineapple",
            4)
        questionList.add(question6)
        return questionList
    }
}