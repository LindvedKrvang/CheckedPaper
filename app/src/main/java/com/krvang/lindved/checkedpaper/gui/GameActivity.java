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
        View.OnClickListener ocl = createTileOnClickListener();

        LinearLayout boardView = findViewById(R.id.llBoard);
        boardView.removeAllViews();

        createTilesOnBoard(boardView, ocl);
    }

    private void createTilesOnBoard(LinearLayout boardView, View.OnClickListener ocl){
        for(int i = 0; i < mRows; i++){
            LinearLayout currentLL = new LinearLayout(this);
            boardView.addView(currentLL);
            currentLL.setOrientation(LinearLayout.HORIZONTAL);
            for(int j = 0; j < mCols; j++){
                Tile tile = new Tile(this, i, j);
                currentLL.addView(tile);
                tile.setOnClickListener(ocl);
                mTiles[i][j] = tile;
            }
        }
    }

    private View.OnClickListener createTileOnClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tile tile = (Tile)v;
                tile.switchImage();
                int connectedTiles = mTilesChecker.countConnectedTiles(tile.getRow(), tile.getCol(), tile.getColor());
                Toast.makeText(getBaseContext(), "There are: " + connectedTiles + " connected", Toast.LENGTH_LONG).show();
                clearForNextClick();
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
