package br.com.dutra.patricio.tvmaze.extensions

import android.view.View
import android.widget.TextView

fun TextView.summary(summary:String){
    this.text = summary.replace("<p>","")
            .replace("</p>","")
            .replace("<b>","")
            .replace("</b>","")
}

