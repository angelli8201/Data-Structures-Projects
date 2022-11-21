//Angel Li
//112784616
//Angel.Li@stonybrook.edu
//Homework #5
//CSE 214 (R03)
//TA Kevin Cheng

/**
 * The FXGuiMaker class takes in a text file and generates an FXComponentTree.
 * The class also provides an interface for a user to manipulate the tree.
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

public class FXGuiMaker {
    public static void main(String[] args) throws FileNotFoundException, InvalidInputException {
        System.out.println("Welcome to counterfeit SceneBuilder.");
        Scanner input = new Scanner(System.in);
        String selection;
        System.out.println("Menu:\n" +
                "L) Load from file\n" +
                "P) Print tree\n" +
                "C) Move cursor to a child node\n" +
                "R) Move cursor to root\n" +
                "A) Add a child\n" +
                "U) Cursor up (to parent)\n" +
                "E) Edit text of cursor\n" +
                "D) Delete child\n" +
                "S) Save to file\n" +
                "Q) Quit");

        FXComponentTree tree = new FXComponentTree();
        do{
            System.out.println("Please select an option: ");
            selection = input.next();

            switch (selection){
                case "L":
                case"l":
                        System.out.println("Please enter a file name: ");
                        String name = input.next();
                        File file = new File(name);
                    try {
                        Scanner s = new Scanner(file);
                        tree = FXComponentTree.readFromFile(name);
                        System.out.println(name + " loaded.");

                    }catch(FileNotFoundException ex){
                        System.out.println(name + " not found.");
                    }
                    break;

                case "P":
                case "p":
                    int counter =-1;
                    tree.printNode(counter,tree.getRoot());
                    System.out.println("");

                    break;

                case "C":
                case "c":
                    try {
                        System.out.println("Please enter number of child (starting with 1): ");
                        int index = input.nextInt();
                        tree.cursorToChild(index - 1);
                        System.out.println("Cursor moved to " + tree.getCursor().getType()
                                + " " + tree.getCursor().getText());
                    }catch(Exception e){
                        tree.cursorToRoot();
                        System.out.println("Invalid input. Cursor moved back to root." +
                                " Please try again.");
                    }
                    break;

                case "R":
                case "r":
                    tree.cursorToRoot();
                    System.out.println("Cursor is at the root.");
                    break;


                case "A":
                case "a":
                    try {
                        System.out.println("Select component type (H - HBox, " +
                                "V - VBox, T - TextArea, B - Button, L - Label): ");
                        String component = input.next();
                        System.out.println("Please enter an index(Starting at 1):");
                        int ind = input.nextInt()-1;
                        input.nextLine();
                        FXTreeNode temp = new FXTreeNode();

                        if (component.equalsIgnoreCase("H")) {
                            temp.setType(ComponentType.HBox);
                            temp.setText("");
                            tree.addChild(ind, temp);
                            System.out.println();

                        } else if (component.equalsIgnoreCase("V")) {
                            temp.setType(ComponentType.VBox);
                            temp.setText("");
                            tree.addChild(ind, temp);
                            System.out.println();

                        } else if (component.equalsIgnoreCase("T")) {
                            System.out.println("Please enter text: ");
                            String text = input.nextLine();
                            temp.setText(text);
                            temp.setType(ComponentType.TextArea);
                            tree.addChild(ind, temp);
                            System.out.println();

                        } else if (component.equalsIgnoreCase("B")) {
                            System.out.println("Please enter text: ");
                            String text = input.nextLine();
                            temp.setText(text);
                            temp.setType(ComponentType.Button);
                            tree.addChild(ind, temp);

                        } else if (component.equalsIgnoreCase("L")) {
                            System.out.println("Please enter text: ");
                            String text = input.nextLine();
                            temp.setText(text);
                            temp.setType(ComponentType.Label);
                            tree.addChild(ind, temp);

                        } else {
                            System.out.println("Not an option. Please try again!");
                        }
                    }catch (Exception ex){
                        System.out.println("Invalid input. Please try again");
                    }


                    break;

                case "U":
                case "u":
                    tree.cursorToParent();
                    System.out.println("Cursor moved to " + tree.getCursor().getType());
                    break;

                case "E":
                case "e":
                    input.nextLine();
                    System.out.println("Please enter new text: ");
                    String newText = input.nextLine();
                    tree.setTextAtCursor(newText);
                    System.out.println("Text edited.");
                    break;

                case "D":
                case "d":
                    System.out.println("Please enter number of child(Starting at 1): ");
                    int indx = input.nextInt();
                    tree.deleteChild(indx -1);
                    System.out.println("Child removed.");
                    break;

                case "S":
                case "s":
                    System.out.println("Please enter file name: ");
                    String filename = input.next();
                    FXComponentTree.writeToFile(tree,filename);

                    break;


                case "Q":
                case "q":
                    System.out.println("Make like a tree and leave!");
                    break;

                default:
                    System.out.println("");
                    System.out.println(selection +" is not a menu option. " +
                            "Please try again:");
                    System.out.println("");

            }

        }while(!selection.equalsIgnoreCase("Q"));
    }
}
