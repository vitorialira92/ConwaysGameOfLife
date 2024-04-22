# Conway's Game Of Life


![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

NOTE: This project is a programming challenge given by the company where I am doing an internship. It consists in building the Conway'a Game of Life in Java. 


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

This game was developed with JavaFX, so there's an interface the user can interact with. The first page, after the logo page, is the configuration one, where the cuser can choose: how many columns, how many rows and how many initial live cells there will be.

The numbers the user choose must obey the MIN and MAX pre-defined. 

----> For the initial live cells, if the user don't type any number, the application will get a random number from MIN to ROWS * COLUMNS.

![image](https://github.com/vitorialira92/ConwaysGameOfLife/assets/48605624/1a75b6ab-a733-4ae3-9e49-b122c363a9bc)

Once the user press START, the initial live cells will be placed in random positions in the grid.
Initially, every living cell will be green but once new generations are generated, living cells might be represented as yellow too.
When in the previous state a certain cell was already alive, its color will be yellow, if not, its color will be green.

-> Users can also choose how many generations the game should have per minute.

![image](https://github.com/vitorialira92/ConwaysGameOfLife/assets/48605624/20b39d2b-3172-4699-81b9-0a27c6fefd44)


## Game Code Logic

The game logic is entirely inside a service class called GameOfLifeService.

The grid is a integer two-dimensional array that can be 0 (dead) or 1 (alive). To generate a new generation, each cells in the current grid is analised to each rule:

![image](https://github.com/vitorialira92/ConwaysGameOfLife/assets/48605624/fec3b285-dd3b-49a2-9fb8-cb1c2a29d3df)

And to count how many alive neighbours a certain cell has, there is a method called CountLiveNeighbours:

![image](https://github.com/vitorialira92/ConwaysGameOfLife/assets/48605624/9253a7ed-f564-4bf7-ad70-79dff09898b1)


