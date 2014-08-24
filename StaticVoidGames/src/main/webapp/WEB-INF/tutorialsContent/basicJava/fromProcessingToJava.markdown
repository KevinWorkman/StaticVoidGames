#  [Basic Java](index.jsp)

## From Processing to Java

At this point you should understand the basics of how objects work, and have a
little window into more advanced topics. There are a couple other concepts
that Processing hides from you, and we'll introduce them by using a nice
feature in Processing: the ability to write pure Java and use it inside a
Processing sketch.

### Setup

Processing allows you to create purely Java classes, which gives you access to
features that Processing normally hides from you. To create a Java class in
Processing, use the triangle to the right of the sketch tab, click "New Tab",
and give the new tab a name that ends in .java. For example, we can create a
Java class by adding a Ball.java tab:

    
    
    class Ball {
      int x;
      int y;
      
      Ball(int x, int y){
        this.x = x;
        this.y = y;
      }
    }
    

Hopefully this doesn't look like much, but we'll use it as a jumping off point
for the rest of the tutorial. You can use the Ball class exactly like you
normally do in the main sketch tab:

    
    
    Ball ball = new Ball(250, 250);
    
    void setup() {
      size(500, 500);
      ellipseMode(CENTER);
    }
    
    void draw() {
      background(0);
      ellipse(ball.x, ball.y, 25, 25);
    }
    
    

Remember, Ball.java needs to be its own tab, and the .java part is really
important!

### Expanding the Example

Let's expand the above program to do something a little more interesting. This
is the updated Ball.java tab, which contains the purely Java Ball class:

    
    
    class Ball {
      int x = 250;
      int y = 250;
    
      int targetX = 250;
      int targetY = 250;
    
      void step() {
    
        if (x < targetX) {
          x++;
        }
        else {
          x--;
        }
    
        if (y < targetY) {
          y++;
        }
        else {
          y--;
        }
      }
    }
    

The updated Ball class adds two variables, targetX and targetY, that the Ball
will chase every time its step() function is called.

And this is the main tab containing the Processing sketch that uses it:

    
    
    Ball ball = new Ball();
    
    void setup() {
      size(500, 500);
      ellipseMode(CENTER);
    }
    
    void draw() {
      
      ball.step();
      
      background(0);
      ellipse(ball.x, ball.y, 25, 25);
    }
    
    void mouseClicked() {
      ball.targetX = mouseX;
      ball.targetY = mouseY;
    }
    
    

This program uses the Ball class to draw a ball that chases the mouse when you
click inside the window.

### Access Modifiers

Access modifiers let you set the visibility of a class's methods and
variables.

Notice how the mouseClicked() method refers to the targetX and targetY
variables of the Ball class. This is possible because those variables use the
default access modifier (none is specified), which gives the sketch access to
them. This gets a little confusing, and it's a good idea to always specify the
visibility of your members, so I'm going to brush passed this point. It might
be easier to compare the default to the alternatives.

### Private

Imagine you're on a team of programmers. It's your job to write the Ball
class, and it's somebody else's job to write the mousePressed() method, which
uses your Ball class. What if the person writing the mousePressed() method
doesn't really know how to program, and accidentally uses the x and y
variables instead?

    
    
    void mouseClicked() {
      ball.x = mouseX;
      ball.y = mouseY;
    }
    

This really breaks the original design of the program, and the person writing
the mouseClicked() method has no indication that they've done anything wrong.
Since we don't really want anybody else changing the Ball's x and y, we can
declare them private:

    
    
    class Ball {
      private int x = 250;
      private int y = 250;
    
      int targetX = 250;
      int targetY = 250;
    
      void step() {
    
        if (x < targetX) {
          x++;
        }
        else {
          x--;
        }
    
        if (y < targetY) {
          y++;
        }
        else {
          y--;
        }
      }
      
      int getX(){
        return x;
      }
      int getY(){
        return y;
      }
    }
    

