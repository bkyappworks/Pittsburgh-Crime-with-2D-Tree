import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * built using a singly linked list with a single head pointer
 */
public class ListOfCrimes {
    crimeNode head;
    int size;
    static class crimeNode {
        private TwoDTree.TwoDTreeNode data;
        crimeNode next;


        public crimeNode(TwoDTree.TwoDTreeNode data) {
            this.data = data;
            next = null;
        }

        @Override
        public String toString() {
            return data+"";
        }
    }
    /**
     * insert crimeNode to a singly linked list.
     * @param crime
     */
    public void insertCrime(crimeNode crime) {
        if (head == null) {
            head = crime;
            size++;
            return;
        }
        crimeNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        // exit -> cur.next == null
        cur.next = crime;
        crime.next = null;
        size++;
        return;
    }

    /**
     * @param index
     * @return value of crimeNode at index
     */
    public TwoDTree.TwoDTreeNode getCrimeAt(int index) {
        crimeNode cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        if (cur != null) {
            return cur.data;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        crimeNode pointer = this.head;
        while (pointer != null) {
            result.append(pointer.data);
            pointer = pointer.next;
        }
        return result.toString();
    }

    /**
     * reference: https://stackoverflow.com/questions/18725039/java-create-a-kml-file-and-insert-elements-in-existing-file
     * reference: https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it
     * @return as a string and write it to a file
     */
    public String toKML() { // PGHCrimes.kml
        String kmlString = "";
            // to KML
        for (int i = 0; i < size; i++) {
            kmlString  +=
                    "   <Placemark>\n" +
                            "<name>"+this.getCrimeAt(i).getOffense()+"</name>\n" +
                            "<description>"+this.getCrimeAt(i).getStreet()+"</description> <styleUrl>#style1</styleUrl>\n" +
                            "<Point>\n" +
                            "<coordinates>"+ this.getCrimeAt(i).getLon()+","+this.getCrimeAt(i).getLat()+","+"0.000000"+"</coordinates>\n" +
                            "      </Point>\n" +
                            "    </Placemark>\n";
        }

        String finalKML =  "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<kml xmlns=\"http://earth.google.com/kml/2.2\"> <Document>\n" +
                "  <Style id=\"style1\">\n" +
                "  <IconStyle>\n" +
                "<Icon>\n" +
                "<href>http://maps.gstatic.com/intl/en_ALL/mapfiles/ms/micons/blue -dot.png</href>\n" +
                "    </Icon>\n" +
                "   </IconStyle>\n" +
                "   </Style>\n" + kmlString +
                "</Document>\n" +
                "</kml>";

            // write to file
            BufferedWriter output = null;
            try {
                File file = new File("PGHCrimes.kml");
                output = new BufferedWriter(new FileWriter(file));
                output.write(finalKML);
            } catch ( IOException e ) {
                e.printStackTrace();
            } finally {
                if ( output != null ) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        return finalKML;
    }
}
