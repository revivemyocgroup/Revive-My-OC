package com.game.revivemyoc.entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Gem {
    private GemType type;  // 方块的类型（颜色）
    private int x;        // X 坐标
    private int y;        // Y 坐标
    private String imagePath; // 图像路径
    private ImageView imageView; // 用于显示图像
    private boolean isAnimating; // 添加动画状态
    private transient ImageView animationView;

    // 构造函数
    public Gem(GemType type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
        // 根据类型设置图片路径
        this.imagePath = "/assets/textures/gem_" + type.name().toLowerCase() + ".png";

        // 创建 ImageView 对象，并设置方块大小
        this.imageView = new ImageView(new Image(getClass().getResource(this.imagePath).toExternalForm()));

        // 设置方块的尺寸（比如 60x60 像素）
        this.imageView.setFitWidth(60);
        this.imageView.setFitHeight(60);
        this.imageView.setPreserveRatio(true);  // 保持图片比例，防止拉伸


    }
    public boolean isAnimating() { return isAnimating; }
    public void setAnimating(boolean animating) { isAnimating = animating; }
    public ImageView getAnimationView() { return animationView; }
    public void setAnimationView(ImageView animationView) { this.animationView = animationView; }
    // 获取方块类型（颜色）
    public GemType getType() {
        return type;
    }

    // 获取方块的颜色
    public String getColor() {
        return type.name().toLowerCase(); // 颜色就是类型
    }

    // 获取图像路径
    public String getImagePath() {
        return imagePath;
    }

    // 获取图像视图
    public ImageView getImageView() {
        return imageView;
    }

    // 获取X坐标
    public int getX() {
        return x;
    }

    // 获取Y坐标
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

