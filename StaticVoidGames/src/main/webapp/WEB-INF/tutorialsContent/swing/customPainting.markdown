#  [Swing](index.jsp)

## Custom Painting

Before reading this tutorial, you should know:

  * How to write, compile, and run a Swing GUI that consists of several components and ActionListeners that update those components to respond to user input.
  * The basics of OOP, including inheritance as discussed in the [OOP Tutorial](http://staticvoidgames.com/tutorials/objects/Inheritance.jsp). 

After this tutorial, you'll be able to:

  * Create a component that performs custom painting.

By now you know how to create a GUI using Java's predefined classes such as
JFrame, JPanel, JButton, and JLabel. These predefined classes will work for
many purposes, and you can customize Swing components by setting their
background, foreground, border, font, etc. But to do more complicated
visualizations and game screens, you'll have to perform custom painting.

Custom painting allows you to dictate exactly what a component looks like by
writing the code that paints it.

### Creating a Subclass

Remember that you can create a subclass that extends another class to modify
its behavior or add some functionality to it. If this is confusing, go back
and read the [inheritance
tutorial](http://staticvoidgames.com/tutorials/objects/Inheritance.jsp).

This is a very powerful notion in Swing, because it allows you to specify
custom behavior. In fact, you've already done this when you [created custom Ac
tionListeners](http://staticvoidgames.com/tutorials/swing/StartingSwing.jsp)!
The ActionListeners you defined were subclasses of the existing ActionListener
class (well, technically they were implementations of the ActionListener
interface, but the idea is the same). We can use this same idea to create
custom components by extending them and overriding any methods we want to
modify.

Remember that a JPanel is a component that usually just holds other components
to allow you to [nest
layouts](http://staticvoidgames.com/tutorials/swing/Layouts.jsp). By itself, a
JPanel is not very exciting. However, we can extend JPanel to create a custom
component that displays whatever we want!

We do this by creating a class that extends JPanel, then we override the
paintComponent() method.

    
    
    import java.awt.Graphics;
    import javax.swing.JPanel;
    
    public class CustomPanel extends JPanel{
    
    	public void paintComponent(Graphics g){
    		
    		//x, y, width, height
    		g.drawRect(25, 50, 100, 75);
    		
    		//x, y, width, height
    		g.fillOval(75, 60, 20, 20);
    	}
    }
    
    

The paintComponent() method is called automatically whenever a component needs
to be displayed. By extending JPanel and overriding the paintComponent()
method, we can specify exactly what a component looks like. This example
JPanel draws a rectangle and a square, just to test things out.

Since we're extending JPanel, our component *is a* JPanel. That means we can
use it wherever we can use a normal JPanel. For example, we can add it to a
JFrame and display it like so:

    
    import javax.swing.JFrame;
    
    public class Main{
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Custom Painting");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	CustomPanel customPanel = new CustomPanel();
        	
        	frame.add(customPanel);
        	
        	frame.setSize(250, 200);
        	frame.setVisible(true);
        }
    }
    

This code creates an instance of CustomPanel, adds it to a JFrame, and then
shows that JFrame to the user. Run the code to see something like this:

![](CustomPainting1.png)

### super.paintComponent()

Remember that since our class extends JPanel, it is a JPanel (in the same way
that a leopard *is a* cat). So, we should be able to do this:

    
    
    import java.awt.Color;
    
    import javax.swing.JFrame;
    import javax.swing.border.LineBorder;
    
    public class Main{
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Custom Painting");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	CustomPanel customPanel = new CustomPanel();
        	
        	//give our panel a thick red border
        	customPanel.setBorder(new LineBorder(Color.RED, 5));
        	
        	//give our panel a green background
        	customPanel.setBackground(Color.GREEN);
        	
        	frame.add(customPanel);
        	
        	frame.setSize(250, 200);
        	frame.setVisible(true);
        }
    }
    

This code uses the methods defined by our CustomPanel's parent class to give
it a border and a background. And since our component is a JPanel, it should
work the same, right? Well, run this code and you'll see something like this:

![](CustomPainting2.png)

Notice that the border works, but not the background. What gives?

The paintComponent() method is in charge of painting everything inside a
component. There are other methods in charge of painting a component's border
and any components that have been added to it, but paintComponent() is in
charge of painting things like a component's background and text (for
components that display text). And since our code overrides the
paintComponent() method, we're basically preventing the paintComponent()
method from doing its job. This is why the border works, but the background
color does not.

