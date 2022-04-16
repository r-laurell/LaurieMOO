import java.text.DecimalFormat;
import java.util.Random;

/** Creates a new RandomMooValue object containing a secret value to be guessed. */
public class RandomMooValue {
    private static String secretValue = null;                                          //creates a string to send the secret value to

    /** Constructor */
    public RandomMooValue() {
        setSecretValue();
    }

    /** The getBigMooCount method stores the guess value
     * @param guess  the number that the user guessed.
     * @param secretNum the secret value number the program generates
     * @return a number 0-4 representing how many digits the user guessed correctly by position.*/
    public int getBigMooCount(String guess, String secretNum){
        int uMoo = 0;                                                                   //initializes the uppercase moo counts
        char[] numberCheck = {'x', 'x', 'x', 'x'};                                      //creates a char array to use to test the users guess and secret value

        for(int userInputDigit = 0; userInputDigit<4; userInputDigit++){                //checking that the users input is not greater than 4 digits
            for(int setSCVDigit = 0; setSCVDigit<4; setSCVDigit++) {                    //checking that the secret value digit is not greater than 4 digits
                if (guess.charAt(userInputDigit) == secretNum.charAt(setSCVDigit)){     //passing in the guess and the secretNum into the userInput and setSCV
                    if (userInputDigit == setSCVDigit){                                 //checks to see if the user Input equals the setSCVDigits
                        numberCheck[setSCVDigit] = 'M';                       //if the users guess is the same number and spot as the secret value a M replaces the x in that spot
                    }
                }
            }
        }

        for(int uMooCount = 0; uMooCount < 4; uMooCount++) {                             //creates a loop to check how many M or m there are in the users input
            if (numberCheck[uMooCount] == 'M') {                                        //checks to see how many uppercase Moos are equal to M
                uMoo++;                                                                //increases the number of uppercase moos based on uppercase Moos
            }
        }

        return uMoo;
    }
    /** The getLittleMooCount method stores the guess value
     * @param guess  the number that the user guessed.
     * @param secretNum the secret value number the program generates
     * @return a number 0-4 representing how many of the guessed digits match, but are in different
     * positions. Note that a guessed number cannot have any one digit generate both a "MOO!"
     * and a "moo." as a result.
     */
    public int getLittleMooCount(String guess, String secretNum){
        int lMoo = 0;                                                                   //initializes lMoo and sets it to zero
        char[] numberCheck = {'x', 'x', 'x', 'x'};                                      //creates a char array to use to test the users guess and secret value

        for(int userInputDigit = 0; userInputDigit < 4; userInputDigit++){                //checking that the users input is not greater than 4 digits
            for(int setSCVDigit = 0; setSCVDigit < 4; setSCVDigit++) {                    //checking that the secret value digit is not greater than 4 digits
                if (guess.charAt(userInputDigit) == secretNum.charAt(setSCVDigit)) {    //passing in the guess and the secretNum into the userInput and setSCV
                    if (userInputDigit != setSCVDigit && numberCheck[setSCVDigit] == 'x') {  //checking to see if the input does not equal the setSCV and that numberCheck array equals lowercase x
                        numberCheck[setSCVDigit] = 'm';                     //if the users guess is the same number and spot as the secret value a m replaces the x in that spot
                    }
                }
            }
        }

        for(int z = 0; z < 4; z++) {                                               //initializes z to be able to pass it in to the numberCheck to check for lowercase m
            if (numberCheck[z] == 'm') {                                           //checks to see how many z are equal to M
                lMoo++;                                                            //increases the number of lowercase moos based on z
            }
        }

        return lMoo;
    }

    /**access the secret value that the user is trying to guess primarily to show the user after running out of guesses.
     * @return the secret value that the user is/was attempting to guess.
     */
    public String getSecretValue(){
        return RandomMooValue.secretValue;
    }
    /** The isCorrectGuess method stores the guess value
     * @param uMooCount  the count of uppercase MOO.
     * @return true if the guess is correct, false otherwise
     */
    public boolean isCorrectGuess(int uMooCount){
        boolean correctGuess = false;                                                 //initializes the correctGuess as a boolean and sets it to false

        if(uMooCount == 4) {                                                          //if statement checking for 4 big MOO print outs
            correctGuess = true;                                                      //if it does equal 4 then the correctGuess with return as true
        }
        return correctGuess;
    }

    /**Generates a new secret value to be guessed in a game of LaurieMOO.
     * @param n The number that will be set as the secret value, if it is within the inclusive range of 0000-9999.
     * @return true in all cases
     */
    public boolean setSecretValueTest(String n){
        System.out.println(n);                                                       //prints to the system to show the random number for testing
        secretValue = n;                                                             //sets the secretValue to equal n
        return true;
    }

    /** Sets the "secret" value to be guessed in a game of LaurieMOO to a known 4-digit quantity. This method is for testing purposes only.
     * @return true if the secret value was reset; false if the passed value was outside of the allowed range of values.
     */
    public boolean setSecretValue(){
        Random rand = new Random();
        int upperbound = 9999;                                                       //creates the limits for the random value
        int int_random = rand.nextInt(upperbound);
        String pattern = "0000";                                                     //string to make sure that the random value always stays 4 digits
        DecimalFormat df = new DecimalFormat(pattern);                               //sets the string pattern
        String R = df.format(int_random);                                            //setting the string R to the string value to keep it 4 digits
        System.out.println(R);                                                       //prints to the system to show the random number for testing
        secretValue = R; //COMMENTED OUT FOR TESTING
        return true;
    }

}

