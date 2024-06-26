// Colton Parham - CDP210001
// Dr. Zhao, CS3345.505
// Splay Tree project (Project 1)

// Scanner Library for input from keyboard
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

  // constructor for publicly viable vars/params, or in consolidation, nodes.
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
    // by default setting the rootPoint to be of null value until updated otherwise
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
      // comparing the values being passed in 
      if (elem > par.element)
      {
        r = r.right;
      }
      else 
      {
        r = r.left;
      }
    }

    // new nodes 
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
    // calling splay action here 
    splayAction(r);
    // increment the count here 
    ct++;
  }

    //child to parent function to rotate the node <right>
    // START OF FUNCTION
    public void childToParentRight(nodes child, nodes par)
    {
      // setting up a condition to throw errors if program is not getting good node vals
      if ((child == null) || (par == null) || (par.right != child) || (child.parentNode != par))
      {
        //ERROR 2 CTPR = CHILD TO PARENT RIGHT - to indicate where the error might have occured
        throw new RuntimeException("ERROR1 CTPR");
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
    // setting up a condition to throw errors if program is not getting good node vals
    if ((child == null) || (par == null) || (par.left != child) || (child.parentNode != par))
    {
      //ERROR 2 CTPL = CHILD TO PARENT LEFT - to indicate where the error might have occured
      throw new RuntimeException("ERROR2 CTPL");
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

    // child to parent, and parent to child
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
    // calling the splay action function
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
      // if the elem being passed through is less than the referenced element move the v value to the left 
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

  // pre-order traversal U(tility) function - used for setting up character assignment for kR/KL
  public void preorderTraversalU(nodes node, String suff)
  {
    // recurs on itself.
    if (node == null)
    {
      return;
    }
    // applying the same rule as the original function, but adding the suff var to apply right after, and since there is no if condition, apply R/L in respective spots 
    System.out.print(node.element + suff + " ");
    // applies to left node
    preorderTraversalU(node.left, "L");
    // applies to right node
    preorderTraversalU(node.right, "R");
  }
  // checking if not null and printing elements with left first then right respectively.
  public void preorderTraversal()
  {
    // root charcter with root value kRT
    this.preorderTraversalU(this.rootPoint, "RT");
    // printing a new line
    System.out.println();
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
    // option prompt
    System.out.println("Please select an option:\n");
    // choice for the input.... 
    char chr;

    // do-while loop to display all of the options, as well it contains the function calls based in the selected 
    do 
    {
      // list of options
      System.out.println("Select your option: ");
      System.out.println("1: Insert");
      System.out.println("2: Delete");
      System.out.println("3: Search");

      // input task
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
          // this will take the input for the value to be deleted
          splayTree.delete(input.nextInt());
          break;
        // search option - calling the search function within the tree
        case 3: 
          System.out.println("Enter int element to search for in the tree");
          // grabbing the search result - produces t/f if the element is in the tree
          System.out.println("results: " + splayTree.searchFunction(input.nextInt()));
          break;
        default: 
        // this will output the error for the invalid option
          System.out.println("Invalid option selected.");
          break;
      }

      // Preorder prompt message, and display function
      System.out.println("PreOrder: ");
      splayTree.preorderTraversal();

      // prompt for continuing after each node is inserted/deleted/searched for 
      System.out.println("\nContinue? (Y/N) ");
      // forcing next input 
      chr = input.next().charAt(0);
      // verifying that input is compatible with the demand of the prompt anything else will be considered NO/N/n
    } while (chr == 'y');
    // closing out input 
    input.close();
  }
  
}
