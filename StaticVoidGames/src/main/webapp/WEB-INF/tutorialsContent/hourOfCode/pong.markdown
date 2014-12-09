##Pong

If you've made it this far, you already know how to use variables, how to use functions, how to use if statements, and how to check for user input. Believe it or not, that's enough knowledge to program your first game!

And don't worry: if you don't care about video games, there are plenty of other things you can do with programming. In fact: contact me and I'd love to write more tutorials on whatever you're interested in doing!

Anyway, let's start with a really basic program:

    int ballX = 250;
    int ballY = 250;

    void setup() {
      size(500, 500);
    }

    void draw() {
      background(0, 0, 0);
      ellipse(ballX, ballY, 10, 10);
    }
    
![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/pong1.png)
    
This program uses the **setup() function** to set the size of the window to 500 pixels by 500 pixels- it would be pretty annoying to play Pong on a tiny window! Then in the draw() function, we just draw a ball in the middle of the screen.

Next, let's add a paddle to the game:

    int ballX = 250;
    int ballY = 250;
    
    int playerOneX = 0;
    int playerOneY = 250;
    
    int paddleHeight = 50;
    int paddleWidth = 25;

    void setup() {
      size(500, 500);
    }

    void draw() {
      background(0, 0, 0);
      
      rect(playerOneX, playerOneY, paddleWidth, paddleHeight);
     
      ellipse(ballX, ballY, 10, 10);
    }
    
![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/pong2.png)
    
We've added four variables: the **playerOneX** variable holds the X-value (distance from the left of the window) of player one's paddle. The **playerOneY** variable holds the Y-value (distance from the top of the window) of player one's paddle. The **paddleHeight** and **paddleWidth** variables hold the height and width of the paddles.

We then **call the rect() function** with those variables to draw our paddle. Remember that the rect() function **takes four arguments**: the x and y positions of the rectangle to draw, and the width and height of the rectangle.

That's not very exciting, so next we'll allow the player to move the paddle using the arrow keys:

    int ballX = 250;
    int ballY = 250;

    int playerOneX = 0;
    int playerOneY = 250;

    int paddleHeight = 50;
    int paddleWidth = 25;

    void setup() {
      size(500, 500);
    }

    void draw() {
      background(0, 0, 0);

      if (keyPressed) {
        if (keyCode == UP) {
          playerOneY = playerOneY - 5;
        }

        if (keyCode == DOWN) {
          playerOneY = playerOneY + 5;
        }
      }

      rect(playerOneX, playerOneY, paddleWidth, paddleHeight);

      ellipse(ballX, ballY, 10, 10);
    }
    
![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/pong3.gif)

That's a good start, but the player can move the paddle off the screen! Let's add two more if statements to prevent that. The only thing that will change is the if(keyPressed) if statement in the draw() function, so let's focus on that:
 
       if (keyPressed) {
       
        if (keyCode == UP) {
          if(playerOneY > 0){
            playerOneY = playerOneY - 5;
          }
        }

        if (keyCode == DOWN) {
          if(playerOneY + paddleHeight < 500){
            playerOneY = playerOneY + 5;
          } 
        }
      }
      
Going through this line-by-line, we can see that the code does the following:

  - if a key is pressed, then check which key it is.
  - If it's the UP key, then check if the playerOneY variable is greater than 0. 
  - If the playerOneY variable is greater than 0, that means it's **not** above the top of the window, so we can move the paddle up. To do that, we decrease the playerOneY variable.
  - If the key is the DOWN key, we check if the playerOneY variable **plus** the paddleHeight variable is less than 500. The Y-position of the **top** of the paddle is playerOneY. That means the Y-position of the **bottom** of the paddle is located at playerOneY+paddleHeight.
  - If the playerOneY variable plus the paddleHeight variable is less than 500, that means the bottom of the paddle is **not** going off the bottom of the window, and we can move the paddle down. To do that, we increase the playerOneY variable.
  
![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/pong4.gif)
 
