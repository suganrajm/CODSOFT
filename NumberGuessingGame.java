import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int totalScore = 0;
        int round = 1;
        final int MAX_ATTEMPTS = 5;

        System.out.println("🎮 Welcome to the Number Guessing Game!");

        boolean playAgain;
        do {
            int targetNumber = random.nextInt(100) + 1; // 1 to 100
            int attemptsLeft = MAX_ATTEMPTS;
            boolean guessedCorrectly = false;

            System.out.println("\n🔁 Round " + round + " - Guess a number between 1 and 100:");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess (" + attemptsLeft + " attempts left): ");
                int guess = scanner.nextInt();

                if (guess == targetNumber) {
                    System.out.println("🎉 Congratulations! You guessed it right.");
                    guessedCorrectly = true;
                    totalScore += 10; // Award points for winning a round
                    break;
                } else if (guess < targetNumber) {
                    System.out.println("📉 Too low!");
                } else {
                    System.out.println("📈 Too high!");
                }
                attemptsLeft--;
            }

            if (!guessedCorrectly) {
                System.out.println("❌ You've used all attempts. The correct number was: " + targetNumber);
            }

            System.out.println("✅ Your current score: " + totalScore);

            System.out.print("Do you want to play another round? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
            round++;

        } while (playAgain);

        System.out.println("\n🏁 Game Over. Your Final Score: " + totalScore);
        scanner.close();
    }
}
