package com.ir.smkcoding

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ir.smkcoding.model.ResultsItem
import kotlinx.android.synthetic.main.item_movie.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick

class movieAdapter (val list : List<ResultsItem?>?,
                    val context: Context
): RecyclerView.Adapter<movieAdapter.ViewHolder>(){

    lateinit var itemView: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        itemView = LayoutInflater.from(context)
            .inflate(R.layout.item_movie, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        holder.bind(context, list?.get(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context, MovieModel: ResultsItem?) {
            itemView.tv_title.text = MovieModel?.title

            Glide.with(context).load("https://image.tmdb.org/t/p/w500"+MovieModel?.posterPath).into(itemView.iv_poster)

            itemView.onClick {
                itemView.context.startActivity(
                    itemView.context.intentFor<detailMovieActivity>("movie" to MovieModel)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                )
            }
        }

    }

}