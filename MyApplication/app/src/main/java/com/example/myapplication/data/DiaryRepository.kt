package com.example.myapplication.data

import androidx.lifecycle.LiveData

class DiaryRepository(private val diaryDao: DiaryDao) {
    val getAlldata: LiveData<List<Diary>> = diaryDao.readalldata()

    suspend fun addDiary(diary: Diary){
        diaryDao.adddiary(diary)
    }

    suspend fun delete(diary: Diary){
        diaryDao.delete(diary)
    }

    suspend fun update(diary: Diary){
        diaryDao.update(diary)
    }

}