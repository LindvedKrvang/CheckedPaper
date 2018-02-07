package com.krvang.lindved.checkedpaper.bll;


import com.krvang.lindved.checkedpaper.be.Tile;

/**
 * Created by Lindved on 07-02-2018.
 */

public class TilesChecker implements ITilesChecker {

    private int mCount, mRows, mCols;
    private Tile[][] mTiles;

    public TilesChecker(int rows, int cols, Tile[][] tileMatrix){
        mRows = rows;
        mCols = cols;
        mTiles = tileMatrix;
    }

    @Override
    public int countConnectedTiles(int row, int col, String color) {
        mCount = 0;
        checkTiles(row, col, color);
        return mCount;
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
}
