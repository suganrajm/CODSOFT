🕹️ 1. Number Guessing Game

Description:
A simple interactive game where the user must guess a randomly generated number within a limited number of attempts.

Features:

Random number generation (1–100)

User input and feedback (too high/too low/correct)

Maximum attempts allowed

Option for multiple rounds

Scoring system based on correct guesses

How to Run:

javac NumberGuessingGame.java
java NumberGuessingGame

SAMPLE OUTPUT:

🎮 Welcome to the Number Guessing Game!

🔁 Round 1 - Guess a number between 1 and 100:
Enter your guess (5 attempts left): 50
📉 Too low!
Enter your guess (4 attempts left): 75
📈 Too high!
Enter your guess (3 attempts left): 65
📉 Too low!
Enter your guess (2 attempts left): 70
🎉 Congratulations! You guessed it right.
✅ Your current score: 10
Do you want to play another round? (yes/no): yes

🔁 Round 2 - Guess a number between 1 and 100:
Enter your guess (5 attempts left): 60
📉 Too low!
Enter your guess (4 attempts left): 80
📉 Too low!
Enter your guess (3 attempts left): 90
📈 Too high!
Enter your guess (2 attempts left): 85
📈 Too high!
Enter your guess (1 attempts left): 83
❌ You've used all attempts. The correct number was: 82
✅ Your current score: 10
Do you want to play another round? (yes/no): no

🏁 Game Over. Your Final Score: 10
