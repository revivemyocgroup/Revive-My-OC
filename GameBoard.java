package com.game.revivemyoc.entity;

import java.util.Random;

public class GameBoard {

    private Gem[][] board;  // 9x9的方块棋盘

    // 方块类型（颜色）数组
    private final String[] gemTypes = {"red", "yellow", "blue", "white", "black"};

    public GameBoard() {
        // 初始化 9x9 的棋盘
        this.board = new Gem[9][9];
    }

    // 随机生成并填充棋盘
    public void generateBoard() {
        Random rand = new Random();

        // 遍历每个位置，随机选择一个方块类型
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                String randomType = gemTypes[rand.nextInt(gemTypes.length)];
                // 根据随机类型为方块设置属性（类型和图像路径）
                board[x][y] = new Gem(randomType, x, y);
            }
        }
    }

    // 获取棋盘上的方块
    public Gem getGemAt(int x, int y) {
        return board[x][y];
    }

    // 设置棋盘上指定位置的方块
    public void setGemAt(int x, int y, Gem gem) {
        board[x][y] = gem;
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
