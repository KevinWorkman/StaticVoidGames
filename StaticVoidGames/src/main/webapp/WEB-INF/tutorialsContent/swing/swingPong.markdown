#  [Swing](index.jsp)

## Swing Pong

Before reading this tutorial, you should know how to:

  * Write, compile, and run a Swing GUI.
  * Use KeyListeners to respond to user input.
  * Create a component that performs custom painting.
  * Use Swing Timers to create animations.
  * Implement multiple interfaces to create a component that listens to its own events.

After this tutorial, you'll be able to:

  * Write a basic animated game using Swing.

So far we've covered a lot of the basics (and not so basics) in Swing:
creating components, responding to user input, performing custom painting, and
drawing animations. We can now combine all of these ideas (as well as some of
the concepts we learned in the Processing tutorials) to make our first
animated game!

We know we're going to write a Main class, and that won't change at all, so
let's start with that:

    
    
    import java.awt.BorderLayout;
    
    import javax.swing.JFrame;
    
    public class Main{
     
        public static void main(String[] args) {
        
        	JFrame frame = new JFrame("Pong");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	frame.setLayout(new BorderLayout());
        	
        	PongPanel pongPanel = new PongPanel();
        	frame.add(pongPanel, BorderLayout.CENTER);
        	
        	frame.setSize(500, 500);
        	frame.setVisible(true);
        	
        }
    }
    

This code should look pretty familiar by now. All we do is create a JFrame,
add a PongPanel to it, and show the JFrame. Now all we need to do is create
our PongPanel class. Remember that the best way to program is by breaking your
problem down into smaller pieces and doing them one at a time!

We start by just making a class skeleton. We know we have to extend JPanel and
override the paintComponent() method, so let's get that out of the way:

    
    
    import java.awt.Color;
    import java.awt.Graphics;
    
    import javax.swing.JPanel;
    
    public class PongPanel extends JPanel{
    	
    	//construct a PongPanel
    	public PongPanel(){
    		setBackground(Color.BLACK);
    	}
    	
    	//paint a ball
    	public void paintComponent(Graphics g){
    		
    		super.paintComponent(g);
    		g.setColor(Color.WHITE);
    		
    		g.fillOval(250, 250, 20, 20);
    	}
    	
    }
    
    

This code doesn't look like much, but it's always a good idea to start small
and test often. Compile and run the code we have so far to make sure it all
works so far!

Now that we have a ball displayed, we can make it bounce around the screen. We
do that by using a Swing Timer, and we can keep it all contained in our
PongPanel class using the techniques we learned in the last tutorial.

    
    
    import java.awt.Color;
    import java.awt.Graphics;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    
    import javax.swing.JPanel;
    import javax.swing.Timer;
    
    public class PongPanel extends JPanel implements ActionListener{
    
    	private int ballX = 250;
    	private int ballY = 250;
    	private int diameter = 20;
    	private int ballDeltaX = -1;
    	private int ballDeltaY = 3;
    
    	//construct a PongPanel
    	public PongPanel(){
    		setBackground(Color.BLACK);
    		
    		//call step() 60 fps
    		Timer timer = new Timer(1000/60, this);
    		timer.start();
    	}
    
    
    	public void actionPerformed(ActionEvent e){
    		step();
    	}
    
    	public void step(){
    
    		//where will the ball be after it moves?
    		int nextBallLeft = ballX + ballDeltaX;
    		int nextBallRight = ballX + diameter + ballDeltaX;
    		int nextBallTop = ballY + ballDeltaY;
    		int nextBallBottom = ballY + diameter + ballDeltaY;
    
    
    		//ball bounces off top and bottom of screen
    		if (nextBallTop < 0 || nextBallBottom > getHeight()) {
    			ballDeltaY *= -1;
    		}
    
    		//will the ball go off the left side?
    		if (nextBallLeft < 0) { 
    			ballDeltaX *= -1;
    		}
    
    		//will the ball go off the right side?
    		if (nextBallRight > getWidth()) { 
    			ballDeltaX *= -1;
    		}
    		
    		//move the ball
    		ballX += ballDeltaX;
    		ballY += ballDeltaY;
    		
    		//stuff has moved, tell this JPanel to repaint itself
    		repaint();
    	}
    
    	//paint the ball
    	public void paintComponent(Graphics g){
    
    		super.paintComponent(g);
    		g.setColor(Color.WHITE);
    
    		g.fillOval(ballX, ballY, diameter, diameter);
    	}
    
    }
    

