package walking;

import GUI.InformationPanel;
import base.Vertex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mayezhou on 15/12/6.
 */
public class SingleSource extends base.Graph {//Dijkstra
    public ArrayList<Vertex> path = new ArrayList<Vertex>();

    public SingleSource() {
        super();
        setW('A', 'B', Double.MAX_VALUE);
        setW('B', 'C', Double.MAX_VALUE);
    }

    public void relax(Vertex u, Vertex v) {
        if (v.d > u.d + getW(u.name, v.name)) {
            //precision of double value
            double temp = u.d + getW(u.name, v.name);
            BigDecimal t = new BigDecimal(temp);
            v.d = t.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            v.p = u;
        }
    }

    public static Vertex extractMin(ArrayList<Vertex> q) {
        int index = 0;
        for (int i = 1; i < q.size(); i++) {
            if (q.get(index).d > q.get(i).d) {
                index = i;
            }
        }
        return q.remove(index);
    }

    public void dijkstra(Vertex s) {
        s.d = 0;
        ArrayList<Vertex> q = new ArrayList<Vertex>(26);
        for (int i = 0; i < v.length; i++) {
            q.add(v[i]);
        }
        while (!q.isEmpty()) {
            Vertex u = extractMin(q);
            for (Vertex i: u.adjacent) {//won't read the w[][] which is not valued, only u's adj
                relax(u, i);
            }
        }
    }

    public ArrayList<Vertex> printPath(Vertex s, Vertex v) {
        if (v == s) {
            s.print();
            InformationPanel.text.append("\n"+s.name);
            path.add(s);
        }
        else if (v.p == null) {
            System.out.println("no print from " + s.name + " to " + v.name);
        }
        else {
            printPath(s, v.p);
            v.print();
            InformationPanel.text.append("\n"+v.name);
            path.add(v);
        }
        return path;
    }

    public double getLength(Vertex v) {
        return v.d;
    }

    public double showCertainLength(Vertex s, Vertex v) {
        path.clear();
        dijkstra(s);
        printPath(s, v);
        return getLength(v);
    }

    public void showAllLength(Vertex s, String txtName) throws FileNotFoundException {
        File file = new File(txtName);
        PrintWriter output = new PrintWriter(file);
        dijkstra(s);
        for (Vertex i: v) {
            if (s != i) {
                if (i.d == Integer.MAX_VALUE) {//Math.abs(i.d - Integer.MAX_VALUE) < 0.0000001
                    System.out.println("no print from " + i.name + " to " + s.name);
                    InformationPanel.text.append("\n"+"no print from " + i.name + " to " + s.name);
                    output.println("no print from " + i.name + " to " + s.name);
                }else {
                    path.clear();
                    printPath(s, i);
                    System.out.print("\nThe length of the path from " + i.name + " to " + s.name + ": " + i.d
                    +"km\t");
                    InformationPanel.text.append("\n"+"The length of the path from " + i.name + " to " + s.name + ": " + i.d+"km\t");
                    for (int j = path.size()-1; j >= 0; j--) {
                        InformationPanel.text.append(path.get(j).name+"");
                    }
                    output.print("\nThe length of the path from " + i.name + " to " + s.name + ": " + i.d + "km\t");
                    for (int j = path.size()-1; j >= 0; j--) {
                        output.print(path.get(j).name);
                    }
                }
            }
        }
        output.close();
    }

    public static void main(String[] args) {
        SingleSource g = new SingleSource();
        System.out.println(g.showCertainLength(g.getVertex('A'), g.getVertex('J')));
        SingleSource g2 = new SingleSource();
        try {
            g2.showAllLength(g2.getVertex('A'), "ss_walking.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
