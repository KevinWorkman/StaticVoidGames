#  [Basic Concepts](index.jsp)

## Scope

So far you've learned about creating variables, functions, and if statements.
Before going too much further, we have to cover an important concept called
scope, or where a variable or function is accessible.

The takeaway is that variables that are declared inside a function, if
statement, or loop are only available inside that block of code.

Consider the following code:

    
    
    void draw() {
    
      background(0);
      
      fill(#00ff00);
      drawSquare();
    }
    
    void drawSquare(){
      int squareX = mouseX;
      int squareY = 50;
      int size = 25;
      
      rect(squareX, squareY, size, size);
    }
    

The drawSquare() function contains variables that decide where and how large
the square is drawn. Since the variables are declared inside the function,
they are only accessible inside that function. For example, if I wanted to
change the value of squareX, I could NOT do this:

    
    
    void draw() {
    
      background(0);
      fill(#00ff00);
      
      int squareX = mouseY;
      drawSquare();
    }
    
    void drawSquare(){
      int squareY = 50;
      int size = 25;
      
      rect(squareX, squareY, size, size);
    }
    

If you run that code, you'll get an error saying that Processing cannot find
anything named "squareX". Why is that?

The squareX variable inside the draw() function is a completely different
variable than any variables inside the drawSquare() function! Any changes you
make to a variable outside of the drawSquare() function will have no effect on
the variables inside the drawSquare() function.

Similarly, if I wanted to draw another square with a different size at the
same coordinates, I could NOT do this:

    
    
    void draw() {
    
      background(0);
      
      fill(#00ff00);
      drawSquare();
      
      rect(squareX, squareY, 10, 10);
    }
    
    void drawSquare(){
      int squareX = mouseX;
      int squareY = 50;
      int size = 25;
      
      rect(squareX, squareY, size, size);
    }
    

The squareX and squareY variables are only available inside the drawSquare()
function. As soon as the drawSquare() function ends (with the closing curly
bracket), those variables are no longer available.

So what if we want to create another function that draws another shape with
the same size? We know we can't do this:

    
    
    void draw() {
    
      background(0);
      
      fill(#00ff00);
      drawSquare();
      
      fill(#ff0000);
      drawCircle();
    }
    
    void drawSquare(){
      int squareX = mouseX;
      int squareY = 50;
      int size = 25;
      
      rect(squareX, squareY, size, size);
    }
    
    void drawCircle(){
      int circleX = 50;
      int circleY = mouseY;
     
      ellipse(circleX, circleY, size, size);
    }
    

The drawCircle() function cannot access the size variable, since it is
declared inside the drawSquare() method. What should we do? We could just
create another size variable inside the drawCircle() function, but what if we
want to have 100 different functions that use the same variable? We'd have to
change the code in 100 different places whenever we made a change to the size.

Another way to do this would be to pass the value as an argument to all of the
functions, and that would work in this example because both functions are
called from the draw() function.

    
    
    
    
    
    void draw() {
    
      background(0);
      
      int size = 25;
      
      fill(#00ff00);
      drawSquare(size);
      
      fill(#ff0000);
      drawCircle(size);
    }
    
    void drawSquare(int length){
      int squareX = mouseX;
      int squareY = 50;
      
      rect(squareX, squareY, length, length);
    }
    
    void drawCircle(int radius){
      int circleX = 50;
      int circleY = mouseY;
     
      ellipse(circleX, circleY, radius, radius);
    }
    

But often the functions aren't all called from the same place, which makes
passing the same value as an argument much harder. In those cases, we can
instead take advantage of the fact that variables declared at the same level
as multiple functions (as in, outside of them) are available inside all of
those functions.

This might sound confusing, but it looks simple:

    
    
    
    int size = 25;
    
    void draw() {
    
      background(0);
      
      fill(#00ff00);
      drawSquare();
      
      fill(#ff0000);
      drawCircle();
    }
    
    void drawSquare(){
      int squareX = mouseX;
      int squareY = 50;
      
      rect(squareX, squareY, size, size);
    }
    
    void drawCircle(){
      int circleX = 50;
      int circleY = mouseY;
     
      ellipse(circleX, circleY, size, size);
    }
    

In the above code, we declare the size variable outside of the functions, so
both the drawSquare() and drawCircle() functions can use it. Now when we
change the size variable in one location, we change it in every location that
uses it.

### Garbage Collection

Variables going outside of scope is a good thing! In Java, when a variable
goes out of scope, that automatically allows the garbage collector to free up
the space previously used by that variable. You don't have to worry about it
too much (that's the beauty of it), but just think about how annoying it would
be to have to manually clean up every variable to prevent a crash when the
program runs out of memory- which is exactly what you have to do in some
languages!

### Exercises

  * In your own words, what is scope?

###  Next: [Animation](Animation.jsp)

