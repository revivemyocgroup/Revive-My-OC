

//动画（用fxgl实现）
package com.game.revivemyoc.service;

import com.game.revivemyoc.entity.Gem;
import com.game.revivemyoc.entity.GemType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.List;
import java.util.Random;

public class   AnimationService implements IAnimationService {

    @Override
    public void animateEliminate(List<Gem> matchedGems, Runnable onFinish) {
        // 消除动画（简单实现：让宝石渐隐或消失）
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            matchedGems.forEach(gem -> {
                // 实现宝石消失的效果（渐隐或其他效果）
            });
            onFinish.run();  // 动画结束后执行回调
        }));
        timeline.play();
    }

    @Override
    public void animateFall(Gem[][] board, Runnable onFinish) {
        // 方块下落动画
        Timeline timeline = new Timeline();
        for (int col = 0; col < 9; col++) {
            for (int row = 8; row >= 0; row--) {
                if (board[row][col] != null) {
                    final int finalRow = row;
                    final Gem gem = board[row][col];
                    KeyFrame fallKeyFrame = new KeyFrame(Duration.seconds(1), event -> {
                        gem.setY(finalRow);
//                    KeyFrame fallKeyFrame = new KeyFrame(Duration.seconds(1), event -> {
//                        // 使宝石下落
//                        gem.setY(row);
                    });
                    timeline.getKeyFrames().add(fallKeyFrame);
                }
            }
        }
        timeline.setOnFinished(event -> onFinish.run());
        timeline.play();
    }

    @Override
    public void animateNewGems(Gem[][] board, Runnable onFinish) {
        // 新宝石下落动画
        Timeline timeline = new Timeline();
        Random rand = new Random();
        for (int col = 0; col < 9; col++) {
            for (int row = 8; row >= 0; row--) {
                if (board[row][col] == null) {
                    final int finalRow = row;   // 复制 row
                    final int finalCol = col;   // 复制 col
                    GemType randomType = GemType.values()[rand.nextInt(GemType.values().length)];
                    Gem gem = new Gem(randomType, finalCol, -1);  // 初始 x=col, y=-1
                    KeyFrame fallKeyFrame = new KeyFrame(Duration.seconds(1), event -> {
                        gem.setY(finalRow);
                        board[finalRow][finalCol] = gem;
//                    GemType randomType = GemType.values()[rand.nextInt(GemType.values().length)];
//                    Gem gem = new Gem(randomType, col,-1);  // 初始位置在上方
//                    KeyFrame fallKeyFrame = new KeyFrame(Duration.seconds(1), event -> {
//                        gem.setY(row);
//                        board[row][col] = gem;
                    });
                    timeline.getKeyFrames().add(fallKeyFrame);
                }
            }
        }
        timeline.setOnFinished(event -> onFinish.run());
        timeline.play();
    }
}


