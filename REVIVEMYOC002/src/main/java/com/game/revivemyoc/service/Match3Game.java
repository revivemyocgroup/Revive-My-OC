package com.game.revivemyoc.service;
import java.util.*;

public class Match3Game {
    static final int ROWS = 9;
    static final int COLS = 9;
    static final int TYPES = 5; // 方块属性种类

    static int[][] board = new int[ROWS][COLS];
    static Random rand = new Random();

     public static void main(String[] args) {
        initBoard();
        printBoard();

        while (true) {
            if (!checkAndRemoveMatches()) {
                break; // 没有更多消除
            }
            dropBlocks();
            refillBoard();
            System.out.println("\n刷新后：");
            printBoard();
        }
    }

    // 初始化棋盘
    static void initBoard() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                board[r][c] = rand.nextInt(TYPES) + 1;
            }
        }
    }

    // 打印棋盘
    static void printBoard() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }

    // 检查并消除（返回是否有消除）
    static boolean checkAndRemoveMatches() {
        boolean[][] remove = new boolean[ROWS][COLS];
        boolean found = false;

        // 横向检测
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS - 2; c++) {
                int val = board[r][c];
                if (val != 0 && val == board[r][c + 1] && val == board[r][c + 2]) {
                    remove[r][c] = remove[r][c + 1] = remove[r][c + 2] = true;
                    found = true;
                }
            }
        }

        // 纵向检测
        for (int c = 0; c < COLS; c++) {
            for (int r = 0; r < ROWS - 2; r++) {
                int val = board[r][c];
                if (val != 0 && val == board[r + 1][c] && val == board[r + 2][c]) {
                    remove[r][c] = remove[r + 1][c] = remove[r + 2][c] = true;
                    found = true;
                }
            }
        }
        return found;}
static void dropBlocks() {for (int c = 0; c < COLS; c++) {
    int writeRow = ROWS - 1;
    for (int r = ROWS - 1; r >= 0; r--) {
        if (board[r][c] != 0) {
            board[writeRow][c] = board[r][c];
            if (writeRow != r) {
                board[r][c] = 0;
            }
            writeRow--;
        }
    }
}}
static void refillBoard() {for (int r = 0; r < ROWS; r++) {
    for (int c = 0; c < COLS; c++) {
        if (board[r][c] == 0) {
            board[r][c] = rand.nextInt(TYPES) + 1;
        }
    }
}}
}
