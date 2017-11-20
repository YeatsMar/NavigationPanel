package driving;

import GUI.InformationPanel;
import walking.D;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;

/**
 * Created by mayezhou on 15/12/8.
 */
public class APforDriving extends SSforDriving{
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
                    BigDecimal t = new BigDecimal(temp);
                    d[k].distance[i][j] = t.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
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
                if (d[i][j] == Double.MAX_VALUE) {
                    System.out.println("no print from " + (char)('A'+i) + " to " + (char)('A'+j));
                    output.println("no print from " + (char)('A'+i) + " to " + (char)('A'+j));
//                    InformationPanel.text.append("\n"+"no print from " + (char)('A'+i) + " to " + (char)('A'+j));
                } else {
                    System.out.println("The shortest path between " + (char)('A'+i) + " and " + (char)('A'+j) + " is " + d[i][j] + "km");
                    output.println("The shortest path between " + (char)('A'+i) + " and " + (char)('A'+j) + " is " + d[i][j] + "km");
//                    InformationPanel.text.append("\n"+"The shortest path between " + (char)('A'+i) + " and " + (char)('A'+j) + " is " + d[i][j]);
                }
            }
        }
        output.close();
    }

    public static void main(String[] args) {
        APforDriving g = new APforDriving();
        try {
            g.showAllLength("ap_driving.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
