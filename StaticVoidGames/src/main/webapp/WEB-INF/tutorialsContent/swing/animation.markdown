#  [Swing](index.jsp)

## Mouse and Keyboard Listeners

Before reading this tutorial, you should know:

  * How to write, compile, and run a Swing GUI that consists of several components and ActionListeners that update those components to respond to user input.
  * The basics of OOP, including inheritance as discussed in the [OOP Tutorial](http://staticvoidgames.com/tutorials/objects/Inheritance.jsp). 
  * How to perform custom painting by extending JPanel and overriding the paintComponent() method.

After this tutorial, you'll be able to:

  * Detect mouse and keyboard events and update your display in response!

By now you know how to create a GUI using Java's predefined classes such as
JFrame, JPanel, JButton, and JLabel. You also know how to create a custom
display by extending JPanel and overriding the paintComponent() method. You
should also know how to use an ActionListener to detect events such as
clicking a button.

But what about more general events, such as when the user presses a key or
clicks something other than a button? These types of events are essential to
interactive visualizations and games. Luckily, Java makes it very easy to tie
into these events using three simple interfaces: MouseListener,
MouseMotionListener, and KeyListener.

Remember that an interface declares a set of methods that any implementations
(classes that implement the interface) must define. We've seen this with
ActionListener, and other types of listeners are very similar.

KeyListeners, MouseListeners, and MouseMotionListeners can be added to any
Swing component (as opposed to ActionListeners, which can only be added to
components with predefined actions). When you add a listener to a component,
Swing automatically detects events in that component and calls the
corresponding functions of the listener.

### KeyListener

The KeyListener interface has three functions: keyPressed(), keyTyped(), and
keyReleased(). We can define a KeyListener by implementing KeyListener and
defining those functions:

    
    
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
    
    public class MyKeyListener implements KeyListener{
    
    	public void keyTyped(KeyEvent e) {
    		System.out.println("Key typed: " + e.getKeyChar());
    	}
    
    	public void keyPressed(KeyEvent e) {
    		System.out.println("Key pressed: " + e.getKeyChar());
    	}
    
    	public void keyReleased(KeyEvent e) {
    		System.out.println("Key released: " + e.getKeyChar());
    	}
    }
    
    

Now that we've defined a KeyListener, we can add it to a component. This code
adds it to a JPanel:

    
    import javax.swing.JFrame;
    
    import javax.swing.JFrame;
    import javax.swing.JPanel;
    
    public class Main{
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Custom Painting");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	JPanel panel = new JPanel();
        	
        	panel.addKeyListener(new MyKeyListener());
        	
        	frame.add(panel);
        	
        	frame.setSize(500, 500);
        	frame.setVisible(true);
        	
        	//make sure the JPanel has the focus
        	panel.requestFocusInWindow();
        }
    }
    

This code should look pretty similar to what you'd expect based on how
ActionListener works. We create a JPanel, then add an instance of our
MyKeyListener class to it. Then we add the JPanel to a JFrame and show it to
the user. The only weird part is the call to the requestFocusInWindow()
method.

The requestFocusInWindow() method makes sure that the JPanel has the focus in
the window. Java isn't smart enough to know which component should receive
user input (imagine a more complicated gui with several JPanels, JLabels,
etc), so we have to tell it which component has the focus. If you forget this
line, no component will have the focus so none of your events will be
detected!

Run this code and press different keys to see your KeyListener in action!

### Detecing Non-Character Keys

If you play with the above program, you'll notice that character keys like
letters, numbers, and symbols work fine. However, non-character keys like
shift, ctrl, and the arrow keys do not work. This is because there are no
characters that represent the arrow keys. We can still detect these kinds of
keys, but we have to check for them directly:

    
    
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
    
    public class MyKeyListener implements KeyListener{
    
    	@Override
    	public void keyTyped(KeyEvent e) {
    
    		if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
    			System.out.println("Right typed.");
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
    			System.out.println("Left typed.");
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_UP ) {
    			System.out.println("Up typed.");
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
    			System.out.println("Down typed.");
    		}
    		else{
    			System.out.println("Key typed: " + e.getKeyChar());
    		}
    	}
    
    	@Override
    	public void keyPressed(KeyEvent e) {
    
    		if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
    			System.out.println("Right pressed.");
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
    			System.out.println("Left pressed.");
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_UP ) {
    			System.out.println("Up pressed.");
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
    			System.out.println("Down pressed.");
    		}
    		else{
    			System.out.println("Key pressed: " + e.getKeyChar());
    		}
    	}
    
    	@Override
    	public void keyReleased(KeyEvent e) {
    
    		if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
    			System.out.println("Right released.");
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
    			System.out.println("Left released.");
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_UP ) {
    			System.out.println("Up released.");
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
    			System.out.println("Down released.");
    		}
    		else{
    			System.out.println("Key released: " + e.getKeyChar());
    		}
    	}
    }
    
    

