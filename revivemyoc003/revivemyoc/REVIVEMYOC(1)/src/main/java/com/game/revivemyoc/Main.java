package com.game.revivemyoc;

import com.game.revivemyoc.entity.GameBoard;
import com.game.revivemyoc.entity.Gem;
import com.game.revivemyoc.service.AnimationService;
import com.game.revivemyoc.service.GameLogicService;
import com.game.revivemyoc.service.SwapService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        GameBoard gameBoard = new GameBoard();
        gameBoard.generateBoard();

        GridPane gridPane = new GridPane();
        AnimationService animationService = new AnimationService();
        GameLogicService logicService = new GameLogicService(animationService);
        SwapService swapService = new SwapService(gameBoard, gridPane, logicService);

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                Gem gem = gameBoard.getGemAt(c, r);
                gem.getImageView().setOnMouseClicked(event -> swapService.handleGemClick(event, gem));
                gridPane.add(gem.getImageView(), c, r);
            }
        }

        Scene scene = new Scene(gridPane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Match-3 Game");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
