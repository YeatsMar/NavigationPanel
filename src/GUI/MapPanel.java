package GUI;

import base.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by mayezhou on 15/12/9.
 */
public class MapPanel extends JPanel {
    private ImageIcon mapIcon = new ImageIcon("src/map.png");
    private Image map = mapIcon.getImage();
    private JButton[] buttons = new JButton[26];
    private ImageIcon dotIcon = new ImageIcon("redpoint.png");
    public static boolean hasSet = false;

    public MapPanel() {
        setLayout(null);
        for (int i = 0; i < 26; i++) {
            buttons[i] = new JButton(dotIcon);
            buttons[i].setHorizontalAlignment(SwingConstants.CENTER);
        }
        Scanner input = null;
        try {
            input = new Scanner(new File("cordinate.txt"));
            for (int i = 0; i < 26; i++) {
                int x = input.nextInt();
                int y = input.nextInt();
                buttons[i].setBounds(x, y, 20, 32);
            }
        } catch (FileNotFoundException e) {
            System.out.println("hasn't setBounds!");
            e.printStackTrace();
        }
        for (int i = 0; i < 26; i++) {
            add(buttons[i]);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = 0;
                    while (e.getSource() != buttons[i]) {
                        i++;
                    }
                    if (!hasSet) {
                        InformationPanel.text0.setText((char)('A'+i)+"");
                        hasSet = true;
                    }
                    else {
                        InformationPanel.text1.setText((char)('A'+i)+"");
                        hasSet = false;
                    }
                }
            });
        }
    }

    // draw the map
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(map, 0, 0, 1024, 607, this);//1024, 607,
        if (InformationPanel.path != null) {
            ArrayList<Vertex> path = InformationPanel.path;
            for (int i = 0; i < path.size()-1; i++) {
                g.setColor(Color.GREEN);
                g.drawLine(path.get(i).x, path.get(i).y, path.get(i+1).x, path.get(i+1).y);
            }
        }
    }
}
