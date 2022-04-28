package com.example.uts_tam.reminder;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.uts_tam.PlannrsRoomDatabase;

import java.util.List;

public class ReminderRepository {

    private ReminderDAO daoReminder;
    private LiveData<List<ReminderDB>> daftarReminder;

    ReminderRepository(Application app){
        PlannrsRoomDatabase db = PlannrsRoomDatabase.getDatabase(app);
        daoReminder = db.daoReminder();
        daftarReminder = daoReminder.getAllReminder();
    }

    LiveData<List<ReminderDB>> getDaftarRem(){
        return this.daftarReminder;
    }

    public void insert(ReminderDB fa){
        new insertAsyncTask(daoReminder).execute(fa);
    }

    public void deleteAlarm(){
        new deleteAlarmAsyncTask(daoReminder).execute();
    }

    public void delete(ReminderDB fa) {
        new deleteAsyncTask(daoReminder).execute(fa);
    }

    public void update(ReminderDB fa) { new updateAsyncTask(daoReminder).execute(fa); }

    private static class insertAsyncTask extends AsyncTask<ReminderDB, Void, Void> {
        private ReminderDAO asyncDaoReminder;
        insertAsyncTask(ReminderDAO dao){
            this.asyncDaoReminder = dao;
        }
        @Override
        protected Void doInBackground(final ReminderDB... ReminderDB){
            asyncDaoReminder.insert(ReminderDB[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<ReminderDB, Void, Void> {
        private ReminderDAO asyncDaoReminder;

        public deleteAsyncTask(ReminderDAO dao){
            this.asyncDaoReminder = dao;
        }

        @Override
        protected Void doInBackground(final ReminderDB... ReminderDB){
            asyncDaoReminder.delete(ReminderDB[0]);
            return null;
        }
    }

    private static class deleteAlarmAsyncTask extends AsyncTask<Void, Void, Void> {
        private ReminderDAO asyncDaoReminder;

        public deleteAlarmAsyncTask(ReminderDAO dao){
            this.asyncDaoReminder = dao;
        }

        @Override
        protected Void doInBackground(final Void... voids){
            asyncDaoReminder.deleteAlarm();
            return null;
        }
    }



    private static class updateAsyncTask extends AsyncTask<ReminderDB, Void, Void>{
        private ReminderDAO asyncDaoReminder;
        updateAsyncTask(ReminderDAO dao){
            this.asyncDaoReminder = dao;
        }
        @Override
        protected Void doInBackground(final ReminderDB... ReminderDB){
            asyncDaoReminder.update(ReminderDB[0]);
            return null;
        }
    }
}
