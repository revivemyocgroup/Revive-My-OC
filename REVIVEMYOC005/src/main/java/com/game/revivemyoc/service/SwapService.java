package com.game.revivemyoc.service;

import com.game.revivemyoc.entity.Gem;
import com.game.revivemyoc.entity.GameBoard;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class SwapService {

    private final GameBoard gameBoard;
    private final GridPane gridPane;
    private final GameLogicService logicService;
    private Gem firstSelectedGem;

    public SwapService(GameBoard gameBoard, GridPane gridPane, GameLogicService logicService) {
        this.gameBoard = gameBoard;
        this.gridPane = gridPane;
        this.logicService = logicService;
        this.firstSelectedGem = null;
    }

    public void handleGemClick(MouseEvent event, Gem clickedGem) {
        if (logicService.getAnimationService().isAnimating()) {
            return;
        }
        if (firstSelectedGem == null) {
            firstSelectedGem = clickedGem;
            highlightGem(firstSelectedGem, true);
        } else {
            if (areGemsAdjacent(firstSelectedGem, clickedGem)) {
                boolean success = logicService.swapGems(firstSelectedGem, clickedGem, gameBoard.getBoard());
                refreshGridDisplay();
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

    private void refreshGridDisplay() {
        gridPane.getChildren().clear();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                Gem gem = gameBoard.getGemAt(c, r);
                if (gem != null) { // 添加null检查
                    gridPane.add(gem.getImageView(), c, r);

                    // 绑定点击事件
                    gem.getImageView().setOnMouseClicked(event -> handleGemClick(event, gem));
                }
            }
        }
    }

    private void highlightGem(Gem gem, boolean highlight) {
        gem.getImageView().setOpacity(highlight ? 0.7 : 1.0);
    }
}
