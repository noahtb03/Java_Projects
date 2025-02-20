import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import components.naturalnumber.NaturalNumber;

/**
 * View class.
 *
 * @author Noah Bennett
 */
public final class NNCalcView1 extends JFrame implements NNCalcView {

    /**
     * Default serial ID
     */
    private static final long serialVersionUID = 1L;

    //object for observing user interaction events
    private NNCalcController controller;

    //state of user interation
    private enum State {

        SAW_CLEAR, SAW_ENTER_OR_SWAP, SAW_OTHER_OP, SAW_DIGIT
    }

    //state variable to track last event
    private State currentState;

    //text areas
    private final JTextArea tTop, tBottom;

    //operator and related buttons
    private final JButton bClear, bSwap, bEnter, bAdd, bSubtract, bMultiply,
            bDivide, bPower, bRoot;

    //digit entry buttons
    private final JButton[] bDigits = new JButton[DIGIT_BUTTONS];

    //useful constants
    private static final int TEXT_AREA_HEIGHT = 5, TEXT_AREA_WIDTH = 20,
            DIGIT_BUTTONS = 10, MAIN_BUTTON_PANEL_GRID_ROWS = 4,
            MAIN_BUTTON_PANEL_GRID_COLUMNS = 4, SIDE_BUTTON_PANEL_GRID_ROWS = 3,
            SIDE_BUTTON_PANEL_GRID_COLUMNS = 1, CALC_GRID_ROWS = 3,
            CALC_GRID_COLUMNS = 1;

