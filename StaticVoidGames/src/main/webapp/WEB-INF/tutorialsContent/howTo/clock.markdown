#How to Build a Clock

This tutorial will show you how to program an analog clock display using the [Processing](https://processing.org/) programming language.

You don't have to know anything about programming to do this, but if you really want to understand what we're doing, I recommend first reading these tutorials:

- [Setup](http://staticvoidgames.com/tutorials/intro/startingProcessing)
- [Calling Functions](http://staticvoidgames.com/tutorials/basicConcepts/callingFunctions) 
- [Variables](http://staticvoidgames.com/tutorials/hourOfCode/variables)
- [Writing functions](http://staticvoidgames.com/tutorials/hourOfCode/writingFunctions)

At the very least, you should go through the [setup tutorial](http://staticvoidgames.com/tutorials/intro/startingProcessing) first, just so you have Processing on your computer. Don't worry- all you have to do is download one file!

##Run Processing

Once you have Processing setup, just run the Processing application to bring up the editor. This is where you'll write all your code!

![blank sketch](http://StaticVoidGames.com/tutorialsContent/howTo/clock1.png)

##Write some code!

Copy this (or better yet, type it yourself) into your Processing editor:

    void setup(){
      size(500, 500);
    }
    
    void draw(){
      background(0);
      stroke(255);
      noFill();
      ellipse(width/2, height/2, width/2, height/2);
    }
    
This code uses the `setup()` function to set the size of the window to 500 x 500 pixels, and then it uses the `draw()` function to do 4 things:

- `background(0);`: Draws a black background.
- `stroke(255);`: Sets the **outer color** of shapes to white.
- `noFill();`: Makes it so the **inner color** of shapes is not shown.
- `ellipse(width/2, height/2, width/2, height/2);`: Draws a circle in the center of the window.

When you press the play button, you should see something like this:

![circle](http://StaticVoidGames.com/tutorialsContent/howTo/clock2.png)

Try changing some of those values around to really understand what each line is doing. Can you make the circle smaller? Can you change the background or circle's color?

##Give Me a Hand

Next, let's write a function that takes two arguments, an angle and a length, and then uses those arguments to draw a clock hand at that angle with that length.

    void drawHand(float degrees, float length){
      
      float centerX = width/2;
      float centerY = height/2;
      
      float endX = centerX + cos(radians(degrees))*length;
      float endY = centerY + sin(radians(degrees))*length;
      
      line(centerX, centerY, endX, endY);
    }
    
This might look confusing, but it really only does a couple things:

- First, find the center of the window.
- Then, use basic trigonometry to find where the other end of the hand should be.
- Finally, use those variables to draw a line from the center to the end point.

The whole program looks like this:

    void setup(){
      size(500, 500);
    }
    
    void draw(){
      background(0);
      stroke(255);
      noFill();
      ellipse(width/2, height/2, width, height);
      
      drawHand(90, 200);
    }
    
    void drawHand(float degrees, float length){
  
      float centerX = width/2;
      float centerY = height/2;
      
      float endX = centerX + cos(radians(degrees))*length;
      float endY = centerY + sin(radians(degrees))*length;
      
      line(centerX, centerY, endX, endY);
    }

And running it gives us this:

![circle with one line](http://StaticVoidGames.com/tutorialsContent/howTo/clock3.png)

Try passing in different values for degrees and length. What happens if you enter in values greater than 360? What happens if you enter negative numbers?

##Just a Second

Now that we have the handy `drawHand()` function, we have to figure out where the second, minute, and hour hands go. Let's start with the second hand.

Step one is figuring out the seconds value of the current time. Check out the [Processing reference](https://www.processing.org/reference/) and try to find a function we might use.

Did you find it? The [second()](https://www.processing.org/reference/second_.html) function gives us the current second value! 

We can then divide that by 60 to figure out what percentage "around the clock" the second hand should be, and then we multiply that percentage by 360 to get the degree value of the second hand. Then we just pass that value into our function:

    float secondDegrees = 360.0 * second()/60.0;
    drawHand(secondDegrees, 200);
    
If you run that code, you might notice that the second hand always seems 90 degrees off. That's because 0 degrees points directly to the right, but we want it to point directly up. To correct that, we simply subtract 90 from our degree value:

    float secondDegrees = 360.0 * second()/60.0 - 90;
    drawHand(secondDegrees, 200);
    
##Gimme a Minute (and an Hour)

Now that we have a second hand displayed, we can do the exact same thing for the minutes and hours. The entire program looks like this now:

    void setup(){
      size(500, 500);
    }
    
    void draw(){
      background(0);
      stroke(255);
      noFill();
      ellipse(width/2, height/2, width, height);
      
      float secondDegrees = 360.0 * second()/60.0 - 90;
      drawHand(secondDegrees, 200);
      
      float minuteDegrees = 360.0 * minute()/60.0 - 90;
      drawHand(minuteDegrees, 175);
      
      float hourDegrees = 360.0 * hour()/12.0 - 90;
      drawHand(hourDegrees, 150);
    }
    
    void drawHand(float degrees, float length){
      
      float centerX = width/2;
      float centerY = height/2;
      
      float endX = centerX + cos(radians(degrees))*length;
      float endY = centerY + sin(radians(degrees))*length;
      
      line(centerX, centerY, endX, endY);
    }
    
Running that code displays our clock:
    
![clock](http://StaticVoidGames.com/tutorialsContent/howTo/clock4.png)

From here, we can use this code as a base for all kinds of enhancements. For example, here is updated code that draws the hands with different colors and thicknesses:

    void setup(){
      size(500, 500);
    }

    void draw(){
      background(0);
      stroke(255);
      noFill();
      ellipse(width/2, height/2, width, height);
  
      float secondDegrees = 360.0 * second()/60.0 - 90;
      drawHand(secondDegrees, 200, 2, #ff0000);
  
      float minuteDegrees = 360.0 * minute()/60.0 - 90;
      drawHand(minuteDegrees, 175, 4, #00ff00);
  
      float hourDegrees = 360.0 * hour()/12.0 - 90;
      drawHand(hourDegrees, 150, 6, #0000ff);
    }

    void drawHand(float degrees, float length, float thickness, color c){
  
      float centerX = width/2;
      float centerY = height/2;
  
      float endX = centerX + cos(radians(degrees))*length;
      float endY = centerY + sin(radians(degrees))*length;
  
      strokeWeight(thickness);
      stroke(c);
  
      line(centerX, centerY, endX, endY);
    }
    
![pretty clock](http://StaticVoidGames.com/tutorialsContent/howTo/clock5.png)

Congratulations, you've built a clock! What you do next is only limited by your imagination. If you liked programming in Processing, check out our [Hour of Code](http://staticvoidgames.com/tutorials/hourOfCode/index) to learn more about it!

##More Ideas!

- The hour hands on most clocks move through the hour: so at 2:30, the hand is halfway between the 2 and 3. Our clock doesn't do that. Can you fix that?
- Can you add tick marks around the circle of our clock? How about numbers?
- How would you make a digital clock?
- Can you add hands for the day, month, or year? Check out [the reference](https://www.processing.org/reference/) for other functions you might use!
- What about a millisecond hand? Warning: the `millis()` function does not give you the millisecond value of the time, so this is a little harder!
- Can you make the second hand move smoothly instead of ticking? What about the minute hand?
- Can you make the clock turn random colors every second?
- Can you make it so the clock changes color throughout the day?
- Can you make an alarm clock that goes off at a certain time? How about a countdown clock for a big event?
- How about a stop watch?
- Can you imagine a different kind of clock? Maybe a [binary clock](https://en.wikipedia.org/wiki/Binary_clock), a [hexadecimal clock](https://en.wikipedia.org/wiki/Hexadecimal_time), a [metric clock](https://en.wikipedia.org/wiki/Metric_time), or a backwards clock, or a clock with numbers in a random order?
- Can you make a clock that measures time in one (or several!) of [these units](https://en.wikipedia.org/wiki/List_of_unusual_units_of_measurement#Time)?
- What would a [FizzBuzz](https://en.wikipedia.org/wiki/Fizz_buzz) clock look like?