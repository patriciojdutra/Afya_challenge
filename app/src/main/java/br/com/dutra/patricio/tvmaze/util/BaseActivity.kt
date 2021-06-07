package br.com.dutra.patricio.tvmaze.util

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {

    protected open fun setToolbar() {}

    protected open fun observes() {}

    protected open fun init(savedInstanceState: Bundle?){}

    protected open fun init(){}

}