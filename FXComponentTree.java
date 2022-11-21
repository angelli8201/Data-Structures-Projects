//Angel Li
//112784616
//Angel.Li@stonybrook.edu
//Homework #5
//CSE 214 (R03)
//TA Kevin Cheng

/**
 * The FXComponentTree class is used to make FXComponentTree object.
 * The tree has a root and a cursor. The tree is also able to be generated
 * and saved from a file.
 *
 *
 * @author Angel Li
 *      Angel.Li@stonybrook.edu
 *      112784616
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class FXComponentTree {
    private FXTreeNode root;
    private FXTreeNode cursor;

    /**
     * This is a constructor used to create an FXComponentTree object.
     */
    public FXComponentTree() {
        root = null;
        cursor = null;
    }

    /**
     * The method gets the root of the tree.
     *
     * @return
     *      Returns FXTreeNode that is the root of the tree
     */
    public FXTreeNode getRoot() {
        return root;
    }

    /**
     * The method changes the root of the tree
     *
     * @param root
     *      FXTreeNode object
     */
    public void setRoot(FXTreeNode root) {
        this.root = root;
    }

    /**
     * The method gets the cursor of the tree
     *
     * @return
     *      Returns FXTreeNode that the cursor currently references
     */
    public FXTreeNode getCursor() {
        return cursor;
    }

    /**
     * The method changes the object the cursor references
     *
     * @param cursor
     *      FXTreeNode object
     */
    public void setCursor(FXTreeNode cursor) {
        this.cursor = cursor;
    }

    /**
     * The method sets the cursor back to the root of the tree.
     */
    public void cursorToRoot() {
        cursor = root;
    }

    /**
     * The method removes the child at the specified index of the tree
     *
     * @param index
     *      Integer describing the index of where to remove the node(s)
     */
    public void deleteChild(int index) {
        if (cursor.getNodeAtIndex(index) != null) {
            FXTreeNode[] tempChildren = new FXTreeNode[10];
            for (int i = 0; i < index; i++) {
                tempChildren[i] = cursor.getNodeAtIndex(i);
            }
            for(int i = index;i<8; i++){
                tempChildren[i]= cursor.getNodeAtIndex(i+1);
            }
            cursor.setChildren(tempChildren);
        }
    }

    /**
     * The method adds the given node to the corresponding index of the
     * children array
     *
     * @param index
     *      Index describing where to add the FXTreeNode object
     * @param node
     *      FXTreeNode object
     * @throws InvalidInputException
     *      Throws exception if adding the node at the index creates a hole in
     *      the children array
     */
    public void addChild(int index, FXTreeNode node) throws InvalidInputException {
        if (root == null) {
            FXTreeNode newNode = node;
            root = newNode;
            cursor = root;
            return;
        }

        
        if (index >0 && cursor.getNodeAtIndex(index - 1) == null) {
            throw new InvalidInputException();
        }

        //index is taken
        if (cursor.getNodeAtIndex(index) != null) {
            FXTreeNode[] tempArray = new FXTreeNode[10];
            for (int i = 0; i < index; i++) {
                tempArray[i] = cursor.getNodeAtIndex(i);
            }
            tempArray[index] = node;
            for (int i = index + 1; i < 10; i++) {
                tempArray[i] = cursor.getNodeAtIndex(i - 1);
            }
            cursor.setChildren(tempArray);

        }
        //index not taken
        else{
            FXTreeNode[] tempArray2 = new FXTreeNode[10];
            for (int i = 0; i < index; i++) {
                tempArray2[i] = cursor.getNodeAtIndex(i);
            }
            tempArray2[index] = node;
            cursor.setChildren(tempArray2);

        }
        for(FXTreeNode n: cursor.getChildren()){
            if(n!= null)
            n.setParent(cursor);
        }

    }

    /**
     * The method changes the text of the FXTreeNode that the cursor
     * currently references
     *
     * @param text
     *      String
     */
    public void setTextAtCursor(String text){
        cursor.setText(text);
    }

    /**
     * The method changes the FXTreeNode the cursor is referencing
     *
     * @param index
     *      Index of child array to change the cursor to
     */
    public void cursorToChild(int index){
        this.setCursor(cursor.getNodeAtIndex(index));
    }

    /**
     * The method changes the cursor to the parent of the current node
     */
    public void cursorToParent(){
        this.setCursor(cursor.getParent());
    }

    /**
     * The method generates an FXComponentTree based on the file name passed in
     *
     * @param filename
     *      The name of the file passed in
     * @return
     *      Returns an FXTreeComponent generated from the file passed in
     * @throws FileNotFoundException
     *      Throws exception if file not found
     * @throws InvalidInputException
     *      Throws exception if file not valid format
     */
    public static FXComponentTree readFromFile(String filename) throws FileNotFoundException, InvalidInputException {
        File file = new File(filename);
        Scanner s = new Scanner(file);
        FXComponentTree tempTree = new FXComponentTree();

        while (s.hasNextLine()) {
            String[] temp = s.nextLine().split(" ",3);
            FXTreeNode tempNode = new FXTreeNode();

            switch (temp[1]) {
                case "AnchorPane":
                case "anchorpane":
                    tempNode.setType(ComponentType.AnchorPane);
                    tempNode.setText("");
                    break;
                case "VBox":
                case "vbox":
                    tempNode.setType(ComponentType.VBox);
                    tempNode.setText("");
                    break;
                case "HBox":
                case "hbox":
                    tempNode.setType(ComponentType.HBox);
                    tempNode.setText("");
                    break;
                case "Button":
                case "button":
                    tempNode.setType(ComponentType.Button);
                    tempNode.setText(temp[2]);
                    break;
                case "Label":
                case "label":
                    tempNode.setType(ComponentType.Label);
                    tempNode.setText(temp[2]);
                    break;
                case "TextArea":
                case "textarea":
                    tempNode.setType(ComponentType.TextArea);
                    tempNode.setText(temp[2]);

                    break;
            }
            String[] position = temp[0].split("-");
            int count = position.length;

            //set cursor to root
            if (count == 1) {
                tempTree.setRoot(tempNode);
                tempTree.setCursor(tempNode);
            }

            else {
                for (int i = 1; i < count - 1; i++) {
                    int x = Integer.parseInt(position[i]);
                    tempTree.setCursor(tempTree.getCursor().getNodeAtIndex(x));
                }

                int y = Integer.parseInt(position[count-1]);
                tempTree.addChild(y, tempNode);
                tempTree.cursorToRoot();
            }
        }
        return tempTree;
    }

    /**
     * The method generates a text file that reflects the structure of the
     * FXComponentTree
     *
     * @param tree
     *      FXComponentTree object
     * @param filename
     *      The name of the file we're trying to save to
     * @throws FileNotFoundException
     *      Throws exception if file not found
     */
    public static void writeToFile(FXComponentTree tree, String filename) throws FileNotFoundException {
        File newFile = new File(filename);
        PrintWriter pw = new PrintWriter(newFile);
        goToNode(tree.root,"0",pw);
        pw.close();
    }

    /**
     * The method is helper method to traverse through the tree
     *
     * @param node
     *      FXTreeNode object
     * @param path
     *      Describes the position of the node in the tree
     * @param pw
     *      PrintWriter object
     */
    public static void goToNode(FXTreeNode node, String path, PrintWriter pw) {
        String s = new String();
        if (node == null) {
           return;
        }
        s+= node.getType() + " "+ node.getText();
        pw.println(path+" "+s);

        FXTreeNode[] children = node.getChildren();
        for (int i =0; i<children.length && children[i] != null; i++) {
                goToNode(children[i],path+"-"+i, pw);
        }
    }

    /**
     * The method gives a representation of the FXComponentTree
     *
     * @param counter
     *      Counter helps with the indentation of each node when printed
     * @param node
     *      FXTreeNode object
     */
    public void printNode(int counter, FXTreeNode node) {
        if(node == null){
            return;
        }
        if(counter == -1 && getCursor() == node){
            System.out.println("==>" +node.getType() +" "+node.getText());
        }
        else if( counter == -1){
            System.out.println(node.getType() +" "+node.getText());
        }
        else if(getCursor() == node) {
            System.out.println("==>" + node.getType() + " " + node.getText());
        }
        else{
            System.out.println("+--" + node.getType() + " " + node.getText());
        }
        FXTreeNode[] childNodes = node.getChildren();

        counter += 1;
        for (FXTreeNode child : childNodes) {
            if(child != null) {
               System.out.println("");
            }

            String tab = "\t";
            System.out.print(tab.repeat(counter));
            printNode(counter,child);
        }

    }


}
