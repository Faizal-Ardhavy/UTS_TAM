package com.example.uts_tam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import com.example.uts_tam.fastaction.DetilActivity;
import com.example.uts_tam.fastaction.FastAction;
import com.example.uts_tam.fastaction.FastActionListAdapter;
import com.example.uts_tam.fastaction.FastActionViewModel;

public class FastActionMain extends AppCompatActivity {
    private RecyclerView rv;
    private FastActionViewModel faVM;
    private static final int REQUEST_TAMBAH = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_action_main);


        FloatingActionButton fab = findViewById(R.id.fast_action_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(FastActionMain.this, DetilActivity.class);
                startActivityForResult(addIntent, REQUEST_TAMBAH);
            }
        });

        rv = (RecyclerView) findViewById(R.id.rv_fast_action);
        faVM = ViewModelProviders.of(this).get(FastActionViewModel.class);
        final FastActionListAdapter adapter = new FastActionListAdapter(this, faVM);
        rv.setAdapter(adapter);

        rv.setLayoutManager(new LinearLayoutManager(this));
        faVM.getDaftarFA().observe(this, new Observer<List<FastAction>>() {
            @Override
            public void onChanged(List<FastAction> fas) {
                adapter.setDaftarFA(fas);
            }
        });
    }
    @Override
    public void onActivityResult ( int reqCode, int resultCode, Intent data){
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            FastAction fa = (FastAction) data.getSerializableExtra("FAST ACTION");
            if (reqCode == REQUEST_TAMBAH) {
                faVM.insert(fa);
            }
        }
        rv.getAdapter().notifyDataSetChanged();
    }
}