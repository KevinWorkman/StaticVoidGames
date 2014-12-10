#  [Swing](index.jsp)

## Action Listeners

Before reading this tutorial, you should know:

  * How to write, compile, and run a simple Swing GUI.
  * The basics of objects (including how classes can extend other classes), methods, variables, etc.
  * How to use layout managers to place multiple components in a JFrame.

After this tutorial, you'll be able to:

  * Listen for user actions, such as clicking on a button.

By now you know how to create a GUI, as well as use nested layouts to create non-trivial displays. But what use is a fancy GUI if none of the buttons do anything? This tutorial will show you how to detect user actions and update the GUI accordingly.

### OOP Review

Before we get into action listeners, it's probably a good idea to review some object oriented principles. If any of this is confusing, go back and read the [OOP tutorial](http://StaticVoidGames.com/tutorials/objects/index) and come back!

We've been using Objects, so you've seen the basics:

  * An object is a collection of related values and methods.
  * A class is a blueprint for creating a particular type of object. For example the Cat class might contain a String name, an int age, and a method named meow().
  * An instance is a specific copy of a class with its own copies of the values and variables in that class. For example we might have one instance of Cat with a name "Stanley" and an age of 6, and another cat instance

And if you went through the [inheritance tutorial](http://staticvoidgames.com/tutorials/objects/inheritance), you should be familiar with more advanced OOP:

  * A class can extend another class to expand the extended class's behavior.
  * An interface is like a class, except none of its methods have been defined. An interface must be implemented by a concrete class.
  * A child class (a class that extends another class or implements an interface) is an instance of the parent class or interface.

ActionListener

With the above OOP concepts in mind, let's meet ActionListener. ActionListener is a predefined interface with a single method named actionPerformed().

Remember that ActionListener is an interface, which means you can't do something like this:

    import java.awt.event.ActionListener;
     
    public class Main{
     
        public static void main(String[] args) {
        	
        	//This is a compiler error because ActionListener is an interface, 
        	//and interfaces can't be instantiated like this.
        	//They must be implemented by a concrete class!
        	ActionListener listener = new ActionListener();
        	
        	System.out.println(listener.toString());
        }
    }
    
To create an ActionListener, you first have to implement the ActionListener interface in a concrete class. You could write a class like this:

    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    
    public class MyListener implements ActionListener{
    
    	//this method overrides the actionPerformed() method of the ActionListener interface
    	public void actionPerformed(ActionEvent e) {
    		System.out.println("Hello world!");
    	}
    
    }
    
This class extends ActionListener, which means that it must define any methods in ActionListener- in this case only one, the actionPerformed() method.

We can now create an instance of MyListener, which *is an* ActionListener since it contains the actionPerformed() method.

    public class Main{
     
        public static void main(String[] args) {
        	
        	//MyListener implements ActionListener, so it *is an* ActionListener
        	MyListener listener = new MyListener();
        	
        	System.out.println(listener.toString());
        }
    }
    
This Main class creates an instance of MyListener, which *is an* ActionListener. You can run this code, but it doesn't do very much... yet.

### Using an ActionListener in Swing

The reason we're paying so much attention to the ActionListener interface is because Swing uses ActionListeners all over the place. Many user actions, such as clicking a button, trigger an ActionEvent that you can intercept using an ActionListener. This might sound complicated, but all it takes is passing an instance of ActionListener into an addActionListener() method. Here's an example:

    import java.awt.FlowLayout;
    
    import javax.swing.JButton;
    import javax.swing.JFrame;
    
    
    public class Main{
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Action Listener");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	frame.setLayout(new FlowLayout());
        	
        	JButton button = new JButton("Click me!");
        	frame.add(button);
        	
        	//MyListener implements ActionListener, so it *is a* ActionListener
        	MyListener listener = new MyListener();
        	
        	//since listener is an instance of ActionListener,
        	//we can pass it into the JButton's addActionListener() method.
        	button.addActionListener(listener);
        	
        	frame.setSize(300, 100);
        	frame.setVisible(true);
        }
    }
    
This code uses the MyListener class we created above. Now, whenever you click the button, the actionPerformed() method of the instance of MyListener is called, and the "Hello world!" message is printed to the console.

We don't really need to worry about how the actionPerformed() method is called, or where the ActionEvent instance is created- Swing handles all of that for us. All we need to know is that if we pass an instance of ActionListener into the addActionListener() method of a Swing component, the actionPerformed() method of that instance will be called whenever the user takes an action on that component. Different Swing components have different actions- JButton's action is triggered when the user clicks the button, JCheckBox's action is triggered when the box is checked or unchecked. Consult the API for other component actions!

### Updating the GUI

