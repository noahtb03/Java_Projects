import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Noah Bennett
 *
 */
public final class SelectionSort {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SelectionSort() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    public static void selectionSort(int[] arr) {

        for (int i = arr.length - 1; i > 0; i--) {
            int max = 0;

            //check each index and swap max with the larger value
            for (int j = 1; j <= i; j++) {
                if (arr[j] > arr[max]) {
                    max = j;
                }
            }

            //swap the value at max with the current index
            int holder = arr[max];
            arr[max] = arr[i];
            arr[i] = holder;
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        int[] arr = { 833, 211, 67, 579, 802, 354, 566, 462, 196, 169, 243, 318,
                193, 28, 916, 910, 976, 224, 663, 282, 567, 283, 528, 99, 137 };
        selectionSort(arr);

        out.close();
    }

}
