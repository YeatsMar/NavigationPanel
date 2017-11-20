package driving;

import GUI.InformationPanel;
import base.Vertex;

import java.io.FileNotFoundException;

/**
 * Created by mayezhou on 15/12/8.
 */
public class SSforDriving extends walking.SingleSource {
    public SSforDriving() {
        super();
        for (int i = 0; i < 26; i++) {
            v[i] = new Vertex((char)('A'+i));
        }
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                w[i][j] = Double.MAX_VALUE;
            }
        }
        setAdjacent(getVertex('A'), getVertice("BD"));
        setAdjacent(getVertex('B'), getVertice("CG"));
        setAdjacent(getVertex('C'), getVertice("H"));
        setAdjacent(getVertex('D'), getVertice("EI"));
        setAdjacent(getVertex('E'), getVertice("F"));
        setAdjacent(getVertex('F'), getVertice("GK"));
        setAdjacent(getVertex('G'), getVertice("HL"));
        setAdjacent(getVertex('H'), getVertice("M"));
        setAdjacent(getVertex('I'), getVertice("JN"));
        setAdjacent(getVertex('J'), getVertice("K"));
        setAdjacent(getVertex('K'), getVertice("LP"));
        setAdjacent(getVertex('L'), getVertice("MR"));
        setAdjacent(getVertex('N'), getVertice("O"));
        setAdjacent(getVertex('O'), getVertice("PU"));
        setAdjacent(getVertex('P'), getVertice("T"));
        setAdjacent(getVertex('R'), getVertice("S"));
        setAdjacent(getVertex('S'), getVertice("Z"));
        setAdjacent(getVertex('T'), getVertice("UZ"));
        setAdjacent(getVertex('U'), getVertice("VXZ"));
        setAdjacent(getVertex('V'), getVertice("XW"));
        setAdjacent(getVertex('W'), getVertice("X"));
        setAdjacent(getVertex('X'), getVertice("Y"));
        setAdjacent(getVertex('Y'), getVertice("Z"));
        setW('A', 'B', 1.7);
        setW('A', 'D', 1.2);
        setW('B', 'C', 0.81);
        setW('B', 'G', 1.12);
        setW('C', 'H', 1.03);
        setW('D', 'E', 0.51);
        setW('D', 'I', 1.32);
        setW('E', 'F', 0.68);
        setW('F', 'G', 0.74);
        setW('F', 'K', 1.14);
        setW('G', 'H', 0.97);
        setW('G', 'L', 1.01);
        setW('H', 'M', 0.94);
        setW('I', 'J', 0.95);
        setW('I', 'N', 0.9);
        setW('J', 'K', 0.62);
        setW('K', 'L', 0.66);
        setW('K', 'P', 1.33);
        setW('L', 'M', 0.92);
        setW('L', 'R', 1.1);
        setW('N', 'O', 0.83);
        setW('O', 'P', 0.55);
        setW('O', 'U', 1.3);
        setW('P', 'T', 0.62);
        setW('R', 'S', 0.87);
        setW('S', 'Z', 0.38);
        setW('T', 'U', 1.12);
        setW('T', 'Z', 1.89);
        setW('U', 'V', 0.99);
        setW('U', 'X', 0.65);
        setW('U', 'Z', 1.7);
        setW('V', 'X', 0.58);
        setW('V', 'W', 1.1);
        setW('W', 'X', 0.57);
        setW('X', 'Y', 1.4);
        setW('Y', 'Z', 0.97);
        setWDirected('U', 'X', Double.MAX_VALUE);
        setWDirected('V', 'U', Double.MAX_VALUE);
        setWDirected('X', 'W', Double.MAX_VALUE);
        setWDirected('W', 'V', Double.MAX_VALUE);
    }

    public static void main(String[] args) {
        SSforDriving g = new SSforDriving();
        double result = g.showCertainLength(g.getVertex('A'), g.getVertex('Q'));
        if (result != Integer.MAX_VALUE) {
            System.out.println(result);//do not print out d
        }
        SSforDriving g2 = new SSforDriving();//if no path, print warning
        try {
            g2.showAllLength(g2.getVertex('A'), "ss_driving.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
