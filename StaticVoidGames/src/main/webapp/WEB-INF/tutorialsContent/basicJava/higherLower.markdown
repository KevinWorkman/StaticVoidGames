#  [Basic Java](index.jsp)

## Higher/Lower

Before reading this tutorial, you should know:

  * How to write, compile, and run a Java class on the command line.
  * The basics of objects, methods, variables, etc.
  * How to use String functions, including the equals() method.
  * How to take input from the user on the command line using the Scanner class.

After this tutorial, you'll be able to:

  * Write a simple game where the user tries to guess a number.

We've written some basic program that take user input and react to it. Now
let's finally start writing a game! Our first game will simulate a really
basic 2 player game. Player One thinks of a number, and Player Two tries to
guess it. Whenever Player Two makes a guess, Player One tells Player Two
whether the number is higher or lower than the guess. A typical game might go
like this:

  * Player One: I'm thinking of a number between 1 and 10!
  * (Player One is thinking of 8)
  * Player Two: Hmm.. Is it 5?
  * Player One: Nope, it's higher than 5!
  * Player Two: Is it 7?
  * Player One: Higher than 7!
  * Player Two: Is it 10?
  * Player One: It's lower than 10!
  * Player Two: It's 8 isn't it!
  * Player One: You got it! It only took you 4 guesses!

In our game, the computer will play the part of Player One. This game is
admittedly not very exciting, but it contains a lot of logic that we'll use as
we develop more complicated games.

### MVC

An important concept in game programming (and programming in general) is the
idea of separating the different parts of your program to help keep everything
organized and to make it easier to expand the program later. Most programs can
be split up into 3 major parts: the model (the data and logic driving the
program), the view (the display of that data), and the controller (the user
input). Splitting your program up into a model, view, and controller, or MVC,
can make your life much easier.

For our example game, the model will be an object that holds Player One's
number and the logic for comparing Player Two's guesses to it. The view and
controller are command line output and input, respectively.

### The Model

The first step is designing the model. Keep in mind that we don't have to get
it completely correct the first time. The important part is to break your
program up into smaller pieces and take those pieces on one at a time. Here's
a first stab at what the model might look like:

    
    
    
    public class HigherLowerModel {
    	
    	private int number;
    	
    	public HigherLowerModel(int number){
    		this.number = number;
    	}
    	
    	public boolean isGuessCorrect(int guess){
    		return guess == number;
    	}
    	
    	public boolean isGuessHigher(int guess){
    		return guess > number;
    	}
    	
    	public boolean isGuessLower(int guess){
    		return guess < number;
    	}
    }
    
    

The model takes a single number as an argument to its constructor, and then
offers a couple methods that determine whether a particular guess is higher,
lower, or equal to that number.

### The View

Now that we have a model, we can write a simple command line view for it:

    
    
    public class Main{
    	public static void main(String[] args){
    		
    		HigherLowerModel model = new HigherLowerModel(50);
    		
    		int guess = 75;
    		
    		System.out.println(model.isGuessCorrect(guess));	
    	}
    }
    

The only thing this code does is test whether we can create an instance of our
model, give it some hard-coded input, and print out the result of calling a
method on the model.

The next thing we have to write code for is the controller, or the user input.
Since programming should be done in small steps, let's start with an
incomplete example just to link user input and our controller:

    
    
    import java.util.Scanner;
    
    public class Main{
    	public static void main(String[] args){
    		
    		HigherLowerModel model = new HigherLowerModel(5);
    		
    		Scanner scanner = new Scanner(System.in);
    		
    		System.out.println("Guess a number between 1 and 10!");
    		
    		String input = scanner.nextLine();
    		int guess = Integer.valueOf(input);
    		
    		if(model.isGuessCorrect(guess)){
    			System.out.println("You guessed correctly. You win!");
    		}
    		else{
    			System.out.println("You guessed incorrectly. Game over.");
    		}
    		
    		scanner.close();
    	}
    }
    

This is again an incomplete program, but we're making our way towards our end
goal by taking small steps that we can easily test. This program creates an
instance of HigherLowerModel as well as an instance of Scanner. It then gets
input from the user, which it converts to a number and passes in to the model.
Next, let's use our model to determine whether the guess is higher or lower
than the number:

    
    
    import java.util.Scanner;
    
    public class Main{
    	public static void main(String[] args){
    		
    		HigherLowerModel model = new HigherLowerModel(5);
    		
    		Scanner scanner = new Scanner(System.in);
    		
    		System.out.println("Guess a number between 1 and 10!");
    		
    		String input = scanner.nextLine();
    		int guess = Integer.valueOf(input);
    		
    		if(model.isGuessCorrect(guess)){
    			System.out.println("You guessed correctly. You win!");
    		}
    		else if(model.isGuessHigher(guess)){
    			System.out.println("Your guess was too high! The number I'm thinking of is lower.");
    		}
    		else if(model.isGuessLower(guess)){
    			System.out.println("Your guess was too low! The number I'm thinking of is higher.");
    		}
    		
    		scanner.close();
    	}
    }
    

