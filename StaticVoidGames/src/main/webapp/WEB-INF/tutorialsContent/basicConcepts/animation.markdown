In this tutorial, we'll combine everything we've learned so far and create
some animations. Most of my games start as a circle bouncing around the
screen, so this is more useful than it might seem!

### Modifying Variables Over Time

Remember from the [tutorial on variables](Variables.jsp) that a variable is
**declared** (you give it a type and a name) and **initialized** (use the
equals sign and assign a value) to be created. After a variable is created,
you can use operators on it (addition, subtraction, etc) to create new values.
For example, we can assign a new variable to be the result of performing some
operation on an existing variable: float x = 10; float y = x * 2; After these
two lines, the x variable will hold 10, and the y variable will hold 20.< You
can also reassign variables to new values after you've declared them: float x
= 10; x = 25; By combining those two ideas, we can change a variable over time
by assigning it a new value based on its current value. float x = 100; x = x +
10; This is extremely useful for things like keeping track of health or
accumulating points. Think of adding one to the variable that holds the number
of lives the player has when she gets an extra life. But this idea of
modifying variables over time can also be used for animation! For example,
this code shows a circle dropping from the top of the window: int y = 0; void
draw(){ background(0); fill(125, 0, 125); ellipse(50, y, 20, 20); y = y+1; }
Remember that the draw() function is called automatically 60 times per second.
Also note that we have to declare the y variable outside the draw() function.
Otherwise, it would start over at 0 each time the draw() function was called,
which would not be very useful! For more info on that, check out the [scope
tutorial](Scope.jsp).

### Bouncing

That's all well and good, but a dropping ball is not very exciting. Let's make
it more exciting, but first let's improve our program by adding some
variables: int ySpeed = 1; int x = 250; int y = 100; int radius = 25; void
setup(){ size(500, 500); ellipseMode(CENTER); ellipseMode(RADIUS); } void
draw(){ background(0); fill(125, 0, 125); ellipse(x, y, radius, radius); y =
y+ySpeed; } Now we can modify the code to change the size of the ball or make
it drop faster. But can we make it more exciting by making the ball bounce?
Remember from the [tutorial on if statements](IfStatements.jsp) that we can
use an if statement to only execute code when a certain condition is met. The
condition we want to check for is whether the ball has hit the bottom of the
window. Think about how you might do that, then continue reading for the
answer: int ySpeed = 5; int x = 250; int y = 100; int radius = 25; void
setup(){ size(500, 500); ellipseMode(CENTER); ellipseMode(RADIUS); } void
draw(){ background(0); fill(125, 0, 125); if(y + ySpeed >=getHeight()){ ySpeed
= -ySpeed; } y = y+ySpeed; ellipse(x, y, radius, radius); } The only addition
is that if statement in the draw() function (I also increased the speed a
bit). The if statement checks to see whether the y variable has increased so
much that it is about to go off the bottom of the screen (remember that the
y-axis goes down). If so, the ySpeed variable is multiplied by -1, reversing
its sign. What does this do?

When the ySpeed variable was positive, it caused the y variable to become
greater, which caused the ball to fall (again, y-axis goes down). So,
reversing the sign of the ySpeed variable right before the ball leaves the
screen will cause the y variable to become smaller, which causes the ball to
start going back up!

But that's still not very exciting. Can we make it so the ball bounces off the
top as wall? Again, I recommend trying it on your own before reading on for
the answer:

    
    
    int ySpeed = 5;
    
    int x = 250;
    int y = 100;
    int radius = 25;
    
    void setup(){
     size(500, 500); 
     ellipseMode(CENTER);
     ellipseMode(RADIUS);
    }
    
    void draw(){
      background(0);
      fill(125, 0, 125);
      
      if(y + ySpeed <= 0 || y + ySpeed >= getHeight()){
        ySpeed = -ySpeed;
      }
      y = y+ySpeed;
      
      ellipse(x, y, radius, radius);
    }
    

All I did was add one more check to the if statement. Now, if the y value is
about to go below 0 (above the top of the window) or above the height (below
the bottom of the window), then the ySpeed is reversed and the ball starts
going in the opposite direction.

