package Npuzzle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Date;

public class BFS
{
    public static int search(int[] board)
    {
        int number_of_nodes;
        SearchNode root = new SearchNode(new Node(board));
        Queue<SearchNode> queue = new LinkedList<SearchNode>();
        queue.add(root);

        number_of_nodes = BFSsearch(queue);
        return number_of_nodes;
    }


    public static int BFSsearch   (Queue<SearchNode>queue)
    {
        int nodecount = 1;
        int maxtime = 100000;     //100 secs
        long starttime = new Date().getTime();


        while (!queue.isEmpty())      //as long as the queue is not empty
        {

            SearchNode tempNode = queue.poll();    //read and remove the first element of the queue
            //tempnode is the current node
            long endtime = new Date().getTime(); // end time
            long t = endtime - starttime;

            if (t > maxtime){
                System.out.println("The solution hasn't been found  in " + maxtime/1000 + " seconds" );

            }

            if (!tempNode.getCurrentstate().isgoal()) // if tempnode is not the goal
            {
                ArrayList<Node> expandtemp = tempNode.getCurrentstate().expandnode(); // expand tempnode


                for (int i = 0; i < expandtemp.size(); i++)
                {

                SearchNode newnode = new SearchNode(tempNode, (Node) expandtemp.get(i));
                if (!isvisited(newnode))
                {
                        queue.add(newnode);
                }
                }
                nodecount++;
            }
            else
            // print path from the initial node to the goal state
            {
                Stack<SearchNode> path = new Stack <SearchNode>();
                path.push(tempNode);
                tempNode = tempNode.getParent();

                while (tempNode.getParent() != null)
                {
                    path.push(tempNode);
                    tempNode = tempNode.getParent();
                }
                path.push(tempNode);

                int pathsize = path.size();

                for (int i = 0; i < pathsize; i++)

                {
                    tempNode = path.pop();
                    tempNode.getCurrentstate().printState();
                    System.out.println();
                    System.out.println();
                }

                return nodecount;
            }
        }
        return nodecount;
    }

    //method to check if this node is visited or not
    private static boolean isvisited (SearchNode node)
    {
        boolean visited = false;
        SearchNode temp = node;
        while (node.getParent() != null && !visited)
        {
            if (node.getParent().getCurrentstate().equals(temp.getCurrentstate()))
            {
                visited = true;
            }
            node = node.getParent();
        }

        return visited;
    }
}