To declare a member private, you use the private keyword before a variable's
type (or before a method's return type). Now nobody except the Ball class can
access the x and y variables. If you try to access them directly, you'll get a
compiler error. Now your teammate will know he's doing something wrong!
Instead, we've created getter methods so that other pieces of code can get the
x and y values but not change them. You'd use them in a sketch like so:

    
    
    Ball ball = new Ball();
    
    void setup() {
      size(500, 500);
      ellipseMode(CENTER);
    }
    
    void draw() {
      
      ball.step();
      
      background(0);
      ellipse(ball.getX(), ball.getY(), 25, 25);
    }
    
    void mouseClicked() {
      ball.targetX = mouseX;
      ball.targetY = mouseY;
    }
    
    

### Public

Public, not surprisingly, is the opposite of private. In Processing, anything
declared without an access modifier is public. In Java, members without access
modifiers have "default visibility", which is a little more confusing so we
won't get into it here.

You've been using the public access modifier this whole time, but it's a good
idea to explicitly specify it rather than leaving it up to the default.

Your ball class becomes:

    
    
    class Ball {
      private int x = 250;
      private int y = 250;
    
      public int targetX = 250;
      public int targetY = 250;
    
      public void step() {
    
        if (x < targetX) {
          x++;
        }
        else {
          x--;
        }
    
        if (y < targetY) {
          y++;
        }
        else {
          y--;
        }
      }
      
      public int getX(){
        return x;
      }
      public int getY(){
        return y;
      }
    }
    

Notice how the functions also get access modifiers. Similarly, this is what
the main sketch looks like when we explicitly add access modifiers:

    
    
    private Ball ball = new Ball();
    
    public void setup() {
      size(500, 500);
      ellipseMode(CENTER);
    }
    
    public void draw() {
      
      ball.step();
      
      background(0);
      ellipse(ball.getX(), ball.getY(), 25, 25);
    }
    
    public void mouseClicked() {
      ball.targetX = mouseX;
      ball.targetY = mouseY;
    }
    
    

This might seem like extra syntax, but as you work on bigger projects or on a
team, or as you switch over to Java, explicitly specifying the access modifier
of every variable and function will become much more important. Better get
into the habit now!

### Static

The static keyword is one of the most misunderstood concepts in Java, so
before we make the jump into full Java, we'll cover it here.

Again, pretend you're on a team of programmers creating a program that draws
some stars. Your job is to create the (purely Java) Star class, so you create
a tab and call is Star.java:

    
    
    class Star {
      private float x;
      private float y;
      private float radius;
    
      public Star(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
      }
    
      public float getX() {
        return x;
      }
      public float getY() {
        return y;
      }
      public float getRadius() {
        return radius;
      }
    }
    
    

There is not much to this: you create x,y, and radius variables, set them in a
constructor, and give access to them via some getter methods. Your teammate
might use your Star class like so:

    
    
    private Star[] stars = new Star[25];
    
    public void setup() {
      size(500, 500);
      ellipseMode(CENTER);
    
      for (int i = 0; i < stars.length; i++) {
        stars[i] = new Star(random(500), random(500), random(20));
      }
    }
    
    public void draw() {
    
      background(0);
    
      for (int i = 0; i < stars.length; i++) {
        ellipse(stars[i].getX(), stars[i].getY(), stars[i].getRadius(), stars[i].getRadius());
      }
    }
    
    public void mouseClicked() {
      for (int i = 0; i < stars.length; i++) {
        stars[i] = new Star(random(500), random(500), random(20));
      }
    }
    
    

This program creates an array of your Star objects, giving each instance a
random x, y, and radius value.

Now let's say you want to decide how many stars are shown instead of hard-
coding 25 in the array initialization (the first line of the program), as well
as the maximum radius. You're only in charge of the Star class, so you want to
add a variable called starCount and a variable called maxRadius to your Star
class. Okay, we know how to do that...

    
    
    class Star {
      
      public int starCount = 50;
      public int maxRadius = 20;
      
      private float x;
      private float y;
      private float radius;
    
      public Star(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
      }
    
      public float getX() {
        return x;
      }
      public float getY() {
        return y;
      }
      public float getRadius() {
        return radius;
      }
    }
    
    

Now, how do we use the starCount and maxRadius variables in the main program?
They're public, so we have access to them. So what goes in these lines?

    
    
    private Star[] stars = new Star[???];
    stars[i] = new Star(random(500), random(500), random(???));
    

The problem is, we're used to creating instances of a class to access its
members, but we don't yet have any instances of the Star class when we want to
access the starCount or maxRadius variables.

This is where the static keyword comes in handy. The static keyword indicates
members that don't belong to any particular instance of the class, and you use
it like this:

    
    
    class Star {
      
      public static int starCount = 50;
      public static int maxRadius = 20;
      
      private float x;
      private float y;
      private float radius;
    
      public Star(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
      }
    
      public float getX() {
        return x;
      }
      public float getY() {
        return y;
      }
      public float getRadius() {
        return radius;
      }
    }
    
    

All we've done is add the static keyword in front of starCount and maxRadius,
indicating that they don't belong to any particular instance of Star. And
hopefully that makes some kind of intuitive sense: no single star determines
the max radius of all stars, or the total number of star instances there are.
Now we can access those static variables without creating an instance of Star
first:

    
    
    private Star[] stars = new Star[Star.starCount];
    
    public void setup() {
      size(500, 500);
      ellipseMode(CENTER);
    
      for (int i = 0; i < stars.length; i++) {
        stars[i] = new Star(random(500), random(500), random(Star.maxRadius));
      }
    }
    
    public void draw() {
    
      background(0);
    
      for (int i = 0; i < stars.length; i++) {
        ellipse(stars[i].getX(), stars[i].getY(), stars[i].getRadius(), stars[i].getRadius());
      }
    }
    
    public void mouseClicked() {
      for (int i = 0; i < stars.length; i++) {
        stars[i] = new Star(random(500), random(500), random(Star.maxRadius));
      }
    }
    
    

To access a static member, which belongs to the whole class as opposed to any
particular instance of the class, we use the class name, followed by a dot,
and the variable or method name.

Another benefit of static variables is that it avoids repeating the same data
in every instance of a class. For example, the max radius and star count don't
change, so even if we had 1000 stars, we wouldn't need 1000 copies of each of
those variables (which is what would happen if each instance had its own copy
of the value). This can really add up if you have a ton of instances hanging
around. On the other hand, static values can't be garbage collected. (Don't
worry about that right now though.)

