package com.example.actioncommunityguild

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.actioncommunityguild.databinding.ActivityMoviePlayerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class MoviePlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviePlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_player)

        binding = ActivityMoviePlayerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //----動画再生処理----
        //画面遷移元から動画IDを受け取り変数textに代入
        val text = intent.getStringExtra("MOVIEID_KEY")
        val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = text
                //第一引数は動画のID, 第二引数は再生開始位置
                if (videoId != null) {
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            }
        })
        binding.tvRequestName.text = intent.getStringExtra("REQUESTNAME_KEY")
        binding.tvRequestUserName.text = "Requested by " + intent.getStringExtra("REQUESTUSERNAME_KEY")

    }
}