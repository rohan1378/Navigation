package com.example.navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class MainActivity extends AppCompatActivity {
    Database mDatabaseHelper;
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
        mDatabaseHelper = new Database(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int source = Integer.parseInt(src.getText().toString());
                int dest = Integer.parseInt(des.getText().toString());

                NodeWeighted s = null;
                NodeWeighted d = null;


                HashMap<Integer, NodeWeighted> map = new HashMap<>();


                GraphWeighted graphWeighted = new GraphWeighted(true);
                NodeWeighted one = new NodeWeighted(1, "1");
                NodeWeighted two = new NodeWeighted(2, "2");
                NodeWeighted three = new NodeWeighted(3, "3");
                NodeWeighted four = new NodeWeighted(4, "4");
                NodeWeighted five = new NodeWeighted(5, "5");
                NodeWeighted six = new NodeWeighted(6, "6");
                NodeWeighted seven = new NodeWeighted(7, "7");
                NodeWeighted eight = new NodeWeighted(8, "8");
                NodeWeighted nine = new NodeWeighted(9, "9");
                NodeWeighted ten = new NodeWeighted(10, "10");

                NodeWeighted eleven = new NodeWeighted(11, "11");
                NodeWeighted twelve = new NodeWeighted(12, "12");
                NodeWeighted thirteen = new NodeWeighted(13, "13");
                NodeWeighted fourteen = new NodeWeighted(14, "14");
                NodeWeighted fifteen = new NodeWeighted(15, "15");
                NodeWeighted sixteen = new NodeWeighted(16, "16");
                NodeWeighted seventeen = new NodeWeighted(17, "17");
                NodeWeighted eighteen = new NodeWeighted(18, "18");
                NodeWeighted nineteen = new NodeWeighted(19, "19");
                NodeWeighted twenty = new NodeWeighted(20, "20");

                // Our addEdge method automatically adds Nodes as well.
                // The addNode method is only there for unconnected Nodes,
                // if we wish to add any



                graphWeighted.addEdge(one, two, 2,"S");
                graphWeighted.addEdge(two, three, 2,"L");
                graphWeighted.addEdge(three, four, 2,"S");
                graphWeighted.addEdge(four, five, 2,"S");
                graphWeighted.addEdge(five, six, 3,"R");
                graphWeighted.addEdge(five, seven, 2,"S");
                graphWeighted.addEdge(seven, eight, 2,"R");
                graphWeighted.addEdge(seven, nine, 2,"S");
                graphWeighted.addEdge(nine, ten, 2,"R");
                graphWeighted.addEdge(nine, eleven, 2,"L");

                graphWeighted.addEdge(twelve, thirteen, 2,"S");
                graphWeighted.addEdge(thirteen, two, 2,"S");
                graphWeighted.addEdge(thirteen, fourteen, 2,"L");
                graphWeighted.addEdge(fourteen, six, 2,"R");
                graphWeighted.addEdge(fourteen, thirteen, 2,"S");
                graphWeighted.addEdge(fifteen, eight, 2,"R");
                graphWeighted.addEdge(fifteen, sixteen, 2,"S");
                graphWeighted.addEdge(sixteen, ten, 2,"R");
                graphWeighted.addEdge(sixteen, twenty, 2,"L");
                graphWeighted.addEdge(sixteen, seventeen, 2,"S");
                graphWeighted.addEdge(seventeen, eighteen, 2,"S");
                graphWeighted.addEdge(eighteen, nineteen, 2,"L");


                mDatabaseHelper.addData("1", "2", 2.0,"S");
                mDatabaseHelper.addData("2", "3", 2.0,"L");
                mDatabaseHelper.addData("3", "4", 2.0,"S");
                mDatabaseHelper.addData("4", "5", 2.0,"S");
                mDatabaseHelper.addData("5", "6", 3.0,"R");
                mDatabaseHelper.addData("5", "7", 2.0,"S");
                mDatabaseHelper.addData("7", "8", 2.0,"R");
                mDatabaseHelper.addData("7", "9", 2.0,"S");
                mDatabaseHelper.addData("9", "10", 2.0,"R");
                mDatabaseHelper.addData("9", "11", 2.0,"L");

                mDatabaseHelper.addData("12", "13", 2.0,"S");
                mDatabaseHelper.addData("13", "2", 2.0,"S");
                mDatabaseHelper.addData("13", "14", 2.0,"L");
                mDatabaseHelper.addData("14", "6", 2.0,"R");
                mDatabaseHelper.addData("15", "13", 2.0,"S");
                mDatabaseHelper.addData("15", "8", 2.0,"R");
                mDatabaseHelper.addData("15", "16", 2.0,"S");
                mDatabaseHelper.addData("16", "10", 2.0,"R");
                mDatabaseHelper.addData("16", "20", 2.0,"L");
                mDatabaseHelper.addData("16", "17", 2.0,"S");
                mDatabaseHelper.addData("17", "18", 2.0,"S");
                mDatabaseHelper.addData("18", "19", 2.0,"L");




                map.put(1, one);
                map.put(2, two);
                map.put(3, three);
                map.put(4, four);
                map.put(5, five);
                map.put(6, six);
                map.put(7, seven);
                map.put(8, eight);
                map.put(9, nine);
                map.put(10, ten);
                map.put(11, eleven);
                map.put(12, twelve);
                map.put(13, thirteen);
                map.put(14, fourteen);
                map.put(15, fifteen);
                map.put(16, sixteen);
                map.put(17, seventeen);
                map.put(18, eighteen);
                map.put(19, nineteen);
                map.put(20, twenty);


                s = map.get(source);
                d = map.get(dest);


                graphWeighted.DijkstraShortestPath(s, d, getApplicationContext());
            }

        });
    }
}
