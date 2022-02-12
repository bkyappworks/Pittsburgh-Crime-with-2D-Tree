import java.io.FileNotFoundException;
import java.util.Scanner;

public class TwoDTreeDriver {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException {
        // load the crime data file into the 2d tree
        TwoDTree twoDTree = new TwoDTree("CrimeLatLonXY.csv");
        System.out.println("Crime file loaded into 2d tree with "+ twoDTree.size +" records.");
        // interact with the user as shown below
        int choice = 0;
        do {
            System.out.println("What would you like to do?");
            System.out.println("1: inorder");
            System.out.println("2: preorder");
            System.out.println("3: levelorder");
            System.out.println("4: postorder");
            System.out.println("5: reverseLevelOrder");
            System.out.println("6: search for points within rectangle");
            System.out.println("7: search for nearest neighbor");
            System.out.println("8: quit");

            choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1) {
                twoDTree.inOrderPrint();
            } else if (choice == 2) {
                twoDTree.preOrderPrint();
            } else if (choice == 3) {
                twoDTree.levelOrderPrint();
            } else if (choice == 4) {
                twoDTree.postOrderPrint();
            } else if (choice == 5) {
                twoDTree.reverseLevelOrderPrint();
            } else if (choice == 6) {
                System.out.println("Enter a rectangle bottom left (X1,Y1) and top right (X2, Y2) as four doubles each separated by a space.");
                System.out.println("X1: ");
                double x1 = Double.parseDouble(scanner.nextLine());
                System.out.println("Y1: ");
                double y1 = Double.parseDouble(scanner.nextLine());
                System.out.println("X2: ");
                double x2 = Double.parseDouble(scanner.nextLine());
                System.out.println("Y2: ");
                double y2 = Double.parseDouble(scanner.nextLine());
                ListOfCrimes cl = twoDTree.findPointsInRange(x1,y1,x2,y2);
                System.out.println("Searching for points within ("+x1+","+y1+") and ("+x2+","+y2+")");
                System.out.println("Examined "+twoDTree.cnt1+" nodes during search.");
                System.out.println("Found "+ cl.size + " crimes.");
                while (cl.head != null) {
                    System.out.println(cl.head);
                    cl.head = cl.head.next;
                }
                twoDTree.findPointsInRange(x1,y1,x2,y2).toKML();
                System.out.println("The crime data has been written to PGHCrimes.KML. It is viewable in Google Earth Pro.");
            } else if (choice == 7) {
                System.out.println("Enter a point to find nearest crime. Separate with a space.");
                System.out.println("X: ");
                double x1 = Double.parseDouble(scanner.nextLine());
                System.out.println("Y: ");
                double y1 = Double.parseDouble(scanner.nextLine());
                twoDTree.nearestNeighbor(x1,y1);
                System.out.println("Looked at 27 nodes in tree. Found the nearest crime at:"+twoDTree.nearestNeighbor(x1,y1));
            }
        } while(choice != 8);
        System.out.println("Thank you for exploring Pittsburgh crimes in the 1990’s.");

    }
}
