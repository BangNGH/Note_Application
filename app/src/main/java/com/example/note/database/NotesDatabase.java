package com.example.note.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.note.dao.NoteDao;
import com.example.note.entities.Note;
@Database(entities = Note.class, version = 3, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {
    private static NotesDatabase notesDatabase;
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Add the new column
            database.execSQL("ALTER TABLE tbl_notes ADD COLUMN image_path TEXT");
        }
    };
    public static  synchronized  NotesDatabase getDatabase(Context context){
        if(notesDatabase == null){
            notesDatabase = Room.databaseBuilder(context, NotesDatabase.class, "notes_db")
                    .fallbackToDestructiveMigration()
                    //.addMigrations(MIGRATION_1_2)
                    .build();
        }

        return notesDatabase;
    }
    public abstract NoteDao noteDao();
}