Luckily, there's an easy fix for this problem. Remember that we can use the
super keyword to refer to the class we've extended. So we can do this:

    
    
    import java.awt.Graphics;
    import javax.swing.JPanel;
    
    public class CustomPanel extends JPanel{
    
    	public void paintComponent(Graphics g){
    		
    		//tell the parent class to paint the background
    		super.paintComponent(g);
    		
    		//x, y, width, height
    		g.drawRect(25, 50, 100, 75);
    		
    		//x, y, width, height
    		g.fillOval(75, 60, 20, 20);
    	}
    }
    

This code now call the paintComponent() method of the parent class, which in
our case is JPanel, before doing the custom painting. This will guarantee that
our background is drawn correctly, like this:

![](CustomPainting3.png)

### Graphics

Swing automatically creates an instance of the Graphics class and passes it
into the paintComponent() method whenever a component needs to be drawn. The
Graphics class contains a bunch of methods that allow you to draw anything
from shapes and lines to text and images. We've used two methods just to get
things working, but the only limit to what you can draw on a Swing component
is your imagination!

Here is a slightly more advanced example that draws a traffic light:

    
    
    import java.awt.Color;
    import java.awt.Graphics;
    import javax.swing.JPanel;
    
    public class CustomPanel extends JPanel{
    
    	public void paintComponent(Graphics g){
    
    		//radius of each light
    		int circleRadius = 50;
    		int circleDiameter = circleRadius * 2;
    		
    		//center of the screen
    		int centerX = 250;
    		int centerY = 250;
    		
    		//circles are drawn from the left, not the center
    		int left = centerX - circleRadius;
    		
    		//figure out the top of each circle
    		int yellowTop = centerY - circleRadius;
    		int redTop = yellowTop - circleDiameter;
    		int greenTop = yellowTop + circleDiameter;
    		
    		
    		//tell the parent class to paint the background
    		super.paintComponent(g);
    		
    		//rounded rectangle
    		g.setColor(Color.GRAY);
    		g.fillRoundRect(left, redTop, circleDiameter, circleDiameter*3, circleRadius, circleRadius);
    		
    		//red circle
    		g.setColor(Color.RED);
    		g.fillOval(left, redTop, circleDiameter, circleDiameter);
    		
    		//yellow circle
    		g.setColor(Color.YELLOW);
    		g.fillOval(left, yellowTop, circleDiameter, circleDiameter);
    		
    		//green circle
    		g.setColor(Color.GREEN);
    		g.fillOval(left, greenTop, circleDiameter, circleDiameter);
    		
    	}
    }
    
    

This custom JPanel draws a traffic light, and you can put it inside a JFrame
like so:

    
    
    import java.awt.Color;
    
    import javax.swing.JFrame;
    
    public class Main{
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Custom Painting");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	CustomPanel customPanel = new CustomPanel();
        	
        	//give our panel a black background
        	customPanel.setBackground(Color.BLACK);
        	
        	frame.add(customPanel);
        	
        	frame.setSize(500, 500);
        	frame.setVisible(true);
        }
    }
    

Run this code and you should see a traffic light that looks like this:

![](CustomPainting5.png)

### Relative Sizing

Notice that the above code assumes the panel's size is 500 pixels by 500
pixels. What happens when you resize the window?

![](CustomPainting6.png)

Notice that the drawing stays the same no matter how small the window is. What
if we want our picture to change size and stay centered even when the window
is resized?

Keep in mind that our class extends JPanel, so it *is a* JPanel. That means it
has all of the methods that JPanel has- including methods like getWidth() and
getHeight(). We can use these methods to size our drawing relative to the size
of the JPanel.

