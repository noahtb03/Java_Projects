import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Given a user input file, sorts all of the words in that file into a queue,
 * counts how many times each word appears and sorts the words and word counts
 * into a table using html .
 *
 * @author Noah Bennett
 *
 */
public final class WordCounter {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private WordCounter() {
    }

    /**
     * Prints the header of the main html file.
     *
     * @param file
     *            name of the user input file
     * @param out
     *            output stream into user-named file
     * @ensures Outputs the first lines of the main html file.
     * @requires Output stream is open
     */
    private static void printHeader(SimpleWriter out, String file) {
        assert out.isOpen() : "Violation of: Output stream is open";

        //print header and all opening tags
        out.println("<html>");
        out.println(" <head>");
        out.println("  <title>Words Counted in " + file + "</title>");
        out.println(" </head>");
        out.println(" <body>");
        out.println("  <h2>Words Counted in " + file + "</h2>");
        out.println("  <hr>");
        out.println("  <table border=\"1\">");
        out.println("    <tbody>");
        out.println("      <tr>");
        out.println("        <th>Words</th>");
        out.println("        <th>Counts</th>");
        out.println("      </tr>");
    }

    /**
     * Outputs each line of the table with the correct word and word count.
     *
     * @param out
     *            output stream into user-named file
     * @param word
     *            word to print into the table
     * @param count
     *            amount of the given word found in the user input file
     * @ensures Outputs each line of the table with the correct word and word
     *          count
     * @requires Output stream is open
     */
    private static void printTable(SimpleWriter out, String word, int count) {
        assert out.isOpen() : "Violation of: Output stream is open";

        //print opening and closing table tags with word and word count in them
        out.println("      <tr>");
        out.println("        <td>" + word + "</td>");
        out.println("        <td>" + count + "</td>");
        out.println("      </tr>");
    }

    /**
     * @param out
     *            output stream into user-named file
     * @ensures Outputs the closing tags for the html file
     * @requires Output stream is open
     */
    private static void printFooter(SimpleWriter out) {
        assert out.isOpen() : "Violation of: Output stream is open";

        //print the closing html tags at the end of the
        out.println("    </tbody>");
        out.println("  </table>");
        out.println(" </body>");
        out.println("</html>");
    }

    /**
     * Creates a queue of all of the words contained in the input file.
     *
     * @param paragraph
     *            all words in the inserted file
     * @return words queue of every word in paragraph
     * @ensures creates a queue of all of the words contained in the input file
     * @requires paragraph isn't an empty string
     */
    private static Queue<String> createQueue(String paragraph) {
        assert paragraph != null : "Violation of: paragraph is not null";

        //create queue and set to hold separators
        Queue<String> words = new Queue1L<String>();
        Set<Character> separators = new Set1L<Character>();
        String sep = " ,./-";
        for (int i = 0; i < sep.length(); i++) {
            separators.add(sep.charAt(i));
        }
        separators.add('\t');

        //initiate variable for amount of characters checked
        int chars = 0;

        //loop that add a word to the queue if it doesn't contain separators
        while (chars < paragraph.length()) {
            String temp = nextWordOrSeparator(paragraph, chars, separators);
            if (!temp.contains(" ") && !temp.contains("-")
                    && !temp.contains(",") && !temp.contains(".")
                    && !temp.contains("\t") && !temp.contains("/")) {
                words.enqueue(temp);
            }
            chars += temp.length();
        }

        return words;
    }

    /**
     * Creates an output file that will contain html code that creates a table
     * ordered alphabetically with every word in the input file and the amount
     * of times said word appears in the file.
     *
     * @param file
     *            user input file
     * @param oFile
     *            user named output file
     * @requires file and oFile are not empty
     */
    private static void printOutputFile(String file, String oFile) {
        assert !file.isEmpty() : "Violation of: file is not null";
        assert !oFile.isEmpty() : "Violation of: oFile is not null";

        //open input and output streams
        SimpleWriter out = new SimpleWriter1L(oFile);
        SimpleReader in = new SimpleReader1L(file);

        //create a queue to hold all the words in the input file
        Queue<String> words = new Queue1L<String>();

        //create a string of all the words in the input file
        String paragraph = "";
        while (!in.atEOS()) {
            paragraph += in.nextLine() + " ";
            paragraph = paragraph.toLowerCase();
        }

        //add all of the words to the queue and sort them alphabetically
        words.transferFrom(createQueue(paragraph));
        words.sort(String.CASE_INSENSITIVE_ORDER);

        int count = 1;

        //print the header and opening tags
        printHeader(out, file);

        //initiate temp1 and temp2 before the loop
        String temp1 = words.dequeue();
        String temp2 = "";

        while (words.length() > 0) {
            count = 1;

            //if the last word is reached set temp1 to that word
            if (words.length() <= 1) {
                temp1 = words.dequeue();
            } else {
                temp2 = words.dequeue();
            }

            //check how many of each word there is and update count accordingly
            while (temp1.equals(temp2)) {
                if (words.length() > 0) {
                    temp2 = words.dequeue();
                } else {
                    temp2 = "";
                }
                count++;
            }

            //print the second to last word if there is only one of the last word
            if (words.length() == 0 && count == 1) {
                printTable(out, temp2, count);
            }

            //print the table and set temp1 to temp2
            printTable(out, temp1, count);
            temp1 = temp2;
        }

        //print closing tags
        printFooter(out);

        //close input and output streams
        in.close();
        out.close();
    }

    /**
     * Returns a string of consecutive letters or separators.
     *
     * @param text
     *            String of characters the code is finding the word or separator
     *            in
     * @param position
     *            Where in the String the method needs to start finding the next
     *            word or separator
     * @param separators
     *            Separators being used
     * @return str
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        String str = "";
        int i = position;

        //create a string of separators if the first character is a separator
        if (separators.contains(text.charAt(position))) {
            while (i < text.length() && separators.contains(text.charAt(i))) {
                str += text.charAt(i);
                i++;
            }
            //create a string of letters if the first character is a letter
        } else {
            while (i < text.length() && !separators.contains(text.charAt(i))) {
                str += text.charAt(i);
                i++;
            }
        }

        return str;
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

        //prompt the user and read the input file
        out.println("Enter a file: ");
        String file = in.nextLine();

        //prompt user and take the name of the output file
        out.println("Enter an output file name: ");
        String oFile = in.nextLine();

        printOutputFile(file, oFile);

        //Close input and output streams
        in.close();
        out.close();
    }

}
