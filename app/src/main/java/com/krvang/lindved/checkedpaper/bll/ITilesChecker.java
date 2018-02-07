package com.krvang.lindved.checkedpaper.bll;


import com.krvang.lindved.checkedpaper.be.Tile;

/**
 * Created by Lindved on 07-02-2018.
 */

public interface ITilesChecker {

    int countConnectedTiles(int row, int col, String color);
}
