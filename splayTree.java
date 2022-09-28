// Colton Parham - CDP210001
// Dr. Zhao, CS3345
// Splay Tree project (Project 1)

// Misc notes: Needs to be bottom up 
// scanner for taking keyboard input 
import java.util.Scanner;

// Class for the nodes 
class nodes{
  nodes left, right, parentNode;
  int element;

  // setting up the constructor - defining the starting point vals..
  public nodes()
  {
    this.left = null;
    this.right = null;
    this.parentNode = null;
    this.element = 0;
  }

  // constructor for publicly viable vars/params
  public nodes(int elem, nodes l, nodes r, nodes parent)
  {
    // invoking the constructed fields/vars 
    this.element = elem;
    this.left = l;
    this.right = r;
    this.parentNode = parent;
  }
}
//END OF CONSTRUCTOR FOR NODE.....

//setting up that splay tree...
class splayTreeConfig{
  // rootPoint will be the effective root, and be set to null as needed
  private nodes rootPoint;
  // count variable set at 0 for it's starting point 
  private int ct = 0;
  // constructor for setting the value of the rootPoint
  public splayTreeConfig()
  {
    rootPoint = null;
  }
  
  // function to insert elements inside of the tree - INSERTION AS REQUIRED... 
  public void insertElement(int elem)
  {
    nodes r = rootPoint;
    nodes par = null;
    // while the rootPoint is not null 
    while (r != null)
    {
      par = r;
      // comparing the values being passed in <TENATIVE>
      if (elem > par.element)
      {
        r = r.right;
      }
      else 
      {
        r = r.left;
      }
    }

    r = new nodes();
    // root element 
    r.element = elem;
    // apply to parent node
    r.parentNode = par;

    if (par == null)
    {
      rootPoint = r;
    }
    else if (elem > par.element)
    {
      par.right = r;
    }
    else
    {
      par.left = r;
    }
    // calling splay action here <tenative until created but needs to be here> 
    splayAction(r);
    // increment the count here 
    ct++;
  }

    //child to parent function to rotate the node <right>
    // START OF FUNCTION
    public void childToParentRight(nodes child, nodes par)
    {
      // changes from prior was changing left to right 
      if ((child == null) || (par == null) || (par.right != child) || (child.parentNode != par))
      {
        // autofill screwed me <come back to this to finish reviewing notes> 
        throw new RuntimeException("shit1");
      }
  
      // cont from here - error checking first , marked as shit to keep track where each error is and is distinguishable 
      if (par.parentNode != null)
      {
        // if that parent value already is equivalent to the left value it will applied to the child <TENATIVE>
        if (par == par.parentNode.left)
        {
          // setting the parent node from the left to the child
          par.parentNode.left = child;
        }
        else 
        {
          // setting the parent node from the right to the child
          par.parentNode.right = child;
        }
      }
  
      // checking if the left child is null 
      if (child.left != null)
      {
        child.left.parentNode = par;
      }
  
      // invoking child to be a parent and vice versa..
      child.parentNode = par.parentNode;
      par.parentNode = child;
      par.right = child.right;
      child.left = par;
    }
    // END OF FUNCTION <this is here to more or less keep track where i am. >


  //child to parent function to rotate the node <left>
  public void childToParentLeft(nodes child, nodes par)
  {
    if ((child == null) || (par == null) || (par.left != child) || (child.parentNode != par))
    {
      // autofill screwed me <come back to this to finish reviewing notes> 
      throw new RuntimeException("shit2");
    }


    // cont from here - error checking first , marked as shit to keep track where each error is and is distinguishable 
    if (par.parentNode != null)
    {
      // if that parent value already is equivalent to the left value it will applied to the child <TENATIVE>
      if (par == par.parentNode.left)
      {
        // setting the parent node from the left to the child
        par.parentNode.left = child;
      }
      else 
      {
        // setting the parent node from the right to the child
        par.parentNode.right = child;
      }
    }

    // checking if the right child is null 
    if (child.right != null)
    {
      child.right.parentNode = par;
    }

    // <COME BACK TO THIS!> 
    child.parentNode = par.parentNode;
    par.parentNode = child;
    par.left = child.right;
    child.right = par;
  }

  // splay action or function
  private void splayAction(nodes v)
  {
    while (v.parentNode != null)
    {
      // new parent node to be used here 
      nodes par = v.parentNode;
      // GO GRANPA GO! 
      nodes grandpa = par.parentNode;

      // about to daisy chain all of these conditions 
      if (grandpa == null)
      {
        if (v == par.left)
        {
          // make the left child to parent 
          childToParentLeft(v, par);
        }
        else
        {
          childToParentRight(v, par);
        }
      }
      else
      {
        if (v == par.left)
        {
          if (par == grandpa.left)
          {
            childToParentLeft(par, grandpa);
            childToParentLeft(v, par);
          }
          else 
          {
            childToParentLeft(v, v.parentNode);
            childToParentRight(v, v.parentNode);
          }
        }
        else
        {
          if (par == grandpa.left)
          {
            childToParentRight(v, v.parentNode);
            childToParentLeft(v, v.parentNode);
          }
          else
          {
            childToParentRight(par, grandpa);
            childToParentRight(v, par);
          }
        }
      }
    }
    // setting the root point to the v once completed.
    rootPoint = v;
  }

