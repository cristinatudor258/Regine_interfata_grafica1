package regine_interfata_grafica1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fereastra1 extends JFrame {

    private int nrB;
    private JButton jb[];
    private JButton jbStart;
    private JTextField jtf;
    private JButton jbNext;
    private int i, j;

    public Fereastra1(int nrB) {
        //DECLARARE COMPONENTE GRAFICE
        this.setTitle("Asezarea reginelor pe tabla ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.nrB = nrB;
        jb = new JButton[nrB];
        int i, j;
        int N = (int) Math.sqrt(nrB);

        //DESIGN
        JPanel jp[] = new JPanel[N];
        Font font = new Font("Arial", Font.PLAIN, 16);
        for (i = 0; i < N; i++) {
            jp[i] = new JPanel();
            for (j = 0; j < N; j++) {
                int nrButonCrt = N * i + j;
                String numeButonCrt = nrButonCrt + "";
                jb[nrButonCrt] = new JButton();
                jb[nrButonCrt].setPreferredSize(new Dimension(60, 60));
                jp[i].add(jb[nrButonCrt]);
            }
        }
        JPanel jpFinal = new JPanel();
        jpFinal.setLayout(new GridLayout(N + 1, 1));
        for (i = 0; i < N; i++) {
            jpFinal.add(jp[i]);
        }
        jtf = new JTextField(14);
        jtf.setFont(font);
        jtf.setEditable(false);
        JPanel j2Panel = new JPanel();
        j2Panel.add(jtf);
        AscultaButoane ab = new AscultaButoane();
        jbStart = new JButton("Start");
        jbStart.setFont(font);
        jbStart.addActionListener(ab);
        jbStart.setPreferredSize(new Dimension(80, 40));
        j2Panel.add(jbStart);
        jbNext = new JButton("Next");
        jbNext.setFont(font);
        jbNext.addActionListener(ab);
        jbNext.setPreferredSize(new Dimension(80, 40));
        j2Panel.add(jbNext);
        jpFinal.add(j2Panel);
        Container c = this.getContentPane();
        c.add(jpFinal, "South");

        //Initializare tabla:
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                int nrButonCrt = N * i + j;
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        jb[nrButonCrt].setBackground(Color.gray);
                    } else {
                        jb[nrButonCrt].setBackground(Color.white);
                    }
                } else if (j % 2 == 0) {
                    jb[nrButonCrt].setBackground(Color.white);
                } else {
                    jb[nrButonCrt].setBackground(Color.gray);
                }
            }
        }

    }

    //EVENIMENTE
    class AscultaButoane implements ActionListener {

        public int S;
        int ok = 0;

        @Override
        public void actionPerformed(ActionEvent ev) {
            Object sursa = ev.getSource();
            int n = (int) Math.sqrt(nrB);
            int rez[] = new int[n];
            Regine1 obj = new Regine1(nrB, rez);
            int sol = 0;
            if ((JButton) sursa == jbStart) {
                obj.BT1(n);
                S = 0;
                ok = 1;
                int i, j;
                ImageIcon gri = new ImageIcon("gri.jpg");
                ImageIcon alb = new ImageIcon("alb.jpg");
                for (i = 0; i < n; i++) {
                    for (j = 0; j < n; j++) {
                        int nrButonCrt = n * i + j;
                        if (i % 2 == 0) {
                            if (j % 2 == 0) {
                                jb[nrButonCrt].setIcon(gri);
                            } else {
                                jb[nrButonCrt].setIcon(alb);
                            }
                        } else if (j % 2 == 0) {
                            jb[nrButonCrt].setIcon(alb);
                        } else {
                            jb[nrButonCrt].setIcon(gri);
                        }
                    }
                }
                sol = 0;
                for (j = 0; j < n; j++) {
                    int k = obj.matrice[0][j];
                    ImageIcon imagine = new ImageIcon("mica.png");
                    jb[k].setIcon(imagine);;
                    jb[k].setPressedIcon(imagine);
                    jtf.setText("Posibilitatea " + (S + 1));
                }
            }
            if ((JButton) sursa == jbNext) {
                int i, j;

                //Initializare tabla:
                ImageIcon gri = new ImageIcon("gri.jpg");
                ImageIcon alb = new ImageIcon("alb.jpg");
                for (i = 0; i < n; i++) {
                    for (j = 0; j < n; j++) {
                        int nrButonCrt = n * i + j;
                        if (i % 2 == 0) {
                            if (j % 2 == 0) {
                                jb[nrButonCrt].setIcon(gri);
                            } else {
                                jb[nrButonCrt].setIcon(alb);
                            }
                        } else if (j % 2 == 0) {
                            jb[nrButonCrt].setIcon(alb);
                        } else {
                            jb[nrButonCrt].setIcon(gri);
                        }
                    }
                }

                S++;

                if (ok == 1) {
                    if (S > obj.nrSol) {
                        JOptionPane.showMessageDialog(null, "Nu mai exista alte posibilitati distincte de aranjare a reginelor!");
                        jtf.setText(" ");
                        obj.nrSol = -1;
                        ok = 0;
                    } else {
                        for (j = 0; j < n; j++) {
                            int k = obj.matrice[S][j];
                            ImageIcon imagine = new ImageIcon("mica.png");
                            jb[k].setIcon(imagine);
                            jtf.setText("Posibilitatea " + (S + 1));
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Apasati mai intai butonul Start!");
                    jtf.setText(" ");
                }
            }
        }
    }
}
