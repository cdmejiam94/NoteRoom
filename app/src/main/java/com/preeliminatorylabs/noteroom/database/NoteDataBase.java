package com.preeliminatorylabs.noteroom.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.preeliminatorylabs.noteroom.dao.NoteDAO;
import com.preeliminatorylabs.noteroom.entity.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDataBase extends RoomDatabase {

    private static NoteDataBase instance;

    public abstract NoteDAO noteDAO();

    public static synchronized NoteDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDataBase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase database){
            super.onCreate(database);
            new PopulateDataBaseAsyncTask(instance).execute();
        }
    };

    private static  class PopulateDataBaseAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDAO noteDao;

        private PopulateDataBaseAsyncTask(NoteDataBase db) {
            noteDao = db.noteDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Delete", "Swipe to the left or delete all on the menu", 1));
            noteDao.insert(new Note("Update", "By touching on each note", 2));
            noteDao.insert(new Note("Add", "Just touch de add icon (+)", 3));
            return null;
        }
    }
}
