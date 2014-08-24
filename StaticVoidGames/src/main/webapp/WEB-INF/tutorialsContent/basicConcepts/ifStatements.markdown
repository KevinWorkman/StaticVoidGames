#  [Basic Concepts](index.jsp)

## If Statements

A programmer's wife tells him: "Go to the store and get a loaf of bread. If
they have eggs, get a dozen."

The programmer comes home with 12 loaves of bread.

If statements are one of the basic building blocks of a program. They allow a
program to execute a block of code only if some condition is met. This is
extremely useful, as you'll quickly learn. If statements work with boolean
values, so let's recap.

### Booleans

Remember that a boolean is a value that holds either true or false. You can
just write true or false directly (this is called a boolean literal) or get a
boolean from the result of an inequality. You can also combine two boolean
using operators such as && or ||.

    
    
    void setup() {
        boolean adult = false;
        println("Are you an adult? " + adult);
        
    	int grade = 95;
    	boolean isGradeA = grade >= 90;
    	
    	println("Is your grade an A? " + isGradeA);
    	
    	boolean both = adult && isGradeA;
    	println("Are you an adult with an A? " + both);
    }
    

What do you expect this code to print out? Run it to check yourself. If it's
confusing, go back and take a closer look at the [boolean
tutorial](Booleans.jsp).

### If Statements

If statements look a bit like calling a function named "if" that takes a
single boolean argument, but if statements are NOT functions. To use an if
statement, you use the keyword if, followed by a boolean value inside
parenthesis, followed by the code you want to execute if the boolean is true
inside a block of curly braces.

    
    
    void draw() {
    	background(0);
        boolean isGradeA = true;
        if(isGradeA){
           text("You got an A!", 5, 40);
           text("Congratulations!", 5, 50);
        }
    }
    

In the above code, the text will only show when isGradeA is true. Note that
you don't have to use boolean values directly- you can put anything that
evaluates to a boolean inside an if statement.

    
    
    void draw() {
    	background(0);
        int grade = 95;
        if(grade >= 90){
           text("You got an A!", 5, 40);
           text("Congratulations!", 5, 50);
        }
    }
    

### Boolean Operators

If statements work on booleans, and remember that booleans have operators AND
and OR that can combine them. This can come in handy with if statements, since
it allows you to group logical inequalities together. For example:

    
    
    void draw() {
    	background(0);
        int grade = 75;
        
        if(grade >= 90){
           text("You got an A!", 5, 40);
           text("Congratulations!", 5, 50);
        }
        if(grade >= 80 && grade < 90){
           text("You got a B!", 5, 40);
           text("Good job!", 5, 50);
        }
        if(grade >= 70 && grade < 80){
           text("You got a C.", 5, 40);
           text("Study harder.", 5, 50);
        }
        if(grade >= 60 && grade < 70){
           text("You got a D.", 5, 40);
           text("Very bad.", 5, 50);
        }
        if(grade < 60){
           text("You got an F.", 5, 40);
           text("You failed.", 5, 50);
        }
    }
    

### Else

Many times, you'll want a certain piece of code to run if a condition is true,
and another piece of code to run if it is not. For that, you can use an else
statement. To use an else statement, you use the keyword else right after an
if statement, and then the block of code you want to execute if the if
evaluates to false, inside curly brackets.

    
    
    void draw() {
    	background(0);
        int age = 17;
        if(age >= 18){
           text("You're an adult!", 5, 40);
           text("Pay the bills!", 5, 50);
        }
        else{
        	text("You're a kid!", 5, 40);
    		text("Go to school!", 5, 50);
        }
    }
    

What does the above code print out? Can you modify it to draw a red background
for adults and a blue background for kids?

### Else-If

Take a closer look at the code we just wrote.

    
    
    void draw() {
    	background(0);
        int grade = 75;
        
        if(grade >= 90){
           text("You got an A!", 5, 40);
           text("Congratulations!", 5, 50);
        }
        if(grade >= 80 && grade < 90){
           text("You got a B!", 5, 40);
           text("Good job!", 5, 50);
        }
        if(grade >= 70 && grade < 80){
           text("You got a C.", 5, 40);
           text("Study harder.", 5, 50);
        }
        if(grade >= 60 && grade < 70){
           text("You got a D.", 5, 40);
           text("Very bad.", 5, 50);
        }
        if(grade < 60){
           text("You got an F.", 5, 40);
           text("You failed.", 5, 50);
        }
    }
    

This code works, but there is definitely room for improvement. Think about it
this way: what happens if grade is 85? Once gets past the first if statement,
we know that it is less than 90. So why do we need to check that again in our
second if statement? And after we exit the second if statement, we know none
of the other ones will evaluate to true and be executed. So why waste time
doing all of those comparisons?

In this case, we can use the else-if statement. An else-if looks like a mix of
an else statement and an if statement: it immediately follows an if statement,
uses the else keyword, but then follows the else up with another if statement.
For example:

    
    
    void draw() {
    	background(0);
        int grade = 78;
        
        if(grade >= 90){
           text("You got an A!", 5, 40);
           text("Congratulations!", 5, 50);
        }
        else if(grade >= 80){
           text("You got a B!", 5, 40);
           text("Good job!", 5, 50);
        }
        else if(grade >= 70){
           text("You got a C.", 5, 40);
           text("Study harder.", 5, 50);
        }
        else if(grade >= 60){
           text("You got a D.", 5, 40);
           text("Very bad.", 5, 50);
        }
        else{
           text("You got an F.", 5, 40);
           text("You failed.", 5, 50);
        }
        
    }
    

