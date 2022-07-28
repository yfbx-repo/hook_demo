package com.yfbx.hookdemo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText = findViewById<EditText>(R.id.pkg_txt)
        val okButton = findViewById<Button>(R.id.ok_btn)

        editText.setText(App.hookPackage)
        okButton.setOnClickListener {
            App.hookPackage = editText.text.toString().trim()
            Toast.makeText(this, "package saved", Toast.LENGTH_LONG).show()
        }
    }

}

