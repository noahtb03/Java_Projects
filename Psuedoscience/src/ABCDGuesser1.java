import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ABCDGuesser1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser1() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        double validNum = 0;

        out.println("Enter a positive double.");
        String constant = in.nextLine();

        if (FormatChecker.canParseDouble(constant)
                && Double.parseDouble(constant) > 0) {
            validNum = Double.parseDouble(constant);
        }

        while (!FormatChecker.canParseDouble(constant)
                || Double.parseDouble(constant) < 0) {
            out.print("Please enter a positive double.");
            constant = in.nextLine();
            if (FormatChecker.canParseDouble(constant)) {
                validNum = Double.parseDouble(constant);
            }
        }

        return validNum;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        double validNum = 0;

        out.println("Enter a positive double that isn't 1.");
        String constant = in.nextLine();

        if (FormatChecker.canParseDouble(constant)
                && Double.parseDouble(constant) > 0
                && Double.parseDouble(constant) != 1) {
            validNum = Double.parseDouble(constant);
        }

        while (!FormatChecker.canParseDouble(constant)
                || Double.parseDouble(constant) < 0
                || Double.parseDouble(constant) == 1) {
            out.print("Please enter a positive double that isn't 1.");
            constant = in.nextLine();
            if (FormatChecker.canParseDouble(constant)
                    && Double.parseDouble(constant) != 1
                    && Double.parseDouble(constant) > 0) {
                validNum = Double.parseDouble(constant);
            }
        }

        return validNum;
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
        final double[] expNums = { -5, -4, -3, -2, -1, -1 / 2, -1 / 3, -1 / 4,
                0, 1 / 4, 1 / 3, 1 / 2, 1, 2, 3, 4, 5 };
        double constant = getPositiveDouble(in, out);
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        double a, b, c, d;
        double closestValue = 0;
        double newGuess = 0;

        while (i < expNums.length) {
            a = Math.pow(w, expNums[i]);
            while (j < expNums.length) {
                b = Math.pow(x, expNums[j]);
                while (k < expNums.length) {
                    c = Math.pow(y, expNums[k]);
                    while (l < expNums.length) {
                        d = Math.pow(z, expNums[l]);
                        l++;
                        newGuess = a * b * c * d;
                        if (Math.abs(constant - closestValue) > Math
                                .abs(constant - newGuess)) {
                            closestValue = newGuess;
                        }
                    }
                    k++;
                    l = 0;
                }
                j++;
                k = 0;
            }
            i++;
            j = 0;
        }

        out.println("The closest value calculated is " + closestValue);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
