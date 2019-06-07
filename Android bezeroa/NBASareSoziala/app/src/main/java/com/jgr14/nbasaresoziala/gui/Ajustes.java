package com.jgr14.nbasaresoziala.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala.dataAccess.DataAccess;

public class Ajustes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        final EditText input = (EditText) findViewById(R.id.input);
        input.setText(DataAccess.URL);

        ((Button)findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DataAccess.URL = input.getText().toString();
                finish();
            }
        });
    }
}
