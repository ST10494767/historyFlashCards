package vcmsa.ci.flashcards

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        Log.d(TAG, "MainActivity created")

        // Initialize the Start button
        val btnStart = findViewById<Button>(R.id.btnStart)

        // Set OnClickListener for the Start button
        btnStart.setOnClickListener {
            Log.d(TAG, "Start button clicked")
            // Create intent to navigate to QuestionActivity
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        }
    }
}

