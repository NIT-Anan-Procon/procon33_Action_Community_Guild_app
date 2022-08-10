package com.example.actioncommunityguild.ui.receive

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.actioncommunityguild.databinding.FragmentReceiveBinding

class ReceiveFragment : Fragment() {

    private var _binding: FragmentReceiveBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val receiveViewModel =
            ViewModelProvider(this).get(ReceiveViewModel::class.java)

        _binding = FragmentReceiveBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val webView = binding.webView
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl("http://10.0.2.2/entry02/dancing.html")
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}