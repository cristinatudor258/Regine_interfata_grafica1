package regine_interfata_grafica1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import static javax.swing.JOptionPane.showMessageDialog;
import regine_interfata_grafica1.Fereastra1;

public class Regine_interfata_grafica1 {

    public static int nrB;
    public static int numar;
    public static String s;

    public static void main(String[] args) {
        s = JOptionPane.showInputDialog("Introduceti dimensiunea n a tablei:");
        numar = Integer.parseInt(s);
        while (numar < 4 || numar > 12) {
            JOptionPane.showMessageDialog(null, "Ati introdus un numar incorect! Introduceti un numar cuprins in intervalul [4,12]!");
            s = JOptionPane.showInputDialog("Introduceti o alta dimensiune n a tablei:");
            numar = Integer.parseInt(s);
        }
        nrB = numar * numar;
        Fereastra1 f = new Fereastra1(nrB);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
