package vcmsa.ci.flashcards

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReviewActivity : AppCompatActivity() {

    private val TAG = "ReviewActivity"

    private lateinit var questions: Array<String>
    private lateinit var correctAnswers: BooleanArray
    private lateinit var userAnswersCorrect: BooleanArray
    private var currentIndex = 0

    private lateinit var txtQuestion: TextView
    private lateinit var txtAnswer: TextView
    private lateinit var txtStatus: TextView
    private lateinit var btnPrevious: Button
    private lateinit var btnNext2: Button
    private lateinit var btnBackToScore: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)

        Log.d(TAG, "ReviewActivity created")

        questions = intent.getStringArrayExtra("QUESTIONS") as Array<String>
        correctAnswers = intent.getBooleanArrayExtra("CORRECT_ANSWERS") as BooleanArray
        userAnswersCorrect = intent.getBooleanArrayExtra("USER_ANSWERS_CORRECT") as BooleanArray

        var txtQuestion = findViewById<TextView>(R.id.txtQuestion)
        var txtAnswer = findViewById<TextView>(R.id.txtAnswer)
        var btnPrevious = findViewById<Button>(R.id.btnPrevious)
        var btnNext2 = findViewById<Button>(R.id.btnNext2)
        var btnBackToScore = findViewById<Button>(R.id.btnBackToScore)



        btnPrevious.setOnClickListener {
            Log.d(TAG, "Previous button clicked")
            if (currentIndex > 0) {
                currentIndex--
            }
        }

        btnNext2.setOnClickListener {
            Log.d(TAG, "Next button clicked")
            if (currentIndex < questions.size - 1) {
                currentIndex++
            }
        }

        btnBackToScore.setOnClickListener {
            Log.d(TAG, "Back to Score button clicked")
            finish()
        }

         fun updateReviewUI() {
            // Display the current question
            txtQuestion.text = "Question ${currentIndex + 1}: ${questions[currentIndex]}"

            // Display the correct answer
            val answerText = if (correctAnswers[currentIndex]) "True" else "False"
            txtAnswer.text = "Correct Answer: $answerText"

            // Display if the user got it right
            val statusText = if (userAnswersCorrect[currentIndex]) {
                "You answered correctly!"
            } else {
                "You answered incorrectly."
            }
            txtStatus.text = statusText

            // Update button states
            btnPrevious.isEnabled = currentIndex > 0
            btnNext2.isEnabled = currentIndex < questions.size - 1

            Log.d(TAG, "Review UI updated for question $currentIndex")
        }










        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}