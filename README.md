
# Revivemyoc Match-3 Game

Revivemyoc 是一款经典的消消乐游戏（Match-3 Game），使用 JavaFX 和 FXGL 构建。游戏的核心玩法包括交换宝石、消除匹配宝石、得分和动画效果。游戏中的棋盘为 9x9 格，每个格子中都有一种颜色的宝石，玩家通过交换相邻的宝石，形成三个或更多同色宝石的直线来进行消除。

## 项目结构

以下是本项目的主要文件夹结构及说明：
<img width="826" height="1190" alt="image" src="https://github.com/user-attachments/assets/8407495d-0067-447c-9c65-440e36c8b52c" />
![image](https://github.com/rosy-nallian/Revive-My-OC/blob/main/image.png)
Revivemyoc/
├── pom.xml # Maven 项目配置文件，定义依赖（如 FXGL 和 JavaFX）
├── README.md # 项目说明文档
├── src/
│ └── main/
│ ├── java/ # Java源代码目录
│ │ └── com/
│ │ └── game/
│ │ └── revivemyoc/
│ │ ├── GameApp.java # 主游戏应用类，继承 FXGL 的 GameApplication
│ │ ├── entity/ # 游戏实体（例如：宝石、位置等）
│ │ │ ├── Gem.java # 宝石实体
│ │ │ ├── Position.java # 坐标位置实体
│ │ ├── service/ # 游戏服务（例如：逻辑、动画等）
│ │ │ ├── GameDataService.java # 数据服务（管理分数等）
│ │ │ ├── GameLogicService.java # 游戏逻辑服务
│ │ │ └── AnimationService.java # 动画服务
│ │ ├── factory/ # 游戏实体工厂（用于创建宝石等）
│ │ │ └── EntityFactory.java # 实体工厂
│ │ ├── ui/ # UI组件（例如：分数显示）
│ │ │ └── ScoreWidget.java # 分数显示组件
│ └── resources/ # 资源文件夹（如：图片、音效、背景等）
│ ├── assets/ # 图片和音效等资源
│ │ ├── textures/ # 宝石的图像资源
│ │ ├── sounds/ # 音效资源
│ │ └── ui/ # UI 相关的图片
│ ├── css/ # JavaFX 的 CSS 样式文件
│ └── music/ # 背景音乐
└── target/ # Maven 构建输出文件（自动生成）
├── classes/ # 编译后的 class 文件
└── Revivemyoc.jar # 打包后的可执行 JAR 文件


## 项目依赖

### 1. 版本Maven

该项目使用 Maven 来管理依赖和构建过程，所有依赖都在 `pom.xml` 文件中定义。以下是部分重要依赖：

```xml
<dependencies>
    <!-- FXGL 游戏引擎依赖 -->
     <dependency>
                <groupId>com.github.almasb</groupId>
                <artifactId>fxgl</artifactId>
                <version>17</version>
            </dependency>

    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>21</version>
    </dependency>

</dependencies>
### 2. 插件
Maven 构建过程中的插件配置：

<build>
    <plugins>
        <plugin>
            <groupId>org.openjfx</groupId>
                <artifactId>javafx-maven-plugin</artifactId>
                <version>0.0.8</version>
                <executions>
                    <execution>
                        <id>default-cli</id>
                        <configuration>
                            <mainClass>com.game.revivemyoc.HelloApplication</mainClass>
                        </configuration>
                        <goals>
                            <goal>run</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
    </plugins>
</build>

### 3.包结构
com.game.revivemyoc.entity
Gem.java: 代表棋盘上的一个方块，包含颜色和位置属性。

Position.java: 代表棋盘上的位置，存储 x 和 y 坐标。

com.game.revivemyoc.service
IGameLogicService.java: 游戏逻辑接口，处理匹配、消除、下落等核心功能。

GameLogicService.java: 游戏逻辑实现类，具体实现了核心功能。

IAnimationService.java: 动画服务接口，负责游戏中的动画效果（如交换、消除、下落等）。

AnimationService.java: 动画服务实现类，负责播放各种动画效果。

IGameDataService.java: 数据服务接口，负责管理游戏分数、进度等数据。

GameDataService.java: 数据服务实现类，具体实现了分数、进度管理等功能。

com.game.revivemyoc.factory
EntityFactory.java: 用于创建游戏中的实体对象（如宝石），并设置相关属性。

com.game.revivemyoc.ui
ScoreWidget.java: 负责显示分数和其他 UI 元素。

### 4.核心接口
1. IGameLogicService (核心游戏逻辑接口)
定义了核心的游戏逻辑方法，如查找匹配的宝石、消除宝石、下落等操作。

2. IAnimationService (动画服务接口)
负责处理所有游戏中的动画效果，如交换、消除、下落和新宝石生成等。

3. IGameDataService (数据服务接口)
管理游戏进度、得分等数据。

4. EntityFactory (实体工厂)
用于创建游戏中的宝石等实体。
