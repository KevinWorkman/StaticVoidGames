#  [Swing](index.jsp)

## Self Listening

Before reading this tutorial, you should know:

  * How to create a component that performs custom painting.
  * How to use KeyListeners to detect key events.
  * How to use Swing Timers to create animations.

After this tutorial, you'll be able to:

  * Create a custom component that listens to its own events.

By now we know how to perform custom painting and animations. For example,
here's a basic custom panel that draws a ball:

    
    
    import java.awt.Color;
    import java.awt.Graphics;
    
    import javax.swing.JPanel;
    
    
    public class BallPanel extends JPanel{
    	
    	private int ballX = 250;
    	private int ballY = 250;
    	private int ballDiameter = 50;
    		
    	public BallPanel(){
    		setBackground(Color.BLACK);
    	}
    	
    	//paint the ball
    	public void paintComponent(Graphics g){
    		
    		super.paintComponent(g);
    		
    		g.setColor(Color.BLUE);
    		g.fillOval(ballX, ballY, ballDiameter, ballDiameter);
    	}
    
    }
    

And here's how we add it to a JFrame:

    
    
    import java.awt.BorderLayout;
    
    import javax.swing.JFrame;
    import javax.swing.Timer;
    
    public class Main{
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Ball");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	frame.setLayout(new BorderLayout());
        	
        	BallPanel ballPanel = new BallPanel();
        	frame.add(ballPanel, BorderLayout.CENTER);
        	
        	frame.setSize(500, 500);
        	frame.setVisible(true);
        }
    }
    

By now you should also be able to expand this code to support a key listener.
A key listener might look like this:

    
    
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
    
    public class MyKeyListener implements KeyListener{
    	
    	private BallPanel ballPanel;
    	
    	public MyKeyListener(BallPanel ballPanel){
    		this.ballPanel = ballPanel;
    	}
    
    	public void keyPressed(KeyEvent e) {
    
    		if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
    			ballPanel.moveBallRight();
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
    			ballPanel.moveBallLeft();
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_UP ) {
    			ballPanel.moveBallUp();
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
    			ballPanel.moveBallDown();
    		}
    	}
    
    	public void keyReleased(KeyEvent e) {}
    	public void keyTyped(KeyEvent e) {}
    }
    

Of course, we have to add the methods in the BallPanel class, which now looks
like this:

    
    
    import java.awt.Color;
    import java.awt.Graphics;
    
    import javax.swing.JPanel;
    
    public class BallPanel extends JPanel{
    
    	private int ballX = 250;
    	private int ballY = 250;
    	private int ballDiameter = 50;
    
    	public BallPanel(){
    		setBackground(Color.BLACK);
    	}
    
    	//paint the ball
    	public void paintComponent(Graphics g){
    
    		super.paintComponent(g);
    
    		g.setColor(Color.BLUE);
    		g.fillOval(ballX, ballY, ballDiameter, ballDiameter);
    	}
    
    	public void moveBallLeft() {
    		ballX-=5;
    		repaint();
    	}
    
    	public void moveBallRight() {
    		ballX+=5;
    		repaint();
    	}
    
    	public void moveBallUp() {
    		ballY-=5;
    		repaint();
    	}
    
    	public void moveBallDown() {
    		ballY+=5;
    		repaint();
    	}
    
    }
    

All of this should be pretty straightfoward so far, except maybe the calls to
the repaint() function inherited from the JPanel class. We need this because
our BallPanel isn't smart enough to know to repaint itself whenever the ball
moves. To tell it to repaint itself, we simply call the repaint() function.

Now all that's left to do is add the MyKeyListener to our BallPanel:

    
    
    import java.awt.BorderLayout;
    
    import javax.swing.JFrame;
    
    public class Main{
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Ball");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	frame.setLayout(new BorderLayout());
        	
        	BallPanel ballPanel = new BallPanel();
        	//make sure the panel can detect key events
        	ballPanel.setFocusable(true);
        	
        	frame.add(ballPanel, BorderLayout.CENTER);
        	
        	MyKeyListener keyListener = new MyKeyListener(ballPanel);
        	ballPanel.addKeyListener(keyListener);
        	
        	frame.setSize(500, 500);
        	frame.setVisible(true);
        
        }
    }
    

