public class Neighbor {
    // Objects of class Neighbor will contain a distance field and a pointer into the 2-d tree
    private TwoDTree.TwoDTreeNode pointer;
    private double distance;
    public Neighbor(TwoDTree.TwoDTreeNode pointer, double distance) {
        this.pointer = pointer;
        this.distance = distance;
    }

    /**
     * getter for pointer.
     * @return pointer.
     */
    public TwoDTree.TwoDTreeNode getPointer() {
        return pointer;
    }

    @Override
    public String toString() {
        return pointer + " ";
    }
}
