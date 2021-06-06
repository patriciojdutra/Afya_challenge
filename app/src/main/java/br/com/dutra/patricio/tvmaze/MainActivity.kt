package br.com.dutra.patricio.tvmaze

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.dutra.patricio.tvmaze.ui.movie.MovieListActivity
import br.com.dutra.patricio.tvmaze.util.BiometricUtil
import br.com.dutra.patricio.tvmaze.util.PreferencesUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val authenticationEnable =PreferencesUtil.getBoolean(this,"Authentication_Enable",false)

        if(authenticationEnable){
             val biometricUtil = BiometricUtil()
            biometricUtil.startBiometricPrompt(this){
                startActivity(Intent(this, MovieListActivity::class.java))
                finish()
            }
            biometricUtil.authenticate(this)
        }else {
            startActivity(Intent(this, MovieListActivity::class.java))
            finish()
        }

    }
}