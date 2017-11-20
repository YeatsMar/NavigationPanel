package GUI;

import base.Vertex;
import bus.APforBus;
import bus.SSforBus;
import driving.APforDriving;
import driving.SSforDriving;
import walking.AllPair;
import walking.SingleSource;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by mayezhou on 15/12/9.
 */
public class InformationPanel extends JPanel {
    public ControlPanel controlPanel = new ControlPanel();
    public TextPanel textPanel = new TextPanel();
    public static JTextArea text = new JTextArea();
    public static JTextField text0;
    public static JTextField text1;
    public static JRadioButton jrb0 = new JRadioButton("path from beginning to end");
    public static JRadioButton jrb1 = new JRadioButton("path to end from every\n other location in .txt");
    public static JRadioButton jrb2 = new JRadioButton("path from every two locations in .txt");
    public static ButtonGroup group = new ButtonGroup();
    public static JButton btnWalk = new JButton("Only on foot");
    public static JButton btnBus = new JButton("Take bus?");
    public static JButton btnDrive = new JButton("Only in car");
    public static JButton btnReset = new JButton("Reset");
    public static ArrayList<Vertex> path;

    public InformationPanel() {
        setLayout(new BorderLayout());//GridLayout(2, 1, 5, 5)
        putClientProperty("JComponent.sizeVariant", "mini");
        setSize(new Dimension(211, 607));
        add(controlPanel, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
    }

    //inner class
    public class TextPanel extends JPanel {
        protected Font font = new Font("Courier", Font.PLAIN, 20);

        public TextPanel() {
            setBorder(new TitledBorder("Information Here"));
            setLayout(new BorderLayout());
            text.setFont(font);
            text.setLineWrap(true);// the line is automatically wrapped when the
            // text cannot fit in one line.
            text.setEditable(false);// user can only read
            text.setWrapStyleWord(true);// wrap on words rather than characters

            // create a scroll pane to hold the text area
            JScrollPane scrpllPane = new JScrollPane(text);

            text.setText("Welcome to Map Navigation System"
                    + "\nYou will see all information here");

            add(scrpllPane, BorderLayout.CENTER);
        }
    }

    //inner class
    public class ControlPanel extends JPanel {
        Font font = new Font("TimesRoman", Font.PLAIN, 25);
        PositionPanel positionPanel = new PositionPanel();
        ModePanel modePanel = new ModePanel();
        ChoicePanel choicePanel = new ChoicePanel();

        public ControlPanel() {
            setLayout(new BorderLayout());
            add(positionPanel, BorderLayout.NORTH);
            add(choicePanel, BorderLayout.CENTER);
            add(modePanel, BorderLayout.SOUTH);
        }

        public class PositionPanel extends JPanel {
            public PositionPanel() {
                setLayout(new GridLayout(1,4,5,5));
                JLabel label0 = new JLabel("From:");
                label0.setFont(font);
                add(label0);
                text0 = new JTextField();
                text0.setFont(font);
                add(text0);
                JLabel label1 = new JLabel("To:");
                label1.setFont(font);
                add(label1);
                text1 = new JTextField();
                text1.setFont(font);
                add(text1);
            }
        }

        public class ChoicePanel extends JPanel {
            public ChoicePanel() {
                setLayout(new GridLayout(3, 1, 5, 5));
                add(jrb0);
                add(jrb1);
                add(jrb2);
                group.add(jrb0);
                group.add(jrb1);
                group.add(jrb2);
            }
        }

        public class ModePanel extends JPanel {
            public ModePanel() {
                setLayout(new GridLayout(2,2));
                add(btnWalk);
                add(btnBus);
                add(btnDrive);
                add(btnReset);
                btnWalk.addActionListener(new WalkListener());
                btnBus.addActionListener(new BusListener());
                btnDrive.addActionListener(new DriveListener());
                btnReset.addActionListener(new ResetListener());
            }
        }
    }

    //inner listener
    public class WalkListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (jrb0.isSelected()) {
                try {
                    char begin = text0.getText().charAt(0);
                    char end = text1.getText().charAt(0);
                    if (begin-'A'>=0 && begin-'Z'<=0 && end-'A'>=0 && end-'Z'<=0) {
                        SingleSource g = new SingleSource();
                        Vertex s = g.getVertex(begin);
                        Vertex v = g.getVertex(end);
                        g.dijkstra(s);
                        path = g.printPath(s, v);
                        text.append("\nThe length of the path from " + s.name + " to " + v.name + ": " + v.d);
                        MyFrame.mapPanel.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid input! Please input a character between A & Z");
                    }
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, " Please input a character between A & Z!");
                }
            }
            else if (jrb1.isSelected()) {
                try {
                    char end = text1.getText().charAt(0);
                    if (end-'A'>=0 && end-'Z'<=0) {
                        SingleSource g2 = new SingleSource();
                        try {
                            g2.showAllLength(g2.getVertex(end), "ss_walking.txt");
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid input! Please input a character between A & Z");
                    }
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, " Please input a character between A & Z!");
                }
            }
            else if (jrb2.isSelected()) {
                AllPair g = new AllPair();
                try {
                    g.showAllLength("ap_walking.txt");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
            else {
                text.append("\nPlease choose a mode!");
            }
        }
    }

