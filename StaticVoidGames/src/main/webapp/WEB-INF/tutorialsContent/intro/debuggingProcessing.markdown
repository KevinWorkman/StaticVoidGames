#  [Intro to Programming](index.jsp)

## Debugging Processing

There are a few types of problems you're likely to run into: **compile-time
errors**, **runtime exceptions**, and **logic problems**. This tutorial
introduces them and suggests methods for fixing them as well as some general
guidelines on making debugging easier for you and for people you might ask for
help.

### Compiler Errors

A compile-time error is a problem in the code that prevents Processing from
compiling your code into Java byte code. There are plenty of compiler errors,
but if you think of Processing as a language, you can think of compiler errors
as the result of using a word that isn't in Processing's dictionary or using
improper grammar. Compiler errors happen before the program is run, and they
are Processing's way of saying "I don't know what you're trying to do here".

Remember our first Processing program, which drew circles wherever the mouse
was:

    
    
    void draw() {
       ellipse(mouseX, mouseY, 10, 10);
    }
    

That code compiles and runs fine, but what if we throw in a typo?

    
    
    void draw() {
       ellipse(mouseX, mousey, 10, 10);
    }
    

If you type that code into your Processing editor and try to run it, you'll
get a compiler error that says Cannot find anything named "mousey". Processing
also highlights the line of code that it's talking about.

In this case, the error is easy enough to fix: Java (and therefore Processing)
is case-sensitive, which means that mouseY is not the same as MouseY, MOUSEY,
or mousey. Changing _mousey_ back to _mouseY_ fixes the problem.

### Runtime Exceptions

A runtime exception is an error that occurs after the program has been
compiled, while it is running. You can think of them as occuring when you've
used correct spelling and grammar, but wrote something that doesn't make sense
once the computer starts executing the program. Some examples include dividing
by zero, trying to read in a file that doesn't exist, or calling a function
with a parameter that it doesn't know how to handle.

Processing rolls up both compiling and running the program into a single step:
pressing the play button. Runtime exceptions occur in the resulting Java code,
not directly in your Processing code. This can make them hard to track down,
but runtime exceptions will also include a **stack trace**, which is a list of
the last steps the program took before encountering the error.

Processing will also highlight the line that resulted in the exception. And
that line might look okay- after all, it compiled just fine! Many times when
you encounter a runtime exception, you then have to figure out what is being
passed into that line, where those values came from, and why that caused the
exception.

Here's an example of some code that might look okay, compiles fine, but throws
an exception when you run it:

    
    
    void setup() {
      size(-100, -100);
      background(0);
    }
    
    void draw() {
       ellipse(mouseX, mouseY, 10, 10);
    }
    

If you try to run the code, you'll get an **IllegalArgumentException**, and
this stack trace:

Exception in thread "Animation Thread" java.lang.IllegalArgumentException:
Width (-100) and height (-100) cannot be <= 0

at java.awt.image.DirectColorModel.createCompatibleWritableRaster(DirectColorM
odel.java:999)

at java.awt.image.BufferedImage. (BufferedImage.java:321)

at processing.core.PGraphicsJava2D.allocate(Unknown Source)

at processing.core.PGraphicsJava2D.setSize(Unknown Source)

at processing.core.PApplet.resizeRenderer(Unknown Source)

at processing.core.PApplet.size(Unknown Source)

at processing.core.PApplet.size(Unknown Source)

at sketch_1.setup(sketch_1.java:21)

at processing.core.PApplet.handleDraw(Unknown Source)

at processing.core.PApplet.run(Unknown Source)

at java.lang.Thread.run(Thread.java:662)

At first glance, that might look like a bunch of scary red gobbledygook, but
if you look carefully, you can find clues about what's going on. Again, this
is happening in the Java code that results from Processing converting your
code, and that Java code is then converted to Java byte code and run by the
JRE, which actually generates the exception.

The exception description itself is pretty telling, since having a negative
width and height doesn't make sense. But in cases where it's not so obvious,
we can use the stack trace to track through what's going on in the program.
Four lines up from the bottom (which is the beginning, the oldest instruction
on the stack), you can see that the error is happening in the setup()
function.

This example is pretty obvious, but things like this can happen if you're
passing in a variable whose value is not always known ahead of time because
it's based on a complicated math equation or user input. In situations like
that, it's your job as a programmer to figure out what's crashing the program
and why, and then how to fix the source of the problem. This often involves
consulting the API and reference pages.

