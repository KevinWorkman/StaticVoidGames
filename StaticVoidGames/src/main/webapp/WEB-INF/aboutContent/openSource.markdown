Static Void Games is now open source!

###What does that mean?

It means that **all** of the source for the website is now on GitHub. From the Java code running on the server, to the jsp creating what you see, to the actual content.. everything can now be edited by anybody.
  
###Won't that be a chaotic mess that breaks the website?

Since we're using GitHub, I still have to accept or deny any changes that people make. I'm happy to accept any changes people are interested in making, but I'll deny anything that doesn't improve the site. This is a 5-step process:
 
 - You can download a *local* copy of the website and database from GitHub. (In GitHub terms, this is called *forking*.)
 - You then make changes to that local copy, and test it on your computer. Any changes you make will only apply to your local copy, so you don't need to worry about breaking the site for anybody else.
 - When you're satisfied with your changes, you can submit them to our GitHub page. (In GitHub terms, this is called a *pull request*.)
 - I can take your pull request and test it locally on *my* local copy, still without worrying about breaking the real site.
 - Then I can either deny the pull request, ask you to make some changes (you did comment your code, didn't you?), or I can accept the pull request and finally make it official on the live site.

###Isn't that insecure for you?

Nope. I'll strip out any of the passwords and stuff I use before putting it on GitHub. People can still run the site locally using their own copy of the database, which I'll create periodically. Any changes they make will only affect their local version, so they won't break the live site. When somebody submits a change, I'll test it locally first and then accept the changes to make it official.
  
###Isn't that insecure for users of the site?

Nope. I'm also stripping out any email addresses or password hashes from the local database that people will use to run the site locally. People won't be able to get anything they can't already get from the public site. I'm also planning on implementing a way to opt out of being included in the local database.
  
###Aren't you tricking people into doing your work for you? What's in it for us?

Just like any other open-source project, this is a strictly **voluntary** offer to contribute. I figured some people might think it's cool to be able to contribute to an open-source project that they can actually use.

Since we're using GitHub, any contributions you make will be attributed to you. Contributing to an open-source project is a great way to build up your resume and to get experience working on a team. I'm also hoping to make getting involved as easy as possible, so it'll be a stepping stone to working on bigger open-source projects.
   
###Isn't it unfair of you to make money from the site, since other people will be working for free?

That's a fair question, but it's a moot point since I haven't made any money from the site. In fact, this site has cost me about 50 dollars every month for over 2 years, so it would take me a long time to make back that money! If I ever start making money from this, I'll revisit this question.
  
###What technologies do you use?
	
- On the server side, I use Amazon Elastic Beanstalk, which is an easy way to set up a Tomcat server.
- Locally, you'll have to setup your own Tomcat server.
- On top of that, I use the Spring framework to organize which code gets called when a user visits a particular URL.
- For the database, I use a mysql database hosted on HostGator. This might change in the near future.
- Locally, you'll use a Derby database so you can test any changes you make on your own computer.
- For storage of uploaded content, I use Amazon S3.
- There isn't a great way to test that locally, so if you want to experiment with uploading stuff, you'll have to setup your own Amazon S3 account.
- I use Maven for dependency management.
- To write code, I personally use Eclipse, but you can use anything you want.
	

###Under what license is Static Void Games released?

The **code** is released under a GPLv3 license. This means that you can use the code and make changes to it, but anything you do with the code has to **also** be GPLv3.

The **content** still belongs to the users who originally uploaded it and should not be modified or distributed without **their** permission.
  
###Can I use the Static Void Games code to create my own website?

Yep. One of my goals was to create a codebase that novice web developers could learn from, and you can feel free to create your own website using the techniques I use. If you do copy the code (as opposed to simply using it as an example), you must also release your code under the same open-source GPLv3 license. I'd also like to hear about any site you create from this, but that's not a license thing- I just think it would be cool to see.

However, the **content** of the site (the user-submitted games, for example) is still the property of the user who submitted it, under whatever license it was released under. You should not redistribute any of this content without the owner's permission.

###How do I get involved?

I'm hoping to make it as easy as possible to get involved. You can either dive right in on our GitHub page, or you can look at the bottom of any page on Static Void Games to see how to edit its different parts (the server code, the view layer, or the content).

You don't have to be an expert programmer to get involved. Even something as small as fixing a typo on a tutorial is extremely helpful. You could also try playing around with the html and css to make the site look better, as I am definitely not a web developer.