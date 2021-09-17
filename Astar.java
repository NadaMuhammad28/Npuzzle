package Npuzzle;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Astar
{

    public static int search(int[] board)
    {
        int maxtime = 80000; //80 secs for each search
        SearchNode root = new SearchNode(new Node(board));
        Queue<SearchNode> queue = new LinkedList<SearchNode>();
        queue.add(root);

        int nodecount = 1; // number of repetition
        long startt = new Date().getTime();

        while (!queue.isEmpty())

        {
            SearchNode tempNode = queue.poll();

            long endt = new Date().getTime(); // end time
            long t = endt - startt; // check different
            if (t > maxtime){
                System.out.println("The solution hasn't been found  in " + maxtime/1000 + " seconds");
                System.out.println("Cost: " + tempNode.getCost());
                System.out.println("The number of visited nodes: " + nodecount);

                return nodecount;
            }

             // if the current node is not the goal
            if (!tempNode.getCurrentstate().isgoal())
            {
                ArrayList<Node> expandtemp = tempNode.getCurrentstate().expandnode();
                ArrayList<SearchNode> nodeSuccessors = new ArrayList<SearchNode>();


                for (int i = 0; i < expandtemp.size(); i++)
                {

                    SearchNode checkedNode = new SearchNode(tempNode, (Node) expandtemp.get(i), tempNode.getCost() + expandtemp.get(i).getcost(), ((Node) expandtemp.get(i)).getManhattandistance());

                    // check if it has been visited before
                    if (!isvisited(checkedNode))
                    {
                        nodeSuccessors.add(checkedNode);    //if not, add
                    }
                }


                if (nodeSuccessors.size() == 0)
                    continue;

                SearchNode lowestnode = nodeSuccessors.get(0);

                for (int i = 0; i < nodeSuccessors.size(); i++)
                {
                    if (lowestnode.getFCost() > nodeSuccessors.get(i).getFCost())
                    {
                        lowestnode = nodeSuccessors.get(i);
                    }
                }

                int lowestValue = (int) lowestnode.getFCost();

                for (int i = 0; i < nodeSuccessors.size(); i++)
                {
                    if (nodeSuccessors.get(i).getFCost() == lowestValue)
                    {
                        queue.add(nodeSuccessors.get(i));
                    }
                }

                nodecount++;
            }
            else
            {
                Stack<SearchNode> path = new Stack<SearchNode>();
                path.push(tempNode);
                tempNode = tempNode.getParent();

                while (tempNode.getParent() != null)
                {
                    path.push(tempNode);
                    tempNode = tempNode.getParent();
                }
                path.push(tempNode);

                int loopSize = path.size();

                for (int i = 0; i < loopSize; i++)
                {
                    tempNode = path.pop();
                    tempNode.getCurrentstate().printState();
                    System.out.println();
                    System.out.println();
                }
                System.out.println("The cost was: " + tempNode.getCost());

                System.out.println("The number of visited nodes " + nodecount);

                return nodecount;
            }
        }
        return nodecount;
    }


    private static boolean isvisited (SearchNode node)
    {
        boolean visited = false;
        SearchNode checknode = node;
        while (node.getParent() != null && !visited)
        {
            if (node.getParent().getCurrentstate().equals(checknode.getCurrentstate()))
            {
                visited = true;
            }
            node = node.getParent();
        }


        return visited;
    }

}