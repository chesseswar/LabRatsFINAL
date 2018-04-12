import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        int numCase = in.nextInt();
        for (int i = 0; i < numCase; i++){
            int cells = in.nextInt();
            int exit = in.nextInt();
            int time = in.nextInt();
            int connections = in.nextInt();
            Graph g = new Graph(cells, exit);
            for (int j = 0; j < connections; j++){
                int first = in.nextInt();
                int second = in.nextInt();
                int weight = in.nextInt();
                g.addEdge(first, second, weight);
            }
            System.out.println(g.Dijkstra(time));
        }
    }
}

