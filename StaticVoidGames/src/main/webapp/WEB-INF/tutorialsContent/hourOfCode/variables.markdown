##Variables

Now we know how to **call functions** and **pass in arguments** to make the computer do stuff. That's a good start, but it doesn't allow for a lot of variety. We can use **variables** to make out programs more interesting.

Let's take our first program:

    void draw() {
       ellipse(50, 50, 25, 25);
    }
    
Remember that the **arguments** for the ellipse() function represent the *distance from the left edge of the screen* (in other words, **the X value of the ellipse**), the *distance from the top edge of the screen* (in other words, **the Y value of the ellipse**), and the width and height of the ellipse.

###Alegbra Review

Remember from basic algebra that a variable is just a letter that stands for *some value*, and you can use variables just like you would use any other value. For example, we might have this:

    X = 7
    Y = X + 3
    Z = Y + 5

What does Z equal? What would Z equal if the first line said X = 10?

Variables in code are a lot like variables in algebra- except they don't have to be a single letter. 

###The mouseX and mouseY Variables

Processing has a bunch of predefined variables that we can use, two of which are **mouseX** and **mouseY**.

Like in algebra, those variables stand for certain values.

  - The **mouseX** variable holds the current **x position of the mouse**.
  - The **mouseY** variable holds the current **y position of the mouse**.
  
To use a variable, you use it like you would use a normal value:

    void draw() {
       ellipse(mouseX, mouseY, 25, 25);
    }
    
Our program works just like it did before: it draws a circle at the location we specified. But now, that location is **the current position of the mouse**! Try moving your mouse around in the window to see something like this:
    
![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/variables1.png)

The mouseX and mouseY variables are not the only variables available in Processing!

###Creating your own variables

In addition to using the variables that Processing keeps track of for you, you can also create your own variables.

To use a variable, you first have to **declare** it. You **declare a variable** by giving it a **type**, a **name**, and setting it equal to a **value**. It might look something like this:

    int ellipseWidth = 25;

The **int** part of that line is the variable's **type**, which tells the computer what kind of value the variable will hold. Here we're using the **int** type, which tells the computer that the value will be an integer: a whole number without a decimal point.

The second part of the variable is just its name. Then we **assign a value** to that variable by using an equals sign followed by a value, and of course a semicolon.
    
After you declare a variable, you can use it just like we used the mouseX and mouseY variables. Here's an example:

    void draw() {
      int ellipseWidth = 25;
      int ellipseHeight = 25;
      ellipse(mouseX, mouseY, ellipseWidth, ellipseHeight);
    }
    
The program hasn't really changed, but now we're using variables instead of **hardcoding the values**. This might not seem like an improvement now, but it comes in handy if, for example, you were calling the ellipse() function a hundred times and wanted all of the ellipses to be the same size. If you then decide that all of the ellipses should be a bit smaller, you better hope you used variables instead of values! If you used values, you'll have to change them in 100 different lines. But if you used variables, you only have to change the value in one place- when you **declare** the variables.

Variables will become a lot more interesting when we get to **animation**. But first: **[writing your own functions](http://staticvoidgames.com/tutorials/hourOfCode/writingFunctions)**!