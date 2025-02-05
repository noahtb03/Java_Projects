import components.queue.Queue;
import components.queue.Queue1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program creates a glossary of terms and definitions from an input file.
 *
 * @author Noah Bennett
 *
 */
public final class GlossaryCreator {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private GlossaryCreator() {
    }

    /**
     * Prints out the return to index section of each terms link.
     *
     * @param out
     * @ensures Outputs the return to index section of the term html file.
     * @requires Output stream is open
     */
    private static void printReturnToIndex(SimpleWriter out) {
        assert out.isOpen() : "Violation of output stream is open";

        out.println(" <hr>");
        out.println(" <p> Click 'index' to return to");
        out.println("  <a href=\"index.html\"> index </a>");
        out.println(" </p>");
    }

    /**
     * Prints the header of the main html file.
     *
     * @param out
     * @ensures Outputs the first lines of the main html file.
     * @requires Output stream is open
     */
    private static void printHeader(SimpleWriter out) {
        assert out.isOpen() : "Violation of output stream is open";

        out.println("<html>");
        out.println(" <head>");
        out.println("  <title>Glossary</title>");
        out.println(" </head>");
        out.println(" <body>");
        out.println("  <h2>Glossary</h2>");
        out.println("  <hr>");
        out.println("  <h3>Index</h3>");
        out.println("  <ul>");
    }

    /**
     * Prints the footer of the main html file.
     *
     * @param out
     * @ensures Outputs the last three lines of the main html file.
     * @requires Output stream is open
     */
    private static void printFooter(SimpleWriter out) {
        assert out.isOpen() : "Violation of output stream is open";

        out.println("  </ul>");
        out.println(" </body>");
        out.println("</html>");
    }

    /**
     * Prints the list of terms in alphabetical order including the links for
     * all the terms.
     *
     * @param out
     * @param words
     * @param links
     * @ensures Outputs each term with a link on the main html file.
     * @requires The output stream is open, and the queues words and links have
     *           entries.
     */
    private static void printList(SimpleWriter out, Queue<String> words,
            Queue<String> links) {
        assert out.isOpen() : "Violation of out is open";
        assert words.length() > 0 : "Violation of words is not empty";
        assert links.length() > 0 : "Violation of links is not empty";

        int length = words.length();

        //for each term link the corresponding html file
        for (int i = 0; i < length; i++) {
            out.println("   <li>");
            out.print("    <a href=\"" + links.dequeue() + "\">");
            out.println(words.dequeue() + "</a>");
            out.println("   </li>");
        }
    }

    /**
     * Prints main file including all of the links and a list of all the terms
     * alphabetized. Repeatedly calls the createLink function to create the to
     * separate html files
     *
     * @param file
     * @param folder
     * @ensures All information is printed to the main html file, and follows
     *          the customer's desired format. Terms are in alphabetical order,
     *          and all have links to their own definition pages.
     * @requires file and folder strings aren't empty
     */
    private static void printMainFile(String file, String folder) {
        assert !file.isEmpty() : "Violation of: file is not null";
        assert !folder.isEmpty() : "Violation of: folder is not null";

        SimpleWriter out = new SimpleWriter1L(folder + "/index.html");
        SimpleReader in = new SimpleReader1L(file);

        Queue<String> words = new Queue1L<String>();
        Queue<String> words2 = new Queue1L<String>();
        Queue<String> links = new Queue1L<String>();
        Sequence<String> wordsSeq = new Sequence1L<String>();
        String holder = "a";

        //add terms to two queues
        while (!in.atEOS()) {
            String word = in.nextLine();

            //continue skipping lines to the definition until line is empty
            while (!holder.isEmpty()) {
                holder = in.nextLine();
            }

            holder = "a";
            words.enqueue(word);
            words2.enqueue(word);
            links.enqueue(word + ".html");
        }

        //create a sequence so it can be called in the createLink functions
        for (int i = 0; i < words.length(); i++) {
            String temp = words2.dequeue();
            wordsSeq.add(i, temp);
        }

        //sort links and words alphabetically
        words.sort(String.CASE_INSENSITIVE_ORDER);
        links.sort(String.CASE_INSENSITIVE_ORDER);

        //print every portion of the main html file
        printHeader(out);
        printList(out, words, links);
        printFooter(out);

        //create the links for each word in wordsSeq
        createAllLinks(file, wordsSeq, folder);

        //close streams
        in.close();
        out.close();
    }

