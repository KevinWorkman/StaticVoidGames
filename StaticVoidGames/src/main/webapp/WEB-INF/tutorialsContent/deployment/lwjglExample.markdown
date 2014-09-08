#  [Deployment](index.jsp)

## Example: Exporting a LWJGL Game

This tutorial takes you through creating a LWJGL game that uses a library jar
and OS-specific native files, and exporting it in a way that will allow you to
package it up as a single file using JarMatey.

This tutorial assumes you already know how to use LWJGL and how to compile a
jar that contains your game's code separately from the LWJGL library jar.

### Example Program

Here's an example class that I got from the [LWJGL
wiki](http://lwjgl.org/wiki/index.php?title=LWJGL_Basics_3_(The_Quad)):

    
    
    import org.lwjgl.LWJGLException;
    import org.lwjgl.Sys;
    import org.lwjgl.input.Keyboard;
    import org.lwjgl.opengl.Display;
    import org.lwjgl.opengl.DisplayMode;
    import org.lwjgl.opengl.GL11;
    
    public class TimerExample {
    
    	/** position of quad */
    	float x = 400, y = 300;
    	/** angle of quad rotation */
    	float rotation = 0;
    	
    	/** time at last frame */
    	long lastFrame;
    	
    	/** frames per second */
    	int fps;
    	/** last fps time */
    	long lastFPS;
    
    	public void start() {
    		try {
    			Display.setDisplayMode(new DisplayMode(800, 600));
    			Display.create();
    		} catch (LWJGLException e) {
    			e.printStackTrace();
    			System.exit(0);
    		}
    
    		initGL(); // init OpenGL
    		getDelta(); // call once before loop to initialise lastFrame
    		lastFPS = getTime(); // call before loop to initialise fps timer
    
    		while (!Display.isCloseRequested()) {
    			int delta = getDelta();
    			
    			update(delta);
    			renderGL();
    
    			Display.update();
    			Display.sync(60); // cap fps to 60fps
    		}
    
    		Display.destroy();
    	}
    	
    	public void update(int delta) {
    		// rotate quad
    		rotation += 0.15f * delta;
    		
    		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) x -= 0.35f * delta;
    		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) x += 0.35f * delta;
    		
    		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) y -= 0.35f * delta;
    		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) y += 0.35f * delta;
    		
    		// keep quad on the screen
    		if (x < 0) x = 0;
    		if (x > 800) x = 800;
    		if (y < 0) y = 0;
    		if (y > 600) y = 600;
    		
    		updateFPS(); // update FPS Counter
    	}
    	
    	/** 
    	 * Calculate how many milliseconds have passed 
    	 * since last frame.
    	 * 
    	 * @return milliseconds passed since last frame 
    	 */
    	public int getDelta() {
    	    long time = getTime();
    	    int delta = (int) (time - lastFrame);
    	    lastFrame = time;
    	 
    	    return delta;
    	}
    	
    	/**
    	 * Get the accurate system time
    	 * 
    	 * @return The system time in milliseconds
    	 */
    	public long getTime() {
    	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    	}
    	
    	/**
    	 * Calculate the FPS and set it in the title bar
    	 */
    	public void updateFPS() {
    		if (getTime() - lastFPS > 1000) {
    			Display.setTitle("FPS: " + fps);
    			fps = 0;
    			lastFPS += 1000;
    		}
    		fps++;
    	}
    	
    	public void initGL() {
    		GL11.glMatrixMode(GL11.GL_PROJECTION);
    		GL11.glLoadIdentity();
    		GL11.glOrtho(0, 800, 0, 600, 1, -1);
    		GL11.glMatrixMode(GL11.GL_MODELVIEW);
    	}
    
    	public void renderGL() {
    		// Clear The Screen And The Depth Buffer
    		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    
    		// R,G,B,A Set The Color To Blue One Time Only
    		GL11.glColor3f(0.5f, 0.5f, 1.0f);
    
    		// draw quad
    		GL11.glPushMatrix();
    			GL11.glTranslatef(x, y, 0);
    			GL11.glRotatef(rotation, 0f, 0f, 1f);
    			GL11.glTranslatef(-x, -y, 0);
    			
    			GL11.glBegin(GL11.GL_QUADS);
    				GL11.glVertex2f(x - 50, y - 50);
    				GL11.glVertex2f(x + 50, y - 50);
    				GL11.glVertex2f(x + 50, y + 50);
    				GL11.glVertex2f(x - 50, y + 50);
    			GL11.glEnd();
    		GL11.glPopMatrix();
    	}
    	
    	public static void main(String[] argv) {
    		TimerExample timerExample = new TimerExample();
    		timerExample.start();
    	}
    }
    

This program uses LWJGL to draw to OpenGL. It's nothing fancy, but it contains
input, timing, and native OpenGL calls.

![](http://StaticVoidGames.com/tutorialsContent/deployment/Lwjgl1.png)

### Export

To use JarMatey, you need to compile this code to a .class file or include it in
a .jar file. Since you're using LWJGL, I'm going to assume you know how to do
that. If not, let me know!

### LWJGL Jar and Natives

To use JarMatey, you'll also need the LWJGL library jar (lwjgl.jar) as well as
the native folder for any OS you want your game to run on. These are all
available in the standard LWJGL download.

For example, here is the lwjgl.jar you'll need:

![](http://StaticVoidGames.com/tutorialsContent/deployment/Lwjgl2.png)

As well as the OS-specific native directories:

![](http://StaticVoidGames.com/tutorialsContent/deployment/Lwjgl3.png)

### Putting it all together using JarMatey

Now that you have all the basics, you can now use JarMatey to combine these into
a single jar that you can send to other people (or upload here!).

#### Step 0: Download JarMatey

JarMatey is a single runnable jar that you can download
[here](http://StaticVoidGames.com/JarMatey/). Download that jar and double-click
it to run the program.

#### Step 1: Add the jars to JarMatey

Run JarMatey, and on the first tab, add the LWJGL library jar along with your
project's class files (which themselves can be in another jar file). I'm just
using a single class, so mine looks like this:

![](http://StaticVoidGames.com/tutorialsContent/deployment/Lwjgl4.png)

#### Step 2: Include the OS-specific natives directories

LWJGL requires OS-specific natives, and luckily they come pre-packaged in OS-
specific directories that we can just include here:

![](http://StaticVoidGames.com/tutorialsContent/deployment/Lwjgl5.png)

JarMatey automatically detects the user's system and extracts the correct
natives.

#### Step 3: Specify your output

Now all that's left is specifying the output.

My main class is TimerExample, so I include that here. If your main class is
in a package, make sure to include the package here!

Then you just specify the name and location of the jar file to be created.

#### Step 3: Run your jar!

Navigate to wherever you specified your output, and double-click the jar to
run it. That's it!

Now you can send this jar file to other people who want to play your game.. or
you can upload it here! :p
