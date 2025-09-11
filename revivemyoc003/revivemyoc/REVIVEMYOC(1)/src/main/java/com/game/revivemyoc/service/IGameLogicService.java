package com.game.revivemyoc.service;

import com.game.revivemyoc.entity.Gem;

import java.util.List;

public interface IGameLogicService {

    /**
     * 检测棋盘上的匹配并触发消除
     * @param board 游戏棋盘 (9x9 Gem 数组)
     */
    void detectAndEliminateMatches(Gem[][] board);

    /**
     * 检查棋盘是否有可匹配的宝石（用于判断游戏是否结束）
     * @param board 游戏棋盘
     * @return true 表示还有可消除的组合
     */
    boolean hasPossibleMoves(Gem[][] board);

    /**
     * 交换两个宝石（用于玩家操作）
     * @param gem1 宝石1
     * @param gem2 宝石2
     * @param board 游戏棋盘
     * @return true 表示交换有效（能产生消除），false 表示无效交换
     */
    boolean swapGems(Gem gem1, Gem gem2, Gem[][] board);

    /**
     * 刷新棋盘（执行下落 + 填充新宝石）
     * @param board 游戏棋盘
     */
    void refreshBoard(Gem[][] board);

    /**
     * 查找棋盘上所有匹配的宝石
     * @param board 游戏棋盘
     * @return 匹配到的宝石列表
     */
    List<Gem> findMatches(Gem[][] board);
}
