package com.krvang.lindved.checkedpaper.be;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;

import com.krvang.lindved.checkedpaper.R;

/**
 * Created by Lindved on 07-02-2018.
 */

public class Tile extends AppCompatImageView {

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

    public int getRow() {
        return mRow;
    }

    public int getCol(){
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
