#  [Deployment](index.jsp)

## Java Security Settings

Java can be used by programmers to write code, and it can be used by end-users
(you) to run that code. Two of the easiest ways for end-users to run Java code
is through applets and webstart applications, both which can be run from an
internet browser.

Java's default security settings do not allow you to run applets or webstarts
unless the developer of the applet or webstart has paid for something called a
certificate. This prevents malicious programmers from using Java to do bad
things, but it also prevents innocent programmers (many who can't afford to
pay for a certificate) from deploying their programs in a way that's easy for
users like you to use.

This page goes through a couple ways you can change your security settings to
allow Java to run applets and webstarts.

There are other ways to run Java programs, but applets and webstarts are what
most people are used to. If you'd rather play a game as a downloadable jar or
executable application, let the developer of the game know!

### Is this safe?

For more detail on this, check out the [brief history of Java
security](History.jsp). But the gist is: running an applet or webstart is no
more or less dangerous than running any other program you download from the
internet. It used to be dangerous because malicious programmers could run code
without telling you, but now even the lowest security settings show you a
prompt before running a Java program.

This leaves it up to you to decide which games and programs you want to run.
We've never had a security problem here, and if we did, it would be dealt with
right away.

### Running an Applet

Many games on this website are deployed as an applet, which embeds the game
directly on the page. [Here](http://staticvoidgames.com/play/?game=GameOfLife)
is an example of a game that uses an applet as a play option.

If you don't have Java installed, navigating to the Applet tab will take you
to the Java download page.

If you do have Java installed, the first thing you'll notice when you navigate
to the Applet tab is a prompt at the top of your browser asking for your
permission to run Java:

![](http://staticvoidgames.com/tutorials/deployment/Applet1.png)

It's up to you whether you choose "Run this time" or "Always run on this
site".

Depending on how the developer created the applet (there is an extra step
where the developer "signs" the applet, which is a way to include information
about who created it), you might then see another prompt asking whether you
want to run an "unsigned" application. Signing the application is a step that
many programmers skip, but all it means is that the code doesn't include info
about who created it.

![](Applet2.png)

Press "Run" in that window, and if your settings are correct (or if the
developer has a certificate), the applet should start! However, if your
security settings are the default, you'll see this message:

![](ApplicationBlocked1.png)

This means that you need to change your Java security settings to tell Java
that you want to run applets from developers without certificates.

### Java Control Panel

Java's settings are controlled from the Java control panel, which can be
accessed like this:

Windows 8

  * Move the Mouse pointer to the bottom-right corner of the screen, then click on the Settings icon.
  * Choose "Control Panel" from the list on the right.
  * In the search box type: Java Control Panel
  * Click on Java icon to open the Java Control Panel.

Windows 7, Vista

  * Click on the Start button and then click on the Control Panel option.
  * In the Control Panel Search enter Java Control Panel.
  * Click on the Java icon to open the Java Control Panel.

Windows XP

* Click on the Start button and then click on the Control Panel option.
* Double click on the Java icon to open the Java Control Panel.

Mac OS X 10.7.3 and above

* Click on Apple icon on upper left of screen.
* Go to System Preferences
* Click on the Java icon to access the Java Control Panel.

The Java Control Panel looks like this:

![](JavaControlPanel.png)

The Java Control Panel gives you access to a bunch of settings, but we care
about the Security tab. The default settings look like this:

![](JavaControlPanel2.png)

You have two options on this tab: you can either change the security level
slider to "Medium" to allow applets and webstart applications from developers
without a certificate, or you can add StaticVoidGames.com to allow applets and
webstart applications from this website while still restricting content on
other websites.

### Changing the security level

If you run a lot of Java programs on the web, it's probably easier just to
change the security level. To do this, you simply drag the slider down to
"Medium" and press the OK button.

![](JavaControlPanel3.png)

This will make it so your web browser still prompts you for permission to run
Java programs (so you can still choose not to run programs you aren't
expecting), but it won't block programs just because the developer couldn't
afford a certificate.

### Adding StaticVoidGames.com to the Exception Site List

If Static Void Games is the only place you run Java programs, then you might
be better off just adding http://StaticVoidGames.com to the security exception
site list. This will make it so Java still prompts you for permission to run
Java programs, but it won't block programs *ONLY ON THIS SITE* just because
the developer couldn't afford a certificate. All other websites remain
unaffected.

To add StaticVoidGames to the exception site list, click the "Edit Site
List..." button towards the bottom of the Java Control Panel:

![](JavaControlPanel4.png)

This brings up the Exception Site List window:

![](JavaControlPanel5.png)

Click the "add" button and type http://StaticVoidGames.com into the text field
that becomes visible, and then click the add button and type
http://s3.StaticVoidGames.com in the next text field, as pictured here:

![](JavaControlPanel6.png)

You need both URLs for this to work. StaticVoidGames.com is the main site, and
s3.StaticVoidGames.com is where all the game files are stored, and Java treats
them as separate sites.

You might get a warning complaining about using http instead of https. Yeah
yeah, I'm getting to it.

When you're done, your exception site list should look like this:

![](JavaControlPanel9.png)

Now you should be able to run applets and webstarts on the website! If you
have any questions, feel free to [contact
me](http://staticvoidgames.com/about/contact.jsp), or leave a comment on the
game you're trying to play to contact the developer directly!