Printing to the console is a great way to make sure our code is working, but usually you want to provide feedback to the user by updating the GUI. Let's start by modifying our MyListener class to keep track of the number of times the actionPerformed() method is called:

    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    
    public class MyListener implements ActionListener{
    	
    	private int clickCount = 0;
    
    	public void actionPerformed(ActionEvent e) {
    		
    		clickCount++;
    		
    		System.out.println("You've clicked " + clickCount + " times.");
    	}
    }
    
Update your code to use this as your ActionListener and see what it does. This code uses a class variable called clickCount to keep track of the number of times the actionPerformed() method is called. When the method is called, the variable is incremented and its value is printed out.

We can modify this code to update a label instead of printing to the console:

    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import javax.swing.JLabel;
    
    public class MyListener implements ActionListener{
    	
    	private int clickCount = 0;
    	
    	private JLabel label;
    	
    	public MyListener(JLabel label){
    		this.label = label;
    	}
    
    	public void actionPerformed(ActionEvent e) {
    		
    		clickCount++;
    		
    		label.setText("You've clicked " + clickCount + " times.");
    	}
    }
    
We've added a constructor that takes an instance of JLabel as an argument. Inside the constructor, that instance of JLabel is saved into a variable that we can access outside of the constructor. Then in the actionPerformed() method, we set the text of the JLabel referenced by the variable, exactly like we did the print statements.

Now all we need to do is create an instance of JLabel to pass into the constructor!

    import java.awt.FlowLayout;
    
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    
    public class Main{
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Action Listener");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	frame.setLayout(new FlowLayout());
        	
        	JButton button = new JButton("Click me!");
        	frame.add(button);
        	
        	JLabel label = new JLabel("You've clicked 0 times.");
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
    

This code creates a JLabel and passes it into the MyListener constructor. We already wrote the code that updates the text of the JLabel, so if you run this code you should see a fully functioning GUI!

### Multiple Buttons

So far we know how to respond to user input of a single JButton. But often, we want to support multiple inputs, not just one. Let's say we want to have two buttons: one that increases the click count, and another that decreases it. Like much of programming, there are a number of ways to do this!

One way to support multiple buttons is by using a single instance of ActionListener along with the ActionEvent getSource() method, which returns the component that generated the action. We can then compare that value to our original JButtons to see which button generated the event. Let's see the ActionListener first:

    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    
    import javax.swing.JButton;
    import javax.swing.JLabel;
    
    public class MyListener implements ActionListener{
    	
    	private int clickCount = 0;
    	
    	private JButton addButton;
    	private JButton subtractButton;
    	private JLabel label;
    	
    	public MyListener(JButton addButton, JButton subtractButton, JLabel label){
    		this.addButton = addButton;
    		this.subtractButton = subtractButton;
    		this.label = label;
    	}
    
    	public void actionPerformed(ActionEvent e) {
    		
    		if(e.getSource() == addButton){
    			clickCount++;
    		}
    		else if(e.getSource() == subtractButton){
    			clickCount--;
    		}
    		
    		label.setText("You've clicked " + clickCount + " times.");
    	}
    }
    
Our ActionListener's constructor now takes two additional arguments: an instance of JButton to indicate addition, and an instance of JButton to indicate subtraction. We reference these JButton instances in class variables exactly like we did with our JLabel. Then in the actionPerformed() method, we use the ActionEvent.getSource() method to determine which JButton was the source of the event. If it was the add button, we increment the count, and if it was the subtract button, we decrement it. Then we update the text of the JLabel, just like before.

To use this ActionListener, we have to update our Main class to include another button:

    import java.awt.FlowLayout;
    
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    
    public class Main{
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Action Listener");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	frame.setLayout(new FlowLayout());
        	
        	JButton addButton = new JButton("+");
        	frame.add(addButton);
        	
        	JButton subtractButton = new JButton("-");
        	frame.add(subtractButton);
        	
        	JLabel label = new JLabel("You've clicked 0 times.");
        	frame.add(label);
        	
        	//create the listener
        	MyListener listener = new MyListener(addButton, subtractButton, label);
        	
        	//add the listener to both JButtons
        	addButton.addActionListener(listener);
        	subtractButton.addActionListener(listener);
        	
        	frame.setSize(225, 100);
        	frame.setVisible(true);
        }
    }
   
This code now creates two JButtons and a JLabel and passes them all into the MyListener constructor. We then add the ActionListener to each of our JButton instances. Notice that we only have a single instance of MyListener! What would happen if we had more than one? (Try it and find out!)

### MVC

Another way to handle multiple ActionListeners is to use MVC, or the model-view-controller pattern we discussed previously. We already have the view (the JLabel) and the controller (the JButtons and the ActionListener), so now all we need is a model.

    public class CountModel {
    	
    	private int count = 0;
    	
    	public void add(){
    		count++;
    	}
    	
    	public void subtract(){
    		count--;
    	}
    	
    	public int getCount(){
    		return count;
    	}
    }
    
