#  [Basic Java](index.jsp)

## Rock Paper Scissors

Before reading this tutorial, you should know:

  * How to write, compile, and run a Java class on the command line.
  * The basics of objects, methods, variables, etc.
  * How to use String functions, including the equals() method.
  * How to take input from the user on the command line using the Scanner class.

After this tutorial, you'll be able to:

  * Write a simple game where the user plays rock-paper-scissors against the computer.

We've written a program that chooses a random number and then tells the user
whether her guesses are higher or lower than that number. This is a really
basic game, but can be expanded to do more interesting things. As more
practice, this tutorial will cover a similar game that plays rock-paper-
scissors against the user.

Just in case you never had a childhood, these are the rules to rock-paper-
scissors:

  * Two players face off.
  * Each player chooses an option between rock, paper, and scissors.
  * The players reveal their choices to one another simultaneously.
  * A winner is declared using this criteria: rock smashes scissors, scissors cut paper, and paper covers rock.
  * If both players choose the same option, it's a tie.
  * This process can be repeated multiple times, and the final winner is whoever won the most matches (for example, 2 out of 3).

In this game, the computer will choose a random option, then ask the user for
her choice, then output the winner.

### The Model

Just like with the Higher/Lower game, we'll design our game from the inside
out, starting with the model. Our model needs to be able to pick a random
option between rock, paper, and scissors. It also needs to determine who won a
game when the user makes her choice. It might also be nice for it to keep
track of how many wins and losses the user has accumulated.

    
    
    
    public class RockPaperScissorsModel {
    	
    	private int wins = 0;
    	private int losses = 0;
    	private int ties = 0;
    
    	public static enum GameOutcome{
    		WIN, LOSE, TIE;
    	}
    	
    	public GameOutcome getGameOutcome(String userChoice, String computerChoice){
    		
    		if(userChoice.equalsIgnoreCase("ROCK")){
    			
    			if(computerChoice.equalsIgnoreCase("PAPER")){
    				//rock is covered by paper
    				losses++;
    				return GameOutcome.LOSE;
    			}
    			else if(computerChoice.equalsIgnoreCase("SCISSORS")){
    				//rock smashes scissors
    				wins++;
    				return GameOutcome.WIN;
    			}
    		}
    		else if(userChoice.equalsIgnoreCase("PAPER")){
    			
    			if(computerChoice.equalsIgnoreCase("SCISSORS")){
    				//paper is cut by scissors
    				losses++;
    				return GameOutcome.LOSE;
    			}
    			else if(computerChoice.equalsIgnoreCase("ROCK")){
    				//paper covers rock
    				wins++;
    				return GameOutcome.WIN;
    			}
    		}
    		else if(userChoice.equalsIgnoreCase("SCISSORS")){
    			
    			if(computerChoice.equalsIgnoreCase("ROCK")){
    				//scissors are smashed by rock
    				losses++;
    				return GameOutcome.LOSE;
    			}
    			else if(computerChoice.equalsIgnoreCase("PAPER")){
    				//scissors cut paper
    				wins++;
    				return GameOutcome.WIN;
    			}
    		}
    		
    		//if none of the above if statements is the case, then the choices must be the same
    		ties++;
    		return GameOutcome.TIE;
    	}
    	
    	public String getRandomChoice(){
    		double d = Math.random();
    		
    		if(d < .33){
    			return "ROCK";
    		}
    		else if(d < .66){
    			return "PAPER";
    		}
    		else{
    			return "SCISSORS";
    		}
    	}
    	
    	public int getWins(){
    		return wins;
    	}
    	
    	public int getLosses(){
    		return losses;
    	}
    	
    	public int getTies(){
    		return ties;
    	}
    }
    
    

Since a game could end in three different outcomes (win, lose, or tie), we
can't use a boolean (which only holds two values, true or false) that returns
whether the player won. Instead, we use an enum that contains the values WIN,
LOSE, and TIE and return a value from that enum whenever a game is played.

The getRandomChoice() method calls the static random() function of the Math
class, which returns a random decimal number between 0 and 1. Since the number
will be less than .33 about a third of the time, between .33 and .66 another
third of the time, and above .66 the other third of the time, we can use those
ranges to create a random choice between the three options.

### The View

Now that we have a model, we can write a simple command line view for it:

    
    
    public class Main {
    	public static void main(String[] args){
    		
    		RockPaperScissorsModel model = new RockPaperScissorsModel();
    		
    		String choice = model.getRandomChoice();
    		
    		System.out.println(choice);
    		
    	}
    }
    

The only thing this code does is test whether we can create an instance of our
model and print out one random choice.

