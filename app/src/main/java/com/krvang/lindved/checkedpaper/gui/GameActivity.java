package com.krvang.lindved.checkedpaper.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.krvang.lindved.checkedpaper.R;
import com.krvang.lindved.checkedpaper.be.Tile;
import com.krvang.lindved.checkedpaper.bll.ITilesChecker;
import com.krvang.lindved.checkedpaper.bll.TilesChecker;

public class GameActivity extends AppCompatActivity {

    private int mRows, mCols;
    private Tile[][] mTiles;
    private ITilesChecker mTilesChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        mRows = intent.getIntExtra(getString(R.string.rows), 1);
        mCols = intent.getIntExtra(getString(R.string.columns), 1);
        mTiles = new Tile[mRows][mCols];

        mTilesChecker = new TilesChecker(mRows, mCols, mTiles);
        createBoard();
    }

    private void createBoard(){
        View.OnClickListener ocl = createOnClickListener();
        View.OnLongClickListener olcl = createOnLongClickListener();

        LinearLayout boardView = findViewById(R.id.llBoard);
        boardView.removeAllViews();

        createTilesOnBoard(boardView, ocl, olcl);
    }

    private void createTilesOnBoard(LinearLayout boardView, View.OnClickListener ocl, View.OnLongClickListener olcl){
        for(int i = 0; i < mRows; i++){
            LinearLayout currentLL = new LinearLayout(this);
            boardView.addView(currentLL);
            currentLL.setOrientation(LinearLayout.HORIZONTAL);
            for(int j = 0; j < mCols; j++){
                Tile tile = new Tile(this, i, j);
                currentLL.addView(tile);
                tile.setOnClickListener(ocl);
                tile.setOnLongClickListener(olcl);
                mTiles[i][j] = tile;
            }
        }
    }

    private View.OnClickListener createOnClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tile tile = (Tile)v;
                tile.switchImage();
            }
        };
    }

    private View.OnLongClickListener createOnLongClickListener(){
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Tile tile = (Tile)v;
                int connectedTiles = mTilesChecker.countConnectedTiles(tile.getRow(), tile.getCol(), tile.getColor());
                clearForNextClick();
                Toast.makeText(getBaseContext(), "There are: " + connectedTiles + " connected", Toast.LENGTH_LONG).show();
                return true;
            }
        };
    }

    private void clearForNextClick(){
        for (Tile[] tileRow: mTiles) {
            for(Tile tile: tileRow){
                tile.setChecked(false);
            }
        }
    }
}
