package com.ir.smkcoding

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ir.smkcoding.CONNECTION.MovieInterface
import com.ir.smkcoding.CONNECTION.config
import com.ir.smkcoding.model.ResultsItem
import com.singpaulee.made_dicoding.bank.Movie
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_moviek.view.*
import org.jetbrains.anko.support.v4.toast

class MoviekFragment : Fragment() {
    lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_moviek, container, false)

        getListMovie()

        /*   val list = Movie.listMovie as ArrayList<MovieModel>
           val layoutManager = LinearLayoutManager(activity)
           val adapter =
               movieAdapter(list, activity!!.applicationContext)

           rootView.rv_movie.apply {
               setLayoutManager(layoutManager)
               setAdapter(adapter)
        */
        return rootView
    }

    @SuppressLint("CheckResult")
    private fun getListMovie() {
        val movieNowPlaying =
            config.configRetrofit()
                .create(MovieInterface::class.java)
                .getlistMovieNowPlaying(
                    "fa7306775a14c3d2a4b6efbfb75eac9b"
                )

        movieNowPlaying
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response ->
                //menerima succes response
                val list = response.results
                val layoutmanager = LinearLayoutManager(activity)
                val adapter = movieAdapter(list, activity!!.applicationContext)

                rootView.rv_movie.apply {
                    layoutManager = layoutmanager
                    setAdapter(adapter)
                }

            }, {
                //menerima failure response
                toast("gagal")
            })
    }
}

