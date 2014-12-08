#  [Basic Concepts](index.jsp)

## Booleans

A logician has a baby. The doctor immediately hands the newborn to her.

Her husband asks impatiently: "Is it a boy or a girl??"

The logician replies: "yes."

We now know how to store values in variables. Next we'll take a look at comparing and interacting with those values.

We've looked at types that hold numbers (int holds whole numbers, float holds decimals). Another type is the boolean type, which can only hold one of two values: true or false. You declare it exactly like you declare any other variable, with the type, the name, an equals sign, and a value:

    void draw() {
    	boolean x = true;
    	println(x);
    }
    
Side Note: Hard-coded values (numbers such as 7, .25, or 1000 or a boolean true or false) are called **literals**.

In addition to hardcoding the literal boolean values true and false, you can use equality and inequality on other values to create a boolean.

    void setup() {
    	int grade = 75;
    	boolean isGradeA = grade >= 90;
    	println("You got an A: " + isGradeA);
    }
    

The above code does the following:

  * Set the grade variable to hold the value 75.
  * Use the >= (greater than or equal to) operator on grade and the value 90. If grade is above 90, it evaluates to the boolean value true. Otherwise, it evaluates to false.
  * Whatever the >= operator evaluated to (in this case it will be false) is stored in the boolean variable isGradeA.
  * The value in isGradeA is appended to the String literal and passed into the println() function, which prints out the value.

Try changing the grade to see what happens! Can you modify the code to check for an F instead?

    void setup() {
    	int lives = 9;
    	boolean isCat = lives == 9;
    	println("Are you a cat? " + isCat);
    }
    
The == operator compares whether two values are the same. Don't confuse it with the single = sign, which is the assignment operator!

Can you modify the code to check whether you have a dozen of something?

### Inequalities

In addition to the == equality operator, there are other operators that check for inequality:

  * != evaluates to true if its operands are NOT equal.
  * < evaluates to true if its left operand is LESS THAN its right operand.
  * <= evaluates to true if its left operand is LESS THAN OR EQUAL TO its right operand.
  * > evaluates to true if its left operand is GREATER THAN its right operand.
  * >=; evaluates to true if its left operand is GREATER THAN OR EQUAL TO its right operand.

### Boolean Operators

Remember that you can perform any calculator operation on number types, like this:

    int x = 7;
    int y = x - 1 + 5;
    int z = x * y;
    
Booleans have their own operators. You can combine two boolean with the AND operator or the OR operator, or you can toggle a single boolean with the NOT operator.

### And

The **and operator** takes two booleans (similar to how the addition operator takes two number types) and evaluates to true only if BOTH boolean operands are also true. Think of the and operator as saying "I want this to be true only if both of these booleans are also true". The and operator is two ampersands (&&) between two booleans. It looks like this:

    boolean a = true;
    boolean b = false;
    boolean c = a && b;
    boolean d = c && true;
    println(d);
    
What does the above code print out?

We can use the && operator to modify our grading program to check for grades other than an A or F:

    void setup() {
    	int grade = 95;
    	boolean isGradeB = grade >= 80 && grade < 90;
    	println("You got a B: " + isGradeB);
    }

The above code does the following:

  * Set the grade variable to hold the value 95.
  * Use the >= operator on grade and the value 80. Since grade is 95, this evaluates to true.
  * Use the < operator on grade and the value 90. Since grade is 95, this evaluates to false.
  * Whatever the && operator on the results of the previous two evaluations. If both are true, the && operator evaluates to true. Otherwise it evaluates to false. In this case, since one of them is true and the other is false, it evaluates to false.
  * The value in isGradeB is appended to the String literal and passed into the println() function, which prints out the value.

Can you add code that checks for every grade? Then change the value of grade to check your code.

### Or

The **or operator** is very similar to the && operator, except it evaluates to true if EITHER boolean is true. The or operator looks like two vertical bars (||). On most keyboards, you type it with "shift+\".

    void setup() {
    	boolean brokeMirror = false;
    	boolean walkedUnderLadder = true;
    	
    	boolean unlucky = brokeMirror || walkedUnderLadder;
    	println("You are unlucky: " + unlucky);
    }
    
### Not

The not operator works on a single boolean value and evaluates to the opposite of the value: not true evaluates to false; not false evaluates to true. The not operator is a single ! exclamation point before the boolean value:

    void setup() {
    	
    	int age = 17;
    	boolean adult = age >= 18;
    	boolean minor = !adult;
    	
    	println("You are a minor: " + minor);
    }
    
Note that the not operator does not do any assignment by itself. So if you want to toggle a variable, you have to do the assignment after using the not operator:

    void setup() {
    	
    	boolean changeMyMind = true;
    	
    	println("Your mind is made up: " + changeMyMind);
    	
    	changeMyMind = !changeMyMind;
    	
    	println("Your mind is now made up: " + changeMyMind);
    }
    

### Order of Operations

Remember that arithmetic operations (+, -, *, /), have an order. Multiplication and division happen before addition and subtraction, so this code:

    void setup() {
    	int x = 1 + 2 * 3 - 4;
    	println("X: " + x);
    }
    
...prints out 3 instead of 5. Boolean operations fit into the order of operations as follows:

  * Multiplicative (multiplication and division) operators are evaluated first.
  * Additive (addition and subtraction) operators are evaluated next.
  * Relational (< > <= >=) operators 
  * Equality (== !=)
  * And (&&)
  * Or (||)
  * Assignment (=)

There are actually other operators that fit into this order, but they are outside the scope of this tutorial. If you're curious, they are listed [here](http://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html). But for now keep in mind that boolean operators fit into the order of operations just like any other arithmetic operator.

Entire classes (actually, entire disciplines) are devoted to boolean logic. For now just keep in mind that you can combine booleans in multiple ways to create new booleans. If you're curious, this wikipedia article on [truth tables](http://en.wikipedia.org/wiki/Truth_table) is a good place to start.

### Exercises

What does the following code print out?

    void setup(){
     
      boolean x = true;
      boolean y = true;
      boolean z = false;
      
      boolean answer = !(x && y) || z && y;
      println(answer);
     
      exit();
    }
    
What about this code?
    
    void setup(){
     
      boolean x = false;
     
      boolean answer = (x = true);
      println(answer);
     
      exit();
    }
    
What do you expect it to print out? What does it print out instead? Hint: == != =

###  Next: [Strings](Strings.jsp)