    /**
     * Uses a loop to call createIndividualLink to create all of the links
     * needed for each term.
     *
     * @param file
     * @param wordsSeq
     * @param folder
     * @ensures For each term in wordsSeq, a link is created where a definition
     *          is found and links to other terms within the definition exist.
     * @requires Strings file and folder are not empty, and the Sequence
     *           wordsSeq has entries.
     */
    public static void createAllLinks(String file, Sequence<String> wordsSeq,
            String folder) {
        assert !file.isEmpty() : "Violation of: file is not null";
        assert !folder.isEmpty() : "Violation of: folder is not null";
        assert wordsSeq.length() > 0 : "Violation of: wordsSeq is not null";
        SimpleReader in = new SimpleReader1L(file);

        String holder = "a";

        //reread all the terms and definitions to use the createLink function
        //create a link for each term
        while (!in.atEOS()) {
            String word = in.nextLine();
            String definition = "";

            //continue adding lines to the definition until the line is empty
            while (!holder.isEmpty()) {
                holder = in.nextLine();
                definition += holder;
            }
            holder = "a";

            createIndividualLink(word, definition, wordsSeq, folder);
        }

        in.close();
    }

    /**
     * Creates each term's link printing out the term, then the definition with
     * each term in the definition that is its own term having a link.
     *
     * @param word
     * @param definition
     * @param words
     * @param folder
     * @clears words
     * @ensures outputs to the term html file term and definition with links to
     *          definition words that are also in the glossary having their own
     *          links.
     * @requires Strings word, definition, folder, aren't empty, and the
     *           sequence words isn't empty.
     */
    private static void createIndividualLink(String word, String definition,
            Sequence<String> words, String folder) {
        assert !word.isEmpty() : "Violation of: word is not null";
        assert !definition.isEmpty() : "Violation of: definition is not null";
        assert !folder.isEmpty() : "Violation of: folder is not null";
        assert words.length() > 0 : "Violation of: words is not null";

        String file = word + ".html";
        SimpleWriter out = new SimpleWriter1L(folder + "/" + file);
        int position = 0;
        int wordsLength = words.length();

        //print out the top of the term page
        out.println("<body>");
        out.println(" <h2>");
        out.println("  <b>");
        out.println("   <i>");
        out.println("    <font color=\"red\">" + word + "</font>");
        out.println("   </i>");
        out.println("  </b>");
        out.println(" </h2>");
        out.print(" <blockquote>");

        //create a set of separators for next word or separator
        Set<Character> separators = new Set1L<Character>();
        separators.add(' ');
        separators.add(',');
        separators.add('\t');
        separators.add('.');

        //check each word in the definition
        while (position < definition.length()) {
            //create str as a string of words or separators
            String str = nextWordOrSeparator(definition, position, separators);

            boolean equal = false;

            //print out each word, print it as a link if it is a term
            for (int i = 0; i < wordsLength; i++) {
                String test = words.entry(i);
                if (str.equals(test)) {
                    equal = true;
                }
            }

            if (equal) {
                out.print("<a href=\"" + str + ".html\">" + str + "</a>");
            } else {
                out.print(str);
            }

            equal = false;

            position += str.length();
        }

        //print the bottom of the html file
        out.println(" </blockquote>");
        printReturnToIndex(out);

        out.close();
    }

    /**
     * Returns a string of consecutive letters or separators.
     *
     * @param text
     * @param position
     * @param separators
     * @return str
     */
    public static String nextWordOrSeparator(String text, int position,
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

        out.println("Enter a file: ");
        String file = in.nextLine();

        out.println("Enter a file location");
        String folder = in.nextLine();

        printMainFile(file, folder);

        //Close input and output streams
        in.close();
        out.close();
    }

}
