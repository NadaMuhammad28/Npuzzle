package Npuzzle;
import java.util.Scanner;

public class puzzle {
    static Scanner scanner = new Scanner(System.in);
     static int[] initialstate = {0, 1, 3, 4, 2, 5, 7, 8, 6};
     //static int[] initialstate = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0, 15}; //shows result
    //static int[] initialstate = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 11, 13, 14, 15, 12}; //slow
    //  static int[] initialstate = {1, 8, 2, 0, 4, 3, 7, 6, 5};
    //  static int[] initialstate = {0, 1, 3, 4, 2, 5, 7, 8, 6};
   // static int[] initialstate = {8, 1, 2, 0, 4, 3, 7, 6, 5};  //this puzzle is not solvable
    public static void main(String[] args) {

        boolean cont = true;


        while (cont) {
            System.out.println("choose an Algorithm:");
            System.out.println("1. BFS");
            System.out.println("2. Greedy Search");
            System.out.println("3. Astar");

            int input = scanner.nextInt();
            System.out.println();

            switch (input) {

                case 1: {
                    if (issolvable(initialstate)) {
                        System.out.println("BFS solution:");
                        BFS.search(initialstate);
                        break;
                    } else {
                        System.out.println("The puzzle is not solvable");
                        break;
                    }
                }
                case 2: {
                    if (issolvable(initialstate)) {
                        System.out.println("Greedy Search solution:");
                        GreedySearch.search(initialstate) ;
                        break;
                    } else {
                        System.out.println("The puzzle is not solvable");
                        break;
                    }

                }

                case 3: {
                    if (issolvable(initialstate)) {
                        System.out.println("A* solution:");
                        Astar.search(initialstate);
                        break;
                    } else {
                        System.out.println("The puzzle is not solvable");
                        break;
                    }


                }

            }


            System.out.println("Do you want to continue?");
            System.out.println();
            System.out.println("1. Yes");
            System.out.println("2. No");
            int ans = scanner.nextInt();
            if (ans == 2)
                cont = false;
        }
    }
     static int invcount (int[] state)
      {   int inv_count = 0 ;
          int n = 3;
        for (int i =0; i< n*n ;i++)
        for (int j = i +1; j<n*n; j++)
            if  (state [i] >0 && state[j] > state [i] && state [j]>0)
               inv_count ++;
            return inv_count ;
    }
     public static boolean issolvable (int [] state)
     {
         int invCount = invcount(state);

         return (invCount % 2 == 0);
     }

}