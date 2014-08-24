#  Intro to Programming

## Exploring the Processing API

If you've been following the tutorials so far, you've succesfully implemented
your first program, executed it, and made some changes to its functionality.
You're well on your way to being a programmer! But what now? Where do you go
from here?

This tutorial introduces Processing's API and encourages experimentation by
showing you what can be done with just a few simple modifications to the
program you've already written and run.

## The API

You know how to use functions and variables in Processing, but how do you know
what kinds of functions and variables are available to you? The API is your
new best friend! An API, or [Application Programming
Interface](http://en.wikipedia.org/wiki/Application_programming_interface), is
basically a list of everything you can do in a language- think of it as a
dictionary for a programming language. Processing's API is
[here](http://processing.org/reference/), and it should be your first stop
when you're confused.

These tutorials will go over the functions and variables you see in
Processing's API in more depth, so don't feel overwhelemed. If you click on an
item in the API, you'll go to a reference page for that function or variable,
which will include more information including examples of how to use it. Going
through the API and trying out things that sound interesting isn't a bad way
to learn programming.

For example, a useful function that you'll use in almost every Processing
program you create is the setup() function. Find it in the API and click on it
to go to its [reference](http://processing.org/reference/setup_.html) page. We
can use the setup() function as well as the
[size()](http://processing.org/reference/size_.html) and background()
functions to improve our first program:

    
    
    void setup() {
      size(500, 500);
      background(0);  
    }
    
    void draw() {
      ellipse(mouseX, mouseY, 10, 10);
    }
    

Like the draw() function, Processing automatically calls the
[setup()](http://processing.org/reference/setup_.html) function- however, the
setup() function is only called once, when the program is first started. The
[size()](http://processing.org/reference/size_.html) function basically takes
two arguments- the width and height of the window. And the background()
function takes a single int representing how bright the background should be.
The above program starts up and the setup() function is called, which sets the
size of the window to 500 pixels wide by 500 pixels tall and the background to
black. Then the draw() function is called 60 times per second, which draws a
circle wherever the mouse is.

## Experiment!

You can play around with the simple program above to get some interesting
effects. For example, try out a few of these alternative programs. Try
combining pieces of each to see what happens!

This one uses the mouse's x and y positions to determine the height and width
of the circle, so the circle's size changes based on its location:

    
    
    void setup() {
      size(500, 500); 
      background(0); 
    }
    
    void draw() {
      ellipse(mouseX, mouseY, mouseY, mouseX);
    }
    

![](http://s3.StaticVoidGames.com/tutorials/intro/experiment1.png)

This one flips the x and y axis, so the circle no longer follows your mouse:

    
    
    void setup() {
      size(500, 500);
      background(0);  
    }
    
    void draw() {
      ellipse(mouseY, mouseX, 25, 25);
    }
    

And this one draws four circles, each based on a different reflection of the
mouse location:

    
    
    void setup() {
      size(500, 500); 
      background(10); 
    }
    
    void draw() {
      ellipse(mouseX, mouseY, 25, 25);
      ellipse(mouseY, mouseX, 25, 25);
      ellipse(mouseX, mouseX, 25, 25);
      ellipse(mouseY, mouseY, 25, 25);
    }
    

![](http://s3.StaticVoidGames.com/tutorials/intro/experiment2.png)

By moving the call to background() inside the draw() function, this program
paints over anything previously drawn each frame. This is a common practice in
games, since you only want to show the current frame.

    
    
    void setup() {
      size(500, 500); 
    }
    
    void draw() {
      background(0); 
      ellipse(mouseX, mouseY, 50, 50);
    }
    

### Things to Think About

  * Java's API is [here](http://docs.oracle.com/javase/7/docs/api/). Processing hides most of that from you and focuses on things you need to make visual programs. What are the benefits of that approach? What are the downsides? 
  * Check out Processing's API page, and draw a rectangle instead of an ellipse in some of the above programs.
  * The fill() function tells Processing what color to fill shapes with. Like the background() function, you can give it a single int argument specifying how dark or light the shapes should be. Can you take that description and draw gray circles instead of white? Consult the API if you have to!
  * Combine the different ideas demonstrated in the above programs to create your own program. What kinds of effects can you achive?

###  Next: [Dealing with Errors](DebuggingProcessing.jsp)

