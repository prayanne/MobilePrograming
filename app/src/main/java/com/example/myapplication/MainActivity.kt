package com.example.myapplication

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.DialogInputBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        enableEdgeToEdge()
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()
        setSupportActionBar(binding.toolbar)

        val items = arrayOf("apple", "phich", "wm", "wtr")

        binding.btn1.setOnClickListener{
            DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    Log.d("text", "date: $year, ${month + 1}, $dayOfMonth")
                }
            }, 2025, 4, 1).show()
        }
        binding.btn2.setOnClickListener {
            TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    Log.d("text", "Time: $hourOfDay, $minute")
                }
            }, 15,30,true).show()
        }
        binding.btn3.setOnClickListener {
            AlertDialog.Builder(this).run{
                setTitle("hi")
                setIcon(android.R.drawable.ic_dialog_info)
                setMessage("exit?")
                setPositiveButton("Ok"){dialog, which -> Log.d("text", "ok")}
                setNegativeButton("cancle"){dialog, which -> Log.d("text", "cancle")}
                setNeutralButton("More"){dialog, which -> Log.d("text", "more")}
                show()
            }
        }
        binding.btn4.setOnClickListener {
            AlertDialog.Builder(this).run{
                setTitle("fu")
                setIcon(android.R.drawable.ic_dialog_info)
                setItems(items, object: DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Toast.makeText(applicationContext, "selected: ${items[which]}", Toast.LENGTH_LONG).show()
                    }
                })
                setPositiveButton("exit", null)
                show()
            }
        }
        binding.btn5.setOnClickListener {
            AlertDialog.Builder(this).run{
                setTitle("fu")
                setIcon(android.R.drawable.ic_dialog_info)
                setMultiChoiceItems(items, booleanArrayOf(true, false, false, false), object: DialogInterface.OnMultiChoiceClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
                        Toast.makeText(applicationContext, "${items[which]} is ${if (isChecked) "selected" else "unselected"}", Toast.LENGTH_LONG).show()
                    }
                })
                setPositiveButton("exit", null)
                setCancelable(false)
                show()
            }.setCanceledOnTouchOutside(false)
        }
        binding.btn6.setOnClickListener {
            AlertDialog.Builder(this).run{
                setTitle("fu")
                setIcon(android.R.drawable.ic_dialog_info)
                setSingleChoiceItems(items, 1){dialog, which -> Toast.makeText(applicationContext,
                    "${items[which]} is selected", Toast.LENGTH_SHORT).show()}
                setPositiveButton("exit", null)
                setCancelable(false)
                show()
            }.setCanceledOnTouchOutside(false)
        }
        binding.btnCst.setOnClickListener {
            val dialogBinding = DialogInputBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run{
                setTitle("input")
                setView(dialogBinding.root)
                setPositiveButton("exit"){_, _ ->
                    val message = dialogBinding.editText.text.toString() +
                            when(dialogBinding.gender.checkedRadioButtonId){
                                R.id.male   -> "(Male)"
                                R.id.female -> "(Female)"
                                else        -> "()"
                            }
                    Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
                }
                show()
            }
        }
//            ViewCompat.setOnApplyWindowInsetsListener(binding.drawer) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}