package com.game.revivemyoc.service;

import com.game.revivemyoc.entity.Gem;
import java.util.List;

public class AnimationService implements IAnimationService {

    @Override
    public void animateEliminate(List<Gem> matchedGems, Runnable callback) {
        // 简单：直接消除
        callback.run();
    }

    @Override
    public void animateFall(Gem[][] board, Runnable callback) {
        for (int c = 0; c < 9; c++) {
            int writeRow = 8;
            for (int r = 8; r >= 0; r--) {
                if (board[r][c] != null) {
                    if (r != writeRow) {
                        board[writeRow][c] = board[r][c];
                        board[writeRow][c].setY(writeRow);
                        board[r][c] = null;
                    }
                    writeRow--;
                }
            }
        }
        callback.run();
    }

    @Override
    public void animateNewGems(Gem[][] board, Runnable callback) {
        callback.run();
    }
}
