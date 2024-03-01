import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Guessing Game of BCIT
 *
 * @author Andres Arevalo, Yeongsuk Oh and Sam
 * @version 1.0
 */
class GuessingGame
{
    private static final int MIN = 1;
    private static final int MAX = 10;
    private static final int COUNTING_RULE = 1;
    private static final String EXITING = "q";
    private static final int MAX_ATTEMPTS = 5;
    private static final int INITIAL_ATTEMPTS = 1;
    private static final int OPTION_USE_FILE = 2;
    private static final int INFINITY_DIVISION = 0;
    private static final int DEFAULT_VALUE = 0;
    private static final int INITIAL_COMPUTER_NUMBER = 0;
    private static final int OFFSET_COMPUTER_NUMBERS = 1;
    private static final int OFFSET_ATTEMPTS_PER_GAME = 1;

    private final Random random;
    private boolean playAgain;
    private String userInput;
    private int userNumber;
    private int computerNumber;
    private int totalGuesses;
    private int attemptsPerGame;
    private int numbersGuessedCounter;
    private int computerNumbersCounter;
    private float guessPercentage;
    private float guessesAverage;

    /**
     * Constructs a GuessingGame object with initial values for game variables.
     */
    public GuessingGame()
    {
        playAgain = true;
        random = new Random();
        attemptsPerGame = INITIAL_ATTEMPTS;
        computerNumbersCounter = INITIAL_COMPUTER_NUMBER - OFFSET_COMPUTER_NUMBERS;
    }

    /**
     * Gets the counter representing the number of times the computer-generated numbers.
     * @return the current value of the computerNumbersCounter.
     */
    public int getComputerNumbersCounter()
    {
        return computerNumbersCounter;
    }

    /**
     * Gets the number of attempts made by the user.
     *
     * @return the number of attempts.
     */
    public int getAttemptsPerGame()
    {
        return attemptsPerGame;
    }

    /**
     * Sets the number of attempts made by the user.
     *
     * @param attemptsPerGame the number of attempts to set.
     */
    public void setAttemptsPerGame(final int attemptsPerGame)
    {
        this.attemptsPerGame = attemptsPerGame;
    }

    /**
     * Increases the number of attempts made by the user.
     */
    public void increaseAttemptsPerGame()
    {
        attemptsPerGame++;
    }

    /**
     * Checks if the game should be played again.
     *
     * @return true if the game should be played again, false otherwise.
     */
    public boolean isPlayAgain()
    {
        return playAgain;
    }

    /**
     * Sets the playAgain variable to control whether the game should be played again.
     *
     * @param playAgain true to play the game again, false otherwise.
     */
    public void setPlayAgain(final boolean playAgain)
    {
        this.playAgain = playAgain;
    }

    /**
     * Gets the user input (either a number or the exit command).
     *
     * @return the user input as a String.
     */
    public String getUserInput()
    {
        return userInput;
    }

    /**
     * Sets the user input.
     *
     * @param userInput the user input as a String.
     */
    public void setUserInput(final String userInput)
    {
        this.userInput = userInput;
    }

    /**
     * Gets the user's guessed number.
     *
     * @return the user's guessed number.
     */
    public int getUserNumber()
    {
        return userNumber;
    }

    /**
     * Sets the user's guessed number.
     *
     * @param userNumber the user's guessed number.
     */
    public void setUserNumber(final int userNumber)
    {
        this.userNumber = userNumber;
    }

    /**
     * Gets the randomly generated computer number.
     *
     * @return the computer-generated number.
     */
    public int getComputerNumber()
    {
        return computerNumber;
    }

    /**
     * Generates a random number and sets it as the computer-generated number.
     */
    public void generateComputerNumber()
    {
        computerNumber = random.nextInt(MAX - MIN + COUNTING_RULE) + MIN;
        computerNumbersCounter++;
    }

    /**
     * Gets the count of guesses made by the user.
     *
     * @return the count of guesses made.
     */
    public int getTotalGuesses()
    {
        return totalGuesses;
    }

    /**
     * Increases the count of guesses made by the user.
     */
    public void increaseTotalGuesses()
    {
        totalGuesses++;
    }

    /**
     * Gets the count of numbers guessed by the user.
     *
     * @return the count of numbers guessed.
     */
    public int getNumbersGuessedCounter()
    {
        return numbersGuessedCounter;
    }

    /**
     * Increases the count of numbers guessed by the user.
     */
    public void increaseNumbersGuessedCounter()
    {
        numbersGuessedCounter++;
    }

    /**
     * Gets the average number of guesses made by the user.
     *
     * @return the average number of guesses.
     */
    public float getGuessesAverage()
    {
        return guessesAverage;
    }

