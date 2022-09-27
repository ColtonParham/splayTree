// Colton Parham - CDP210001
// Dr. Zhao, CS3345
// splay tree project (Project 1)

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
    // reinstantiating 
    this.element = elem;
    this.left = l;
    this.right = r;
    this.parentNode = parent;
  }
}
//END OF CONSTRUCTOR FOR NODE.....

//setting up that splay tree...
class splayTree{
  // rootPoint will be the effective root, and be set to null as needed
  private nodes rootPoint;
  // count variable set at 0 for it's starting point 
  private int ct = 0;
  // constructor for setting the value of the rootPoint
  public splayTree()
  {
    rootPoint = null;
  }
  // function to verify that the tree is empty 
  public boolean empty()
  {
    return rootPoint == null;
  }
  // function to clear out the splay tree 
  public void clearTree()
  {
    rootPoint = null;
    ct = 0;
  }

  // function to insert elements inside of the tree 
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

  //child to parent function to rotate the node 
  public void childToParent(nodes child, nodes par)
  {
    if ((child == null) || (par == null) || (par.left != child) || (child.parentNode != par))
    {
      // autofill screwed me <come back to this to finish reviewing notes> 
      throw new RuntimeException("shit");
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

  }

}
