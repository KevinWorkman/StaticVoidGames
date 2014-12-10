#Swing

## Layouts

Before reading this tutorial, you should know:

  * How to write, compile, and run a very simple Swing GUI.
  * The basics of objects (including how classes can extend other classes), methods, variables, etc.
  * How to use colors to give Swing components a background.

After this tutorial, you'll be able to:

  * Add multiple components to a JFrame in various layouts.

So far we've used very basic GUIs consisting of a window with a single component. However, most GUIs are made up of many components, not just a single label or button! This tutorial will show you how to add multiple components to a window, and how to position them how you want them.

### Layout Managers

Take a look at a GUI you use (such as this web browser), and pay attention to where the buttons, menus, labels, icons, and other components are. Now resize the window. Notice how the components reposition or resize themselves to fit into the window. The bounds (the position and size) of a component are determined by their **layout**. There are a ton of different types of layouts.

When adding multiple components to a window, you have to tell the window how to place them, and how to reposition and resize them when the window is resized. In other words, you have to give the window a layout.

In Java, layouts are handled by objects called Layout Managers. There are a bunch of different layout managers, each which behaves a little bit differently.

To use a layout manager, pass an instance of one of the layout manger classes into the setLayout() function. For example, we can use a FlowLayout on our JFrame:

    import java.awt.FlowLayout;
    
    import javax.swing.JButton;
    import javax.swing.JCheckBox;
    import javax.swing.JLabel;
    import javax.swing.JFrame;
     
    public class Main {
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Flow Layout");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	FlowLayout layout = new FlowLayout();
        	frame.setLayout(layout);
        
        	JLabel label = new JLabel("label");
        	frame.add(label);
        	   	
        	JCheckBox checkbox = new JCheckBox("checkbox");
        	frame.add(checkbox);
        	
        	JButton button = new JButton("button");
        	frame.add(button);
        
        	frame.setSize(250, 100);
        	frame.setVisible(true);
        }  
    }
    

In this code, we create an instance of JFrame, just like always. Then we create an instance of FlowLayout and pass it into the setLayout() function of our JFrame. Then we create a label, a checkbox, and a button, and add each to the JFrame. Finally, we set the size of the JFrame and show it to the user.

Different layout managers do different things, and FlowLayout is one of the
simplest: it places the components from left to right in the window, and when
it runs out of room, it drops down to the next line. So we start with a window
that looks like this:

