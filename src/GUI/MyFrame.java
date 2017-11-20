package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mayezhou on 15/12/9.
 */
public class MyFrame extends JFrame {
    public static MapPanel mapPanel = new MapPanel();
    public static InformationPanel informationPanel = new InformationPanel();

    public MyFrame() {
        setLayout(new BorderLayout());
        add(mapPanel, BorderLayout.CENTER);
        add(informationPanel, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        frame.setTitle("MapNavigationSystem");
        frame.setSize(1279, 644);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
