#  [Basic Concepts](index.jsp)

## Variable Casting

You've learned that variables must have a specific type, and there are several
different types: int, long, float, double, etc. Casting lets you convert
between some of those types.

Casting takes a few forms, some of which are done automatically and invisibly,
so it can be confusing.

Remember that ints can only hold whole numbers, no decimals. Floats can hold
decimals.

### Operator Result Types

Run the following code:

    
    
    void setup(){
    	int x = 100;
    	int y = 25;
    	println(x*y);
    	
    	float z = x*y;
    	println(z);
    	
    	exit();
    }
    

You might notice that the code prints out two numbers: first it prints out
2500 without a decimal point, then it prints out 2500.0, with the decimal
point.

This is because the result of multiplication (or any other operator) of two
int values is always an int. So when you print the result directly (before
storing it in a variable), you don't get the decimal part.

However, if you change the code so that the y variable is a float, you'll see
that the code prints out 2500.0 with the decimal point twice.

    
    
    void setup(){
    	int x = 100;
    	float y = 25.0;
    	println(x*y);
    	
    	float z = x*y;
    	println(z);
    	
    	exit();
    }
    

This is because the result of multiplication (or any other operator) of an int
and a float is always a float. So when you print the result directly, you're
printing a float, so you get the decimal part.

### Integer Truncation

Remember that ints can only hold whole numbers, no decimal places. So what
happens when you perform an operation that results in a decimal?

    
    
    void draw(){
    	int x = 10;
    	int y = 3;
    	int z = x / y;
    	println(z);
    }
    

What do you expect this code to print out? What does it print out instead?

If you run the code, you'll discover that it prints out 3, not 3.33333 like
you might expect. This is because x and y are both ints, so the decimal part
of the number is simply dropped. This is called truncation.

### Widening

Consider the following code:

    
    
    void setup(){
    	int x = 100;
    	int y = 25;
    	float z = x * y;
    	println(z);
    	
    	exit();
    }
    

Notice the part where x and y (which are both int) are multiplied, and the
result is stored in the z variable (which is a float). This won't give us any
problems, since anything that an int can hold, a float can also hold. This is
called widening.

You can still lose information due to integer truncation, but that is
considered "your fault" for using ints. In other words, the computer trusts
you to know what you're doing.

This is called an implicit conversion, since the conversion is implied by
storing it in a wider type.

### Narrowing

Consider the following code, which is basically the opposite of the above
code:

    
    
    void setup(){
      float x = 10;
      float y = 3;
      int z = x / y;
      println(z);
      
      exit();
    }
    

What do you expect it to print out?

When you try to run the code, you'll see that you actually get a compiler
error saying that Processing cannot convert from a float to an int. What's
going on?

Remember that the results of any operator on two floats is also a float. So
the result of x / y is a float. Also remember that ints can't hold decimals,
and floats can. So when you try to store the result of x / y (which is
3.33333), you're losing the decimal part. The computer recognizes that, and
stops you to make sure you're okay with losing that information.

Note: this does not necessarily have to be a result of arithmetic. For
example, both of the following lines of code generate the same compiler error
as above:

    
    
    
      float x = 10;
    
      //x stores a float.. error!
      int a = x;
      
      //5.0 is a float literal.. error!
      int b = 5.0;
    

To tell the computer that you're really okay potentially losing information
due to narrowing, you have to use a cast. To cast one type to another type,
you put the post-conversion type inside parentheses () in front of the value
to be converted:

    
    
    void setup(){
      float x = 10;
      float y = 3;
      int z = (int) (x / y);
      
      println(z);
      
      int a = (int) x;
      int b = (int) 3.25;
      int c = a + b;
      
      println(c);
      
      exit();
    }
    

In the above code, we see several example of casting from a float value to an
int. The compiler no longer generates an error, because you've told it that
you're really okay with the narrowing conversion. This is called an explicit
conversion because you've explicitly told the computer to convert, or cast,
from one type to another.

    
    
    void draw(){
    	int x = 10;
    	int y = 3;
    	float z = x / y;
    	println(z);
    }
    

However, if you run that code you'll discover that it still only prints out
3.0. Why is that?

The answer is that the truncation happens BEFORE the assignment, meaning that
the steps the code takes are as follows:

  * Assign the value 10 to the variable x.
  * Assign the value 3 to the variable y.
  * Divide x by y, and since both x and y are ints, the result is an int as well. Since the result is an int, it is truncated.
  * Take the already-truncated result and assign it to the variable z.
  * Print the value of z.

One way around that is to make either the x or y variable (or both) a float.
Since the result of an int mixed with a float is a float, truncation will not
occur.

    
    
    void draw(){
    	float x = 10;
    	int y = 3;
    	float z = x / y;
    	println(z);
    }
    

Now the code takes these steps

  * Assign the value 10 to the variable x.
  * Assign the value 3 to the variable y.
  * Divide x by y, and since x is a float, the result is a float as well. Since the result is a float, it is NOT truncated.
  * Take the non-truncated result and assign it to the variable z.
  * Print the value of z.

### Exercises

  * How is truncation different from rounding?

###  Next: [Booleans](Booleans.jsp)


