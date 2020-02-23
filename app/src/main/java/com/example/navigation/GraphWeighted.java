package com.example.navigation;

import android.content.Context;
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
    private Set<NodeWeighted> nodes;
    private boolean directed;

    GraphWeighted(boolean directed) {
        this.directed = directed;
        nodes = new HashSet<>();
    }

    public void addNode(NodeWeighted... n) {
        // We're using a var arg method so we don't have to call
        // addNode repeatedly
        nodes.addAll(Arrays.asList(n));
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


    public void DijkstraShortestPath(NodeWeighted start, NodeWeighted end, Context context) {
        // We keep track of which path gives us the shortest path for each node
        // by keeping track how we arrived at a particular node, we effectively
        // keep a "pointer" to the parent node of each node, and we follow that
        // path to the start
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

        ArrayList<String> dir = new ArrayList<String>();


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
                return;
            }

            // If the closest non-visited node is our destination, we want to print the path
            if (currentNode == end) {
                System.out.println("The path with the smallest weight between "
                        + start.name + " and " + end.name + " is:");

                Toast toast1 =  Toast.makeText(context, "The path with the smallest weight between "
                        + start.name + " and " + end.name + " is:", Toast.LENGTH_LONG);
                toast1.show();

                NodeWeighted child = end;

                // It makes no sense to use StringBuilder, since
                // repeatedly adding to the beginning of the string
                // defeats the purpose of using StringBuilder
                String path = end.name;


                HashMap<Double, NodeWeighted> shortestPathMapasc = sortByValue(shortestPathMap);
                Set<Double> keys = shortestPathMapasc.keySet(); // The set of keys in the map.

                HashMap<NodeWeighted, Double> Pat = new LinkedHashMap<NodeWeighted, Double>();

                Iterator<Double> keyIter = keys.iterator();

                while (keyIter.hasNext()) {
                    Double key = keyIter.next();
                    NodeWeighted value = shortestPathMapasc.get(key);
                    Pat.put(value, key);
                }


                ArrayList<String> direction = direction(Pat);

                int i = 0;
                while (true) {
                    NodeWeighted parent = changedAt.get(child);
                    if (parent == null) {
                        break;
                    }

                    // Since our changedAt map keeps track of child -> parent relations
                    // in order to print the path we need to add the parent before the child and
                    // it's descendants
                    path = parent.name  + " " + path;
                    child = parent;
                    i++;
                }
                System.out.println(path);
                Toast toast2 =  Toast.makeText(context, path, Toast.LENGTH_LONG);
                toast2.show();


                System.out.println("The path costs: " + shortestPathMap.get(end));
                Toast toast3 =  Toast.makeText(context, "The path costs: " + shortestPathMap.get(end), Toast.LENGTH_LONG);
                toast3.show();

                return;
            }
            currentNode.visit();

            // Now we go through all the unvisited nodes our current node has an edge to
            // and check whether its shortest path value is better when going through our
            // current node than whatever we had before
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

    private ArrayList<String> direction(HashMap<NodeWeighted, Double> hm)
    {

        ArrayList<String> dir = new ArrayList<String>();
        Iterator it = hm.entrySet().iterator();

        Map.Entry pair = (Map.Entry) it.next();
        NodeWeighted nd = (NodeWeighted) pair.getKey();

        for (int i = 0; i<hm.size()-1; i++) {
            LinkedList<EdgeWeighted> ele = nd.edges;
            // Iterating through the created list from the position
            NodeWeighted no = (NodeWeighted) ((Map.Entry) it.next()).getKey();


            int r = no.n;
            for (int j = 0; j<ele.size(); j++)
            {
                NodeWeighted sr = ele.get(j).destination;
                int test = sr.n;
                if(test == r)
                    dir.add(ele.get(j).direction);
            }

            nd = no;
            //it.remove(); // avoids a ConcurrentModificationException
        }
        return dir;
    }

    public static HashMap<Double, NodeWeighted> sortByValue(HashMap<NodeWeighted, Double> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<NodeWeighted, Double> > list =
                new LinkedList<Map.Entry<NodeWeighted, Double> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<NodeWeighted, Double> >() {
            public int compare(Map.Entry<NodeWeighted, Double> o1,
                               Map.Entry<NodeWeighted, Double> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<NodeWeighted, Double> temp = new LinkedHashMap<NodeWeighted, Double>();
        for (Map.Entry<NodeWeighted, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }

        Set<NodeWeighted> keys = temp.keySet(); // The set of keys in the map.

        HashMap<Double, NodeWeighted> temp2 = new LinkedHashMap<Double, NodeWeighted>();

        Iterator<NodeWeighted> keyIter = keys.iterator();

        while (keyIter.hasNext()) {
            NodeWeighted key = keyIter.next();
            Double value = temp.get(key);
            temp2.put(value, key);
        }
        return temp2;
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
}