We've added some variables for the ball position and speed (delta means
"change in", so "delta Y" means "change in Y"), and we implemented the
ActionListener interface so the BallPanel can passed into a Timer. That Timer
calls actionPerformed() 60 times per second, which calls the step() function,
which moves our ball and does collision detection.

If any of this is unfamiliar, I recommend going back and looking at the
Processing tutorials, where we covered things like collision detection in more
detail.

Now that we have a ball bouncing around the screen, it makes sense to add a
paddle next.

    
    
    import java.awt.Color;
    import java.awt.Graphics;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
    
    import javax.swing.JPanel;
    import javax.swing.Timer;
    
    public class PongPanel extends JPanel implements ActionListener, KeyListener{
    	
    	private boolean upPressed = false;
    	private boolean downPressed = false;
    
    	private int ballX = 250;
    	private int ballY = 250;
    	private int diameter = 20;
    	private int ballDeltaX = -1;
    	private int ballDeltaY = 3;
    	
    	private int playerOneX = 25;
    	private int playerOneY = 250;
    	private int playerOneWidth = 10;
    	private int playerOneHeight = 50;
    	
    	private int paddleSpeed = 5;
    	
    
    	//construct a PongPanel
    	public PongPanel(){
    		setBackground(Color.BLACK);
    		
    		//listen to key presses
    		setFocusable(true);
    		addKeyListener(this);
    		
    		//call step() 60 fps
    		Timer timer = new Timer(1000/60, this);
    		timer.start();
    	}
    
    
    	public void actionPerformed(ActionEvent e){
    		step();
    	}
    
    	public void step(){
    		
    		if (upPressed) {
    			if (playerOneY-paddleSpeed > 0) {
    				playerOneY -= paddleSpeed;
    			}
    		}
    		if (downPressed) {
    			if (playerOneY + paddleSpeed + playerOneHeight < getHeight()) {
    				playerOneY += paddleSpeed;
    			}
    		}
    		
    		
    
    		//where will the ball be after it moves?
    		int nextBallLeft = ballX + ballDeltaX;
    		int nextBallRight = ballX + diameter + ballDeltaX;
    		int nextBallTop = ballY + ballDeltaY;
    		int nextBallBottom = ballY + diameter + ballDeltaY;
    		
    		int playerOneRight = playerOneX + playerOneWidth;
    		int playerOneTop = playerOneY;
    		int playerOneBottom = playerOneY + playerOneHeight;
    
    
    		//ball bounces off top and bottom of screen
    		if (nextBallTop < 0 || nextBallBottom > getHeight()) {
    			ballDeltaY *= -1;
    		}
    
    		//will the ball go off the left side?
    		if (nextBallLeft < playerOneRight) { 
    			//is it going to miss the paddle?
    			if (nextBallTop > playerOneBottom || nextBallBottom < playerOneTop) {
    				
    				System.out.println("PLAYER TWO SCORED");
    
    				ballX = 250;
    				ballY = 250;
    			}
    			else {
    				ballDeltaX *= -1;
    			}
    		}
    
    		//will the ball go off the right side?
    		if (nextBallRight > getWidth()) { 
    			ballDeltaX *= -1;
    		}
    		
    		//move the ball
    		ballX += ballDeltaX;
    		ballY += ballDeltaY;
    		
    		//stuff has moved, tell this JPanel to repaint itself
    		repaint();
    	}
    
    	//paint the ball and paddle
    	public void paintComponent(Graphics g){
    
    		super.paintComponent(g);
    		g.setColor(Color.WHITE);
    
    		g.fillOval(ballX, ballY, diameter, diameter);
    		
    		g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
    	}
    
    
    	
    	public void keyTyped(KeyEvent e) {}
    
    
    	
    	public void keyPressed(KeyEvent e) {
    		if (e.getKeyCode() == KeyEvent.VK_UP) {
    			upPressed = true;
    		}
    		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
    			downPressed = true;
    		}
    	}
    
    
    	public void keyReleased(KeyEvent e) {
    		if (e.getKeyCode() == KeyEvent.VK_UP) {
    			upPressed = false;
    		}
    		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
    			downPressed = false;
    		}
    	}
    
    }
    
    

