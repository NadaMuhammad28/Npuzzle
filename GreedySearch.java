package Npuzzle;

import java.util.*;

public class GreedySearch {

        public static int search(int[] board) {
            int maxtime = 80000;   //80 secs time limit
            SearchNode root = new SearchNode(new Node(board));
            Queue<SearchNode> queue = new LinkedList<SearchNode>();
            queue.add(root);

            int nodecount = 1;
            long lStartTime = new Date().getTime();
            while (!queue.isEmpty()) {
                SearchNode tempNode = queue.poll();

                long lEndTime = new Date().getTime(); // end time
                long t = lEndTime - lStartTime;
                if (t > maxtime) {
                    System.out.println("The solution hasn't been found  in " + maxtime / 1000 + " seconds");
                    System.out.println("The number of visited nodes: " + nodecount);

                    return nodecount;
                }

                // if the current node is not the goal
                if (!tempNode.getCurrentstate().isgoal()) {
                    ArrayList<Node> expandtemp = tempNode.getCurrentstate().expandnode();
                    ArrayList<SearchNode> Successors = new ArrayList<SearchNode>();

                    for (int i = 0; i < expandtemp.size(); i++) {


                        SearchNode sunode = new SearchNode(tempNode, (Node) expandtemp.get(i), ((Node) expandtemp.get(i)).getManhattandistance());
                        // check if it has been visited before
                        if (!isvisited(sunode)) {
                            Successors.add(sunode);    //if not, add
                        }
                    }


                    if (Successors.size() == 0)
                        continue;

                    SearchNode lowestnode = Successors.get(0);

                    for (int i = 0; i < Successors.size(); i++) {
                        if (lowestnode.getHCost()> Successors.get(i).getHCost()) {
                            lowestnode = Successors.get(i);
                        }
                    }

                    int lowestH = (int) lowestnode.getHCost();

                    for (int i = 0; i < Successors.size(); i++) {
                        if (Successors.get(i).getHCost() == lowestH) {
                            queue.add(Successors.get(i));
                        }
                    }

                    nodecount++;
                } else
                {
                    Stack<SearchNode> path = new Stack<SearchNode>();
                    path.push(tempNode);
                    tempNode = tempNode.getParent();

                    while (tempNode.getParent() != null) {
                        path.push(tempNode);
                        tempNode = tempNode.getParent();
                    }
                    path.push(tempNode);

                    int pathsize = path.size();

                    for (int i = 0; i < pathsize; i++) {
                        tempNode = path.pop();
                        tempNode.getCurrentstate().printState();
                        System.out.println();
                        System.out.println();
                    }

                    System.out.println("The number of visited nodes: " + nodecount);

                    return nodecount;
                }
            }
            return nodecount;
        }


        private static boolean isvisited(SearchNode node) {
            boolean visited = false;
            SearchNode checknode = node;
            while (node.getParent() != null && !visited) {
                if (node.getParent().getCurrentstate().equals(checknode.getCurrentstate())) {
                    visited = true;
                }
                node = node.getParent();
            }


            return visited;
        }


    }
