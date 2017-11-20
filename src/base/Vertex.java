package base;

import java.util.ArrayList;

/**
 * Created by mayezhou on 15/12/6.
 */
public class Vertex {
    public ArrayList<Vertex> adjacent = new ArrayList<Vertex>();
    public Vertex p;
    public double d;
    public char name;
    public int x;
    public int y;

    public Vertex(char name) {
        d = Integer.MAX_VALUE;
        p = null;
        this.name = name;
    }

    public void print() {
        System.out.println(name);
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
