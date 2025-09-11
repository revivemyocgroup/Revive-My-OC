package com.game.revivemyoc.entity;

import java.util.Random;

public class GameBoard {

    private Gem[][] board;  // 9x9的方块棋盘


    public GameBoard() {
        // 初始化 9x9 的棋盘
        this.board = new Gem[9][9];
    }

    // 随机生成并填充棋盘
    public void generateBoard() {
        board = new Gem[9][9];
        Random random = new Random();

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Gem gem;
                do {
                    int type = random.nextInt(GemType.values().length);
                    gem = new Gem(GemType.values()[type], x, y);
                } while (
                        (y >= 2 && board[y-1][x].getType() == gem.getType() && board[y-2][x].getType() == gem.getType()) || // 纵向
                                (x >= 2 && board[y][x-1].getType() == gem.getType() && board[y][x-2].getType() == gem.getType())    // 横向
                );
                board[y][x] = gem;
            }
        }
    }
    public Gem getGemAt(int x, int y) {
        return board[y][x];
    }

    public void setGemAt(int x, int y, Gem gem) {
        board[y][x] = gem;
    }


    // 打印棋盘（仅用于调试）
    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j].getType() + "\t");
            }
            System.out.println();
        }
    }

    // 获取整个棋盘
    public Gem[][] getBoard() {
        return board;
    }

}


