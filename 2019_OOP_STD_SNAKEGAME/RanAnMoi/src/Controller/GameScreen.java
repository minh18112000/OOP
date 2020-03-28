package Controller;

import View.MainScreen;
import static java.lang.Thread.sleep;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameScreen extends JPanel implements Runnable {

    MainScreen ms;
    static int[][] bg = new int[20][20];
    static int padding = 10;
    static int WIDTH = 400;
    static int HEIGHT = 400;
    static boolean isPlaying = false;
    static boolean enableTextStartGame = true;
    Snake snake;
    Thread thread;
    static int currentLevel = 1;
    static int score = 0;
    static boolean isGameOver = false;
    static int map;

    public GameScreen() {
        snake = new Snake();
        bg[10][10] = 2;
        thread = new Thread(this);
        thread.start();
    }

    public int getMap() {
        return map;
    }

    public void setMap(int map) {
        this.map = map;
    }

    @Override
    public void run() {
        long t2 = 0;

        while (true) {
            if (isPlaying) {
                snake.upDate();
            }
            repaint();

            try {
                sleep(20);
            } catch (InterruptedException ex) {

            }
        }
    }

    public void drawBg(Graphics g) {
        g.fillRect(0, 0, WIDTH + padding * 2 + 250, HEIGHT + padding * 2);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (map == 1) {
                    if (bg[i][j] == 2) {
                        g.setColor(Color.red);
                        g.fillRect(i * 20 + 1, j * 20 + 1, 18, 18);
                        g.setColor(Color.gray);
                    }
                } else if (map == 2) {
                    if (bg[i][j] == 2 || i == 0 || i == 19) {
                        g.setColor(Color.red);
                        g.fillRect(i * 20 + 1, j * 20 + 1, 18, 18);
                        g.setColor(Color.gray);
                    }
                }
            }
        }
    }

    public void drawFrame(Graphics g) {
        g.setColor(Color.orange);
        g.drawRect(0, 0, WIDTH + GameScreen.padding * 2, HEIGHT + GameScreen.padding * 2);
        g.drawRect(1, 1, WIDTH + GameScreen.padding * 2 - 2, HEIGHT + GameScreen.padding * 2 - 2);
        g.drawRect(2, 2, WIDTH + GameScreen.padding * 2 - 4, HEIGHT + GameScreen.padding * 2 - 4);

        g.drawRect(0, 0, WIDTH + GameScreen.padding * 2 + 250, HEIGHT + GameScreen.padding * 2);
        g.drawRect(1, 1, WIDTH + GameScreen.padding * 2 - 2 + 250, HEIGHT + GameScreen.padding * 2 - 2);
        g.drawRect(2, 2, WIDTH + GameScreen.padding * 2 - 4 + 250, HEIGHT + GameScreen.padding * 2 - 4);
    }

//    public void paint(Graphics g) {
//        drawBg(g);
//        snake.drawSnake(g);
//        drawFrame(g);
//        if (!isPlaying) {
//            if (enableTextStartGame) {
//                g.setColor(Color.white);
//                g.setFont(g.getFont().deriveFont(18.0f));
//                g.drawString("PRESS \"SPACE\" TO PLAY GAME", 80, 250);
//            }
//        }
//        if (isGameOver) {
//
//            g.setColor(Color.white);
//            g.setFont(g.getFont().deriveFont(28.0f));
//            g.drawString("GAME OVER", 120, 160);
//        }
//
//        g.setColor(Color.white);
//        g.setFont(g.getFont().deriveFont(28.0f));
//        g.drawString("LEVEL: " + currentLevel, 450, 100);
//
//        g.setFont(g.getFont().deriveFont(20.0f));
//        g.drawString("SCORE: " + score, 450, 150);
//
//    }
    public void paint(Graphics g) {
        drawBg(g);
        snake.drawSnake(g);
        drawFrame(g);
        if (!isPlaying) {
            if (enableTextStartGame) {
                g.setColor(Color.white);
                g.setFont(g.getFont().deriveFont(18.0f));
                g.drawString("PRESS SPACE TO PLAY GAME", 100, 250);
            }
        }
        if (isGameOver) {
            g.setColor(Color.white);
            g.setFont(g.getFont().deriveFont(28.0f));
            g.drawString("GAME OVER", 100, 150);
        }
        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(28.0f));
        g.drawString("LEVEL: " + currentLevel, 450, 100);

        g.setFont(g.getFont().deriveFont(20.0f));
        g.drawString("SCORE: " + score, 450, 150);
    }
    

    public static void main(String[] args) {
        SnakeGame f = new SnakeGame();
        f.setVisible(true);
    }
}
