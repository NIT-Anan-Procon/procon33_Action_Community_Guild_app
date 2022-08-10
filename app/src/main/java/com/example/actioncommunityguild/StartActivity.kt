package com.example.actioncommunityguild

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.actioncommunityguild.databinding.ActivityStartBinding
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //intent時の処理
        binding.buttonStart.setOnClickListener {
            val intent = Intent(application, MainActivity::class.java)
            val editText = binding.etUserName.text.toString().trim()
            if(editText.isEmpty()){
                Toast.makeText(this, "エラー：ユーザー名を入力してください", Toast.LENGTH_SHORT).show()
            }else {
                intent.putExtra("VALUE", editText)
                startActivity(intent)
            }
        }
    }
}