#  [Swing](index.jsp)

## Starting Swing

Before reading this tutorial, you should know:

  * How to write, compile, and run a Java class on the command line.
  * The basics of objects (including how classes can extend other classes), methods, variables, etc.

After this tutorial, you'll be able to:

  * Display a simple gui consisting of a window and a message.

Swing is built in to the JDK, so very little setup is required. Swing classes
are in the javax.swing package, and can be used exactly like any other class
(such as the Scanner class that we've been using for command-line
interactions).

    
    
    import javax.swing.JFrame;
    import javax.swing.JLabel;
     
    public class Main {
     
        public static void main(String[] args) {
        	
        	//create a JFrame, which is a window
        	JFrame frame = new JFrame("Starting Swing");
        	
        	//when we close the JFrame, quit the program
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	//create a label and add it to the JFrame
        	JLabel label = new JLabel("Hello, world!");
        	frame.add(label);
        	
        	//set the JFrame's size and show it to the user
        	frame.setSize(250, 100);
        	frame.setVisible(true);
        	
        	//even though the main method is done, the JFrame will stay open
        }  
    }
    

Save that code into a file named Main.java. You compile and run this program
exactly how we've been compiling and running all of our programs so far: via
the command line. I know some of you are eager to start using something like
eclipse, but you'll save yourself a lot of headaches if you stick with a basic
text editor and the command line.

That program displays a window with a message in it. Let's go over each line
in more detail:

  * The class is named Main and contains a static main() method, which should be pretty boring to you by now.
  * In the main method, we first create a JFrame and give it a title.
  * We make sure that the program exits when we close the JFrame. Otherwise, the program could continue taking up memory even though no windows are visible.
  * We create a label, which is a component that displays some uneditable text, and we add it to the JFrame.
  * We set the size of the JFrame.
  * Finally, we make the JFrame visible, which is what pops the window up so the user can see it.
  * The JFrame will stay around until the user closes it.

### JFrame

You'll be using the JFrame class a lot in Swing, so it's probably a good idea
to get acquainted with it now. The JFrame class is Swing's version of a basic
window- the outermost part of the program that usually contains a border for
resizing and buttons for minimizing or closing the window. You put other
components inside a JFrame to make them visible.

### The API

There are a ton of different components in the Swing API (which is just a
fancy way of saying "the classes that the Java team wrote for you to use"),
and it would be a waste of time for these tutorials to go over each component
individually.

If you've been following these tutorials and reading [the
API](http://docs.oracle.com/javase/7/docs/api/) for each class we've been
using, you now possess 90% of the skills that a professional developer uses:
reading documentation, writing code, debugging that code when it doesn't work
how you'd expect, and going back to the documentation.

For example, given the above example code and a link to [Swing's
API](http://docs.oracle.com/javase/7/docs/api/javax/swing/package-
summary.html), you should be able to do something like this: create a program
that shows a button instead of some text!

To accomplish this task, the first thing you should do is look at the API. Can
you find a Swing class that represents a button?

Eventually you should find the JButton class, and you can look at [its
API](http://docs.oracle.com/javase/7/docs/api/javax/swing/JButton.html) to see
how it works. Looking at the JButton API, we can see that one of its
constructors takes a String parameter to create a button with that text.
Knowing that, we can create some code that tests exactly what this does:

    
    
    import javax.swing.JButton;
    import javax.swing.JFrame;
     
    public class Main {
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Continuing Swing");
        	
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	JButton button = new JButton("button");
        	frame.add(button);
        	
        	frame.setSize(250, 100);
        	frame.setVisible(true);
        }  
    }
    

This program now creates a JButton and adds it to the JFrame. We could follow
a similar process to display a checkbox instead, or to add a border to the
button.

These tutorials will focus on general concepts like handling user input,
placing components, and creating custom components (like games!), but if
you're curious about how to use a specific class, method, or variable, the API
is your best friend!

### Exercises

  * Modify the JFrame program to take command line arguments for the title of the JFrame, the size of the JFrame, and the message displayed inside the JFrame.
  * Add code that sets the location of the JFrame. Take a look at the API for JFrame for useful functions!
  * Modify the above program to show a checkbox instead of a JLabel. The checkbox should be checked already when the window pops up. The API is your friend!
  * Create a program that displays a single component with a border that you specify. Again, the API is your friend!
  * Look carefully at the GUI of some programs you use often. Can you identify the various individual components, such as buttons, menus, sliders, etc? Can you find classes that represent each component in the Swing API?

###  Next: [Colors in Java](JavaColors.jsp)
