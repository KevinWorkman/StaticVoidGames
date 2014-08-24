#  [Intermediate Concepts](index.jsp)

## Pong

You're probably familiar with the game of
[Pong](http://en.wikipedia.org/wiki/Pong), which consists of two paddles on
either side of the screen bouncing a ball back and forth. It was the first
commercially successful video game, and it makes a perfect first game to
program. You already know everything you need to make Pong! But this tutorial
might help you connect all of the pieces.

### Game Cycle

Before we dive into the game, we should set up a game cycle: a beginning title
screen, a place to put our game logic, and a game over screen. Players expect
this kind of game cycle, so it's pretty important to have. We might as well
build it in from the very beginning!

We accomplish this by having a boolean for each mode our game can be in: in
this case, one for the title, one for the actual game, and one for the game
over screen. We then check those booleans in the draw() function as well as
the input functions to switch between the modes.

    
    
    boolean showTitleScreen = true;
    boolean playing = false;
    boolean gameOver = false;
    
    void setup() {
      size(500, 500);
    }
    
    void keyPressed() {
      if (showTitleScreen) {
        if (key == 'P' || key == 'p') {
          showTitleScreen = false;
          playing = true;
        }
      }
      else if (gameOver) {
        if (key == ' ') {
          gameOver = false;
          showTitleScreen = true;
        }
      }
    }
    
    void mousePressed() {
      if (playing) {
        playing = false;
        gameOver = true;
      }
    }
    
    void draw() {
      if (showTitleScreen) {
        background(0, 0, 255);
        
        textSize(36);
        text("Game Title", 165, 100);
        
        textSize(18);
        text("Press 'P' to play.", 175, 400);
      }
      else if (playing) {
        background(0, 255, 0);
        
        textSize(36);
        text("Playing!", 165, 100);
        
        textSize(18);
        text("Click mouse for end screen.", 165, 400);
      }
      else if (gameOver) {
        background(255, 0, 0);
        
        textSize(36);
        text("Game Over", 165, 100);
        
        textSize(18);
        text("Press space to restart.", 175, 400);
      }
    }
    
    
    

Note that you can have as many different modes as you want, as long as you
keep track of which one you're in. Also note that this should probably be an
enum or even sepate Objects in charge of different modes, but I'm using
booleans to keep things simple. But now we have a skeleton that we can use to
create our first game!

### Player One

The first step in making our game is to get the left paddle on the screen. We
do that by using code very similar to what we used in the [Keyboard Input
tutorial](KeyboardInput.jsp).

First we create float variables holding the X, Y, width, and height of player
one's paddle. We also create booleans to keep track of whether the up or down
arrow keys are pressed. We update those boolean variables in the keyPressed()
and keyReleased() functions. Finally, in the draw() function, we check the
booleans, update the Y value of the paddle, and draw it using the rect()
function.

    
    
    boolean showTitleScreen = true;
    boolean playing = false;
    boolean gameOver = false;
    
    float playerOneX = 25;
    float playerOneY = 125;
    float playerOneWidth = 10;
    float playerOneHeight = 50;
    
    float paddleSpeed = 5;
    
    boolean upPressed = false;
    boolean downPressed = false;
    
    void setup() {
      size(500, 500);
    
      ellipseMode(CENTER);
      ellipseMode(RADIUS);
    }
    
    void keyPressed() {
      if (showTitleScreen) {
        if (key == 'P' || key == 'p') {
          showTitleScreen = false;
          playing = true;
        }
      }
      else if (playing) {
        if (key == CODED) {
          if (keyCode == UP) {
            upPressed = true;
          }
          else if (keyCode == DOWN) {
            downPressed = true;
          }
        }
      }
      else if (gameOver) {
        if (key == ' ') {
          gameOver = false;
          showTitleScreen = true;
        }
      }
    }
    
    void keyReleased() {
      if (playing) {
        if (key == CODED) {
          if (keyCode == UP) {
            upPressed = false;
          }
          else if (keyCode == DOWN) {
            downPressed = false;
          }
        }
      }
    }
    
    void mousePressed() {
      if (playing) {
        playing = false;
        gameOver = true;
      }
    }
    
    void draw() {
      if (showTitleScreen) {
        background(0, 0, 255);
    
        textSize(36);
        text("Pong", 165, 100);
    
        textSize(18);
        text("Press 'P' to play.", 175, 400);
      }
      else if (playing) {
        background(0, 255, 0);
    
        if (upPressed) {
          playerOneY -= paddleSpeed;
        }
        if (downPressed) {
          playerOneY += paddleSpeed;
        }
    
        rect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
    
        textSize(18);
        text("Click mouse for end screen.", 165, 400);
      }
      else if (gameOver) {
        background(255, 0, 0);
    
        textSize(36);
        text("Game Over", 165, 100);
    
        textSize(18);
        text("Press space to restart.", 175, 400);
      }
    }
    
    

Note that we only care about drawing the paddle or updating the player input
while the game is being played. If you play with this code, you might also
notice that we don't stop the paddle from going off the screen. Luckily, if
you've gone through the [Collision Detection tutorial](CollisionDetection), it
should be pretty easy to add that check:

    	
    
    else if (playing) {
        background(0, 255, 0);
    
        if (upPressed) {
          if (playerOneY-paddleSpeed > 0) {
            playerOneY -= paddleSpeed;
          }
        }
        if (downPressed) {
          if (playerOneY + paddleSpeed + playerOneHeight < height) {
            playerOneY += paddleSpeed;
          }
        }
    
        rect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
    
        textSize(18);
        text("Click mouse for end screen.", 165, 400);
      }
      

We simply add two if statements in the draw() method, just before updating the
paddle's Y position. If the next move would take the paddle off the screen, we
ignore it.

### Player Two

Let's repeat the same logic, this time for player two. We'll have player two
controled by the W and S keys, or you can use whatever makes the most sense on
your keyboard.

    
    				
    				boolean showTitleScreen = true;
    boolean playing = false;
    boolean gameOver = false;
    
    float playerOneX = 25;
    float playerOneY = 125;
    float playerOneWidth = 10;
    float playerOneHeight = 50;
    
    float playerTwoX = 465;
    float playerTwoY = 125;
    float playerTwoWidth = 10;
    float playerTwoHeight = 50;
    
    float paddleSpeed = 5;
    
    boolean upPressed = false;
    boolean downPressed = false;
    boolean wPressed = false;
    boolean sPressed = false;
    
    void setup() {
      size(500, 500);
    
      ellipseMode(CENTER);
      ellipseMode(RADIUS);
    }
    
    void keyPressed() {
      if (showTitleScreen) {
        if (key == 'P' || key == 'p') {
          showTitleScreen = false;
          playing = true;
        }
      }
      else if (playing) {
        if (key == CODED) {
          if (keyCode == UP) {
            upPressed = true;
          }
          else if (keyCode == DOWN) {
            downPressed = true;
          }
        }
        else if (key == 'W' || key == 'w') {
          wPressed = true;
        }
        else if (key == 'S' || key == 's') {
          sPressed = true;
        }
      }
      else if (gameOver) {
        if (key == ' ') {
          gameOver = false;
          showTitleScreen = true;
        }
      }
    }
    
    void keyReleased() {
      if (playing) {
        if (key == CODED) {
          if (keyCode == UP) {
            upPressed = false;
          }
          else if (keyCode == DOWN) {
            downPressed = false;
          }
        }
        else if (key == 'W' || key == 'w') {
          wPressed = false;
        }
        else if (key == 'S' || key == 's') {
          sPressed = false;
        }
      }
    }
    
    void mousePressed() {
      if (playing) {
        playing = false;
        gameOver = true;
      }
    }
    
    void draw() {
      if (showTitleScreen) {
        background(0, 0, 255);
    
        textSize(36);
        text("Pong", 165, 100);
    
        textSize(18);
        text("Press 'P' to play.", 175, 400);
      }
      else if (playing) {
        background(0, 255, 0);
    
        if (upPressed) {
          if (playerOneY-paddleSpeed > 0) {
            playerOneY -= paddleSpeed;
          }
        }
        if (downPressed) {
          if (playerOneY + paddleSpeed + playerOneHeight < height) {
            playerOneY += paddleSpeed;
          }
        }
        if (wPressed) {
          if (playerTwoY-paddleSpeed > 0) {
            playerTwoY -= paddleSpeed;
          }
        }
        if (sPressed) {
          if (playerTwoY + paddleSpeed + playerTwoHeight < height) {
            playerTwoY += paddleSpeed;
          }
        }
    
        rect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
        rect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
    
        textSize(18);
        text("Click mouse for end screen.", 165, 400);
      }
      else if (gameOver) {
        background(255, 0, 0);
    
        textSize(36);
        text("Game Over", 165, 100);
    
        textSize(18);
        text("Press space to restart.", 175, 400);
      }
    }
    				
    				  

Now that we have two paddles moving around in our game world, the next step is
to get a ball bouncing around in there with them!

### Follow the Bouncing Ball

If you've been following these tutorials, you're probably getting sick of
seeing a ball bounce around the screen. Look on the bright side: that should
make this next part really easy for you! We want to add a ball that bounces
off the top and bottom of the screen, bounces off the paddles, and causes a
score to increase when it hits the side of the screen.

This is a lot of little additions, so let's take them one at a time. First, we
set up the variables we're going to need at the top of our code:

    
    
    int playerOneScore = 0;
    int playerTwoScore = 0;
    
    float ballX = 250;
    float ballY = 250;
    float radius = 10;
    
    float ballDeltaX = -1;
    float ballDeltaY = 3;
    

Next, in the draw() function, we create a few variables to make our code more
readable:

    
    
        float nextBallLeft = ballX - radius + ballDeltaX;
        float nextBallRight = ballX + radius + ballDeltaX;
        float nextBallTop = ballY - radius + ballDeltaY;
        float nextBallBottom = ballY + radius + ballDeltaY;
    
        float playerOneRight = playerOneX + playerOneWidth;
        float playerOneTop = playerOneY;
        float playerOneBottom = playerOneY + playerOneHeight;
    
        float playerTwoLeft = playerTwoX;
        float playerTwoTop = playerTwoY;
        float playerTwoBottom = playerTwoY + playerTwoHeight;
    

Note that we only care about where the ball will be during the next frame, the
top and bottom of the paddles, and the sides facing the playing field. In
other words, we don't care about the ball bouncing off the back of the
paddles.

We then use a couple of those variables to detect when the ball hits the top
and bottom of the window:

    
    
    	//ball bounces off top and bottom of screen
        if (nextBallTop < 0 || nextBallBottom > height) {
          ballDeltaY *= -1;
        }
    

If the ball is going off the top or bottom of the screen, it simply bounces
back in the other direction. However, if it is going off the left or right
side, we need to check whether it's hitting or missing the paddle. If it's
about to miss the paddle, we add one to the other player's score and teleport
the ball back to the middle.

    
    
        //will the ball go off the left side?
        if (nextBallLeft < playerOneRight) { 
          //is it going to miss the paddle?
          if (nextBallTop > playerOneBottom || nextBallBottom < playerOneTop) {
            playerTwoScore ++;
            
            ballX = 250;
            ballY = 250;
          }
          //ball hit player one's paddle
          else {
            ballDeltaX *= -1;
          }
        }
    
        //will the ball intersect player two?
        if (nextBallRight > playerTwoLeft) {
          //is it going to miss the paddle?
          if (nextBallTop > playerTwoBottom || nextBallBottom < playerTwoTop) {
            playerOneScore ++;
    
            ballX = 250;
            ballY = 250;
          }
          //ball hit player two's paddle
          else {
            ballDeltaX *= -1;
          }
        }
    
    

Finally, we update the ball's position and draw each player's score as well as
the paddles and the ball on the screen. I've also added some other polishing
such as drawing a dotted line down the middle and goal lines on each side.

    
    
    	ballX += ballDeltaX;
        ballY += ballDeltaY;
    
        //draw dashed line down center
        for (int lineY = 0; lineY < height; lineY += 50) {
         line(250, lineY, 250, lineY+25);
        }
        
        //draw "goal lines" on each side
        line(playerOneRight, 0, playerOneRight, height);
        line(playerTwoLeft, 0, playerTwoLeft, height);
    
        textSize(36);
        text(playerOneScore, 100, 100);
        text(playerTwoScore, 400, 100);
    
        rect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
        rect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
        ellipse(ballX, ballY, radius, radius);
    

At this point we have a working Pong game, but it has one problem: it never
ends! Luckily, we worked in the game cycle from the very beginning, so all we
have to do is set up an end condition and move the code from the
mousePressed() function. Let's make our Pong game end when one of the players
scores 3 points. To do that, we add an if statement after either player's
score is incremented, and end the game when one gets to three points.

    
    
    //is it going to miss the paddle?
          if (nextBallTop > playerOneBottom || nextBallBottom < playerOneTop) {
            playerTwoScore ++;
    
            if (playerTwoScore == 3) {
              playing = false;
              gameOver = true;
            }
    
            ballX = 250;
            ballY = 250;
          }
    

The other if statement looks pretty much exactly like this, just for the other
player. The last thing to add resets the game state when a new game is started
in the keyPressed() method:

    
    
      else if (gameOver) {
        if (key == ' ') {
          gameOver = false;
          showTitleScreen = true;
          playerOneY = 250;
          playerTwoY = 250;
          ballX = 250;
          ballY = 250;
          playerOneScore = 0;
          playerTwoScore = 0;
        }
      }
    

Putting all of that together, the final code for the game looks like this:

    
    
    boolean showTitleScreen = true;
    boolean playing = false;
    boolean gameOver = false;
    
    float playerOneX = 25;
    float playerOneY = 250;
    float playerOneWidth = 10;
    float playerOneHeight = 50;
    
    float playerTwoX = 465;
    float playerTwoY = 250;
    float playerTwoWidth = 10;
    float playerTwoHeight = 50;
    
    float paddleSpeed = 5;
    
    boolean upPressed = false;
    boolean downPressed = false;
    boolean wPressed = false;
    boolean sPressed = false;
    
    int playerOneScore = 0;
    int playerTwoScore = 0;
    
    float ballX = 250;
    float ballY = 250;
    float radius = 10;
    float ballDeltaX = -1;
    float ballDeltaY = 3;
    
    void setup() {
      size(500, 500);
    
      ellipseMode(CENTER);
      ellipseMode(RADIUS);
      
      //draw with white
      stroke(255, 255, 255);
    }
    
    void keyPressed() {
      if (showTitleScreen) {
        if (key == 'P' || key == 'p') {
          showTitleScreen = false;
          playing = true;
        }
      }
      else if (playing) {
        if (key == CODED) {
          if (keyCode == UP) {
            upPressed = true;
          }
          else if (keyCode == DOWN) {
            downPressed = true;
          }
        }
        else if (key == 'W' || key == 'w') {
          wPressed = true;
        }
        else if (key == 'S' || key == 's') {
          sPressed = true;
        }
      }
      else if (gameOver) {
        if (key == ' ') {
          gameOver = false;
          showTitleScreen = true;
          playerOneY = 250;
          playerTwoY = 250;
          ballX = 250;
          ballY = 250;
          playerOneScore = 0;
          playerTwoScore = 0;
        }
      }
    }
    
    void keyReleased() {
      if (playing) {
        if (key == CODED) {
          if (keyCode == UP) {
            upPressed = false;
          }
          else if (keyCode == DOWN) {
            downPressed = false;
          }
        }
        else if (key == 'W' || key == 'w') {
          wPressed = false;
        }
        else if (key == 'S' || key == 's') {
          sPressed = false;
        }
      }
    }
    
    void draw() {
      if (showTitleScreen) {
        background(0, 0, 0);
    
        textSize(36);
        text("Pong", 165, 100);
    
        textSize(18);
        text("Press 'P' to play.", 175, 400);
      }
      else if (playing) {
        background(0, 0, 0);
    
        if (upPressed) {
          if (playerOneY-paddleSpeed > 0) {
            playerOneY -= paddleSpeed;
          }
        }
        if (downPressed) {
          if (playerOneY + paddleSpeed + playerOneHeight < height) {
            playerOneY += paddleSpeed;
          }
        }
        if (wPressed) {
          if (playerTwoY-paddleSpeed > 0) {
            playerTwoY -= paddleSpeed;
          }
        }
        if (sPressed) {
          if (playerTwoY + paddleSpeed + playerTwoHeight < height) {
            playerTwoY += paddleSpeed;
          }
        }
    
        float nextBallLeft = ballX - radius + ballDeltaX;
        float nextBallRight = ballX + radius + ballDeltaX;
        float nextBallTop = ballY - radius + ballDeltaY;
        float nextBallBottom = ballY + radius + ballDeltaY;
    
        float playerOneRight = playerOneX + playerOneWidth;
        float playerOneTop = playerOneY;
        float playerOneBottom = playerOneY + playerOneHeight;
    
        float playerTwoLeft = playerTwoX;
        float playerTwoTop = playerTwoY;
        float playerTwoBottom = playerTwoY + playerTwoHeight;
        
        //ball bounces off top and bottom of screen
        if (nextBallTop < 0 || nextBallBottom > height) {
          ballDeltaY *= -1;
        }
    
        //will the ball go off the left side?
        if (nextBallLeft < playerOneRight) { 
          //is it going to miss the paddle?
          if (nextBallTop > playerOneBottom || nextBallBottom < playerOneTop) {
            playerTwoScore ++;
    
            if (playerTwoScore == 3) {
              playing = false;
              gameOver = true;
            }
    
            ballX = 250;
            ballY = 250;
          }
          else {
            ballDeltaX *= -1;
          }
        }
    
        //will the ball intersect player two?
        if (nextBallRight > playerTwoLeft) {
          //is it going to miss the paddle?
          if (nextBallTop > playerTwoBottom || nextBallBottom < playerTwoTop) {
            playerOneScore ++;
    
            if (playerOneScore == 3) {
              playing = false;
              gameOver = true;
            }
    
            ballX = 250;
            ballY = 250;
          }
          else {
            ballDeltaX *= -1;
          }
        }
    
        ballX += ballDeltaX;
        ballY += ballDeltaY;
    
        //draw dashed line down center
        for (int lineY = 0; lineY < height; lineY += 50) {
         line(250, lineY, 250, lineY+25);
        }
        
        //draw "goal lines" on each side
        line(playerOneRight, 0, playerOneRight, height);
        line(playerTwoLeft, 0, playerTwoLeft, height);
    
        textSize(36);
        text(playerOneScore, 100, 100);
        text(playerTwoScore, 400, 100);
    
        rect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
        rect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
        ellipse(ballX, ballY, radius, radius);
      }
      else if (gameOver) {
        background(0, 0, 0);
    
        textSize(36);
        text(playerOneScore, 100, 100);
        text(playerTwoScore, 400, 100);
    
        textSize(36);
        if (playerOneScore > playerTwoScore) {
          text("Player 1 Wins!", 165, 200);
        }
        else {
          text("Player 2 Wins!", 165, 200);
        }
    
        textSize(18);
        text("Press space to restart.", 150, 400);
      }
    }
    

There are a ton of ways to improve upon this code. I highly recommend watching
[this video](http://www.youtube.com/watch?v=Fy0aCDmgnxg) on "juicing", or
simple techniques that you can use to make basic games like this one so much
better. At the very least, this game serves as a skeleton for building other
games.

![](PongScreenShot.png)

You can play the game we just created
[here](http://staticvoidgames.com/play/?game=Pong).

### Exercises

  * Modify the above program so the paddles accelerate and decelerate.
  * Modify the program so the paddles can affect the direction the ball bounces: hitting the ball in the middle of the paddle causes it to bounce straight at the other player; hitting the ball towards the top of the paddle causes it to bounce up; and hitting the ball with the top part of the paddle causes the ball to bounce down.
  * Add another game mode that explains the rules of the game.
  * Add obstacles in the middle of the pong field.
  * Add powerups to the Pong game: speed up or slow down the ball, make the ball multiply, or anything else you can think of!
  * Create a one-player Pong game: there is not player two, and the ball simply bounces off the right side of the screen. Make the ball go faster every time it bounces, and the object of the game to survive as long as possible without letting the ball passed your paddle.
  * Create a one-player Pong game where the paddle is on the bottom, and use the gravity code from the [Animation tutorial](Animation.jsp) to make the bouncing more realistic.

###  Next: [Object Oriented
Programming](http://StaticVoidGames.com/tutorials/objects/)