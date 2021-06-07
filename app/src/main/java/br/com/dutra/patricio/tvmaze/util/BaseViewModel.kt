package br.com.dutra.patricio.tvmaze.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    var compositeDisposable = CompositeDisposable()
    var loading = MutableLiveData<Boolean>()
    var errorMessage = MutableLiveData<String>()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}