  // delete function
  public void delete(int elem)
  {
    // to be created locNode to locate the node 
    nodes node = locNode(elem);
    // remove function further 
    delete(node);
  }

  // remove/delete node function
  private void delete(nodes node)
  {
    if (node == null)
    {
      return;
    }
    
    splayAction(node);
    if ((node.left != null) && (node.right != null))
    {
      nodes minimum = node.left;
      while (minimum.right != null)
      {
        minimum = minimum.right;
      }
      // setting the min right to be the node right 
      minimum.right = node.right;
      node.right.parentNode = minimum;
      node.left.parentNode = null;
      rootPoint = node.left;
    }
    else if (node.right != null)
    {
      // setting that right parent node to null and setting the rootPoint to that same value in accordinance to the left.
      node.right.parentNode = null;
      rootPoint = node.right;
    }
    else if (node.left != null)
    {
      // setting that left parent node to null and setting the rootPoint to that same value in accordinance to the left.
      node.left.parentNode = null;
      rootPoint = node.left;
    }
    else
    {
      // setting the root to null
      rootPoint = null;
    }
    node.parentNode = null;
    // setting the left node to null
    node.left = null;
    // setting the right node to null
    node.right = null;
    // setting the node to null val
    node = null;
    // decrement 
    ct--;
  }

  // function to return the # of nodes counter - used to keep track of the nodes in use....
  public int nodeQty()
  {
    // returns the number of nodes in use....
    return ct;
  }
  
  // search func return t/f
  public boolean searchFunction (int value)
  {
    return locNode(value) != null;
  }

  // function to find the node 
  private nodes locNode(int elem)
  {
    // this will be updated as needed, set to null as the place holder 
    nodes prevNodes = null; 
    nodes v = rootPoint;
    while (v != null)
    {
      prevNodes = v;
      if (elem > v.element)
      {
        // if greater than the other element it will be updated to the right 
        v = v.right;
      }
      else if (elem < v.element)
      {
        v = v.left;
      }
      else if (elem == v.element)
      {
        // if elem equals the v based element it will call the splayAction function to get passed through and solve for 
        splayAction(v);
        return v;
      }
    }
    // if the previous node is not null it will do the splayAction function on the prevNodes, so if there is that value it will be placed accordingly
    if (prevNodes != null)
    {
      splayAction(prevNodes);
      return null;
    }
    return null;
  }

  // pre-order traversal 
  public void preorderTraversal()
  {
    preorderTraversal(rootPoint);
  }
  private void preorderTraversal(nodes k)
  {
    if (k != null)
    {
      // THIS IS TENTATIVE AS THIS IS JUST SOMETHING EASY TO DISTINGUISH WHERE SOMETHING IS HAVING AN OUTPUT.
      System.out.print(k.element + " - ");
      preorderTraversal(k.left); // might be worth to create a condition here to indicate whether left or right with a if statement with the proper k value. 
      // System.out.print(k.element + "L"); - right idea, wrong execution
      preorderTraversal(k.right);
      // System.out.print(k.element + "R"); 
    }
  }
}

// originally splayTreeConfig was labeled splayTree because I made a silly mistake - corrected, and will adjust as needed.
public class splayTree
{
  public static void main(String[] args)
  {
    // taking the input 
    Scanner input = new Scanner(System.in);
    // splay tree variable to be used 
    splayTreeConfig splayTree = new splayTreeConfig();
    System.out.println("Please select an option:\n");
    // remove changed to delete function....
    // BEFORE GOING ANY FURTHER NEED TO UPDATE THE REMOVE TO BE DELETION/DELETE, AS WELL AS VERIFY THAT IT'S INSERTION, AND SEARCH
    // choice for the input.... 
    char chr;

    // do-while loop 
    do 
    {
      System.out.println("Select your option: ");
      System.out.println("1: Insert");
      System.out.println("2: Delete");
      System.out.println("3: Search");

      int select = input.nextInt();
      // switch statement to make it slightly easier to read...
      switch(select)
      {
        // insert option - calling the insert element function
        case 1: 
          System.out.println("Enter int element to insert in the node");
          splayTree.insertElement(input.nextInt());
          break;
        // delete option - calling the delete function
        case 2: 
          System.out.println("Enter int element to delete in the tree");
          splayTree.delete(input.nextInt());
          break;
        // search option - calling the search function within the tree
        case 3: 
          System.out.println("Enter int element to search for in the tree");
          System.out.println("results: " + splayTree.searchFunction(input.nextInt()));
          break;
          // default for invalid selections 
        default: 
          System.out.println("Invalid option selected.");
          break;
      }

      // Found that preOrder should be working fine, but is running into an issue of not showing R/L/RT, and needs to be able to distinguish correctly. 
      System.out.println("PreOrder: ");
      splayTree.preorderTraversal();

      // prompt for continuing after each node is inserted/deleted/searched for 
      System.out.println("\nContinue? (Y/N) ");
      // forcing next input 
      chr = input.next().charAt(0);
      // verifying that input is compatible with the demand of the prompt
    } while (chr == 'Y' || chr == 'y');
  }
}
