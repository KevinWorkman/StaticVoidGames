#  [Intermediate Concepts](index.jsp)

## Collision Detection

So far, we know how to update variables to create an animation as well as get
user input to control the animation. Next, we'll look at how to start
constraining those animations to create a game's logic.

Collision detection is the process of determining whether two game objects
have come into contact with one another. This is essential to almost all game
development- figuring out when a character is touching the ground or a wall,
when the play has collected an item or hit an enemy, or when a bullet or
projectile has hit something. How collision detection is accomplished ranges
from simple if statements to complicated algorithms handling thousands of
objects at once, and even libraries that simulate realistic physics. We'll
start with something simple and work our way up from there.

### Bouncing Ball

Remember this program from the animation tutorial, which displays a ball
bouncing around the screen:

    
    
    float xSpeed = 5;
    float ySpeed = 4;
    
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
    
      ellipse(x, y, radius, radius);
      
      if (x <= 0) {
        xSpeed *= -1;
      }
      else if ( x >= getWidth()) {
        xSpeed *= -1;
      }
      
      if (y <= 0 ) {
        ySpeed *= -1;
      }
      else if (y >= getHeight()) {
        ySpeed *= -1;
      }
    
      x += xSpeed;
      y += ySpeed;
    
    
    
    
    }
    
    

This program is pretty simple, but it actually does contain a basic form of
collision detection: it detects when the ball collides with the walls, which
in this case are the sides of the window. We'll use this program as a start to
our first game, but for now let's cover a couple different kinds of collision
detection.

### Grid-Based Collision Detection

One of the simplest forms of collision detection is to use a grid. The game
world is split up into cells, and the game objects fill those cells. Games
like battleship or snake use this kind of collision detection. Also, many
platformers use a grid-based approach (think of the blocks in Mario) but allow
the player to be "between" blocks for smoother animations.

Grid-based collision detection will probably get its own tutorial in the near
future.

### Radius-Based Collision Detection

The above program is simple for a couple reasons: first, it only cares about
collisions going one way. In other words, you don't have to worry about
collisions coming from outside the bounds of the window, since the ball is
always inside. Secondly, the fact that we're using a ball makes checking for
collisions easier, since we only have to use one measurement: distance. If the
distance from the middle of the ball to another game object is less than the
ball's radius, then you know there is a collision regardless of the ball's
rotation.

This is pretty useful for things like determining whether a player is near a
game object that can be interacted with or collected. The collision detection
can be accomplished in a single line, using the dist() function:

    
    
    
    float circleX = 50;
    float circleY = 50;
    
    float radius = 25;
    
    void setup(){
     size(100, 100);
     ellipseMode(CENTER);
     ellipseMode(RADIUS);
    }
    
    void draw(){
      
      background(0);
      
      if(dist(mouseX, mouseY, circleX, circleY) < radius){
        //color with red
       fill(255, 0, 0); 
      }
      else{
        //color with green
        fill(0, 255, 0);
      }
      
      ellipse(circleX, circleY, radius, radius);
    }
    

This program displays a green circle that turns red when the mouse collides
with it. We accomplish this by simply using the dist() function, which returns
the distance between two points. If the distance is smaller than the radius of
the circle, we know the point is inside the circle.

Writing code that causes circles to bounce off of each other accurately goes a
bit beyond this tutorial, but it involves using basic trigonometry to
calculate angles. There's actually an example of doing exactly this in the
Processing examples menu.

### Box-Based Collision Detection

Using the distance is a good quick-and-easy collision check, but many game
objects will not be circles. A step up from circles is to use boxes. Here's
the same program as before, but using a box instead of a circle:

    
    
    float boxX = 25;
    float boxY = 25;
    float boxWidth = 50;
    float boxHeight = 50;
    
    void setup(){
     size(100, 100); 
     rectMode(CORNER);
    }
    
    void draw(){
      
      background(0);
      
      if(intersectsBox(mouseX, mouseY)){
        //color with red
       fill(255, 0, 0); 
      }
      else{
        //color with green
        fill(0, 255, 0);
      }
      
      rect(boxX, boxY, boxWidth, boxHeight);
    }
    
    
    boolean intersectsBox(float x, float y){
      if(x > boxX && x < boxX + boxWidth){
       if(y > boxY && y < boxY + boxHeight){
        return true;
       } 
      }
      
      return false;
    }
    

