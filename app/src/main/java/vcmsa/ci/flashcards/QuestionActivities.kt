package vcmsa.ci.flashcards

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuestionActivities : AppCompatActivity() {
    private val Tag = "QuestionActivities"

    private val historyQuestion = arrayOf(
        "Nelson Mandela became the president of South Africa in 1994.",
        "The Berlin Wall fell in 1991.",
        "World War II ended in 1945.",
        "The United States gained independence in 1776.",
        "The first human landed on the moon in 1975."
    )

    private val historyAnswers = arrayOf(
        true,
        false,
        true,
        true,
        false
    )

    private val userCorrectAnswer = BooleanArray(historyQuestion.size)

    private var currentQuestionIndex = 0
    private var score = 0

    private lateinit var txtQuestion: TextView
    private lateinit var txtFeedback: TextView
    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button
    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question_activities)


        val txtQuestion = findViewById<TextView>(R.id.txtQuestion)
        var txtFeedback = findViewById<TextView>(R.id.txtFeedback2)
        val btnFalse = findViewById<Button>(R.id.btnFalse)
        val btnTrue = findViewById<Button>(R.id.btnTrue)
        val btnNext = findViewById<Button>(R.id.btnNext2)

        txtFeedback.visibility = View.INVISIBLE
        btnNext.isEnabled = false
        val checkAnswer = null
        txtFeedback = checkAnswer


        btnTrue.setOnClickListener {
            Log.d(Tag, "True button clicked for question $currentQuestionIndex")
            check(true)
        }

        btnFalse.setOnClickListener {
            Log.d(Tag, "False button clicked for question $currentQuestionIndex")
            check(false)
        }


        btnNext.setOnClickListener {
            Log.d(Tag, "Next button clicked, moving to next question")
        }



        fun displayQuestion() {
            Log.d(Tag, "Displaying question: $currentQuestionIndex")
            txtQuestion.text = historyQuestion[currentQuestionIndex]

            txtFeedback?.visibility = View.INVISIBLE
            btnTrue.isEnabled = true
            btnFalse.isEnabled = true
            btnNext.isEnabled = false

            fun checkAnswer(userAnswer: Boolean) {
                val correctAnswer = historyAnswers[currentQuestionIndex]


                btnTrue.isEnabled = false
                btnFalse.isEnabled = false


                if (userAnswer == correctAnswer) {
                    txtFeedback?.text = "Correct!"
                    score++
                    userCorrectAnswer[currentQuestionIndex] = true
                    Log.d(Tag, "Answer correct! Score: $score")
                } else {
                    txtFeedback?.text = "Incorrect!"
                    userCorrectAnswer[currentQuestionIndex] = false
                    Log.d(Tag, "Answer incorrect. Score remains: $score")
                }

                txtFeedback?.visibility = View.VISIBLE
                btnNext.isEnabled = true


                fun moveToNextQuestion() {
                    currentQuestionIndex++


                    if (currentQuestionIndex < historyQuestion.size) {
                        displayQuestion()
                    } else {

                        Log.d(Tag, "All questions completed. Final score: $score")


                        fun navigateToScoreScreen() {
                            val intent = Intent(this, ScoreActivity::class.java)


                            intent.putExtra("SCORE", score)
                            intent.putExtra("TOTAL_QUESTIONS", historyQuestion.size)
                            intent.putExtra("QUESTIONS", historyQuestion)
                            intent.putExtra("CORRECT_ANSWERS", historyAnswers)
                            intent.putExtra("USER_ANSWERS_CORRECT", userCorrectAnswer)


                        }
                    }
                    startActivity(intent)
                    finish() // Close this activity
                }
            }
        }
    }
}


