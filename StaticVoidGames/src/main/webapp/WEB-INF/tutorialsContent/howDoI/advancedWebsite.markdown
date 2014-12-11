##How do I build an advanced website that includes things like registered users?

Most modern websites allow users to have their own profiles, upload their own content, interact with each other, etc.

This kind of website consists of a couple different parts: a database that stores user information, a server that interacts with that database and **serves** the user html, and the actual html that the user sees.

A "real" company that wants to build this kind of website will probably consits of an entire team, with different people handling the different parts: you might have a person in charge of the database, a person in charge of the server, and a person in charge of the html. Bigger companies have entire teams of people working on each separate piece!

But assuming you're a single individual looking to start your own website, here's how I would break it down:

###Learn the Basics

If you've never created a website before, you should go back and learn the basics: how to write html and css, how to use a host, and how to register a domain that points at that host.

###Get a Database

The first step to a website like this is to get a database. There are a ton of options out there. Googling something like "database hosting" returns a bunch of results.

There are a ton of different types of database, and a ton of different places that will host it for you. I personally use HostGator (the same place I get my basic hosting from), but that doesn't necessarily make it the **best** option for you.

###Learn SQL

You need to know the basics of how a database works (you should know what a table is, what a primary key is, how data is retrieved, how data is updated, etc), and the best way to learn that is by learning SQL. This was the hardest part for me, but some people really like it.

You don't have to become an expert, but you need to understand how data is organized in a database.

As always, [W3Schools](http://www.w3schools.com/sql/default.asp) is a great place to learn SQL.

###Learn ORM

You don't have to become a SQL expert to interact with a database. If you're already familiar with a programming language, you can get around using SQL by using something called **Object Relational Mapping**, or ORM.

ORM allows you to write code in your native programming language that interacts with a database without worrying about SQL. But even then, you need to know the basics of how a database works. 

[Here](http://en.wikipedia.org/wiki/List_of_object-relational_mapping_software) is a list of ORM options for different languages, but you can google for more. For example, if you're a Java programmer, try googling "Java Object Relational Mapping".

I personally use JPA (through Spring) for Static Void Games, but I've also used Hibernate.

###Get a Server

This is the "meat and potatoes" of a website. It's what connects the database to the stuff the user actually sees. A server interacts with a database to **serve** content to a specific user. Compare that to a basic website, where every visitor sees the same content.

For example, when you visit a particular game on Static Void Games, you're telling the Static Void Games server to look in the database for that game. The server then creates html that includes the game's description, thumbnail, etc. Then the server sends the html to that user, and the html is displayed.

The server does this by running **server-side applications**. Just like client-side applications, server-side applications can be written in a bunch of different langauges.

Which language you choose depends on what you already know. I personally use Java, but other options include PHP and server-side JavaScript. It really depends on your preferences.

Finally, which server you choose depends on what language you want to use: different servers support different types of server-side languages. I personally use Amazon Web Services, but as always, Google is your friend.

Your server host will include instructions on how to deploy your server-side application and point your domain to your server. The rest is up to you!

###Copy Me

Static Void Games is a Spring application running on AWS, and all of its code is available [here](https://github.com/KevinWorkman/StaticVoidGames). I'm not saying my way is the best way, but seeing how Static Void Games works might help you understand the basics.
