//Angel Li
//112784616
//Angel.Li@stonybrook.edu
//Homework #5
//CSE 214 (R03)
//TA Kevin Cheng

/**
 * The FXTreeNode class is used to make and represent a FXTreeNode object
 * that has some text, a component type, a parent node, and an array of child
 * nodes.
 *
 *
 * @author Angel Li
 *      Angel.Li@stonybrook.edu
 *      112784616
 */
public class FXTreeNode {
    private String text;
    private ComponentType type;
    private FXTreeNode parent;
    private FXTreeNode[] children;
    final int maxChildren = 10;

    /**
     * This is a constructor used to create an FXTreeNode object.
     */
    public FXTreeNode(){
        this.children = new FXTreeNode[maxChildren];
    }

    /**
     * The method gets the text the object has.
     *
     * @return
     *      Returns string the object has
     */
    public String getText() {
        return text;
    }

    /**
     * The method changes the text an object has
     *
     * @param text
     *      A string
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * The method gets the component type of the object
     *
     * @return
     *      Returns component type of object.
     */
    public ComponentType getType() {
        return type;
    }

    /**
     * The method changes the component type of the object
     *
     * @param type
     *      A component type
     */
    public void setType(ComponentType type) {
        this.type = type;
    }

    /**
     * The method gets the parent node of the object
     *
     * @return
     *      Returns FXTreeNode that is the parent of the object
     */
    public FXTreeNode getParent() {
        return parent;
    }

    /**
     * The method changes the parent node of the FXTreeNode object
     *
     * @param parent
     *      FXTreeNode
     */
    public void setParent(FXTreeNode parent) {
        this.parent = parent;
    }

    /**
     * The method gets the child nodes of the FXTreeNode object
     *
     * @return
     *      Returns array of child nodes associated with the FXTreeNode object
     */
    public FXTreeNode[] getChildren() {
        return children;
    }

    /**
     * The method changes the array of child nodes of the FXTreeNode object
     *
     * @param children
     *      Array of FXTreeNodes
     */
    public void setChildren(FXTreeNode[] children) {
        this.children = children;
    }

    /**
     * The method gets the max number of child nodes an FXTreeNode can have
     *
     * @return
     *      Returns integer of max amount of nodes in FXTreeNode's children array
     */
    public int getMaxChildren() {
        return maxChildren;
    }

    /**
     * The method gets the FXTreeNode in the object's children array
     *
     * @param index
     *      Integer describing index of object's children array
     * @return
     *      FXTreeNode at the specified index in the object's children array
     */
    public FXTreeNode getNodeAtIndex(int index){
        return children[index];
    }

}
