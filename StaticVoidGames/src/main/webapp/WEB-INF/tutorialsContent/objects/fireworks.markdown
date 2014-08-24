#  [Objects](index.jsp)

## Fireworks

Now that we know how objects and OOP work, we're going to use that knowledge
to show how powerful it can be.

### Starting Out

If you've been following these tutorials, you're probably pretty familiar with
this type of program:

    
    
    float gravity = .5;
    
    float x = 250;
    float y = 250;
    float deltaX = 5;
    float deltaY = -5;
    float radius = 10;
    
    void setup() {
      size(500, 500);
    }
    
    void draw() {
    
      background(0);
    
      //increase ySpeed by gravity so the ball falls faster over time
      deltaY += gravity;
    
      //increment x and y by their speeds so the ball moves
      x += deltaX;
      y += deltaY;
    
      //draw the ball
      fill(255, 0, 0);
      ellipse(x, y, radius, radius);
    }
    

This program displays a particle that starts out going up and to the right,
but falls down due to gravity. We use deltaX and deltaY (the word "delta"
usually means "change in" in math) to control the speed of the ball, and we
use gravity to increase deltaY to simulate how an object speeds up as it
falls, or starts going down after it goes up.

This program works, but once it's done, it's done! We can use the
mousePressed() function to reset the ball to the location of the mouse when
the user clicks:

    
    
    void mousePressed() {
      x = mouseX;
      y = mouseY;
      deltaX = 5;
      deltaY = -5;
    }
    

Adding that to the program resets the ball whenever the user clicks. All this
code does is move the particle to the mouse position and reset the particle's
speed. Add this into the above program and test it out!

### Converting to OOP

What happens if we want to have more than one particle? We'd have to copy the
gravity, x, y, deltaX, deltaY, and radius variables for each particle, then
use a bunch of repeated code to update every particle. We could also use a
bunch of arrays, but remember that parallel arrays are a bad habit. Instead,
let's create a Particle object and go from there.

    
    
    class Particle {
      float x = 250;
      float y = 250;
      float deltaX = 5;
      float deltaY = -5;
      float radius = 10;
      float gravity = .5;
      
      void reset() {
        x = mouseX;
        y = mouseY;
        deltaX = 5;
        deltaY = -5;
      }
    
      void step() {
        //increase ySpeed by gravity so the ball falls faster over time
        deltaY += gravity;
    
        //increment x and y by their speeds so this Particle moves
        x += deltaX;
        y += deltaY;
      }
    
      void draw() {
        //draw this Particle
        fill(255, 0, 0);
        ellipse(x, y, radius, radius);
      }
    }
    

The Particle class contains everything that deals with the particle on the
screen- remember that this is called encapsulation. The Particle class
contains the variables we were using before, and sets their default values.
The step() function moves the particle and increases deltaY by gravity, and
the draw() function draws a circle where the Particle is. The reset() function
just moves the Particle to wherever the mouse is and resets its speed.

Now that we've implemented a Particle class, the rest of the program is easy:

    
    
    Particle particle = new Particle();
    
    void setup() {
      size(500, 500);
    }
    
    void mousePressed() {
      particle.reset();
    }
    
    void draw() {
      background(0);
      particle.step();
      particle.draw();
    }
    

Now the program uses our Particle class to do all the work for us. First, we
create a new instance of Particle and assign it to the particle variable. Then
in the draw() function, we simply paint the background and then tell the
Particle to move and paint itself. When the mouse is pressed, we tell the
Particle to reset itself. That's it!

Let's spice up the program a little bit by using the random() function to make
the Particle go in a random direction when the user clicks:

    
    
    Particle particle = new Particle();
    
    void setup() {
      size(500, 500);
    }
    
    void mousePressed() {
      particle.reset();
    }
    
    void draw() {
      background(0);
      particle.step();
      particle.draw();
    }
    
    class Particle {
      float x = 250;
      float y = 250;
      float deltaX = random(-7, 7);
      float deltaY = random(-10, 5);
      float radius = random(5, 10);
      float gravity = .5;
    
      void reset() {
        x = mouseX;
        y = mouseY;
        deltaX = random(-7, 7);
        deltaY = random(-10, 5);
        radius = random(5, 10);
      }
    
      void step() {
        //increase ySpeed by gravity so the ball falls faster over time
        deltaY += gravity;
    
        //increment x and y by their speeds so this Particle moves
        x += deltaX;
        y += deltaY;
      }
    
      void draw() {
        //draw this Particle
        fill(255, 0, 0);
        ellipse(x, y, radius, radius);
      }
    }
    

Remember that the random() function just returns a random number between the
two values you pass into it. Now when the user clicks, the Particle resets
itself to have a random velocity instead of always reseting to the same speed.

### Multiple Objects

We now have a single instance of Particle, now all we need to do is make a
bunch of them! We could do this by making a bunch of Particle variables, like
this:

    
    
    Particle particle1 = new Particle();
    Particle particle2 = new Particle();
    Particle particle3 = new Particle();
    
    void setup() {
      size(500, 500);
    }
    
    void mousePressed() {
      particle1.reset();
      particle2.reset();
      particle3.reset();
    }
    
    void draw() {
      background(0);
      particle1.step();
      particle2.step();
      particle3.step();
      
      particle1.draw();
      particle2.draw();
      particle3.draw();
    }
    

But if you've read the [arrays
tutorial](http://StaticVoidGames.com/tutorials/basicConcepts/Arrays.jsp), you
know there's a much easier way! We can create an array of Particles and use a
for loop to iterate over them when we need to:

    
    
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

###  Next: [Inheritance](Inheritance.jsp)