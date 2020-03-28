package Controller;


import Model.User;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

public class Snake {

    HighScore hs = new HighScore();
    List<String> list = hs.readFile();
    List<String> lists = new ArrayList<>();
    GameScreen gs;
    int length = 3;
    int[] x;
    int[] y;
    public static int GO_UP = 1;
    public static int GO_DOWN = -1;
    public static int GO_LEFT = 2;
    public static int GO_RIGHT = -2;
    int vector = Snake.GO_DOWN;
    long t1 = 0;
    static int speed;
    int maxLength = 9;
    boolean udAfterChangeVt = true;
    static int map;

    public Snake() {
        x = new int[100];
        y = new int[100];
        x[0] = 5;
        y[0] = 4;
        x[1] = 5;
        y[1] = 3;
        x[2] = 5;
        y[2] = 5;

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMap() {
        return map;
    }

    public void setMap(int map) {
        this.map = map;
    }
    
    public void restGame() {
        x = new int[100];
        y = new int[100];
        x[0] = 5;
        y[0] = 4;
        x[1] = 5;
        y[1] = 3;
        x[2] = 5;
        y[2] = 5;
        length = 3;

        vector = Snake.GO_DOWN;
    }

    public void setVecto(int v) {
        if (vector != -v && udAfterChangeVt) {
            vector = v;
            udAfterChangeVt = false;
        }
    }

    public boolean baitCoincideSnake(int x1, int y1) {
        if (map == 1) {
            for (int i = 0; i < length; i++) {
                if (x[i] == x1 && y[i] == y1) {
                    return true;
                }
            }
        } else if (map == 2) {
            for (int i = 0; i < length; i++) {
                if ((x[i] == x1 && y[i] == y1) || x1 == 0 || x1 == 19 || y1 == 0 || y1 == 19) {
                    return true;
                }
            }
        }

        return false;
    }

    public Point getPointBait() {
        Random r = new Random();
        int x;
        int y;
        do {
            x = r.nextInt(19);
            y = r.nextInt(19);
        } while (baitCoincideSnake(x, y));
        return new Point(x, y);

    }

    public int getCurrentSpeed() {
        for (int i = 0; i < GameScreen.currentLevel; i++) {
            speed *= 0.8;
        }
        return speed;
    }

    public void upDate() {
        if (length == maxLength) {
            GameScreen.isPlaying = false;
            restGame();
            GameScreen.currentLevel++;
            maxLength += 2;
            speed = getCurrentSpeed();
        }
        if (map == 1) {
            for (int i = 2; i < length; i++) {
                if (x[0] == x[i] && y[0] == y[i]) {
                    GameScreen.isPlaying = false;
                    GameScreen.isGameOver = true;
                    int n;
                    String name = JOptionPane.showInputDialog("Moi ban nhap ten");
                    if (name == null) {
                        name = "Player";
                    }
                    do {
                        n = 0;
                        for (String s : list) {
                            String[] str = s.split(" ");
                            if (name.compareTo(str[0]) == 0) {
                                JOptionPane.showMessageDialog(hs, "Name existed");
                                name = JOptionPane.showInputDialog("Moi ban nhap ten");
                                n = 1;
                            }
                        }

                    } while (n == 1);
                    SnakeGame.users.add(new User(name, String.valueOf(GameScreen.currentLevel), String.valueOf(GameScreen.score)));
                    SnakeGame.updateData();
                    GameScreen.score = 0;
                    GameScreen.currentLevel = 1;
                }
            }
        }
        if (map == 2) {
            for (int i = 2; i < length; i++) {
                if ((x[0] == x[i] && y[0] == y[i])) {
                    GameScreen.isPlaying = false;
                    GameScreen.isGameOver = true;
                    int n;
                    String name = JOptionPane.showInputDialog("Moi ban nhap ten");
                    if (name == null) {
                        name = "Player";
                    }
                    do {
                        n = 0;
                        for (String s : list) {
                            String[] str = s.split(" ");
                            if (name.compareTo(str[0]) == 0) {
                                JOptionPane.showMessageDialog(hs, "Name existed");
                                name = JOptionPane.showInputDialog("Moi ban nhap ten");
                                n = 1;
                            }
                        }

                    } while (n == 1);
                    SnakeGame.users.add(new User(name, String.valueOf(GameScreen.currentLevel), String.valueOf(GameScreen.score)));
                    SnakeGame.updateData();
                    GameScreen.score = 0;
                    GameScreen.currentLevel = 1;

                }
            }
            if (x[0] == 19 || x[0] == 0) {
                GameScreen.isPlaying = false;
                GameScreen.isGameOver = true;
                int n;
                String name = JOptionPane.showInputDialog("Moi ban nhap ten");
                if (name == null) {
                    name = "Player";
                }
                do {
                    n = 0;
                    for (String s : list) {
                        String[] str = s.split(" ");
                        if (name.compareTo(str[0]) == 0) {
                            JOptionPane.showMessageDialog(hs, "Name existed");
                            name = JOptionPane.showInputDialog("Moi ban nhap ten");
                            n = 1;
                        }
                    }

                } while (n == 1);
                SnakeGame.users.add(new User(name, String.valueOf(GameScreen.currentLevel), String.valueOf(GameScreen.score)));
                SnakeGame.updateData();
                GameScreen.score = 0;
                GameScreen.currentLevel = 1;
            }

        }
        if (System.currentTimeMillis() - t1 > speed) {
            udAfterChangeVt = true;
            if (GameScreen.bg[x[0]][y[0]] == 2) {
                length++;
                GameScreen.bg[x[0]][y[0]] = 0;
                GameScreen.bg[getPointBait().x][getPointBait().y] = 2;
                GameScreen.score += 10;

            }

            for (int i = length - 1; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }
            if (vector == Snake.GO_UP) {
                y[0]--;
            }
            if (vector == Snake.GO_DOWN) {
                y[0]++;
            }
            if (vector == Snake.GO_LEFT) {
                x[0]--;
            }
            if (vector == Snake.GO_RIGHT) {
                x[0]++;
            }
            if (x[0] < 0) {
                x[0] = 19;
            }
            if (x[0] > 19) {
                x[0] = 0;
            }
            if (y[0] < 0) {
                y[0] = 19;
            }
            if (y[0] > 19) {
                y[0] = 0;
            }
            t1 = System.currentTimeMillis();
        }

    }

    public void drawSnake(Graphics g) {
        g.setColor(Color.RED);
        for (int i = 0; i < length; i++) {
            g.fillRect(x[i] * 20 + 1, y[i] * 20 + 1, 18, 18);
        }

    }

}
