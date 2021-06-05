package br.com.dutra.patricio.tvmaze

import br.com.dutra.patricio.tvmaze.util.Constants
import org.junit.Test

import org.junit.Assert.*

class MovieListActivityTest {

    @Test
    fun initialValueOfThePage(){
        val value = Constants.minimumNumberOfPagesToUpdateList
        assertEquals(1, value)
    }

}