import java.util.*;

public class Graph {
    HashMap<Integer,Node> keys;
    LinkedList<Integer>[] vertices;
    LinkedList<Integer>[] weights;
    HashSet<Integer> visited;
    int size, edges, exit;

    public Graph(int numCells, int e){
        size = numCells;
        edges = 0;
        exit = e;
        keys = new HashMap<>();
        visited = new HashSet<>();
        vertices = new LinkedList[numCells];
        weights = new LinkedList[numCells];
        for (int i = 0; i < vertices.length; i++){
            vertices[i] = new LinkedList<>();
            weights[i] = new LinkedList<>();
        }
    }

    public void addEdge(int first, int second, int weight){
        first--;
        second--;
        if (keys.get(first) == null){
            keys.put(first, new Node(first));
        }

        if (keys.get(second) == null){
            keys.put(second, new Node(second));
        }

        vertices[first].add(second);
        weights[first].add(weight);
    }

    public int Dijkstra(int time){
        int output = 0;
        keys.get(exit).cost.value = 0;
        keys.get(exit).cost.isInf = false;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (int i = 0; i < size; i++){
            queue.add(keys.get(i));
        }

        while ((visited.size() < size)){
            int current = queue.poll().value;
            visited.add(current);
            for (int connection : vertices[current]){
                if (!visited.contains(connection)){
                    InfInt sum = new InfInt(keys.get(current).cost.value + keys.get(connection).cost.value);
                    if (keys.get(connection).cost.isInf || sum.value < keys.get(connection).cost.value){
                        keys.get(connection).cost = new InfInt(sum.value);
                        keys.get(connection).next = keys.get(current);
                    }
                }
            }
        }

        for (int i = 0; i < size; i++){
            if (!keys.get(i).cost.isInf && keys.get(i).cost.value < time){
                output++;
            }
        }
        return output;
    }

    public class Node implements Comparable<Node>{
        int value;
        InfInt cost;
        Node next;

        public Node(int v){
            value = v;
            cost = new InfInt(true);
            next = null;
        }

        public int compareTo(Node other){
            return this.cost.compareTo(other.cost);
        }
    }

    public class InfInt implements Comparable<InfInt>{
        int value;
        boolean isInf;

        public InfInt(boolean inf){
            value = 0;
            isInf = inf;
        }

        public InfInt(int value){
            isInf = false;
            this.value = value;
        }

        public int compareTo(InfInt other){
            if (this.isInf && other.isInf){
                return 0;
            } else if (this.isInf && !other.isInf){
                return -1;
            } else if (!this.isInf && other.isInf){
                return 1;
            } else {
                return this.value - other.value;
            }
        }

        public String toString(){
            return isInf ? Integer.toString(value) : "INFINITY";
        }
    }
}