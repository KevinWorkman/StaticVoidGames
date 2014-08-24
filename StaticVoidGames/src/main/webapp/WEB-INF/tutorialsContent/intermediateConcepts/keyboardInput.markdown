#  [Intermediate Concepts](index.jsp)

## Keyboard Input

Now we know how to get mouse input from the user, and that's probably enough
for many games- especially if you're looking to port your game to a mobile
device. But most games involve the keyboard, so we'll go over a few ways to do
that here.

### Polling the Keyboard

The simplest piece of information you can ask they keyboard is: is a key
pressed? Processing stores the answer to this question in a boolean variable
called keyPressed.

    
    
    void draw() {
      if (keyPressed) {
        background(0, 255, 0);
      } 
      else {
        background(0, 0, 0);
      }
    }
    

This might be enough for things like skipping intro screens, but for most
purposes you probably want to know which key is pressed. Processing holds this
information in another predefined variable: this time a char variable aptly
named key.

    
    
    void draw() {
      if (keyPressed) {
        
        println(key);
        
        if(key == 'r' || key == 'R'){
          background(255, 0, 0);
        }
        else if(key == 'g' || key == 'G'){
          background(0, 255, 0);
        }
        else if(key == 'b' || key == 'B'){
          background(0, 0, 255);
        }
        else{
          background(100, 100, 100);
        }
      } 
      else {
        background(0, 0, 0);
      }
    }
    

Using char values to check which key is pressed makes your code easier to
read, but what about keys like enter or the arrow keys? For these, another
variable holds the underlying value in a variable called keyCode.

To use the keyCode variable, you have to first check that the key pressed is
coded. Then you can compare the keyCode variable one of Processing's other
predefined variables holding keyCode values such as UP or DOWN to figure out
which key is pressed.

    
    
    void draw() {
      if (keyPressed) {
    
        if (key == CODED) {
          
          if (keyCode == UP) {
            background(255, 0, 0);
          } 
          else if (keyCode == DOWN) {
            background(0, 255, 0);
          }
          else if(keyCode == LEFT){
            background(0, 0, 255);
          }
          else if(keyCode == RIGHT){
            background(255, 0, 255);
          }
        } //end if coded
        else {
          background(255, 255, 0);
        }
      } //end if pressed
      else {
        background(0, 0, 0);
      }
    }
    
    

### Detecting Keyboard Events

We can also detect events instead of polling, which is like having the
keyboard tell us when stuff happens instead of us asking the keyboard whether
stuff is happening. This gives you more control over how you react to user
input. For example, here's a program that allows a user to move a circle
around with the arrow keys:

    
    
    float ballX = 250;
    float ballY = 250;
    
    float speed = 5;
    
    void setup(){
     size(500, 500); 
    }
    
    void draw(){
      background(0);
     ellipse(ballX, ballY, 25, 25);
    } 
    
    void keyPressed(KeyEvent e){
     if(key == CODED){
      if(keyCode == UP){
        ballY -= speed;
      }
      else if(keyCode == DOWN){
       ballY += speed; 
      }
      else if(keyCode == LEFT){
        ballX -= speed;
      }
      else if(keyCode == RIGHT){
        ballX += speed;
      }
     } 
    }
    
    

### Detecting Multiple Key Presses

The above program works okay, but it doesn't support multiple key presses.
Also, different keyboards repeat key signals at different times, so you will
end up with jerky or speedy animations depending on the system. We can solve
both problems by keeping track of which keys are currently pressed.

    
    
    
    float ballX = 250;
    float ballY = 250;
    
    float speed = 5;
    
    boolean upPressed = false;
    boolean downPressed = false;
    boolean leftPressed = false;
    boolean rightPressed = false;
    
    void setup() {
      size(500, 500);
    }
    
    void draw() {
    
      if (upPressed) {
        ballY -= speed;
      }
      if (downPressed) {
        ballY += speed;
      }
      if (leftPressed) {
        ballX -= speed;
      }
      if (rightPressed) {
        ballX += speed;
      }
    
      background(0);
      ellipse(ballX, ballY, 25, 25);
    } 
    
    void keyPressed(KeyEvent e) {
      if (key == CODED) {
        if (keyCode == UP) {
          upPressed = true;
        }
        else if (keyCode == DOWN) {
          downPressed = true;
        }
        else if (keyCode == LEFT) {
          leftPressed = true;
        }
        else if (keyCode == RIGHT) {
          rightPressed = true;
        }
      }
    }
    
    void keyReleased(KeyEvent e) {
      if (key == CODED) {
        if (keyCode == UP) {
          upPressed = false;
        }
        else if (keyCode == DOWN) {
          downPressed = false;
        }
        else if (keyCode == LEFT) {
          leftPressed = false;
        }
        else if (keyCode == RIGHT) {
          rightPressed = false;
        }
      }
    }
    
    
    

This program uses a boolean to keep track of whether each arrow key is
currently pressed down. It sets these variables in the keyPressed() and
keyReleased() functions and checks the values in the draw() function to take
the appropriate action. Notice that using the keyReleased() function gives us
more information than simply using the predefined keyPressed, key, and keyCode
variables by themselves.

Note: Different keyboards support different numbers of simultaneous key
presses. For example, my computer only detects two arrow keys, and pressing a
third is not registered. This is a hardware limitation, and there isn't much
we can do about it in our code.

### Exercises

  * Modify the circle program to not allow the circle to leave the screen. Check out the tutorial on if statements for some help!
  * Modify the circle program so that the circle accelerates and decelerates.
  * Create a program that displays text entered by the user. Hint: You could use a String variable and add to it in the keyTyped() function.

###  Next: [Collision Detection](CollisionDetection.jsp)
