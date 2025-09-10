package com.game.revivemyoc.service;

import com.game.revivemyoc.entity.Gem;
import com.game.revivemyoc.entity.GameBoard;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class SwapService {
    private GameBoard gameBoard;
    private Gem firstSelectedGem;
    private GridPane gridPane;

    public SwapService(GameBoard gameBoard, GridPane gridPane) {
        this.gameBoard = gameBoard;
        this.gridPane = gridPane;
        this.firstSelectedGem = null;
    }

    public void handleGemClick(MouseEvent event, Gem clickedGem) {
        if (firstSelectedGem == null) {
            firstSelectedGem = clickedGem;
            highlightGem(firstSelectedGem, true);
        } else {
            if (areGemsAdjacent(firstSelectedGem, clickedGem)) {
                swapGems(firstSelectedGem, clickedGem);
                refreshGridDisplay(); // 交换后刷新显示
            }
            highlightGem(firstSelectedGem, false);
            firstSelectedGem = null;
        }
    }

    private boolean areGemsAdjacent(Gem gem1, Gem gem2) {
        int dx = Math.abs(gem1.getX() - gem2.getX());
        int dy = Math.abs(gem1.getY() - gem2.getY());
        return (dx == 1 && dy == 0) || (dx == 0 && dy == 1);
    }

    private void swapGems(Gem gem1, Gem gem2) {
        // 交换棋盘数据
        int tempX = gem1.getX();
        int tempY = gem1.getY();

        gem1.setX(gem2.getX());
        gem1.setY(gem2.getY());

        gem2.setX(tempX);
        gem2.setY(tempY);

        // 更新棋盘数组
        gameBoard.setGemAt(gem1.getX(), gem1.getY(), gem1);
        gameBoard.setGemAt(gem2.getX(), gem2.getY(), gem2);
    }

    private void refreshGridDisplay() {
        gridPane.getChildren().clear();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                Gem gem = gameBoard.getGemAt(x, y);
                gridPane.add(gem.getImageView(), y, x);
            }
        }
    }

    private void highlightGem(Gem gem, boolean highlight) {
        if (highlight) {
            gem.getImageView().setOpacity(0.7);
        } else {
            gem.getImageView().setOpacity(1.0);
        }
    }
}