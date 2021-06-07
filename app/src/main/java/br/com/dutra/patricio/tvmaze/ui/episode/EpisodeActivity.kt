package br.com.dutra.patricio.tvmaze.ui.episode

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import br.com.dutra.patricio.tvmaze.R
import br.com.dutra.patricio.tvmaze.databinding.ActivityEpisodeBinding
import br.com.dutra.patricio.tvmaze.extensions.load
import br.com.dutra.patricio.tvmaze.extensions.summary
import br.com.dutra.patricio.tvmaze.model.Episode
import br.com.dutra.patricio.tvmaze.util.BaseActivity
import br.com.dutra.patricio.tvmaze.util.Constants

class EpisodeActivity : BaseActivity() {

    private val binding by lazy{ ActivityEpisodeBinding.inflate(layoutInflater) }
    private val episode: Episode? by lazy {
        intent.getSerializableExtra(Constants.keyEpisode) as Episode
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()
        init()
    }

    override fun setToolbar() {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(episode?.movieName?:"")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    @SuppressLint("SetTextI18n")
    override fun init() {
        episode.let { it ->
            it?.image.let {image ->
                binding.imgPosterEpisode.load(image?.original?:"",this)
            }

            val textViewNumberAndName = TextView(this)
            textViewNumberAndName.text = it?.number.toString() + getString(R.string.separator_text) + it?.name
            binding.layoutEpisode.addView(textViewNumberAndName)

            val textViewSeason = TextView(this)
            textViewSeason.text = "${getString(R.string.season)} ${it?.season}"
            binding.layoutSeason.addView(textViewSeason)

            val textViewSummary = TextView(this)
            it?.let { it1 -> textViewSummary.summary(it1.summary) }
            binding.layoutSummary.addView(textViewSummary)
        }
    }
}