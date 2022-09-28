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