##Calling Functions

Remember our first program:

    void draw() {
       ellipse(50, 50, 25, 25);
    }

When you run this code, a circle is drawn in the middle of the window.

That circle is drawn because you're **calling the ellipse() function**.

###What's a function?

You can think of a function as a set of instructions that tell the computer what to do. The ellipse() function tells the computer to draw an ellipse!

###What are those numbers for?

When you tell a computer to jump, it asks "how high?"

By *calling the ellipse() function*, you're telling the computer to draw an ellipse- and those numbers tell the computer **where** and **how big** the ellipse should be.

  * The first number is how far from the *left edge* of the window the ellipse should be.
  * The second number is how far from the *top edge* of the window the ellipse should be.
  * The third number is how *wide* the ellipse should be.
  * The fourth number is how *tall* the ellipse should be.

In this case, we're drawing the ellipse at a coordinate 50 pixels from the left edge of the window, and 50 pixels from the top edge of the window. The ellipse is 25 pixels wide and tall, so it's a circle instead of an oval.

Those numbers are called **arguments**.

Try changing the arguments to move the ellipse around.

###Thinking outside the ellipse

The ellipse() function is not the only function you can call- that would be pretty boring!

There are plenty of different functions available, and each function takes different arguments depending on what it does. Some functions don't take any arguments! Here is another program that **calls the background() function**, then **calls the rect() function** and then **calls the triangle function**.

    void draw() {
    	background(255, 0, 0);
      rect(10, 70, 70, 20);
      triangle(50, 10, 25, 50, 75, 50);
    }
    
![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/callingFunctions1.png)

Can you figure out what the **arguments** for these functions mean?

###What's with the semicolons?

Similar to how you end a sentence with a period, you end individual code statements with a semicolon.

If you forget a semicolon, your code won't run!

**Remember:** You **call a function** by writing its name, then putting a comma-separated list of arguments inside parenthesis. Don't forget semicolons!

##Next: [Variables](http://staticvoidgames.com/tutorials/hourOfCode/variables)
