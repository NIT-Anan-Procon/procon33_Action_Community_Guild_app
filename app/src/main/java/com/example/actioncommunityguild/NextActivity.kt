package com.example.actioncommunityguild

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.actioncommunityguild.databinding.ActivityNextBinding
import com.example.actioncommunityguild.databinding.ActivityStartBinding

class NextActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNextBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNextBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //intent時の処理
        val button = binding.button
        button.setOnClickListener {
            val intent = Intent(application, MainActivity::class.java)
            startActivity(intent)
        }
        val webView = binding.webView
        webView.webViewClient = WebViewClient()
        //localhostへ接続するにはurlを「http://localhost:8080/~~」から「http://10.0.2.2:8080/~~」に変える必要がある。
        webView.loadUrl("http://202.231.44.30:8081/Login/assignment.php")
        webView.settings.javaScriptEnabled = true
    }
}