Now that we have one paddle, let's add player two's paddle. Let's have player two's paddle controlled by the mouse:

    int ballX = 250;
    int ballY = 250;

    int playerOneX = 0;
    int playerOneY = 250;

    int playerTwoX = 475;
    int playerTwoY = 250;

    int paddleHeight = 50;
    int paddleWidth = 25;

    void setup() {
      size(500, 500);
    }

    void draw() {
      background(0, 0, 0);

      if (keyPressed) {
        if (keyCode == UP) {
          if(playerOneY > 0){
            playerOneY = playerOneY - 5;
          }
        }

        if (keyCode == DOWN) {
          if(playerOneY + paddleHeight < 500){
            playerOneY = playerOneY + 5;
          }
        }
      }
  
      if(playerTwoY < mouseY){
        playerTwoY = playerTwoY + 5;
      }
  
      if(playerTwoY > mouseY){
       playerTwoY = playerTwoY - 5; 
      }

      rect(playerOneX, playerOneY, paddleWidth, paddleHeight);
      rect(playerTwoX, playerTwoY, paddleWidth, paddleHeight);

      ellipse(ballX, ballY, 10, 10);
    }
    
![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/pong5.gif)

Now we've added variables for player two's position, and two if statements that compare the position of player two's paddle to the position of the mouse. If playerTwoY is less than mouseY, that means the paddle is **above** the mouse, so we move the paddle down by increasing the playerTwoY variable. If playerTwoY is greater than mouseY, that means the paddle is **below** the mouse, so we move the paddle up by decreasing the playerTwoY variable.

Okay, now we have two players- let's get the ball moving! You already know how to make the ball bounce around, so let's start with a ball that bounces off the top and bottom of the screen:

    int ballX = 250;
    int ballY = 250;

    int xSpeed = -1;
    int ySpeed = 2;

    int playerOneX = 0;
    int playerOneY = 250;

    int playerTwoX = 475;
    int playerTwoY = 250;

    int paddleHeight = 50;
    int paddleWidth = 25;

    void setup() {
      size(500, 500);
    }

    void draw() {
      background(0, 0, 0);

      if (keyPressed) {
        if (keyCode == UP) {
          if(playerOneY > 0){
            playerOneY = playerOneY - 5;
          }
        }

        if (keyCode == DOWN) {
          if(playerOneY + paddleHeight < 500){
            playerOneY = playerOneY + 5;
          }
        }
      }
  
      if(playerTwoY < mouseY){
        playerTwoY = playerTwoY + 5;
      }
  
      if(playerTwoY > mouseY){
       playerTwoY = playerTwoY - 5; 
      }
  
      ballX = ballX + xSpeed;
      ballY = ballY + ySpeed;
  
      if(ballY < 0){
        ySpeed = ySpeed * -1;
      }
  
      if(ballY > 500){
       ySpeed = ySpeed * -1; 
      }
  
      rect(playerOneX, playerOneY, paddleWidth, paddleHeight);
      rect(playerTwoX, playerTwoY, paddleWidth, paddleHeight);

      ellipse(ballX, ballY, 10, 10);
    }

![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/pong6.gif)

Now we've added 4 variables: two for the ball's X and Y position, and two for the ball's X and Y speed. Then we added two if statements: one that bounces the ball of the top of the window, and one that bounces it off the bottom. We're bouncing the ball by multiplying the ySpeed variable by -1, which will cause the direction to reverse.

But how do we check whether the ball is **colliding** with a paddle? That's simple- more if statements!

