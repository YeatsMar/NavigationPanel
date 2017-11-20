package walking;

import GUI.InformationPanel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;

/**
 * Created by mayezhou on 15/12/7.
 */
public class AllPair extends base.Graph {
    public AllPair() {
        super();
        setW('A', 'B', Double.MAX_VALUE);
        setW('B', 'C', Double.MAX_VALUE);
    }

    public D FloydWarshall() {
        int n = w.length;
        D[] d = new D[n+1];
        d[0] = new D();
        d[0].distance = w.clone();
        for (int k = 1; k < n+1; k++) {
            d[k] = new D();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    double temp = Math.min(d[k-1].distance[i][j], d[k-1].distance[i][k-1]+d[k-1].distance[k-1][j]);
                    if (!Double.isInfinite(temp) && !Double.isNaN(temp)) {
                        BigDecimal t = new BigDecimal(temp);
                        d[k].distance[i][j] = t.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    } else {
                        d[k].distance[i][j] = Math.min(d[k-1].distance[i][j], d[k-1].distance[i][k-1]+d[k-1].distance[k-1][j]);
                    }
                }
            }
        }
        return d[n];
    }

    public void showAllLength(String txtName) throws FileNotFoundException {
        File file = new File(txtName);
        PrintWriter output = new PrintWriter(file);
        double[][] d = FloydWarshall().distance;
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d.length; j++) {
                if (i == j) {
                    d[i][j] = 0;
                }
                System.out.println("The shortest path between " + (char)('A'+i) + " and " + (char)('A'+j) + " is " + d[i][j] + "km");
                output.println("The shortest path between " + (char)('A'+i) + " and " + (char)('A'+j) + " is " + d[i][j] + "km");
//                InformationPanel.text.append("\n"+"The shortest path between " + (char)('A'+i) + " and " + (char)('A'+j) + " is " + d[i][j]);
            }
        }
        output.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        AllPair g = new AllPair();
        g.showAllLength("ap_walking.txt");
    }
}
