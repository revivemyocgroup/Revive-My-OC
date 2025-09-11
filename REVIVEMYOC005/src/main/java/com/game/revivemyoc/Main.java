package com.game.revivemyoc;

import com.game.revivemyoc.entity.GameBoard;
import com.game.revivemyoc.entity.Gem;
import com.game.revivemyoc.service.AnimationService;
import com.game.revivemyoc.service.GameLogicService;
import com.game.revivemyoc.service.SwapService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    private GameBoard gameBoard;
    private Pane animationLayer;

    @Override
    public void start(Stage primaryStage) {
        gameBoard = new GameBoard();
        gameBoard.generateBoard();

        // 先创建动画层
        animationLayer = new Pane();
        animationLayer.setMouseTransparent(true);

        GridPane gridPane = new GridPane();

        // 然后创建动画服务并设置动画层
        AnimationService animationService = new AnimationService();
        animationService.setAnimationLayer(animationLayer);

        GameLogicService logicService = new GameLogicService(animationService);
        SwapService swapService = new SwapService(gameBoard, gridPane, logicService);

        StackPane root = new StackPane();
        root.getChildren().addAll(gridPane, animationLayer);

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                Gem gem = gameBoard.getGemAt(c, r);
                if (gem != null) {
                    gem.getImageView().setOnMouseClicked(event -> swapService.handleGemClick(event, gem));
                    gridPane.add(gem.getImageView(), c, r);
                }
            }
        }

        // 删除动画渲染循环（不再需要）
        // startAnimationLoop();

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Match-3 Game");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}