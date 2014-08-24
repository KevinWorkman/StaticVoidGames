#  [Objects](index.jsp)

## ArrayLists

So far you've learned the basics of OOP and how to create and use your own
objects. That is extremely useful, but a huge part of programming is using
objects that have already been created for you.

To demonstrate this concept, we'll introduce you to one of the most useful
objects you'll encounter: ArrayLists.

### Arrays

Remember that arrays are a way to hold multiple values in one variable. Also
remember that to use an array, you have to know exactly how many values the
array will hold. For example, we can have 5 balls bouncing around:

    
    
    Ball[] ballArray = new Ball[5];
    
    void setup() {
      size(500, 500);
      ellipseMode(CENTER);
      ellipseMode(RADIUS);
      
      for(int i = 0; i < ballArray.length; i++){
        ballArray[i] = new Ball(250, 250);
      }
    }
    
    void draw() {
    
      background(0);
    
      for(int i = 0; i < ballArray.length; i++){
        ballArray[i].step();
      }
      
        for(int i = 0; i < ballArray.length; i++){
        ballArray[i].draw();
      }
    }
    
    class Ball{
     float x;
     float y; 
     float xSpeed;
     float ySpeed;
     float radius = 5;
     
     Ball(float x, float y){
      this.x = x;
      this.y = y;
      xSpeed = random(-5, 5);
      ySpeed = random(-5, 5);
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
    

But what happens if we want to add another ball, or remove an existing ball?
Arrays have a fixed size, meaning once an array is created, its size can't
change. There are ways around this, such as creating a new array of a
different size and then copying over the elements you want from the new array,
and they're worth playing around with if you really want to understand arrays.

### Using an ArrayList

However, Java (and therefore Processing) has a premade object that does all of
that for you. An ArrayList is a lot like an array, except it supports adding
and removing elements instead of always having a fixed size.

To use an ArrayList, first we have to construct an instance of the ArrayList
class using the new keyword:

    
    
    ArrayList ballList = new ArrayList();
    

Now we can use the ballList variable to access the methods of the ArrayList
class. One useful method is the add() method, which takes a single argument:
an element to add to the list. Here we use a for loop to add 5 Ball instances
to our ArrayList:

    
    
      for (int i = 0; i < 5; i++) {
        Ball ball = new Ball(250, 250);
        ballList.add(ball);
      }
    

Now that we have an ArrayList containing the balls, we can use a couple other
methods to update and draw them:

    
    
      for (int i = 0; i < ballList.size(); i++) {
        Ball ball = (Ball)ballList.get(i);
        ball.step();
      }
    
      for (int i = 0; i < ballList.size(); i++) {
        Ball ball = (Ball)ballList.get(i);
        ball.draw();
      }
    

The size() method returns how many elements are in the list, so we know how
many times to loop. The get() method takes an index argument and returns the
element at that index.

Now that we're using an ArrayList, it's trivial to add a new Ball to the list.
How about when the user clicks the mouse?

    
    
    void mousePressed(){
      Ball ball = new Ball(mouseX, mouseY);
      ballList.add(ball);
    }
    

### Putting it Together

    
    
    ArrayList ballList = new ArrayList();
    
    void setup() {
      size(500, 500);
      ellipseMode(CENTER);
      ellipseMode(RADIUS);
    
      for (int i = 0; i < 5; i++) {
        Ball ball = new Ball(250, 250);
        ballList.add(ball);
      }
    }
    
    void mousePressed(){
      Ball ball = new Ball(mouseX, mouseY);
      ballList.add(ball);
    }
    
    void draw() {
    
      background(0);
    
      for (int i = 0; i < ballList.size(); i++) {
        Ball ball = (Ball)ballList.get(i);
        ball.step();
      }
    
      for (int i = 0; i < ballList.size(); i++) {
        Ball ball = (Ball)ballList.get(i);
        ball.draw();
      }
    }
    
    class Ball {
      float x;
      float y; 
      float xSpeed;
      float ySpeed;
      float radius = 5;
    
      Ball(float x, float y) {
        this.x = x;
        this.y = y;
        xSpeed = random(-5, 5);
        ySpeed = random(-5, 5);
      }
    
      //check for collisions and update the position
      void step() {
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
      void draw() {
        ellipse(x, y, radius, radius);
      }
    }
    
    

This is just the full code of what we've been writing.

### Generics

Notice how when we call the get() function, we have to cast the value
returned, like so:

    
    
    Ball ball = (Ball)ballList.get(i);
    

This is because an ArrayList doesn't "know" what type of Object is inside it.
This can lead to problems though: what if we put something else in the
ArrayList?

    
    
    Particle[] particles = new Particle[25];
    
    void setup() {
      size(500, 500);
    
      //put a new Particle in each index of the array
      for (int i = 0; i < particles.length; i++) {
        particles[i] = new Particle();
      }
    }
    
    //reset all the particles
    void mousePressed() {
      for (int i = 0; i < particles.length; i++) {
        particles[i].reset();
      }
    }
    
    void draw() {
    
      background(0);
    
      //iterate over each particle and call the step() function of each
      for (int i = 0; i < particles.length; i++) {
        particles[i].step();
      }
    
      //iterate over each particle and call the draw() function of each
      for (int i = 0; i < particles.length; i++) {
        particles[i].draw();
      }
    }
    

The above code creates an array of Particles, then iterates over them using
for loops to call the functions of every instance of Particle in each index of
the array. Click the screen to create a firework!

### Exercises

  * Modify the firework program so each Particle has its own color and/or gravity.
  * Modify the Particle class so that Particles darken as they fall.
  * Move the call to background() around to create different effects. Remember that the background() function clears out anything previously drawn. What if you only call it once in the setup() function? What if you only call it when the mouse is pressed?