We've now added variables to keep track of the left paddle as well as whether
the up and down arrow keys are pressed. We've also implemented the KeyListener
interface and defined the keyPressed() and keyReleased() functions to modify
those booleans appropriately. We added collision detection between the paddle
and the top and bottom of the screen, and we also tell the difference between
the ball hitting the paddle and the ball scoring a point. We don't actually
keep track of points yet.

The next thing to do is add the other paddle, which we do pretty much exactly
how we added the first one:

    
    
    import java.awt.Color;
    import java.awt.Graphics;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
    
    import javax.swing.JPanel;
    import javax.swing.Timer;
    
    public class PongPanel extends JPanel implements ActionListener, KeyListener{
    	
    	private boolean upPressed = false;
    	private boolean downPressed = false;
    	private boolean wPressed = false;
    	private boolean sPressed = false;
    
    	private int ballX = 250;
    	private int ballY = 250;
    	private int diameter = 20;
    	private int ballDeltaX = -1;
    	private int ballDeltaY = 3;
    	
    	private int playerOneX = 25;
    	private int playerOneY = 250;
    	private int playerOneWidth = 10;
    	private int playerOneHeight = 50;
    	
    	private int playerTwoX = 465;
    	private int playerTwoY = 250;
    	private int playerTwoWidth = 10;
    	private int playerTwoHeight = 50;
    	
    	private int paddleSpeed = 5;
    	
    
    	//construct a PongPanel
    	public PongPanel(){
    		setBackground(Color.BLACK);
    		
    		//listen to key presses
    		setFocusable(true);
    		addKeyListener(this);
    		
    		//call step() 60 fps
    		Timer timer = new Timer(1000/60, this);
    		timer.start();
    	}
    
    
    	public void actionPerformed(ActionEvent e){
    		step();
    	}
    
    	public void step(){
    		
    		//move player 1
    		if (upPressed) {
    			if (playerOneY-paddleSpeed > 0) {
    				playerOneY -= paddleSpeed;
    			}
    		}
    		if (downPressed) {
    			if (playerOneY + paddleSpeed + playerOneHeight < getHeight()) {
    				playerOneY += paddleSpeed;
    			}
    		}
    		
    		//move player 2
    		if (wPressed) {
    			if (playerTwoY-paddleSpeed > 0) {
    				playerTwoY -= paddleSpeed;
    			}
    		}
    		if (sPressed) {
    			if (playerTwoY + paddleSpeed + playerTwoHeight < getHeight()) {
    				playerTwoY += paddleSpeed;
    			}
    		}
    		
    		
    
    		//where will the ball be after it moves?
    		int nextBallLeft = ballX + ballDeltaX;
    		int nextBallRight = ballX + diameter + ballDeltaX;
    		int nextBallTop = ballY + ballDeltaY;
    		int nextBallBottom = ballY + diameter + ballDeltaY;
    		
    		int playerOneRight = playerOneX + playerOneWidth;
    		int playerOneTop = playerOneY;
    		int playerOneBottom = playerOneY + playerOneHeight;
    		
    		float playerTwoLeft = playerTwoX;
    		float playerTwoTop = playerTwoY;
    		float playerTwoBottom = playerTwoY + playerTwoHeight;
    
    
    		//ball bounces off top and bottom of screen
    		if (nextBallTop < 0 || nextBallBottom > getHeight()) {
    			ballDeltaY *= -1;
    		}
    
    		//will the ball go off the left side?
    		if (nextBallLeft < playerOneRight) { 
    			//is it going to miss the paddle?
    			if (nextBallTop > playerOneBottom || nextBallBottom < playerOneTop) {
    				
    				System.out.println("PLAYER TWO SCORED");
    
    				ballX = 250;
    				ballY = 250;
    			}
    			else {
    				ballDeltaX *= -1;
    			}
    		}
    
    		//will the ball go off the right side?
    		if (nextBallRight > playerTwoLeft) {
    			//is it going to miss the paddle?
    			if (nextBallTop > playerTwoBottom || nextBallBottom < playerTwoTop) {
    				
    				System.out.println("PLAYER ONE SCORED");
    
    				ballX = 250;
    				ballY = 250;
    			}
    			else {
    				ballDeltaX *= -1;
    			}
    		}
    		
    		//move the ball
    		ballX += ballDeltaX;
    		ballY += ballDeltaY;
    		
    		//stuff has moved, tell this JPanel to repaint itself
    		repaint();
    	}
    
    	//paint the ball and paddle
    	public void paintComponent(Graphics g){
    
    		super.paintComponent(g);
    		g.setColor(Color.WHITE);
    
    		g.fillOval(ballX, ballY, diameter, diameter);
    		
    		g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
    		g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
    	}
    
    
    	
    	public void keyTyped(KeyEvent e) {}
    
    
    	
    	public void keyPressed(KeyEvent e) {
    		if (e.getKeyCode() == KeyEvent.VK_UP) {
    			upPressed = true;
    		}
    		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
    			downPressed = true;
    		}
    		else if (e.getKeyCode() == KeyEvent.VK_W) {
    			wPressed = true;
    		}
    		else if (e.getKeyCode() == KeyEvent.VK_S) {
    			sPressed = true;
    		}
    	}
    
    
    	public void keyReleased(KeyEvent e) {
    		if (e.getKeyCode() == KeyEvent.VK_UP) {
    			upPressed = false;
    		}
    		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
    			downPressed = false;
    		}
    		else if (e.getKeyCode() == KeyEvent.VK_W) {
    			wPressed = false;
    		}
    		else if (e.getKeyCode() == KeyEvent.VK_S) {
    			sPressed = false;
    		}
    	}
    
    }
    