To make sense of the above piece of code, keep in mind that when Processing
(or Java, or any language with if statements) sees a block like this, it's
going to take the following steps:

  * Set the background to black and the int grade to 78.
  * At the if statement, evaluate whether grade is greater than or equal to 90. Since it is not, skip the code inside the if statement.
  * After skipping down to the next else-if, evaluate whether grade is greater than or equal to 80. Since it is not, skip that code as well.
  * Skip down to the next else-if and evaluate whether grade is greater than or equal to 70. Since it is, enter the code block and execute the code.
  * Since that else-if evaluated to true, we skip the rest of the else-ifs and the else block.

That last step is important, so pay attention to it: when one of the if
statements in an if/else-if/else block evaluates to true, the code stops
checking the other if statements in the block. This is what allows us to only
check whether the grade is above a value. Once we're passed the first if
statement, we know that grade is less than 90, so if it's greater than 80, you
know it's a B.

To explore that idea a bit more, consider the following code:

    
    
    void draw() {
    	background(0);
        int grade = 99;
        
        if(grade >= 90){
           text("You got an A!", 5, 40);
           text("Congratulations!", 5, 50);
        }
        
        if(grade >= 80){
           text("You got a B!", 5, 40);
           text("Good job!", 5, 50);
        }
        
        if(grade >= 70){
           text("You got a C.", 5, 40);
           text("Study harder.", 5, 50);
        }
        
        if(grade >= 60){
           text("You got a D.", 5, 40);
           text("Very bad.", 5, 50);
        }
        
        else{
           text("You got an F.", 5, 40);
           text("You failed.", 5, 50);
        }
        
    }
    

What do you expect this code to display? What does it actually display? If
it's hard to tell what it's displaying, consider adding print statements to
help you debug:

    
    
    void draw() {
    	background(0);
        int grade = 99;
        
        println("Grad is: " + grade);
        
        if(grade >= 90){
           text("You got an A!", 5, 40);
           text("Congratulations!", 5, 50);
           println("Grade is an A");
        }
        
        if(grade >= 80){
           text("You got a B!", 5, 40);
           text("Good job!", 5, 50);
           println("Grade is a B");
        }
        
        if(grade >= 70){
           text("You got a C.", 5, 40);
           text("Study harder.", 5, 50);
           println("Grade is a C");
        }
        
        if(grade >= 60){
           text("You got a D.", 5, 40);
           text("Very bad.", 5, 50);
           println("Grade is a D");
        }
        
        else{
           text("You got an F.", 5, 40);
           text("You failed.", 5, 50);
           println("Grade is an F");
        }
        
    }
    

If you run that code, you'll notice that the code enters the blocks for A, B,
C, and D. What gives? Duh, we forgot our else-if keyword! And since sequential
if statements are not linked like if/else-if/else blocks are, the code does
not skip over the other blocks after entering the first one.

### Nested If Statements

Another useful feature with if statements is the ability to nest them. In
other words, there's nothing stopping you from putting an if statement inside
of the block of another if statement! For example, what do you think this code
does?

    
    
    void draw() {
      println(mouseX + ", " + mouseY);
      
      if (mouseX < 50) {
        if (mouseY < 50) {
          background(255, 0, 0);
        }
        else {
          background(0, 255, 0);
        }
      }
      else {
        if (mouseY < 50) {
          background(0, 0, 255);
        }
        else {
          background(0, 0, 0);
        }
      }
    }
    

Run the code and put your mouse in each corner of the program. The background
changes depending on which corner your mouse is in. How is it achieving this
effect? Step through the code one line at a time:

  * At the first if statement, the code checks whether mouseX is less than 50. Remember that the default width and height is 100, so this is like checking whether the mouse is on the left half of the program.
  * If the mouse is on the left side of the program, it performs a similar check for the mouseY, which determines whether the mouse is on the top or bottom.
  * But if the first if statement evaluates to false and the mouse is on the right side of the screen, it enters the else statement and performs a different check involving the mouseY variable.

The outer if statement is asking "is the mouse on the left or right side of
the program?" and the inner if statements are asking "is the mouse on the top
or bottom side of the program?". Let's see the same code with some useful
comments added:

    
    
    void draw() {
      println(mouseX + ", " + mouseY);
      
      //is the mouse in the left or right half?
      if (mouseX < 50) {
        //it's on the LEFT half. Now is it on the top or bottom half?
        if (mouseY < 50) {
          //it's on the top half. That means we're in the upper-left corner.
          background(255, 0, 0);
        }
        else {
          //it's on the bottom half. That means we're in the bottom-left corner.
          background(0, 255, 0);
        }
      }
      else {
        //it's on the RIGHT half. Now is it on the top or bottom half?
        if (mouseY < 50) {
          //it's on the top half. That means we're in the upper-right corner.
          background(0, 0, 255);
        }
        else {
          //it's on the bottom half. That means we're in the bottom-right corner.
          background(0, 0, 0);
        }
      }
    }
    

Your code can get pretty confusing once you start nesting if statements, so
remember that formatting is important! Make sure your if/else-if/else
statements are lined up using indentation, that way you always know what's
going on. Note that Processing has an Auto Format option in the edit menu!

### Exercises

  * Remember that the && and || operators combine two boolean values. Why doesn't this code work? 
    
    
    void draw() {
    	if(grade >= 80 && < 90){
           text("You got a B!", 5, 40);
           text("Good job!", 5, 50);
        }
    }
    

  * Modify the program that displays different colors depending on which corner the mouse is in. Make it display a fifth color if the mouse is in the middle of the window.

###  Next: [Loops](Loops.jsp)