We start out with an if statement that checks whether the ball has gone to the left of player one's paddle:

    if (ballX < playerOneX + paddleWidth) {
      
The **right side** of player one's paddle is at X-position playerOneX + paddleWidth. So if ballX is less than that, we know that the ball has gone "behind" the right side of player one's paddle.

Next, we have to check whether the ball is **above** or **below** player one's paddle. If it is, that means player two scores a point, and we should reset the ball to the middle of the screen:

    if (ballY < playerOneY) {
      playerTwoScore = playerTwoScore + 1;
      ballX = 250;
      ballY = 250;
    }

    if (ballY > playerOneY + paddleHeight) {
      playerTwoScore = playerTwoScore + 1;
      ballX = 250;
      ballY = 250;
    }
    
And finally, we bounce the ball by reversing the xSpeed variable:

    xSpeed = xSpeed * -1;
    
Putting it all together, we get this code:

    int ballX = 250;
    int ballY = 250;

    int xSpeed = -1;
    int ySpeed = 2;

    int playerOneX = 0;
    int playerOneY = 250;

    int playerTwoX = 475;
    int playerTwoY = 250;

    int paddleHeight = 50;
    int paddleWidth = 25;

    int playerOneScore = 0;
    int playerTwoScore = 0;

    void setup() {
      size(500, 500);
    }

    void draw() {
      background(0, 0, 0);

      if (keyPressed) {
        if (keyCode == UP) {
          if (playerOneY > 0) {
            playerOneY = playerOneY - 5;
          }
        }

        if (keyCode == DOWN) {
          if (playerOneY + paddleHeight < 500) {
            playerOneY = playerOneY + 5;
          }
        }
      }

      if (playerTwoY < mouseY) {
        playerTwoY = playerTwoY + 5;
      }

      if (playerTwoY > mouseY) {
        playerTwoY = playerTwoY - 5;
      }

      ballX = ballX + xSpeed;
      ballY = ballY + ySpeed;

      if (ballY < 0) {
        ySpeed = ySpeed * -1;
      }

      if (ballY > 500) {
        ySpeed = ySpeed * -1;
      }

      if (ballX < playerOneX + paddleWidth) {

        if (ballY < playerOneY) {
          playerTwoScore = playerTwoScore + 1;
          ballX = 250;
          ballY = 250;
        }

        if (ballY > playerOneY + paddleHeight) {
          playerTwoScore = playerTwoScore + 1;
          ballX = 250;
          ballY = 250;
        }

        xSpeed = xSpeed * -1;
      }

      rect(playerOneX, playerOneY, paddleWidth, paddleHeight);
      rect(playerTwoX, playerTwoY, paddleWidth, paddleHeight);

      ellipse(ballX, ballY, 10, 10);
    }
    
![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/pong7.gif)

Now all we have to do is add the same logic in for player two. That's exactly like the logic for player one.

And the last thing we have to do is display the scores using the text() function!

Here is what our completed code looks like:

    int ballX = 250;
    int ballY = 250;

    int xSpeed = -1;
    int ySpeed = 2;

    int playerOneX = 0;
    int playerOneY = 250;

    int playerTwoX = 475;
    int playerTwoY = 250;

    int paddleHeight = 50;
    int paddleWidth = 25;

    int playerOneScore = 0;
    int playerTwoScore = 0;

    void setup() {
      size(500, 500);
    }

    void draw() {
      background(0, 0, 0);

      if (keyPressed) {
        if (keyCode == UP) {
          if (playerOneY > 0) {
            playerOneY = playerOneY - 5;
          }
        }

        if (keyCode == DOWN) {
          if (playerOneY + paddleHeight < 500) {
            playerOneY = playerOneY + 5;
          }
        }
      }

      if (playerTwoY < mouseY) {
        playerTwoY = playerTwoY + 5;
      }

      if (playerTwoY > mouseY) {
        playerTwoY = playerTwoY - 5;
      }

      ballX = ballX + xSpeed;
      ballY = ballY + ySpeed;

      if (ballY < 0) {
        ySpeed = ySpeed * -1;
      }

      if (ballY > 500) {
        ySpeed = ySpeed * -1;
      }

      if (ballX < playerOneX + paddleWidth) {

        if (ballY < playerOneY) {
          playerTwoScore = playerTwoScore + 1;
          ballX = 250;
          ballY = 250;
        }

        if (ballY > playerOneY + paddleHeight) {
          playerTwoScore = playerTwoScore + 1;
          ballX = 250;
          ballY = 250;
        }

        xSpeed = xSpeed * -1;
      }
      
      if (ballX > playerTwoX) {

        if (ballY < playerTwoY) {
          playerOneScore = playerOneScore + 1;
          ballX = 250;
          ballY = 250;
        }

        if (ballY > playerTwoY + paddleHeight) {
          playerOneScore = playerOneScore + 1;
          ballX = 250;
          ballY = 250;
        }

        xSpeed = xSpeed * -1;
      }
      
      textSize(36);
      text(playerOneScore, 100, 100);
      text(playerTwoScore, 400, 100);

      rect(playerOneX, playerOneY, paddleWidth, paddleHeight);
      rect(playerTwoX, playerTwoY, paddleWidth, paddleHeight);

      ellipse(ballX, ballY, 10, 10);
    }
    
![](http://StaticVoidGames.com/tutorialsContent/hourOfCode/pong8.gif)
    
Congratulations! You've now coded a full Pong game. [What's next?](http://staticvoidgames.com/tutorials/hourOfCode/next)