### Left and Right

A natural extension of this is to make bouncing work for the left and right
sides of the window as well. Can you do that without looking at the code
below?

    
    
    int xSpeed = 2;
    int ySpeed = 5;
    
    int x = 250;
    int y = 100;
    int radius = 25;
    
    void setup() {
      size(500, 500); 
      ellipseMode(CENTER);
      ellipseMode(RADIUS);
    }
    
    void draw() {
      background(0);
      fill(125, 0, 125);
    
      if (x + xSpeed <= 0 || x + xSpeed >= getWidth()) {
        xSpeed *= -1;
      }
    
      if (y + ySpeed <= 0 || y + ySpeed >= getHeight()) {
        ySpeed *= -1;
      }
    
      x += xSpeed;
      y += ySpeed;
    
      ellipse(x, y, radius, radius);
    }
    
    

I've added similar code for xSpeed, which controls the bouncing off the left
and right sides of the window. I've also started using the faster += and *=
operators, which do exactly what we've been doing, just with less typing. For
example, the += operator sets a variable equal to itself plus whatever value
follows it.

Now we have a ball bouncing around the screen, and you might have seen this
exact type of animation in screensavers or DVD menus. Now you know how they
work!

### Gravity

We have a bouncing ball, but it doesn't look very realistic. Balls don't
bounce in straight lines like that (unless you're making pong, or a top-down
pool-type game). So let's think about how gravity works.

