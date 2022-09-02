package com.example.actioncommunityguild.ui.judge

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.actioncommunityguild.databinding.FragmentJudgeBinding

class JudgeFragment : Fragment() {

    private var _binding: FragmentJudgeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val judgeViewModel =
            ViewModelProvider(this).get(JudgeViewModel::class.java)

        _binding = FragmentJudgeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val webView = binding.webView
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        //localhostへ接続するにはurlを「http://localhost:8080/~~」から「http://10.0.2.2:8080/~~」に変える必要がある。
        webView.loadUrl("http://202.231.44.30:8081/Judge/judge.php")

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}