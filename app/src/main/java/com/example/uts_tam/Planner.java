package com.example.uts_tam;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.uts_tam.planner.CompletedAdapter;
import com.example.uts_tam.planner.InProgressAdapter;
import com.example.uts_tam.planner.ListPlan;
import com.example.uts_tam.planner.ListPlanViewModel;
import com.example.uts_tam.planner.TodoAdapter;
import com.example.uts_tam.planner.popup_addplan;

public class Planner extends AppCompatActivity {
    private RecyclerView rvTodo, rvInProgress, rvCompleted;
    private Button addPlan, goReminder;
    private ListPlanViewModel listPlanViewModelVM;
    private static final int REQUEST_TAMBAH = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);

        addPlan = findViewById(R.id.addPlan);
        goReminder = findViewById(R.id.goReminder);
        goReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reminder = new Intent(Planner.this, Reminder.class);
                startActivity(reminder);
            }
        });
        addPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addPlanIntent = new Intent(Planner.this, popup_addplan.class);
                startActivityForResult(addPlanIntent, REQUEST_TAMBAH);
            }
        });
        listPlanViewModelVM = ViewModelProviders.of(this).get(ListPlanViewModel.class);
//      Bagian To Do
        rvTodo = (RecyclerView) findViewById(R.id.rvTodo);
        final TodoAdapter todoAdapter = new TodoAdapter(this, listPlanViewModelVM);
        rvTodo.setAdapter(todoAdapter);
        rvTodo.setLayoutManager(new LinearLayoutManager(this));
        listPlanViewModelVM.getTodoTask().observe(this, new Observer<List<ListPlan>>() {
            @Override
            public void onChanged(List<ListPlan> tasks) {
                todoAdapter.setTodoTask(tasks);
            }
        });

//      Bagian InProgress
        rvInProgress = (RecyclerView) findViewById(R.id.rvInProgress);
        final InProgressAdapter inProgressAdapter = new InProgressAdapter(this,listPlanViewModelVM);
        rvInProgress.setAdapter(inProgressAdapter);
        rvInProgress.setLayoutManager(new LinearLayoutManager(this));
        listPlanViewModelVM.getInProgressTask().observe(this, new Observer<List<ListPlan>>() {
            @Override
            public void onChanged(List<ListPlan> tasks) {
                inProgressAdapter.setInProgressTask(tasks);
            }
        });
//      Bagian Completed
        rvCompleted = (RecyclerView) findViewById(R.id.rvCompleted);
        final CompletedAdapter completedAdapter = new CompletedAdapter(this, listPlanViewModelVM);
        rvCompleted.setAdapter(completedAdapter);
        rvCompleted.setLayoutManager(new LinearLayoutManager(this));
        listPlanViewModelVM.getCompletedTask().observe(this, new Observer<List<ListPlan>>() {
            @Override
            public void onChanged(List<ListPlan> tasks) {
                completedAdapter.setCompletedTask(tasks);
            }
        });

//      Smooth Scrolling
        rvTodo.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
        rvInProgress.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
        rvCompleted.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });

    }
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data){
        super.onActivityResult(reqCode, resultCode, data);
        if(resultCode == RESULT_OK){
            ListPlan todo = (ListPlan) data.getSerializableExtra("Todo");
            if(reqCode == REQUEST_TAMBAH ) {
                listPlanViewModelVM.insert(todo);
            }
        }
        rvTodo.getAdapter().notifyDataSetChanged();
    }


}