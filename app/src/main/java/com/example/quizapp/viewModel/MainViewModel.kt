package com.example.quizapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizapp.model.ListOfQuizModel

class MainViewModel(quizOfList:List<ListOfQuizModel>):ViewModel() {
    val quizList:List<ListOfQuizModel> = quizOfList
    var score:Int=0
    val questionCount:Int=10
    var counter:Int=0
}