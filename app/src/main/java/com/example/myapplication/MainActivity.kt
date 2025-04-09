package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.shape.ShapeAppearanceModel.CornerSizeUnaryOperator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            thread {
                var sum = 0L
                var time = measureTimeMillis {
                    for ( i in 1..200000000){
                        SystemClock.sleep(1000)
                        sum += 1
                    }
                    runOnUiThread{
                        binding.editTextView.setText("sum: $sum")
                    }
                }
                Log.d("test", "time: $time")
            }
        }

        // coroutine
//        val channel = Channel<Int>()
//        val backgroundScope = CoroutineScope(Dispatchers.Default)
//
//        binding.button.setOnClickListener{
//            backgroundScope.launch {
//                var sum = 0L
//                var time = measureTimeMillis {
//                    for (i in 1..200000){
//                        sum += i
//                    }
//                }
//                channel.send(sum.toInt())
//                Log.d("checkit", "time: $time")
//            }
//        }
//
//        var mainScope = GlobalScope.launch {
//            channel.consumeEach {
//                binding.editTextView.setText("sum: ${it}")
//            }
//        }

        // thread
//        var handler = object :Handler(){
//            override fun handleMessage(msg: Message){
//                super.handleMessage(msg)
//                binding.editTextView.setText("sum: ${msg.arg1}")
//            }
//        }
//
//        binding.button.setOnClickListener{
//            thread {
//                var sum = 0L
//                var time = measureTimeMillis {
//                    for (i in 1..200000){
//                        sum += i
//                    }
//                }
//                val message = Message()
//                message.arg1 = sum.toInt()
//                handler.sendMessage(message)
//                Log.d("checkit", "time: $time")
//            }
//        }
    }
}