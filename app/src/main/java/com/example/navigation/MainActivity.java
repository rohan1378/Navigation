package com.example.navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Iterator;
import java.util.LinkedList;


public class MainActivity extends AppCompatActivity {

    Button submit;
    EditText src;
    EditText des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// Java program to print BFS traversal from a given source vertex.
        submit = findViewById(R.id.getData);

        src= (EditText) findViewById(R.id.src);
        des = (EditText) findViewById(R.id.des);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int source = Integer.parseInt(src.getText().toString());
                int dest = Integer.parseInt(des.getText().toString());


                NodeWeighted s = new NodeWeighted(source, src.getText().toString());
                NodeWeighted d = new NodeWeighted(dest, des.getText().toString());


                GraphWeighted graphWeighted = new GraphWeighted(true);
                NodeWeighted zero = new NodeWeighted(0, "0");
                NodeWeighted one = new NodeWeighted(1, "1");
                NodeWeighted two = new NodeWeighted(2, "2");
                NodeWeighted three = new NodeWeighted(3, "3");
                NodeWeighted four = new NodeWeighted(4, "4");
                NodeWeighted five = new NodeWeighted(5, "5");
                NodeWeighted six = new NodeWeighted(6, "6");
                // Our addEdge method automatically adds Nodes as well.
                // The addNode method is only there for unconnected Nodes,
                // if we wish to add any
                graphWeighted.addEdge(zero, one, 9, "S");
                graphWeighted.addEdge(zero, two, 11, "S");
                graphWeighted.addEdge(one, three, 3, "S");
                graphWeighted.addEdge(one, four, 8, "S");
                graphWeighted.addEdge(one, two, 7, "S");
                graphWeighted.addEdge(two, four, 9, "S");
                graphWeighted.addEdge(three, four, 5, "S");
                graphWeighted.addEdge(three, five, 2, "S");
                graphWeighted.addEdge(four, six, 6, "S");
                graphWeighted.addEdge(five, four, 1, "S");
                graphWeighted.addEdge(five, six, 8, "S");

                graphWeighted.DijkstraShortestPath(zero, six);
            }

        });
    }
}
