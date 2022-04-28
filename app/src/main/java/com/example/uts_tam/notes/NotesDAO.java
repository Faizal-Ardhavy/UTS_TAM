package com.example.uts_tam.notes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDAO {
    @Query("SELECT * FROM tblNotes")
    LiveData<List<Notes>> getAllNotes();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Notes notes);

    @Delete
    void delete(Notes notes);

    @Update
    void update(Notes notes);

    @Query("DELETE FROM tblNotes")
    void deleteAll();
}
