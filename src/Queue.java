/**
 * reference:
 * @param <T>
 */
public class Queue<T> {
    // Queue.java is built with a linked list based queue – using front and rear pointers
    private class qNode {
        private TwoDTree.TwoDTreeNode data;
        private  qNode prev,next;

        public qNode(TwoDTree.TwoDTreeNode t) {
            data = t;
            prev = null;
            next = null;
        }
    }
    int qSize,curSize;
    qNode head, rear;
    TwoDTree.TwoDTreeNode dummy = new TwoDTree.TwoDTreeNode(-1,-1,-1,"-1","-1","-1",-1,-1,-1);
    public Queue(int size) {
        qSize = size;
        curSize = 0;
        head = new qNode(dummy); // dummy
        rear = new qNode(dummy); // dummy
        head.next = rear;
        rear.prev = head;
    }

    // add to the rear
    public void addToRear(TwoDTree.TwoDTreeNode t) {
        if (curSize == qSize) {
            return;
        }
        qNode node= new qNode(t);
        node.next = rear;
        node.prev = rear.prev;
        rear.prev.next = node;
        rear.prev = node;
        curSize++;

    }
    // remove from the front
    public TwoDTree.TwoDTreeNode removeFront() {
        if (curSize == 0) {
            return dummy;
        }
        qNode toBeDeleted = head.next;
        head.next = toBeDeleted.next;
        toBeDeleted.next.prev = head;
        toBeDeleted.next = null;
        toBeDeleted.prev = null;
        curSize--;
        return toBeDeleted.data;
    }

    public boolean isEmpty() {
        return curSize == 0;
    }
    public TwoDTree.TwoDTreeNode peek() {
        if (isEmpty()) {
            return dummy;
        }
        return head.next.data;
    }
}