### Logic Problems

Another kind of error you'll encounter while programming is problematic logic.
This is when your program does something different from what you expected it
to do, but it doesn't actually generate a compiler error or runtime exception.
This is usually due to misunderstanding what a function really does, or when
your assumptions don't match what's actually happening. These can be the
hardest problems to track down.

At first glance, this is the program you're probably getting sick of seeing.
But it actually contains a logic error:

    
    
    void Setup() {
      size(500, 500);
      background(0);
    }
    
    void draw() {
       ellipse(mouseX, mouseY, 10, 10);
    }
    

If you run that program, you'll notice that it's a small window with a gray
background, even though we're setting its size to 500 by 500 and its
background to black. What gives?

We can use the [println()](http://processing.org/reference/println_.html)
function to find clues about what's going on. The println() function outputs
text to the **console**, which is the black box at the bottom of Processing.

    
    
    void Setup() {
      println("Setup is running!");
      size(500, 500);
      background(0);
    }
    
    void draw() {
       ellipse(mouseX, mouseY, 10, 10);
    }
    

If you run that program, you can see that the "Setup is running!" message is
never printed out. So we now know there must be something preventing the
setup() function from being called. After taking a closer look at the setup()
function, we realize that we accidentally used Setup() instead of setup(). If
we correct that, the message is indeed printed out, and the size and
background are set correctly.

This problem is overly simplified just to demonstrate what I'm talking about,
but you'll go through this process more times than you can count as you
program. Note- if we couldn't figure out the problem after adding the
println() statement, the next step might have been to eliminate the draw()
function- if the problem is fixed when we remove it, we know the problem is in
the draw() function somewhere. And even if the problem is not fixed by
removing it, going through a **process of elimination** helps narrow down the
problem by eliminating code that doesn't cause the problem, which makes it
easier for other people to help you out by looking at your code.

### Debugging Problems

When you encounter these problems, you can do a few things to figure out
what's going on:

  * Check your spelling and capitalization.
  * Make sure your brackets and parenthesis match. For every opening brace or parenthesis, make sure you know where its closing brace is, and vice versa. Using indentation helps with this.
  * Make sure the functions you're using take the types of variables you're trying to give them- if a function wants 4 values and you only give it 3, you're going to have a bad time. Similarly, if a function wants a String (more on that later) and you're giving it an int, the compiler won't know what to do.
  * Make sure you have access to whatever you're trying to use- we'll go over this in more depth later.
  * Google the compiler error to see how other people dealt with similar problems.
  * Break your problem down into smaller pieces. If everything in your program works except setting the background, then write a new program that ONLY sets the background- this will be easier to debug, and easier to ask other people to look at for you.
  * Use the println() function to make sure variables have the values you expect them to and that the code is executing correctly.
  * Post your question, compiler error, and code in the [forum](forum.StaticVoidGames.com) and somebody will try to help you out. To increase your chances of getting help, [make it easy for people to help you](http://forum.staticvoidgames.com/posts/list/10.page)! 

### Program in Small Steps

If you only remember one thing from this tutorial, it should be this: you
should write programs by breaking them down into smaller pieces, then writing
those pieces independently, and, finally, combining the pieces to form your
program.

A common mistake that novice programmers make is writing a whole program
before doing any testing. Don't do this! Program in small steps- compile and
run the program as often as possible- if not every line, then at least every
logical block or function.

Test pieces as you write them by hardcoding their inputs and printing out
their outputs, or by displaying incomplete animations. If your program
requires two different pieces (like setting the background and drawing a
shape), then develop each piece independently- write a program that sets the
background, then write a different program that draws a shape. Only when both
pieces work by themselves should you think about combining them. This might
seem tedious, but as you write more complicated programs, this approach will
save you countless headaches. It will also help you post on the
[forum](forum.StaticVoidGames.com) if you get stuck- it's much easier to help
somebody with one problematic piece than it is to help somebody who wrote
hundreds of lines and doesn't know where things went wrong.

### Things to Think About

  * The println() function is pretty much the only thing traditional programming lessons use. Aren't you glad you're using beautiful circles instead?
  * What's the difference between a compiler error, a runtime exception, and a logic problem?

###  Next: You now know how to write, debug, and run a program. How do you
[deploy](ProcessingDeployment.jsp) it so other people can see it?