    //inner listener
    public class BusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (jrb0.isSelected()) {
                char begin = text0.getText().charAt(0);
                char end = text1.getText().charAt(0);
                if (begin-'A'>=0 && begin-'Z'<=0 && end-'A'>=0 && end-'Z'<=0) {
                    SSforBus g = new SSforBus();
                    Vertex s = g.getVertex(begin);
                    Vertex v = g.getVertex(end);
                    g.dijkstra(s);
                    path = g.printPath(s, v);
                    text.append("\nThe length of the path from " + s.name + " to " + v.name + ": " + v.d + "mins");
                    MyFrame.mapPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please input a character between A & Z");
                }
            }
            else if (jrb1.isSelected()) {
                char end = text1.getText().charAt(0);
                if (end-'A'>=0 && end-'Z'<=0) {
                    SSforBus g2 = new SSforBus();
                    try {
                        g2.showAllLength(g2.getVertex(end), "ss_bus.txt");
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please input a character between A & Z");
                }
            }
            else if (jrb2.isSelected()) {
                APforBus g = new APforBus();
                try {
                    g.showAllLength("ap_bus.txt");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
            else {
                text.append("\nPlease choose a function!");
            }
        }
    }

    //inner listener
    public class DriveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (jrb0.isSelected()) {
                char begin = text0.getText().charAt(0);
                char end = text1.getText().charAt(0);
                if (begin-'A'>=0 && begin-'Z'<=0 && end-'A'>=0 && end-'Z'<=0) {
                    SSforDriving g = new SSforDriving();
                    Vertex s = g.getVertex(begin);
                    Vertex v = g.getVertex(end);
                    g.dijkstra(s);
                    path = g.printPath(s, v);
                    if (v.d != Integer.MAX_VALUE) {
                        text.append("\nThe length of the path from " + s.name + " to " + v.name + ": " + v.d);
                        MyFrame.mapPanel.repaint();
                    } else {
                        text.append("\nThere is no path from " + s.name + " to " + v.name);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please input a character between A & Z");
                }
            }
            else if (jrb1.isSelected()) {
                char end = text1.getText().charAt(0);
                if (end-'A'>=0 && end-'Z'<=0) {
                    SSforDriving g2 = new SSforDriving();
                    try {
                        g2.showAllLength(g2.getVertex(end), "ss_driving.txt");
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please input a character between A & Z");
                }
            }
            else if (jrb2.isSelected()) {
                APforDriving g = new APforDriving();
                try {
                    g.showAllLength("ap_driving.txt");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
            else {
                text.append("\nPlease choose a mode!");
            }
        }
    }

    //inner listener
    public class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            text0.setText("");
            text1.setText("");
            MapPanel.hasSet = false;
            jrb0.setSelected(false);
            jrb1.setSelected(false);
            jrb2.setSelected(false);
        }
    }
}
