import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 *
 * @author Noah Bennett
 *
 */

public final class Newton3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton3() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    private static double sqrt(double x, SimpleReader in, SimpleWriter out) {
        double root = x;

        out.print("Enter precision amount");
        final double epsilon = in.nextDouble();

        while (Math.abs(root * root - x) / x > epsilon) {
            root = (root + x / root) / 2;
        }

        return root;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter a positive starting double: ");
        double x = in.nextDouble();
        double root = 1;
        String cont = "y";
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        while (cont.equals("y")) {
            if (root > 0) {
                out.println(sqrt(x, in, out));
            } else if (x == 0) {
                out.println("undefined");
            }
            out.print("Press 'y' to calculate another root.");
            cont = in.nextLine();
            out.print("Enter a positive starting double: ");
            x = in.nextDouble();
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
