package vcmsa.ci.flashcards

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScoreActivity : AppCompatActivity() {

    private val TAG = "ScoreActivity"

    private var score = 0
    private var totalQuestions = 0
    private lateinit var questions: Array<String>
    private lateinit var correctAnswers: BooleanArray
    private lateinit var userAnswersCorrect: BooleanArray



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)

        Log.d(TAG, "ScoreActivity created")

        score = intent.getIntExtra("SCORE", 0)
        totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 0)
        questions = intent.getStringArrayExtra("QUESTIONS") as Array<String>
        correctAnswers = intent.getBooleanArrayExtra("CORRECT_ANSWERS") as BooleanArray
        userAnswersCorrect = intent.getBooleanArrayExtra("USER_ANSWERS_CORRECT") as BooleanArray

        Log.d(TAG, "Final score: $score/$totalQuestions")

        val txtScore = findViewById<TextView>(R.id.txtScore)
        val txtFeedback2 = findViewById<TextView>(R.id.txtFeedback2)
        val btnReview = findViewById<Button>(R.id.btnReview)
        val btnExit = findViewById<Button>(R.id.btnExit)

        txtScore.text = "Your Score: $score out of $totalQuestions"


        if (score >= 3) {
            txtFeedback2.text = "Well done! You're such an historian."
        } else {
            txtFeedback2.text = "Keep working hard! You got this."
        }

        btnReview.setOnClickListener {
            Log.d(TAG, "Review button clicked")
            val intent = Intent(this, ReviewActivity::class.java)

            intent.putExtra("QUESTIONS", questions)
            intent.putExtra("CORRECT_ANSWERS", correctAnswers)
            intent.putExtra("USER_ANSWERS_CORRECT", userAnswersCorrect)

            startActivity(intent)
        }

        btnExit.setOnClickListener {
            Log.d(TAG, "Exit button clicked")
            finishAffinity()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}


