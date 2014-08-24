#  [Deployment](index.jsp)

## Example: Exporting a JOGL Game

This tutorial takes you through creating a JOGL game that uses a the JOGL and
Gluegen library jars and OS-specific native files, and exporting it in a way
that will allow you to package it up as a single file using SvgExe.

This tutorial assumes you already know how to use JOGL and how to compile your
game's code with JOGL.

### Example Program

Here's an example class that I got from the [JOGL demos
page](http://jogamp.org/jogl-demos/www/):

    
    
    
    
    import javax.media.opengl.GL2;
    import javax.media.opengl.GLAutoDrawable;
    import javax.media.opengl.GLEventListener;
    import javax.media.opengl.GLProfile;
    import javax.media.opengl.awt.GLCanvas;
    import com.jogamp.opengl.util.Animator;
    
    import com.jogamp.newt.Window;
    import com.jogamp.newt.event.KeyAdapter;
    import com.jogamp.newt.event.KeyEvent;
    import com.jogamp.newt.event.KeyListener;
    import com.jogamp.newt.event.MouseAdapter;
    import com.jogamp.newt.event.MouseEvent;
    import com.jogamp.newt.event.MouseListener;
    import com.jogamp.newt.event.awt.AWTKeyAdapter;
    import com.jogamp.newt.event.awt.AWTMouseAdapter;
    
    /**
     * Gears.java   
    
     * author: Brian Paul (converted to Java by Ron Cemer and Sven Gothel)
     *
     * This version is equal to Brian Paul's version 1.2 1999/10/21
     */
    
    public class Gears implements GLEventListener {
      private float view_rotx = 20.0f, view_roty = 30.0f, view_rotz = 0.0f;
      private int gear1=0, gear2=0, gear3=0;
      private float angle = 0.0f;
      private int swapInterval;
    
      private boolean mouseRButtonDown = false;
      private int prevMouseX, prevMouseY;
    
      public static void main(String[] args) {
        // set argument 'NotFirstUIActionOnProcess' in the JNLP's application-desc tag for example
        // 
        //   NotFirstUIActionOnProcess 
        // 
        // boolean firstUIActionOnProcess = 0==args.length || !args[0].equals("NotFirstUIActionOnProcess") ;
    
        java.awt.Frame frame = new java.awt.Frame("Gear Demo");
        frame.setSize(300, 300);
        frame.setLayout(new java.awt.BorderLayout());
    
        final Animator animator = new Animator();
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
              // Run this on another thread than the AWT event queue to
              // make sure the call to Animator.stop() completes before
              // exiting
              new Thread(new Runnable() {
                  public void run() {
                    animator.stop();
                    System.exit(0);
                  }
                }).start();
            }
          });
    
        GLCanvas canvas = new GLCanvas();
        animator.add(canvas);
        // GLCapabilities caps = new GLCapabilities(GLProfile.getDefault());
        // GLCanvas canvas = new GLCanvas(caps);
    
        final Gears gears = new Gears();
        canvas.addGLEventListener(gears);
    
        frame.add(canvas, java.awt.BorderLayout.CENTER);
        frame.validate();
    
        frame.setVisible(true);
        animator.start();
      }
      
      public Gears(int swapInterval) {
        this.swapInterval = swapInterval;
      }
    
      public Gears() {
        this.swapInterval = 1;
      }
      
      public void setGears(int g1, int g2, int g3) {
          gear1 = g1;
          gear2 = g2;
          gear3 = g3;
      }
    
      /**
       * @return display list gear1
       */
      public int getGear1() { return gear1; }
    
      /**
       * @return display list gear2
       */
      public int getGear2() { return gear2; }
    
      /**
       * @return display list gear3
       */
      public int getGear3() { return gear3; }
    
      public void init(GLAutoDrawable drawable) {
        System.err.println("Gears: Init: "+drawable);
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));
    
        GL2 gl = drawable.getGL().getGL2();
    
        System.err.println("Chosen GLCapabilities: " + drawable.getChosenGLCapabilities());
        System.err.println("INIT GL IS: " + gl.getClass().getName());
        System.err.println("GL_VENDOR: " + gl.glGetString(GL2.GL_VENDOR));
        System.err.println("GL_RENDERER: " + gl.glGetString(GL2.GL_RENDERER));
        System.err.println("GL_VERSION: " + gl.glGetString(GL2.GL_VERSION));
    
        float pos[] = { 5.0f, 5.0f, 10.0f, 0.0f };
        float red[] = { 0.8f, 0.1f, 0.0f, 0.7f };
        float green[] = { 0.0f, 0.8f, 0.2f, 0.7f };
        float blue[] = { 0.2f, 0.2f, 1.0f, 0.7f };
    
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, pos, 0);
        gl.glEnable(GL2.GL_CULL_FACE);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_DEPTH_TEST);
                
        /* make the gears */
        if(0>=gear1) {
            gear1 = gl.glGenLists(1);
            gl.glNewList(gear1, GL2.GL_COMPILE);
            gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, red, 0);
            gear(gl, 1.0f, 4.0f, 1.0f, 20, 0.7f);
            gl.glEndList();
            System.err.println("gear1 list created: "+gear1);
        } else {
            System.err.println("gear1 list reused: "+gear1);
        }
                
        if(0>=gear2) {
            gear2 = gl.glGenLists(1);
            gl.glNewList(gear2, GL2.GL_COMPILE);
            gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, green, 0);
            gear(gl, 0.5f, 2.0f, 2.0f, 10, 0.7f);
            gl.glEndList();
            System.err.println("gear2 list created: "+gear2);
        } else {
            System.err.println("gear2 list reused: "+gear2);
        }
                
        if(0>=gear3) {
            gear3 = gl.glGenLists(1);
            gl.glNewList(gear3, GL2.GL_COMPILE);
            gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, blue, 0);
            gear(gl, 1.3f, 2.0f, 0.5f, 10, 0.7f);
            gl.glEndList();
            System.err.println("gear3 list created: "+gear3);
        } else {
            System.err.println("gear3 list reused: "+gear3);
        }
                
        gl.glEnable(GL2.GL_NORMALIZE);
                    
        // MouseListener gearsMouse = new TraceMouseAdapter(new GearsMouseAdapter());
        MouseListener gearsMouse = new GearsMouseAdapter();    
        KeyListener gearsKeys = new GearsKeyAdapter();
    
        if (drawable instanceof Window) {
            Window window = (Window) drawable;
            window.addMouseListener(gearsMouse);
            window.addKeyListener(gearsKeys);
        } else if (GLProfile.isAWTAvailable() && drawable instanceof java.awt.Component) {
            java.awt.Component comp = (java.awt.Component) drawable;
            new AWTMouseAdapter(gearsMouse).addTo(comp);
            new AWTKeyAdapter(gearsKeys).addTo(comp);
        }
      }
        
      public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        System.err.println("Gears: Reshape "+x+"/"+y+" "+width+"x"+height);
        GL2 gl = drawable.getGL().getGL2();
    
        gl.setSwapInterval(swapInterval);
    
        float h = (float)height / (float)width;
                
        gl.glMatrixMode(GL2.GL_PROJECTION);
    
        gl.glLoadIdentity();
        gl.glFrustum(-1.0f, 1.0f, -h, h, 5.0f, 60.0f);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -40.0f);
      }
    
      public void dispose(GLAutoDrawable drawable) {
        System.err.println("Gears: Dispose");
        setGears(0, 0, 0);
      }
    
      public void display(GLAutoDrawable drawable) {
        // Turn the gears' teeth
        angle += 2.0f;
    
        // Get the GL corresponding to the drawable we are animating
        GL2 gl = drawable.getGL().getGL2();
    
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    
        // Special handling for the case where the GLJPanel is translucent
        // and wants to be composited with other Java 2D content
        if (GLProfile.isAWTAvailable() && 
            (drawable instanceof javax.media.opengl.awt.GLJPanel) &&
            !((javax.media.opengl.awt.GLJPanel) drawable).isOpaque() &&
            ((javax.media.opengl.awt.GLJPanel) drawable).shouldPreserveColorBufferIfTranslucent()) {
          gl.glClear(GL2.GL_DEPTH_BUFFER_BIT);
        } else {
          gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        }
                
        // Rotate the entire assembly of gears based on how the user
        // dragged the mouse around
        gl.glPushMatrix();
        gl.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(view_rotz, 0.0f, 0.0f, 1.0f);
                
        // Place the first gear and call its display list
        gl.glPushMatrix();
        gl.glTranslatef(-3.0f, -2.0f, 0.0f);
        gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
        gl.glCallList(gear1);
        gl.glPopMatrix();
                
        // Place the second gear and call its display list
        gl.glPushMatrix();
        gl.glTranslatef(3.1f, -2.0f, 0.0f);
        gl.glRotatef(-2.0f * angle - 9.0f, 0.0f, 0.0f, 1.0f);
        gl.glCallList(gear2);
        gl.glPopMatrix();
                
        // Place the third gear and call its display list
        gl.glPushMatrix();
        gl.glTranslatef(-3.1f, 4.2f, 0.0f);
        gl.glRotatef(-2.0f * angle - 25.0f, 0.0f, 0.0f, 1.0f);
        gl.glCallList(gear3);
        gl.glPopMatrix();
                
        // Remember that every push needs a pop; this one is paired with
        // rotating the entire gear assembly
        gl.glPopMatrix();
      }
    
      public static void gear(GL2 gl,
                              float inner_radius,
                              float outer_radius,
                              float width,
                              int teeth,
                              float tooth_depth)
      {
        int i;
        float r0, r1, r2;
        float angle, da;
        float u, v, len;
    
        r0 = inner_radius;
        r1 = outer_radius - tooth_depth / 2.0f;
        r2 = outer_radius + tooth_depth / 2.0f;
                
        da = 2.0f * (float) Math.PI / teeth / 4.0f;
                
        gl.glShadeModel(GL2.GL_FLAT);
    
        gl.glNormal3f(0.0f, 0.0f, 1.0f);
    
        /* draw front face */
        gl.glBegin(GL2.GL_QUAD_STRIP);
        for (i = 0; i <= teeth; i++)
          {
            angle = i * 2.0f * (float) Math.PI / teeth;
            gl.glVertex3f(r0 * (float)Math.cos(angle), r0 * (float)Math.sin(angle), width * 0.5f);
            gl.glVertex3f(r1 * (float)Math.cos(angle), r1 * (float)Math.sin(angle), width * 0.5f);
            if(i < teeth)
              {
                gl.glVertex3f(r0 * (float)Math.cos(angle), r0 * (float)Math.sin(angle), width * 0.5f);
                gl.glVertex3f(r1 * (float)Math.cos(angle + 3.0f * da), r1 * (float)Math.sin(angle + 3.0f * da), width * 0.5f);
              }
          }
        gl.glEnd();
    
        /* draw front sides of teeth */
        gl.glBegin(GL2.GL_QUADS);
        for (i = 0; i < teeth; i++)
          {
            angle = i * 2.0f * (float) Math.PI / teeth;
            gl.glVertex3f(r1 * (float)Math.cos(angle), r1 * (float)Math.sin(angle), width * 0.5f);
            gl.glVertex3f(r2 * (float)Math.cos(angle + da), r2 * (float)Math.sin(angle + da), width * 0.5f);
            gl.glVertex3f(r2 * (float)Math.cos(angle + 2.0f * da), r2 * (float)Math.sin(angle + 2.0f * da), width * 0.5f);
            gl.glVertex3f(r1 * (float)Math.cos(angle + 3.0f * da), r1 * (float)Math.sin(angle + 3.0f * da), width * 0.5f);
          }
        gl.glEnd();
        
        /* draw back face */
        gl.glBegin(GL2.GL_QUAD_STRIP);
        for (i = 0; i <= teeth; i++)
          {
            angle = i * 2.0f * (float) Math.PI / teeth;
            gl.glVertex3f(r1 * (float)Math.cos(angle), r1 * (float)Math.sin(angle), -width * 0.5f);
            gl.glVertex3f(r0 * (float)Math.cos(angle), r0 * (float)Math.sin(angle), -width * 0.5f);
            gl.glVertex3f(r1 * (float)Math.cos(angle + 3 * da), r1 * (float)Math.sin(angle + 3 * da), -width * 0.5f);
            gl.glVertex3f(r0 * (float)Math.cos(angle), r0 * (float)Math.sin(angle), -width * 0.5f);
          }
        gl.glEnd();
        
        /* draw back sides of teeth */
        gl.glBegin(GL2.GL_QUADS);
        for (i = 0; i < teeth; i++)
          {
            angle = i * 2.0f * (float) Math.PI / teeth;
            gl.glVertex3f(r1 * (float)Math.cos(angle + 3 * da), r1 * (float)Math.sin(angle + 3 * da), -width * 0.5f);
            gl.glVertex3f(r2 * (float)Math.cos(angle + 2 * da), r2 * (float)Math.sin(angle + 2 * da), -width * 0.5f);
            gl.glVertex3f(r2 * (float)Math.cos(angle + da), r2 * (float)Math.sin(angle + da), -width * 0.5f);
            gl.glVertex3f(r1 * (float)Math.cos(angle), r1 * (float)Math.sin(angle), -width * 0.5f);
          }
        gl.glEnd();
        
        /* draw outward faces of teeth */
        gl.glBegin(GL2.GL_QUAD_STRIP);
        for (i = 0; i < teeth; i++)
          {
            angle = i * 2.0f * (float) Math.PI / teeth;
            gl.glVertex3f(r1 * (float)Math.cos(angle), r1 * (float)Math.sin(angle), width * 0.5f);
            gl.glVertex3f(r1 * (float)Math.cos(angle), r1 * (float)Math.sin(angle), -width * 0.5f);
            u = r2 * (float)Math.cos(angle + da) - r1 * (float)Math.cos(angle);
            v = r2 * (float)Math.sin(angle + da) - r1 * (float)Math.sin(angle);
            len = (float)Math.sqrt(u * u + v * v);
            u /= len;
            v /= len;
            gl.glNormal3f(v, -u, 0.0f);
            gl.glVertex3f(r2 * (float)Math.cos(angle + da), r2 * (float)Math.sin(angle + da), width * 0.5f);
            gl.glVertex3f(r2 * (float)Math.cos(angle + da), r2 * (float)Math.sin(angle + da), -width * 0.5f);
            gl.glNormal3f((float)Math.cos(angle), (float)Math.sin(angle), 0.0f);
            gl.glVertex3f(r2 * (float)Math.cos(angle + 2 * da), r2 * (float)Math.sin(angle + 2 * da), width * 0.5f);
            gl.glVertex3f(r2 * (float)Math.cos(angle + 2 * da), r2 * (float)Math.sin(angle + 2 * da), -width * 0.5f);
            u = r1 * (float)Math.cos(angle + 3 * da) - r2 * (float)Math.cos(angle + 2 * da);
            v = r1 * (float)Math.sin(angle + 3 * da) - r2 * (float)Math.sin(angle + 2 * da);
            gl.glNormal3f(v, -u, 0.0f);
            gl.glVertex3f(r1 * (float)Math.cos(angle + 3 * da), r1 * (float)Math.sin(angle + 3 * da), width * 0.5f);
            gl.glVertex3f(r1 * (float)Math.cos(angle + 3 * da), r1 * (float)Math.sin(angle + 3 * da), -width * 0.5f);
            gl.glNormal3f((float)Math.cos(angle), (float)Math.sin(angle), 0.0f);
          }
        gl.glVertex3f(r1 * (float)Math.cos(0), r1 * (float)Math.sin(0), width * 0.5f);
        gl.glVertex3f(r1 * (float)Math.cos(0), r1 * (float)Math.sin(0), -width * 0.5f);
        gl.glEnd();
        
        gl.glShadeModel(GL2.GL_SMOOTH);
        
        /* draw inside radius cylinder */
        gl.glBegin(GL2.GL_QUAD_STRIP);
        for (i = 0; i <= teeth; i++)
          {
            angle = i * 2.0f * (float) Math.PI / teeth;
            gl.glNormal3f(-(float)Math.cos(angle), -(float)Math.sin(angle), 0.0f);
            gl.glVertex3f(r0 * (float)Math.cos(angle), r0 * (float)Math.sin(angle), -width * 0.5f);
            gl.glVertex3f(r0 * (float)Math.cos(angle), r0 * (float)Math.sin(angle), width * 0.5f);
          }
        gl.glEnd();
      }
    
      class GearsKeyAdapter extends KeyAdapter {      
        public void keyPressed(KeyEvent e) {
            int kc = e.getKeyCode();
            if(KeyEvent.VK_LEFT == kc) {
                view_roty -= 1;
            } else if(KeyEvent.VK_RIGHT == kc) {
                view_roty += 1;
            } else if(KeyEvent.VK_UP == kc) {
                view_rotx -= 1;
            } else if(KeyEvent.VK_DOWN == kc) {
                view_rotx += 1;
            }
        }
      }
      
      class GearsMouseAdapter extends MouseAdapter {
          public void mousePressed(MouseEvent e) {
            prevMouseX = e.getX();
            prevMouseY = e.getY();
            if ((e.getModifiers() & e.BUTTON3_MASK) != 0) {
              mouseRButtonDown = true;
            }
          }
            
          public void mouseReleased(MouseEvent e) {
            if ((e.getModifiers() & e.BUTTON3_MASK) != 0) {
              mouseRButtonDown = false;
            }
          }
            
          public void mouseDragged(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            int width=0, height=0;
            Object source = e.getSource();
            if(source instanceof Window) {
                Window window = (Window) source;
                width=window.getWidth();
                height=window.getHeight();
            } else if (GLProfile.isAWTAvailable() && source instanceof java.awt.Component) {
                java.awt.Component comp = (java.awt.Component) source;
                width=comp.getWidth();
                height=comp.getHeight();
            } else {
                throw new RuntimeException("Event source neither Window nor Component: "+source);
            }
            float thetaY = 360.0f * ( (float)(x-prevMouseX)/(float)width);
            float thetaX = 360.0f * ( (float)(prevMouseY-y)/(float)height);
            
            prevMouseX = x;
            prevMouseY = y;
    
            view_rotx += thetaX;
            view_roty += thetaY;
          }
      }
    }
    

