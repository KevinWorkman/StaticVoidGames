#  [Swing](index.jsp)

## Java Colors

Before reading this tutorial, you should know:

  * How to write, compile, and run a very simple Swing GUI.
  * The basics of objects (including how classes can extend other classes), methods, variables, etc.

After this tutorial, you'll be able to:

  * Use colors!

One of the big differences between Processing and Java is how colors work. In
Processing, colors are disguised as primitives, so you can treat them like an
int or a double and even use literal color values. In Java, however, colors
are objects. Specifically, colors are represented by instances of the Color
class, which is located in the java.awt package.

The Color class has several constructors, one of which takes 3 int parameters
representing the red, green, and blue values on a scale of 0-255. Once you
have an instance of Color, you can pass that instance around to various Swing
methods. For example, we can set the color of the text in our hello world
program:

    
    
    import java.awt.Color;
    
    import javax.swing.JFrame;
    import javax.swing.JLabel;
     
    public class Main {
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Starting Swing");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	JLabel label = new JLabel("Hello, world!");
        	frame.add(label);
        	
        	//create an instance of color
        	Color textColor = new Color(0, 0, 255);
        	//pass that instance into the setForeground() method.
        	label.setForeground(textColor);
        	
        	//center the text in the window
        	label.setHorizontalAlignment(JLabel.CENTER);
        	
        	frame.setSize(250, 100);
        	frame.setVisible(true);
        }  
    }
    

This code creates a JFrame and adds a JLabel to it. It then creates an
instance of Color, in this case blue. That instance is passed into the
setForeground() method of JLabel, which sets the text color. The code then
calls the setHorizontalAlignment() method, passing the static variable
JLabel.CENTER into it. This has nothing to do with the color, it just
demonstrates calling multiple functions on a Swing component. Finally, the
JFrame is sized and displayed.

Just like in Processing, you can also pass in floats in the range 0.0-1.0
instead of ints. Colors also have an optional fourth value that specifies the
opacity of the color, so you can create transparencies.

For more info, as always, check out [the
API](http://docs.oracle.com/javase/7/docs/api/java/awt/Color.html).

### Predefined Colors

In the above code, we used the Color constructor to create instances of the
Color class. We can use this to create a huge number of colors: 16,777,216 to
be exact! However, very often we just want to use basic colors like red,
green, orange, or white.

Instead of forcing you to create a new instance of Color every time you need
one of these popular colors, Java has created them ahead of time and saved
them in static variables of the Color class. For example, the above program
can be rewritten like this:

    
    
    import java.awt.Color;
    
    import javax.swing.JFrame;
    import javax.swing.JLabel;
     
    public class Main {
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Starting Swing");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	JLabel label = new JLabel("Hello, world!");
        	frame.add(label);
        	
        	label.setForeground(Color.BLUE);
        	
        	label.setHorizontalAlignment(JLabel.CENTER);
        	
        	frame.setSize(250, 100);
        	frame.setVisible(true);
        }  
    }
    

The only real difference is I'm now using the predefined static variable
Color.BLUE instead of creating an instance of Color with a blue value by hand.
Color.BLUE is a static variable in the Color class that points to an instance
of Color created using the Color(0, 0, 255) constructor.

### Component Opacity

Swing components almost always have a background color associated with them.
For example, we can create a JButton and call the setBackground() function to
give it a background color:

    
    
    import java.awt.Color;
    
    import javax.swing.JButton;
    import javax.swing.JFrame;
     
    public class Main {
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Starting Swing");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	JButton button = new JButton("Hello, world!");
        	frame.add(button);
        	
        	button.setBackground(Color.GREEN);
        	
        	frame.setSize(250, 100);
        	frame.setVisible(true);
        }  
    }
    

This program displays a green button. Let's try to use a JLabel in a similar
way:

    
    
    import java.awt.Color;
    
    import javax.swing.JLabel;
    import javax.swing.JFrame;
     
    public class Main {
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Starting Swing");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	JLabel label = new JLabel("Hello, world!");
        	frame.add(label);
        	
        	label.setBackground(Color.GREEN);
        	
        	frame.setSize(250, 100);
        	frame.setVisible(true);
        }  
    }
    

That code compiles fine, but if you run it, the JLabel's background isn't
green. Why is that?

The reason for this strange behavior is something called the opacity of
components, in this case your JLabel. Every Swing component has a background
color, but it also has a boolean value that determines whether the component
is opaque or not. Opaque components paint their background color, and non-
opaque components do not, instead allowing underlying components to show
through.

The opacity of a Swing component can be set by the setOpaque() function. To
make our JLabel show its background color, we simply have to make it opaque:

    
    
    import java.awt.Color;
    
    import javax.swing.JLabel;
    import javax.swing.JFrame;
     
    public class Main {
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Starting Swing");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	JLabel label = new JLabel("Hello, world!");
        	frame.add(label);
        	
        	label.setBackground(Color.GREEN);
        	label.setOpaque(true);
        	
        	frame.setSize(250, 100);
        	frame.setVisible(true);
        }  
    }
    

### Exercises

  * Modify the JFrame program to take command line arguments for the title of the JFrame, the size of the JFrame, and the message displayed inside the JFrame.
  * Add code that sets the location of the JFrame. Take a look at the API for JFrame for useful functions!
  * Modify the above program to show a checkbox instead of a JLabel. The checkbox should be checked already when the window pops up. The API is your friend!
  * Create a program that displays a single component with a border that you specify. Again, the API is your friend!
  * Look carefully at the GUI of some programs you use often. Can you identify the various individual components, such as buttons, menus, sliders, etc? Can you find classes that represent each component in the Swing API?

###  Next: [Laying Out Multiple Components](Layouts.jsp)
