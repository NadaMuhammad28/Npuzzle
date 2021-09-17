package Npuzzle;


public class SearchNode
{

    public Node currentstate;                //currentstate
    public SearchNode parent;              //the parent node
    public double cost;                    // the cost to reach the state
    public double hCost;                   // heuristic
    public double Fn;                     // f(n) cost = g(n) + h(n)

//constructor to use in other classes
public SearchNode()
{
    this.parent =  null;
    this.cost = 0;
    this.hCost = 0;
    this.Fn = 0;
}
    public SearchNode(Node state)
    {
        this.currentstate = state;
        this.parent =  null;
        this.cost = 0;
        this.hCost = 0;
        this.Fn = 0;
    }

//BFS constructor


    public SearchNode(SearchNode prparent, Node state)
    {
        parent = prparent;
        currentstate = state;

    }
    // prev    previous parent
    // s      the state
    // c       c is g(n) cost to reach to the next node
    // h       the cost of heuristic
    //Astar constructor
    public SearchNode(SearchNode prparent, Node state, double c,double h )
    {
        parent = prparent;
        currentstate = state;
       this.cost = c;
        this.hCost = h;
        this.Fn = cost + hCost;
    }
    //Greedy constructor
    public SearchNode(SearchNode prparent, Node state,double h )
    {
        parent = prparent;
        currentstate = state;
        this.hCost = h;

    }

    //returns the current state
    public Node getCurrentstate()
    {
        return currentstate;
    }

    //returns the parent
    public SearchNode getParent()
    {
        return parent;
    }

    //returns the cost
    public double getCost()
    {
        return cost;
    }

    //returns the cost of the heuristic
    public double getHCost()
    {
        return hCost;
    }

    public double getFCost()
    {
        return (getCost()+getHCost()) ;
    }

}