This program uses JOGL to draw some gears using OpenGL. It looks like this:

![](Jogl1.png)

### Compile Your Code

To use SvgExe, you need to compile this code to a .class file or include it in
a .jar file. Since you're using JOGL, I'm going to assume you know how to do
that. If not, let me know!

Since this class uses anonymous inner classes, when we compile it we get the
following:

  * Gears.class
  * Gears$1.class
  * Gears$1$1.class
  * Gears$GearsKeyAdapter.class
  * Gears$GearsMouseAdapter.class

You can either package these class files into a jar, or you can just use them
in SvgExe directly.

### JOGL Jar and Natives

To create a runnable jar, you'll also need the JOGL library jar (jogl-
all.jar), the Gluegen library jar (gluegen-rt.jar), as well as the native
library files for any OS you want your game to run on. These are all available
in the standard JOGL download.

For example, here is my JOGL directory structure:

    
    JOGL
    |   jogl-all.jar
    |   
    \---natives
        +---linux
        |   +---amd64
        |   |       libjogl_desktop.so
        |   |       libjogl_mobile.so
        |   |       libnativewindow_awt.so
        |   |       libnativewindow_x11.so
        |   |       libnewt.so
        |   |       
        |   +---armv6
        |   |       libjogl_desktop.so
        |   |       libjogl_mobile.so
        |   |       libnativewindow_awt.so
        |   |       libnativewindow_x11.so
        |   |       libnewt.so
        |   |       
        |   +---armv6hf
        |   |       libjogl_desktop.so
        |   |       libjogl_mobile.so
        |   |       libnativewindow_awt.so
        |   |       libnativewindow_x11.so
        |   |       libnewt.so
        |   |       
        |   \---i586
        |           libjogl_desktop.so
        |           libjogl_mobile.so
        |           libnativewindow_awt.so
        |           libnativewindow_x11.so
        |           libnewt.so
        |           
        +---macosx
        |       libjogl_desktop.jnilib
        |       libjogl_mobile.jnilib
        |       libnativewindow_awt.jnilib
        |       libnativewindow_macosx.jnilib
        |       libnewt.jnilib
        |       
        +---solaris
        |   +---amd64
        |   |       libjogl_desktop.so
        |   |       libjogl_mobile.so
        |   |       libnativewindow_awt.so
        |   |       libnativewindow_x11.so
        |   |       libnewt.so
        |   |       
        |   \---i586
        |           libjogl_desktop.so
        |           libjogl_mobile.so
        |           libnativewindow_awt.so
        |           libnativewindow_x11.so
        |           libnewt.so
        |           
        \---windows
            +---amd64
            |       jogl_desktop.dll
            |       jogl_mobile.dll
            |       nativewindow_awt.dll
            |       nativewindow_win32.dll
            |       newt.dll
            |       
            \---i586
                    jogl_desktop.dll
                    jogl_mobile.dll
                    nativewindow_awt.dll
                    nativewindow_win32.dll
                    newt.dll
    			

