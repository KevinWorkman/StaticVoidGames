#  [Deployment](index.jsp)

## A Brief History of Java Security

Why does Java deployment work the way it does? Why doesn't it just work
automatically like javascript or html5, where you can just play a game
embedded on a website? To answer that question, we have to go through a brief
history of Java deployment.

### The Early Years

Before Java, programmers had to create separate versions of their programs for
each system they were targeting. You might have noticed this if you've ever
had to pick your operating system out of a list when installing new software.
This can be a pretty big headache from a programmer's perspective, since
different computers can behave very differently! Just ask a web developer how
much fun it is to write websites that work in both firefox and Internet
Explorer!

Java became popular in the early-mid 1990s because of its "write once, run
anywhere" mantra. Java is cross-platform, which means that programmers only
have to write one version of their program, and it will run on any computer
that has Java. That might not sound very impressive to us 20 years later, but
back then that was a huge leap forward!

A programmer could write a Java program and then package it up in one of the
following ways:

  * Runnable jar: A runnable jar is a lot like a .zip file, except the end-user can double-click a jar to run the Java program it contains.
  * Applet: An applet is embedded in a website. The user just visits the website to run the applet.
  * Web Start Application: A web start application is a bit of a mix between a runnable jar and an applet. Web start applications are generally in the form of a button on a website. Unlike an applet that is embedded directly in a website, a web start application pops up in its own window when the user clicks the button.

### The Sandbox

This is where things start to confuse people. Programming languages can be
very powerful, and malicious programs can do things like delete or copy your
hard drive, or install viruses or keyloggers. This makes it important for the
end user to understand what's going on- this is why most people know not to
run executable files attached to shady emails!

However, you have very little control over what happens on the internet- one
wrong click could send you to a malicious website. Html by itself can't do
much, but Java can be embedded in that html in the form of an applet.

That's why all Java applets (and webstarts) are sandboxed. This is just
another way of saying that Java limits what an applet can do- applets can't
access your hard drive or connect to other servers, for example. That way,
even if you do stumble onto a malicious website, that website can't use Java
against you.

Or so we thought.

### Valid Reasons to Escape the Sandbox

By default, applets and webstarts are not allowed to access things like the
hard drive. However, there are many valid reasons for an applet to need to
work "outside the sandbox"- you might want an applet to save or load a file,
or you might want your game to use your graphics card to run faster.

A programmer can ask a user for extended permissions by signing the applet.
This causes the applet to show a dialog to the user asking for permission to
run outside the sandbox. The user can still say no, so they are still in
control. An applet running outside the sandbox is called a privileged applet,
since the user has to grant privileges to access system resources.

This actually worked pretty well for quite a long time. In fact, one of the
reasons the next section was such big news was because of how good Java's
security was, especially compared to languages like flash.

### Escaping the Sandbox

Then during the spring of 2012, an exploit was found that gave malicious
programmers (the media might hype them up as "hackers") the ability to run
privileged applets without first prompting the user for permission.

This was a big deal, because any website could do things like modify the hard
drive or lock down the system, and the user would not have a good way to track
down the cause (compared to the security prompt that should have displayed).
Even advertisements could contain a malicious applet, making it even more
difficult. Several viruses spread through this applet exploit- one popular
virus locked down the user's system and displayed a scary-looking warning from
the FBI telling users to enter their credit card information to get rid of the
warning. The message wasn't actually from the FBI, and entering your credit
card information did not get rid of the message, it only gave thiefs access to
your credit card.

### What the Exploit Wasn't

This exploit was bad news. However, many people who didn't understand the
exploit reacted by declaring Java as a whole insecure, which simply isn't
true. This exploit was bad because Java can be embedded in a website, and
websites make it hard to be sure where code is coming from (picture a website
with 4 advertisements all running code from different places).

However, only a few Java applications are deployed as applets (stuff like code
examples, academic stuff, and games work well as applets). Most applications
are deployed either as runnable jars (mentioned above) or as packaged
executables (mentioned later), and this exploit did not make those
applications any less secure. Similarly, a lot of Java code is run on servers
(including this website!), and this exploit did not affect that code at all.

So, yes, the exploit was bad. But it doesn't mean that Java itself is
insecure, just that applets can be abused to do bad things.

### Oracle's Reaction

After these exploits were made public, Oracle released a patch fixing the
problem. But as soon as Oracle could fix one problem, malicious programmers
would find another exploit, starting a back-and-forth that continued for
almost two years.

Over the course of those 2 years, Oracle has made significant changes to how
applets and webstarts work. For example, Java now prompts the user when ANY
applet is run, including applets that are completely sandboxed, and other
settings can completely disable Java in internet browsers if the user so
chooses.

These changes have thwarted most of the malicious uses of applets, but they've
also added a bunch of annoying and scary warnings to perfectly innocent
applets.

### Update 51

In January of 2014, Oracle released update 51 for Java, which contained
another major change to how Java works on the internet. As of this update, by
default, applets will only work if the programmer has paid for a certificate.
These certificates can be pretty expensive and can be revoked if they are
abused. This makes it even harder for malicious programmers to abuse Java, but
it also makes it almost impossible for novices and students to deploy their
programs as applets.

The rest of the deployment tutorial contains other options for deploying Java
(and Processing).

###  Next: [Java Security Settings](JavaSecuritySettings.jsp)
