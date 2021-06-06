package br.com.dutra.patricio.tvmaze.ui.moviedetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import br.com.dutra.patricio.tvmaze.R
import br.com.dutra.patricio.tvmaze.databinding.ActivityMovieDetailsBinding
import br.com.dutra.patricio.tvmaze.extensions.load
import br.com.dutra.patricio.tvmaze.model.Episode
import br.com.dutra.patricio.tvmaze.model.Movie
import br.com.dutra.patricio.tvmaze.ui.episode.EpisodeActivity
import br.com.dutra.patricio.tvmaze.ui.movie.MovieListActivity
import br.com.dutra.patricio.tvmaze.util.Alerta
import br.com.dutra.patricio.tvmaze.viewmodel.MovieDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList

class MovieDetailsActivity : AppCompatActivity() {


    private val viewModel: MovieDetailsViewModel by viewModel()
    private val binding by lazy{ ActivityMovieDetailsBinding.inflate(layoutInflater) }
    private val movie: Movie by lazy {
        intent.getSerializableExtra("movie") as Movie
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()
        observes()
        init()
    }

    private fun setToolbar() {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(movie.name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun init(){

        viewModel.getEpisode(movie.id)

        binding.imgViewMovieDetails.load(movie.image.original, this)

        var time: String
        movie.schedule.let { schedule ->
            schedule?.time.let {time = " - $it"}
            schedule?.days.let { days ->
                days?.forEach {
                    val textView = TextView(this)
                    textView.text = "$it$time"
                    binding.layoutSchedule.addView(textView)
                }
            }
        }

        movie.genres.let { genres ->
            genres.forEach {
                val textView = TextView(this)
                textView.text = it
                binding.layoutGenres.addView(textView)
            }
        }

        movie.summary.let {
            val textView = TextView(this)
            textView.text = it
            binding.layoutSummary.addView(textView)
        }

    }

    private fun observes() {
        viewModel.episodeList.observe(this, {
            loadSeason(it)
        })

        viewModel.errorMessage.observe(this, {
            Alerta.aviso(it, this) { finishAffinity() }
        })

    }

    private fun loadSeason(list: ArrayList<Episode>?) {

        val maxSeason = list?.maxByOrNull { e -> e.season }
        val quantSeason = maxSeason?.season?:0

        for ( i in 1..quantSeason){

            val seasons = list?.filter { e -> e.season == i }

            val view = LayoutInflater.from(this).inflate(R.layout.season_list,null) as LinearLayout
            view.findViewById<TextView>(R.id.txtTitle).text = "Temporada ${i}"

            seasons?.forEach { episode ->

                val viewEpisode = LayoutInflater.from(this).inflate(R.layout.episode_item,null) as LinearLayout
                viewEpisode.findViewById<TextView>(R.id.txtNameEpisode).text = "${episode.number} ${episode.name}"
                viewEpisode.findViewById<Button>(R.id.btnWatch).setOnClickListener {
                    Toast.makeText(this, episode.name, Toast.LENGTH_LONG ).show()
                    episode.movieName = movie.name
                    var intent  = Intent(this, EpisodeActivity::class.java)
                    intent.putExtra("episode",episode)
                    startActivity(intent)
                }

                view.findViewById<LinearLayout>(R.id.layoutEpisodes).addView(viewEpisode)
            }

            binding.layoutSeasons.addView(view)

        }
    }
}