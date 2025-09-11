package com.game.revivemyoc.service;

import com.game.revivemyoc.entity.Gem;
import java.util.List;

public interface IAnimationService {
    // 执行消除宝石的动画
    void animateEliminate(List<Gem> matchedGems, Runnable onFinish);

    // 执行方块下落的动画
    void animateFall(Gem[][] board, Runnable onFinish);

    // 执行新宝石生成的下落动画
    void animateNewGems(Gem[][] board, Runnable onFinish);


}