Now we're getting somewhere. Next, we put this logic inside an input loop:

    
    
    import java.util.Scanner;
    
    public class Main{
    	public static void main(String[] args){
    		
    		Scanner scanner = new Scanner(System.in);
    
    		HigherLowerModel model = new HigherLowerModel(5);
    
    		System.out.println("Guess a number between 1 and 10!");
    		System.out.println("(type 'quit' to quit)");
    		
    		int guesses = 0;
    
    		while(true){
    			String input = scanner.nextLine();
    			
    			if(input.equals("quit")){
    				System.out.println("The program will now exit.");
    				break;
    			}
    			
    			int guess = Integer.valueOf(input);
    			guesses++;
    
    			if(model.isGuessCorrect(guess)){
    				System.out.println("You guessed correctly. You win!");
    				System.out.println("It took you " + guesses + " guesses to win.");
    				break;
    			}
    			else if(model.isGuessHigher(guess)){
    				System.out.println("Your guess was too high! The number I'm thinking of is lower than " + guess + ".");
    			}
    			else if(model.isGuessLower(guess)){
    				System.out.println("Your guess was too low! The number I'm thinking of is higher than " + guess + ".");
    			}
    		}
    
    		scanner.close();
    	}
    }
    

The code now uses an input loop to continuously ask the user to guess again.
We keep track of the number of guesses the user makes, and print it out when
the user guesses correctly. We also break out of the input loop, otherwise
we'd keep asking the user to guess even after a correct guess. We also break
out of the loop if the user gets tired of playing and wants to quit.

This program plays a single round of Higher/Lower, but we can expand it to
continue playing until the user exits:

    
    
    import java.util.Scanner;
    
    public class Main{
    	public static void main(String[] args){
    		
    		Scanner scanner = new Scanner(System.in);
    
    		while(true){
    			
    			playOneGame(scanner);
    			
    			System.out.println("Play again? (type yes or no)");
    			String input = scanner.nextLine();
    			if(input.equals("no")){
    				break;
    			}
    		}
    		
    
    		scanner.close();
    	}
    	
    	public static void playOneGame(Scanner scanner){
    		
    		int low = 1;
    		int high = 10;
    		
    		int choice = (int) (low + Math.random()*((high-low+1)) );
    		
    		HigherLowerModel model = new HigherLowerModel(choice);
    
    		System.out.println("Guess a number between " + low + "  and " + high + "!");
    		System.out.println("(type 'quit' to quit)");
    		
    		int guesses = 0;
    
    		while(true){
    			String input = scanner.nextLine();
    			
    			if(input.equals("quit")){
    				System.out.println("Quitting this round.");
    				break;
    			}
    			
    			int guess = Integer.valueOf(input);
    			guesses++;
    
    			if(model.isGuessCorrect(guess)){
    				System.out.println("You guessed correctly. You win!");
    				System.out.println("It took you " + guesses + " guesses to win.");
    				break;
    			}
    			else if(model.isGuessHigher(guess)){
    				System.out.println("Your guess was too high! The number I'm thinking of is lower than " + guess + ".");
    			}
    			else if(model.isGuessLower(guess)){
    				System.out.println("Your guess was too low! The number I'm thinking of is higher than " + guess + ".");
    			}
    		}
    	}
    }
    

We've moved our logic for playing a single game into its own method. Now,
whenever we want to play a single game, we just call that method. We put that
method inside an input loop so that we keep playing until the user quits.

We also generate a random choice between 1 and 10 instead of using a hardcoded
value, so that the game is different every time it's played.

Congratulations, you just wrote your first Java game!

### Exercises

  * Modify the game so it becomes more difficult over time. Every time the user guesses correctly, increase the range. For example, the first level should choose a random number between 1 and 5; the second level between 1 and 10, the third level between 1 an 25, etc.
  * Create a version of this game where the player thinks of a number and the computer tries to guess it.

###  Next: [Rock-Paper-Scissors](RockPaperScissors.jsp)
