
<%
	session.setAttribute("loginRedirect", request.getRequestURL());
%>
<!DOCTYPE HTML>
<html>

<head>
<title>Static Void Games - Learn</title>
<%@ include file="../../../include/meta.inc"%>
<%@ include file="../../../include/jspCss.inc"%>
<%@ include file="../../../include/analytics.inc"%>

<link rel="stylesheet" type="text/css"
	href="http://s3.staticvoidgames.com/javascript/prettify/sunburst.css" />
<script type="text/javascript" src="http://s3.staticvoidgames.com/javascript/prettify/prettify.js"></script>


</head>

<body onload="prettyPrint()">
	<div class="wrap">

		<%@ include file="../../../include/navigation.inc"%>

		<div id="lonePage">

			<h1 id="pageTitle">Deploying as Both an Applet and an Application</h1>
			<div class="centeredDiv" style="text-align: left;">

				<p>One of Java's strengths is that it makes it easy to deploy your game in a number of different
					formats, which allows more people to play your game. By following this guide, you'll be able to
					deploy your game as an applet, a webstart application, and a standalone runnable jar file.</p>

				<p>To start, I'll create a new Java project in Eclipse and call it DeploymentTest.</p>

				<p>Then I'll add a class named GamePanel to that project:</p>

				<pre>
				<code class="prettyprint">
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel{

	public GamePanel(){
		setBackground(Color.ORANGE);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawString("Static Void Games!", 100, 100);
	}	
}
				</code>
				</pre>

				<p>That class simply draws a String, but that JPanel could contain anything, including your
					game, other components, whatever. The main point is, put everything into a JPanel.</p>


				<p>Then I create another class that contains a main method and shows the GamePanel class in
					a JFrame:</p>


				<pre>
				<code class="prettyprint">
import javax.swing.JFrame;

public class MainClass {
	public static void main(String... args){
		JFrame frame = new JFrame("Deployment Test - Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GamePanel gamePanel = new GamePanel();
		frame.setContentPane(gamePanel);
		
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
}
</code>
				</pre>


				<p>I run MainClass, and I see this:</p>
				<img src="applicationScreenshot.png" />

				<p>From there, I just add another class that extends JApplet and also uses the GamePanel
					class as a content pane:</p>


				<pre>
				<code class="prettyprint">
import javax.swing.JApplet;

public class AppletClass extends JApplet{
	public void init(){
		GamePanel gamePanel = new GamePanel();
		setContentPane(gamePanel);
	}
}</code>
				</pre>

				<p>When I run that class as an applet, I see the following, which should not be very
					surprising:</p>
				<img src="appletScreenshot.png" />


				<p>I then export the project as a runnable jar, using MainClass as the deployment
					configuration so the program will launch as an application when a user runs the jar standalone.
					AppletClass will also be included in the jar, and it will run instead of the MainClass when my
					jar is launched as an applet. Now I know that people can run my game as an applet, a webstart,
					or as a standalone jar!</p>

				<p>I just upload the jar and specify MainClass as the main class and AppletClass as the
					applet class. And that's it!</p>

				<hr />

				<p>I could condense my MainClass and AppletClass into a single class that will run as both
					an applet and an application:</p>


				<pre>
				<code class="prettyprint">
import javax.swing.JApplet;
import javax.swing.JFrame;

public class GameLauncher extends JApplet{

	public void init(){
		GamePanel gamePanel = new GamePanel();
		setContentPane(gamePanel);
	}
	
	public static void main(String... args){
		JFrame frame = new JFrame("Deployment Test - Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GamePanel gamePanel = new GamePanel();
		frame.setContentPane(gamePanel);
		
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
}
</code>
</pre>

				<p>Then I can use this class as both the main class and the applet class. That works because
					the init() method is only called when the class is run as an applet, and the main() method is
					only called when the program is run as a jar. You should do whichever one fits into your brain
					the best.</p>



				<hr />

				<p>To summarize, here's the gist of the process:</p>
				<ol>
					<li>Put your game into a JPanel.</li>
					<li>Create a class that contains a main method. From the main method, create a JFrame and
						set an instance of your game JPanel as the JFrame's content pane. Set the JFrame's size and
						default close operation, then set it to visible. Specify this class as the main application
						class when you upload your jar.</li>
					<li>Create a class that extends JApplet, and set an instance of your game JPanel as the
						JApplet's content pane. Specify this class as the applet class when uploading your game.</li>
				</ol>


			</div>



		</div>

		<div class="ad">
			Advertisement
			<hr />

			<script type="text/javascript"><!--
				google_ad_client = "ca-pub-4466462725672711";
				/* Static Void Games */
				google_ad_slot = "6683391146";
				google_ad_width = 728;
				google_ad_height = 90;
				//-->
			</script>
			<script type="text/javascript" src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
			</script>
		</div>
		<div style="clear: both;"></div>


		<%@ include file="../../../include/footer.inc"%>

	</div>



</body>

</html>