Again, this should still be pretty straightforward. The only new line might be
the call to the setFocusable() method, which we need to make sure the
BallPanel (which is a JPanel) can receive key input.

Compile and run these classes to see a ball that's controlled by the arrow
keys!

### Listening to Yourself

We've been separating our KeyListener code into its own class just to make
things more obvious, but that can result in a lot of [boilerplate
code](http://en.wikipedia.org/wiki/Boilerplate_code). Instead of separating
the KeyListener code into its own class, we can tell the BallPanel to listen
to its own key events. We do this by having the BallPanel class implement
KeyListener, like so:

    
    
    import java.awt.Color;
    import java.awt.Graphics;
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
    
    import javax.swing.JPanel;
    
    public class BallPanel extends JPanel implements KeyListener{
    
    	private int ballX = 250;
    	private int ballY = 250;
    	private int ballDiameter = 50;
    
    	public BallPanel(){
    		setBackground(Color.BLACK);
    		setFocusable(true);
    		addKeyListener(this);
    	}
    
    	//paint the ball
    	public void paintComponent(Graphics g){
    
    		super.paintComponent(g);
    
    		g.setColor(Color.BLUE);
    		g.fillOval(ballX, ballY, ballDiameter, ballDiameter);
    	}
    	
    	public void keyPressed(KeyEvent e) {
    
    		if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
    			ballX+=5;
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
    			ballX-=5;
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_UP ) {
    			ballY-=5;
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
    			ballY+=5;
    		}
    		
    		repaint();
    	}
    
    	public void keyReleased(KeyEvent e) {}
    	public void keyTyped(KeyEvent e) {}
    
    
    
    }
    

Because BallPanel extends JPanel and implements KeyListener, it is BOTH a
JPanel AND a KeyListener. We use the this keyword to let the BallPanel
instance refer to itself, and we add it as its own KeyListener. Now we can
move the ball directly from the keyPressed() method!

Now we can get rid of the MyKeyListener class entirely. This is what the Main
class looks like now:

    
    
    import java.awt.BorderLayout;
    
    import javax.swing.JFrame;
    
    public class Main{
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Ball");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	frame.setLayout(new BorderLayout());
        	
        	BallPanel ballPanel = new BallPanel();
        	frame.add(ballPanel, BorderLayout.CENTER);
        	    	
        	frame.setSize(500, 500);
        	frame.setVisible(true);
       
        }
    }
    

Now that BallPanel handles itself, all we have to do is add it to a JFrame and
show it. Much simpler!

### Smooth Movement

If you compile and run the above program, you'll see a ball that moves when
you push the arrow keys. However, you'll also notice that the movement has
some problems: the ball movement depends on your keyboard's repeat rate, and
it only listens to one key at a time.

If you followed the Processing tutorials, you know the fix to this: simply
keep track of which keys are currently pressed, and move the ball based on
those keys 60 times per second.

