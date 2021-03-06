import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.String;
import java.text.DecimalFormat;
import java.util.Random;

/** “LaurieMOO!”  The game is simple: you have ten attempts to guess a four-digit
 * number randomly generated by the game.  For each digit correctly specified by
 * position, you get a “MOO!” displayed by the program.  For each digit specified
 * that is not in the correct position but is in the solution, a “moo.” is
 * displayed.  You are NOT to indicate the location of the correct digits through
 * the “MOO!” or “moo.” items displayed by your program. If the number is correctly
 * guessed, then in addition to all four “MOO!” strings being displayed you also get
 * a “LaurieMOO!!!” as a reward.  If the user fails to guess the number after ten at
 * tempts, the message “Boo hoo -- no LaurieMOO.” is displayed within a message
 * dialog along with the four-digit number (so that the user knows what the answer was).  */

public class MP1Test {
    private int turnCount = 0;                                  //creates turn count and sets it to zero
    private JTextField getSecretValue;                          //field to place the secret value in
    private JButton setSecretValue;                             //button to generate the secret value
    private JTextField guessValueField;                         //where the user enters in their guess for the secret value
    private JLabel getLittleMooCount;                           //function call for the number of lowercase moos
    private JLabel getBigMooCount;                              //function call for the number of uppercase moos
    private JLabel isCorrectGuess;                              //keeps track of the number of guess made throughout the game
    private JPanel MP1;                                         //panel name for the whole project
    private JLabel gameResult;                                  //shows if the the user has won or lost the game
    private JTextField setPreviousGuess;                        //allows user to see the last guess they made
    public String SecretMooValue = null;                        //creates the random value and sets it to zero or "null"
    private RandomMooValue thisGameMoo = new RandomMooValue();

    
    /** This class calculates the guess value field and the random button or set secret value field*/
    public MP1Test() {


        /** The guess value field is set to an action listener so that it can take in the value that the user Inputs*/
        guessValueField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean corGuess = false;                                                      //creates a boolean to check for correct guess and is set to false
                gameResult.setText("Game Result:");                                            //sets the gameResult label to show Game Result on the gui
                String userInput = guessValueField.getText();                                  //gets the text that user enters in the guess value field and sets it to userInput
                if(SecretMooValue == null){                                                    //if statement for when the SecretMooValue string is equal to null
                    SecretMooValue = thisGameMoo.getSecretValue();                             //if null, sets the SecretMooValue to the getSecretValue that was generated
                }
                String setSCV = SecretMooValue;                                                //sets the secret value to the random value number

                if(userInput.length() != 4 || setSCV.length() != 4){                           //checks to make sure the user entered a 4 digit number
                    gameResult.setText("Please use 4 digit numbers");                          //if the user did not enter a 4 digit number places an error
                    return;
                }

                int lMoo = 0;                                                                   //initializes the lowercase moo counts
                int uMoo = 0;                                                                   //initializes the uppercase moo counts

                lMoo = thisGameMoo.getLittleMooCount(userInput,setSCV);                         //passes the userInput and setSCV to the lMoo
                uMoo = thisGameMoo.getBigMooCount(userInput, setSCV);                           //passes the userInput and setSCV to the uMoo


                turnCount++;                                                                   //increasing the turn count after each turn
                isCorrectGuess.setText(Integer.toString(turnCount));                           //converting turn count to a string and setting it to the correct guess class


                String strUMoo = "";                                                           //creates the uppercase moo string and setting it to a space
                String strLMoo = "";                                                           //creating the lowercase moo string and setting it to a space

                for(int x = 0; x < uMoo; x++) {                                                //creates a loop to check how many uppercase moos need to be printed
                    strUMoo = strUMoo + " Moo! ";                                              //concatenation of the strings to figure out how many big moos to print out
                }

                getBigMooCount.setText(strUMoo);                                               //setting the uppercase moos to the get big moo count class on the gui

                for(int y = 0; y < lMoo; y++) {                                                //creates a loop to check how many uppercase moos need to be printed
                    strLMoo = strLMoo + " moo ";                                               //concatenation of the strings to figure out how many little moos to print out
                }

                getLittleMooCount.setText(strLMoo);                                           //setting the lowercase moo string to the get little moo count class on the gui
                setPreviousGuess.setText(userInput);                                          //setting the users previous guess to the set previous guess text box on gui
                if(uMoo == 0 && lMoo == 0){                                                   //loop for if the user gets no correct numbers in there guess
                    JOptionPane.showMessageDialog(null, "All the user hears are cowbells");       //displays a dialog box telling the user got 0 correct
                }
                corGuess = thisGameMoo.isCorrectGuess(uMoo);                                  //pass the isCorrectGuess uMoo value into the corGuess boolean

                if(corGuess || turnCount == 10){                                              //if statement to keep track of the turn count and the corGuess
                    if(corGuess) {
                        gameResult.setText("LaurieMOO!!!");                                   //prints out when the user wins the game
                        JOptionPane.showMessageDialog( null, "LaurieMOO!!!");
                        getLittleMooCount.setText("0");                                       //sets the little moo count to zero when the user wins the game
                    }
                    else{
                        gameResult.setText("Boo hoo -- no LaurieMOO");                        //prints out when the user runs out of turns and loses the game
                        JOptionPane.showMessageDialog( null, "Boo hoo -- no LaurieMOO --  " + SecretMooValue + " was the correct number");
                    }
                    getSecretValue.setText(SecretMooValue);                                   //sets the random value number to the secret value text box
                }
            }
        });

        /** The set secret value field is used to create the random secret value for the user to guess
         * can also be used for testing to input in the number you wish to test. All you have to do is comment
         * out the setSecretValue and then uncomment the setSecretValueTest as well as change the GUI to be editable again
         * as well as commenting out line 109 on RandomMooValue class*/
        setSecretValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisGameMoo.setSecretValue();  //COMMENT OUT FOR TESTING                      //passes in the setSecretValue class
                //thisGameMoo.setSecretValueTest(getSecretValue.getText());  //FOR TESTING ONLY
                SecretMooValue = thisGameMoo.getSecretValue();                                //sets the getSecretValue into the SecretMooValue
            }
        });

    }

    public static void main(String[] args) {
        JFrame myFrame = new JFrame("MP1"); //Makes the frame
        myFrame.setContentPane(new MP1Test().MP1); //attaches the panel to the frame

        // Using java dimension, set the preferred size - both width and height
        myFrame.setPreferredSize(new Dimension(2000, 1000));

        //sets up what happens with the frame is closes
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myFrame.pack(); //put everything in the frame
        myFrame.setVisible(true); //allows us to see the frame

        //Creates the directions at the start of the program
        JOptionPane.showMessageDialog(null, """
                Hit the random button to generate the 4-digit number,
                you get 10 tries to get the number right.
                Hit enter to submit your guess.""");

    }
}
