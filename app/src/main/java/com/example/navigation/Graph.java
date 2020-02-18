package com.example.navigation;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Graph extends AppCompatActivity {

    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency Lists
    EditText src;
    EditText des;
    // Constructor
    Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        src= (EditText) findViewById(R.id.src);
        des = (EditText) findViewById(R.id.des);

        int s = Integer.parseInt(src.getText().toString());
        int d = Integer.parseInt(src.getText().toString());

        Graph g = new Graph(4);

        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 6);
        g.addEdge(5, 7);
        g.addEdge(7, 8);
        g.addEdge(7, 9);
        g.addEdge(9, 10);
        g.addEdge(9, 11);
        g.addEdge(12, 13);
        g.addEdge(13, 2);
        g.addEdge(13, 14);
        g.addEdge(14, 6);
        g.addEdge(14, 15);
        g.addEdge(15, 8);
        g.addEdge(15, 16);
        g.addEdge(16, 10);
        g.addEdge(16, 20);
        g.addEdge(16, 17);
        g.addEdge(17, 18);
        g.addEdge(18, 19);





        System.out.println("Following is Breadth First Traversal "+
                "(starting from vertex 2)");

        g.BFS(s, d);
    }



    // Function to add an edge into the graph
    void addEdge(int v,int w)
    {
        adj[v].add(w);
    }

    // prints BFS traversal from a given source s
    void BFS(int s, int d)
    {

        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[V];

        boolean parent[] = new boolean[V];
        for (int i = 0; i<parent.length; i++)
            parent[i] = true;

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Mark the current node as visited and enqueue it
        visited[s]=true;
        queue.add(s);

        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();

            if (s == d)
                System.out.print(s+" ");

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = adj[s].listIterator();
            int count=0;
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                {
                    parent[count]=false;
                    visited[n] = true;
                    queue.add(n);
                }
                count++;
            }
        }
    }

}

