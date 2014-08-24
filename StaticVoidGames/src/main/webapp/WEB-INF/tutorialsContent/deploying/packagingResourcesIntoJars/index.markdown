
<%
	session.setAttribute("loginRedirect", request.getRequestURL());
%>
<!DOCTYPE HTML>
<html>

<head>
<title>Packaging Resources Into Jars- Static Void Games</title>
<%@ include file="../../../include/meta.inc"%>
<meta name="description" content="Packaging Resources Into Jars- Static Void Games">

<%@ include file="../../../include/jspCss.inc"%>
<%@ include file="../../../include/analytics.inc"%>

<link rel="stylesheet" type="text/css"
	href="http://s3.staticvoidgames.com/javascript/prettify/sunburst.css" />
<script type="text/javascript" src="http://s3.staticvoidgames.com/javascript/prettify/prettify.js"></script>


<style type="text/css">
#lonePage2 {
	width: 800px;
	margin: auto;
	background: black;
	border: thin solid darkGrey;
	color: white;
	margin-top: 25px;
}

div.centeredDiv {
	
}
</style>

</head>

<body onload="prettyPrint()">

	<%@ include file="../../../include/topBar.inc"%>

		<div id="lonePage2">

			<h1 id="pageTitle">Packaging Resources into Jars</h1>
			
			<div class="centeredDiv" style="text-align: left;">

				<p>There are many reasons that games must use other files- images, sounds, level schematics,
					etc. But it's annoying to require a user to download all of those files as well as your jar or
					to try to download the files from an external host. Luckily, you can easily include those files
					inside your jar- you just have to know the proper way to access them.</p>

				<p>I'm going to start by creating a Java project in Eclipse, and I'll call it
					JarResourceTest. The project contains a source folder (usually called src by default) which
					will contain all of my Java classes. I then create a folder inside the src directory. I'll call
					it "resources", but you could call it anything you want. That's where I'll put all the files I
					want to be included in the jar. For starters, I'll put a simple text file in that diretory,
					test.txt, which contains some Strings I want to read in line by line.</p>

				<p>Then I'll create a class inside the main src folder and call it JarResourceTest. So far,
					my project looks like this:</p>

				<img src="projectSetup.png" />


				<p>
					This is JarResourceTest, reading in the file the way everybody is used to seeing. <font
						style="color: red">Note: This is NOT how you read files that are inside a jar! Keep reading!</font>
				</p>

				<pre>
				<code class="prettyprint">
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class JarResourceTest {	
	public static void main(String... args){
		
		
		try {
			Scanner s = new Scanner(new File("src/resources/test.txt"));
			while(s.hasNextLine()){
				System.out.println(s.nextLine());
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
				</code>
				</pre>


				<p>I run that file, and anything in the test file will be printed out. But if I export my
					project as a jar, that won't work! What's going on?</p>

				<p>The reason I can't load files inside a jar is because things inside jars are not files, they're resources! So File IO won't work
					on them. Loading images from file won't work; loading sounds from file won't work. Because if
					it's inside a jar, it's not a file. It's a resource.</p>

				<p>That might seem like a pedantic distinction, but it makes a big difference in the code.
					If you're using File IO, you're telling your program to look for a file, which by definition is
					outside that jar. You have to tell it to look for a resource instead. It's probably best just
					to demonstrate the updated JarResourceTest:</p>

				<pre>
				<code class="prettyprint">
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;


public class JarResourceTest {	
	public static void main(String... args){
	
		InputStream input = JarResourceTest.class.getClassLoader()
			.getResourceAsStream("resources/test.txt");
	
		Scanner s = new Scanner(input);
		while(s.hasNextLine()){
			System.out.println(s.nextLine());
		}
	}
}
				</code>
				</pre>


				<p>Now the code gets an InputStream from a resource instead of a file. That InputStream can
					be used to load images, sounds, or other files, and those files can be inside OR outside the
					jar!</p>

				<p>Running that file results in the same output. And now if I export the file as a jar, that
					jar will work anywhere because it's reading from the resource inside of it!</p>



				<p>Similarly, this is how I would load an image named image.png as a resource:</p>

				<pre>
<code class="prettyprint">

import java.awt.Image;
import javax.swing.ImageIcon;

public class JarResourceTest {	
	public static void main(String... args){

		Image image = new ImageIcon(JarResourceTest.class.getClassLoader()
			.getResource("resources/image.png")).getImage();
			
		//I could now display the image, but just to test things out:
		System.out.println("width: " + image.getWidth(null));
		System.out.println("height: " + image.getHeight(null));
	}
}
</code>
</pre>

				<p>So, to summarize:
				<ol>
					<li>Create a "resources" directory inside the src directory.</li>
					<li>Load those files (which are called resources when they're inside a jar) as either a
						resource or a resourceAsStream using the class's class loader.</li>
					<li>Pass the loaded resource or InputStream into an appropriate constructor to access it!</li>
				</ol>

				<p>This method will work from your code (before you package it as a jar), as a standalone
					jar, applet, or webstart application!</p>


				<p>
					This tutorial has assumed you're working from eclipse, but you don't need eclipse to include a
					directory in a jar. For more info, check out this section of the Java tutorial : <a
						href="http://docs.oracle.com/javase/tutorial/deployment/jar/update.html">Updating a JAR
						File</a>
				</p>

			</div>


		<div style="clear: both;"></div>

		</div>

		<div class="ad">
			Advertisement
			<hr />

			<script type="text/javascript">
			<!--
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