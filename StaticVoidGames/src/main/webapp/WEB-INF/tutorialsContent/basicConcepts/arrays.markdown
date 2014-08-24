## Arrays

What's a pirate's favorite data structure? ARRays!

So far you know how to use variables and interact with them using things like
if statements and for loops. Arrays are a lot like the variables you're used
to seeing, with one major difference: arrays hold multiple values instead of
just one.

### The Wrong Way

Imagine a scenario where you want to draw a bunch of vertical lines, all
moving to the right at different speeds. Let's start out with three:

    
    
    float x1 = 100;
    float x2 = 200;
    float x3 = 300;
    
    float speed1 = 2;
    float speed2 = 3;
    float speed3 = 4;
    
    void setup(){
     size(500, 200); 
    }
    
    void draw(){
      
      background(0);
      stroke(255);
      
      x1 += speed1;
      if(x1 > width){
        x1 = 0;
      }
      
      x2 += speed2;
      if(x2 > width){
        x2 = 0;
      }
      
      x3 += speed3;
      if(x3 > width){
        x3 = 0;
      }
      
      line(x1, 0, x1, height);
      line(x2, 0, x2, height);
      line(x3, 0, x3, height);
    }
    

Run this program to see three lines, each moving at a different speed. This
works, but what happens if we want to have 10 lines, or a hundred?

### Arrays

An array as a single variable that can hold multiple values. You can think of
an array as a drawer, a piece of furniture with several boxes that pull out.
Each box has a number, so you can store things in a box at a specific drawer
number or refer to the contents of a specific box by using its drawer number.

Arrays work the same as a drawer, with each value, or element, held in an
index of the array. You can use the index to refer to a specific value, which
we'll see below.

![image](http://docs.oracle.com/javase/tutorial/figures/java/objects-
tenElementArray.gif)

Arrays look a lot like the variables you're used to, with some extra syntax.
For example, this is how you declare an array that holds 10 floats:

    
    
    float[] xArray = new float[10];
    

In addition to giving the xArray variable a type of float, we use the square
brackets to indicate that it's an array of floats, which is a single variable
that holds multiple floats. We then use the new keyword to tell Processing to
reserve enough memory for those floats. Finally we specify how many floats we
want to hold in the array, in this case 10.

The above is a lot like telling Processing to construct a drawer designed to
hold floats, with 10 individual float-shaped boxes.

Once you have your array initialized, you can access each index of the array
using the [] brackets. This asks for the first index (array numbering starts
at 0), then puts the value of 100 at that index.

float[] xArray = new float[10]; xArray[0] = 100;

Think of this as opening the first box in a drawer and placing the value 100
in it. You can also go the other way:

int index = 0; float[] xArray = new float[10]; xArray[index] = 100; float
value = xArray[index]; println(value);

This accesses the first (remember, array indexes start at 0) element in the
array, then assigns it to our value variable. This is just like opening the
first box in the drawer and copying whatever is inside of it to the value
variable. I've used a variable called index just to show that you can use
variables to access the indexes of an array.

With these ideas in mind, we can now modify our line program to use two arrays
(one array for the x values and another for the speed values) instead of 6
separate variables:

float[] xArray = new float[3]; float[] speedArray = new float[3]; void
setup(){ size(500, 200); xArray[0] = 100; xArray[1] = 200; xArray[2] = 300;
speedArray[0] = 2; speedArray[1] = 3; speedArray[2] = 5; } void draw(){
background(0); stroke(255); xArray[0] += speedArray[0]; if(xArray[0] > width){
xArray[0] = 0; } xArray[1] += speedArray[1]; if(xArray[1] > width){ xArray[1]
= 0; } xArray[2] += speedArray[2]; if(xArray[2] > width){ xArray[2] = 0; }
line(xArray[0], 0, xArray[0], height); line(xArray[1], 0, xArray[1], height);
line(xArray[2], 0, xArray[2], height); }

The above program uses two arrays: one array that holds the x values of each
line, and one array that holds the speed of each line.

### Arrays and For Loops

The above program might not seem like a big improvement over our original
example, and the way we coded it, it's really not. However, the real beauty of
arrays comes when you couple them with for loops. Remember that for loops
allow you to iterate over a certain range of numbers. Notice in the above
program we repeatedly access indexes 0, 1, and 2 from our arrays. How about we
use for loops instead?

    
    
    float[] xArray = new float[3];
    float[] speedArray = new float[3];
    
    void setup(){
     size(500, 200); 
     
     for(int i = 0; i < 3; i++){
       xArray[i] = 0;
       speedArray[i] = i+1;
     }
    }
    
    void draw(){
      
      background(0);
      stroke(255);
      
      for(int i = 0; i < 3; i++){
        xArray[i] += speedArray[i];
        if(xArray[i] > width){
          xArray[i] = 0;
        }
        
        line(xArray[i], 0, xArray[i], height);
      }
    }
    

This code now uses for loops to iterate over each index of our arrays.

### Array Length

You might notice that we keep iterating over the indexes of each array by
using a hardcoded value of 3, which we know ahead of time is the length of the
array. What if we don't know how long the array is, or if we want to change
how many lines we have?

