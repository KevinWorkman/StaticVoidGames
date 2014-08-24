#  [Swing](index.jsp)

## Swing Timers

Before reading this tutorial, you should know:

  * How to write, compile, and run a Swing GUI that consists of several components and ActionListeners that update those components to respond to user input.
  * How to create a component that performs custom painting.

After this tutorial, you'll be able to:

  * Use Swing Timers to create animations.

So far we've covered the creation of GUIs that respond to user input. We also
went over custom painting so you can draw anything you want. The last concept
we need to introduce is modifying a gui over time, which can be accomplished
using Swing Timers.

Swing Timers allow you to schedule some action to happen over and over again,
which allows you to create animations and even games.

If you're coming from the Processing tutorials, notice that Java doesn't have
a magical draw() method that's called 60 times a second. You have to set that
up yourself, and that's where Timers come in handy.

### Example GUI

To make it easier to talk about Swing Timers, let's introduce a simple example
gui. This gui contains a JLabel and a JButton. The JLabel displays the number
of times the JButton was clicked. When the JButton is clicked, an
ActionListener sets the text of the JLabel. Here's the ActionListener:

    
    
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import javax.swing.JLabel;
    
    public class MyListener implements ActionListener{
    	
    	private int count = 0;
    	
    	private JLabel label;
    	
    	public MyListener(JLabel label){
    		this.label = label;
    	}
    
    	public void actionPerformed(ActionEvent e) {
    		
    		count++;
    		
    		label.setText("Count: " + count);
    	}
    }
    

The MyListener class implements ActionListener, which means that it is an
ActionListener and must define an actionPerformed() method. In the
actionPerformed() method, we increment a count variable and set the text of
the JLabel, which is passed into the constructor of the MyListener class. We
use the MyListener class inside our gui class:

    
    
    import java.awt.FlowLayout;
    
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    
    public class Main{
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Counter");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	frame.setLayout(new FlowLayout());
        	
        	JButton button = new JButton("Click me!");
        	frame.add(button);
        	
        	JLabel label = new JLabel("Count: 0");
        	frame.add(label);
        	
        	//pass the label into the MyListener constructor
        	MyListener listener = new MyListener(label);
        	
        	//add the listener to the JButton
        	//whenever the button is clicked, the JLabel is updated
        	button.addActionListener(listener);
        	
        	frame.setSize(225, 100);
        	frame.setVisible(true);
        }
    }
    

This code simply creates a gui containing a JLabel and a JButton. It also
creates an instance of MyListener by passing the JLabel into its constructor.
It adds the instance of MyListener to the JButton, so that when the user
clicks the button, the JLabel text changes. Copy this code into your text
editor, compile it, and run it to see for yourself!

### Creating a Timer

Think of a Timer like a ticking clock where you set the time between ticks and
what happens every tick. For example, what if we wanted the count to
automatically increase every second instead of when the user clicks a button?
A Timer would be perfect for that job!

To use a Swing Timer, just like every other class we use in Java, we first
have to create an instance of Timer by calling one of its constructors using
the new keyword. And by consulting the
[API](http://docs.oracle.com/javase/7/docs/api/javax/swing/Timer.html), we can
see that the constructor for Timer requires two arguments: a delay between
ticks, and an ActionListener to be fired every tick.

We already wrote an ActionListener, so we can reuse that. And since we want
the event to repeat every second, we pass in 1000 milliseconds to the
constructor.

    
    
    import java.awt.FlowLayout;
    
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.Timer;
    
    public class Main{
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Counter");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	frame.setLayout(new FlowLayout());
        	
        	
        	JLabel label = new JLabel("Count: 0");
        	frame.add(label);
        	
        	//pass the label into the MyListener constructor
        	MyListener listener = new MyListener(label);
        	
        	//the timer fires every 1000 MS (1 second)
        	//when it does, it calls the actionPerformed() method of MyListener
        	Timer timer = new Timer(1000, listener);
        	
        	//start the timer
        	timer.start();
        	
        	
        	frame.setSize(225, 100);
        	frame.setVisible(true);
        }
    }
    

Our gui no longer contains a JButton. Instead, we use a Timer to call the
MyListener actionPerformed() method every 1000 milliseconds.

Run this updated code and watch the count increase automatically!

### Animation

Now we know how to use Timers to repeat an action every X milliseconds, and
the previous tutorial taught us how to perform custom painting. We can combine
these ideas to create animations!

