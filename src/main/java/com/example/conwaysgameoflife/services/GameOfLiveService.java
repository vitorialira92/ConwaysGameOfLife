package com.example.conwaysgameoflife.services;

import com.example.conwaysgameoflife.configuration.Configurations;

public class GameOfLiveService {

    public static int[][] Play(int[][] previousState){
        int rows = previousState.length;
        int columns = previousState[0].length;

        int[][] newState = new int[rows][columns];

        for (int i = 0; i < rows; i ++){
            for(int j = 0; j < columns; j++){
                int liveNeighbours = CountLiveNeighbours(previousState, i, j);
                if(previousState[i][j] == 1){
                    if(liveNeighbours < 2 || liveNeighbours > 3) {
                        newState[i][j] = 0;
                    }
                    else
                        newState[i][j] = previousState[i][j];

                }else if(liveNeighbours == 3){
                    newState[i][j] = 1;
                }
                else
                    newState[i][j] = previousState[i][j];
            }
        }

        return newState;
    }

    private static int CountLiveNeighbours(int[][] states, int row, int column) {
        int count = 0;

        int rows = states.length;
        int columns = states[0].length;

        for(int i = row - 1; i < row + 2; i ++){
            if(i < rows && i >= 0){
                for(int j = column - 1; j < column + 2; j ++){
                    if(j < columns && j >= 0 && !(i == row && j == column)){
                        count += states[i][j];
                    }
                }
            }
        }

        return (count - states[row][column]);
    }

}
