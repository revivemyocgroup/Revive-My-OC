package com.game.revivemyoc.service;

import com.game.revivemyoc.entity.Gem;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.util.List;

public class AnimationService implements IAnimationService {

    private boolean isAnimating = false; // 添加全局动画状态
    private Pane animationLayer;
    @Override
    public void animateEliminate(List<Gem> matchedGems, Runnable callback) {
        isAnimating = true;

        // 在对应位置创建动画
        for (Gem gem : matchedGems) {
            createEliminationAnimationAtPosition(gem.getX(), gem.getY());
        }

        // 立即执行回调
        callback.run();

        // 0.5秒后清理
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5), event -> {
                    isAnimating = false;
                })
        );
        timeline.play();
    }

    private void createEliminationAnimationAtPosition(int x, int y) {
        try {
            Image animationImage = new Image(getClass().getResourceAsStream("/assets/textures/EliminateAnim.GIF"));
            ImageView animationView = new ImageView(animationImage);
            animationView.setFitWidth(60);
            animationView.setFitHeight(60);
            animationView.setPreserveRatio(true);
            animationView.setLayoutX(x * 60);
            animationView.setLayoutY(y * 60);

            if (animationLayer != null) {
                animationLayer.getChildren().add(animationView);
            }

            // 0.5秒后移除动画
            Timeline cleanupTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(0.5), event -> {
                        if (animationLayer != null) {
                            animationLayer.getChildren().remove(animationView);
                        }
                    })
            );
            cleanupTimeline.play();

        } catch (Exception e) {
            System.err.println("加载消除动画失败: " + e.getMessage());
        }
    }

    private void createEliminationAnimation(Gem gem) {
        try {
            // 加载GIF动画
            Image animationImage = new Image(getClass().getResourceAsStream("/assets/textures/EliminateAnim.GIF"));
            ImageView animationView = new ImageView(animationImage);

            // 设置动画大小与方块一致
            animationView.setFitWidth(60);
            animationView.setFitHeight(60);
            animationView.setPreserveRatio(true);

            gem.setAnimating(true);
            gem.setAnimationView(animationView);

            // 0.5秒后清理动画状态
            Timeline cleanupTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(0.5), event -> {
                        gem.setAnimating(false);
                        gem.setAnimationView(null);
                    })
            );
            cleanupTimeline.play();

        } catch (Exception e) {
            System.err.println("加载消除动画失败: " + e.getMessage());
            // 如果动画加载失败，直接标记完成
            gem.setAnimating(false);
        }
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
    public boolean isAnimating() {
        return isAnimating;
    }
    public void setAnimationLayer(Pane animationLayer) {
        this.animationLayer = animationLayer;
    }
}