We've simply mirrored our code for the left paddle to create another paddle.
Compile and run this code to make sure it still works! Now that we have both
paddles moving around the screen, we can keep track of each player's score.

    
    
    import java.awt.Color;
    import java.awt.Font;
    import java.awt.Graphics;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
    
    import javax.swing.JPanel;
    import javax.swing.Timer;
    
    public class PongPanel extends JPanel implements ActionListener, KeyListener{
    	
    	private boolean upPressed = false;
    	private boolean downPressed = false;
    	private boolean wPressed = false;
    	private boolean sPressed = false;
    
    	private int ballX = 250;
    	private int ballY = 250;
    	private int diameter = 20;
    	private int ballDeltaX = -1;
    	private int ballDeltaY = 3;
    	
    	private int playerOneX = 25;
    	private int playerOneY = 250;
    	private int playerOneWidth = 10;
    	private int playerOneHeight = 50;
    	
    	private int playerTwoX = 465;
    	private int playerTwoY = 250;
    	private int playerTwoWidth = 10;
    	private int playerTwoHeight = 50;
    	
    	private int paddleSpeed = 5;
    	
    	private int playerOneScore = 0;
    	private int playerTwoScore = 0;
    	
    
    	//construct a PongPanel
    	public PongPanel(){
    		setBackground(Color.BLACK);
    		
    		//listen to key presses
    		setFocusable(true);
    		addKeyListener(this);
    		
    		//call step() 60 fps
    		Timer timer = new Timer(1000/60, this);
    		timer.start();
    	}
    
    
    	public void actionPerformed(ActionEvent e){
    		step();
    	}
    
    	public void step(){
    		
    		//move player 1
    		if (upPressed) {
    			if (playerOneY-paddleSpeed > 0) {
    				playerOneY -= paddleSpeed;
    			}
    		}
    		if (downPressed) {
    			if (playerOneY + paddleSpeed + playerOneHeight < getHeight()) {
    				playerOneY += paddleSpeed;
    			}
    		}
    		
    		//move player 2
    		if (wPressed) {
    			if (playerTwoY-paddleSpeed > 0) {
    				playerTwoY -= paddleSpeed;
    			}
    		}
    		if (sPressed) {
    			if (playerTwoY + paddleSpeed + playerTwoHeight < getHeight()) {
    				playerTwoY += paddleSpeed;
    			}
    		}
    		
    		
    
    		//where will the ball be after it moves?
    		int nextBallLeft = ballX + ballDeltaX;
    		int nextBallRight = ballX + diameter + ballDeltaX;
    		int nextBallTop = ballY + ballDeltaY;
    		int nextBallBottom = ballY + diameter + ballDeltaY;
    		
    		int playerOneRight = playerOneX + playerOneWidth;
    		int playerOneTop = playerOneY;
    		int playerOneBottom = playerOneY + playerOneHeight;
    		
    		float playerTwoLeft = playerTwoX;
    		float playerTwoTop = playerTwoY;
    		float playerTwoBottom = playerTwoY + playerTwoHeight;
    
    
    		//ball bounces off top and bottom of screen
    		if (nextBallTop < 0 || nextBallBottom > getHeight()) {
    			ballDeltaY *= -1;
    		}
    
    		//will the ball go off the left side?
    		if (nextBallLeft < playerOneRight) { 
    			//is it going to miss the paddle?
    			if (nextBallTop > playerOneBottom || nextBallBottom < playerOneTop) {
    				
    				playerTwoScore ++;
    
    				ballX = 250;
    				ballY = 250;
    			}
    			else {
    				ballDeltaX *= -1;
    			}
    		}
    
    		//will the ball go off the right side?
    		if (nextBallRight > playerTwoLeft) {
    			//is it going to miss the paddle?
    			if (nextBallTop > playerTwoBottom || nextBallBottom < playerTwoTop) {
    				
    				playerOneScore ++;
    
    				ballX = 250;
    				ballY = 250;
    			}
    			else {
    				ballDeltaX *= -1;
    			}
    		}
    		
    		//move the ball
    		ballX += ballDeltaX;
    		ballY += ballDeltaY;
    		
    		//stuff has moved, tell this JPanel to repaint itself
    		repaint();
    	}
    
    	//paint the game screen
    	public void paintComponent(Graphics g){
    
    		super.paintComponent(g);
    		g.setColor(Color.WHITE);
    
    		int playerOneRight = playerOneX + playerOneWidth;
    		int playerTwoLeft =  playerTwoX;
    		
    		//draw dashed line down center
    		for (int lineY = 0; lineY < getHeight(); lineY += 50) {
    			g.drawLine(250, lineY, 250, lineY+25);
    		}
    		
    		//draw "goal lines" on each side
    		g.drawLine(playerOneRight, 0, playerOneRight, getHeight());
    		g.drawLine(playerTwoLeft, 0, playerTwoLeft, getHeight());
    
    		//draw the scores
    		g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
    		g.drawString(String.valueOf(playerOneScore), 100, 100);
    		g.drawString(String.valueOf(playerTwoScore), 400, 100);
    		
    		//draw the ball
    		g.fillOval(ballX, ballY, diameter, diameter);
    		
    		//draw the paddles
    		g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
    		g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
    	}
    
    
    	
    	public void keyTyped(KeyEvent e) {}
    
    
    	
    	public void keyPressed(KeyEvent e) {
    		if (e.getKeyCode() == KeyEvent.VK_UP) {
    			upPressed = true;
    		}
    		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
    			downPressed = true;
    		}
    		else if (e.getKeyCode() == KeyEvent.VK_W) {
    			wPressed = true;
    		}
    		else if (e.getKeyCode() == KeyEvent.VK_S) {
    			sPressed = true;
    		}
    	}
    
    
    	public void keyReleased(KeyEvent e) {
    		if (e.getKeyCode() == KeyEvent.VK_UP) {
    			upPressed = false;
    		}
    		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
    			downPressed = false;
    		}
    		else if (e.getKeyCode() == KeyEvent.VK_W) {
    			wPressed = false;
    		}
    		else if (e.getKeyCode() == KeyEvent.VK_S) {
    			sPressed = false;
    		}
    	}
    
    }
    

