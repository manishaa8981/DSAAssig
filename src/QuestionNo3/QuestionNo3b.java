/*
Question 3 (b)
Implement Kruskal algorithm and priority queue using minimum heap
*/
package QuestionNo3;
import java.util.Arrays;
public class QuestionNo3b {
    public static class Edge implements Comparable<Edge> {
        int source;
        int dest;
        int weight;

        Edge(int source, int dest, int w) {
            this.source = source;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    int v;
    Edge edge[];
    int mst[][];  // Add this line to declare mst array
    QuestionNo3b(int v) {
        this.v = v;
        edge = new Edge[v * (v - 1) / 2];  // Initialize edge array
        mst = new int[v][v];  // Initialize MST array
    }
    int edgecnt = -1;

    void addEdge(int s, int d, int w) {
        if (edgecnt + 1 < v) {  // Check if enough space in the array
            edge[++edgecnt] = new Edge(s, d, w);
        } else {
            System.out.println("Checking to Add Edge. Cannot add more edges. Array is full.");
        }
    }

    void KruskalExample() {
        Arrays.sort(edge, 0, edgecnt + 1);
        int edgeTaken = 1;
        int edgeCounter = -1;
        int size[] = new int[v];
        int parent[] = new int[v];
        for (int i = 0; i < v; i++) {
            parent[i] = -1;
        }

        while (edgeTaken <= v - 1) {
            Edge e = edge[++edgeCounter];
            int uabsroot = find(e.source, parent);
            int vabsroot = find(e.dest, parent);
            if (uabsroot == vabsroot) {
                continue;
            }
            mst[e.source][e.dest] = e.weight;
            mst[e.dest][e.source] = e.weight;
            edgeTaken++;
            union(uabsroot, vabsroot, size, parent);
        }
    }

    int find(int x, int[] parent) {
        if (parent[x] == -1) {
            return x;
        }
        return parent[x] = find(parent[x], parent);
    }

    void union(int uabs, int vabs, int[] size, int[] parent) {
        if (size[uabs] > size[vabs]) {
            parent[vabs] = uabs;
        } else if (size[uabs] < size[vabs]) {
            parent[uabs] = vabs;
        } else {
            parent[vabs] = uabs;
            size[uabs]++;
        }
    }

    public static void main(String[] args) {
        QuestionNo3b kruskal = new QuestionNo3b(4);

        // Adding edges to the graph
        kruskal.addEdge(0, 1, 10);
        kruskal.addEdge(0, 2, 6);
        kruskal.addEdge(0, 3, 5);
        kruskal.addEdge(1, 3, 15);

        //Check Full
        kruskal.addEdge(2, 3, 4);
        kruskal.KruskalExample();

        System.out.println("Minimum Spanning Tree using Kruskal's Algorithm:");
        for (int i = 0; i < kruskal.v; i++) {
            for (int j = 0; j < kruskal.v; j++) {
                System.out.print(kruskal.mst[i][j] + "\t");
            }
            System.out.println();
        }
    }

}
