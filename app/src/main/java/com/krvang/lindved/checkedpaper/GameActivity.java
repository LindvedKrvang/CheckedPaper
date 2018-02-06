package com.krvang.lindved.checkedpaper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private int mRows, mCols;
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
    }

    private void createBoard(){
        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tile tile = (Tile)v;
                tile.switchImage();
                checkTiles(tile.getRow(), tile.getCol(), tile.getColor());
//                Toast.makeText(getBaseContext(),
//                        "(" + tile.getRow() + ":" + tile.getCol() + ")",
//                        Toast.LENGTH_SHORT).show();
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

    private void checkTiles(int row, int col, String color){
        List<Tile> tiles = new ArrayList();
        tiles.add(mTiles[row - 1][col]);
        tiles.add(mTiles[row + 1][col]);
        tiles.add(mTiles[row][col - 1]);
        tiles.add(mTiles[row][col + 1]);

        int count = 0;

        for (Tile tile: tiles) {
            if(tile.getColor().equals(color))
                count++;
        }

        Toast.makeText(getBaseContext(), "There are: " + count + " connected", Toast.LENGTH_LONG).show();
    }

    class Tile extends AppCompatImageView {

        private int mRow, mCol, mImage;

        public Tile(Context context, int row, int col) {
            super(context);
            mRow = row;
            mCol = col;
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
            return (mImage == R.drawable.circlered) ? "White" : "Red";
        }
    }
}
