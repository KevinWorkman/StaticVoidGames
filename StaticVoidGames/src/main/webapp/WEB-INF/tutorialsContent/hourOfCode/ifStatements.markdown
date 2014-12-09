##If Statements

We now know how to call functions, how to write functions, how to declare variables, how to use variables, and how to update variables to do animations.

The last thing we'll learn is how to use **if statements**, which allow you to execute code **only when something happens**.

You use an **if statement** by typing the **if** keyword, then putting **the thing you want to check for** inside parenthesis, then putting **what you want to happen when that thing is true** inside curly brackets.

Let's start with the code we've already written:

    int ballY = 10;

    void draw() {
      background(0, 0, 0);
  
      ballY = ballY + 1; 
  
      ellipse(50, ballY, 10, 10);
    }

This is the same program as before: it just shows a ball that falls off the bottom of the window.

Remember that the (x, y) position (0, 0) is in the **upper-left** corner of the screen. If our window size is 100 by 100, then the (x, y) position (100, 100) is in the bottom-right of the window. In other words, the x position of a coordinate measures the **distance from the left edge of the window** and the y position of a coordinate measures the **distance from the top edge of the window**.

We can use an **if statement** to check **if the ball has gone off the bottom of the screen** and if so, we can **reset the position of the ball**! It might be easier to just show you the code (and if you went into this knowing nothing about code, isn't that kinda cool?). It looks like this:

    int ballY = 0;

    void draw() {
      background(0, 0, 0);

      ballY = ballY + 1;
  
      if(ballY > 100){
        ballY = 0;
      }

      ellipse(50, ballY, 10, 10);
    }
    
![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/animation2.gif)
    
This code **declares** a variable named ballY outside of the draw() function, which represents the ball's **y position** (the distance from the top edge of the window). Inside the draw() function, we start out by clearing the background. Then we simply add one to the ballY variable so that the ball looks like it's falling.

Then we use an **if statement** to check if the **y position** of the ball has become **greater than** 100, which happens to be the default height of the window.

Then in the **body** of the if statement (the set of instructions between curly brackets), we simply reset the ballY variable to 0.

Finally, we just draw the ball at the ballY position. When we run our program, the ballY variable will increase by one every time the draw() function is called. When the ballY variable becomes greater than 100, we reset it to 0. And since we're drawing the ball at the ballY position, the ball will look like it's falling.

##More if Statements

Let's expand our program so that the ball bounces instead of resets. How would you do that?

I'd start out by creating another variable called ySpeed:

    int ballY = 0;
    int ySpeed = 1;

    void draw() {
      background(0, 0, 0);

      ballY = ballY + ySpeed;
  
      if(ballY > 100){
        ballY = 0;
      }

      ellipse(50, ballY, 10, 10);
    }
    
This program still works the same, except now we use the ySpeed variable to keep track of which direction the ball should go in the y (up and down) direction. Now we can modify our if statement to change the ySpeed variable instead of the ballY variable:

    int ballY = 0;
    int ySpeed = 1;

    void draw() {
      background(0, 0, 0);

      ballY = ballY + ySpeed;
  
      if(ballY > 100){
        ySpeed = -1;
      }

      ellipse(50, ballY, 10, 10);
    }
    
![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/ifStatements3.gif)
    
Now when the ball reaches the bottom of the screen, we set the ySpeed variable to -1. And since we're always adding ySpeed to the ballY variable, changing the ySpeed variable to -1 will cause the ballY value to **decrease** by one instead. Decreasing the ballY value is decreasing the distance from the top edge of the window, so the ball goes back up!

But now the ball just goes off the top of the screen. To prevent this, we can add another if statement that checks for that condition and modifies the ySpeed variable accordingly:

    int ballY = 0;
    int ySpeed = 1;

    void draw() {
      background(0, 0, 0);

      ballY = ballY + ySpeed;

      if (ballY < 0) {
        ySpeed = 1;
      }

      if (ballY > 100) {
        ySpeed = -1;
      }

      ellipse(50, ballY, 10, 10);
    }
    
![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/ifStatements2.gif)

The if statement we just added checks if the ballY variable is below 0, and when that happens, it changes the ySpeed variable back to 1. In other words, if the ball's **distance from the top edge of the window** is below 0, then the ball has started to go off the top of the window, and we bounce it back by modifying the ySpeed variable.

Now that our ball bounces up and down, we just have to do the same thing for the left-right direction. So we add a ballX variable and a ballY variable, and we add if statements that check when the ball goes off the left or right sides of the screen:

    int ballX = 50;
    int ballY = 0;

    int xSpeed = 1;
    int ySpeed = 1;

    void draw() {
      background(0, 0, 0);

      ballX = ballX + xSpeed;
      ballY = ballY + ySpeed;

      if (ballY < 0) {
        ySpeed = 1;
      }

      if (ballY > 100) {
        ySpeed = -1;
      }
  
      if(ballX < 0){
        xSpeed = 1;
      }
  
      if(ballX > 100){
       xSpeed = -1; 
      }

      ellipse(ballX, ballY, 10, 10);
    }
    
![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/ifStatements1.gif)
    
##Next: [User Input](http://staticvoidgames.com/tutorials/hourOfCode/input)
