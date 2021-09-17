package Npuzzle;
import java.util.ArrayList;
import java.util.Arrays;

public class Node  {

    private final int puzzlesize = 9;
   // private final int puzzlesize = 16;
    public static String path = "";
    private int manhattandistance = 0;// Manhattan distance
    private final int[] goal = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};  //final goal
     //private final int[] goal = new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0 };  //final goal
    private int[] currentstate;
    SearchNode node = new SearchNode();

    //constructor
    public Node(int[] p) {
        this.currentstate = p;
    }

    //get the zero location
    private int getzeroloc() {
        int zeroindex = -1;

        for (int i = 0; i < puzzlesize; i++) {
            if (currentstate[i] == 0)
                zeroindex = i;
        }
        return zeroindex;
    }


    //Returns an ArrayList with all the children of the current state
  public ArrayList<Node> expandnode() {
        ArrayList<Node> successors = new ArrayList<Node>();
        int zeroloc = getzeroloc();

        // left
        if (zeroloc != 0 && zeroloc != 3 && zeroloc != 6) {
            swap(zeroloc - 1, zeroloc, successors);
        }

        // down
        if (zeroloc != 6 && zeroloc != 7 && zeroloc != 8) {

            swap(zeroloc + 3, zeroloc, successors);
        }

        //up
        if (zeroloc != 0 && zeroloc != 1 && zeroloc != 2) {

            swap(zeroloc - 3, zeroloc, successors);
        }
        //right
        if (zeroloc != 2 && zeroloc != 5 && zeroloc != 8) {
            swap(zeroloc + 1, zeroloc, successors);
        }
        return successors;  //return children
    }
// discomment this and comment the previous function for 15 puzzle

    /* public ArrayList<State> expandnode() {
        ArrayList<State> successors = new ArrayList<State>();
        int zeroloc = getzeroloc();

        // left
        if (zeroloc != 0 && zeroloc != 4 && zeroloc != 8 && zeroloc != 12 ) {

            swap(zeroloc - 1, zeroloc, successors);
        }

        // down
        if (zeroloc != 12 && zeroloc != 13 && zeroloc != 14 && zeroloc != 15 ) {

            swap(zeroloc + 3, zeroloc, successors);
        }

        // up
        if (zeroloc != 0 && zeroloc != 1 && zeroloc != 2 && zeroloc != 3) {

            swap(zeroloc - 3, zeroloc, successors);
        }
        // right
        if (zeroloc != 3 && zeroloc != 7 && zeroloc != 11  && zeroloc != 15) {

            swap(zeroloc + 1, zeroloc, successors);
        }

        return successors;  //return children
    }
*/
    //changes the data of positions pos1 and pos2 in a copy of the current node.

    private void swap(int pos1, int pos2, ArrayList<Node> list) {
        int[] p = coppyarray(currentstate);  //copy the current array into p
        int temp = p[pos1];
        p[pos1] = currentstate[pos2];
        p[pos2] = temp;
        list.add((new Node(p)));
    }


    //return the cost

    public int getcost() {
        return 1;
    }


    // the following method calculates the cost of the Manhattan heuristic
    private double setManDist() {
        int index = -1;
        for (int i = 0; i < currentstate.length; i++) {
            index++;

            int val = (currentstate[index] - 1);

            if (val != -1) {

                manhattandistance += Math.abs(currentstate[i] - goal[i]);
                node.hCost = node.hCost + (manhattandistance/3) +(manhattandistance%3) ;

            }
        }
        return node.hCost ;
    }

    public int getManhattandistance() {
        return (int) setManDist();

    }

    private int[] coppyarray(int[] puzzle) {
        int[] p = new int[puzzlesize];
        for (int i = 0; i < puzzlesize; i++) {
            p[i] = puzzle[i];
        }
        return p;
    }


    public boolean isgoal() {
        if (Arrays.equals(currentstate, goal)) {
            return true;
        }
        return false;
    }


    public void printState() {
        System.out.println(currentstate[0] + "  " + currentstate[1] + "  " + currentstate[2]);
        System.out.println();
        System.out.println(currentstate[3] + "  " + currentstate[4] + "  " + currentstate[5]);
        System.out.println();
        System.out.println(currentstate[6] + "  " + currentstate[7] + "  " + currentstate[8]);

    }

// discomment this and comment the previous function for 15 puzzle
   /*   public void printState()
    {
        System.out.println(currentstate[0] + "  " +currentstate[1] +"   " +currentstate[2] +"    " +currentstate[3]);
        System.out.println();
        System.out.println(currentstate[4] + "  "+currentstate[5] +"   "+currentstate[6]  + "    "+currentstate[7]);
        System.out.println();
        System.out.println(currentstate[8] + "  " +currentstate[9] + "  " +currentstate[10]  + "  "+currentstate[11]);
        System.out.println();
        System.out.println(currentstate[12] + "  " +currentstate[13] + "  "+currentstate[14]  +"  " +currentstate[15]);


    }*/

    public boolean equals(Node s) {
        if (Arrays.equals(currentstate, ((Node) s).getCurrentstate())) {
            return true;
        } else
            return false;

    }

    public int[] getCurrentstate() {
        return currentstate;
    }


}