For example, this class extends JPanel and overrides paintComponent() to draw
a ball. This class also defines a step() function that moves the ball around
and bounces it off the sides of the window. This should look pretty familiar
if you went through the Processing tutorials!

    
    
    import java.awt.Color;
    import java.awt.Graphics;
    
    import javax.swing.JPanel;
    
    
    public class BallPanel extends JPanel{
    	
    	private int ballX = 250;
    	private int ballY = 250;
    	private int ballDiameter = 50;
    	
    	private double ballXSpeed = 5;
    	private double ballYSpeed = 10;
    	
    	
    	public BallPanel(){
    		setBackground(Color.BLACK);
    	}
    	
    	//call this method from a Timer to move the ball!
    	public void step(){
    		
    		//move the ball on the X axis
    		ballX += ballXSpeed;
    		//if the ball goes off the edge, bounce it by reversing its speed
    		if(ballX < 0 || ballX > getWidth()-ballDiameter){
    			ballXSpeed *= -1;
    		}
    		
    		//move the ball on the Y axis
    		ballY += ballYSpeed;
    		//if the ball goes off the edge, bounce it by reversing its speed
    		if(ballY < 0 || ballY > getHeight()-ballDiameter){
    			ballYSpeed *= -1;
    		}
    		
    		//tell this JPanel to repaint itself since the ball has moved
    		repaint();
    	}
    	
    	//paint the ball
    	public void paintComponent(Graphics g){
    		
    		super.paintComponent(g);
    		
    		g.setColor(Color.BLUE);
    		g.fillOval(ballX, ballY, ballDiameter, ballDiameter);
    	}
    
    }
    

An important line to notice is the last line in the step() function, which
calls repaint(). We need this because our BallPanel isn't smart enough to know
to repaint itself whenever the ball moves. To tell it to repaint itself, we
simply call the repaint() function.

We know that the paintComponent() method is called automatically by Java
whenever a component needs to be painted. But when is the step() function
called? Answer: it's not! We have to call the step() function ourselves. To do
that, we can create an ActionListener that calls the step() function:

    
    
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    
    public class MyListener implements ActionListener{
    		
    	private BallPanel ballPanel;
    	
    	public MyListener(BallPanel ballPanel){
    		this.ballPanel = ballPanel;
    	}
    
    	public void actionPerformed(ActionEvent e) {
    		ballPanel.step();
    	}
    }
    

This ActionListener is pretty straightforward: we pass in an instance of
BallPanel to the constructor, and the ActionListener calls its step() function
inside its own actionPerformed() function. Now all that's left to do is put
the pieces together!

    
    
    import java.awt.BorderLayout;
    
    import javax.swing.JFrame;
    import javax.swing.Timer;
    
    public class Main{
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Counter");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	frame.setLayout(new BorderLayout());
        	
        	BallPanel ballPanel = new BallPanel();
        	frame.add(ballPanel, BorderLayout.CENTER);
        	
        	//pass the BallPanel instance into the MyListener constructor
        	MyListener listener = new MyListener(ballPanel);
        	
        	//the timer fires 30 times a second
        	Timer timer = new Timer(1000/30, listener);
        	
        	//start the timer
        	timer.start();
        	
        	frame.setSize(500, 500);
        	frame.setVisible(true);
        }
    }
    

This code creates an instance of BallPanel and adds it to a JFrame. It passes
that BallPanel into an ActionListener that simply calls its step() function.
Finally, we pass that ActionListener into a Timer that is fired 30 times per
second. The end result is the step() function being called 30 times a second,
which causes the ball to bounce around the screen.

### Precise Timing

We're relying on Swing Timers to always fire at the rate we specify. However,
this isn't always the case! For example, it's simply impossible for a Timer to
fire 1,000 times per second- the computer wouldn't be able to keep up! Even a
rate like 60 times a second can't be guaranteed, such as when a lot of other
programs are open on a computer. And even with no other programs open, some
operating systems can't guarantee precise timing.

This is a problem because many games or visualizations rely on precise timing.
Imagine trying to play Mario as the game randomly got faster or slower!

The solution to this problem is to take into account the actual time between
frames. In our example, we would calculate how long it's been since the last
time step() was called and move the ball more or less depending on that time.
This way we can guarantee the ball always moves at a smooth pace, even if the
step() function is called at different intervals than the one we specified.

The details of that go a bit beyond this tutorial, but I wanted to give you a
heads up. For now, relying on Swing Timers is probably good enough for our
purposes.

### Exercises

  * Create a stop watch GUI that starts at zero and displays the elapsed time down to tenths of seconds. The display should change every tenth of a second, and it should display the elapsed hours, minutes, seconds, and tenths of seconds. For example, a stopwatch that has been running for 1 hour, 45 minutes, and 37.4 seconds should display 01:45:37:4. Hint: the Java API is your friend!
  * Add JButtons to the stopwatch display so the user can start, pause, and reset the stopwatch.
  * Create a program similar to the stopwatch display, but show a countdown instead. Let the user specify the end time, then count down to that moment. How long is it until your birthday? Your next vacation?

### Next: [Self Listening](SelfListening.jsp)
