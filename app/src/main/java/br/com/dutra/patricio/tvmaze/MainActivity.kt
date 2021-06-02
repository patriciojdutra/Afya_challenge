package br.com.dutra.patricio.tvmaze

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.dutra.patricio.tvmaze.ui.movie.MovieListActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this, MovieListActivity::class.java))
        finish()

    }
}