This model keeps track of a count value and contains methods for adding and subtracting, as well as a method to obtain the count value. Now that we have a model, we can write two separate ActionListeners that use it:
    
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    
    import javax.swing.JLabel;
    
    public class AddListener implements ActionListener{
    	
    	private CountModel model;
    	private JLabel label;
    	
    	public AddListener(CountModel model, JLabel label){
    		this.model = model;
    		this.label = label;
    	}
    
    	public void actionPerformed(ActionEvent e) {
    		
    		model.add();
    		
    		label.setText("You've clicked " + model.getCount() + " times.");
    	}
    }
    
    
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    
    import javax.swing.JLabel;
    
    public class SubtractListener implements ActionListener{
    	
    	private CountModel model;
    	private JLabel label;
    	
    	public SubtractListener(CountModel model, JLabel label){
    		this.model = model;
    		this.label = label;
    	}
    
    	public void actionPerformed(ActionEvent e) {
    		
    		model.subtract();
    		
    		label.setText("You've clicked " + model.getCount() + " times.");
    	}
    }
    
These two ActionListeners both use our model. One calls the add() method of our model, and the other calls the subtract() method. Now we just need to create the view and glue the pieces together:

    import java.awt.FlowLayout;
    
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    
    public class Main{
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("MVC Listeners");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	frame.setLayout(new FlowLayout());
        	
        	JButton addButton = new JButton("+");
        	frame.add(addButton);
        	
        	JButton subtractButton = new JButton("-");
        	frame.add(subtractButton);
        	
        	JLabel label = new JLabel("You've clicked 0 times.");
        	frame.add(label);
        	
        	CountModel model = new CountModel();
        	
        	//create the listeners
        	AddListener addListener = new AddListener(model, label);
        	SubtractListener subtractListener = new SubtractListener(model, label);
        	
        	//add the listeners to each JButton
        	addButton.addActionListener(addListener);
        	subtractButton.addActionListener(subtractListener);
        	
        	frame.setSize(225, 100);
        	frame.setVisible(true);
        }
    }
    
In our gui code, we now create an instance of our CountModel, which we then pass in to each of our ActionListeners. Finally, we add each ActionListener to its corresponding JButton.

### Anonymous Classes

We've been creating classes that implement ActionListeners, giving those classes their own names, and putting them in their own files. This approach works perfectly, but it might be overkill for our purposes. For example, in our above code, we only use one instance of AddListener and SubtractListener. Do we really need to create a whole extra file for each?

The answer is no. Since we only use a single instance of each class, we can use anonymous classes instead. An anonymous class is a way to define a subclass (in this case an implementation of ActionListener) without giving that class its own file, or even a name- that's why they're called anonymous classes!

To create an anonymous class, you use the new keyword along with the name of the parent class (in this case ActionListener), just like a constructor. You then give that particular instance a body by putting curly brackets {} right after. Finally, you define any methods from the parent class that you want to override.

This might sound confusing, but hopefully an example is more obvious:

    import java.awt.FlowLayout;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    
    public class Main{
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Anonymous Listeners");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	frame.setLayout(new FlowLayout());
        	
        	JButton addButton = new JButton("+");
        	frame.add(addButton);
        	
        	JButton subtractButton = new JButton("-");
        	frame.add(subtractButton);
        	
        	//variables used inside anonymous classes have to be final
        	final JLabel label = new JLabel("You've clicked 0 times.");
        	frame.add(label);
        	
        	final CountModel model = new CountModel();
        	
        	//this automatically creates an implementation of ActionListener
        	//this class doesn't have a name- hence anonymous class
        	ActionListener addListener = new ActionListener(){
    
    			public void actionPerformed(ActionEvent e) {
    				model.add();
    				label.setText("You've clicked " + model.getCount() + " times.");
    			}
        	};
        	
        	ActionListener subtractListener = new ActionListener(){
    
    			public void actionPerformed(ActionEvent e) {
    				model.subtract();
    				label.setText("You've clicked " + model.getCount() + " times.");
    			}
        	};
        	
        	//add the listeners to each JButton
        	addButton.addActionListener(addListener);
        	subtractButton.addActionListener(subtractListener);
        	
        	frame.setSize(225, 100);
        	frame.setVisible(true);
        }
    }
    

This code does the same thing as our previous program, but now we don't need separate AddListener and SubtractListener classes.

You can use named classes or anonymous classes for cases like these. As a general rule, anonymous classes are used for one-time-use cases where they don't have to be reused or dealt with in multiple places. Swing listeners are a perfect example of that, so you'll probably see a lot of anonymous classes in Swing code. But if you're more comfortable with named classes, by all means use them instead!

### Exercises

  * The first example ActionListener program called the toString() method. Where is that method defined? What does it return? What is printed out?
  * Modify the above program to include a third button that resets the count.
  * Modify your calculator program (the one you wrote for the layout tutorial) so that it actually works!

###  Next: [RockPaperScissors](http://StaticVoidGames.com/tutorials/swing/rockPaperScissors)