Take a look at the intersectsBox() function. The first if statement checks
whether the supplied x value is to the left of boxX (which indicates the left
side of the box) and to the right of boxX+boxWidth, which indicates the right
side of the box. If both cases are true, then we know the x value is between
the left and right sides of the box. The second if statement checks whether
the y value is between boxY (the top of the box) and boxY + boxHeight (the
bottom of the box). If both if statements evaluate to true, we know that the
position is inside the box, so we return true.

Note that returning true in the middle of a function prevents any code that
comes after the return statement to execute. So if we are still executing
after the if statements, we know that the point is not inside the box, so the
function returns false.

### Collision between Game Objects

So how can we use all of this to actually detect collision between two game
objects? We can combine the program that displays a bouncing ball and the
program that detects when the mouse is inside the square to cause the ball to
bounce off the square:

    
    
    
    float boxX = 200;
    float boxY = 200;
    float boxWidth = 100;
    float boxHeight = 100;
    
    float ballX = 100;
    float ballY = 100;
    float xSpeed = 3;
    float ySpeed = 5;
    
    float radius = 5;
    
    void setup(){
     size(500, 500); 
     rectMode(CORNER);
     ellipseMode(CENTER);
     ellipseMode(RADIUS);
    }
    
    void draw(){
      
      background(0);
      
      //will the ball hit the box?
      if(intersectsBox(ballX+xSpeed, ballY)){
        xSpeed *= -1;
      }
      //will the ball leave the screen?
      else if(ballX+xSpeed < 0 || ballX+xSpeed > width){
        xSpeed *= -1;
      }
      
      //will the ball hit the box?
      if(intersectsBox(ballX, ballY+ySpeed)){
        ySpeed *= -1;
      }
      //will the ball leave the screen?
      else if(ballY+ySpeed < 0 || ballY+ySpeed > height){
        ySpeed *= -1;
      }
      
      ballX += xSpeed;
      ballY += ySpeed;
      
      ellipse(ballX, ballY, radius, radius);
      rect(boxX, boxY, boxWidth, boxHeight);
    }
    
    
    boolean intersectsBox(float x, float y){
      if(x > boxX && x < boxX + boxWidth){
       if(y > boxY && y < boxY + boxHeight){
        return true;
       } 
      }
      
      return false;
    }
    
    
    

This program uses all of the logic you've seen before- if the ball is about to
go off the sides of the screen, or if it's about to collide with the box, it
reverses direction and bounces.

### Hit Boxes

We've been a little lazy with our hit detection. If you pay close attention,
you might notice that the ball actually goes partway off the sides of the
screen, or partway inside the box, before bouncing. This is more obvious if
you increase the radius (try 25). Why does this happen?

Remember that the program draws the **center** of the ball at the x and y
variables, since we called ellipseMode(CENTER) in the setup() function. We're
also using the x and y variables to perform the bounce checks. The center of
the ball bounces off the sides of the window, but we want the sides of the
ball to bounce.

Pointing out this problem might seem pedantic, but it's exactly the type of
detail that can distract a player from an otherwise good game, so it's best to
start dealing with it early.

