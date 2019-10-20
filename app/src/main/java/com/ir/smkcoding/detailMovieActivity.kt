package com.ir.smkcoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.util.rangeTo
import com.bumptech.glide.Glide
import com.ir.smkcoding.database.database
import com.ir.smkcoding.database.database_contrac
import com.ir.smkcoding.model.ResultsItem
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.activity_profile.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class detailMovieActivity : AppCompatActivity() {

    var isMovieFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val movie =
            intent.getParcelableExtra<ResultsItem>("movie")

        tv_title_movie.text = movie.title
        tv_rating_movie.text = "Rating : ${movie.voteAverage}"
        tv_description_movie.text = movie.overview
        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + movie?.posterPath)
            .into(iv_poster_movie)

        //untuk memeriksa apakah movie favorite atau tidak
        checkMovieFavorite(movie)

        btnfv.onClick {
            /*
            jka Favorite maka btn difungsikan utk mengapus dari daftar favorite
            jika bukan Favorite maka btn difungsikan untuk memasukan ke daftar favorite
             */
            if (isMovieFavorite) {
                deleteMovieFromFavorite(movie)
            } else {
                addMovieToFavorite(movie)
            }
        }
    }

    private fun deleteMovieFromFavorite(movie: ResultsItem?) {
    database.use{
        delete(ResultsItem.TABLE_FAVORITE,
        "$(ResultsItem.COLUMN TITLE} = {title}",
        "title" to movie?.title.toString()
    )

     toast("movie dihapus dari daftar favorite")
        isMovieFavorite = false
        btnfv.text = "Tambah Favorite"
        }
    }

    private fun addMovieToFavorite(movie: ResultsItem?) {
        database.use {
            insert(
                ResultsItem.TABLE_FAVORITE,
                ResultsItem.COLUMN_TITLE to movie?.title,
                ResultsItem.COLUMN_POSTERPATH to movie?.posterPath,
                ResultsItem.COLUMN_RATING to movie?.popularity,
                ResultsItem.COLUMN_DESCRIPTION to movie?.overview
            )
            toast("Berhasil Ditambah ke Favorite!!")

            isMovieFavorite = true
            btnfv.text = "Hapus Favorite"
        }
    }

    private fun checkMovieFavorite(movie: ResultsItem) {
        //TODO pengecekan film ini sudah favorite kah belum
        database.use {
            val isFavorite = select(
                ResultsItem.TABLE_FAVORITE,
                ResultsItem.COLUMN_TITLE
            )
                .whereArgs(
                    ResultsItem.COLUMN_TITLE + " = {title}",
                    "title" to movie?.title.toString()
                )
            val dataMovie: database_contrac? = isFavorite.parseOpt(classParser<database_contrac>())

            Log.d("FAVORITEMOVIE", dataMovie.toString())

            if (dataMovie != null) {
                isMovieFavorite = true
                btnfv.text = "Hapus Favorite"
            } else {
                isMovieFavorite = false
                btnfv.text = "Tambah Favorite"
            }
        }
        toast(isMovieFavorite.toString())
    }
}


