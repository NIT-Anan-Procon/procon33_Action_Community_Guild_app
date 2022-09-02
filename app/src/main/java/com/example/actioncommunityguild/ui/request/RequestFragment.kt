package com.example.actioncommunityguild.ui.request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.actioncommunityguild.R
import com.example.actioncommunityguild.databinding.FragmentRequestBinding
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.webkit.*
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

var uploadMessage: ValueCallback<Array<Uri>>? = null

val REQUEST_SELECT_FILE = 100

class RequestFragment : Fragment() {

    private var _binding: FragmentRequestBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val newViewModel =
            ViewModelProvider(this).get(RequestViewModel::class.java)

        _binding = FragmentRequestBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val webView = binding.webView
        webView.webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(mWebView:WebView, filePathCallback:ValueCallback<Array<Uri>>, fileChooserParams: FileChooserParams):Boolean {
                if (uploadMessage != null) {
                    uploadMessage?.onReceiveValue(null)
                    uploadMessage = null
                }
                uploadMessage = filePathCallback
                val intent = fileChooserParams.createIntent()
                try {
                    startActivityForResult(intent, REQUEST_SELECT_FILE)
                } catch (e: ActivityNotFoundException) {
                    uploadMessage = null
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
        //localhostへ接続するにはurlを「http://localhost:8080/~~」から「http://10.0.2.2:8080/~~」に変える必要がある。
        webView.loadUrl("http://10.0.2.2:8080/procon33_ver2/procon33_Action_Community_Guild_WebApps/")

        return root
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_SELECT_FILE) {
            if (uploadMessage != null) {
                uploadMessage?.onReceiveValue(
                    WebChromeClient.FileChooserParams.parseResult(
                        resultCode,
                        data
                    )
                )
                uploadMessage = null
            }
        }
    }
}