Imagine dropping a basketball off the top of a tall building. The ball doesn't
instantly start falling at the speed it'll eventually hit the ground with,
does it? No, it starts out falling very slowly, and then speeds up (on Earth,
that speed is [9.8 m/s per
second](http://en.wikipedia.org/wiki/Gravitation#Earth.27s_gravity)) until it
hits the ground. When it hits the ground, all of that momentum is reversed and
the ball bounces back up. As the ball rises into the air, it slows down until
it hovers in the air for a split second and comes back down, repeating the
process.

How do we translate that into code? Think about it in terms of ySpeed. When
you drop the ball, what does it start out as? As the ball falls, what does
gravity do to the ySpeed?

    
    
    
    
    float xSpeed = 5;
    float ySpeed = 0;
    float gravity = .5;
    
    float x = 250;
    float y = 100;
    float radius = 25;
    
    void setup() {
      size(500, 500); 
      ellipseMode(CENTER);
      ellipseMode(RADIUS);
    }
    
    void draw() {
      background(0);
      fill(255, 125, 0);
    
      if (x+xSpeed <= 0) {
        xSpeed *= -1;
      }
      else if ( x + xSpeed >= getWidth()) {
        xSpeed *= -1;
      }
    
      if (y+ySpeed <= 0 ) {
        ySpeed *= -1;
      }
      else if (y + ySpeed >= getHeight()) {
        ySpeed *= -1;
      }
      
      ySpeed += gravity;
      
      x += xSpeed;
      y += ySpeed;
    
      ellipse(x, y, radius, radius);
    }
    
    
    

The above code is not much different from what you've seen before. The biggest
difference is the gravity value, which is added to the ySpeed variable every
frame. This causes the ySpeed to speed up when the ball is falling (when
ySpeed is positive) and slow down when the ball is rising back into the air
(when ySpeed is negative). You can try changing the gravity to make it heavier
or lighter!

If you run this program until the ball's bouncing peters out, you'll notice
something weird: the ball slips off the bottom of the window! What's going on?
Think about it this way: as the ball's bouncing gets lower and lower, it
accumulates less y-speed on the way down. That means that when it bounces back
up, it'll have less y-speed to counteract the gravity. Eventually, that speed
will be less than the gravity, and the gravity alone will cause the ball to
continue dropping until it leaves the window.

We can get around this problem by adding an if statement that detects this
condition:

    
    
    
    float xSpeed = 5;
    float ySpeed = 0;
    float gravity = .5;
    
    float x = 250;
    float y = 100;
    float radius = 25;
    
    void setup() {
      size(500, 500); 
      ellipseMode(CENTER);
      ellipseMode(RADIUS);
    }
    
    void draw() {
      background(0);
      fill(255, 125, 0);
    
      if (x+xSpeed <= 0) {
        xSpeed *= -1;
      }
      else if ( x + xSpeed >= getWidth()) {
        xSpeed *= -1;
      }
    
      if (y+ySpeed <= 0 ) {
        ySpeed *= -1;
      }
      else if (y + ySpeed >= getHeight()) {
        if (ySpeed < gravity) {
          ySpeed = 0;
          gravity = 0;
        }
        else{
          ySpeed *= -1;
        }
      }
      
      ySpeed += gravity;
      
      x += xSpeed;
      y += ySpeed;
    
      ellipse(x, y, radius, radius);
    }
    
    

In the if statement that checks for the ball bouncing off the bottom, we add
an if statement that detects whether the ySpeed has dropped to below the
gravity value. If so, we discontinue bouncing by setting both ySpeed and
gravity to zero.

This is a big oversimplification of how gravity works, but it'll get the job
done. There are other ways to handle gravity, and you can even use entire
libraries devoted to advanced physics for your games. But for now, this'll do.

### Wraparound

We might not always want the ball to bounce off the sides of the window.
Another option is to have the ball wraparound, so that when it goes off the
right side of the screen, it comes back around the left side of the screen. To
do this, we just switch around the logic in our if statements that detect when
the ball has collided with a side of the window:

    
    
    float xSpeed = 1;
    float ySpeed = 5;
    
    float x = 250;
    float y = 100;
    float radius = 25;
    
    void setup() {
      size(500, 500); 
      ellipseMode(CENTER);
      ellipseMode(RADIUS);
    }
    
    void draw() {
      background(0);
      fill(255, 125, 0);
      
      if (x+xSpeed <= 0) {
        x = getWidth()+radius;
      }
      else if ( x+xSpeed >= getWidth()) {
        x = 0;
      }
    
      if (y + ySpeed <= 0 ) {
        y = getHeight();
      }
      else if (y + ySpeed >= getHeight()) {
        y = 0;
      }
      
      x += xSpeed;
      y += ySpeed;
      
      ellipse(x, y, radius, radius);
      println(y);
    }
    

By checking when the ball goes off a side of the window and then moving it to
the other side, we teleport the ball so that it appears to the user as if the
screen wraps around.

### Orbiting

We can even play around with the above ideas, as well as the predefined mouseX
and mouseY variables, to achieve a strange orbiting effect:

    
    
    float xSpeed = 0;
    float ySpeed = 0;
    
    float xGravity = .1;
    float yGravity = .1;
    
    float x = 250;
    float y = 250;
    float radius = 25;
    
    void setup() {
      size(500, 500); 
      ellipseMode(CENTER);
      ellipseMode(RADIUS);
    }
    
    void draw() {
      background(0);
      fill(255, 125, 0);
    
      ellipse(x, y, radius, radius);
      
      if (x <= mouseX) {
        xSpeed += xGravity;
      }
      else {
        xSpeed -= xGravity;
      }
    
      if (y <= mouseY ) {
        ySpeed += yGravity;
      }
      else{
        ySpeed -= yGravity;
      }
      
      x += xSpeed;
      y += ySpeed;
      
      
    }
    

These are by no means the only kinds of effects you can achieve, but I wanted
to demonstrate the basics of animation and show that you can start to form the
building blocks of a game just by using the basic concepts: variables,
functions, and if statements. We'll get into more advanced concepts (coming
soon!), but right now you have enough knowledge to make a basic game, which is
where everybody should start!

### Exercises

  * Can you name any games that use these kinds of effects? For example, pong bounces the ball off the sides, but pacman wraps the ghosts around. Do any games mix the effects?
  * Change the above programs to use a rectangle instead of a ball. 
  * Modify the bouncing ball program to change the color of the ball and background whenever the ball bounces.
  * Our bouncing ball program does not involve friction or air resistance. So why does the ball bounce get lower each time?
  * The wraparound program looks a little jerky because it teleports the ball when its center hits the side of a screen. Improve it by teleporting the ball only when it goes completely off the screen.

###  Next: [Arrays](Arrays.jsp)

