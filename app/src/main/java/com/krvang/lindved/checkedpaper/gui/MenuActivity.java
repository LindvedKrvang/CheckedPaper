package com.krvang.lindved.checkedpaper.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.krvang.lindved.checkedpaper.R;
import com.krvang.lindved.checkedpaper.gui.GameActivity;

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
        intent.putExtra(getString(R.string.rows), Integer.parseInt(mRows.getText().toString()));
        intent.putExtra(getString(R.string.columns), Integer.parseInt(mColumns.getText().toString()));
        startActivity(intent);
    }
}
