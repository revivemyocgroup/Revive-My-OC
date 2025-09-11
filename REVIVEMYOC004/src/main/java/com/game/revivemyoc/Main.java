package com.game.revivemyoc;

import com.game.revivemyoc.entity.GameBoard;
import com.game.revivemyoc.entity.Gem;
import com.game.revivemyoc.service.AnimationService;
import com.game.revivemyoc.service.GameLogicService;
import com.game.revivemyoc.service.SwapService;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    private GameBoard gameBoard;
    private Pane animationLayer; // 添加动画层

    @Override
    public void start(Stage primaryStage) {
        gameBoard = new GameBoard();
        gameBoard.generateBoard();

        GridPane gridPane = new GridPane();
        AnimationService animationService = new AnimationService();
        GameLogicService logicService = new GameLogicService(animationService);
        SwapService swapService = new SwapService(gameBoard, gridPane, logicService);

        // 创建动画层
        animationLayer = new Pane();
        StackPane root = new StackPane();
        root.getChildren().addAll(gridPane, animationLayer);
        animationLayer.setMouseTransparent(true);

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                Gem gem = gameBoard.getGemAt(c, r);
                gem.getImageView().setOnMouseClicked(event -> swapService.handleGemClick(event, gem));
                gridPane.add(gem.getImageView(), c, r);
            }
        }

        // 启动动画渲染循环
        startAnimationLoop();

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Match-3 Game");
        primaryStage.show();
    }

    private void startAnimationLoop() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                renderAnimations();
            }
        };
        animationTimer.start();
    }

    private void renderAnimations() {
        animationLayer.getChildren().clear();

        // 渲染所有正在播放的动画
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                Gem gem = gameBoard.getGemAt(c, r);
                if (gem != null && gem.isAnimating() && gem.getAnimationView() != null) {
                    // 设置动画位置（与方块位置对应）
                    gem.getAnimationView().setLayoutX(c * 60);
                    gem.getAnimationView().setLayoutY(r * 60);
                    animationLayer.getChildren().add(gem.getAnimationView());
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}