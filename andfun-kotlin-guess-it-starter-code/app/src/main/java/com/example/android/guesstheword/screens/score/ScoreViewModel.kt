package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {

    private val _finalScore = MutableLiveData<Int>()
    val finalScore: LiveData<Int>
        get() = _finalScore
    private val _onPlayAgainEvent = MutableLiveData<Boolean>()
    val onPlayAgainEvent: LiveData<Boolean>
        get() = _onPlayAgainEvent

    init {
        _finalScore.value = finalScore
        Log.i("ScoreViewModel", "Final score is $finalScore")
    }

    fun onPlayAgain() {
        _onPlayAgainEvent.value = true
    }

    fun onPlayAgainFinish() {
        _onPlayAgainEvent.value = false
    }

}