We've now added variables to keep track of each player's score, and we
increment the correct one when the ball gets passed a paddle. We now paint the
scores on the background of the game, and we've added a few lines to spruce
the game screen up a bit.

We now have a working pong prototype, but it just has one small problem: it
never ends. For that matter, it doesn't really begin either; it just starts
right away!

Most games you have are going to have a beginning (a title screen), a middle
(the playing part, which we've done), and an ending (a game over). Many new
programmers skip the beginning and end parts, but adding them can really make
your game feel like a real game!

There are many different ways to make sure your game has a beginning, middle,
and end, but we'll take the same very simply approach we took in the
Processing tutorials.

    
    
    import java.awt.Color;
    import java.awt.Font;
    import java.awt.Graphics;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
    
    import javax.swing.JPanel;
    import javax.swing.Timer;
    
    public class PongPanel extends JPanel implements ActionListener, KeyListener{
    
    	private boolean showTitleScreen = true;
    	private boolean playing = false;
    	private boolean gameOver = false;
    
    	private boolean upPressed = false;
    	private boolean downPressed = false;
    	private boolean wPressed = false;
    	private boolean sPressed = false;
    
    	private int ballX = 250;
    	private int ballY = 250;
    	private int diameter = 20;
    	private int ballDeltaX = -1;
    	private int ballDeltaY = 3;
    
    	private int playerOneX = 25;
    	private int playerOneY = 250;
    	private int playerOneWidth = 10;
    	private int playerOneHeight = 50;
    
    	private int playerTwoX = 465;
    	private int playerTwoY = 250;
    	private int playerTwoWidth = 10;
    	private int playerTwoHeight = 50;
    
    	private int paddleSpeed = 5;
    
    	private int playerOneScore = 0;
    	private int playerTwoScore = 0;
    
    
    	//construct a PongPanel
    	public PongPanel(){
    		setBackground(Color.BLACK);
    
    		//listen to key presses
    		setFocusable(true);
    		addKeyListener(this);
    
    		//call step() 60 fps
    		Timer timer = new Timer(1000/60, this);
    		timer.start();
    	}
    
    
    	public void actionPerformed(ActionEvent e){
    		step();
    	}
    
    	public void step(){
    
    		if(playing){
    			//move player 1
    			if (upPressed) {
    				if (playerOneY-paddleSpeed > 0) {
    					playerOneY -= paddleSpeed;
    				}
    			}
    			if (downPressed) {
    				if (playerOneY + paddleSpeed + playerOneHeight < getHeight()) {
    					playerOneY += paddleSpeed;
    				}
    			}
    
    			//move player 2
    			if (wPressed) {
    				if (playerTwoY-paddleSpeed > 0) {
    					playerTwoY -= paddleSpeed;
    				}
    			}
    			if (sPressed) {
    				if (playerTwoY + paddleSpeed + playerTwoHeight < getHeight()) {
    					playerTwoY += paddleSpeed;
    				}
    			}
    
    
    
    			//where will the ball be after it moves?
    			int nextBallLeft = ballX + ballDeltaX;
    			int nextBallRight = ballX + diameter + ballDeltaX;
    			int nextBallTop = ballY + ballDeltaY;
    			int nextBallBottom = ballY + diameter + ballDeltaY;
    
    			int playerOneRight = playerOneX + playerOneWidth;
    			int playerOneTop = playerOneY;
    			int playerOneBottom = playerOneY + playerOneHeight;
    
    			float playerTwoLeft = playerTwoX;
    			float playerTwoTop = playerTwoY;
    			float playerTwoBottom = playerTwoY + playerTwoHeight;
    
    
    			//ball bounces off top and bottom of screen
    			if (nextBallTop < 0 || nextBallBottom > getHeight()) {
    				ballDeltaY *= -1;
    			}
    
    			//will the ball go off the left side?
    			if (nextBallLeft < playerOneRight) { 
    				//is it going to miss the paddle?
    				if (nextBallTop > playerOneBottom || nextBallBottom < playerOneTop) {
    
    					playerTwoScore ++;
    					
    					if (playerTwoScore == 3) {
    						playing = false;
    						gameOver = true;
    					}
    
    					ballX = 250;
    					ballY = 250;
    				}
    				else {
    					ballDeltaX *= -1;
    				}
    			}
    
    			//will the ball go off the right side?
    			if (nextBallRight > playerTwoLeft) {
    				//is it going to miss the paddle?
    				if (nextBallTop > playerTwoBottom || nextBallBottom < playerTwoTop) {
    
    					playerOneScore ++;
    					
    					if (playerOneScore == 3) {
    						playing = false;
    						gameOver = true;
    					}
    
    					ballX = 250;
    					ballY = 250;
    				}
    				else {
    					ballDeltaX *= -1;
    				}
    			}
    
    			//move the ball
    			ballX += ballDeltaX;
    			ballY += ballDeltaY;
    		}
    
    		//stuff has moved, tell this JPanel to repaint itself
    		repaint();
    	}
    
    	//paint the game screen
    	public void paintComponent(Graphics g){
    
    		super.paintComponent(g);
    		g.setColor(Color.WHITE);
    
    		if (showTitleScreen) {
    
    			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
    			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
    			g.drawString("Pong", 165, 100);
    
    			g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
    
    			g.drawString("Press 'P' to play.", 175, 400);
    		}
    		else if (playing) {
    
    
    			int playerOneRight = playerOneX + playerOneWidth;
    			int playerTwoLeft =  playerTwoX;
    
    			//draw dashed line down center
    			for (int lineY = 0; lineY < getHeight(); lineY += 50) {
    				g.drawLine(250, lineY, 250, lineY+25);
    			}
    
    			//draw "goal lines" on each side
    			g.drawLine(playerOneRight, 0, playerOneRight, getHeight());
    			g.drawLine(playerTwoLeft, 0, playerTwoLeft, getHeight());
    
    			//draw the scores
    			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
    			g.drawString(String.valueOf(playerOneScore), 100, 100);
    			g.drawString(String.valueOf(playerTwoScore), 400, 100);
    
    			//draw the ball
    			g.fillOval(ballX, ballY, diameter, diameter);
    
    			//draw the paddles
    			g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
    			g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
    		}
    		else if (gameOver) {
    
    			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
    			g.drawString(String.valueOf(playerOneScore), 100, 100);
    			g.drawString(String.valueOf(playerTwoScore), 400, 100);
    
    			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
    			if (playerOneScore > playerTwoScore) {
    				g.drawString("Player 1 Wins!", 165, 200);
    			}
    			else {
    				g.drawString("Player 2 Wins!", 165, 200);
    			}
    
    			g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
    			g.drawString("Press space to restart.", 150, 400);
    		}
    	}
    
    
    
    	public void keyTyped(KeyEvent e) {}
    
    
    
    	public void keyPressed(KeyEvent e) {
    		if (showTitleScreen) {
    			if (e.getKeyCode() == KeyEvent.VK_P) {
    				showTitleScreen = false;
    				playing = true;
    			}
    		}
    		else if(playing){
    			if (e.getKeyCode() == KeyEvent.VK_UP) {
    				upPressed = true;
    			}
    			else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
    				downPressed = true;
    			}
    			else if (e.getKeyCode() == KeyEvent.VK_W) {
    				wPressed = true;
    			}
    			else if (e.getKeyCode() == KeyEvent.VK_S) {
    				sPressed = true;
    			}
    		}
    		else if (gameOver) {
    			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
    				gameOver = false;
    				showTitleScreen = true;
    				playerOneY = 250;
    				playerTwoY = 250;
    				ballX = 250;
    				ballY = 250;
    				playerOneScore = 0;
    				playerTwoScore = 0;
    			}
    		}
    	}
    
    
    	public void keyReleased(KeyEvent e) {
    		if (playing) {
    			if (e.getKeyCode() == KeyEvent.VK_UP) {
    				upPressed = false;
    			}
    			else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
    				downPressed = false;
    			}
    			else if (e.getKeyCode() == KeyEvent.VK_W) {
    				wPressed = false;
    			}
    			else if (e.getKeyCode() == KeyEvent.VK_S) {
    				sPressed = false;
    			}
    		}
    	}
    
    }
    
    

We've added 3 booleans to represent which stage of gameplay the game is in. If
the showTitleScreen boolean is true, we show the title screen and ignore any
key press other than P. When P is pressed, we set showTitleScreen to false and
playing to true. When playing is true, we do everything we did before: respond
to the player keys, animate the game, etc. We do this until one of the
player's score reaches 3, at which point we set playing to false and gameOver
to true. When gameOver is true, we just display the winner and ignore every
key other than space. When space is pressed, we reset the variables
representing the game, set gameOver to false, and showTitleScreen to true.

Now our pong game is complete with a beginning, middle, and end!

### Exercises

  * Add a basic artificial intelligence to your game so player 2's paddle moves on its own.
  * Modify this pong game so the paddles can move left and right as well as up and down!
  * Modify this pong game to support 4 players, one on each side.

