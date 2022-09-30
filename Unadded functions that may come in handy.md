Between Search and Delete: 

// function to return the # of nodes counter
  public int nodeQty()
  {
    return ct;
  }

This actually turned out to be useful for the integral part of the program.....  



// This is between the SplayTreeConfig() and InsertElement 

// This might not be needed in the end, but keeping just in case i'm missing something..

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


  // messing with the preorder: 

  System.out.print(k.element + " - ");
      preorderTraversal(k.left); // might be worth to create a condition here to indicate whether left or right with a if statement with the proper k value. 
      preorderTraversal(k.right);
// keeping this in case i mess it up...



ADDITIONAL STUFF: 
// so this might be helpful as we are needing to do the pre-order traversal and could potentially do something similar to this, but adjusted....


// ORIGINAL CODE 
        /* first print data of node */
        System.out.print(node.key + " ");
 
        /* then recur on left subtree */
        printPreorder(node.left);
 
        /* now recur on right subtree */
        printPreorder(node.right);
    }


        /* first print data of node */
        System.out.print(node.key + " ");
 
        /* then recur on left subtree */ - could require a direct conversion to work correctly...
        System.out.print(printPreorder(node.left) + "L");
 
        /* now recur on right subtree */ - this could require a conditional, but also might not if it's being directly applied, but if its printing recursively could cause issues. 
        System.out.print((printPreorder(node.right) + "R");
    }

    void printPreorder() { System.out.print(printPreorder(root) + "RT"); }

might need to be able to seperate or trim out the additional L/R/RT when checking the value against the position.
or could be a matter of the indexed tree point/node - might be able to sort without arithmetic, and base it purely off of logic base. 

PROMPT LOG: 

Might need to go through hell and test everything individually for each function to verify where it could appear.... once that's figured, all good! 

Maybe in reference to GFG splay tree example, having the right justified value of the tree be directly correlated with the value if bigger when recurring on itself? <This might need a loop, but thats probably not the best idea since there is 
already one for the input.>

First value = RT
Second value = L
Third value = R <Needs to be of higher V>

Element value needs to output that char and number, but since the element is code as an int, a reference might be needed.

Turns out in the end we didn't need the RT/L/R values......
