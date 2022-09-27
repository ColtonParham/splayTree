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
  
      // <COME BACK TO THIS!> 
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

  public void remove(int elem)
  {
    // to be created locNode to locate the node 
    nodes node = locNode(elem);
    // remove function further 
    remove(node);
  }

  // remove node function
  private void remove(nodes node)
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

  // function to return the # of nodes counter
  public int nodeQty()
  {
    return ct;
  }
  
}
