package base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;

/**
 * Created by mayezhou on 15/12/6.
 */
public class Graph {
    public Vertex[] v = new Vertex[26];
    public double[][] w = new double[26][26];

    public Graph() {
        for (int i = 0; i < 26; i++) {
            v[i] = new Vertex((char)('A'+i));
        }
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                w[i][j] = Double.MAX_VALUE;
            }
        }
        getVertex('A').setXY(70, 221);
        getVertex('B').setXY(130, 470);
        getVertex('C').setXY(150, 593);
        getVertex('D').setXY(240, 157);
        getVertex('E').setXY(257, 220);
        getVertex('F').setXY(268, 330);
        getVertex('G').setXY(280, 420);
        getVertex('H').setXY(300, 564);
        getVertex('I').setXY(407, 73);
        getVertex('J').setXY(417, 183);
        getVertex('K').setXY(425, 295);
        getVertex('L').setXY(430, 397);
        getVertex('M').setXY(440, 532);
        getVertex('N').setXY(541, 46);
        getVertex('O').setXY(572, 144);
        getVertex('P').setXY(602, 238);
        getVertex('Q').setXY(658, 311);
        getVertex('R').setXY(593, 397);
        getVertex('S').setXY(719, 374);
        getVertex('T').setXY(674, 191);
        getVertex('U').setXY(774, 113);
        getVertex('V').setXY(875, 22);
        getVertex('W').setXY(955, 98);
        getVertex('X').setXY(880, 106);
        getVertex('Y').setXY(909, 309);
        getVertex('Z').setXY(772, 356);
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
        setAdjacent(getVertex('P'), getVertice("QT"));
        setAdjacent(getVertex('Q'), getVertice("RST"));
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
        setW('P', 'Q', 0.62);
        setW('P', 'T', 0.62);
        setW('Q', 'R', 0.73);
        setW('Q', 'S', 0.51);
        setW('Q', 'T', 0.93);
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
    }

    public Vertex getVertex(char name) {
        return v[name-'A'];
    }

    public Vertex[] getVertice(String vs) {
        Vertex[] result = new Vertex[vs.length()];
        for (int i = 0; i < result.length; i++) {
            result[i] = getVertex(vs.charAt(i));
        }
        return result;
    }

    public void setAdjacent(Vertex u, Vertex[] adj) {
        for (int i = 0; i < adj.length; i++) {
            u.adjacent.add(adj[i]);
            adj[i].adjacent.add(u);
        }
    }

    public void clearAdjacent(Vertex u) {
        u.adjacent.clear();
    }

    public void setW(char a, char b, double edge) {
        w[a-'A'][b-'A'] = edge;
        w[b-'A'][a-'A'] = edge;
    }

    public void setWTime() {
        for (int i = 0; i < w.length; i++) {
            for (int j = 0; j < w[0].length; j++) {
                w[i][j] /= 0.07;
            }
        }
    }

    public void setWDirected(char a, char b, double edge) {
        w[a-'A'][b-'A'] = edge;
    }

    public double getW(char a, char b) {
        return w[a-'A'][b-'A'];
    }
}