![](http://StaticVoidGames.com/tutorialsContent/swing/FlowLayout1.png)

The components are arranged left to right in the order we added them to the JFrame. If we make the window smaller, the components will scrunch together, and if we make the window too narrow to show the components in a single row, the right-most component will go to the next line.

![](http://StaticVoidGames.com/tutorialsContent/swing/FlowLayout2.png)

If we make the window even smaller, the components will display in a single column instead of a single row.

![](http://StaticVoidGames.com/tutorialsContent/swing/FlowLayout3.png)

FlowLayout is useful for quick GUI design, or for parts of your GUI that you want to react to resizing.

Another type of layout manager is BorderLayout. With a BorderLayout, you specify which section of the window a component should occupy when you add it to the window. We can modify our above program to use a BorderLayout:

    import java.awt.BorderLayout;
    
    import javax.swing.JButton;
    import javax.swing.JFrame;
     
    public class Main {
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Border Layout");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	BorderLayout layout = new BorderLayout();
        	frame.setLayout(layout);
        
        	JButton northButton = new JButton("North");
        	JButton westButton = new JButton("West");
        	JButton centerButton = new JButton("Center");
        	JButton eastButton = new JButton("East");
        	JButton southButton = new JButton("South");
        	
        	frame.add(northButton, BorderLayout.NORTH);
        	frame.add(westButton, BorderLayout.WEST);
        	frame.add(centerButton, BorderLayout.CENTER);
        	frame.add(eastButton, BorderLayout.EAST);
        	frame.add(southButton, BorderLayout.SOUTH);
        	
        	frame.setSize(250, 250);
        	frame.setVisible(true);
        }
    }
    

Run this code, and you should see something like this:

![](http://StaticVoidGames.com/tutorialsContent/swing/BorderLayout.png)

When using BorderLayout, the component you place in the CENTER spot will get the most room. The other components will take up as little room as they can. You also don't have to fill every section of a BorderLayout. For example, you might put a menu in the BorderLayout.EAST position and the main section of your GUI in the BorderLayout.CENTER position. This is useful for the overall design of your GUI.

### JPanel

We've seen a few different kinds of components (such as JButton, JLabel, and JCheckBox). Yet another component is a JPanel, and you'll probably end up using it quite a bit.

A JPanel is a rectangular component with the typical properties such as a background color and a border, but it doesn't have any interactive functionality like a button or a checkbox. For example, we can use JPanels in a BorderLayout:

    import java.awt.BorderLayout;
    import java.awt.Color;
    
    import javax.swing.JPanel;
    import javax.swing.JFrame;
     
    public class Main {
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("JPanels");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	BorderLayout layout = new BorderLayout();
        	frame.setLayout(layout);
        
        	JPanel northPanel = new JPanel();
        	JPanel westPanel = new JPanel();
        	JPanel centerPanel = new JPanel();
        	JPanel eastPanel = new JPanel();
        	JPanel southPanel = new JPanel();
        	
        	northPanel.setBackground(Color.RED);
        	westPanel.setBackground(Color.BLUE);
        	centerPanel.setBackground(Color.GREEN);
        	eastPanel.setBackground(Color.ORANGE);
        	southPanel.setBackground(Color.CYAN);
        	
        	frame.add(northPanel, BorderLayout.NORTH);
        	frame.add(westPanel, BorderLayout.WEST);
        	frame.add(centerPanel, BorderLayout.CENTER);
        	frame.add(eastPanel, BorderLayout.EAST);
        	frame.add(southPanel, BorderLayout.SOUTH);
        	
        	frame.setSize(250, 250);
        	frame.setVisible(true);
        }
    }
    
Compile and run this code to see this program:

![](http://StaticVoidGames.com/tutorialsContent/swing/JPanels1.png)

This might not seem very useful, but JPanels have one extremely useful functionality: you can add other components to them, exactly like we've been adding components to a JFrame.

    import java.awt.BorderLayout;
    import java.awt.Color;
    
    import javax.swing.JButton;
    import javax.swing.JPanel;
    import javax.swing.JFrame;
     
    public class Main {
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("JPanels");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	BorderLayout layout = new BorderLayout();
        	frame.setLayout(layout);
        
        	JPanel northPanel = new JPanel();
        	JPanel westPanel = new JPanel();
        	JPanel centerPanel = new JPanel();
        	JPanel eastPanel = new JPanel();
        	JPanel southPanel = new JPanel();
        	
        	northPanel.setBackground(Color.RED);
        	westPanel.setBackground(Color.BLUE);
        	centerPanel.setBackground(Color.GREEN);
        	eastPanel.setBackground(Color.ORANGE);
        	southPanel.setBackground(Color.CYAN);
        	
        	northPanel.add(new JButton("A"));
        	northPanel.add(new JButton("B"));
        	northPanel.add(new JButton("C"));
        	
        	westPanel.add(new JButton("One"));
        	westPanel.add(new JButton("Two"));
        	westPanel.add(new JButton("Three"));
        	
        	eastPanel.add(new JButton("1"));
        	eastPanel.add(new JButton("2"));
        	eastPanel.add(new JButton("3"));
        	
        	southPanel.add(new JButton("Yes"));
        	southPanel.add(new JButton("No"));
        	southPanel.add(new JButton("Maybe"));
        	
        	frame.add(northPanel, BorderLayout.NORTH);
        	frame.add(westPanel, BorderLayout.WEST);
        	frame.add(centerPanel, BorderLayout.CENTER);
        	frame.add(eastPanel, BorderLayout.EAST);
        	frame.add(southPanel, BorderLayout.SOUTH);
        	
        	frame.setSize(500, 250);
        	frame.setVisible(true);
        }
    }
   
Compile and run the above code to see this program:

![](http://StaticVoidGames.com/tutorialsContent/swing/JPanels2.png)

This program adds some JButton instances to a few JPanels, splitting the whole window up into different sections.

### Nesting Layouts

The above code adds several JButtons to different JPanels, but the components aren't arranged in any particular order. How do we tell a JPanel how to position and size its components? That's right, with a layout! More specifically, you can set the LayoutManager of a JPanel exactly like you do with a JFrame, by calling the setLayout() method.

    import java.awt.BorderLayout;
    import java.awt.Color;
    
    import javax.swing.JButton;
    import javax.swing.JPanel;
    import javax.swing.JFrame;
     
    public class Main {
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Nesting Layouts");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	BorderLayout frameLayout = new BorderLayout();
        	frame.setLayout(frameLayout);
        
        	JPanel northPanel = new JPanel();
        	JPanel westPanel = new JPanel();
        	JPanel centerPanel = new JPanel();
        	JPanel eastPanel = new JPanel();
        	JPanel southPanel = new JPanel();
        	
        	northPanel.setBackground(Color.RED);
        	westPanel.setBackground(Color.BLUE);
        	centerPanel.setBackground(Color.GREEN);
        	eastPanel.setBackground(Color.ORANGE);
        	southPanel.setBackground(Color.CYAN);
        	
        	northPanel.setLayout(new BorderLayout());
        	westPanel.setLayout(new BorderLayout());
        	eastPanel.setLayout(new BorderLayout());
        	southPanel.setLayout(new BorderLayout());
        	
        	northPanel.add(new JButton("A"), BorderLayout.NORTH);
        	northPanel.add(new JButton("B"), BorderLayout.WEST);
        	northPanel.add(new JButton("C"), BorderLayout.EAST);
        	
        	westPanel.add(new JButton("One"), BorderLayout.NORTH);
        	westPanel.add(new JButton("Two"), BorderLayout.WEST);
        	westPanel.add(new JButton("Three"), BorderLayout.EAST);
        	
        	eastPanel.add(new JButton("1"), BorderLayout.NORTH);
        	eastPanel.add(new JButton("2"), BorderLayout.WEST);
        	eastPanel.add(new JButton("3"), BorderLayout.EAST);
        	
        	southPanel.add(new JButton("Yes"), BorderLayout.NORTH);
        	southPanel.add(new JButton("No"), BorderLayout.WEST);
        	southPanel.add(new JButton("Maybe"), BorderLayout.EAST);
        	
        	frame.add(northPanel, BorderLayout.NORTH);
        	frame.add(westPanel, BorderLayout.WEST);
        	frame.add(centerPanel, BorderLayout.CENTER);
        	frame.add(eastPanel, BorderLayout.EAST);
        	frame.add(southPanel, BorderLayout.SOUTH);
        	
        	frame.setSize(500, 250);
        	frame.setVisible(true);
        }
    }
   
This code gives a BorderLayout to each JPanel that contains JButtons.

![](http://StaticVoidGames.com/tutorialsContent/swing/NestingLayouts1.png)

You can even add a JPanel to another JPanel. By nesting JPanels with different layouts like that, you can achieve just about any overall layout for your GUI.

Full disclosure: You can actually add components to any Swing component, but for our purposes, we don't need to worry about that. For now, think of a JPanel as a rectangular component that you can add other components to.

### Null Layout

So far we've looked at using predefined layouts to size and place components. These layout managers handle the bounds of components as the window resizes. This saves us a lot of work, as it makes moving components around much easier- not just when the window is resized, but also if we change our mind about where the components should go.

For example, we might initially place our components in a JPanel with a vertical BoxLayout in the west section of a JFrame with a BorderLayout, but then change our mind and move the JPanel to the south section of the JFrame and change the vertical BoxLayout to a horizontal BoxLayout.

It's okay if not all of those words made sense to you, but the gist is: layout managers make your life much easier!

That being said, it's also possible to place your components manually. You do this using a null layout, or more specifically, by passing null into the setLayout() function.

    import javax.swing.JButton;
    import javax.swing.JFrame;
     
    public class Main {
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Null Layout");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	frame.setLayout(null);
        	
        	JButton button = new JButton("Okay");
        	
        	frame.add(button);
        	
        	frame.setSize(500, 500);
        	frame.setVisible(true);
        }
    }
   
If you run this code, you'll notice that we can't see the JButton. That's because we're using a null layout, which means we have to set the JButton's size and location!

    import javax.swing.JButton;
    import javax.swing.JFrame;
     
    public class Main {
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Null Layout");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	frame.setLayout(null);
        	
        	JButton button = new JButton("Okay");
        	button.setSize(100, 50);
        	button.setLocation(200, 400);
        	
        	frame.add(button);
        	
        	frame.setSize(500, 500);
        	frame.setVisible(true);
        }
    }
    
Now that we've set the JButton's size and location, the JButton should display as a rectangle in the middle of the bottom of the JFrame.

The coordinates passed into the size and location functions are in pixels, and the coordinate system starts at 0,0 in the upper-left corner of the window. If you're coming from Processing, this is the same coordinate system you're used to by now.

We can add as many components to a null layout as we want, as long as we set the size and location of each.

    import javax.swing.JButton;
    import javax.swing.JFrame;
     
    public class Main {
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Null Layout");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	frame.setLayout(null);
        	
        	JButton button1 = new JButton("One");
        	button1.setSize(100, 50);
        	button1.setLocation(200, 400);
        	
        	JButton button2 = new JButton("Two");
        	button2.setSize(100, 200);
        	button2.setLocation(350, 50);
        	
        	JButton button3 = new JButton("Three");
        	button3.setSize(200, 200);
        	button3.setLocation(50, 100);
        	
        	frame.add(button1);
        	frame.add(button2);
        	frame.add(button3);
        	
        	frame.setSize(500, 500);
        	frame.setVisible(true);
        }
    }
    
A null layout gives you the most control over your GUI, but you lose functionality that automagically works with a layout manager. For example, run the above code and try resizing the window. Notice how the buttons don't move or resize. Also, if we want to rearrange our gui, we have to modify the bounds of every component.

Long story short, you should know that using a null layout is an option, but you should almost always use an actual layout manager.

To see how other layout managers work, check out [this](http://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html) page.

### Exercises

  * Create a JFrame uses a BorderLayout. In each section of the BorderLayout, place a JPanel with one of the following: BorderLayout, BoxLayout (vertical), BoxLayout (horizontal), FlowLayout, and a null layout.
  * Create a calculator GUI with buttons for 0-9, the basic arithmetic functions, and a screen for displaying numbers. The gui doesn't have to do anything, just focus on getting the positioning correct. Hint: nested layouts are your friend!
  * Modify the above program to show a checkbox instead of a JLabel. The checkbox should be checked already when the window pops up. The API is your friend!
  * Look carefully at the GUI of some programs you use often. Can you identify the various layouts of different parts of the GUI? What kind of nested layouts might they use?

###  Next: [Listening for Actions](http://StaticVoidGames.com/tutorials/swing/actionListeners)