We can deal with this problem by using a method of collision detection that
almost every game you've ever played uses: hit boxes. You can think of a hit
box an invisible box around whatever you want to detect collisions for (a
ball, a player, an object), and hit box collision detection simply checks
whether those boxes are colliding.

    
    
    float boxX = 200;
    float boxY = 200;
    float boxWidth = 100;
    float boxHeight = 100;
    
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
    
      float boxLeft = boxX;
      float boxRight = boxX + boxWidth;
      float boxTop = boxY;
      float boxBottom = boxY + boxHeight;
    
      float currentBallLeft = ballX - radius;
      float currentBallRight = ballX + radius;
      float currentBallTop = ballY - radius;
      float currentBallBottom = ballY + radius;
    
      float nextBallLeft = currentBallLeft + xSpeed;
      float nextBallRight = currentBallRight + xSpeed;
      float nextBallTop = currentBallTop + ySpeed;
      float nextBallBottom = currentBallBottom + ySpeed;
    
      //will the ball hit the box?
      if (doBoxesIntersect(boxLeft, boxRight, boxTop, boxBottom, nextBallLeft, nextBallRight, currentBallTop, currentBallBottom)) {
        xSpeed *= -1;
      }
      //will the ball leave the screen?
      else if (ballX+xSpeed < 0 || ballX+xSpeed > width) {
        xSpeed *= -1;
      }
    
      //will the ball hit the box?
      if (doBoxesIntersect(boxLeft, boxRight, boxTop, boxBottom, currentBallLeft, currentBallRight, nextBallTop, nextBallBottom)) {
        ySpeed *= -1;
      }
      //will the ball leave the screen?
      else if (ballY+ySpeed < 0 || ballY+ySpeed > height) {
        ySpeed *= -1;
      }
    
      ballX += xSpeed;
      ballY += ySpeed;
    
      //rect(nextBallLeft, nextBallTop, radius*2, radius*2);
      ellipse(ballX, ballY, radius, radius);
    
      rect(boxX, boxY, boxWidth, boxHeight);
    }
    
    
    boolean doBoxesIntersect(float aBoxLeft, float aBoxRight, float aBoxTop, float aBoxBottom, 
                              float bBoxLeft, float bBoxRight, float bBoxTop, float bBoxBottom) {
                              
      return bBoxRight > aBoxLeft && bBoxBottom > aBoxTop && aBoxRight > bBoxLeft && aBoxBottom > bBoxTop;
    }
    
    

In this code, we've changed the intersection method to take parameters
representing two boxes. We then use the most common intersection algorithm to
determine whether the boxes overlap. I highly suggest drawing out a few
different scenarios for two boxes (not colliding, colliding on the left,
colliding on the top, etc) to really understand how this algorithm works. It's
pretty neat, and you'll see it all over the place in game development!

We call the updated doBoxesIntersect() method to determine when the ball will
intersect it horizontally or vertically, and bounce when appropriate!

Using hit boxes will be good enough for most purposes. You can even use
multiple hit boxes for one object- a hit box around the player's head, another
around the player's body, more for the arms, etc.

### More Advanced Collision Detection

These are all pretty simple methods of collision detection, but they'll be
good enough for most games. There are much more complicated algorithms dealing
with problems such as fast-moving objects that are moving so fast that they
move through obstacles between frames, and there are algorithms such as Quad
Trees that deal with efficiently calculating collisions between thousands of
game objects.

Another way to approach collision detection is to use a hybrid algorithm- for
example, first do a distance-based check, which can be done very fast and with
little code. If that check is true, then spend more time doing something like
hit box detection. If that is true, then you can do more specific or even
pixel-perfect collision detection. This avoids extra work, but gives you the
maximum amount of precision.

There are also entire libraries and frameworks devoted to collision detection
and even accurate physics!

Which method you use will depend on your game. Some games don't need very
complicated collision detection, whereas other games require realistic physics
and pixel-perfect accuracy. We'll stick with hit boxes for a while, but just
keep in mind that there are other methods out there!

### Exercises

  * The ball in our hit box program still uses the center of the circle to calculate bouncing off the sides of the screen. Fix that to use the sides of the ball!
  * Create a program that displays a box and a bouncing ball. Make it so the bouncing ball stays on the inside of the square- the reverse of what this tutorial shows.

###  Next: Your first game: [Pong](Pong.jsp)!
