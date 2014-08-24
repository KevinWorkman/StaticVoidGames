#  [Basic Concepts](index.jsp)

## Writing Your Own Functions

So far, you've learned how to declare and use variables and how to call
functions. Next we'll cover how to write your own functions, which lets you
avoid typing the same thing over and over again and also gives you a way to
receive information from Processing.

### Declaring a Function

Remember that to declare a variable, you give it a type and a name. Declaring
a function is a lot like declaring a variable: you give it a return type and a
name, and then you list any arguments it takes inside parentheses, and finally
you give it a body that contains the actual code that the function performs.

You can think of a function like a to-do list of instructions that the
computer carries out any time the function is called.

This might sound complicated, but you've already been doing this! Recall a
basic program might look like this:

    
    
    void draw() {
    	background(0);
    	ellipse(50, 50, 25, 25);
    }
    

The draw() function is automatically called by Processing 60 times a second,
but you have to declare it first, which is exactly what you've been doing!

There are other functions that Processing calls automatically, which you can
declare in order to expand your program. For example, the setup() function is
called only a single time at the beginning of the program. This allows you to
do things like set the size of the window. Unlike the draw() function, the
setup() function is only called once.

To use the setup() function, we first have to declare it by typing its return
type (which is void, more on that later), then its name. Then inside
parentheses we declare any arguments it takes, and since the setup() function
does not take any arguments (more on those later too), we can just use empty
parentheses. Finally, inside curly brackets {} we declare the body of the
function, which is any code we want to run when the setup() function is
called. We can call the size() function, which takes two parameters to change
the size of the window:

    
    
    void setup(){
      size(500, 500); 
    }
    
    void draw() {
    	background(0);
    	ellipse(50, 50, 25, 25);
    }
    

The setup function is useful because some functions, such as the size()
function, should only be called once.

### Calling Your Own Functions

The below code draws a target by drawing 4 circles at decreasing sizes in
alternating colors. Run it and try changing the values to understand what it's
doing.

    
    
    void setup() {
      size(500, 500);
    }
    
    void draw() {
    
      background(0);
      
      fill(255, 0, 0);
      ellipse(250, 250, 100, 100);
    
      fill(255, 255, 255);
      ellipse(250, 250, 75, 75);
    
      fill(255, 0, 0);
      ellipse(250, 250, 50, 50);
    
      fill(255, 255, 255);
      ellipse(250, 250, 25, 25);
    }
    

We can simplify this code by taking the 8 lines that draw the target and
putting them inside their own function.

    
    
    void setup() {
      size(500, 500);
    }
    
    void draw() {
    
      background(0);
      
      drawTarget();
    }
    
    void drawTarget(){
      fill(255, 0, 0);
      ellipse(250, 250, 100, 100);
    
      fill(255, 255, 255);
      ellipse(250, 250, 75, 75);
    
      fill(255, 0, 0);
      ellipse(250, 250, 50, 50);
    
      fill(255, 255, 255);
      ellipse(250, 250, 25, 25);
    }
    

Now we can see that the draw() function is really only doing two things:
drawing the background, and then drawing the target. This becomes even more
useful with longer, more complicated functions or when working on a team. The
draw() function doesn't care what the drawTarget() method is doing, as long as
it gets the job done. So you can imagine multiple team members working on
different functions and just using them without caring about the gory details.

### Arguments

The above code is more organized and readable, but it's not very reusable. For
example, what if we wanted to draw the target smaller or at a different
location? What if we wanted to draw multiple targets? With our approach so
far, we'd have to copy and paste the drawTarget() function and then change the
numbers we're passing into the ellipse() function. That sounds like a lot of
work! Luckily, we can use function arguments to make our life easier.

Declaring function arguments is exactly like declaring normal variables,
except you do it inside the parentheses that come after a function's name.
Give each argument a type and a name, and any code that calls your function
will have to supply those types of values. You can then access the values
inside your function by referring to the argument variables. This is useful
for doing the same thing multiple times or with varying values.

