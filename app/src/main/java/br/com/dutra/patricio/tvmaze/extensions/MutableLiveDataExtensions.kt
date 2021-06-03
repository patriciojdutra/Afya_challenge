package br.com.dutra.patricio.tvmaze.extensions

import androidx.lifecycle.MutableLiveData

fun<T> MutableLiveData<ArrayList<T>>.addAllValue(newlist: List<T>){
    val list = this.value?: arrayListOf()
    list.addAll(newlist)
    this.value = list
}