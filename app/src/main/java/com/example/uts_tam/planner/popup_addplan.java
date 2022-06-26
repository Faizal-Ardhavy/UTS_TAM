package com.example.uts_tam.planner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uts_tam.R;

import com.example.uts_tam.R;

public class popup_addplan extends AppCompatActivity {
    private Button btnCancel, btnAddPlan;
    private EditText addTodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_addplan);
        btnCancel = findViewById(R.id.btnCancel);
        addTodo = (EditText) findViewById(R.id.addTodo);
        btnAddPlan = findViewById(R.id.btnAddPlan);
    }
    public void simpanTask(View view){
        String todo = addTodo.getText().toString();
        String status = "Todo";
        if(todo.length() <=0){
            Toast.makeText(this, "What to do?",Toast.LENGTH_SHORT).show();
        }else{
            Intent intentJawab = new Intent();
            ListPlan plan = new ListPlan(todo, status);
            intentJawab.putExtra("Todo",plan);
            setResult(RESULT_OK, intentJawab);
            finish();
        }
    }
    public void batal(View view){
        Intent intentJawab = new Intent();
        setResult(RESULT_CANCELED,intentJawab);
        finish();
    }
}