Instead of hardcoding the value, we can use the length variable of any array
we create. Every array has a length variable associated with it, which you can
access by using the name of the array, followed by a dot, followed by the
length variable. It looks like this:

    
    
    int[] array = new int[10];
    for(int i = 0; i < array.length; i++){
       println(i);
    }
    

We can now update our program to use the length variable of our array:

    
    
    float[] xArray = new float[3];
    float[] speedArray = new float[3];
    
    void setup(){
     size(500, 200); 
     
     for(int i = 0; i < xArray.length; i++){
       xArray[i] = 0;
       speedArray[i] = i+1;
     }
    }
    
    void draw(){
      
      background(0);
      stroke(255);
      
      for(int i = 0; i < xArray.length; i++){
        xArray[i] += speedArray[i];
        if(xArray[i] > width){
          xArray[i] = 0;
        }
        
        line(xArray[i], 0, xArray[i], height);
      }
    }
    

Now if we want to change the program to use 10 lines, or 100 lines, or even
1000 lines, all we have to do is change a single number!

Note: using two (or more) arrays to hold related information like this is
called parallel arrays and it's actually a pretty bad habit to get into. You
should use OOP to encapsulate related data into a single structure instead!
But for now, parallel arrays are fine.

Another note: Nothing is stopping us from adding 100,000 lines, but eventually
you'll tell your computer to do more work than it can handle. At that point
you'll start seeing your program slow down (since it can't keep up with doing
everything 60 times a second) or even crash (if you run out of places in
memory to store your variables). You don't have to worry too much about it for
these examples, but just keep in mind that there is an upper limit on how much
your computer can handle.

### Multi-Dimensional Arrays

You can put anything into an array... including other arrays! Arrays with more
arrays inside them are called multi-dimensional arrays, and they come in handy
for things like grids or more complicated things involving multiple
dimensions. For example, we can use a 2-dimensional array (an array that holds
arrays that hold values) to create a grid that we can use in games like
checkers, battleship, or even Mario.

The syntax for creating multi-dimensional arrays looks a lot like a normal
array, except you add another set of square brackets [] for each dimension.
For example, this creates a 2-dimensional array of ints:

    
    
    int[][] gridArray = new int[10][5];
    

In the above code, gridArray is an array that has 10 indexes. Each of those
indexes holds another array that has 5 indexes, and each of those indexes
contains an int. This single array holds a total of 50 ints! You can think of
a multi-dimensional array as a magical drawer, and in each box of the drawer,
you have another drawer! You can keep putting drawers inside drawers, but for
most purposes, 2 or 3 dimensional arrays are all you need.

### Multi-Dimensional Arrays and Nested For Loops

We know how to use a for loop with a single array, but how do we iterate over
an array with more than one dimension? Remember that you can nest for loops by
putting one for loop inside another. You can use the outer loop to iterate
over the first dimension of the array. Then for each index of the first array,
you use another for loop to iterate over the array in the second dimension.
For example:

int[][] gridArray = new int[10][5]; for(int i=0; i < 10; i++){ for(int j = 0;
j < 5; j++){ println(gridArray[i][j]); } }

In the case of our magical drawer that holds other drawers, it's like writing
a to-do list that says "open up each box of the outer drawer and take out the
drawer inside the box. Then open up each box of the inner drawer and show me
what's inside of it."

We can use this knowledge to create a grid of random black and white squares:

//2-dimensional array of booleans boolean[][] grid = new boolean[100][100];
void setup() { size(500, 500); //iterate over the 2D array, randomly assign
true or false for (int x = 0; x < 100; x++) { for (int y = 0; y < 100; y++) {
//a random number between 0 and 1 will be less than .5 half the time
grid[x][y] = random(1) < .5; } } } void draw() { float squareWidth =
width/100; float squareHeight = height/100; for (int x = 0; x < 100; x++) {
for (int y = 0; y < 100; y++) { //draw either black or white based on grid
value if(grid[x][y]){ fill(0); } else{ fill(255); } //draw square
ellipse(squareWidth * x, squareHeight*y, squareWidth, squareHeight); } } }

The above code uses a 2-dimensional array to hold a grid of squares. The first
dimension of the array represents the columns (along the x axis), and the
second dimension represents each row in those columns (along the y axis). This
code is pretty simple, but you could use similar techniques for a grid of
chess pieces, Mario blocks, tetris pieces, or battleship hits and misses.

### Exercises

  * Once you create an array, you can't change its size. However, you can create a new bigger array, copy over the contents of the first array into it, then point the original array's variable to the new bigger array. With that in mind, modify the lines program so that the user can add a new moving line by clicking in the window.
  * Use parralel arrays (as many as you need) to show multiple balls bouncing around the screen. The balls don't have to bounce off each other!
  * Modify the grid program so that the user can toggle a square by clicking on it.
  * Modify the grid program to implement [Conway's Game of Life](http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life).

###  Next: [Intermediate
Concepts!](http://StaticVoidGames.com/tutorials/intermediateConcepts/)

