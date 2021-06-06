package br.com.dutra.patricio.tvmaze.ui.episode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import br.com.dutra.patricio.tvmaze.R
import br.com.dutra.patricio.tvmaze.databinding.ActivityEpisodeBinding
import br.com.dutra.patricio.tvmaze.extensions.load
import br.com.dutra.patricio.tvmaze.model.Episode

class EpisodeActivity : AppCompatActivity() {

    private val binding by lazy{ ActivityEpisodeBinding.inflate(layoutInflater) }
    private val episode: Episode by lazy {
        intent.getSerializableExtra("episode") as Episode
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()
        init()
    }

    private fun setToolbar() {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(episode.movieName)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun init() {

        binding.imgPosterEpisode.load(episode.image.original, this)

        episode.let {

            var textViewNumberAndName = TextView(this)
            textViewNumberAndName.text = "${it.number} - ${it.name}"
            binding.layoutEpisode.addView(textViewNumberAndName)

            var textViewSeason = TextView(this)
            textViewSeason.text = "Temporada ${it.season}"
            binding.layoutSeason.addView(textViewSeason)

            var textViewSummary = TextView(this)
            textViewSummary.text = it.summary
            binding.layoutSummary.addView(textViewSummary)

        }
    }


}