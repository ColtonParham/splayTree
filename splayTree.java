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
    nodes k = null;
    // while the rootPoint is not null 
    while (r != null)
    {
      k = r;
      // comparing the values being passed in <TENATIVE>
      if (elem > k.element)
      {
        r = r.right;
      }
      else 
      {
        r = r.left;
      }
    }
  }
}
