package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.CompoundButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.chk1.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                when(isChecked){
                    true -> {
                        Log.d("chk1", "chked")
                    }
                    false -> {
                        Log.d("chk1", "unchked")
                    }
                }
            }
        })

    }
    override fun onTouchEvent(event: MotionEvent): Boolean{
        val binding = ActivityMainBinding.inflate(layoutInflater)
        when (event.action){
            MotionEvent.ACTION_MOVE -> {
                binding.x.text = ("X: ${event.rawX}")
                binding.y.text = ("Y: ${event.rawY}")
                Log.d("test","X: ${event.rawX}, Y: ${ event.rawY }")
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onKeyDown(keyCode: Int, e: KeyEvent?): Boolean{
        when(keyCode){
            KeyEvent.KEYCODE_BACK -> Log.d("test", "press Back")
            KeyEvent.KEYCODE_VOLUME_UP -> Log.d("test", "VOLUME_UP")
            KeyEvent.KEYCODE_VOLUME_DOWN -> Log.d("test", "VOLUME_DOWN")
        }
        return super.onKeyDown(keyCode, e)
    }
}