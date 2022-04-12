package com.escan.kendy


import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


import java.util.*



class MainActivity : AppCompatActivity() {

    var score = 0
    val imageArray = ArrayList<ImageView>()
     var handler:Handler = Handler(Looper.myLooper()!!)
    lateinit var runnable: Runnable
    lateinit var countDownTimer: CountDownTimer
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root //Layout ile çalışacağımız belirtiyor
        setContentView(view)

        //gridLayout içinde olan itemlerin sayısını alıyor.
        val count : Int = (binding.gridLayout.getChildCount() -1)
        // 0'dan başla count a kadar
        for(index in 0..count){
            imageArray.add(binding.gridLayout.getChildAt(index) as ImageView)
        }
        hideImages()

        countDownTimer = object : CountDownTimer(15000,1000) {
            override fun onFinish() {
                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                binding.textView.text = "0"
                binding.textView2.text = "Score: 0"
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Are you want restart ?")
                alert.setMessage("Your Score: $score")
                alert.setPositiveButton("Yes"){ dialog,which ->
                    // Restart
                    val intent = intent
                    finish()
                    startActivity(intent)
                    handler.removeCallbacks(runnable)

                }
                alert.setNegativeButton("No"){ dialog,which ->
                    Toast.makeText(this@MainActivity, "GameOver", Toast.LENGTH_SHORT).show()
                    finish()
                }
                alert.show()
            }

            override fun onTick(p0: Long) {
                binding.textView.text = "${p0/1000}"

            }
        }.start()
    }

    fun increaseScore(view: View){
        score = score + 1
        binding.textView2.text = "Score: $score"

    }

    fun hideImages(){
        runnable = object : Runnable {
            override fun run() {
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                var random = Random()
                var randomIndex = random.nextInt(15)
                while (imageArray[randomIndex].equals(randomIndex)){

                }
                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable,550)
            }
        }
            handler.post(runnable)
    }
}