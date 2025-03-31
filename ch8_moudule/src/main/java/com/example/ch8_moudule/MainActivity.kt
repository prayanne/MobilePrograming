package com.example.ch8_moudule

import android.app.Activity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch8_moudule.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var initTime = 0L
    var pauseTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("current", (System.currentTimeMillis()).toString())
        Log.d("elapsed", (SystemClock.elapsedRealtime()).toString())
        binding.start.setOnClickListener{
            binding.chro.base = SystemClock.elapsedRealtime() + pauseTime
            binding.chro.start()

            binding.stop.isEnabled = true
            binding.reset.isEnabled = true
            binding.start.isEnabled = true
        }
        binding.stop.setOnClickListener{
            pauseTime = binding.chro.base - SystemClock.elapsedRealtime()
            binding.chro.stop()
            binding.stop.isEnabled = false
            binding.reset.isEnabled = true
            binding.start.isEnabled = true
        }
        binding.reset.setOnClickListener{
            pauseTime = 0L
            binding.chro.base = SystemClock.elapsedRealtime()
            binding.chro.stop()
            binding.stop.isEnabled = false
            binding.reset.isEnabled = false
            binding.start.isEnabled = true
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
                if(System.currentTimeMillis() - initTime > 3000) {
                    Toast.makeText(this, "one More Back Button!", Toast.LENGTH_SHORT).show()
                    initTime = System.currentTimeMillis()
                    return true
                }
        }
        return super.onKeyDown(keyCode, event)
    }
}