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
import android.app.Activity
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts

class RequestFragment : Fragment() {

    private var _binding: FragmentRequestBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val RECORD_REQUEST_CODE = 1000

    //ギャラリーから送られた画像をivStorageの画像にする
    private val launcher = registerForActivityResult(ActivityResultContracts.OpenDocument()) {
        binding.ivStorage.setImageURI(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val newViewModel =
            ViewModelProvider(this).get(RequestViewModel::class.java)

        _binding = FragmentRequestBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //spinner処理
        var spinner = binding.spinnerRequestRank
        var RequestRank = binding.ivRequestRank
        val items = resources.getStringArray(R.array.request_ranks)
        val Adapter =
            context?.let { ArrayAdapter(it,android.R.layout.simple_spinner_dropdown_item, items) }
        spinner.adapter = Adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val rank = parent.getItemAtPosition(position)
                when(rank) {
                    "A" -> RequestRank.setImageResource(R.drawable.ic_button_a_108dp)
                    "B" -> RequestRank.setImageResource(R.drawable.ic_button_b_108dp)
                    "C" -> RequestRank.setImageResource(R.drawable.ic_button_c_108dp)
                    "D" -> RequestRank.setImageResource(R.drawable.ic_button_d_108dp)
                    "E" -> RequestRank.setImageResource(R.drawable.ic_button_e_108dp)
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        //ギャラリーにアクセスしていいかのパーミッション設定
        setupPermissions()

        //buttonStorageが押されるとギャラリーに遷移する
        binding.buttonStorage.setOnClickListener {
            launcher.launch(arrayOf("image/*"))
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //パーミッションのチェックを設定するためのメソッド
    private fun setupPermissions() {
        val permission = context?.let {
            ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }
    //パーミッションをリクエストするためのメソッド
    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            RECORD_REQUEST_CODE)
    }

    //パーミッションの許可の結果による実行されるメソッド
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when(requestCode){
            RECORD_REQUEST_CODE ->{
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, "デバイス内の写真やメディアへのアクセスが許可されませんでした。", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "デバイス内の写真やメディアへのアクセスが許可されました。", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
}

/*

//依頼名の入力
val requestName : EditText = binding.etRequestName
//

//送信ボタン
val ButtonSendRequest : Button = binding.buttonSendRequest




//入力された後の処理(EditTextに入力されたテキストはEditText.text.toString()で取得)
ButtonSendRequest.setOnClickListener {
    val t : String = requestName.text.toString().trim()
    if(t.isEmpty()) {
        //ActivityとFragmentではToastの記述が変わり、getActivityが必要
        Toast.makeText(getActivity(), "エラー：依頼名を入力してください", Toast.LENGTH_SHORT).show()
    }else {
        //送信処理
    }
}


 */

