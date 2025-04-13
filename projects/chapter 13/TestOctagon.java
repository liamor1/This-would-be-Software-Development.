// test class for the octagon
// creates an octagon, clones it, and compares the original and the clone
public class TestOctagon {
    public static void main(String[] args) {
        try {
            // create an octagon object
            Octagon oct1 = new Octagon(5);

            // clone the octagon
            Octagon oct2 = (Octagon) oct1.clone();

            // display area of both octagons
            System.out.println("Octagon 1 Area: " + oct1.getArea());
            System.out.println("Octagon 2 Area (cloned): " + oct2.getArea());

            // compare the two octagons
            int result = oct1.compareTo(oct2);
            if (result == 0)
                System.out.println("Both octagons are equal in area.");
            else if (result > 0)
                System.out.println("Octagon 1 is larger.");
            else
                System.out.println("Octagon 2 is larger.");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
