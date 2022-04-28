package com.example.uts_tam;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.uts_tam.fastaction.FastAction;
import com.example.uts_tam.fastaction.FastActionDAO;
import com.example.uts_tam.notes.Notes;
import com.example.uts_tam.notes.NotesDAO;
import com.example.uts_tam.planner.ListPlan;
import com.example.uts_tam.planner.ListPlanDAO;
import com.example.uts_tam.reminder.ReminderDAO;
import com.example.uts_tam.reminder.ReminderDB;

@Database(entities = {Notes.class, ListPlan.class, FastAction.class, ReminderDB.class}, version = 1, exportSchema = false)
public abstract class PlannrsRoomDatabase extends RoomDatabase {
    public abstract ListPlanDAO daoPlanner();
    public abstract NotesDAO daoNotes();
    public abstract FastActionDAO daoFastAction();
    public abstract ReminderDAO daoReminder();
    private static PlannrsRoomDatabase INSTANCE;

    public static PlannrsRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized(PlannrsRoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder
                            (context.getApplicationContext(), PlannrsRoomDatabase.class, "dbPlannrs")
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}