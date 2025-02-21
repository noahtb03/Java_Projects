import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 *
 * @author Noah Bennett
 *
 */
public final class ABCDGuesser2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser2() {
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
     * Calculates the best estimate and returns it.
     *
     * @param expNums
     * @param in
     * @param out
     * @return closestValue
     */

    private static double getBestEstimate(final double[] expNums,
            SimpleReader in, SimpleWriter out) {
        double constant = getPositiveDouble(in, out);
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);
        int i, j, k, l;
        double a, b, c, d;
        double closestValue = 0;
        double newGuess = 0;

        for (i = 0; i < expNums.length; i++) {
            a = Math.pow(w, expNums[i]);
            for (j = 0; j < expNums.length; j++) {
                b = Math.pow(x, expNums[j]);
                for (k = 0; k < expNums.length; k++) {
                    c = Math.pow(y, expNums[k]);
                    for (l = 0; l < expNums.length; l++) {
                        d = Math.pow(z, expNums[l]);
                        newGuess = a * b * c * d;
                        if (Math.abs(constant - closestValue) > Math
                                .abs(constant - newGuess)) {
                            closestValue = newGuess;
                        }
                    }
                    l = 0;
                }
                k = 0;
            }
            j = 0;
        }

        return closestValue;
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

        double closestValue = getBestEstimate(expNums, in, out);

        out.println("The closest value calculated is " + closestValue);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
