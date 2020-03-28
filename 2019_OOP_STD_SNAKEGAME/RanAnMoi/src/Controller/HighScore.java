package Controller;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class HighScore extends JFrame {

    static ArrayList<String> a = new ArrayList<String>();

    public static List<String> readFile() {
        List<String> sort = new ArrayList<String>();
        List<Integer> data = new ArrayList<Integer>();
        try {
            Scanner sc = new Scanner(Paths.get("data/data.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] str = line.split(" ");
                data.add(Integer.parseInt(str[1]));

                a.add(line);
            }

            for (int i = 0; i < a.size(); i++) {
                for (int j = i + 1; j < a.size(); j++) {
                    String[] str1 = a.get(i).split(" ");
                    String[] str2 = a.get(j).split(" ");
                    if (Integer.parseInt(str1[2]) < Integer.parseInt(str2[2])) {
                        String temp = a.get(i);
                        a.set(i, a.get(j));
                        a.set(j, temp);
                    }
                }
            }
            for (int i = 0; i < a.size(); i++) {
                sort.add(a.get(i));
            }

            sc.close();
        } catch (IOException ex) {
            Logger.getLogger(HighScore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sort;
    }

    public HighScore() {
        setSize(500, 500);
        setTitle("HIGH SCORE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void sort(List<String> a) {
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
    }

    public static void main(String[] args) {
        sort(readFile());
    }
}
