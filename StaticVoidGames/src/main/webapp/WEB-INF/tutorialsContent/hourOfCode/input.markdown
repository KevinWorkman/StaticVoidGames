##User Input

Now we know how to use variables, functions, and if statements. You can go surprisingly far with just those three things! But so far all we've done is display stuff- programming becomes much more interesting when you do stuff based on user input!

Processing keeps track of several variables that allow you to get input from the user. Here are a few:

  - mouseX: holds the current x position (distance from the **left edge** of the window) of the mouse.
  - mouseY: holds the current y position (distance from the **top edge** of the window) of the mouse.
  - mousePressed: holds whether or not a button on the mouse is currently pressed.
  - keyPressed: holds whether or not a key on the keyboard is currently pressed.
  - key: holds the letter key on the keyboard being pressed. This variable is used for letters (a, b, c, 1, 2, 3, etc).
  - keyCode: holds the non-letter key on the keyboard being pressed. This variable is used for things like the ctrl, alt, and arrow keys, which aren't letters.
  
There are other variables (and functions) that you can use, but these are the main ones you'll probably want to use.

###Mouse Input

You've already seen one way of getting user input: the **mouseX** and **mouseY** variables, which hold the current position of the mouse. We can use those variables to have a ball follow the mouse:

    void draw() {
      background(0, 0, 0);
      ellipse(mouseX, mouseY, 10, 10);
    }
    
But the **mousePressed** variable is new. The mousePressed variable keeps track of whether a mouse button is currently pressed. You can use it in if statements to do something when the mouse is pressed. For example, we can modify the above program to only show the circle at the mouse position when a mouse button is pressed:

    void draw() {
      background(0, 0, 0);
  
      if(mousePressed){
        ellipse(mouseX, mouseY, 10, 10);
      }
    }
    
![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/input1.gif)
    
If it seems weird that we're using a variable directly in an if statement instead of setting up an equality, don't worry- remember that variables have different **types**? (You've already seen the **int** type.) The mousePressed variable is a **boolean**, which means that we can use it in if statements without an inequality. The if statement will only be triggered when a button on the mouse is pressed.

###Keyboard Input

Similar to the mousePressed variable, the **keyPressed** variable is a **boolean** that keeps track of whether a key is being pressed on the keyboard. Since they keyPressed variable is a boolean, we can use it directly in an if statement. For example this program draws an ellipse only when a key on the keyboard is being pressed:

    void draw() {
      background(0, 0, 0);
  
      if(keyPressed){
        ellipse(50, 50, 25, 25);
      }
    }
    
![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/input2.gif)
    
Copy this code into your Processing editor, press the play button, and hold in a key!

You can also use the **key** and **keyCode** variables to figure out which key the user pressed.

The **key** variable keeps track of which **character** (basically, any letter you can type) pressed by the user. This example displays the character being pressed:

    void draw() {
      background(0, 0, 0);
  
      if(keyPressed){
        textSize(36);
        text(key, 50, 50);
      }
    }
    
![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/input3.gif)
    
You can also check whether the user is holding in a specific character by **comparing** the key variable to a **character value**. Using a character value is a lot like using a number, except instead of a number, you use a single character between quotes. The values 'a', 'b', and 'c' are all character values.

Here is some example code that displays an ellipse when the 'e' character is pressed, and a rectangle when the 'r' character is pressed:

    void draw() {
      background(0, 0, 0);
  
      if(keyPressed){
        
        if(key == 'e'){
          ellipse(50, 50, 25, 25);
        }
        
        if(key == 'r'){
         rect(25, 25, 50, 50); 
        }
     
      }
    }
    
![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/input4.gif)
    
The first thing you might notice is that we have if statements inside of other if statements- and that's completely okay! This is called **nested** if statements, and you can think about it this way: we only want to check if the key being pressed is 'e' or 'r' **if** we know that a key is being pressed in the first place!

The other thing you might notice is that we're using double equals (==) to check if the **key** variable is **equal to** the 'e' or 'r' **character values**. This is similar to the **less than** and **greater than** operators you saw earlier. Remeber: The single equals operator (=) is for **assignment** (like when we set a variable equal to a value), and the double equals operator (==) is for **comparison** (like we're doing in the above program). The double equals **compares** two things, whereas the single equals **changes the value** of something.

But how do we check for things like ctrl, alt, or arrow keys? What are their character values?

The answer is that they don't have any character values, so they don't work with the key variable. Instead, we have to use the **keyCode** variable to check for them, and we can compare them to other predefined variables that Processing gives us. For example, this program displays an ellipse in the top or bottom of the window when the user presses the UP or DOWN arrows:

    void draw() {
      background(0, 0, 0);
  
      if(keyPressed){
        
        if(keyCode == UP){
          ellipse(50, 25, 25, 25);
        }
        
        if(keyCode == DOWN){
          ellipse(50, 75, 25, 25);
        }
     
      }
    }
    
![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/input5.gif)
    
Here we're using if statements and the == comparison to check if the user has pressed UP or DOWN.

Next up: Believe it or not, you now know enough about programming to make your first game! Let's go make [Pong](http://staticvoidgames.com/tutorials/hourOfCode/pong)!!