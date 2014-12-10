#  [Swing](http://StaticVoidGames.com/tutorials/swing/index)

## Rock Paper Scissors

Before reading this tutorial, you should know:

  * How to write, compile, and run a simple Swing GUI.
  * How to use nested layout managers to place multiple components in a JFrame.
  * How to use ActionListeners to detect user actions and update the GUI in response.

After this tutorial, you'll be able to:

  * Write a simple GUI game!

By now you know how to create a GUI, as well as use nested layouts to create non-trivial displays. You also know how to use ActionListeners to update the GUI in response to user actions. You've also written a [command-line version of rock-paper-scissors](http://StaticVoidGames.com/tutorials/basicJava/rockPaperScissors). Now we're going to combine all of that to create your first graphical game!

### The Model

Remember that we write games using MVC, the model-view-controller pattern. This lets us separate the game logic from how we display the game or how the user interacts wit the game. This approach makes it easier to work on one small piece at a time.

For us, the easiest place to start is the model- because we already wrote it! That's the beauty of MVC- by separating the model from how it's displayed (the view) and how the user interacts with it (the controller), we can reuse the model. In our case, we can take the model we wrote for command-line rock-paper-scissors and reuse it for our GUI rock-paper-scissors game.

As a reminder, this is the model we wrote:

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
    
This model contains all of the logic for carrying out a game of rock-paper-scissors: it generates a random choice for the computer, determines whether a game is a win, loss, or tie, and keeps track of the totals of each case.

### The View

Now that we have a model, we "just" have to decide how to display our game. We know we need 3 buttons for rock, paper, and scissors. We also know we need JLabels to display the outcome of the game (who chose what, who won) and the total wins, losses, and ties.

That's a lot of components! It's a good idea to sketch out your GUI before you start coding it. Keep in mind that there are MANY different ways to lay your components out. Here's what I came up with, but feel free to experiment and come up with your own design!

