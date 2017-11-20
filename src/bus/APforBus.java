package bus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by mayezhou on 15/12/8.
 */
public class APforBus extends walking.AllPair{
    public APforBus() {
        super();
        setWTime();
        setW('F', 'K', 4);
        setW('T', 'K', 5);
        setAdjacent(getVertex('K'), getVertice("T"));
        setW('B', 'G', 2);
        setW('G', 'L', 3);
        setW('L', 'R', 2);
        setAdjacent(getVertex('R'), getVertice("Z"));
        setW('Z', 'R', 2);
        setW('Y', 'Z', 1);
    }

    @Override
    public void showAllLength(String txtName) throws FileNotFoundException {
        File file = new File(txtName);
        PrintWriter output = new PrintWriter(file);
        double[][] d = FloydWarshall().distance;
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d.length; j++) {
                if (i == j) {
                    d[i][j] = 0;
                }
                System.out.println("The shortest path between " + (char)('A'+i) + " and " + (char)('A'+j) + " is " + d[i][j] + "mins");
                output.println("The shortest path between " + (char)('A'+i) + " and " + (char)('A'+j) + " is " + d[i][j] + "mins");
            }
        }
        output.close();
    }

    public static void main(String[] args) {
        APforBus g = new APforBus();
        try {
            g.showAllLength("ap_bus");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
