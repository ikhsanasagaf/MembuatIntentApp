package com.example.myintentapp

import android.app.Instrumentation
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvResult: TextView

    companion object{
        private const val REQUEST_CODE = 100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveActivityWithDataActivity: Button = findViewById(R.id.btn_move_activity_data)
        btnMoveActivityWithDataActivity.setOnClickListener(this)

        val btnMoveWithObject:Button = findViewById(R.id.btn_move_activity_object)
        btnMoveWithObject.setOnClickListener(this)

        val btnDialPhone: Button = findViewById(R.id.btn_dial_number)
        btnDialPhone.setOnClickListener(this)

        val btnMoveForResult: Button = findViewById(R.id.btn_move_for_result)
        btnMoveForResult.setOnClickListener(this)

        tvResult = findViewById(R.id.tv_result);
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.btn_move_activity -> {
                val moveIntent = Intent(this@MainActivity, moveActivity::class.java)
                startActivity(moveIntent)
            }

            R.id.btn_move_activity_data -> {
                val moveWithDataIntent = Intent(this@MainActivity,
                    moveWithDataActivity::class.java)
                moveWithDataIntent.putExtra(moveWithDataActivity.EXTRA_NAME,
                    "Muhammad Ikhsan Asagaf")
                moveWithDataIntent.putExtra(moveWithDataActivity.EXTRA_AGE, 16)
                startActivity(moveWithDataIntent)
            }

            R.id.btn_move_activity_object -> {
                val person = Person(
                    "Ikhsan Asagaf",
                    16,
                    "ikhsan.asagaf2012@gmail.com",
                    "Cilacap"
                )

                val moveWithObjectIntent = Intent(this@MainActivity, moveWithObjectActivity::class.java)
                moveWithObjectIntent.putExtra(moveWithObjectActivity.EXTRA_PERSON, person)
                startActivity(moveWithObjectIntent)
            }

            R.id.btn_dial_number -> {
                val phoneNumber = "082256158707"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }

            R.id.btn_move_for_result -> {
                val moveForResultIntent = Intent(this@MainActivity, moveForResultActivity::class.java)
                startActivityForResult(moveForResultIntent, REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE){
            if(resultCode == moveForResultActivity.RESULT_CODE){
                val selectedValue =data?.getIntExtra(moveForResultActivity.EXTRA_SELECTED_VALUE, 0)
                tv_result.text = "Hasil : $selectedValue"
            }
        }
    }
}