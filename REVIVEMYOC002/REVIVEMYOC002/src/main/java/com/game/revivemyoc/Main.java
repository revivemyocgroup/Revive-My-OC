package com.game.revivemyoc;

import com.game.revivemyoc.entity.GameBoard;
import com.game.revivemyoc.entity.Gem;
import com.game.revivemyoc.service.SwapService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 创建游戏棋盘对象
        GameBoard gameBoard = new GameBoard();

        // 生成棋盘
        gameBoard.generateBoard();

        // 创建一个 GridPane 来布局方块（9x9）
        GridPane gridPane = new GridPane();
        SwapService swapService = new SwapService(gameBoard, gridPane);
        // 遍历棋盘，获取每个方块并添加到 GridPane

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                Gem gem = gameBoard.getGemAt(x, y);
                gem.getImageView().setOnMouseClicked(event -> {
                    swapService.handleGemClick(event, gem);
                });
                gridPane.add(gem.getImageView(), y, x);
            }
        }

        // 创建场景
        Scene scene = new Scene(gridPane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Match-3 Game");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}