This code compares the value returned from KeyEvent's getKeyCode() method to
the predefined static variables VK_RIGHT, VK_LEFT, VK_UP, and VK_DOWN of the
KeyEvent class. Run this code to see that you can detect arrow keys, which are
pretty important!

### MouseListener

The MouseListener interface is very similar to the KeyListener interface. Here
we define a class that implements the MouseListener interface and defines all
of the necessary methods:

    
    
    import java.awt.event.MouseEvent;
    import java.awt.event.MouseListener;
    
    public class MyMouseListener implements MouseListener{
    
    	public void mouseClicked(MouseEvent e) {
    		System.out.println("Mouse clicked at " + e.getX() + ", " + e.getY());
    	}
    
    	public void mousePressed(MouseEvent e) {
    		System.out.println("Mouse pressed at " + e.getX() + ", " + e.getY());
    	}
    
    	public void mouseReleased(MouseEvent e) {
    		System.out.println("Mouse released at " + e.getX() + ", " + e.getY());
    	}
    
    	public void mouseEntered(MouseEvent e) {
    		System.out.println("Mouse entered at " + e.getX() + ", " + e.getY());
    	}
    
    	public void mouseExited(MouseEvent e) {
    		System.out.println("Mouse exited at " + e.getX() + ", " + e.getY());
    	}
    }
    
    

The mouseEntered() function is called when the mouse enteres the component,
and the mouseExit() function is called when it leaves the component. The other
methods are probably pretty self-explanatory, but I recommend playing around
to make sure everything works how you expect!

Now that we have a MouseListener implementation, we can add it to our JPanel:

    
    
    import javax.swing.JFrame;
    import javax.swing.JPanel;
    
    public class Main{
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Custom Painting");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	JPanel panel = new JPanel();
        	
        	panel.addKeyListener(new MyKeyListener());
        	panel.addMouseListener(new MyMouseListener());
        	
        	frame.add(panel);
        	
        	frame.setSize(500, 500);
        	frame.setVisible(true);
        	
        	//make sure the JPanel has the focus
        	panel.requestFocusInWindow();
        }
    }
    

Run this program and click the mouse around the screen to make sure your
MouseListener works.

### MouseMotionListener

The MouseListener interface is used for detecting mouse clicks and the cursor
entering and exiting a component. However, to detect when the mouse is moved,
you have to use a MouseMotionListener. The MouseMotionListener interface works
just like you'd expect, defining functions that Swing automatically calls when
certain events happen. The MouseMotionListener interface defines two
functions: mouseDragged() and mouseMoved(). Here's an example class that
implements MouseMotionListener:

    
    
    import java.awt.event.MouseEvent;
    import java.awt.event.MouseMotionListener;
    
    
    public class MyMouseMotionListener implements MouseMotionListener{
    
    	public void mouseDragged(MouseEvent e) {
    		System.out.println("Mouse dragged: " + e.getX() + ", " + e.getY());
    	}
    
    	public void mouseMoved(MouseEvent e) {
    		System.out.println("Mouse moved: " + e.getX() + ", " + e.getY());
    	}
    }
    
    

Now that we've implemented MouseMotionListener, we can add it to our JPanel,
just like the other listeners:

    
    
    import javax.swing.JFrame;
    import javax.swing.JPanel;
    
    public class Main{
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Custom Painting");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	JPanel panel = new JPanel();
        	
        	panel.addKeyListener(new MyKeyListener());
        	panel.addMouseListener(new MyMouseListener());
        	panel.addMouseMotionListener(new MyMouseMotionListener());
        	
        	frame.add(panel);
        	
        	frame.setSize(500, 500);
        	frame.setVisible(true);
        	
        	//make sure the JPanel has the focus
        	panel.requestFocusInWindow();
        }
    }
    

