##How to Make a Twitter Bot in Java

Twitter bots are programs that automatically interact with twitter- some post tweets, some automatically retweet certain hashtags, some give out information... and many just post spam.

Some examples of twitter bots are:

- [@everycolorbot](https://twitter.com/everycolorbot) tweets random colors
- [@OneGameAMonth](https://twitter.com/OneGameAMonth) retweets the [#1GAM](https://twitter.com/hashtag/1GAM?src=hash) hashtag
- [@factbot1](https://twitter.com/factbot1) tweets random "facts" to show how gullible people are

This tutorial goes through the basics of creating a twitter bot in Java. I'll create a real twitter bot that I'll while I write this tutorial. My bot will tweet at people using the wrong form of "your" or "you're" in their tweets.

###Step 1: Register your bot's account

Go to [twitter's signup page](https://twitter.com/signup) and register your bot's account. This will require also registering for an email for your bot!

Choose a twitter name for your bot. For this example, I'm using @WrongYourBot.

###Step 2: Include a phone number

To register for twitter's API in the next step, you have to include a mobile phone number. Either enter it after you register, or go to [your account settings](https://twitter.com/settings/add_phone) and add it after you register.

This is annoying, but you can delete your phone after you've registered for twitter's API.

###Step 3: Register your bot

To use twitter's API, you have to register your bot. 

- Go to the [twitter apps](https://apps.twitter.com/) page. 
- Click "Create new App"
- Fill out the application form- it doesn't really matter what you put here, unless you're planning on posting from other people's accounts.
- Click the "Create your Twitter application" button.
- Go to the "Keys and Access Tokens" tab.
- Click "Create my access tokens" at the bottom of that page.

You should now have 4 different values listed on that page: 

- Consumer Key (API Key)
- Consumer Secret (API Secret)
- Access Token
- Access Token Secret

Keep that page open, because you'll need those numbers in step 6.

###Step 4: Download Twitter4j

Twitter4j is a library that allows you to access Twitter's API from Java code.

- Go to the [Twitter4j download page](http://twitter4j.org/en/index.html#download)
- Download the latest stable version. At the time of this writing, that's twitter4j-4.0.4.zip
- Unzip that into a directory.

Remember where you put those files, because you'll need them in step 7!

###Step 5: Create a place for your bot's source code

Just create a folder named `MyBot`, where you can put your source code. I'm going to create a folder on my desktop.

###Step 6: Create twitter4j.properties

Create a file named `twitter4j.properties`.

Put this file in the `MyBot` directory you created in step 5. You can use any basic text editor, such as [jEdit](http://www.jedit.org/).

Go back to the `Keys and Access Tokens` tab from Step 3. Copy those values into your twitter4j.properties file, so your file looks like this:

    oauth.consumerKey=Your_Consumer_Key
    oauth.consumerSecret=Your_Consumer_Secret
    oauth.accessToken=Your_Access_Token
    oauth.accessTokenSecret=Your_Access_Token_Secret
    
This file will be used by Twitter4j to access Twitter's API using your bot's keys.

###Step 7: Copy twitter4j-core-4.0.4.jar

- In the twitter4j directory you unzipped in step 4, find `twitter4j-core-4.0.4.jar`. It should be in the `lib` directory.
- Copy that jar file into the `MyBot` directory.


###Step 8: Create a Java file

Create a file named `MyBot.java`, and put it in the directory you created in step 5.

(You can actually name it anything you want, as long as it ends with the `.java` extension!)

For starters, I'm just going create a very basic twitter bot that simply sends a single "hello world" tweet:


    import twitter4j.Status;
    import twitter4j.Twitter;
    import twitter4j.TwitterException;
    import twitter4j.TwitterFactory;

    public class MyBot{

    	//if something goes wrong, we might see a TwitterException
    	public static void main(String... args) throws TwitterException{
		
    		//access the twitter API using your twitter4j.properties file
    		Twitter twitter = TwitterFactory.getSingleton();

    		//send a tweet
    		Status status = twitter.updateStatus("hello world");
		
    		//print a message so we know when it finishes
    		System.out.println("Done.");
    	}
    }
    
###Step 9: Compile and Run

To make sure we've done everything right so far, let's compile and run our program.

- Open a command prompt in your `MyBot` directory.
- Type `javac -cp twitter4j-core-4.0.4.jar MyBot.java` and press enter to compile your program.
- When that finishes, type `java -cp "twitter4j-core-4.0.4.jar;." MyBot` and press enter to run your program.

![](http://StaticVoidGames.com/tutorialsContent/howTo/twitterBot1.png)
    
We're using the `-cp` option here to specify the Java **classpath**. In other words, we're making sure that Java knows where to find the Twitter4j library.

Go to your bot's twitter page, and you should see a [hello world tweet](https://twitter.com/WrongYourBot/status/616733994751795200). Hooray! If you don't see the tweet, go back and make sure you've followed each of the steps so far.

###Step 10: Search for tweets

From here, what you do really depends on what type of bot you're trying to create. Since I want to reply to certain kinds of messages, I need to search for tweets containing specific text.

Here's an example program that simply prints out tweets that contain "your welcome":


    import twitter4j.Query;
    import twitter4j.QueryResult;
    import twitter4j.Status;
    import twitter4j.Twitter;
    import twitter4j.TwitterException;
    import twitter4j.TwitterFactory;

    public class MyBot{

    	//if something goes wrong, we might see a TwitterException
    	public static void main(String... args) throws TwitterException{
		
    		//access the twitter API using your twitter4j.properties file
    		Twitter twitter = TwitterFactory.getSingleton();
		
    		//create a new search
    		Query query = new Query("\"your welcome\"");
		
    		//get the results from that search
    		QueryResult result = twitter.search(query);
		
    		//iterate over every search result
    		for(Status tweet : result.getTweets()){
    			//print out the tweet
    			System.out.println(tweet.getUser().getScreenName() + ":" + tweet.getText());
    		}
    	}
    }
    
Compile and run this program, and you should see a bunch of tweets that contain bad grammar.
    
###Step 11: Reply to a tweet

Now that I know how to send a tweet and how to search for tweets, I can combine them and reply to tweets!

    import twitter4j.Query;
    import twitter4j.QueryResult;
    import twitter4j.Status;
    import twitter4j.StatusUpdate;
    import twitter4j.Twitter;
    import twitter4j.TwitterException;
    import twitter4j.TwitterFactory;

    public class MyBot{

    	//if something goes wrong, we might see a TwitterException
    	public static void main(String... args) throws TwitterException{
		
    		//access the twitter API using your twitter4j.properties file
    		Twitter twitter = TwitterFactory.getSingleton();
		
    		//create a new search
    		Query query = new Query("\"your welcome\"");
		
    		//get the results from that search
    		QueryResult result = twitter.search(query);
    	
    		//get the first tweet from those results
    		Status tweetResult = result.getTweets().get(0);
    		
    		//reply to that tweet
    		StatusUpdate statusUpdate = new StatusUpdate(".@" + tweetResult.getUser().getScreenName() +" I believe you meant \"you're\" here?");
    		statusUpdate.inReplyToStatusId(tweetResult.getId());
    		Status status = twitter.updateStatus(statusUpdate);	
    		
    		System.out.println("Done.");
    	}
    }

###Step 12: Add some variety!

My bot would be pretty boring if it always sent the same tweet, so I'm going to add some variety to what it searches for and what it sends.

    import java.util.ArrayList;
    import java.util.List;

    import twitter4j.Query;
    import twitter4j.QueryResult;
    import twitter4j.Status;
    import twitter4j.StatusUpdate;
    import twitter4j.Twitter;
    import twitter4j.TwitterException;
    import twitter4j.TwitterFactory;

    public class MyBot{

    	//if something goes wrong, we might see a TwitterException
    	public static void main(String... args) throws TwitterException{
		
    		//access the twitter API using your twitter4j.properties file
    		Twitter twitter = TwitterFactory.getSingleton();
		
    		List<String> searches = new ArrayList<>();
    		searches.add("\"your welcome\"");
    		searches.add("\"your the\"");
    		searches.add("\"your a \"");
    		
    		List<String> replies = new ArrayList<>();
    		replies.add( "I believe you meant \"you're\" here?");
    		replies.add(" I've detected the wrong \"you're\". Destroy!");
    		replies.add(" No, you are! Seriously. You are. \"You're\".");
    		
    		//create a new search, chosoe from random searches
    		Query query = new Query(searches.get((int)(searches.size()*Math.random())));
		
    		//get the results from that search
    		QueryResult result = twitter.search(query);
    	
    		//get the first tweet from those results
    		Status tweetResult = result.getTweets().get(0);
    		
    		//reply to that tweet, choose from random replies
    		StatusUpdate statusUpdate = new StatusUpdate(".@" + tweetResult.getUser().getScreenName() + replies.get((int)(replies.size()*Math.random())));
    		statusUpdate.inReplyToStatusId(tweetResult.getId());
    		Status status = twitter.updateStatus(statusUpdate);	
    		
    		System.out.println("Done.");
    	}
    }
    
###Step 13: Schedule it!

I want to be able to run my bot and have it go off on its own. To do that, I simply wrap the whole thing in a `while(true)` loop and make it go to sleep for an hour after it sends a tweet:

    import java.util.ArrayList;
    import java.util.List;

    import twitter4j.Query;
    import twitter4j.QueryResult;
    import twitter4j.Status;
    import twitter4j.StatusUpdate;
    import twitter4j.Twitter;
    import twitter4j.TwitterException;
    import twitter4j.TwitterFactory;

    public class MyBot{

    	//if something goes wrong, we might see a TwitterException
    	public static void main(String... args) throws TwitterException, InterruptedException{
		
    		//access the twitter API using your twitter4j.properties file
    		Twitter twitter = TwitterFactory.getSingleton();
		
    		List<String> searches = new ArrayList<>();
    		searches.add("\"your welcome\"");
    		searches.add("\"your the\"");
    		searches.add("\"your a \"");
    		
    		List<String> replies = new ArrayList<>();
    		replies.add( "I believe you meant \"you're\" here?");
    		replies.add(" I've detected the wrong \"you're\". Destroy!");
    		replies.add(" No, you are! Seriously. You are. \"You're\".");
    		
    		//keep tweeting forever
    		while(true){
    		
    			//create a new search, chosoe from random searches
    			Query query = new Query(searches.get((int)(searches.size()*Math.random())));
		
    			//get the results from that search
    			QueryResult result = twitter.search(query);
    	
    			//get the first tweet from those results
    			Status tweetResult = result.getTweets().get(0);
    		
    			//reply to that tweet, choose from random replies
    			StatusUpdate statusUpdate = new StatusUpdate(".@" + tweetResult.getUser().getScreenName() + replies.get((int)(replies.size()*Math.random())));
    			statusUpdate.inReplyToStatusId(tweetResult.getId());
    			Status status = twitter.updateStatus(statusUpdate);	
    		
    			System.out.println("Sleeping.");
    		
    			//go to sleep for an hour
    			Thread.sleep(60*60*1000);
    		}
    	}
    }
    
The twitter API enforces [rate limits](https://dev.twitter.com/rest/public/rate-limits), which means that you can't post an unlimited number of tweets. So make sure that you include a `Thread.sleep()` between your tweets to space them out!
    
###What's next?

Now that we have a simple twitter bot, it's pretty easy to enhance it. The only limit is your imagination! Here are a few ideas you could try out:

- Explore the Twitter4j [JavaDoc](http://twitter4j.org/javadoc/) and [code examples](http://twitter4j.org/en/code-examples.html) to get an idea of some of the other stuff you can do!
- Right now our twitter bot will crash if it encounters an error (such as going over the rate limits). Add exception handling so that the bot just goes back to sleep instead of crashing.
- Find some [existing twitter bots](https://en.wikipedia.org/wiki/Twitterbot). Think about how each one of them might be implemented. Try to create a similar bot!
- You can use the Twitter4j library with Processing. Create a sketch that tweets out procedurally generated images!

