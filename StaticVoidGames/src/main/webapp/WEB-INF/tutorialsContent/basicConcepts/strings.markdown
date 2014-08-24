#  [Basic Concepts](index.jsp)

## Strings

We've covered a few different types, such as int and float, that you must use
when declaring function return types, function parameters, and variables. So
far they all have similar syntax (think of syntax as the rules that specify
how a language works, including programming languages) and how they are used
all looks pretty similar:

    
    
    int x = 7;
    float y = 4.5;
    double x = y * 2;
    

But there are other types that do not hold numbers and don't have the same
kind of syntax.

### Strings

Strings hold text. To use a String directly, you simply wrap whatever text you
want inside two quotation marks. This is called a String literal, and it works
a lot like using numbers directly:

    
    
    void draw() {
    
    	background(0);
    
    	String message = "Hello World!";
    	text(message, 10, 50);
    }
    

Note that the text() function above draws a String at the specified X and Y
coordinates.

### The String Append Operator

Remember that number types support every calculator operation (+, -, *, /),
and they look like this:

    
    
    int x = 8 + 4 * 6;
    int y = x / 2;
    

Strings only support the append operator, which uses the + symbol to combine a
String with another value. For example, you can combine multiple Strings:

    
    
    void draw() {
    
    	background(0);
    
    	String firstAnimal = "cats";
    	String secondAnimal = "dogs";
    	
    	String animals = firstAnimal + " and " + secondAnimal;
    	
    	text(animals, 10, 50);
    }
    

Note that the only whitespace that matters is inside quotation marks.

You can also combine a String with other types. This is particularly useful
for labeling information:

    
    
    
    int count = 0;
    
    void draw() {
    
    	background(0);
    	
    	count = count + 1;
    
    	String message = "Count: " + count;
    	text(message, 10, 50);
    }
    

Or for finding out the value of a variable:

    
    
    
    
    void setup() {
    
    	int x = 10;
    	int y = 50;
    	int z = x / y;
    
    	String message = "The value of z is " + z;
    	
    	println(message);
    }
    

What is the value of z in the above code? Run the program and find out! Using
the println() function to test assumptions like this is a trick many
programmers use all the time!

Strings are a bit more complicated than that because they are Objects, but
we'll get to that later.

### Exercises

  * Consider the following code: 
    
    
    
    
    void setup() {
    
    	int x = 10;
    	int y = 50;
    	int z = x / y;
    
    	String message = "Output: " + x + y + z;
    	
    	println(message);
    }
    

What do you expect it to output? What does it actually output? Why is that?

###  Next: [Colors](Colors.jsp)
