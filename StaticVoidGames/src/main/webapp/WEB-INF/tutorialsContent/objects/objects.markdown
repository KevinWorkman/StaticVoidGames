#  [Objects](index.jsp)

## OOP

A programming paradigm is a collection of ideas that help a programmer think
about how a programming language works and to organize a program's code. These
paradigms allow a programmer to think on a higher level instead of worrying
about the underlying machine code that's actually being executed behind the
scenes. There are a few types of programming paradigms that approach these
issues in different ways (including imperative and functional langauges), and
many languages mix several types of paradigms. Java uses Object-Oriented
Programming, or OOP, and by association so does Processing. So for now we'll
focus on that.

### What is an Object?

At its core, OOP uses the concept of Objects to organize a program. An Object
is a group of variables and functions organized in a way that makes sense: for
example, a Point object might contain an x and y variable, and perhaps a
method that draws the point. This makes your life easier if you want to draw
10 points- instead of keeping track of 20 variables and drawing 10 different
things, you can simply tell each point to take care of itself.

You can think of an Object in OOP in a very similar way to how you think about
objects in real life: you might have a bottle object that has a certain
capacity, a current volume, and perhaps a liquid type. You might have a few of
these bottle object laying around, all with different capacities (sizes),
different current volumes (how much liquid is held), and different liquid
types (one water bottle, a soda bottle, etc). To extend the metaphor (that's a
pun that you'll understand at the end of this tutorial), you might even have
different types of bottles with different attachments (baby bottles) or uses
(bottle rockets).

### Defining Objects

An Object is any item that has attributes and any number of possible actions
it can take. Related items (say, a bowling ball and a bouncy ball) might
actually be two items of the same type of object- for example a bowling ball
and a bouncy ball might both be Ball objects, with traits including their
size, their location, and their color.

In programming terms, the definition for a type of object is called a class.
You can think of a class as a blueprint telling the computer how to create a
certain type of Object, with spaces to fill in the details.

The Ball class, which again you can think of as a blueprint for making
different kinds of Ball objects, might look like this:

    
    
    class Ball {
      float radius;
      float x;
      float y;
      color c;
    }
    
    

A class is defined using the class keyword, followed by the name of the class
(which by convention should start with a capital letter), and the body of the
class contains any variables or methods belonging to that class.

You can put a class definition in a Processing sketch the same way you put
methods or variables in a sketch. Here's an example, but note that the class
isn't actually being used yet!

    
    
    
    //variables might go here
    
    void setup(){
     Ball myBall = new Ball();
     
     //just example code, program doesn't do anything
     println("Exiting, not exciting.");
     exit();
    }
    
    class Ball {
      float radius;
      float x;
      float y;
      color c;
    }
    

You can also protect your sanity by putting each class in its own file. From
the dropdown next to your sketch tab, select "new tab", then specify the name
of the file (in this case, calling the file Ball would make sense). Then put
the Ball class in that new file. This keeps your sketch a little cleaner,
especially when you have a bunch of classes. These tutorials will keep all
classes in the main sketch tab just to make it easy to copy and paste code,
but feel free to use multiple files instead.

Note: This only scratches the surface of how classes are defined. For example,
you can do things like define classes inside other classes, or even inside a
single method, if statement, or for loop- but remember how scope works! For
our purposes, top-level classes defined in the main sketch tab or in their own
file will work just fine.

### Creating Objects

A class is like a plan for creating a type of object, with blanks that we fill
in to control different aspects of each individual object. But a plan by
itself is not an actual object. To create a particular type of object, we have
to fill in the blanks of that plan and feed that plan into some manufacturing
process. Similarly, to create an object of a particular type of class, we have
to fill in the blanks of that class and tell Processing to manufacture that
type of object. We do that with the new keyword.

Once an object of a particular class has been created using the new keyword,
we can store that object in a variable exactly like we've been doing with
primitive ints, floats, and booleans. The variable type is the name of the
class.

This example simply creates a Ball object, stores it in a Ball variable called
myBall, and exits.

    
    
    void setup(){
     Ball myBall = new Ball();
     
     //just example code, program doesn't do anything
     exit();
    }
    
    class Ball {
      float radius;
      float x;
      float y;
      color c;
    }
    

This is new syntax, so let's take a closer look. We create a Ball object using
the new keyword, followed by the name of the class we want to use as a
blueprint, followed by a pair of parenthesis. We then store that object in the
myBall variable, which is of type Ball. We don't actually do anything with the
ball we just created, but we're getting there!

### Using Objects

We now know how to create a Ball object. How do we use it? Given a variable of
a particular type of object, we can access its members (that's just a shorter
way to say "anything inside an object, like its variables and methods") by
using the dereference operator. That might sound scary, but in Java and
Processing the dereference operator is just a dot! Using the dot operator on
an object variable gives you access to that particular object's members, which
you can then treat the same way you've been treating variables and methods
this whole time:

    
    
    void setup(){
    
    Ball myBall = new Ball();
    
    void setup(){
      size(100, 200);
      
      myBall.x = 50; 
      myBall.y = 100;
      myBall.radius = 20;
      myBall.c = color(0, 255, 0);
    }
    
    void draw(){
      background(0);
      
      fill(myBall.c);
      ellipse(myBall.x, myBall.y, myBall.radius, myBall.radius);
    }
    
    class Ball {
      float radius;
      float x;
      float y;
      color c;
    }
    

The above program creates a Ball object and stores it in the myBall variable.
It then sets each of that object's variables in the setup() function. Then in
the draw() function, the program uses those variables to draw a ball.

### Constructors

The above code will work perfectly fine, but there's an easier way to set an
object's variables. Instead of setting each one individually, we can pass
values into a constructor that will set them for us.

A constructor looks a lot like a method, except it has no return type and has
the same name as the class itself. You declare any parameters that the
constructor takes, exactly as you would with a method. Then you can use those
parameters inside the constructor to set the class variables. Here's an
example:

    
    
    
    Ball myBall = new Ball(50, 100, color(0, 255, 255));
    
    void setup(){
      size(100, 200);
    }
    
    void draw(){
      background(0);
      
      fill(myBall.c);
      ellipse(myBall.x, myBall.y, myBall.radius, myBall.radius);
    }
    
    class Ball {
      float radius;
      float x;
      float y;
      color c;
      
      Ball(float x, float y, color myColor){
        
        //use this keyword to refer to class members with
        //same name as method or constructor parameters
        this.x = x;
        this.y = y;
        
        //you could also just give them different names
        c = myColor;
        
        //you can also give class variables default values
        radius = 25;
      }
    }
    

The above code shows a couple ways to use constructor parameters.

### This Keyword

You might notice the weird this keyword inside the above constructor. The this
keyword is a handy way to distinguish between class variables and
method/constructor parameters that have the same name. The this keyword acts
exactly like any other object variable, so you can use the dot operator to
refer to the class variables and methods specifically.

The this keyword comes in handy for more advanced stuff, but for now, you can
use it to make it easier to keep track of class variables.

### Using an Object

Remember our bouncing ball program, which uses few different variables to show
a ball moving around the screen:

    
    
    float ballX = 100;
    float ballY = 100;
    float xSpeed = 3;
    float ySpeed = 5;
    
    float radius = 25;
    
    void setup() {
      size(500, 500); 
      rectMode(CORNER);
      ellipseMode(CENTER);
      ellipseMode(RADIUS);
    }
    
    void draw() {
    
      background(0);
    
      //will the ball leave the screen?
      if (ballX+xSpeed < 0 || ballX+xSpeed > width) {
        xSpeed *= -1;
      }
      if (ballY+ySpeed < 0 || ballY+ySpeed > height) {
        ySpeed *= -1;
      }
    
      ballX += xSpeed;
      ballY += ySpeed;
    
      ellipse(ballX, ballY, radius, radius);
    }
    

We can modify this code to use a Ball object instead:

    
    
    Ball ball;
    
    void setup() {
      size(500, 500);
      ellipseMode(CENTER);
      ellipseMode(RADIUS);
      
      ball = new Ball(100, 100);
    }
    
    void draw() {
    
      background(0);
    
      //will the ball leave the screen?
      if (ball.x+ball.xSpeed < 0 || ball.x+ball.xSpeed > width) {
        ball.xSpeed *= -1;
      }
      if (ball.y+ball.ySpeed < 0 || ball.y+ball.ySpeed > height) {
        ball.ySpeed *= -1;
      }
    
      ball.x += ball.xSpeed;
      ball.y += ball.ySpeed;
    
      ellipse(ball.x, ball.y, ball.radius, ball.radius);
    }
    
    class Ball{
     float x;
     float y; 
     float xSpeed;
     float ySpeed;
     float radius = 25;
     
     Ball(float x, float y){
      this.x = x;
      this.y = y;
      xSpeed = 5;
      ySpeed = 7;
     }
    }
    

This code is identical to the code we used before, but now we've begun to
encapsulate all of the data dealing with the ball in the Ball class.

### Let Objects Handle Themselves

We can go a step further. There is still a bunch of code in the draw()
function that uses the variables in the Ball class. Why don't we move that
code into the Ball class along with the variables?

    
    
    Ball ball;
    
    void setup() {
      size(500, 500);
      ellipseMode(CENTER);
      ellipseMode(RADIUS);
      
      ball = new Ball(100, 100);
    }
    
    void draw() {
    
      background(0);
    
      //let the ball take care of itself
      ball.step();
      ball.draw();
    }
    
    class Ball{
     float x;
     float y; 
     float xSpeed;
     float ySpeed;
     float radius = 25;
     
     Ball(float x, float y){
      this.x = x;
      this.y = y;
      xSpeed = 5;
      ySpeed = 7;
     }
     
     //check for collisions and update the position
     void step(){
       //will the ball leave the screen?
      if (x+xSpeed < 0 || x+xSpeed > width) {
        xSpeed *= -1;
      }
      if (y+ySpeed < 0 || y+ySpeed > height) {
        ySpeed *= -1;
      }
    
      x += xSpeed;
      y += ySpeed;
     }
     
     //draw this ball
     void draw(){
       ellipse(x, y, radius, radius);
     }
    }
    

Now all of the code dealing with the ball is in the Ball class, and our code
simply tells the ball to update and draw itself. Keeping the updating code
separate from the drawing code is a good habit to get into, as it will come in
handy down the road.

### Multiple Objects

So far, it might just seem like we're moving around our code without a real
reason. While that's true if we only have one ball, what if we want to have
two balls bouncing around the screen? Ten? A hundred? Thanks to OOP, that
becomes extremely easy to do.

Remember, a class is just a blueprint for creating objects of a certain type.
We can use the blueprint over and over again, filling in the blanks with
different values each time, to create a bunch of different objects. In our
case, we can use the Ball class multiple times to create multiple balls with
different starting locations.

    
    
    Ball ballOne;
    Ball ballTwo;
    
    void setup() {
      size(500, 500);
      ellipseMode(CENTER);
      ellipseMode(RADIUS);
      
      ballOne = new Ball(100, 100);
      ballTwo = new Ball(400, 400);
    }
    
    void draw() {
    
      background(0);
    
      ballOne.step();
      ballTwo.step();
     
      ballOne.draw();
      ballTwo.draw();
    }
    
    class Ball{
     float x;
     float y; 
     float xSpeed;
     float ySpeed;
     float radius = 25;
     
     Ball(float x, float y){
      this.x = x;
      this.y = y;
      xSpeed = 5;
      ySpeed = 7;
     }
     
     //check for collisions and update the position
     void step(){
       //will the ball leave the screen?
      if (x+xSpeed < 0 || x+xSpeed > width) {
        xSpeed *= -1;
      }
      if (y+ySpeed < 0 || y+ySpeed > height) {
        ySpeed *= -1;
      }
    
      x += xSpeed;
      y += ySpeed;
     }
     
     //draw this ball
     void draw(){
       ellipse(x, y, radius, radius);
     }
    }
    

Note that the second ball is completely independent from the first ball, even
though they are both instances of the same class. They were created by the
same blueprint, but each one has its own copy of the functions and variables
in the Ball class. This is the central core of OOP, and it's probably the most
important (and difficult) concepts to understand.

### Terminology

We've gone over a bunch of concepts that are very important to object oriented
programming. They are definitely pretty hard to understand at first, so let's
go over some terminology.

  * Object- a collection of values and methods that work together to form a state. That state can be updated, like when we move the ball around the screen.
  * Class- the blueprint for creating an object. A class defines the variables and methods that belong to an object.
  * Constructor- part of a class that looks like a method and allows you to fill in the blanks to create an object.
  * Instance- a particular object created using the new keyword. You can have many different instances of one class, each with a different state.
  * Instantiation- the process of creating an instance.
  * Encapsulation- keeping all the data, variables, and functions that affect an object inside that object's class.

### Exercises

  * Look around at the objects around you right now. What kinds of Objects do you see, and what might their variables and methods be? Write a class that represents a real-life object.
  * Add a third ball to the above program. Give each ball its own color.

###  Next: [Fireworks!](Fireworks.jsp)
