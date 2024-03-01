import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

class GuessingGame
{
    private static final int MIN = 1;
    private static final int MAX = 10;
    private static final int COUNTING_RULE = 1;
    private static final String EXITING = "q";
    private boolean playAgain;
    private String userInput;
    private int userNumber;
    private int computerNumber;
    private final Random random;
    private int guess_counter;
    private int numbers_guessed_counter;
    private float guesses_average;

    public GuessingGame()
    {
        playAgain = true;
        userInput = null;
        userNumber = 0;
        computerNumber = 0;
        random = new Random();
        guess_counter = 0;
        numbers_guessed_counter = 0;
    }

    public boolean isPlayAgain()
    {
        return playAgain;
    }

    public void setPlayAgain(final boolean playAgain)
    {
        this.playAgain = playAgain;
    }

    public String getUserInput()
    {
        return userInput;
    }

    public void setUserInput(final String userInput)
    {
        this.userInput = userInput;
    }

    public int getUserNumber()
    {
        return userNumber;
    }

    public void setUserNumber(final int userNumber)
    {
        this.userNumber = userNumber;
    }

    public int getComputerNumber()
    {
        return computerNumber;
    }

    public void setComputerNumber()
    {
        computerNumber = random.nextInt(MAX - MIN + COUNTING_RULE) + MIN;
    }

    public int getGuess_counter()
    {
        return guess_counter;
    }

    public void increaseGuess_counter()
    {
        guess_counter++;
    }

    public int getNumbers_guessed_counter()
    {
        return numbers_guessed_counter;
    }

    public void increase_numbers_guessed_counter()
    {
        numbers_guessed_counter++;
    }

    public float getGuesses_average()
    {
        return guesses_average;
    }

    public void calculateGuesses_average()
    {
        if(numbers_guessed_counter == 0)
        {
            guesses_average = 0;
        }
        else
        {
            guesses_average = (float) guess_counter / numbers_guessed_counter;
        }
    }

    public void printGameDetails()
    {
        System.out.format("It took you %d guesses to guess %d numbers: %.1f guesses average. ",
                          guess_counter,
                          numbers_guessed_counter,
                          guesses_average);
    }

    public static void main(final String[] args) throws FileNotFoundException
    {

        final Scanner scan;
        final File f;
        final GuessingGame game1;

        f = new File("guesses.txt");
        scan = new Scanner(f);  // scan = new Scanner(System.in);
        game1 = new GuessingGame();

        game1.setComputerNumber();

        while(game1.isPlayAgain())
        {
            System.out.printf("Enter between %d and %d (%s to exit): \n", MIN,
                    MAX,
                    EXITING);

            if(scan.hasNext())
            {
                if(scan.hasNextInt())
                {
                    game1.increaseGuess_counter();
                    game1.setUserNumber(scan.nextInt());

                    if(game1.getUserNumber() == game1.getComputerNumber())
                    {
                        game1.increase_numbers_guessed_counter();
                        System.out.println("Correct!");
                        System.out.format("You have guessed: %d.\n\n",
                                          game1.getUserNumber());

                        game1.setComputerNumber();
                    }
                    else if (game1.getUserNumber() > MAX)
                    {
                        System.out.format("You have entered: %d which is larger than %d.\n",
                                          game1.getUserNumber(), MAX);
                    }
                    else if (game1.getUserNumber() < MIN)
                    {
                        System.out.format("You have entered: %d which is smaller than %d.\n",
                                          game1.getUserNumber(), MIN);
                    }
                    else if (game1.getUserNumber() < game1.getComputerNumber())
                    {
                        System.out.format("You have guessed: %d, which is smaller than the random number.\n",
                                          game1.getUserNumber());
                    }
                    else
                    {
                        System.out.format("You have guessed: %d, which is larger than the random number.\n",
                                          game1.getUserNumber());
                    }
                }
                else // something, but it's not an int
                {
                    game1.setUserInput(scan.nextLine());

                    if(game1.getUserInput().equalsIgnoreCase(EXITING))
                    {
                        game1.calculateGuesses_average();
                        game1.setPlayAgain(false);
                        System.out.println("Thanks for playing");
                        game1.printGameDetails();
                    }
                    else
                    {
                        System.err.println("Invalid input! You have typed " +
                                            game1.getUserInput());
                    }
                }
            }
        }
    }
}