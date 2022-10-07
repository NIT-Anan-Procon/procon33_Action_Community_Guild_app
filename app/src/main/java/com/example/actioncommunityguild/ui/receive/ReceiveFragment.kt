package com.example.actioncommunityguild.ui.receive

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.actioncommunityguild.databinding.FragmentReceiveBinding

var uploadMessage: ValueCallback<Array<Uri>>? = null

val REQUEST_SELECT_FILE = 100

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
        webView.webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(mWebView: WebView, filePathCallback:ValueCallback<Array<Uri>>, fileChooserParams: FileChooserParams):Boolean {
                if (com.example.actioncommunityguild.ui.request.uploadMessage != null) {
                    com.example.actioncommunityguild.ui.request.uploadMessage?.onReceiveValue(null)
                    com.example.actioncommunityguild.ui.request.uploadMessage = null
                }
                com.example.actioncommunityguild.ui.request.uploadMessage = filePathCallback
                val intent = fileChooserParams.createIntent()
                try {
                    startActivityForResult(intent,
                        com.example.actioncommunityguild.ui.request.REQUEST_SELECT_FILE
                    )
                } catch (e: ActivityNotFoundException) {
                    com.example.actioncommunityguild.ui.request.uploadMessage = null
                    return false
                }
                return true
            }
        }
        // これがないとwebView内でなくブラウザが起動してしまう
        webView.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }
        })
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        //localhostへ接続するにはurlを「http://localhost:8080/~~」から「http://10.0.2.2:8080/~~」に変える必要がある。
        webView.loadUrl("http://202.231.44.30:8081/Receive/receiveList.php")

        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == com.example.actioncommunityguild.ui.request.REQUEST_SELECT_FILE) {
            if (com.example.actioncommunityguild.ui.request.uploadMessage != null) {
                com.example.actioncommunityguild.ui.request.uploadMessage?.onReceiveValue(
                    WebChromeClient.FileChooserParams.parseResult(
                        resultCode,
                        data
                    )
                )
                com.example.actioncommunityguild.ui.request.uploadMessage = null
            }
        }
    }
}