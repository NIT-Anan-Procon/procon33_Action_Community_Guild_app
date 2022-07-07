package com.example.actioncommunityguild

//MainActivityは司令塔の役割をする。他のフラグメント(dashboard,home,notifications)を制御する

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.actioncommunityguild.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //下部ナビゲーションのレイアウトIDを設定
        //ビューバインディングを有効にしている(findViewByIdを使いやすくしたもの)
        //findViewById(R.id.nav_view) と binding.navView は同じ意味
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_new
            )
        )
        //アクションバーの文字列（記述しなければプロジェクト名が表示）を各フラグメントの文字列に変更するコード
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}