![](http://StaticVoidGames.com/tutorialsContent/swing/outline1.png)

That still might seem like a lot of components to layout in a JFrame. But we can use nested layouts to make our lives a little easier by grouping related components together.

In this image, I've highlighted different parts of the program. The outermost black line indicates the JFrame, and each colored section represents a JPanel inside the JFrame.

![](http://StaticVoidGames.com/tutorialsContent/swing/outline2.png)

We can start to see that the JFrame itself only contains three components that we need to worry about: the JPanels that will contain each individual section of the GUI. With that in mind, we can start building each section.

Notice that we aren't just diving in and trying to create the whole thing at once! Like most of programming, gui development is an iterative process. You write a little piece, test that single piece, then add another small piece and repeat the process.

With that in mind, let's start at the top, with the JLabels that display the outcome of a single game. For now we're just hardcoding all of our values. We can worry about hooking things up later, for now let's just get the layout right!

    import java.awt.BorderLayout;
    
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JPanel;
    
    public class RockPaperScissorsGui {
    
    	public static void main(String[] args){
    		
    		JFrame frame = new JFrame("Rock Paper Scissors");
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		
    		JLabel playerChoiceLabel = new JLabel("You chose rock.", JLabel.CENTER);
    		JLabel computerChoiceLabel = new JLabel("CPU chose scissors.", JLabel.CENTER);
    		JLabel outcomeLabel = new JLabel("You won!", JLabel.CENTER);
    		
    		JPanel singleGameOutcomePanel = new JPanel();
    		singleGameOutcomePanel.setLayout(new BorderLayout());
    		
    		singleGameOutcomePanel.add(playerChoiceLabel, BorderLayout.WEST);
    		singleGameOutcomePanel.add(computerChoiceLabel, BorderLayout.EAST);
    		singleGameOutcomePanel.add(outcomeLabel, BorderLayout.SOUTH);
    		
    		frame.setLayout(new BorderLayout());
    		
    		frame.add(singleGameOutcomePanel, BorderLayout.NORTH);
    
    		frame.setSize(300, 200);
    		frame.setVisible(true);
    		
    	}
    }
    
This code creates a JPanel that contains three JLabels, which are organized using a BorderLayout. That JPanel is then added to a JFrame in the BorderLayout.NORTH position. Run this code and you should see something like this:

![](http://StaticVoidGames.com/tutorialsContent/swing/rpc1.png)

We can now work on the middle JPanel that contains our buttons:

    import java.awt.BorderLayout;
    import javax.swing.BoxLayout;
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JPanel;
    
    public class RockPaperScissorsGui {
    
    	public static void main(String[] args){
    		
    		JFrame frame = new JFrame("Rock Paper Scissors");
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		
    		JLabel playerChoiceLabel = new JLabel("You chose rock.", JLabel.CENTER);
    		JLabel computerChoiceLabel = new JLabel("CPU chose scissors.", JLabel.CENTER);
    		JLabel outcomeLabel = new JLabel("You won!", JLabel.CENTER);
    		
    		JButton rockButton = new JButton("Rock");
    		JButton paperButton = new JButton("Paper");
    		JButton scissorsButton = new JButton("Scissors");
    
    		JPanel singleGameOutcomePanel = new JPanel();
    		singleGameOutcomePanel.setLayout(new BorderLayout());
    		
    		singleGameOutcomePanel.add(playerChoiceLabel, BorderLayout.WEST);
    		singleGameOutcomePanel.add(computerChoiceLabel, BorderLayout.EAST);
    		singleGameOutcomePanel.add(outcomeLabel, BorderLayout.SOUTH);
    		
    		JPanel buttonPanel = new JPanel();
    		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    	
    		buttonPanel.add(rockButton);
    		buttonPanel.add(paperButton);
    		buttonPanel.add(scissorsButton);
    				
    		frame.setLayout(new BorderLayout());
    		
    		frame.add(singleGameOutcomePanel, BorderLayout.NORTH);
    		frame.add(buttonPanel, BorderLayout.CENTER);
    		
    		frame.pack();
    		frame.setVisible(true);	
    	}
    }
    
Now our JFrame contains two JPanels: one JPanel contains the outcome of a single game, and the other contains the buttons that the user will eventually click to play. To guarantee that the buttons are placed in a single row, we use a BoxLayout to organize them. Run the program with this updated code, and you'll see something like this:

![](http://StaticVoidGames.com/tutorialsContent/swing/rps2.png)

The last thing we need to do for the layout is create the JPanel that contains the JLabels displaying the total number of wins, losses, and ties. We'll organize these into a FlowLayout.

    import java.awt.BorderLayout;
    import java.awt.FlowLayout;
    
    import javax.swing.BoxLayout;
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JPanel;
    
    public class RockPaperScissorsGui {
    
    	public static void main(String[] args){
    		
    		JFrame frame = new JFrame("Rock Paper Scissors");
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		
    		JLabel playerChoiceLabel = new JLabel("You chose rock.", JLabel.CENTER);
    		JLabel computerChoiceLabel = new JLabel("CPU chose scissors.", JLabel.CENTER);
    		JLabel outcomeLabel = new JLabel("You won!", JLabel.CENTER);
    		
    		JButton rockButton = new JButton("Rock");
    		JButton paperButton = new JButton("Paper");
    		JButton scissorsButton = new JButton("Scissors");
    		
    		JLabel winsLabel = new JLabel("Wins: 10", JLabel.CENTER);
    		JLabel lossesLabel = new JLabel("Losses: 5", JLabel.CENTER);
    		JLabel tiesLabel = new JLabel("Ties: 3", JLabel.CENTER);
    
    		//top panel
    		JPanel singleGameOutcomePanel = new JPanel();
    		singleGameOutcomePanel.setLayout(new BorderLayout());
    		
    		singleGameOutcomePanel.add(playerChoiceLabel, BorderLayout.WEST);
    		singleGameOutcomePanel.add(computerChoiceLabel, BorderLayout.EAST);
    		singleGameOutcomePanel.add(outcomeLabel, BorderLayout.SOUTH);
    		
    		//middle panel
    		JPanel buttonPanel = new JPanel();
    		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    		
    		buttonPanel.add(rockButton);
    		buttonPanel.add(paperButton);
    		buttonPanel.add(scissorsButton);
    		
    		//bottom panel
    		JPanel totalsPanel = new JPanel();
    		totalsPanel.setLayout(new FlowLayout());
    		
    		totalsPanel.add(winsLabel);
    		totalsPanel.add(lossesLabel);
    		totalsPanel.add(tiesLabel);
    		
    		frame.setLayout(new BorderLayout());
    		
    		frame.add(singleGameOutcomePanel, BorderLayout.NORTH);
    		frame.add(buttonPanel, BorderLayout.CENTER);
    		frame.add(totalsPanel, BorderLayout.SOUTH);
    		
    		frame.pack();
    		frame.setVisible(true);	
    	}
    }
    
Now if you run this updated code, you'll see our finished gui prototype:

![](http://StaticVoidGames.com/tutorialsContent/swing/rps2.png)

As you can see, we started out with a complicated design with a bunch of components, but broke those components down into individual groups that we could organize, then added each group as a single component (a JPanel). This is how pretty much all gui development works, so if you can do this, you can create anything!

### The Controller

Now we have a model and a view, but we don't have a way for the user to interact with our GUI. For that, we need to add an ActionListener to each JButton. As a first step, let's create a basic ActionListener that just prints out the player's choice:

    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import javax.swing.JButton;
    
    public class RockPaperScissorsListener implements ActionListener{
    
    	JButton rockButton;
    	JButton paperButton;
    	JButton scissorsButton;
    
    	public RockPaperScissorsListener(JButton rockButton, JButton paperButton, JButton scissorsButton){
    		
    		this.rockButton = rockButton;
    		this.paperButton = paperButton;
    		this.scissorsButton = scissorsButton;
    
    	}
    
    	public void actionPerformed(ActionEvent e) {
    		
    		String playerChoice;
    		
    		if(e.getSource() == rockButton){
    			playerChoice = "ROCK";
    		}
    		else if(e.getSource() == paperButton){
    			playerChoice = "PAPER";
    		}
    		else if(e.getSource() == scissorsButton){
    			playerChoice = "SCISSORS";
    		}
    		else{
    			//if the code gets here, something is wrong
    			playerChoice = "ERROR";
    		}
    		
    		System.out.println("You chose: " + playerChoice);
    		
    	}
    }
    
This ActionListener will be added to our three JButtons. Depending on which one is clicked, it will print out a different message. Back in our main class, we create an instance of our ActionListener, pass the three buttons into the constructor, and then add the ActionListener to each button:

    import java.awt.BorderLayout;
    import java.awt.FlowLayout;
    import java.awt.event.ActionListener;
    
    import javax.swing.BoxLayout;
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JPanel;
    
    public class RockPaperScissorsGui {
    
    	public static void main(String[] args){
    
    		JFrame frame = new JFrame("Rock Paper Scissors");
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    		JLabel playerChoiceLabel = new JLabel("You chose ??????.", JLabel.CENTER);
    		JLabel computerChoiceLabel = new JLabel("CPU chose ???????.", JLabel.CENTER);
    		JLabel outcomeLabel = new JLabel("You won/lost!", JLabel.CENTER);
    
    		JButton rockButton = new JButton("Rock");
    		JButton paperButton = new JButton("Paper");
    		JButton scissorsButton = new JButton("Scissors");
    
    		JLabel winsLabel = new JLabel("Wins: ??", JLabel.CENTER);
    		JLabel lossesLabel = new JLabel("Losses: ??", JLabel.CENTER);
    		JLabel tiesLabel = new JLabel("Ties: ??", JLabel.CENTER);
    
    		//create the listener and pass the buttons into it
    		ActionListener listener = new RockPaperScissorsListener(rockButton, paperButton, scissorsButton);
    		
    		//add the listener to the buttons
    		rockButton.addActionListener(listener);
    		paperButton.addActionListener(listener);
    		scissorsButton.addActionListener(listener);
    
    		//top panel
    		JPanel singleGameOutcomePanel = new JPanel();
    		singleGameOutcomePanel.setLayout(new BorderLayout());
    
    		singleGameOutcomePanel.add(playerChoiceLabel, BorderLayout.WEST);
    		singleGameOutcomePanel.add(computerChoiceLabel, BorderLayout.EAST);
    		singleGameOutcomePanel.add(outcomeLabel, BorderLayout.SOUTH);
    
    		//middle panel
    		JPanel buttonPanel = new JPanel();
    		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    
    		buttonPanel.add(rockButton);
    		buttonPanel.add(paperButton);
    		buttonPanel.add(scissorsButton);
    
    		//bottom panel
    		JPanel totalsPanel = new JPanel();
    		totalsPanel.setLayout(new FlowLayout());
    
    		totalsPanel.add(winsLabel);
    		totalsPanel.add(lossesLabel);
    		totalsPanel.add(tiesLabel);
    
    		frame.setLayout(new BorderLayout());
    
    		frame.add(singleGameOutcomePanel, BorderLayout.NORTH);
    		frame.add(buttonPanel, BorderLayout.CENTER);
    		frame.add(totalsPanel, BorderLayout.SOUTH);
    
    		frame.setSize(300, 150);
    		frame.setVisible(true);	
    	}
    }
    
Run this code and test it to make sure the ActionListener is working correctly.

The next step is to add our model to our controller, which just means creating an instance of the RockPaperScissorsModel we already wrote inside the RockPaperScissorsListener, and passing in the player's choice, just like we did for our command-line game!

For now, let's keep using print statements. Remember, you should always write code in small steps like this!

    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    
    import javax.swing.JButton;
    
    public class RockPaperScissorsListener implements ActionListener{
    
    	RockPaperScissorsModel model;
    
    	JButton rockButton;
    	JButton paperButton;
    	JButton scissorsButton;
    
    	public RockPaperScissorsListener(JButton rockButton, JButton paperButton, JButton scissorsButton){
    
    		this.model = new RockPaperScissorsModel();
    
    		this.rockButton = rockButton;
    		this.paperButton = paperButton;
    		this.scissorsButton = scissorsButton;
    
    	}
    
    	public void actionPerformed(ActionEvent e) {
    
    		String playerChoice;
    
    		if(e.getSource() == rockButton){
    			playerChoice = "ROCK";
    		}
    		else if(e.getSource() == paperButton){
    			playerChoice = "PAPER";
    		}
    		else if(e.getSource() == scissorsButton){
    			playerChoice = "SCISSORS";
    		}
    		else{
    			//if the code gets here, something is wrong
    			playerChoice = "ERROR";
    		}
    
    		//use the model to generate a random choice
    		String computerChoice = model.getRandomChoice();
    
    		//update the outcome labels
    		System.out.println("You chose " + playerChoice + ".");
    		System.out.println("CPU chose " + computerChoice + ".");
    
    		//who won?
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
    
    		//update the totals labels
    		System.out.println("Wins: " + model.getWins());
    		System.out.println("Losses: " + model.getLosses());
    		System.out.println("Ties: " + model.getTies());
    
    	}
    }
    
Run this updated code, and you should be see that entire games of rock-paper-scissor can be played using the buttons as input and the console as output. However, we want to use our GUI as the output, so the last thing we need to do is pass the JLabels into the ActionListener.

The final version of our ActionListener should look like this:

    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    
    import javax.swing.JButton;
    import javax.swing.JLabel;
    
    public class RockPaperScissorsListener implements ActionListener{
    
    	RockPaperScissorsModel model;
    
    	JLabel playerChoiceLabel;
    	JLabel computerChoiceLabel;
    	JLabel outcomeLabel;
    
    	JButton rockButton;
    	JButton paperButton;
    	JButton scissorsButton;
    
    	JLabel winsLabel;
    	JLabel lossesLabel;
    	JLabel tiesLabel;
    
    	public RockPaperScissorsListener(JLabel playerChoiceLabel, JLabel computerChoiceLabel, JLabel outcomeLabel,
    			JButton rockButton, JButton paperButton, JButton scissorsButton,
    			JLabel winsLabel, JLabel lossesLabel, JLabel tiesLabel){
    		
    		this.model = new RockPaperScissorsModel();
    		
    		this.playerChoiceLabel = playerChoiceLabel;
    		this.computerChoiceLabel = computerChoiceLabel;
    		this.outcomeLabel = outcomeLabel;
    		this.rockButton = rockButton;
    		this.paperButton = paperButton;
    		this.scissorsButton = scissorsButton;
    		this.winsLabel = winsLabel;
    		this.lossesLabel = lossesLabel;
    		this.tiesLabel = tiesLabel;
    		
    	}
    
    	public void actionPerformed(ActionEvent e) {
    		
    		String playerChoice;
    		
    		if(e.getSource() == rockButton){
    			playerChoice = "ROCK";
    		}
    		else if(e.getSource() == paperButton){
    			playerChoice = "PAPER";
    		}
    		else if(e.getSource() == scissorsButton){
    			playerChoice = "SCISSORS";
    		}
    		else{
    			//something is wrong
    			playerChoice = "ERROR";
    		}
    		
    		//use the model to generate a random choice
    		String computerChoice = model.getRandomChoice();
    
    		//update the outcome labels
    		playerChoiceLabel.setText("You chose " + playerChoice + ".");
    		computerChoiceLabel.setText("CPU chose " + computerChoice + ".");
    
    		//who won?
    		RockPaperScissorsModel.GameOutcome outcome = model.getGameOutcome(playerChoice, computerChoice);
    
    		if(outcome == RockPaperScissorsModel.GameOutcome.WIN){
    			outcomeLabel.setText("You won the game!");
    		}
    		else if(outcome == RockPaperScissorsModel.GameOutcome.LOSE){
    			outcomeLabel.setText("The computer won the game!");
    		}
    		else{
    			outcomeLabel.setText("The game was a tie!");
    		}
    
    		//update the totals labels
    		winsLabel.setText("Wins: " + model.getWins());
    		lossesLabel.setText("Losses: " + model.getLosses());
    		tiesLabel.setText("Ties: " + model.getTies());
    
    	}
    
    }
    
The ActionListener now uses the setText() method of our various JLabels instead of the System.out.println() method. This causes our GUI to update whenever a game is played.

Now the only thing left to do is pass the JLabels into the ActionListener. Our main method now looks like this:

    import java.awt.BorderLayout;
    import java.awt.FlowLayout;
    import java.awt.event.ActionListener;
    
    import javax.swing.Box;
    import javax.swing.BoxLayout;
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JPanel;
    
    public class RockPaperScissorsGui {
    
    	public static void main(String[] args){
    
    		JFrame frame = new JFrame("Rock Paper Scissors");
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    		JLabel playerChoiceLabel = new JLabel("", JLabel.CENTER);
    		JLabel computerChoiceLabel = new JLabel("", JLabel.CENTER);
    		JLabel outcomeLabel = new JLabel("Rock, paper, scissors... shoot!", JLabel.CENTER);
    
    		JButton rockButton = new JButton("Rock");
    		JButton paperButton = new JButton("Paper");
    		JButton scissorsButton = new JButton("Scissors");
    
    		JLabel winsLabel = new JLabel("Wins: 0", JLabel.CENTER);
    		JLabel lossesLabel = new JLabel("Losses: 0", JLabel.CENTER);
    		JLabel tiesLabel = new JLabel("Ties: 0", JLabel.CENTER);
    
    		//create the listener and pass the GUI components into it
    		ActionListener listener = new RockPaperScissorsListener(playerChoiceLabel, computerChoiceLabel, outcomeLabel, rockButton, paperButton, scissorsButton, winsLabel, lossesLabel, tiesLabel);
    		
    		//add the listener to the buttons
    		rockButton.addActionListener(listener);
    		paperButton.addActionListener(listener);
    		scissorsButton.addActionListener(listener);
    
    		//top panel
    		JPanel singleGameOutcomePanel = new JPanel();
    		singleGameOutcomePanel.setLayout(new BorderLayout());
    
    		singleGameOutcomePanel.add(playerChoiceLabel, BorderLayout.WEST);
    		singleGameOutcomePanel.add(computerChoiceLabel, BorderLayout.EAST);
    		singleGameOutcomePanel.add(outcomeLabel, BorderLayout.SOUTH);
    
    		//middle panel
    		JPanel buttonPanel = new JPanel();
    		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    
    		buttonPanel.add(Box.createGlue());
    		buttonPanel.add(rockButton);
    		buttonPanel.add(Box.createGlue());
    		buttonPanel.add(paperButton);
    		buttonPanel.add(Box.createGlue());
    		buttonPanel.add(scissorsButton);
    		buttonPanel.add(Box.createGlue());
    
    		//bottom panel
    		JPanel totalsPanel = new JPanel();
    		totalsPanel.setLayout(new FlowLayout());
    
    		totalsPanel.add(winsLabel);
    		totalsPanel.add(lossesLabel);
    		totalsPanel.add(tiesLabel);
    
    		frame.setLayout(new BorderLayout());
    
    		frame.add(singleGameOutcomePanel, BorderLayout.NORTH);
    		frame.add(buttonPanel, BorderLayout.CENTER);
    		frame.add(totalsPanel, BorderLayout.SOUTH);
    
    		frame.setSize(300, 150);
    		frame.setVisible(true);	
    	}
    }

### Exercises

  * This gui works, but it's a little boring. Spruce it up using colors, other layouts, different fonts, etc!
  * Create a GUI for your higher/lower game.
  * Modify the program to implement [rock-paper-scissors-lizard-Spock](http://en.wikipedia.org/wiki/Rock-paper-scissors-lizard-Spock), which has the following rules: scissors cut paper, paper covers rock, rock crushes lizard, lizard poisons Spock, Spock smashes scissors, scissors decapitate lizard, lizard eats paper, paper disproves Spock, Spock vaporizes rock, and rock crushes scissors.
  * Our algorithm that chooses a random option is actually impossible to get an advantage over: ties, wins, and losses will each happen a third of the time. However, since humans are non-random (even if you think you're making a random choice, you probably aren't), it's possible to design a smarter algorithm that outsmarts a human player. Try to implement an algorithm that wins more than 30% of its games!
  * Implement a GUI that plays [Morra](http://en.wikipedia.org/wiki/Morra_(game)), [Matching Pennies](http://en.wikipedia.org/wiki/Matching_pennies), or [Odds and Evens](http://en.wikipedia.org/wiki/Odd_or_Even).

###  Next: [Custom Painting](http://StaticVoidGames.com/tutorials/swing/customPainting)
