package com.krvang.lindved.checkedpaper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MenuActivity extends AppCompatActivity {

    private EditText mRows, mColumns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mRows = findViewById(R.id.etxtRows);
        mColumns = findViewById(R.id.etxtColumns);
    }

    public void goToGameActivity(View view){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("Rows", Integer.parseInt(mRows.getText().toString()));
        intent.putExtra("Columns", Integer.parseInt(mColumns.getText().toString()));
        startActivity(intent);
    }
}
