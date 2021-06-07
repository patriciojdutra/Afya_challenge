package br.com.dutra.patricio.tvmaze.extensions

import android.view.View

fun View.setVisible(value:Boolean){
    if(value)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE

}

