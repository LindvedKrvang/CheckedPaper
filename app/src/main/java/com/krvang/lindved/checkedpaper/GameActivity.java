package com.krvang.lindved.checkedpaper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private int mRows, mCols, mCount;
    private Tile[][] mTiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        mRows = intent.getIntExtra("Rows", 1);
        mCols = intent.getIntExtra("Columns", 1);
        mTiles = new Tile[mRows][mCols];

        createBoard();

        mCount = 0;
    }

    private void createBoard(){
        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tile tile = (Tile)v;
                tile.switchImage();
                checkTiles(tile.getRow(), tile.getCol(), tile.getColor());
                Toast.makeText(getBaseContext(), "There are: " + mCount + " connected", Toast.LENGTH_LONG).show();
                clearForNextClick();
            }
        };

        LinearLayout boardView = findViewById(R.id.llBoard);
        boardView.removeAllViews();

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

    private void clearForNextClick(){
        mCount = 0;
        for (Tile[] tileRow: mTiles) {
            for(Tile tile: tileRow){
                tile.setChecked(false);
            }
        }
    }

    private void checkTiles(int row, int col, String color){
        mTiles[row][col].setChecked(true);

        if(row + 1 < mRows){
            Tile tile = mTiles[row + 1][col];
            if (tile.getColor().equals(color) && !tile.isChecked()){
                mCount++;
                checkTiles(row + 1, col, color);
            }
        }
        if(row - 1 >= 0){
            Tile tile = mTiles[row - 1][col];
            if(tile.getColor().equals(color) && !tile.isChecked()){
                mCount++;
                checkTiles(row - 1, col, color);
            }
        }
        if(col + 1 < mCols){
            Tile tile = mTiles[row][col + 1];
            if(tile.getColor().equals(color)  && !tile.isChecked()){
                mCount++;
                checkTiles(row, col + 1, color);
            }
        }
        if(col - 1 >= 0){
            Tile tile = mTiles[row][col - 1];
            if(tile.getColor().equals(color) && !tile.isChecked()){
                mCount++;
                checkTiles(row, col - 1, color);
            }
        }
    }

    class Tile extends AppCompatImageView {

        private int mRow, mCol, mImage;
        private boolean mIsChecked;

        public Tile(Context context, int row, int col) {
            super(context);
            mRow = row;
            mCol = col;
            mIsChecked = false;
            mImage = R.drawable.circlered;
            setImageResource(mImage);
        }

        private int getRow() {
            return mRow;
        }

        private int getCol(){
            return  mCol;
        }

        public void switchImage(){
            mImage = (mImage == R.drawable.circlered) ? R.drawable.circlewhite : R.drawable.circlered;
            setImageResource(mImage);
        }

        public String getColor(){
            return (mImage == R.drawable.circlered) ? "Red" : "White";
        }

        public boolean isChecked(){
            return mIsChecked;
        }

        public void setChecked(boolean checked){
            mIsChecked = checked;
        }
    }
}
