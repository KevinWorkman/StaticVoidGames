#  [Objects](index.jsp)

## Advanced Inheritance

We've gone over the basics of OOP, objects, and inheritance. You should
understand that you can create a sublass by extending a class to add
functionality to it while retaining the baseline variables and methods of that
class.

Now we'll go a little deeper and talk about different types of classes and how
they relate to inheritance.

This tutorial is meant more to prepare you for stuff you're going to see
rather than to show you things you really need to use at this point.

### Interfaces

The first type of class we'll talk about isn't a class at all. An interface
looks sort of like a class, except it simply defines the methods that any
subclasses must override. An example interface might look like this:

    
    
    interface Drawable {
      void draw();
    }
    

You can think of an interface as a contract that lists all of the methods that
any implementations, or subclasses of the interface, must define. Our
interface lists a single method named draw(), so any implementations of
Drawable must define a draw() function.

For example, we can use the Drawable interface to store two different kinds of
shapes. Both the Rectangle and the Circle class implement Drawable, which
means they have to contain a draw() method. We can use that in our code by
storing Rectangles and Circles both in a single ArrayList, and because we know
that anything that implements Drawable is guaranteed to have a draw() method,
we can call it without knowing exactly what it does.

    
    
    
    ArrayList shapes = new ArrayList();
    
    void setup() {
      size(100, 100);
    
      shapes.add(new Rectangle(10, 10, 80, 10, color(255, 0, 0)));
      shapes.add(new Circle(40, 60, 20, color(0, 255, 0)));
     }
    
    void draw() {
      for (int i = 0; i < shapes.size(); i++) {
        shapes.get(i).draw();
      }
    }
    
    interface Drawable {
      void draw();
    }
    
    class Rectangle implements Drawable {
    
      float x;
      float y;
      float width;
      float height;
      color c;
    
      Rectangle(float x, float y, float width, float height, color c) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.c = c;
      } 
    
      void draw() {
        fill(c);
        rect(x, y, width, height);
      }
    }
    
    class Circle implements Drawable{
      
      float x;
      float y;
      float radius;
      color c;
      
      Circle(float x, float y, float radius, color c){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.c = c;
      }
      
      void draw(){
        fill(c);
        ellipse(x, y, radius, radius);
      }
    }
    
    

An interface is not a class, so you can't directly instantiate it. For
example, this code would not compile:

    
    
    
    void setup(){
       //Drawable is an interface and can't be instantiated, so this is illegal
       //You have to implement it first, then instantiate the implementation!
       Drawable d = new Drawable(); 
    }
    
    interface Drawable {
      void draw();
    }
    

Also note that unlike extending a class, you can implement any number of
interfaces, as long as you satisfy each interface's contract. This code
contains two interfaces, one for objects that contain a draw() function, and
another for objects that contain a step() function. Note that objects can have
both functions!

    
    
    
    ArrayList shapes = new ArrayList();
    ArrayList movingShapes = new ArrayList();
    
    void setup() {
      size(100, 100);
    
      Rectangle r = new Rectangle(10, 10, 80, 10, color(255, 0, 0));
      Circle c = new Circle(40, 0, 20, color(0, 255, 0));
    
      shapes.add(r);
      shapes.add(c);
      
      movingShapes.add(c);
     }
    
    void draw() {
      
      background(0);
      
      for (int i = 0; i < movingShapes.size(); i++) {
        movingShapes.get(i).step();
      }
      
      for (int i = 0; i < shapes.size(); i++) {
        shapes.get(i).draw();
      }
    }
    
    interface Drawable {
      void draw();
    }
    
    interface Mover{
      void step();
    }
    
    class Rectangle implements Drawable {
    
      float x;
      float y;
      float width;
      float height;
      color c;
    
      Rectangle(float x, float y, float width, float height, color c) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.c = c;
      } 
    
      void draw() {
        fill(c);
        rect(x, y, width, height);
      }
    }
    
    class Circle implements Drawable, Mover{
      
      float x;
      float y;
      float radius;
      color c;
      
      Circle(float x, float y, float radius, color c){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.c = c;
      }
      
      void step(){
        y++;
      }
      
      void draw(){
        fill(c);
        ellipse(x, y, radius, radius);
      }
    }
    
    

In this example code, Rectangle has a draw() function but not a step()
function. The Circle class has both, so it can implement both interfaces. This
becomes useful if you have a bunch of different kinds of objects. Imagine a
game with different kinds of enemies: some that moved, some that chased you,
some that stayed stationary. Instead of keeping track of each enemy, you could
use interfaces to organize them into a hierarchy.

### Abstract Classes

So far we've covered interfaces, which list the methods that a class must
have, and regular classes, which define those methods. But there's something
in-between an interface and a class: an abstract class.

An abstract class can define variables and methods exactly like a normal
class, but it can also define abstract methods much like an interface. This
can be used when you know partly what a set of classes should do, but want to
leave some of the functionality completely up to a subclass. For example, both
our Circle and Rectangle classes have an x and a y value, but they have
completely different draw() functions. We could use an abstract class to
organize that, like so:

<

    
    
    abstract class Shape{
      float x;
      float y;
      
      Shape(float x, float y){
       this.x = x;
       this.y = y; 
      }
      
      abstract void draw();
    }
    

