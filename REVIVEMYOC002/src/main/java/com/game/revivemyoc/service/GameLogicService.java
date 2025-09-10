
    package com.game.revivemyoc.service;

import com.game.revivemyoc.entity.Gem;
import com.game.revivemyoc.entity.GemType;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

    public abstract class GameLogicService implements IGameLogicService {

        private final IAnimationService animationService;//动画

        public GameLogicService(IAnimationService animationService) {
            this.animationService = animationService;
        }//动画

        //这里放是否匹配（水平垂直）的判断，返回matchedGems

        @Override
        public void detectAndEliminateMatches(Gem[][] board) {
            // 步骤 1：查找匹配的宝石
            List<Gem> matchedGems = findMatches(board);
            if (matchedGems.isEmpty()) return;

            // 步骤 2：执行消除动画，等待动画完成后继续
            animationService.animateEliminate(matchedGems, () -> {
                // 消除匹配的宝石
                eliminateMatches(matchedGems, board);

                // 步骤 3：下落并填补空位
                refreshBoard(board);
            });
        }

        private void eliminateMatches(List<Gem> matchedGems, Gem[][] board) {
            // 消除匹配的宝石
            for (Gem gem : matchedGems) {
                board[gem.getX()][gem.getY()] = null;
            }
        }//宝石消除

        @Override
        public void refreshBoard(Gem[][] board) {
            // 执行方块下落动画
            animationService.animateFall(board, () -> {
                // 填补空位并生成新宝石
                fillEmptySpacesWithNewGems(board);

                // 执行新宝石下落动画
                animationService.animateNewGems(board, () -> {
                    // 新宝石下落完成后可以进行其他操作
                });
            });
        }//原先上方的宝石下落

        private void fillEmptySpacesWithNewGems(Gem[][] board) {
            // 随机生成新的宝石并填补空位
            for (int col = 0; col < 9; col++) {
                for (int row = 8; row >= 0; row--) {
                    if (board[row][col] == null) {
                        Random rand = new Random();
                        GemType randomType = GemType.values()[rand.nextInt(GemType.values().length)];
                        board[row][col] = new Gem(randomType, col, row);
                    }
                }
            }
        }//随机在上方生成宝石并下落填补空位
    }