### The Controller

The next thing we have to write code for is the controller, or the user input.
Since programming should be done in small steps, let's start with a program
that plays a single game:

    
    
    import java.util.Scanner;
    
    public class Main {
    	public static void main(String[] args){
    		
    		RockPaperScissorsModel model = new RockPaperScissorsModel();
    		
    		Scanner scanner = new Scanner(System.in);
    		System.out.println("Rock, paper, scissors... shoot! (Type ROCK, PAPER, or SCISSORS)");
    		String playerChoice = scanner.nextLine().toUpperCase();
    		
    		String computerChoice = model.getRandomChoice();
    		
    		System.out.println("You chose " + playerChoice + ".");
    		System.out.println("The computer chose " + computerChoice + ".");
    		
    		RockPaperScissorsModel.GameOutcome outcome = model.getGameOutcome(playerChoice, computerChoice);
    		
    		if(outcome == RockPaperScissorsModel.GameOutcome.WIN){
    			System.out.println("You won the game!");
    		}
    		else if(outcome == RockPaperScissorsModel.GameOutcome.LOSE){
    			System.out.println("The computer won the game!");
    		}
    		else{
    			System.out.println("The game was a tie!");
    		}
    		
    		scanner.close();
    	}
    }
    
    

This code plays a single game of rock-paper-scissors. We can put this code
inside an input loop to play a series of games:

    
    
    import java.util.Scanner;
    
    public class Main {
    	public static void main(String[] args){
    
    		RockPaperScissorsModel model = new RockPaperScissorsModel();
    		Scanner scanner = new Scanner(System.in);
    
    		//input loop, play games until the user quits
    		while(true){
    			
    			System.out.println("Rock, paper, scissors... shoot! (Type ROCK, PAPER, or SCISSORS)");
    			String playerChoice = scanner.nextLine().toUpperCase();
    
    			String computerChoice = model.getRandomChoice();
    
    			System.out.println("You chose " + playerChoice + ".");
    			System.out.println("The computer chose " + computerChoice + ".");
    
    			RockPaperScissorsModel.GameOutcome outcome = model.getGameOutcome(playerChoice, computerChoice);
    
    			if(outcome == RockPaperScissorsModel.GameOutcome.WIN){
    				System.out.println("You won the game!");
    			}
    			else if(outcome == RockPaperScissorsModel.GameOutcome.LOSE){
    				System.out.println("The computer won the game!");
    			}
    			else{
    				System.out.println("The game was a tie!");
    			}
    
    			System.out.println("Wins: " + model.getWins());
    			System.out.println("Losses: " + model.getLosses());
    			System.out.println("Ties: " + model.getTies());
    
    			System.out.println("Do you want to play again? (yes/no)");
    
    			String answer = scanner.nextLine();
    			if(answer.equalsIgnoreCase("no")){
    				//user wants to quit, end the input loop
    				break;
    			}
    
    		}
    
    		scanner.close();
    	}
    }
    
    

This is our finished version of rock-paper-scissors. The game asks for user
input, chooses a random option, and determines a winner. It then allows the
user to play multiple games and keeps track of total wins, losses, and ties.

### Exercises

  * Our games are missing one important feature: error handling. What if the user misspells a word, or enters some gibberish? Check the user input every time it is read to make sure it's a reasonable input. If it is not reasonable, prompt the user to enter something else. Hint: look at how we do input loops!
  * This program's use of hard-coded Strings for rock, paper, and scissor is a pretty bad idea. Modify the above code to use an enum instead.
  * Modify the program to implement [rock-paper-scissors-lizard-Spock](http://en.wikipedia.org/wiki/Rock-paper-scissors-lizard-Spock), which has the following rules: scissors cut paper, paper covers rock, rock crushes lizard, lizard poisons Spock, Spock smashes scissors, scissors decapitate lizard, lizard eats paper, paper disproves Spock, Spock vaporizes rock, and rock crushes scissors.
  * Our algorithm that chooses a random option is actually impossible to get an advantage over: ties, wins, and losses will each happen a third of the time. However, since humans are non-random (even if you think you're making a random choice, you probably aren't), it's possible to design a smarter algorithm that outsmarts a human player. Try to implement an algorithm that wins more than 30% of its games!
  * Implement a program that plays [Morra](http://en.wikipedia.org/wiki/Morra_(game)), [Matching Pennies](http://en.wikipedia.org/wiki/Matching_pennies), or [Odds and Evens](http://en.wikipedia.org/wiki/Odd_or_Even).

###  Next: Create a graphical user interface using
[Swing](http://StaticVoidGames.com/tutorials/swing/)