This program uses our three different listeners to detect keyboard events,
mouse clicks, and mouse movement.

### Implementing Multiple Interfaces

Remember that a class can implement more than one interface! This means that
we can collapse our three above classes into a single class that implements
all three listener interfaces:

    
    
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
    import java.awt.event.MouseEvent;
    import java.awt.event.MouseListener;
    import java.awt.event.MouseMotionListener;
    
    public class MyListener implements KeyListener, MouseListener, MouseMotionListener{
    
    
    	public void keyTyped(KeyEvent e) {
    
    		if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
    			System.out.println("Right typed.");
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
    			System.out.println("Left typed.");
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_UP ) {
    			System.out.println("Up typed.");
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
    			System.out.println("Down typed.");
    		}
    		else{
    			System.out.println("Key typed: " + e.getKeyChar());
    		}
    	}
    
    	public void keyPressed(KeyEvent e) {
    
    		if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
    			System.out.println("Right pressed.");
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
    			System.out.println("Left pressed.");
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_UP ) {
    			System.out.println("Up pressed.");
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
    			System.out.println("Down pressed.");
    		}
    		else{
    			System.out.println("Key pressed: " + e.getKeyChar());
    		}
    	}
    
    	public void keyReleased(KeyEvent e) {
    
    		if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
    			System.out.println("Right released.");
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
    			System.out.println("Left released.");
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_UP ) {
    			System.out.println("Up released.");
    		} 
    		else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
    			System.out.println("Down released.");
    		}
    		else{
    			System.out.println("Key released: " + e.getKeyChar());
    		}
    	}
    	
    
    
    	public void mouseClicked(MouseEvent e) {
    		System.out.println("Mouse clicked at " + e.getX() + ", " + e.getY());
    	}
    
    	public void mousePressed(MouseEvent e) {
    		System.out.println("Mouse pressed at " + e.getX() + ", " + e.getY());
    	}
    
    	public void mouseReleased(MouseEvent e) {
    		System.out.println("Mouse released at " + e.getX() + ", " + e.getY());
    	}
    
    	public void mouseEntered(MouseEvent e) {
    		System.out.println("Mouse entered at " + e.getX() + ", " + e.getY());
    	}
    
    	public void mouseExited(MouseEvent e) {
    		System.out.println("Mouse exited at " + e.getX() + ", " + e.getY());
    	}
    
    	
    	public void mouseDragged(MouseEvent e) {
    		System.out.println("Mouse dragged: " + e.getX() + ", " + e.getY());
    	}
    
    	public void mouseMoved(MouseEvent e) {
    		System.out.println("Mouse moved: " + e.getX() + ", " + e.getY());
    	}
    
    }
    

This class implements all three listener interfaces, which means it *is* a
MouseListener, a MouseMotionListener and a KeyListener. We can use an instance
of this class as all three listeners instead of splitting out logic up into
multiple classes:

    
    
    import javax.swing.JFrame;
    import javax.swing.JPanel;
    
    public class Main{
     
        public static void main(String[] args) {
        	
        	JFrame frame = new JFrame("Custom Painting");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	JPanel panel = new JPanel();
        	
        	//this object is all three listeners!
        	MyListener listener = new MyListener();
        	
        	panel.addKeyListener(listener);
        	panel.addMouseListener(listener);
        	panel.addMouseMotionListener(listener);
        	
        	frame.add(panel);
        	
        	frame.setSize(500, 500);
        	frame.setVisible(true);
        	
        	//make sure the JPanel has the focus
        	panel.requestFocusInWindow();
        }
    }
    

This approach can make our life easier if we want to control a model from
multiple listeners. This will come in handy in the next few lessons!

### Exercises

  * Use the getWhen() method of various events to figure out how much time is really between a keyPressed and a keyReleased event.
  * Create a program that displays (in a JLabel) the coordinates of the mouse.
  * Create a program that displays 10 or so JButtons, each with a different letter. Make it so the program automatically clicks a JButton when the user types the corresponding letter on the keyboard. Hint: the JButton class has a doClick() method!

### Coming soon: Animation!