We use four boolean values to keep track of whether each of the arrow keys are
currently being pressed, and we set them in the keyPressed() and keyReleased()
methods. We've now added a step() function that moves the ball based on those
booleans, and now all we need to do is call it 60 times per second!

    
    
    import java.awt.Color;
    import java.awt.Graphics;
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
    
    import javax.swing.JPanel;
    
    public class BallPanel extends JPanel implements KeyListener{
    	
    	private boolean upPressed = false;
    	private boolean downPressed = false;
    	private boolean leftPressed = false;
    	private boolean rightPressed = false;
    
    	private int ballX = 250;
    	private int ballY = 250;
    	private int ballDiameter = 50;
    
    	public BallPanel(){
    		setBackground(Color.BLACK);
    		setFocusable(true);
    		addKeyListener(this);
    	}
    	
    	public void step(){
    		if (rightPressed) {
    			ballX+=5;
    		} 
    		if (leftPressed) {
    			ballX-=5;
    		} 
    		if (upPressed) {
    			ballY-=5;
    		} 
    		if (downPressed) {
    			ballY+=5;
    		}
    		
    		repaint();
    	}
    
    	//paint the ball
    	public void paintComponent(Graphics g){
    
    		super.paintComponent(g);
    
    		g.setColor(Color.BLUE);
    		g.fillOval(ballX, ballY, ballDiameter, ballDiameter);
    	}
    	
    	public void keyPressed(KeyEvent e) {
    
    		if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
    			rightPressed = true;
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
    			leftPressed = true;
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_UP ) {
    			upPressed = true;
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
    			downPressed = true;
    		}
    		
    	}
    
    	public void keyReleased(KeyEvent e) {
    		if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
    			rightPressed = false;
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
    			leftPressed = false;
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_UP ) {
    			upPressed = false;
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
    			downPressed = false;
    		}
    	}
    	
    	public void keyTyped(KeyEvent e) {}
    
    }
    

We know how to use a Swing Timer to call a function 60 times a second: we'd
create a class that implements ActionListener and takes an instance of
BallPanel as an argument into its constructor. Then in the actionPerformed()
function, we'd call the step() function of that BallPanel instance. Then we'd
build a Swing Timer out of that listener class and start it.

But instead of having a completely separate class that implements
ActionListener, we could do all of that directly in the BallPanel class!

    
    
    import java.awt.Color;
    import java.awt.Graphics;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
    
    import javax.swing.JPanel;
    import javax.swing.Timer;
    
    public class BallPanel extends JPanel implements KeyListener, ActionListener{
    	
    	private boolean upPressed = false;
    	private boolean downPressed = false;
    	private boolean leftPressed = false;
    	private boolean rightPressed = false;
    
    	private int ballX = 250;
    	private int ballY = 250;
    	private int ballDiameter = 50;
    
    	public BallPanel(){
    		setBackground(Color.BLACK);
    		setFocusable(true);
    		addKeyListener(this);
    		
    		//call the step() function 60 times per second
    		Timer timer = new Timer(1000/60, this);
    		timer.start();
    	}
    
    	public void actionPerformed(ActionEvent e) {
    		step();
    	}
    	
    	//move the ball based on which keys are pressed
    	public void step(){
    		if (rightPressed) {
    			ballX+=5;
    		} 
    		if (leftPressed) {
    			ballX-=5;
    		} 
    		if (upPressed) {
    			ballY-=5;
    		} 
    		if (downPressed) {
    			ballY+=5;
    		}
    		
    		repaint();
    	}
    
    	//paint the ball
    	public void paintComponent(Graphics g){
    
    		super.paintComponent(g);
    
    		g.setColor(Color.BLUE);
    		g.fillOval(ballX, ballY, ballDiameter, ballDiameter);
    	}
    	
    	public void keyPressed(KeyEvent e) {
    
    		if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
    			rightPressed = true;
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
    			leftPressed = true;
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_UP ) {
    			upPressed = true;
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
    			downPressed = true;
    		}
    		
    	}
    
    	public void keyReleased(KeyEvent e) {
    		if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
    			rightPressed = false;
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
    			leftPressed = false;
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_UP ) {
    			upPressed = false;
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
    			downPressed = false;
    		}
    	}
    	
    	public void keyTyped(KeyEvent e) {}
    
    }
    

Remember that you can implement as many interfaces as you want, and in this
case we make the BallPanel class implement both KeyListener AND
ActionListener. So our BallPanel class is simultaneous a JPanel, a
KeyListener, and an ActionListener. And since it's an ActionListener, we can
pass it into the constructor of a Swing Timer.

The Swing Timer calls our actionPerformed() method 60 times per second, and
that lets us call the step() function. Now we have smooth movement, all rolled
up into one class!

### Exercises

  * Modify the above program so the ball bounces off the sides of the screen.
  * Give the ball speed and acceleration.
  * Make it so the ball's color changes when the user clicks the mouse.

### Next: [Pong!](SwingPong.jsp)

