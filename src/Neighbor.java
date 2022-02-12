public class Neighbor {
    // Objects of class Neighbor will contain a distance field and a pointer into the 2-d tree
    private TwoDTree.TwoDTreeNode pointer;
    private double distance;


    public Neighbor(TwoDTree.TwoDTreeNode pointer, double distance) {
        this.pointer = pointer;
        this.distance = distance;
    }

    public TwoDTree.TwoDTreeNode getPointer() {
        return pointer;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return pointer + " ";
    }
}
