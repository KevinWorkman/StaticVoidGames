##Writing Your Own Functions

Now you know how to use the variables that Processing keeps track of for you (like mouseX and mouseY), how to create and use your own variables, and how to call functions. Next, we'll learn how to write our own functions!

A function is just a set of instructions that tell the computer what to do. We've already seen the ellipse() function, which draws an ellipse. Processing has a bunch of other functions we can use- to draw shapes, to set the color, the background, etc.

But you can also write your own functions- in fact, you've already been doing that this whole time!

###Declaring a Function

We've seen that to **declare a variable**, we give it a **type**, a **name**, and a **value**. **Declaring a function** is similar: you give it a **type**, a **name**, and a **body**, which is just the set of instructions that the computer will follow whenever you **call that function**. This might sound complicated, but you've been doing that this whole time!

Remember our program:

    void draw() {
      int ellipseWidth = 25;
      int ellipseHeight = 25;
      ellipse(mouseX, mouseY, ellipseWidth, ellipseHeight);
    }
    
What you actually did here was **declare a draw() function** by giving it a **type** (void), a **name** (draw), and a **body** (the set of instructions inside the {}).

Whenever the draw() function is called, the computer follows the **set of instructions** inside that function.

##Processing automatically calls the draw() function 60 times every second!

This is how the code you wrote results in stuff displaying on screen. It's also why the above program draws a bunch of circles- 60 times a second, Processing calls the draw() function, and the draw() function tells the computer to draw a circle wherever the mouse is.

###The setup() function

Now we know how to **declare the draw() function**, and we know that Processing automatically calls that function 60 times per second.

Similarly, if you **declare a setup() function**, Processing will call the it, **once, at the very beginning of the program**. This allows us to set the size of the window.

How do we set the size of the window? You guessed it- by calling a function!

Here is a program that **declares a setup() function**, and in that setup() function, **calls the size() function** to set the size of the window.

    void setup() {
      size(500, 500);
    }

    void draw() {
      int ellipseWidth = 25;
      int ellipseHeight = 25;
      ellipse(mouseX, mouseY, ellipseWidth, ellipseHeight);
    }
    
![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/writingFunctions1.png)
    
**Remember**: Processing calls the setup() function once when the program starts, and then it calls the draw() function 60 times per second.

###Clearing the Background

Every time Processing calls the draw() function in the above program, you tell the computer to draw an ellipse at the current location of the mouse. That means you draw 60 circles per second! What if you only want to draw **one** circle that follows the mouse?

The answer is simple: at the beginning of your draw() function, you should clear out any old circles by **calling the background() function**.

    void setup() {
      size(500, 500);
    }

    void draw() {
      
      background(0, 0, 0);
      
      int ellipseWidth = 25;
      int ellipseHeight = 25;
      ellipse(mouseX, mouseY, ellipseWidth, ellipseHeight);
    }
    
The background() function takes 3 arguments: a red value, a green value, and a blue value, all between 0 and 255. Try out different values to get different colors! What happens if you use the mouseX and mouseY variables as arguments to the background() function?

Now, whenever the draw() function is called, you tell the computer to first **clear the screen and set it to black**, then draw an ellipse at the current location of the mouse.

Without the background() function, anything you draw to the window will stay there until you draw over it.


##Next: [Animation!](http://staticvoidgames.com/tutorials/hourOfCode/animation)