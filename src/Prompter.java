import java.util.Scanner;

public class Prompter {
  private Game game;

  public Prompter(Game game) {
    this.game = game;
  }

  public boolean promptForGuess() {
    Scanner scanner = new Scanner(System.in);
    boolean isHit = false;
    boolean isAcceptable = false;

    do {
      System.out.print("Enter a letter: ");
      String guessInput = scanner.nextLine();
      try {
        isHit = game.applyGuess(guessInput);
        isAcceptable = true;
      } catch(IllegalArgumentException iae) {
        System.out.printf("%s. Please tray again. %n",
                          iae.getMessage());
      }
    } while(!isAcceptable);
    return isHit;
  }

  public void displayProgress() {
    System.out.printf("%nYou have %d tries left to solve: %s%n",
                      game.getRemainingTries(),
                      game.getCurrentProgress());
  }

  public void displayOutcome() {
    if(game.isWon()) {
      System.out.printf("Congratulations. You guessed the word %s with %d tries remaining.%n",
                        game.getAnswer(),
                        game.getRemainingTries());
    } else {
      System.out.printf("Game lost. The word was %s.%n",
                       game.getAnswer());
    }
  }
}