And here is my Gluegen directory structure:

    
    Glugen
    |   gluegen-rt.jar
    |   gluegen.jar
    |   
    \---natives
        +---linux
        |   +---amd64
        |   |       libgluegen-rt.so
        |   |       
        |   +---armv6
        |   |       libgluegen-rt.so
        |   |       
        |   +---armv6hf
        |   |       libgluegen-rt.so
        |   |       
        |   \---i586
        |           libgluegen-rt.so
        |           
        +---macosx
        |       libgluegen-rt.jnilib
        |       
        +---solaris
        |   +---amd64
        |   |       libgluegen-rt.so
        |   |       
        |   \---i586
        |           libgluegen-rt.so
        |           
        \---windows
            +---amd64
            |       gluegen-rt.dll
            |       
            \---i586
                    gluegen-rt.dll
                    
    			
    			

### Putting it all together using SvgExe

Now that you have all the basics, you can use SvgExe to combine these into a
single jar that you can send to other people (or upload here!).

#### Step 0: Download SvgExe

SvgExe is a single runnable jar that you can download
[here](http://StaticVoidGames.com/SvgExe/). Download that jar and double-click
it to run the program.

#### Step 1: Add the jars to SvgExe

Run SvgExe, and on the first tab, add the JOGL and Gluegen library jars, along
with your project's class files (which themselves can be in another jar file).
Mine looks like this:

![](Jogl2.png)

#### Step 2: Include the OS-specific natives directories

JOGL and Gluegen require OS-specific natives. You can see above how I've laid
them out in OS and architecture specific directories, and I include the OS-
specific directories here:

![](Jogl3.png)

Notice that I have two directories for each operating system: one for JOGL,
and one for Gluegen. You could combine the natives for a particular OS and
architecture into one directory, I just happened to keep my JOGL and Gluegen
natives separate.

SvgExe automatically detects the user's system and extracts the correct
natives.

#### Step 3: Specify your output

Now all that's left is specifying the output.

My main class is Gear, so I include that here. If your main class is in a
package, make sure to include the package here!

Then you just specify the name and location of the jar file to be created.

![](Jogl4.png)

#### Step 3: Run your jar!

Navigate to wherever you specified your output, and double-click the jar to
run it. That's it!

Now you can send this jar file to other people who want to play your game.. or
you can upload it here! :p