Remember that static variables do not belong to any particular instance, so
they're shared across all instances of a class. What would happen if we made
the x and y variables static?

    
    
    class Star {
      
      public static int starCount = 50;
      public static int maxRadius = 20;
      
      private float x;
      private float y;
      private float radius;
    
      public Star(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
      }
    
      public float getX() {
        return x;
      }
      public float getY() {
        return y;
      }
      public float getRadius() {
        return radius;
      }
    }
    
    

If you run this code, you'll notice that all of the stars are being drawn in
the same location. Because x and y are static, each instance does not have its
own copy of the variables. In the constructor, this.x and this.y are referring
to the static variables x and y. This is misleading, and it's why static
variables should only be referred to using the class name. Anyway, every time
the constructor is called for a new instance, the static variables x and y are
updated across all instances of Star. This is why they all appear in the same
location.

The moral of the story is: only use static variables when you know you need
them!

### Static Methods

Static methods are exactly like static variables: they don't require an
instance to be used. For example, we could prevent unwanted changes by making
the static maxRadius and starCount variables private and creating static
getter functions for them:

    
    
    class Star {
      
      private static int starCount = 50;
      private static int maxRadius = 20;
      
      private float x;
      private float y;
      private float radius;
    
      public Star(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
      }
    
      public float getX() {
        return x;
      }
      public float getY() {
        return y;
      }
      public float getRadius() {
        return radius;
      }
      
      public static int getStarCount(){
        return Star.starCount;
      }
      public static int getMaxRadius(){
        return Star.maxRadius;
      }
    }
    

Now nobody can change the starCount or maxRadius variables except the Star
class, and we can still use them this way:

    
    
    private Star[] stars = new Star[Star.getStarCount()];
    
    public void setup() {
      size(500, 500);
      ellipseMode(CENTER);
    
      for (int i = 0; i < stars.length; i++) {
        stars[i] = new Star(random(500), random(500), random(Star.getMaxRadius()));
      }
    }
    
    public void draw() {
    
      background(0);
    
      for (int i = 0; i < stars.length; i++) {
        ellipse(stars[i].getX(), stars[i].getY(), stars[i].getRadius(), stars[i].getRadius());
      }
    }
    
    public void mouseClicked() {
      for (int i = 0; i < stars.length; i++) {
        stars[i] = new Star(random(500), random(500), random(Star.getMaxRadius()));
      }
    }
    
    

Again, static members are accessed using the name of the class, a dot, and
then the name of the variable or method.

### Static Reference to Non-Static Field

Remember that static members do not belong to any particular instance. This
means that inside a static function, you can't access non-static variables (or
non-static functions). Think about what would happen if you accidentally made
the getRadius() function static:

    
    
    class Star {
      
      private static int starCount = 50;
      private static int maxRadius = 20;
      
      private float x;
      private float y;
      private float radius;
    
      public Star(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
      }
    
      public float getX() {
        return x;
      }
      public float getY() {
        return y;
      }
      public static float getRadius() {
        return radius;
      }
      
      public static int getStarCount(){
        return Star.starCount;
      }
      public static int getMaxRadius(){
        return Star.maxRadius;
      }
    }
    

Notice that the getRadius() function is now static, but it attempts to return
the non-static radius value. The getRadius() function, now that it's static,
is not associated with any particular instance of Star. But since each
instance of Star can have a different radius, this function doesn't know which
one to use! If you run this code, you'll get a compiler error: Cannot make a
static reference to the non-static field radius.

As you start programming Java, this error will be one of your worst enemies,
but remember: static members are not associated with any particular instance,
so they can't directly access anything that depends on an instance's state.

### Exercises

  * What is the difference between a private and public member? Who can access each?
  * What is the difference between a static and non-static member? How is each related to an instance of a class?

###  Next: [Java Setup](JavaSetup.jsp)

