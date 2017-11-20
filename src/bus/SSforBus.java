package bus;

import GUI.InformationPanel;
import base.Vertex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by mayezhou on 15/12/8.
 */
public class SSforBus extends walking.SingleSource{
    public SSforBus() {
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
    public void showAllLength(Vertex s, String txtName) throws FileNotFoundException {
        File file = new File(txtName);
        PrintWriter output = new PrintWriter(file);
        dijkstra(s);
        for (Vertex i: v) {
            if (s != i) {
                if (i.d == Integer.MAX_VALUE) {//Math.abs(i.d - Integer.MAX_VALUE) < 0.0000001
                    System.out.println("no print from " + i.name + " to " + s.name);
                    InformationPanel.text.append("\n"+"no print from " + i.name + " to " + s.name);
                    output.println("\nno print from " + i.name + " to " + s.name);
                }else {
                    path.clear();
                    printPath(s, i);
                    System.out.print("\nThe length of the path from " + i.name + " to " + s.name + ": " + i.d
                            +"mins\t");
                    InformationPanel.text.append("\n"+"The length of the path from " + i.name + " to " + s.name + ": " + i.d+"mins\t");
                    for (int j = path.size()-1; j >= 0; j--) {
                        InformationPanel.text.append(path.get(j).name+"");
                    }
                    output.print("\nThe length of the path from " + i.name + " to " + s.name + ": " + i.d + "mins\t");
                    for (int j = path.size()-1; j >= 0; j--) {
                        output.print(path.get(j).name);
                    }
                }
            }
        }
        output.close();
    }

    public static void main(String[] args) {
        SSforBus g = new SSforBus();
        System.out.println(g.showCertainLength(g.getVertex('A'), g.getVertex('J')));
        SSforBus g2 = new SSforBus();
        try {
            g2.showAllLength(g2.getVertex('A'), "ss_bus");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