Our updated code now uses the getWidth() and getHeight() methods to
automatically resize the drawing based on the size of the JPanel.

    
    
    import java.awt.Color;
    import java.awt.Graphics;
    import javax.swing.JPanel;
    
    public class CustomPanel extends JPanel{
    
    	public void paintComponent(Graphics g){
    
    		//radius of each light is 10% of the window width
    		int circleRadius = getWidth()/10;
    		int circleDiameter = circleRadius * 2;
    		
    		//center of the screen, no matter the size
    		int centerX = getWidth()/2;
    		int centerY = getHeight()/2;
    		
    		//circles are drawn from the left, not the center
    		int left = centerX - circleRadius;
    		
    		//figure out the top of each circle
    		int yellowTop = centerY - circleRadius;
    		int redTop = yellowTop - circleDiameter;
    		int greenTop = yellowTop + circleDiameter;
    		
    		
    		//tell the parent class to paint the background
    		super.paintComponent(g);
    		
    		//rounded rectangle
    		g.setColor(Color.GRAY);
    		g.fillRoundRect(left, redTop, circleDiameter, circleDiameter*3, circleRadius, circleRadius);
    		
    		//red circle
    		g.setColor(Color.RED);
    		g.fillOval(left, redTop, circleDiameter, circleDiameter);
    		
    		//yellow circle
    		g.setColor(Color.YELLOW);
    		g.fillOval(left, yellowTop, circleDiameter, circleDiameter);
    		
    		//green circle
    		g.setColor(Color.GREEN);
    		g.fillOval(left, greenTop, circleDiameter, circleDiameter);
    		
    	}
    }
    
    

Now our drawing makes sense if we make the window very small:

![](CustomPainting7.png)

Or even if we make it very large:

![](CustomPainting8.png)

### Bonus

We can use a series of calls to various methods of the Graphics instance to
draw a face:

    
    
    import java.awt.Color;
    import java.awt.Graphics;
    import javax.swing.JPanel;
    
    public class CustomPanel extends JPanel{
    
    	public void paintComponent(Graphics g){
    
    		//tell the parent class to paint the background
    		super.paintComponent(g);
    
    		//yellow face
    		g.setColor(Color.ORANGE);
    		g.fillOval(50, 50, 400, 400);
    
    		//outline of face
    		g.setColor(Color.BLACK);
    		g.drawOval(50, 50, 400, 400);
    
    		//left eye white
    		g.setColor(Color.WHITE);
    		g.fillOval(125, 125, 75, 100);
    
    		//left eye outline
    		g.setColor(Color.BLACK);
    		g.drawOval(125, 125, 75, 100);
    
    		//left eye ball
    		g.setColor(Color.BLACK);
    		g.fillOval(150, 150, 25, 50);
    
    		//right eye white
    		g.setColor(Color.WHITE);
    		g.fillOval(300, 125, 75, 100);
    
    		//right eye outline
    		g.setColor(Color.BLACK);
    		g.drawOval(300, 125, 75, 100);
    
    		//right eye ball
    		g.setColor(Color.BLACK);
    		g.fillOval(325, 150, 25, 50);
    		
    		//nose color
    		g.setColor(Color.ORANGE.brighter());
    		g.fillOval(212, 237, 75, 50);
    		
    		//nose outline
    		g.setColor(Color.BLACK);
    		g.drawOval(212, 237, 75, 50);
    
    		//mouth inside
    		g.setColor(Color.RED);
    		g.fillArc(100, 275, 300, 125, 0, -180);
    		
    		//mouth outline
    		g.setColor(Color.BLACK);
    		g.drawArc(100, 275, 300, 125, 0, -180);
    		g.drawLine(100, 338, 400, 338);
    		
    	}
    }
    
    

Run this code and you should see a face that looks like this:

![](CustomPainting4.png)

### Timing

The paintComponent() method is called automatically by Java, sometimes
multiple times a second, so you might be tempted to use it as a game loop or
animation thread (especially if you're coming from Processing where you're
accustomed to the draw() method). But never use the paintComponent() method as
an animation method! You cannot rely on when the paintComponent() method is
called!

The only thing you should have in your paintComponent() method is painting
code, never game or animation logic! The next lesson will demonstrate how to
handle animations.

### Exercises

  * The traffic light only takes the width of the window into account when deciding the radius of the circles. Modify the program so that the vertical height and horizontal width are based on the height and width of the window. (If you make the window very short but very wide, the lights should be wide ovals instead of circles.)
  * Modify the code that draws a face to take the panel size into account.
  * Add a hat or some hair to the face!
  * Write a program that draws your favorite fictional character!
  * Write a program that displays a custom drawing (such as a figure or a face), as well as a series of JButtons to cycle through different aspects of the drawing. For example, you might display a face as well as a JButton that toggles different kinds of eyes, a JButton for noses, and a JButton for different kinds of mouths.
  * Create a program that draws a virtual turkey for the [46 Million Turkey Project](http://staticvoidgames.com/blog/viewEntry.jsp?entry=NovembersMotivator46MillionTurkeyProject)! 

### Next: [Mouse and Keyboard Listeners](Listeners.jsp)
