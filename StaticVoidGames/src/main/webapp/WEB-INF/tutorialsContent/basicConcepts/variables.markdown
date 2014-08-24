#  [Basic Concepts](index.jsp)

## Variables

Variables are a way to store information. They can store things like numbers,
words, Objects, and data structures (if those last two don't make sense, we'll
have more on that later). They can make it easier to refer to a changing
value, or they can make it easier to modify code.

### Predefined Variables

Processing has quite a few predefined variables, which you can find in the
[Processing Api](http://processing.org/reference/). For example, mouseX and
mouseY store the X and Y value of the mouse location.

Remember our program from before, which simply draws a blue and red circle on
a black background:

    
    
    void draw() {
      background(0);
      
      fill(0, 0, 255);
      ellipse(25, 50, 25, 25);
      
      fill(255, 0, 0);
      ellipse(75, 50, 25, 25);
    }
    

Instead of using predefined numbers (an evil practice called hard-coding), we
can use the mouseX and mouseY variables to draw one of the circles wherever
the mouse is.

    
    
    void draw() {
      background(0);
      
      fill(0, 0, 255);
      ellipse(mouseX, mouseY, 25, 25);
      
      fill(255, 0, 0);
      ellipse(75, 50, 25, 25);
    }
    

### Defining Your Own Variables

The predefined variables in Processing are pretty useful, but most programming
involves creating your own variables for storing information that you care
about. To define a variable, you have to give it a type, a name, and a value.
For example, to define a variable that holds a whole number named radius with
a value of 25, you would do this:

    
    
    int radius = 25;
    

The 'int' part stands for integer; the radius part is simply a name and can be
anything you want; the equals sign applies whatever is to the right of it; the
25 is the value you want to apply; and the semicolon indicates that the line
is over, much like a period in this text.

This is called declaring and initializing a variable. You declare it by giving
it a type and a name, and you initialize it by giving it a value.

We can use our variable to make our life a little easier if we want to change
the size of the circles:

    
    
    void draw() {
    
      int radius = 25;
    
      background(0);
      
      fill(0, 0, 255);
      ellipse(mouseX, mouseY, radius, radius);
      
      fill(255, 0, 0);
      ellipse(75, 50, radius, radius);
    }
    

So now when we want to change the size of the circles, we only have to make a
change in one place instead of four. This might not seem like a big deal, but
in real programs that are thousands of lines long, this really adds up!

### Variable Types

Remember: when defining a variable, you always have to give it a type. We've
looked at the int type, but there are several other types of variable
available:

  * int: An int variable can hold any whole number (no decimal places) between -2,147,483,648 and 2,147,483,647.
  * long: A long is like an int, except it can hold numbers between -9,223,372,036,854,775,808 and 9,223,372,036,854,775,807. Use this for really big numbers (such as the number of milliseconds since 1970, which is how Processing keeps track of time). A long takes up more memory than an int variable, so for most cases, you should use int.
  * double: A double can hold decimal places, such as 1.5 or 0.3333. If you're dealing with decimals in Java, double is usually what you'll want to use.
  * float: A float is a lot like a double, except it takes up less memory because it's less precise. In Processing, many functions require a float argument. For example, the values passed into the fill() function in the above code are floats.
  * char: A char is a single unicode character. Since characters are stored as numbers, char variables can have any value from 0 to 65,535. To make it easier to read, you can also use a letter in single quotes ('a', 'b', 'b', etc) to refer to a char value. Processing functions that deal with keyboard input often use char values.
  * boolean: A boolean can only hold one of two values: true or false. This is useful when working with loops or if statements.
  * Object: An Object is a way to keep track of data by encapsulating related variables in a single instance. More on those later.
  * String: A String value holds text, which you can refer to by surrounding it with quotation marks: "some text here", "hello", etc.

There are a couple other variables types, but you'll almost never use them in
most Processing applications. If you're curious, check out [this page on data 
types](http://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
). If you're confused about all of this, that's okay, as we'll get to each
type of variable as we go. For now, just keep in mind that when defining a
variable, you have to give it a type, and there are a bunch of different types
you can use.

### Performing Operations on Variables

Variables hold values of certain types, so any operator that can be used on a
variable's type can also be used on that variable. For example, all the normal
calculator operators (+, -, *, /) can be used on any of the number types. For
example, we can use the width and height variables as well as division to draw
a circle in the middle of the screen:

    
    
    void draw() {
    
      int radius = 25;
    
      background(0);
      
      fill(0, 255, 0);
      ellipse(width/2, height/2, radius, radius);
    }
    

Or we can use subtraction with two variables to achieve a mirroring effect:

    
    
    void draw() {
    
      int radius = 25;
    
      background(0);
      
      fill(0, 0, 255);
      ellipse(mouseX, mouseY, radius, radius);
      
      fill(255, 0, 0);
      ellipse(width-mouseX, height-mouseY, radius, radius);
    }
    

### Exercises

  * Remember, int variables do not have a decimal point after them. What do you think the following code does? 
    
    
    int a = 10;
    int b = 20;
    int c = a/b;
    println(c);
    

Write a program that tests your hypothesis. What do you think you should do
instead?

  * Where is the coordinate (0, 0) in a Processing window? What about (100, 50)? Write a program to test your answers.
  * What happens if you remove the call to the background() function in the above code? Why is that?
  * Write a program that draws 10 circles with a radius of 5 on the screen, like polka dots.
  * Change the size of the polka dots to have a radius of 10. If you used a variable, this should be very easy!
  * Consider the following statements: 
    
    
    int a = 10;
    int b = 20;
    a = b;
    

What are the new values of a and b?

###  Next: Learn how to convert between different number types by [Casting
Primitives](CastingPrimitives.jsp)
