package com.escan.kendy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var score = 0
    val imageArray = ArrayList<ImageView>()
    lateinit var handler:Handler
    lateinit var runnable: Runnable
    lateinit var countDownTimer: CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val count : Int = (gridLayout.getChildCount() -1)
        for(index in 0..count){
            imageArray.add(gridLayout.getChildAt(index) as ImageView)
        }
        hideImages()

        countDownTimer = object : CountDownTimer(15000,1000) {
            override fun onFinish() {
                textView.text = "0"
                textView2.text = "Score: 0"

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Are you want restart ?")
                alert.setPositiveButton("Yes"){ dialog,which ->
                    // Restart
                    val intent = intent
                    finish()
                    startActivity(intent)

                }
                alert.setNegativeButton("No"){ dialog,which ->
                    Toast.makeText(this@MainActivity, "GameOver", Toast.LENGTH_SHORT).show()
                    finish()
                }
                alert.show()
            }

            override fun onTick(p0: Long) {
                textView.text = "Score: ${p0/1000}"
            }
        }.start()
    }

     fun hideImages(){
        for (image in imageArray){
            image.visibility = View.INVISIBLE
        }
        val random = Random()
        val randomIndex = random.nextInt(15)
        imageArray[randomIndex].visibility = View.VISIBLE
    }
    
    fun increaseScore(view: View){
        score = score + 1
        textView2.text = "$score"

    }
}
