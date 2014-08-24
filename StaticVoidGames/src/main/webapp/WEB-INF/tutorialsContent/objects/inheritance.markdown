#  [Objects](index.jsp)

## Inheritance

By now you know how to create and use your own objects. Another important idea
in OOP is inheritance, which allows a programmer to extend a class and
organize code in a hierarchy.

This is probably easier to demonstrate with an example. Let's start with a
basic object:

    
    
    class Rectangle {
    
      float x;
      float y;
      float width;
      float height;
    
      Rectangle(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
      } 
    
      void draw() {
        fill(255);
        rect(x, y, width, height);
      }
    }
    

The Rectangle class defines an object with an x and y position, a width, and a
height. It has a draw() function that draws the Rectangle. We can use the
class in a sketch like so:

    
    
    Rectangle rectangle = new Rectangle(10, 30, 20, 10);
    
    void setup() {
      size(100, 100);
    }
    
    void draw() {
      rectangle.draw();
    }
    

This code creates an instance of Rectangle, and then tells that instance to
draw itself in the sketch's draw() function.

Keep in mind that a square is a special type of rectangle that has the same
width and height. We can use inheritance to expand our code to reflect this:

    
    
    class Square extends Rectangle{
      Square(float x, float y, float length){
       super(x, y, length, length); 
      }
    }
    

The Square class extends our Rectangle class, which means that Square *is a*
Rectangle. The Square constructor only takes three arguments: x, y, and
length. Rectangle is Square's super class, so we use the super keyword to call
the Rectangle constructor with the supplied values, passing in length for both
width and height.

We can use the Square class exactly how we used the Rectangle class:

    
    
    Rectangle rectangle = new Rectangle(10, 30, 20, 10);
    Square square = new Square(10, 50, 25);
    
    void setup() {
      size(100, 100);
    }
    
    void draw() {
      rectangle.draw();
      square.draw();
    }
    

You might notice that we're calling the draw() function of our Square
instance. Where is that coming from, since there is no draw() function in the
Square class? Remember that Square is a Rectangle, which means that a Square
includes everything a Rectangle contains, including methods and variables. In
fact, we can access the variables in the Rectangle class from the Square
class:

    
    
    class Square extends Rectangle {
      Square(float x, float y, float length) {
        super(x, y, length, length);
      }
    
      void printMe() {
        println("X: " + x);
        println("Y: " + y);
        println("Width: " + width);
        println("Height: " + height);
      }
    }
    

If you call square.printMe() in the setup() function of the sketch, the values
are printed out even though the x, y, width, and height variables aren't
defined directly in the Square class. This works because Square *is a*
Rectangle, so Square has access to everything in the Rectangle class.

We can further expand our program by making two special kinds of Square:
RedSquare and BlueSquare.

    
    
    Rectangle rectangle = new Rectangle(10, 10, 80, 10);
    Square square = new Square(40, 30, 20);
    RedSquare redSquare = new RedSquare(10, 75, 20);
    BlueSquare blueSquare = new BlueSquare(70, 75, 20);
    
    void setup() {
      size(100, 100);
    }
    
    void draw() {
      rectangle.draw();
      square.draw();
      redSquare.draw();
      blueSquare.draw();
    }
    
    class Rectangle {
    
      float x;
      float y;
      float width;
      float height;
    
      Rectangle(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
      } 
    
      void draw() {
        fill(255);
        rect(x, y, width, height);
      }
    }
    
    class Square extends Rectangle {
      Square(float x, float y, float length) {
        super(x, y, length, length);
      }
    }
    
    class RedSquare extends Square{
      
      RedSquare(float x, float y, float length) {
        super(x, y, length);
      }
      
     void draw(){
      fill(255, 0, 0);
      rect(x, y, width, height); 
     }
    }
    
    class BlueSquare extends Square{
      
      BlueSquare(float x, float y, float length) {
        super(x, y, length);
      }
      
     void draw(){
      fill(0, 0, 255);
      rect(x, y, width, height); 
     }
    }
    

RedSquare and BlueSquare both override the draw() function of Square to change
its behavior.

### Generalizing

Remember that a BlueSquare is a Square, which in turn is a Rectangle. That
means that we can do this:

    
    
    Rectangle rectangle = new Rectangle(10, 10, 80, 10);
    Rectangle square = new Square(40, 30, 20);
    Rectangle redSquare = new RedSquare(10, 75, 20);
    Rectangle blueSquare = new BlueSquare(70, 75, 20);
    

Since we have an *is a* relationship, we can store our instances in more
general variables. This comes in handy when dealing with groups of objects,
like so:

    
    
    ArrayList rectangles = new ArrayList();
    
    void setup() {
      size(100, 100);
    
      rectangles.add(new Rectangle(10, 10, 80, 10));
      rectangles.add(new Square(40, 30, 20));
      rectangles.add(new RedSquare(10, 75, 20));
      rectangles.add(new BlueSquare(70, 75, 20));
    }
    
    void draw() {
      for (int i = 0; i < rectangles.size(); i++) {
        rectangles.get(i).draw();
      }
    }
    

Now we just have an ArrayList of Rectangles. We don't have to know what type
of Rectangle each instance is- we know the Rectangle class has a draw()
function, and that's all we care about.

Notice that when we call the draw() function, the draw() function of the
correct subclass is called. Java and Processing use the most specific function
they can find, which is why the RedSquare and BlueSquare draw() functions are
called for those instances, even though the variables only know they hold
Rectangle values.

### Composition over Inheritance

The above example is admittedly contrived, and it's actually a pretty bad
design. If this were real life, the BlueSquare and RedSquare classes could
probably be rolled into one class called ColoredRectangle, which would contain
a color value.

    
    
    
    ArrayList rectangles = new ArrayList();
    
    void setup() {
      size(100, 100);
    
      rectangles.add(new Rectangle(10, 10, 80, 10));
      rectangles.add(new Square(40, 30, 20));
      rectangles.add(new ColoredSquare(10, 75, 20, color(255, 0, 0)));
      rectangles.add(new ColoredSquare(70, 75, 20, color(0, 0, 255)));
    }
    
    void draw() {
      for (int i = 0; i < rectangles.size(); i++) {
        rectangles.get(i).draw();
      }
    }
    
    class Rectangle {
    
      float x;
      float y;
      float width;
      float height;
    
      Rectangle(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
      } 
    
      void draw() {
        fill(255);
        rect(x, y, width, height);
      }
    }
    
    class Square extends Rectangle {
      Square(float x, float y, float length) {
        super(x, y, length, length);
      }
    }
    
    class ColoredSquare extends Square {
    
      color c;
    
      ColoredSquare(float x, float y, float length, color c) {
        super(x, y, length);
        this.c = c;
      }
    
      void draw() {
        fill(c);
        rect(x, y, width, height);
      }
    }
    

In fact, it would probably be best to get rid of the ColoredSquare class
altogether and instead modify the Rectangle class to use a color value. This
isn't always feasible, but the general rule is that you should use inheritance
when you need it, and not when you don't. If you can accomplish your goal
using composition instead of inheritance, you probably should.

### Exercises

  * Create another class called MovingSquare that bounces around the screen. This class should extend Square and be stored in the ArrayList of Rectangles.

###  Next: [Advanced Inheritance](AdvancedInheritance.jsp)