    //no argument constructor
    public NNCalcView1() {

        //Create the JFrame being extended
        //JFrame constructor with string parameter
        super("Natural Number Calculator");

        //set up intitial GUI state
        this.currentState = State.SAW_CLEAR;

        //text areas
        this.tTop = new JTextArea("", TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);
        this.tBottom = new JTextArea("", TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);

        //input buttons
        this.bClear = new JButton("Clear");
        this.bSwap = new JButton("Swap");
        this.bEnter = new JButton("Enter");
        this.bAdd = new JButton("Add");
        this.bSubtract = new JButton("Subtract");
        this.bMultiply = new JButton("Multiply");
        this.bDivide = new JButton("Divide");
        this.bPower = new JButton("Power");
        this.bRoot = new JButton("Root");

        //number buttons
        this.bDigits[0] = new JButton("0");
        this.bDigits[1] = new JButton("1");
        this.bDigits[2] = new JButton("2");
        this.bDigits[3] = new JButton("3");
        this.bDigits[4] = new JButton("4");
        this.bDigits[5] = new JButton("5");
        this.bDigits[6] = new JButton("6");
        this.bDigits[7] = new JButton("7");
        this.bDigits[8] = new JButton("8");
        this.bDigits[9] = new JButton("9");

        //set text area settings
        this.tTop.setEditable(false);
        this.tTop.setLineWrap(true);
        this.tTop.setWrapStyleWord(true);
        this.tBottom.setEditable(false);
        this.tBottom.setLineWrap(true);
        this.tBottom.setWrapStyleWord(true);

        //initially disable these buttons
        this.bDivide.setEnabled(false);
        this.bRoot.setEnabled(false);

        //create scroll panes
        JScrollPane tTopScrollPane = new JScrollPane(this.tTop);
        JScrollPane tBottomScrollPane = new JScrollPane(this.tBottom);

        //main buttone panel
        JPanel mainButtonPanel = new JPanel(new GridLayout(
                MAIN_BUTTON_PANEL_GRID_ROWS, MAIN_BUTTON_PANEL_GRID_COLUMNS));

        //add buttons to the main button panel, left to right, top to bottom
        mainButtonPanel.add(this.bDigits[7]);
        mainButtonPanel.add(this.bDigits[8]);
        mainButtonPanel.add(this.bDigits[9]);
        mainButtonPanel.add(this.bAdd);
        mainButtonPanel.add(this.bDigits[4]);
        mainButtonPanel.add(this.bDigits[5]);
        mainButtonPanel.add(this.bDigits[6]);
        mainButtonPanel.add(this.bSubtract);
        mainButtonPanel.add(this.bDigits[1]);
        mainButtonPanel.add(this.bDigits[2]);
        mainButtonPanel.add(this.bDigits[3]);
        mainButtonPanel.add(this.bMultiply);
        mainButtonPanel.add(this.bDigits[0]);
        mainButtonPanel.add(this.bPower);
        mainButtonPanel.add(this.bRoot);
        mainButtonPanel.add(this.bDivide);

        //side button panel
        JPanel sideButtonPanel = new JPanel(new GridLayout(
                SIDE_BUTTON_PANEL_GRID_ROWS, SIDE_BUTTON_PANEL_GRID_COLUMNS));

        //add buttons to side panel, left to right, top to bottom
        sideButtonPanel.add(this.bClear);
        sideButtonPanel.add(this.bSwap);
        sideButtonPanel.add(this.bEnter);

        //create button panel to hold all buttons
        JPanel combinedButtonPanel = new JPanel(new FlowLayout());

        //nest other button panels in the combined one
        combinedButtonPanel.add(mainButtonPanel);
        combinedButtonPanel.add(sideButtonPanel);

        //create layout
        this.setLayout(new GridLayout(CALC_GRID_ROWS, CALC_GRID_COLUMNS));

        //add scroll panes to button panel
        this.add(tTopScrollPane);
        this.add(tBottomScrollPane);
        this.add(combinedButtonPanel);

        //register this as observer for all GUI events
        this.bDigits[0].addActionListener(this);
        this.bDigits[1].addActionListener(this);
        this.bDigits[2].addActionListener(this);
        this.bDigits[3].addActionListener(this);
        this.bDigits[4].addActionListener(this);
        this.bDigits[5].addActionListener(this);
        this.bDigits[6].addActionListener(this);
        this.bDigits[7].addActionListener(this);
        this.bDigits[8].addActionListener(this);
        this.bDigits[9].addActionListener(this);
        this.bAdd.addActionListener(this);
        this.bSubtract.addActionListener(this);
        this.bMultiply.addActionListener(this);
        this.bDivide.addActionListener(this);
        this.bEnter.addActionListener(this);
        this.bSwap.addActionListener(this);
        this.bClear.addActionListener(this);
        this.bPower.addActionListener(this);
        this.bRoot.addActionListener(this);

        //size main window, exit it on close, and is visible to user
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    @Override
    public void registerObserver(NNCalcController controller) {
        this.controller = controller;
    }

    @Override
    public void updateTopDisplay(NaturalNumber n) {
        String num = n.toString();
        this.tTop.setText(num);
    }

    @Override
    public void updateBottomDisplay(NaturalNumber n) {
        String num = n.toString();
        this.tBottom.setText(num);
    }

    @Override
    public void updateSubtractAllowed(boolean allowed) {
        this.bSubtract.setEnabled(allowed);
    }

    @Override
    public void updateDivideAllowed(boolean allowed) {
        this.bDivide.setEnabled(allowed);
    }

    @Override
    public void updatePowerAllowed(boolean allowed) {
        this.bPower.setEnabled(allowed);
    }

    @Override
    public void updateRootAllowed(boolean allowed) {
        this.bRoot.setEnabled(allowed);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        /*
         * Set cursor to indicate computation on-going; this matters only if
         * processing the event might take a noticeable amount of time as seen
         * by the user
         */
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        /*
         * Determine which event has occurred that we are being notified of by
         * this callback; in this case, the source of the event (i.e, the widget
         * calling actionPerformed) is all we need because only buttons are
         * involved here, so the event must be a button press; in each case,
         * tell the controller to do whatever is needed to update the model and
         * to refresh the view
         */
        Object source = event.getSource();
        if (source == this.bClear) {
            this.controller.processClearEvent();
            this.currentState = State.SAW_CLEAR;
        } else if (source == this.bSwap) {
            this.controller.processSwapEvent();
            this.currentState = State.SAW_ENTER_OR_SWAP;
        } else if (source == this.bEnter) {
            this.controller.processEnterEvent();
            this.currentState = State.SAW_ENTER_OR_SWAP;
        } else if (source == this.bAdd) {
            this.controller.processAddEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bSubtract) {
            this.controller.processSubtractEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bMultiply) {
            this.controller.processMultiplyEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bDivide) {
            this.controller.processDivideEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bPower) {
            this.controller.processPowerEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bRoot) {
            this.controller.processRootEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else {
            for (int i = 0; i < DIGIT_BUTTONS; i++) {
                if (source == this.bDigits[i]) {
                    switch (this.currentState) {
                        case SAW_ENTER_OR_SWAP:
                            this.controller.processClearEvent();
                            break;
                        case SAW_OTHER_OP:
                            this.controller.processEnterEvent();
                            this.controller.processClearEvent();
                            break;
                        default:
                            break;
                    }
                    this.controller.processAddNewDigitEvent(i);
                    this.currentState = State.SAW_DIGIT;
                    break;
                }
            }
        }

        //Set the cursor back to normal (because we changed it at the beginning
        //of the method body)
        this.setCursor(Cursor.getDefaultCursor());
    }

}
