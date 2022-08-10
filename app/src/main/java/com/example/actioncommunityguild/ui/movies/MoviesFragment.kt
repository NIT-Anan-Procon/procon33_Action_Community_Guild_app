package com.example.actioncommunityguild.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.actioncommunityguild.Movie
import com.example.actioncommunityguild.MovieListAdapter
import com.example.actioncommunityguild.MoviePlayerActivity
import com.example.actioncommunityguild.databinding.FragmentMoviesBinding
import java.text.FieldPosition

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val moviesViewModel =
            ViewModelProvider(this).get(MoviesViewModel::class.java)

        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Movieインスタンスを格納した配列
        val MovieList = arrayListOf<Movie>(
            Movie(0,"みんなで自作バトルアニメを作る","tanaka", "A", "LxsQZNTFfxc"),
            Movie(1,"人間の禁忌に触れる", "shaba-ni", "A", "_OuJy-aje8A"),
            Movie(2,"バレンタインまでソーラン節を踊り続ける","forger Magbel", "B", "NwSriMki_UA"),
            Movie(3,"全員でゴッドタレントに応募する", "simon", "C", "PQQVopTF5AI"),
            Movie(4,"うまい棒買い占めてみた", "ni-chan", "E", "VfI2AZ22zwY"),
            Movie(5,"クソ特技グランプリ", "hiro", "D", "HhWB7X2Mgyk"),
            Movie(6,"クソ理系しりとり頂上決戦", "chachamaru", "C", "YtTlPAMp8po"),
            Movie(7,"NHKの集金業者に大人の対応見せつける", "tachibana", "B", "ypt_85x8rIk"),
            Movie(8,"自作バトルアニメをつくる２", "tanaka", "A", "BoZ0Zwab6Oc"),
            Movie(9,"高クオリティ音MADみんなでつくる", "gigan", "B", "zksBNXz7bUs"),
            Movie(10,"【多分野】専門用語解説してみた", "服従太郎", "C", "S80_mDJZ5Rk"),
            Movie(11,"令和版湘南浜清掃事業", "2ch伝道師", "D", "veEq31AesGo"),
        )
        //検索して出てくるMovieインスタンスを格納する予定の配列
        val filter = arrayListOf<Movie>()
        //listViewのidを取得
        var listView = binding.listView
        //アダプター(MovieListAdapter)にMovieListを導入
        //アダプターというのは、リストビューとデータのアイテム間を連結する役割を持つ
        // 普通の書き方：val Adapter = MovieListAdapter(this,MovieList) Fragmentではエラーが発生
        val Adapter = context?.let { MovieListAdapter(it, MovieList) }
        //listViewにアダプターを設定
        listView.adapter = Adapter

        //★onClickMovie()でつかうためのprimaryKeyが入る配列
        val primaryKeys = arrayListOf<Int>()
        for(index in 0..MovieList.size) primaryKeys.add(index)

        //新しくMovieListAdapterに空の情報を導入
        val result = context?.let {MovieListAdapter(it, filter)}
        //searchViewのidを取得
        val search = binding.searchView
        //searchViewのイベントリスナー
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //検索ボタンが押された時に呼び出されるメソッド
            override fun onQueryTextSubmit(p0: String?): Boolean {
                //処理を行わないのでfalseを返す
                return false
            }
            //入力文字が変更される度に呼び出されるメソッド
            override fun onQueryTextChange(p0: String?): Boolean {
                //入力された文字列をRegexに代入
                val regex = Regex(p0.toString())
                //前回行った結果がまた表示されてしまうので一度resultのアダプターを初期化
                if (result != null) {
                    result.clear()
                    //★onClickMovie()でつかうためのprimaryKeyが入る配列のの初期化
                    primaryKeys.clear()
                }
                //MovieListのサイズだけループする
                for(item in 0 until MovieList.size) {
                    //Regexに代入されていた値がMovieインスタンスのrequestNameと部分一致なら
                    if(regex.containsMatchIn(MovieList[item].requestName)) {
                        //空のMovie配列filterにMovieインスタンスを格納する
                        filter.add(MovieList[item])
                        //★onClickMovie()でつかうためのprimaryKeyを入れる配列に入れる
                        primaryKeys.add(MovieList[item].primaryKey)
                    }
                }
                //部分一致だけの配列filterが完成したらlistViewに新しくアダプターを設定
                listView.adapter = result
                //処理が必ず行われるのでtrueを返す
                return true
            }
        })

        //listViewが押された時の処理
        listView.setOnItemClickListener { parent, view, position, id ->
            //parent   : タップされたListView全体
            //view     : タップされた一行分の画面部品
            //position : タップされた行番号(0から始まる)
            //id       : DBのデータをもとにListViewを生成した際の主キーの値
            onClickMovie(MovieList,position, primaryKeys)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onClickMovie(MovieList: ArrayList<Movie>, position: Int, primaryKeys: ArrayList<Int>) {
        val intent = Intent(context, MoviePlayerActivity::class.java)
        intent.putExtra("REQUESTNAME_KEY", MovieList[primaryKeys[position]].requestName)
        intent.putExtra("REQUESTUSERNAME_KEY", MovieList[primaryKeys[position]].requestUserName)
        intent.putExtra("MOVIEID_KEY", MovieList[primaryKeys[position]].movieId)
        startActivity(intent)
    }
}