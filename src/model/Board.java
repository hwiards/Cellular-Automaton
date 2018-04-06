package model;

import rules.Rule;

public class Board {

    private static final double PROBABILTY = 0.1;
    private Cell[][] board;
    private int x_dim;
    private int y_dim;
    private Rule rule;

    public Board(int x, int y, Rule rule){
        this.board = new Cell[x][y];
        this.x_dim = x;
        this.y_dim = y;
        this.rule = rule;

        initBoard();
    }

    public Board(Cell[][] board, Rule rule){
        this.board = board;
        x_dim = board.length;
        y_dim = board[0].length;
        this.rule = rule;
    }


    public Board tick(){
        Cell[][] newBoard = new Cell[x_dim][y_dim];
        for(int x = 0; x < x_dim; x++){
            for(int y = 0; y < y_dim; y++){
                CellState north =       (y-1 >= 0) ? board[x][y-1].getState() : null;
                CellState northeast =   (y-1 >= 0 && x+1 < board.length)? board[x+1][y-1].getState() : null;
                CellState east =        (x+1 < board.length)? board[x+1][y].getState() : null;
                CellState southeast =   (x+1 < board.length && y+1 < board[x].length)? board[x+1][y+1].getState() : null;
                CellState south =       (y+1 < board[x].length)? board[x][y+1].getState() : null;
                CellState southwest =   (x-1 >= 0 && y+1 < board[x].length)? board[x-1][y+1].getState() : null;
                CellState west =        (x-1 >= 0)? board[x-1][y].getState() : null;
                CellState northwest =   (x-1 >= 0 && y-1 >= 0)? board[x-1][y-1].getState() : null;
                CellState actual = board[x][y].getState();

                newBoard[x][y] = new Cell(rule.next(north, northeast, east, southeast, south, southwest, west, northwest, actual));
            }
        }
        return new Board(newBoard, rule);
    }

    private void initBoard(){
        for(int x = 0; x < this.board.length; x++){
            for(int y = 0; y < this.board[x].length; y++){
                this.board[x][y] = new Cell(PROBABILTY);
            }
        }
    }

    public CellState getState(int x, int y){
        return board[x][y].getState();
    }

    public int getX_dim() {
        return x_dim;
    }

    public int getY_dim() {
        return y_dim;
    }
}