    /**
     * Calculates and sets the average number of guesses made by the user.
     */
    public void calculateGuessesAverage()
    {
        if(numbersGuessedCounter == INFINITY_DIVISION)
        {
            guessesAverage = DEFAULT_VALUE;
        }
        else
        {
            guessesAverage = (float) totalGuesses / numbersGuessedCounter;
        }
    }

    public float getGuessPercentage()
    {
        return guessPercentage;
    }

    public void calculateGuessPercentage()
    {
        if(computerNumbersCounter == INFINITY_DIVISION)
        {
            guessPercentage = 0;
        }
        else
        {
            guessPercentage = ((float)numbersGuessedCounter / computerNumbersCounter) * 100;
        }
    }

    /**
     * Prints game details including the number of guesses made and the average number of guesses.
     */
    public void printGameDetails()
    {
        System.out.println("\n\nGame Over");
        System.out.format("The computer picked %d different numbers.\n", computerNumbersCounter);
        System.out.format("You guessed %d of them with %d guesses.\n",
                          numbersGuessedCounter,
                          totalGuesses);
        System.out.format("You averaged %.1f guesses and guessed %.2f%% of the numbers.\n",
                          guessesAverage,
                          guessPercentage);
    }


    /**
     * The main method to run the guessing game.
     *
     * @param args Command-line arguments (not used in this implementation).
     * @throws FileNotFoundException if the file specified is not found.
     */
    public static void main(final String[] args) throws FileNotFoundException
    {

        Scanner            scan;
        final Scanner      scanUserInput;
        final File         f;
        final GuessingGame game1;
        final String       fileName;

        scanUserInput = new Scanner(System.in);

        System.out.println("""
                                   Do you want to (default type answers):\s
                                   1) Type answers
                                   2) Use a file to input answers""");

        if(scanUserInput.nextInt() == OPTION_USE_FILE)
        {
            System.out.print("Type name of the file: ");
            fileName = scanUserInput.next();

            try
            {
                f = new File(fileName);
                scan = new Scanner(f);
            }
            catch(Exception e)
            {
                System.out.println("Invalid file name switching to default option");
                scan = new Scanner(System.in);
            }
        }
        else
        {
            scan = new Scanner(System.in);
        }

        game1 = new GuessingGame();
        game1.generateComputerNumber();

        while(game1.isPlayAgain())
        {
            System.out.printf("Enter between %d and %d (%s to exit): (attempts %d of %d): ",
                              MIN,
                              MAX,
                              EXITING,
                              game1.getAttemptsPerGame(),
                              MAX_ATTEMPTS);

            if(scan.hasNext())
            {
                if(scan.hasNextInt())
                {
                    game1.increaseTotalGuesses();
                    game1.setUserNumber(scan.nextInt());

                    if(game1.getUserNumber() == game1.getComputerNumber())
                    {
                        game1.increaseNumbersGuessedCounter();
                        System.out.println("Correct!");
                        System.out.format("You have guessed: %d.\n\n", game1.getUserNumber());

                        game1.generateComputerNumber();
                        game1.setAttemptsPerGame(INITIAL_ATTEMPTS - OFFSET_ATTEMPTS_PER_GAME);
                    }
                    else if(game1.getUserNumber() > MAX)
                    {
                        System.out.format("You have entered: %d which is larger than %d.\n", game1.getUserNumber(),
                                          MAX);
                    }
                    else if(game1.getUserNumber() < MIN)
                    {
                        System.out.format("You have entered: %d which is smaller than %d.\n", game1.getUserNumber(),
                                          MIN);
                    }
                    else if(game1.getUserNumber() < game1.getComputerNumber())
                    {
                        System.out.format("You have guessed: %d, which is smaller than the random number.\n",
                                          game1.getUserNumber());
                    }
                    else
                    {
                        System.out.format("You have guessed: %d, which is larger than the random number.\n",
                                          game1.getUserNumber());
                    }

                    if(game1.getAttemptsPerGame() == MAX_ATTEMPTS)
                    {
                        System.out.println("Wrong!");
                        System.out.format("The number to guess was %d, generating new number\n", game1.getComputerNumber());
                        game1.setAttemptsPerGame(INITIAL_ATTEMPTS);
                        game1.generateComputerNumber();
                    }
                    else
                    {
                        game1.increaseAttemptsPerGame();
                    }
                }
                else // something, but it's not an int
                {
                    game1.setUserInput(scan.nextLine());

                    if(game1.getUserInput().equalsIgnoreCase(EXITING))
                    {
                        game1.setPlayAgain(false);

                        game1.calculateGuessPercentage();
                        game1.calculateGuessesAverage();
                        game1.printGameDetails();
                    }
                    else
                    {
                        System.out.println("Invalid input! You have typed " + game1.getUserInput());
                    }
                }
            }
        }
    }
}