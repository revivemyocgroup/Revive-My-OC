package com.game.revivemyoc.service;

public interface IGameDataService {

    /**
     * 获取当前分数
     */
    int getScore();

    /**
     * 增加分数
     * @param points 分数值
     */
    void addScore(int points);

    /**
     * 重置分数（新游戏开始时）
     */
    void resetScore();

    /**
     * 获取当前关卡
     */
    int getLevel();

    /**
     * 进入下一关
     */
    void nextLevel();

    /**
     * 获取剩余步数（如果有步数限制模式）
     */
    int getMoves();

    /**
     * 使用一步操作
     */
    void useMove();

    /**
     * 重置步数（开始新关卡）
     * @param moves 初始步数
     */
    void resetMoves(int moves);
}

