package com.example.navigation;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class GraphWeighted {
    Database mDatabaseHelper;
    private Set<NodeWeighted> nodes;
    private boolean directed;

    GraphWeighted(boolean directed) {
        this.directed = directed;
        nodes = new HashSet<>();
    }

    public void addEdge(NodeWeighted source, NodeWeighted destination, double weight, String direction) {
        // Since we're using a Set, it will only add the nodes
        // if they don't already exist in our graph
        nodes.add(source);
        nodes.add(destination);

        // We're using addEdgeHelper to make sure we don't have duplicate edges
        addEdgeHelper(source, destination, weight, direction);

        if (!directed && source != destination) {
            addEdgeHelper(destination, source, weight, direction);
        }
    }

    private void addEdgeHelper(NodeWeighted a, NodeWeighted b, double weight, String direction) {
        // Go through all the edges and see whether that edge has
        // already been added
        for (EdgeWeighted edge : a.edges) {
            if (edge.source == a && edge.destination == b && edge.direction == direction) {
                // Update the value in case it's a different one now
                edge.weight = weight;
                edge.direction = direction;
                return;
            }
        }
        // If it hasn't been added already (we haven't returned
        // from the for loop), add the edge
        a.edges.add(new EdgeWeighted(a, b, weight, direction));
    }


    public String DijkstraShortestPath(NodeWeighted start, NodeWeighted end, Context context) {
        // We keep track of which path gives us the shortest path for each node
        // by keeping track how we arrived at a particular node, we effectively
        // keep a "pointer" to the parent node of each node, and we follow that
        // path to the start
        String path = null;
        String newpath = null;
        mDatabaseHelper = new Database(context);

        HashMap<NodeWeighted, NodeWeighted> changedAt = new HashMap<>();
        changedAt.put(start, null);

        // Keeps track of the shortest path we've found so far for every node
        HashMap<NodeWeighted, Double> shortestPathMap = new HashMap<>();

        // Setting every node's shortest path weight to positive infinity to start
        // except the starting node, whose shortest path weight is 0
        for (NodeWeighted node : nodes) {
            if (node == start)
                shortestPathMap.put(start, 0.0);
            else shortestPathMap.put(node, Double.POSITIVE_INFINITY);
        }


        // Now we go through all the nodes we can go to from the starting node
        // (this keeps the loop a bit simpler)
        for (EdgeWeighted edge : start.edges) {
            shortestPathMap.put(edge.destination, edge.weight);
            changedAt.put(edge.destination, start);
        }

        start.visit();

        // This loop runs as long as there is an unvisited node that we can
        // reach from any of the nodes we could till then
        while (true) {
            NodeWeighted currentNode = closestReachableUnvisited(shortestPathMap);
            // If we haven't reached the end node yet, and there isn't another
            // reachable node the path between start and end doesn't exist
            // (they aren't connected)
            if (currentNode == null) {
                System.out.println("There isn't a path between " + start.name + " and " + end.name);
                Toast toast0 =  Toast.makeText(context, "There isn't a path between " + start.name + " and " + end.name, Toast.LENGTH_LONG);
               toast0.show();
            }


            // If the closest non-visited node is our destination, we want to print the path
            if (currentNode == end) {
                System.out.println("The path with the smallest weight between "
                        + start.name + " and " + end.name + " is:");

                Toast toast1 = Toast.makeText(context, "The path with the smallest weight between "
                        + start.name + " and " + end.name + " is:", Toast.LENGTH_LONG);
                toast1.show();

                NodeWeighted child = end;

                // It makes no sense to use StringBuilder, since
                // repeatedly adding to the beginning of the string
                // defeats the purpose of using StringBuilder
                path = end.name;
                while (true) {
                    NodeWeighted parent = changedAt.get(child);
                    if (parent == null) {
                        break;
                    }
                    String Des = null;
                    int check = 0;

                    if (check == 0) {
                        Des = currentNode.name.toString();
                        check++;
                    }

                    if (check > 0) {
                        Des = child.name;
                    }


                    String dw = null;

                    Cursor k = mDatabaseHelper.getItemDirection(parent.name, Des);
                    if (k.moveToFirst()) // data?
                    {
                        dw = k.getString(k.getColumnIndex("Direction"));
                    }
                    k.close();
                    path = parent.name + " " + dw + " " + path;
                    child = parent;

                }
                HashMap h =  MainActivity.getmap();
                String[] splited = path.split("\\s+");
                System.out.println("Path w/o spaces and num " + splited+ "\n");
                int ite = Character.getNumericValue(path.charAt(0));
                newpath = h.get(ite) + "\n";
                for (int k = 1; k < splited.length; k++)
                {
                    if(splited[k].length()==1) {
                        char p = splited[k].charAt(0);
                        if (Character.isAlphabetic(p)) {
                            if (p == 'S')
                                newpath = newpath + "Go Straight -";
                            else if (p == 'L')
                                newpath = newpath + "Take a Left -";
                            else
                                newpath = newpath + "Take a Right -";
                        }
                        else if (Character.isDigit(p)) {
                            int in = Character.getNumericValue(p);
                            newpath = newpath + (h.get(in)) + "\n";
                        }
                    }
                    else{
                        int in = Integer.parseInt(splited[k]);
                        newpath = newpath + (h.get(in)) + "\n";
                    }
                }
                //System.out.println("New Path "+ newpath);
                Toast toast2 =  Toast.makeText(context, newpath , Toast.LENGTH_LONG);
                toast2.show();



                System.out.println("The path costs: " + shortestPathMap.get(end));
                Toast toast3 =  Toast.makeText(context, "The path costs: " + shortestPathMap.get(end), Toast.LENGTH_LONG);
                toast3.show();
                return path;
            }

            currentNode.visit();
            for (EdgeWeighted edge : currentNode.edges) {
                if (edge.destination.isVisited())
                    continue;

                if (shortestPathMap.get(currentNode)
                        + edge.weight
                        < shortestPathMap.get(edge.destination)) {
                    shortestPathMap.put(edge.destination,
                            shortestPathMap.get(currentNode) + edge.weight);
                    changedAt.put(edge.destination, currentNode);
                }
            }
        }

    }

    private NodeWeighted closestReachableUnvisited(HashMap<NodeWeighted, Double> shortestPathMap) {

        double shortestDistance = Double.POSITIVE_INFINITY;
        NodeWeighted closestReachableNode = null;
        for (NodeWeighted node : nodes) {
            if (node.isVisited())
                continue;

            double currentDistance = shortestPathMap.get(node);
            if (currentDistance == Double.POSITIVE_INFINITY)
                continue;

            if (currentDistance < shortestDistance) {
                shortestDistance = currentDistance;
                closestReachableNode = node;
            }
        }
        return closestReachableNode;
    }




//UN-USED METHODS ---- UN-USED METHODS ---- //

    public void addNode(NodeWeighted... n) {
        // We're using a var arg method so we don't have to call
        // addNode repeatedly
        nodes.addAll(Arrays.asList(n));
    }

    public void printEdges() {
        for (NodeWeighted node : nodes) {
            LinkedList<EdgeWeighted> edges = node.edges;

            if (edges.isEmpty()) {
                System.out.println("Node " + node.name + " has no edges.");
                continue;
            }
            System.out.print("Node " + node.name + " has edges to: ");

            for (EdgeWeighted edge : edges) {
                System.out.print(edge.destination.name + "(" + edge.weight + ") ");
            }
            System.out.println();
        }
    }

    public boolean hasEdge(NodeWeighted source, NodeWeighted destination) {
        LinkedList<EdgeWeighted> edges = source.edges;
        for (EdgeWeighted edge : edges) {
            // Again relying on the fact that all classes share the
            // exact same NodeWeighted object
            if (edge.destination == destination) {
                return true;
            }
        }
        return false;
    }

    public void resetNodesVisited() {
        for (NodeWeighted node : nodes) {
            node.unvisit();
        }
    }
//UN-USED METHODS ---- UN-USED METHODS ---- //

}
