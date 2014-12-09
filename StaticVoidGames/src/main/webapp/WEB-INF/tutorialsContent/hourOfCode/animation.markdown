##Animation

So far, we've learned how to **declare** and **use** variables by **passing them in as arguments** to **functions**.

Next, we'll learn how to **modify** a variable over time in order to do **animation**.

###Arithmetic Operators

A computer is just a giant calculator (or more accurately, a calculator is just a tiny computer), so we can use **arithmetic operators** on values. We already know how to create a variable like this:

    int sum = 15;
    
But we could also use the **addition operator** to create it like this:

    int sum = 10 + 5;
    
Either way, the result is the same: our sum variable holds the value 15. We can test this out by drawing it to the screen using the text() function:

    void draw() {
  
      int sum = 10 + 5;
  
      textSize(36);
      text(sum, 0, 50);
    }

This program **declares** the sum variable by adding two values together. Then it **calls** the textSize() function to set the size of any text we draw (try taking that line out!), then calls the text() function to draw the value of the sum variable at position (0, 50) in the window.

###Using Variables as Values

Remember that you can use a variable wherever you can use a value. So we can do something like this:

    void draw() {
  
      int apples = 10;
      int oranges = 5;
      int sum = apples + oranges;
  
      textSize(36);
      text(sum, 0, 50);
    }
    
###Changing the Value of a Variable

So far we know how to **declare** and **use** a variable. But we can also **change** the value of a variable.

After a variable has been declared, you can change its value just by setting it equal to another value.

    void draw() {
  
      int apples = 10;
      int oranges = 5;
      
      apples = 25;
      oranges = 10;
      
      int sum = apples + oranges;
  
      textSize(36);
      text(sum, 0, 50);
    }


###Modifying a Variable Over Time

We can change the value of a variable by setting it equal to a new value, and we can use a variable wherever we can use a value. We can also use arithmetic operators on values and variables.

Combining all of those ideas, that means we can increase a variable by one: we just set the new value of the variable equal to **the current value of the variable plus one**. 

    void draw() {
  
      int count = 10;
      
      count = count + 1;
  
      textSize(36);
      text(count, 0, 50);
    }

This code declares a variable called count, which is initially set to the value of 10. Then it **modifies** the count variable by setting it equal to **the current value of count** (which is 10) plus one. When we run this code, count is 11.

That's not very exciting, but that's because we **declare** the count variable every time the draw() function is called, so it will always have the same value. Instead, we can **declare a variable outside the draw() function** so that the value is not reset every time the draw() function is called!


    int count = 10;
    
    void draw() {
    
      background(0, 0, 0);
  
      count = count + 1;
  
      textSize(36);
      text(count, 0, 50);
    }
    
**When you declare a variable outside a function, that declaration happens only once, right before the program starts running.**

The draw() function is still called 60 times a second, so now when you tell the computer to add one to the count variable, it's adding one to *whatever it was last time*.

When you run this program, you should see the value of count displayed on the screen, and the value should be going up very fast- it increases by 60 every second!

![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/animation1.gif)

###Animation

We can use all of the above to perform animations. Let's start with a simple program that just displays a ball at the top of the screen:

    int ballY = 10;

    void draw() {
      background(0, 0, 0);
      ellipse(50, ballY, 10, 10);
    }
    
That's not very exciting, but we can use everything we just learned to **animate the circle** and have it fall down- just by adding a single line of code!

    int ballY = 10;

    void draw() {
      background(0, 0, 0);
      
      ballY = ballY + 1;
      
      ellipse(50, ballY, 10, 10);
    }

![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/animation2.gif)

Don't be afraid to experiment- what happens if you use mouseX and mouseY instead of 50? Or instead of 10? Try drawing different shapes!

Next up: add some bounce with [if statements](http://staticvoidgames.com/tutorials/hourOfCode/ifStatements)!