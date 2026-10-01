import java.util.Random;
import java.util.Scanner;

public class LuckyNumber {
    private static final int MAX_NUMBER = 20;
    private static final int MAX_ATTEMPTS = 6;

    public static void main(String[] args) {
        Random random = new Random();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Lucky Number ===");
            System.out.println("Guess the secret number from 1 to " + MAX_NUMBER + "!");

            boolean playAgain = true;
            while (playAgain) {
                playRound(scanner, random);
                playAgain = askToPlayAgain(scanner);
            }

            System.out.println("Thanks for playing!");
        }
    }

    private static void playRound(Scanner scanner, Random random) {
        int secretNumber = random.nextInt(MAX_NUMBER) + 1;
        System.out.println("\nI picked a number. You have " + MAX_ATTEMPTS + " tries.");

        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            int guess = readGuess(scanner, attempt);
            if (guess == -1) {
                return;
            }

            if (guess == secretNumber) {
                System.out.println("You got it in " + attempt + (attempt == 1 ? " try!" : " tries!"));
                return;
            }

            if (guess < secretNumber) {
                System.out.println("Too low!");
            } else {
                System.out.println("Too high!");
            }
        }

        System.out.println("Out of tries! The number was " + secretNumber + ".");
    }

    private static int readGuess(Scanner scanner, int attempt) {
        while (true) {
            System.out.print("Try " + attempt + "/" + MAX_ATTEMPTS + " - enter a number (1-" + MAX_NUMBER + "): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("q")) {
                return -1;
            }

            try {
                int guess = Integer.parseInt(input);
                if (guess >= 1 && guess <= MAX_NUMBER) {
                    return guess;
                }
            } catch (NumberFormatException ignored) {
            }

            System.out.println("Please enter a whole number from 1 to " + MAX_NUMBER + ", or q to quit the round.");
        }
    }

    private static boolean askToPlayAgain(Scanner scanner) {
        while (true) {
            System.out.print("Play again? (y/n): ");
            String answer = scanner.nextLine().trim();
            if (answer.equalsIgnoreCase("y")) {
                return true;
            }
            if (answer.equalsIgnoreCase("n")) {
                return false;
            }
            System.out.println("Please enter y or n.");
        }
    }
}
