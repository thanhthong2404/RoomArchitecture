package com.lmtn.roomarchitecture.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lmtn.roomarchitecture.model.Word;
import com.lmtn.roomarchitecture.room.WordDAO;
import com.lmtn.roomarchitecture.room.WordRoomDatabase;

import java.util.List;

public class WordRepository {
    private WordDAO mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDAO();
        mAllWords = mWordDao.getAllWord();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.insert(word);
        });
    }
}
