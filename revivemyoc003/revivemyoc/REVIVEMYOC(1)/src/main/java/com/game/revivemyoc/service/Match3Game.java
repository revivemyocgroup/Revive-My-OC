package com.game.revivemyoc.service;

import java.util.ArrayList;
import java.util.List;

public class Match3Game {

    /**
     * 在棋盘上查找所有匹配（横向/纵向 >=3 连消）
     * @param board 9x9 棋盘，0 表示空，非 0 表示宝石类型
     * @return 所有匹配位置的列表（用 int[2] 表示 {row, col}）
     */
    public static List<int[]> findMatches(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] remove = new boolean[rows][cols];
        List<int[]> matches = new ArrayList<>();

        // 横向检测
        for (int r = 0; r < rows; r++) {
            int count = 1;
            for (int c = 1; c <= cols; c++) { // 注意 <= cols 处理最后一列
                if (c < cols && board[r][c] != 0 && board[r][c] == board[r][c - 1]) {
                    count++;
                } else {
                    if (count >= 3) {
                        for (int k = 0; k < count; k++) {
                            remove[r][c - 1 - k] = true;
                        }
                    }
                    count = 1;
                }
            }
        }

        // 纵向检测
        for (int c = 0; c < cols; c++) {
            int count = 1;
            for (int r = 1; r <= rows; r++) { // 注意 <= rows 处理最后一行
                if (r < rows && board[r][c] != 0 && board[r][c] == board[r - 1][c]) {
                    count++;
                } else {
                    if (count >= 3) {
                        for (int k = 0; k < count; k++) {
                            remove[r - 1 - k][c] = true;
                        }
                    }
                    count = 1;
                }
            }
        }

        // 收集匹配的格子
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (remove[r][c]) {
                    matches.add(new int[]{r, c});
                }
            }
        }
        return matches;
    }
}
