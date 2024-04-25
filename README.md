# Conway's Game Of Life


![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

NOTE: This project is part of a programming challenge set by the company where I am currently interning. The task is to develop Conway’s Game of Life using Java.

## Table of Contents

- [About The Game](#about-the-game)
- [How This Game Works](#how-this-game-works)
- [Game Code Logic](#game-code-logic)


## About The Game

The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970. It is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves. It is Turing complete and can simulate a universal constructor or any other Turing machine. [Wikipedia, read more [here](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#Rules)]

In the game, there are 4 rules:
1. Any live cell with fewer than two live neighbors dies, as if by underpopulation
2. Any live cell with two or three live neighbors lives on to the next generation
3. Any live cell with more than three live neighbors dies, as if by overpopulation
4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction

## How This Game Works

This game was developed using JavaFX, which provides an interactive user interface. Following the logo page, the first page is the configuration page, where the user can choose the number of columns, the number of rows, and the number of initial live cells.

The numbers the user chooses must adhere to predefined MIN and MAX limits.

For the initial live cells, if the user does not enter a number, the application will randomly select a number between the MIN and ROWS*COLUMNS.

![image](https://github.com/vitorialira92/ConwaysGameOfLife/assets/48605624/c51a7be2-fe78-4d3c-b2f1-aaed6423c748)

Once the user presses START, the initial live cells will be placed randomly on the grid. Initially, every living cell will be green, but as new generations are generated, living cells may also be represented in yellow. If a cell was alive in the previous state, its color will be yellow; if not, it will be green.

Users can also choose how many generations the game should produce per minute.

![image](https://github.com/vitorialira92/ConwaysGameOfLife/assets/48605624/e0d997e2-0bb7-4078-b284-50174f547d5b)

When all cells die, a popup shows up asking the user if they wish to start over or close the application.

![image](https://github.com/vitorialira92/ConwaysGameOfLife/assets/48605624/ce0eeedf-aae9-43b2-a8f1-112340ce853b)


## Game Code Logic

The game logic is encapsulated within a service class called GameOfLifeService.

The grid is represented by a two-dimensional integer array, where 0 indicates a dead cell and 1 indicates a live cell. To generate a new generation, each cell in the current grid is analyzed according to specific rules:

![image](https://github.com/vitorialira92/ConwaysGameOfLife/assets/48605624/fec3b285-dd3b-49a2-9fb8-cb1c2a29d3df)

To count how many alive neighbours a certain cell has, there is a method called CountLiveNeighbours:

![image](https://github.com/vitorialira92/ConwaysGameOfLife/assets/48605624/9253a7ed-f564-4bf7-ad70-79dff09898b1)


