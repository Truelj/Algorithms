
package BinaryTree;

/**
 *
 * @author Jie Li
 * Date:3/4/2016
 * Descritpion: A binary tree is made of nodes, where each node contains a "left" reference, a "right" reference,
 * and a data element. The topmost node in the tree is called the root
 * 
 * Some tree terminology
 *  * The depth of a node is the number of edges from the root to the node.
 *  * The height of a node is the number of edges from the node to the deepest leaf. 
 *  * The height of a tree is a height of the root
 *  * A full binary tree, is a binary tree in which each node has eactly 0 or 2 children. 
 *  * A complete binary tree is a binary tree, which si completely filled, with the possbile exception of the bottom leverl, which is filled from left to right. 
 *
 */
public class BinaryTree {
    int nodeValue;
    BinaryTree left;//left refers to an instance of BinrayTree that is one of 2 direct children of current node. 
    BinaryTree right;//left refers to an instance of BinrayTree that is the other direct child of current node.
    
    //to create a binary tree
    public BinaryTree(int nodeValue){
        this.nodeValue = nodeValue;
    }
    public void setLeft(BinaryTree Left){
        this.left = Left;
    }
    public void setRight(BinaryTree right){
        this.right = right;
    }
    public BinaryTree getLeft(){
        return this.left;
    }
    public BinaryTree getRight(){
        return this.right;
    }
    //get the current node value
    public int getNodeValue(){
        return this.nodeValue;
    }
    
    //to insert a node to the binary tree, starting from the root
    public void insertNode(BinaryTree parent, BinaryTree newNode){
        if(parent.getNodeValue() > newNode.getNodeValue()){
            if(parent.getLeft() == null){
                parent.setLeft(newNode);
                System.out.println("parent:"+ parent.nodeValue + " child: "+ newNode.nodeValue);
            }else{//compare newNode with left child
                if(parent.getLeft().getNodeValue()< newNode.getNodeValue()){//newNode is greater than left child
                    newNode.setLeft(parent.getLeft());
                    parent.setLeft(newNode);
                    System.out.println("parent:"+ parent.nodeValue + " child: "+ newNode.nodeValue);
                }else{//newNode is smaller than left child
                    insertNode(parent.getLeft(), newNode);
                }
                
            }             
        }else{
            if(parent.getRight() == null){
                parent.setRight(newNode);   
                System.out.println("parent:"+ parent.nodeValue + " child: "+ newNode.nodeValue);
            }else{//compare newNode with right node
                if(parent.getRight().getNodeValue()> newNode.getNodeValue()){//newNode is smaller than right child
                    newNode.setRight(parent.getRight());
                    parent.setRight(newNode);
                    System.out.println("parent:"+ parent.nodeValue + " child: "+ newNode.nodeValue);
                }else{//newNode is greater than right child
                    insertNode(parent.getRight(), newNode);
                }  
            }
        }
        
    }
    
    public static void main(String args[]){
        int[] nodeValues =  {2,3,4,5};
        BinaryTree tree1 = new BinaryTree(1);
        for(int i = 0; i < nodeValues.length; i++){
            //create a new node andd add that node to tree
            tree1.insertNode(tree1, new BinaryTree(nodeValues[i]));
        }
    }
    
}






















