package View;

import Controller.HighScore;
import Model.User;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PC
 */
public class TypeName extends javax.swing.JFrame {

    /**
     * Creates new form AddFriend
     */
    MainScreen ms = new MainScreen();
    PrintWriter writer;
    HighScore hs = new HighScore();
    public static ArrayList<User> users;
    List<String> sort = hs.readFile();
    Vector vctHeader = new Vector();
    Vector vctData = new Vector();
    String name;

    public TypeName() {
        initComponents();
        this.setLocationRelativeTo(null);
        setTitle("ADD FRIEND");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("NEXT »»\n");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TYPE YOUR NAME TO SEARCH:");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("RETURN");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    public List<String> readFile() {
        List<String> sort = new ArrayList<String>();
        ArrayList<String> a = new ArrayList<String>();
        try {
            Scanner sc = new Scanner(Paths.get("data/data1.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                a.add(line);
            }    
            for (int i = 0; i < a.size() - 1; i++) {
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
                String[] n = a.get(i).split(" ");
                if (!sort.contains(a.get(i))) {
                    sort.add(a.get(i));
                }
            }
            sc.close();
        } catch (IOException ex) {
            Logger.getLogger(HighScore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sort;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String name = jTextField1.getText();
        String[] str;
        String user;
        String score;
        List<String> users = new ArrayList<>();
        List<String> scores = new ArrayList<>();
        List<String> list = new ArrayList<>();
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < sort.size(); i++) {
            str = sort.get(i).split(" ");
            user = str[0];
            if (user.compareTo(name) == 0) {
                users.add(user);
                scores.add(str[2]);
            }
        }
        if (users.size() == 0) {
            JOptionPane.showMessageDialog(rootPane, "User name does not exist" + "\n" + "Please enter user name again");

        } else {
            JOptionPane.showMessageDialog(rootPane, "Enter success");
            try {
                File temp = new File("data/data1.txt");
                if (temp.exists()) {
                    RandomAccessFile raf = new RandomAccessFile(temp, "rw");
                    raf.setLength(0);

                }
            } catch (IOException ex) {
                Logger.getLogger(TypeName.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            for (int i = 0; i < sort.size(); i++) {
                str = sort.get(i).split(" ");
                score = str[2];
                int diff = Integer.parseInt(score) - Integer.parseInt(scores.get(0));
                if (Math.abs(diff) <= 30 && name.compareTo(str[0]) != 0) {
                    list.add(sort.get(i));
                }
            }
            for (String u : list) {
                if (!lists.contains(u)) {
                    lists.add(u);
                }
            }
            try {
                FileWriter fw = new FileWriter("data/data1.txt");
                BufferedWriter bw = new BufferedWriter(fw);
                for (String u : lists) {
                    str = u.split(" ");
                    bw.write(str[0] + " " + str[1] + " " + str[2]);
                    bw.newLine();
                }
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.setVisible(false);
            SearchFriend search = new SearchFriend();
            search.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        MainScreen ms = new MainScreen();
        this.setVisible(false);
        ms.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TypeName.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TypeName.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TypeName.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TypeName.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TypeName().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
