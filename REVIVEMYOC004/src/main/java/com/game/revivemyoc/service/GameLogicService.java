package com.game.revivemyoc.service;

import com.game.revivemyoc.entity.Gem;
import com.game.revivemyoc.entity.GemType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogicService implements IGameLogicService {

    private final IAnimationService animationService;

    public GameLogicService(IAnimationService animationService) {
        this.animationService = animationService;
    }

    @Override
    public void detectAndEliminateMatches(Gem[][] board) {
        List<Gem> matchedGems = findMatches(board);
        if (matchedGems.isEmpty()) return;

        // 消除宝石
        animationService.animateEliminate(matchedGems, () -> {
            for (Gem gem : matchedGems) {
                board[gem.getY()][gem.getX()] = null;
            }

            // 下落填补
            refreshBoard(board);

            // 连锁消除
            detectAndEliminateMatches(board);
        });
    }

    @Override
    public boolean hasPossibleMoves(Gem[][] board) {
        return !findMatches(board).isEmpty();
    }

    @Override
    public boolean swapGems(Gem gem1, Gem gem2, Gem[][] board) {
        int x1 = gem1.getX(), y1 = gem1.getY();
        int x2 = gem2.getX(), y2 = gem2.getY();

        // 交换
        gem1.setX(x2); gem1.setY(y2);
        gem2.setX(x1); gem2.setY(y1);
        board[y1][x1] = gem2;
        board[y2][x2] = gem1;

        // 检测匹配
        List<Gem> matches = findMatches(board);
        if (matches.isEmpty()) {
            // 无效交换，换回来
            gem1.setX(x1); gem1.setY(y1);
            gem2.setX(x2); gem2.setY(y2);
            board[y1][x1] = gem1;
            board[y2][x2] = gem2;
            return false;
        } else {
            detectAndEliminateMatches(board);
            return true;
        }
    }

    @Override
    public void refreshBoard(Gem[][] board) {
        animationService.animateFall(board, () -> fillEmptySpacesWithNewGems(board));
    }

    @Override
    public List<Gem> findMatches(Gem[][] board) {
        int[][] intBoard = new int[9][9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                intBoard[r][c] = board[r][c] != null ? board[r][c].getType().ordinal() + 1 : 0;
            }
        }

        List<int[]> matchPos = Match3Game.findMatches(intBoard);
        List<Gem> matchedGems = new ArrayList<>();
        for (int[] pos : matchPos) {
            matchedGems.add(board[pos[0]][pos[1]]);
        }
        return matchedGems;
    }

    private void fillEmptySpacesWithNewGems(Gem[][] board) {
        Random rand = new Random();
        for (int c = 0; c < 9; c++) {
            for (int r = 8; r >= 0; r--) {
                if (board[r][c] == null) {
                    GemType type = GemType.values()[rand.nextInt(GemType.values().length)];
                    board[r][c] = new Gem(type, c, r);
                }
            }
        }
    }
}



