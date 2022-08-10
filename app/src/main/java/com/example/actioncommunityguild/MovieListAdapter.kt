package com.example.actioncommunityguild

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.actioncommunityguild.ui.movies.MoviesFragment

class MovieListAdapter(val context: Context, val MovieList: ArrayList<Movie>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_movie, null)
        val RequestName = view.findViewById<TextView>(R.id.tv_request_name)
        val ReqeustUserName = view.findViewById<TextView>(R.id.tv_request_user_name)
        val ImageIcon = view.findViewById<ImageView>(R.id.imageIcon)

        val movie = MovieList[position]

        RequestName.text = movie.requestName
        ReqeustUserName.text = "Requested by " + movie.requestUserName

        when(movie.requestRank) {
            "A" -> ImageIcon.setImageResource(R.drawable.ic_button_a_108dp)
            "B" -> ImageIcon.setImageResource(R.drawable.ic_button_b_108dp)
            "C" -> ImageIcon.setImageResource(R.drawable.ic_button_c_108dp)
            "D" -> ImageIcon.setImageResource(R.drawable.ic_button_d_108dp)
            "E" -> ImageIcon.setImageResource(R.drawable.ic_button_e_108dp)
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return MovieList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return MovieList.size
    }

    fun clear() {
        MovieList.clear()
    }
}