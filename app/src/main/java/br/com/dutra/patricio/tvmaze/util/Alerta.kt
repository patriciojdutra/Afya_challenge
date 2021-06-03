package br.com.dutra.patricio.tvmaze.util

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import br.com.dutra.patricio.tvmaze.R

class Alerta {

    companion object {

        lateinit var dialog: Dialog

        @JvmStatic
        fun aviso(msg: String, act: Activity, onClick:() -> Unit) {

            val builder = AlertDialog.Builder(act)
            builder.setTitle(R.string.Warning)
            builder.setMessage(msg)
            builder.setPositiveButton(R.string.close_app) { dialog, which -> onClick() }
            builder.setCancelable(true)
            dialog = builder.create()
            dialog.show()

        }
    }
}