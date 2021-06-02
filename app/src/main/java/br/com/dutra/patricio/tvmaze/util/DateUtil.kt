package br.com.dutra.patricio.tvmaze.util

import java.lang.Exception
import java.text.NumberFormat

class DateUtil {

    companion object {

        @JvmStatic
        fun dateFormat(value:String): String{
            try {
                val arrayDate = value.split("-")
                return "${arrayDate[2].subSequence(0, 2)}/${arrayDate[1]}"
            }catch (e: Exception){
                return value
            }
        }

        @JvmStatic
        fun dateAndTimeFormat(value:String): String{
            try {
                val sDate = value.replace("-","/")
                        .replace("T"," - ")
                        .replace("Z","")
                return sDate
            }catch (e: Exception){
                return value
            }
        }
    }
}