An abstract class looks a lot like a normal class, except for the abstract
keyword. Any methods that you want to force subclasses to override are also
declared abstract and end in a semicolon, just like in an interface.

We can now extend the Shape class and store all subclasses in a single
ArrayList:

    
    
    ArrayList shapes = new ArrayList();
    
    void setup() {
      size(100, 100);
    
      Rectangle r = new Rectangle(10, 10, 80, 10, color(255, 0, 0));
      Circle c = new Circle(40, 50, 20, color(0, 255, 0));
    
      shapes.add(r);
      shapes.add(c);
     }
    
    void draw() {
      
      background(0);
     
      for (int i = 0; i < shapes.size(); i++) {
        shapes.get(i).draw();
      }
    }
    
    abstract class Shape{
      float x;
      float y;
      
      Shape(float x, float y){
       this.x = x;
       this.y = y; 
      }
      
      abstract void draw();
    }
    
    class Rectangle extends Shape {
    
      float width;
      float height;
      color c;
    
      Rectangle(float x, float y, float width, float height, color c) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.c = c;
      } 
    
      void draw() {
        fill(c);
        rect(x, y, width, height);
      }
    }
    
    class Circle extends Shape{
      
      float radius;
      color c;
      
      Circle(float x, float y, float radius, color c){
        super(x, y);
        this.radius = radius;
        this.c = c;
      }
      
      void draw(){
        fill(c);
        ellipse(x, y, radius, radius);
      }
    }
    

We extend the Shape class and use the super keyword to pass the x and y values
up to the Shape constructor. And since we know any subclasses of Shape are
guaranteed to have a draw() method, we can safely call it on every instance in
our ArrayList.

Note: like interfaces, you can't directly instantiate an abstract class. You
must extend it first!

### Anonymous Classes

Now we know the different types of classes and how to create subclasses of
them. Everything we've talked about so far involves named classes, which just
means you've had to give every class you've used so far a name. This works for
most cases, and should probably be what you use until you're more comfortable
with OOP.

However, there is a lazy way to create a subclass that doesn't involve
creating a named class. This doesn't give you any functionality that a named
class doesn't, but it comes in handy when you only have to use a class once
and don't need to create multiple instances of it.

For example, we can create a one-time extension of class that draws a
triangle:

    
    
    Shape triangle = 
      new Shape(50, 75){
        void draw(){
          fill(0, 255, 255);
          triangle(x, y, x-10, y+10, x+10, y+10);
        }
      };
      
      void setup() {
      size(100, 100);
    
      Rectangle r = new Rectangle(10, 10, 80, 10, color(255, 0, 0));
      Circle c = new Circle(40, 50, 20, color(0, 255, 0));
    
      shapes.add(r);
      shapes.add(c);
      shapes.add(triangle);
     }
     
     //rest of code stays the same
    

Notice that this class doesn't have a name. The variable has a name, but the
actual class is just an anonymous extension of Shape. Unlike Circle and
Rectangle, we can't reuse this class to create multiple instances, but
oftentimes that's okay.

### Final Classes

Now we know how to create extensions and implementations of various types of
classes. There's one more type of class you might encounter that makes all of
it a moot point: a final class.

A final class is simply a class that cannot be extended. If you try to extend
it, you'll get a compile-time error.

    
    
    final class Shape{
      float x;
      float y;
      
      Shape(float x, float y){
       this.x = x;
       this.y = y; 
      }
      
      void draw(){
       point(x, y); 
      }
    }
    
    //compiler error, can't extend Shape!
    class Rectangle extends Shape {
    
      float width;
      float height;
      color c;
    
      Rectangle(float x, float y, float width, float height, color c) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.c = c;
      } 
    
      void draw() {
        fill(c);
        rect(x, y, width, height);
      }
    }
    

This is useful when you want to restrict the use of a class, either for
optimization or security. For example, the String class is final because Java
(and by extension Processing) does a bunch of optimization with it. You can't
create a subclass of String.

You can also declare individual methods final, which means that a subclass
cannot override it. This is slightly different from declaring a final
variable, which means that its value cannot change.

### Combinations

As you can see, there are a bunch of different ways to create class
hierarchies, and there is no one single correct way to do things. Two
different programmers will probably use these concepts in two different ways,
and most real-life programs will use a combination of all of them to achieve
their desired organization.

This tutorial is meant to prepare you for concepts that you'll see as you
continue programming, so if this doesn't all make sense to you, that's okay.
Just keep this page in mind when you see some weird syntax and want to figure
out what's going on.

### Exercises

  * Create a program that includes the following: 
    * An abstract class Animal with a constructor that takes the Animal's name as an argument and an abstract method move().
    * An interface Bird with a single method fly().
    * An interface Fish with a single method swim().
    * An interface Mammal with a single method walk().
    * Implementations of each of those interfaces that also extend Animal. For example: 
    
    
    class Cat extends Animal implements Mammal{
       Cat(String name){
          super(name);
       }
       
       void move(){
          walk();
       }
       
       
       void walk(){
          println(name + " goes pitter patter.");
       }
    }
    

    * Extensions of each of those classes that modify the baseline behavior. For example: 
    
    					
    class Kitten extends Cat{
       Kitten(String name){
          super(name);
       }
       
       void walk(){
          println(name + " clumsily pounces.");
       }
    }
    

###  Next: [Java](http://staticvoidgames.com/tutorials/basicJava/index.jsp)
