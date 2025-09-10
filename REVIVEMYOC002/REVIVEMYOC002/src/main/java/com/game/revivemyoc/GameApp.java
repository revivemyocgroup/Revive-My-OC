package com.game.revivemyoc;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import static com.almasb.fxgl.dsl.FXGL.*;   // 静态导入 FXGL DSL 方法

public class GameApp extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(2000);   // 背景图的宽
        settings.setHeight(1100);  // 背景图的高
        settings.setTitle("复活吧我的oc");
        settings.setVersion("0.1");
    }

    @Override
    protected void initGame() {
        // 1. 背景
        entityBuilder()
                .at(0, 0)
                .view(texture("boat.png", getAppWidth(), getAppHeight()))
                .zIndex(-1) // 保证背景在最底层
                .buildAndAttach();

        // 2. 登录图标（比如放在右上角）
        Texture loginIcon = texture("load in.png", 350, 84);

        Entity loginButton = entityBuilder()
                .at(getAppWidth() - 1150, 900) // 距离右边 1000px，顶部 900px
                .view(loginIcon)
                .zIndex(1)
                .buildAndAttach();

        // 让登录图标能响应鼠标点击
        loginIcon.setOnMouseClicked(e -> {
            System.out.println("登录中...");
            showMessage("正在登录...");  // FXGL 自带弹窗
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
