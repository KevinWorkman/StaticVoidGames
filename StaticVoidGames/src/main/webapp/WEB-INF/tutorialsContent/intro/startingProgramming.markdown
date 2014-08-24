#  [Intro to Programming](index.jsp)

## Starting Programming

This discussion covers the basics of how a program works, including variables,
functions, and other syntactic topics.

Remember our first Processing program, which drew circles wherever the mouse
was:

    
    
    void draw() {
       ellipse(mouseX, mouseY, 10, 10);
    }
    

But how does that code cause your program to do what it did? To really
understand what's going on, we have to review some concepts from middle school
algebra.

### Variables

In algebra, a **variable** is basically a letter that can be used in the place
of an unknown number. For example, consider the following expressions:

x = 7 + 3

y = x + 5

What is x equal to now? What is y equal to now?

In programming, a variable is a name that can hold a value. The idea of
assigning values to variables is crucial to programming, at least in languages
like Processing and Java. Variables allow a programmer to keep track of data
that might change.

Variables do not have to be single letters- actually, the more descriptive the
name, the better. In the circle program, **mouseX** and **mouseY** are
variables that hold the location of the mouse- the x position is the number of
pixels from the left side of the window, and the y position is the number of
pixels from the TOP of the window.

So, we can already make an improvement to the program. What if we want to make
the circles bigger? If you haven't guessed by now, the 10s specify the width
and height of the ellipse. So to change the size of the circle, we have to
change two numbers, right? Not if we use a variable!

    
    
    void draw() {
       int diameter = 25;
       ellipse(mouseX, mouseY, diameter, diameter);
    }
    

Now, to change the size of the circle, we just have to make a single change!

When creating, which is called **declaring**, a variable, we have to tell the
computer what **type** of data the variable will hold as well as the
variable's name. There are a few different types of data, but an int (integer)
is just a whole number without a decimal point. To put a value in a variable,
which is called **initializing** the variable, we use the equals sign followed
by a value of the type we specified.

### Functions

Remember functions like f(x) = mx + b from algebra? In algebra, functions are
a way to generate some output based on some input. You feed in some values
(which can be stored in variables), and you get back another value.

In programming, a function is a way to repeat a set of instructions without
needing to type them in every time. They have a name (the name of the above
algrebra function is f) and **arguments** which are just variables that the
function uses (the above algebra function takes one argument, x), and they can
also return a value (the above algebra function returns the y value of a line
based on the x value, the slope, m, of the line, and the y intercept, b).

To **declare** a function, it's a lot like declaring a variable- you give it
the following:

  * A **return type**- the type of value returned by the function, or **void** if it doesn't return anything. 
  * A name- just like a variable, the more descriptive the better.
  * A list of **parameters**- the values that the function must be given as arguments, in parenthesis. 

Those are called the function's **signature**. The **body** of the function is
listed after the signature, and it's just a list of instructions to be carried
out when the function is called, surrounded by {} brackets.

You declared the draw() function by giving it a void return type, the name
draw, and an empty parameter list.

To call a function, you simply use its name followed by any arguments it
requires in parenthesis: you called the ellipse() function by using its name
and supplying it 4 arguments: the x and y positions to locate the ellipse, and
the width and height to draw the ellipse.

So, who calls the draw() function? Processing automatically calls it, 60 times
every second!

The idea of using functions to repeat the same operations on different values
is widely used in programming. It allows a programmer to perform the same
action many times without needing to repeat code. The call to ellipse() is
hiding about a hundred lines of code! You definitely would not want to type
100 lines of code every time you wanted to draw a circle. Another benefit of
using functions is that you don't need to know the exact code inside a
function to use it- we don't care how the computer accomplishes drawing the
ellipse, as long as it draws the ellipse we asked for.

### Semicolons

You've probably noticed that each line of code in the program ends with a
semicolon. This is just a way to tell the computer that the current
instruction is done. Think of it like a period at the end of a sentence. I
guarantee that you WILL get a compiler error because you forgot a semicolon-
welcome to the club.

### Comments

In addition to regular code, we can also add **comments** to help humans read
the code. The computer ignores comments, but they can help you keep your code
organized. You can write a one line comment by adding two slashes // at the
beginning, or a multiple-line comment by surrounding the whole block with /*
and */. For example:

    
    
    /*
    This is our first example Processing program.
    It draws a circle wherever the mouse is.
    */
    void draw() {
      //specify the diameter of the ellipse
      int diameter = 25; 
      //draw the ellipse
      ellipse(mouseX, mouseY, diameter, diameter);
    }
    

### Functions vs Methods

We've been calling them functions, but some people call them **methods**
instead. In Java, both words are talking about the same thing. I'll probably
switch between calling them functions and methods from here on out, but both
words mean the same thing.

### Naming Conventions

Technically, you can name variables and methods whatever you want. But your
code will be much easier to read if you follow the standard naming
conventions- sketches (later, classes) start with an upper-case letter.
Variables and functions start with a lower-case letter. If a variable or a
method is multiple words, you should use camelCase, for example,
diameterOfEllipse. It might sound pedantic, but it can be really confusing if
somebody doesn't follow these unwritten rules- actually, they are written, [he
re](http://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-13
6057.html) and [here](http://docs.oracle.com/javase/tutorial/java/nutsandbolts
/variables.html#naming).

### Exercises

  * In Processing coordinates, where is the point (0, 0)? Where is the point (50, 75)? Where is the point (75, 25)? Write a program to test your answers!
  * What is the difference between a parameter and an argument?
  * What happens if you rename the draw() function? Why?
  * Consider the following statements: 
    
    
    int a = 10;
    int b = 20;
    a = b;
    

What are the new values of a and b?

###  Next: [Experimenting with the API](ProcessingApiExploring.jsp)
