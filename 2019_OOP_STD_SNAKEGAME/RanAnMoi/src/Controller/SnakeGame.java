package Controller;

import Model.User;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class SnakeGame extends JFrame {

    GameScreen game;
    public static ArrayList<User> users;

    public SnakeGame() {

        setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("PLAY GAME");
        game = new GameScreen();
        add(game);
        this.addKeyListener(new handler());
        users = new ArrayList<User>();
        readData();
    }

    private class handler implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                GameScreen.isPlaying = !GameScreen.isPlaying;
                if (GameScreen.isGameOver) {
                    GameScreen.isGameOver = false;
                    game.snake.restGame();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                game.snake.setVecto(Snake.GO_UP);

            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                game.snake.setVecto(Snake.GO_DOWN);

            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                game.snake.setVecto(Snake.GO_LEFT);

            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                game.snake.setVecto(Snake.GO_RIGHT);

            }
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

    }

    public static void updateData() {
        try {
            FileWriter fw = new FileWriter("data/data.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (User u : users) {
                bw.write(u.getName() + " " + u.getLevel() + " " + u.getScore());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readData() {
        try {
            FileReader fr = new FileReader("data/data.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] str = line.split(" ");
                users.add(new User(str[0], str[1], str[2]));
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
