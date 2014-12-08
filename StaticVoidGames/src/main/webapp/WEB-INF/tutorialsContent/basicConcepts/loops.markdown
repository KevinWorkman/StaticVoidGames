#  [Basic Concepts](index.jsp)

## Loops

A programmers husband sends her to the store and says "get some bread, and while you're there pick up some eggs".

The programmer never came back.

Loops cause a block of code to be executed multiple times in a row. This comes in handy for patterns or for iterating through data structures (more on that in a bit). There are a few different kinds of loops: for loops, while loops, iteration loops (aka enhanced for loops), and do-while loops. They all operate on the same principle though: making your life easier by repeating work.

### The Wrong Way

Let's say we want to create a grid (for a game like checkers or tic-tac-toe). A grid consists of a series of evenly-spaced vertical and horizontal lines. We can pretty easily draw the grid using multiple calls to the line() function, like so:

    void setup(){
     size(500, 500); 
    }
    
    void draw(){
      
      //white background
      background(255);
      
      //horizontal lines
      line(0, 100, 500, 100);
      line(0, 200, 500, 200);
      line(0, 300, 500, 300);
      line(0, 400, 500, 400);
      
      //vertical lines
      line(100, 0, 100, 500);
      line(200, 0, 200, 500);
      line(300, 0, 300, 500);
      line(400, 0, 400, 500);
    }
    

Run this program to see our grid in all its glory. If it's not immediately clear how this is working, I suggest taking out a piece of paper and drawing a square that represents the window. Then step through each line of code, drawing the line represented by the coordinates passed into the line() function.

We can even improve the program by using variables:

    void setup(){
     size(500, 500); 
    }
    
    void draw(){
      
      //white background
      background(255);
      
      int spacing = 100;
      
      //horizontal lines
      line(0, spacing*1, width, spacing*1);
      line(0, spacing*2, width, spacing*2);
      line(0, spacing*3, width, spacing*3);
      line(0, spacing*4, width, spacing*4);
      
      //vertical lines
      line(spacing*1, 0, spacing*1, height);
      line(spacing*2, 0, spacing*2, height);
      line(spacing*3, 0, spacing*3, height);
      line(spacing*4, 0, spacing*4, height);
    }
    
Try changing the spacing variable to different values. Changing it to mouseX is pretty fun!

### For Loops

For loops allow you to repeat a block of code by grouping together three expressions: an initializer that runs a single time right before the loop is executed, a boolean evaluator that determines whether to continue executing the block of code, and an updater that changes something after each loop iteration. The syntax for a for loop consists of the for keyword, followed by the init, evaluator, and updater expressions separated by semicolons, and then the block of code to repeat until the evaluator evaluates to false. It sounds confusing, but it looks pretty simple:

    for(initializer; evaluator; updater){
       //code to repeat goes here
    }
    
Remember: the initializer runs a single time before the loop is executed, the evaluator runs right before each iteration of the loop and determines when to stop looping, and the updater runs after each iteration of the loop. A common form of the for loop lets you do something a certain number of times:

    void setup(){
     for(int i = 0; i < 10; i++){
      println(i);
     }
     exit();
    }
    
  * At the very beginning of this program, the initializer declares an int variable named i and initializes it to 0.
  * The evaluator is then run, and since 0 is less than 10, it evaluates to true.
  * Since the evaluator evaluated to true, the code block after the loop is executed.
  * The i variable holds 0, so 0 is printed out to the console.
  * The program reaches the end of the code block, and the updater which increments the i variable is run.
  * The evaluator is then run again, and since 1 is still less than 10, the code block is run again, with i holding 1.
  * This process repeates until i reaches 10 and the evaluator evaluatoes to false, at which point the program skips the code block and reaches the call to the exit() function.

Note: In programming, it's pretty standard to start counting from 0. For example, instead of going from 1 to 10, most programs will go from 0 to 9. This will make a lot more sense as you learn more, so get used to it now!

The initializer, evaluator, and updater can be anything you want. For example, we can use a for loop to recreate the grid program in fewer lines:

    void setup() {
      size(500, 500);
    }
    
    void draw() {
    
      //white background
      background(255);
    
      int spacing = 100;
    
      for (int i = 1; i < 5; i++) {
        //horizontal lines
        line(0, spacing*i, width, spacing*i);
    
        //vertical lines
        line(spacing*i, 0, spacing*i, height);
      }
    }
    
