package com.example.myapplication.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiaryViewModel(application: Application): AndroidViewModel(application) {
    val readAllData : LiveData<List<Diary>>
    private val repository: DiaryRepository

    init{
        val dao=DiaryDatabase.getDatabase(application).getDiaryDao()
        repository= DiaryRepository(dao)
        readAllData = repository.getAlldata
    }
    fun deleteDiary(diary: Diary)=viewModelScope.launch(Dispatchers.IO){
        repository.delete(diary)
    }
    fun updateDiary(diary: Diary)=viewModelScope.launch(Dispatchers.IO){
        repository.update(diary)
    }
    fun addDiary(diary:Diary)=viewModelScope.launch(Dispatchers.IO){
        repository.addDiary(diary)
    }
}