For example, we can change our drawTarget() function to take 3 arguments: the
x and y position of the target, as well as the target size to draw. Then we
can call that function with whatever values we want, as many times as we want!

    
    
    void setup() {
      size(500, 500);
    }
    
    void draw() {
    
      background(0);
      
      drawTarget(100, 50, 50);
      drawTarget(300, 200, 100);
      drawTarget(50, 450, 10);
      drawTarget(250, 400, 75);
      drawTarget(400, 100, 50);
    }
    
    void drawTarget(int x, int y, int size){
      fill(255, 0, 0);
      ellipse(x, y, size, size);
    
      fill(255, 255, 255);
      ellipse(x, y, size*.75, size*.75);
    
      fill(255, 0, 0);
      ellipse(x, y, size*.5, size*.5);
    
      fill(255, 255, 255);
      ellipse(x, y, size*.25, size*.25);
    }
    

The above code has parameterized the drawTarget() function to take in the
location and size of the target. Then that function is called with different
values, allowing us to do the same thing multiple times without repeating
code.

### Return Types

So far we've only been using functions with void return types, which means
they do not return anything. These functions usually do something (like draw a
target), but you can write functions that return a value instead.

Declaring a function that returns a value is exactly like declaring a void
function, except we use the type of value returned instead of void. For
example, this function returns pi:

    
    
    float getPi(){
      return 3.14159;
    }
    

The above code declares a function named getPi that doesn't take any arguments
and returns a float value. In the body of the function, the return keyword is
used to return a hard-coded value.

We can do more than return hard-coded values. Let's say we want to draw a
single target in the center of the screen. We could call our drawTarget()
function with a hard-coded value for its position, like this:

    
    
    void setup() {
      size(100, 100);
    }
    
    void draw() {
    
      background(0);
      
      drawTarget(50, 50, 25);
    }
    
    void drawTarget(int x, int y, int size){
      fill(255, 0, 0);
      ellipse(x, y, size, size);
    
      fill(255, 255, 255);
      ellipse(x, y, size*.75, size*.75);
    
      fill(255, 0, 0);
      ellipse(x, y, size*.5, size*.5);
    
      fill(255, 255, 255);
      ellipse(x, y, size*.25, size*.25);
    }
    

But what happens if we change the size of the window that we pass into the
size() function inside the setup() function? We'd then have to change the
numbers we pass into the drawTarget() function as well. That makes it harder
to maintain our code, since it doubles the amount of work we have to do each
time we make a change- and imagine if you wanted to draw 1000 things based on
those values!

Instead, we can create functions that return the center X and Y coordinates of
the window, that way no matter what the window size is, we know where the
center is!

    
    
    void setup() {
      size(500, 500);
    }
    
    void draw() {
      background(0);
      int centerX = getCenterX();
      int centerY = getCenterY();
      drawTarget(centerX, centerY, 50);
    }
    
    int getCenterX(){
      return width/2;
    }
    
    int getCenterY(){
      return height/2;
    }
    
    void drawTarget(int x, int y, int size){
      fill(255, 0, 0);
      ellipse(x, y, size, size);
    
      fill(255, 255, 255);
      ellipse(x, y, size*.75, size*.75);
    
      fill(255, 0, 0);
      ellipse(x, y, size*.5, size*.5);
    
      fill(255, 255, 255);
      ellipse(x, y, size*.25, size*.25);
    }
    

In the above code, we've declared two functions, getCenterX() and
getCenterY(), that use the predefined width and height variables to return the
center X and Y coordinates of the screen. In the draw() function, those
functions are called, and the values they return are then passed into the
drawTarget() function.

Note that we could have shortened the code slightly by using inline function
calls to getCenterX() and getCenterY() instead of storing them in variables.
You should do whichever seems more natural to you.

    
    
    void draw() {
      background(0);
      drawTarget(getCenterX(), getCenterY(), 50);
    }
    

### Exercises

  * Create a function that draws 4 targets around an X and Y location: one above, one below, one to the left, and one to the right of the location. Hint: You can call the drawTarget() method from your function!
  * Why is it probably not smart to have a function called getPi() that returns a hard-coded value of pi? Hint: Look at the Processing API for predefined variables.
  * Create a function that draws a house, taking in parameters for the house size and location.
  * Create a function that draws a city block that consists of 4 houses, taking in parameters for the location and size of the block. This function can call the function you create above!
  * Create a function that draws 4 a neighborhood that consists of 4 blocks, as well as the streets in between them. This function should take in parameters for the location and size of the neighborhood.
  * From the draw() function, call the drawNeighborhood() function enough times to create a city! You might have to make the window bigger by calling the size() function inside the setup() function.

###  Next: [If Statements!](IfStatements.jsp)