Try changing the value of the spacing variable and the evaluator (mouseX and 1000 are fun!) and see what happens! If you change them both to small numbers (like changing both to 10), you might notice something like this:

![](smallGrid.png)

This happens because of a combination of not using enough grid spaces, and the grid spaces being too small. Can't we just tell the program to fill the screen with lines?

    void setup() {
      size(500, 500);
    }
    
    void draw() {
    
      //white background
      background(255);
    
      int spacing = 10;
    
      for (int i = spacing; i < width; i+=spacing) {
        //horizontal lines
        line(0, i, width, i);
    
        //vertical lines
        line(i, 0, i, height);
      }
    }
    
Take a close look at the initializer, evaluator, and updater in the loop. Now, the loop tells the program to start at the value specified by the spacing variable and to continue drawing lines at that spacing until it reaches the width of the window. 

Note: This works because the width and height of the window happen to be the same! What would you have to do if they were different?

### While Loops

A while loop is like a for loop, except it only takes the boolean evaluator. The initializer and the updater are up to you! For example:

    void setup(){
     int i = 0;
     while(i < 10){
      println(i);
      i++;
     }
     exit();
    }
    
By now you can probably guess exactly what this program does. The while loop acts a lot like an if statement that repeats until it evaluates to false. 

Note: be careful to avoid infinite loops, or loops whose evaluators never evaluate to false! This will cause your program to crash!

Here is the same program as above, now using a while loop instead:

    void setup() {
      size(500, 500);
    }
    
    void draw() {
    
      //white background
      background(255);
    
      int spacing = 10;
      int i = spacing;
      while (i < width) {
        //horizontal lines
        line(0, i, width, i);
    
        //vertical lines
        line(i, 0, i, height);
        
        i+=spacing;
      }
    }
    
### Nested Loops

You can put just about anything inside the code block of a loop- including other loops! To see how useful that can be, let's start with this program that uses one loop to draw a single row of circles:

    void setup() {
      size(500, 500);
      ellipseMode(CENTER);
      ellipseMode(RADIUS);
    }
    
    void draw() {
    
      //white background
      background(255);
    
      int spacing = 25;
      int radius = 10;
      for(int x = spacing; x < width; x += spacing){
       ellipse(x, 250, radius, radius); 
      }
    }
    
This program draws a single row of circles. But how can we draw multiple rows? By putting the row-drawing code inside another loop!

    void setup() {
      size(500, 500);
      ellipseMode(CENTER);
      ellipseMode(RADIUS);
    }
    
    void draw() {
    
      //white background
      background(255);
    
      int spacing = 25;
      int radius = 10;
      for (int y = spacing; y < height; y += spacing) {
        for (int x = spacing; x < width; x += spacing) {
          ellipse(x, y, radius, radius);
        }
      }
    }
    
Taking a closer look at the nested for loop, the outer loop works its way from the top of the screen down to the bottom, and at each y value, it draws a row. If it makes more sense, you can put the row-drawing code into its own function:

    void setup() {
      size(500, 500);
      ellipseMode(CENTER);
      ellipseMode(RADIUS);
    }
    
    void draw() {
    
      //white background
      background(255);
    
      int spacing = 25;
      int radius = 10;
      for (int y = spacing; y < height; y += spacing) {
        drawRow(y, spacing, radius);
      }
    }
    
    void drawRow(int y, int spacing, int radius) {
      for (int x = spacing; x < width; x += spacing) {
        ellipse(x, y, radius, radius);
      }
    }
    
This code does the same exact thing as before; it just splits the row-drawing code off into its own function to improve readability. Keep in mind that you can put all kinds of code inside loops- if statements, function calls, other loops, etc. Every program you use consists of a combination of these basic building blocks!

### Exercises

  * Create a 10 x 10 grid that is 1000 pixels wide and 250 pixels tall.
  * Modify that program so that if you change the size, the grid stretches. It should draw the same number of grid cells, but they should stetch to fill the screen. Hint: get out a piece of paper and a pencil and start drawing out the locations of lines given different window sizes!
  * Modify the program to draw any amount of cells and still stretch to fill the window at any size.
  * Create a program that draws a checkerboard, complete with alternating red and black squares!

###  Next: [Scope](Scope.jsp)
