#  [Intermediate Concepts](index.jsp)

## Mouse Input

So far, we've learned some the of basics of programming and animation. This is
actually already enough to do cool things like data visualization and art
displays, but if you want to make a game, chances are you want the user to
interact with the display. First, let's go over a couple ways to get
information from the mouse.

Something to perhaps keep in mind is that mouse input maps pretty easily to
touch screen input. So if you're looking to get into mobile development, this
might be pretty important to you!

### Polling the Mouse Position

You've probably already used the predefined mouseX and mouseY variables.
Processing defines them for you, and they hold the current value of the mouse
position. Using them is called polling, since you're asking Processing for the
values instead of reacting to changes, which we'll cover in a minute. You can
use the mouseX and mouseY variables like this:

    
    
    void draw(){
     ellipse(mouseX, mouseY, 50, 50); 
    }
    

![](mouseCircles.png)

But get creative! You could definitely make a game (or just a cool
visualization) using only the mouseX and mouseY variables!

### Polling Mouse Buttons

Processing has a few other predefined variables dealing with the mouse:
mousePressed and mouseButton. The mousePressed variable is a boolean and is
true when a mouse button is pressed down. The mouseButton variable holds which
mouse button is pressed and equals either LEFT, RIGHT, or CENTER.

    
    
    void draw() {
      if (mousePressed) {
        if (mouseButton == LEFT) {
          background(255, 0, 0);
        } 
        else if (mouseButton == RIGHT) {
          background(0, 255, 0);
        }
        else if (mouseButton == CENTER) {
          background(0, 0, 255);
        }
      }
      else {
        background(0, 0, 0);
      }
    }
    
    

This program simply draws a red background when the user is left-clicking, a
green background when right-clicking, and a blue background when the center
mouse button (usually the scroll wheel) is down. If no buttons are pressed, it
draws a black background.

Note: the LEFT, RIGHT, and CENTER values are actually other predefined
variables that hold values to help make your code more readable. Try printing
them out to see their values!

If you play with the above program, you might notice a few weird behaviors.
Try this:

  * Hold in the left mouse button, and the window displays red.
  * Continue holding in the left mouse button, and press the right button so both buttons are pressed.

  * The background changes to green, since the mouseButton variable contains the most recently pressed button.
  * Now release the right button, so only the left button is held in.
  * The background changes to black, even though the left button is still held in! What gives?

The mousePressed and mouseButton variables are meant for quick-and-easy logic
that doesn't have to be very exact. So when you release a mouse button,
Processing acts like you've released all the buttons. This might be good
enough for many applications, but games are probably going to need to be more
exact. How do we do that?

### Detecting Mouse Events

Remember that Processing automatically calls certain functions: the setup()
function at the very beginning, and then the draw() function 60 times per
second. There are other functions that are automatically called at certain
times if you define them, and you can use some of them to get more exact
information on mouse events. Let's just dive in with an example program:

    
    
    void draw(){
     text("Do mouse stuff!", 5, 25); 
    }
    
    void mousePressed(MouseEvent e){
     println("pressed"); 
    }
    
    void mouseReleased(MouseEvent e){
     println("released"); 
    }
    
    void mouseClicked(MouseEvent e){
     println("clicked"); 
    }
    
    void mouseMoved(MouseEvent e){
     println("moved"); 
    }
    
    void mouseDragged(MouseEvent e){
     println("dragged"); 
    }
    
    void mouseWheel(MouseEvent e){
     println("wheel"); 
    }
    
    

As you can see, there are several functions related to mouse actions. They all
take an instance of MouseEvent as an argument, and they give you more exact
information about the mouse. These functions are called automatically be
Processing. They might be a bit self-explanatory, but we'll go over them in
more detail:

  * The mousePressed() function is called when a mouse button is pressed in.
  * The mouseReleased() function is called when a mouse button is released.
  * The mouseClicked() function is called after the mouse is pressed and released.
  * The mouseMoved() function is called every time the mouse moves- this can be pretty often!
  * The mouseDragged() function is like the mouseMoved() function, but only when a mouse button is also pressed.
  * The mouseWheel() function is called when the mouse wheel is scrolled.

Clicking the mouse actually results in several method invocations. The
mousePressed() method is called when you first press the button, the
mouseReleased() function is called when you release it, and then the
mouseClicked() function is triggered right afterwards. And if you moved the
mouse during the click, the mouseDragged() function can also be called. If
this is confusing, play around with the above program to understand the order
of events.

The MouseEvent argument passed into these functions is an Object that contains
information such as which button was pressed or the amount scrolled. You don't
have to fully understand the MouseEvent Object, just know that it can give you
a bunch more information than the predefined mouse variables in Processing.

For example, this program uses the mouse functions and MouseEvent's
getButton() function to keep better track of which buttons are pressed:

    
    
    
    boolean leftMouseButtonDown = false;
    boolean rightMouseButtonDown = false;
    boolean middleMouseButtonDown = false;
    
    void draw() {
      
      background(0, 0, 0);
      
      if (leftMouseButtonDown) {
        background(255, 0, 0);
      }
      if (rightMouseButtonDown) {
        background(0, 255, 0);
      }
      if (middleMouseButtonDown) {
        background(0, 0, 255);
      }
    }
    
    void mousePressed(MouseEvent e) {
      if (e.getButton() == LEFT) {
        leftMouseButtonDown = true;
      }
      else if (e.getButton() == RIGHT) {
        rightMouseButtonDown = true;
      }
      else if (e.getButton() == CENTER) {
        middleMouseButtonDown = true;
      }
    }
    
    void mouseReleased(MouseEvent e) {
      println(e.getButton());
      if (e.getButton() == LEFT) {
        leftMouseButtonDown = false;
      }
      else if (e.getButton() == RIGHT) {
        rightMouseButtonDown = false;
      }
      else if (e.getButton() == CENTER) {
        middleMouseButtonDown = false;
      }
    }
    
    void mouseClicked(MouseEvent e) {
      println("clicked");
    }
    
    void mouseMoved(MouseEvent e) {
      println("moved");
    }
    
    void mouseDragged(MouseEvent e) {
      println("dragged");
    }
    
    void mouseWheel(MouseEvent e) {
      println("wheel");
    }
    
    

### Exercises

  * Modify the above program to show different colors when two buttons are held in.
  * Create a program with a background that gets darker or lighter when the user scrolls the mouse wheel. Hint: try the getAmount() method in MouseEvent!
  * Create a program that displays a square that the user can drag around.

###  Next: [Keyboard Input](KeyboardInput.jsp)

