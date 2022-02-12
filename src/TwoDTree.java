import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TwoDTree {
    private TwoDTreeNode root; // root node is X-aligned
    int size = 0;
    int cnt1 = 0;
    static class TwoDTreeNode {
        double x,y,time,tract,lat,lon;
        String street, offense, date;
        TwoDTreeNode left, right;

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public String getStreet() {
            return street;
        }

        public String getOffense() {
            return offense;
        }

        public TwoDTreeNode(double x, double y, double time, String street, String offense, String date, double tract, double lat, double lon) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.street = street;
            this.offense = offense;
            this.date = date;
            this.tract = tract;
            this.lat = lat;
            this.lon = lon;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return
                    x +
                    ", " + y +
                    ", " + time +
                    ", " + street +
                    ", " + offense +
                    ", " + date +
                    ", " + tract +
                    ", " + lat +
                    ", " + lon ;
        }
        //        @Override
//        public String toString() {
//            return "x=" + x +
//                    ", y=" + y + " ";
//
////            return "Node{" +
////                    "x=" + x +
////                    ", y=" + y +
////                    ", left=" + left +
////                    ", right=" + right +
////                    '}';
//        }
    }
    /**
     * pre-condition: The String crimeDataLocation contains the path to a file formatted in the exact same way as CrimeLatLonXY.csv
     * post-condition: The 2d tree is constructed and may be printed or queried.
     * @param crimeDataLocation
     */
    public TwoDTree(String crimeDataLocation) throws FileNotFoundException {
        buildTwoDTree(crimeDataLocation);
//        System.out.println(root);
    }
    public void buildTwoDTree(String crimeDataLocation) {
        File file = new File(crimeDataLocation);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.nextLine();
        while (scanner.hasNext()) {
            String data = scanner.nextLine();
            size++;
            try {
                double x = Double.parseDouble(data.split(",")[0]);
                double y = Double.parseDouble(data.split(",")[1]);
                double time = Double.parseDouble(data.split(",")[2]);
                String street = data.split(",")[3];
                String offense = data.split(",")[4];
                String date = data.split(",")[5];
                double tract = Double.parseDouble(data.split(",")[6]);
                double lat = Double.parseDouble(data.split(",")[7]);
                double lon = Double.parseDouble(data.split(",")[8]);
                TwoDTreeNode node = new TwoDTreeNode(x,y,time,street,offense,date,tract,lat,lon);
                insert(node);

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private void insert(TwoDTreeNode node) {
        if (node == null) {
            return;
        }
        if (root == null) {
            root = node;
            return;
        }
        TwoDTreeNode parent = root;
        TwoDTreeNode current = root;
        int level = 0;
        int cnt = 0;
        while (true) {
            cnt++;
            if (cnt == size) {
                return;
            }
//            if (current.x == node.x && current.y == node.y) {
//                return;
//            }
            parent = current;
            if (level % 2 == 0) {
                if (current.x <= node.x) {
                    current = current.right;
                    level++;
                    if (current == null) {
                        parent.right = node;
                        return;
                    }

                } else {
                    current = current.left;
                    level++;
                    if (current == null) {
                        parent.left = node;
                        return;
                    }
                }

            } else {
                if (current.y <= node.y) {
                    current = current.right;
                    level++;
                    if (current == null) {
                        parent.right = node;
                        return;
                    }

                } else {
                    current = current.left;
                    level++;
                    if (current == null) {
                        parent.left = node;
                        return;
                    }
                }
            }
        }
    }
    /**
     * pre-condition: The 2d tree has been constructed.
     * post-condition: The 2d tree is displayed with a pre-order traversal. Note: an example pre-order traversal appears on the course slides and will be discussed in class.
     */
    public void preOrderPrint() {
        preOrderHelper(root);
    }
    private void preOrderHelper(TwoDTreeNode visit) {
        if (visit != null) {
            System.out.println(visit);
            preOrderHelper(visit.left);
            preOrderHelper(visit.right);
        }
    }
    /**
     * pre-condition: The 2d tree has been constructed.
     * post-condition: The 2d tree is displayed with an in-order traversal. Note: an example in-order traversal appears on the course slides and will be discussed in class.
     */
    public void inOrderPrint() {
        inOrderHelper(root);
    }
    private void inOrderHelper(TwoDTreeNode visit) {
        if (visit != null) {
            inOrderHelper(visit.left);
            System.out.println(visit);
            inOrderHelper(visit.right);
        }
    }
    /**
     * pre-condition: The 2d tree has been constructed.
     * post-condition: The 2d tree is displayed with a post-order traversal. Note: an example post-order traversal appears on the course slides and will be discussed in class.
     */
    public void postOrderPrint() {
        postOrderHelper(root);
    }
    private void postOrderHelper(TwoDTreeNode visit) {
        if (visit != null) {
            postOrderHelper(visit.left);
            postOrderHelper(visit.right);
            System.out.println(visit);
        }
    }
    /**
     * pre-condition: The 2d tree has been constructed.
     * post-condition: The 2d tree is displayed with a level-order traversal.
     */
    public void levelOrderPrint() {
        Queue<TwoDTreeNode> queue = new Queue<>(size);
        List<List<TwoDTreeNode>> wrapList = new ArrayList<>();

        if (root == null) {
            System.out.println(wrapList);
        }
        queue.addToRear(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.curSize;
            List<TwoDTreeNode> subList = new ArrayList<>();
            for (int i=0; i<levelNum; i++) {
                if (queue.peek().left != null) {
                    queue.addToRear(queue.peek().left);
                }
                if (queue.peek().right != null) {
                    queue.addToRear(queue.peek().right);
                }
                subList.add(queue.removeFront());
            }
            wrapList.add(subList);
        }
        System.out.println(wrapList);
    }
    /**
     * pre-condition: The 2d tree has been constructed.
     * post-condition: The 2d tree is displayed with a reverse level- order traversal.
     * Big Theta value
     * Big Theta value reasoning
     */
    public void reverseLevelOrderPrint() {
        Queue<TwoDTreeNode> queue = new Queue<>(size);
        List<List<TwoDTreeNode>> wrapList = new ArrayList<>();

        if (root == null) {
            System.out.println(wrapList);
        }
        queue.addToRear(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.curSize;
            List<TwoDTreeNode> subList = new ArrayList<>();
            for (int i=0; i<levelNum; i++) {
                if (queue.peek().left != null) {
                    queue.addToRear(queue.peek().left);
                }
                if (queue.peek().right != null) {
                    queue.addToRear(queue.peek().right);
                }
                subList.add(queue.removeFront());
            }
            subList = reverseList(subList);
            wrapList.add(subList);
        }
        wrapList = reverseList(wrapList);
        System.out.println(wrapList);
    }
    private ArrayList reverseList(List original) {
        ArrayList newList = new ArrayList<>();
        for (int i = original.size()-1; i >= 0; i--) {
            newList.add(original.get(i));
        }
        return newList;
    }
    /**
     * pre-condition: The 2d tree has been constructed
     * post-condition: A list of 0 or more crimes is returned.
     * These crimes occurred within the rectangular range specified by the four parameters.
     * The pair (x1, y1) is the left bottom of the rectangle.
     * The pair (x2, y2) is the top right of the rectangle.
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public ListOfCrimes findPointsInRange(double x1, double y1, double x2, double y2) {
        ListOfCrimes crimeList = new ListOfCrimes();
        findPointsInRangeHelper(x1,y1,x2,y2,crimeList,root,true);
        return crimeList;
    }
    private void findPointsInRangeHelper(double x1, double y1, double x2, double y2, ListOfCrimes crimeList,TwoDTreeNode cur,boolean align) {
        if (cur == null) {
            return;
        }
        // case 0: contains, for both x,y align
        if (contains(x1,y1,x2,y2,cur.x, cur.y)) {
            crimeList.insertCrime(new ListOfCrimes.crimeNode(cur));
            findPointsInRangeHelper(x1,y1,x2,y2,crimeList,cur.left,changeBoolean(align));
            findPointsInRangeHelper(x1,y1,x2,y2,crimeList,cur.right,changeBoolean(align));
            cnt1++;
            return;
        }
        // the x cases
        // case 1: rec above/below cur
        if (align == true && x1 <= cur.x && x2 >= cur.x) {
            findPointsInRangeHelper(x1,y1,x2,y2,crimeList,cur.left,changeBoolean(align));
            findPointsInRangeHelper(x1,y1,x2,y2,crimeList,cur.right,changeBoolean(align));
        }
        // case 2 rec on the right
        if (align == true && x1 > cur.x) {
            findPointsInRangeHelper(x1,y1,x2,y2,crimeList,cur.right,changeBoolean(align));
        }
        // case 3 rec on the left
        if (align == true && x2 < cur.x) {
            findPointsInRangeHelper(x1,y1,x2,y2,crimeList,cur.left,changeBoolean(align));
        }
        // the y cases
        // case 1 : rec on the left, right go both sides
        if (align == false && y1 <= cur.y && y2 >= cur.y) {
            findPointsInRangeHelper(x1,y1,x2,y2,crimeList,cur.left,changeBoolean(align));
            findPointsInRangeHelper(x1,y1,x2,y2,crimeList,cur.right,changeBoolean(align));
        }
        // case 2 rec above
        if (align == false && y1 > cur.y) {
            findPointsInRangeHelper(x1,y1,x2,y2,crimeList,cur.right,changeBoolean(align));
        }
        // case 3 rec below
        if (align == false && y2 < cur.y) {
            findPointsInRangeHelper(x1,y1,x2,y2,crimeList,cur.left,changeBoolean(align));
        }
        cnt1++;


    }
    private boolean contains(double x1, double y1, double x2, double y2, double x, double y) {
        if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
            return true;
        }
        return false;
    }
    private boolean changeBoolean(boolean align) {
        if (align == true) {
            align = false;
        } else {
            align = true;
        }
        return align;
    }
    /**
     * pre-condition: the 2d tree has been constructed.
     * The (x1,y1) pair represents a point in space near Pittsburgh and in the state plane coordinate system.
     * post-condition: the distance in feet to the nearest node is returned in Neighbor.
     * In addition, the Neighbor object contains a reference to the nearest neighbor in the tree.
     * @param x1
     * @param y1
     * @return
     */
    public Neighbor nearestNeighbor(double x1, double y1) {
        double distance = Integer.MAX_VALUE;
        Neighbor[] neighbor = new Neighbor[1];
        neighbor[0] = new Neighbor(root,distance);
        nearestNeighborHelper(x1,y1,neighbor,root,true,distance);
        return neighbor[0];
    }
    /**
     * @param x1 x-ordinate of user input
     * @param y1 y-ordinate of user input
     * @param neighbor an array stores the nearest neighbor
     * @param cur current node
     * @param align boolean value, true means vertical, false means horizontal.
     * @param distance
     * @return
     */
    private void nearestNeighborHelper(double x1, double y1,Neighbor[] neighbor, TwoDTreeNode cur,boolean align, double distance) {
        // base case
        if (cur == null) {
            return;
        }
        // if cur == (x1,y1)
        if (cur.x == x1 && cur.y == y1) {
            neighbor[0] = new Neighbor(cur,0);
            return;
        }

        // X case
        if (align == true) {
            double curDistance = getDistance(cur.x,cur.y,x1,y1);
            double minDistance = Math.min(curDistance,distance);
            if (curDistance < distance) {
                // found a newChampion
                neighbor[0] = new Neighbor(cur,curDistance);
            }
            // if found a newChampion or not, go left
            if (cur.x > x1) {
                TwoDTreeNode prev = cur;
                nearestNeighborHelper(x1,y1,neighbor,cur.left,changeBoolean(align),minDistance);
                // check the other plane
                if (cur.left != null && Math.abs(cur.left.x-x1) > Math.abs(cur.x-x1)) {
                    nearestNeighborHelper(x1,y1,neighbor,cur.right,changeBoolean(align),minDistance);
                }
            } else { // cur.x <= x1
                nearestNeighborHelper(x1,y1,neighbor,cur.right,changeBoolean(align),minDistance);
                // check the other plane
                if (cur.right != null && Math.abs(cur.right.x-x1) > Math.abs(cur.x-x1)) {
                    nearestNeighborHelper(x1,y1,neighbor,cur.left,changeBoolean(align),minDistance);
                }
            }
        }
        if (align == false) {
            double curDistance = getDistance(cur.x,cur.y,x1,y1);
            double minDistance = Math.min(curDistance,distance);
            if (curDistance < distance) {
                // found a newChampion
                neighbor[0] = new Neighbor(cur,curDistance);
            }
            // if found a newChampion or not, go left
            if (cur.y > y1) {
                nearestNeighborHelper(x1,y1,neighbor,cur.left,changeBoolean(align),minDistance);
                // check the other plane
                if (cur.left != null && Math.abs(cur.left.y-y1) > Math.abs(cur.y-y1)) {
                    nearestNeighborHelper(x1,y1,neighbor,cur.right,changeBoolean(align),minDistance);
                }
            } else { // cur.y <= y1
                nearestNeighborHelper(x1,y1,neighbor,cur.right,changeBoolean(align),minDistance);
                // check the other plane
                if (cur.right != null && Math.abs(cur.right.y-y1) > Math.abs(cur.y-y1)) {
                    nearestNeighborHelper(x1,y1,neighbor,cur.left,changeBoolean(align),minDistance);
                }
            }
        }

    }
    private double getDistance(double x1,double y1,double x2,double y2) {
        double distance = Math.pow(Math.pow(x1-x2,2)+Math.pow(y1-y2,2),0.5);
        return distance;
    }
    public static void main(String[] args) throws FileNotFoundException {
        TwoDTree twoDTree = new TwoDTree("test.csv");
        System.out.println("size: "+twoDTree.size);
        System.out.println("---------- preOrderPrint ---------- ");
        twoDTree.preOrderPrint();
        System.out.println("---------- inOrderPrint ---------- ");
        twoDTree.inOrderPrint();
        System.out.println("---------- postOrderPrint ---------- ");
        twoDTree.postOrderPrint();
        System.out.println("---------- levelOrderPrint ---------- ");
        twoDTree.levelOrderPrint();
        System.out.println("---------- reverseLevelOrderPrint ---------- ");
        twoDTree.reverseLevelOrderPrint();
        System.out.println("crimeList: "+twoDTree.findPointsInRange(1357605.688,411838.5393,1358805.688,413038.5393));
//        System.out.println("nearestNeighbor: "+twoDTree.nearestNeighbor(9,3));
//        System.out.println(twoDTree.contains(1357605.688,411838.5393,1358805.688,413038.5393,1358205.688,412438.5393));

    }

}
