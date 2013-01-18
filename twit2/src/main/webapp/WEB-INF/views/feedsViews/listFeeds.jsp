<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>List Feeds</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/javascript">


var tweetUsers = new Array();

<c:forEach items="${catList}" var="feed">
	tweetUsers.push("${feed.name}");
</c:forEach>



// The twitter accounts that will be included in the ticker

var buildString = "";

$(document).ready(function(){

    // After the page is loaded

    $('#twitter-ticker').slideDown('slow');
    // Show the ticker

    buildString = tweetUsers[0];

    for(var i=1;i<tweetUsers.length;i++)
    {
        buildString += "," +tweetUsers[i];
    }
    

    var fileref = document.createElement('script');
    // Creating a new script element
//https://api.twitter.com/1/users/lookup.json?screen_name=twitterapi,twitter&include_entities=true
    fileref.setAttribute("type","text/javascript");
    fileref.setAttribute("src", "https://api.twitter.com/1/users/lookup.json?screen_name="+buildString+"&include_entities&callback=TweetTick");
    // Setting its src to the search API URL; We provide TweetTick as a callback

    document.getElementsByTagName("head")[0].appendChild(fileref);
    // Appending it to the head of the page and thus executing it
});

function TweetTick(ob)
{
    //alert(ob[0].profile_image_url);
    //alert(ob[1].profile_image_url);
    // This is the callback function

    var container=$('#tweet-container');
    container.html('');
    // Removing the loading gif animation

    for(var i =0; i<ob.length;i++){
        var str = '    <div class="tweet">\
        <div class="avatar"><a href="http://twitter.com/'+ob[i].screen_name+'" target="_blank"><img src="'+ob[i].profile_image_url+'" alt="'+ob[i].screen_name+'" /></a></div>\
        <div class="user"><a href="http://twitter.com/'+ob[i].screen_name+'" target="_blank">'+ob[i].screen_name+'</a></div>\
        <div class="time">'+relativeTime(ob[i].created_at)+'</div>\
        <div class="txt">'+formatTwitString(ob[i].status.text)+'</div>\
        </div>';

        container.append(str);

    }

/*

    $(ob).each(function(el){

        // ob contains all the tweets

        var str = '    <div class="tweet">\
        <div class="avatar"><a href="http://twitter.com/'+this.from_user+'" target="_blank"><img src="'+this.profile_image_url+'" alt="'+this.from_user+'" /></a></div>\
        <div class="user"><a href="http://twitter.com/'+this.from_user+'" target="_blank">'+this.from_user+'</a></div>\
        <div class="time">'+relativeTime(this.created_at)+'</div>\
        <div class="txt">'+formatTwitString(this.text)+'</div>\
        </div>';

        container.append(str);
        // Adding the tweet to the container

    });
*/
    container.jScrollPane();
    // After all the tweets have been added, create the slidebar
}

function formatTwitString(str)
{
    // This function formats the tweet body text

    str=' '+str;

    str = str.replace(/((ftp|https?):\/\/([-\w\.]+)+(:\d+)?(\/([\w/_\.]*(\?\S+)?)?)?)/gm,'<a href="$1" target="_blank">$1</a>');
    // The tweets arrive as plain text, so we replace all the textual URLs with hyperlinks

    str = str.replace(/([^\w])\@([\w\-]+)/gm,'$1@<a href="http://twitter.com/$2" target="_blank">$2</a>');
    // Replace the mentions

    str = str.replace(/([^\w])\#([\w\-]+)/gm,'$1<a href="http://twitter.com/search?q=%23$2" target="_blank">#$2</a>');
    // Replace the hashtags

    return str;
}

function relativeTime(pastTime)
{
    // Generate a JavaScript relative time for the tweets

    var origStamp = Date.parse(pastTime);
    var curDate = new Date();
    var currentStamp = curDate.getTime();
    var difference = parseInt((currentStamp - origStamp)/1000);

    if(difference < 0) return false;

    if(difference <= 5)            return "Just now";
    if(difference <= 20)            return "Seconds ago";
    if(difference <= 60)            return "A minute ago";
    if(difference < 3600)        return parseInt(difference/60)+" minutes ago";
    if(difference <= 1.5*3600)     return "One hour ago";
    if(difference < 23.5*3600)    return Math.round(difference/3600)+" hours ago";
    if(difference < 1.5*24*3600)    return "One day ago";

    // If the tweet is older than a day, show an absolute date/time value;

    var dateArr = pastTime.split(' ');

    return dateArr[4].replace(/\:\d+$/,'')+' '+dateArr[2]+' '+dateArr[1]+
    (dateArr[3]!=curDate.getFullYear()?' '+dateArr[3]:'');
}




</script>
</head>
<body>



<section id="divHeader">
<jsp:include page="/partials/header.jsp"></jsp:include>
</section>
<section id="divContent">
<aside id="divNav"><jsp:include page="/partials/nav.jsp"></jsp:include></aside>
</section>



<div id="twitter-ticker">
<!-- Twitter container, hidden by CSS and shown if JS is present -->

    <div id="top-bar">
    <!-- This contains the title and icon -->

    <div id="twitIcon"><img src="https://twitter.com/images/resources/twitter-bird-white-on-blue.png" width="64" height="64" alt="Twitter icon" /></div>
    <!-- The twitter icon -->

    <h2 class="tut">Your Feeds:</h2>
    <!-- Title -->

    </div>

    <div id="tweet-container"></div>
    

    <div id="scroll"></div>
    
    
    <br /><br />
    
    <form:form method="POST" action="newFavorite.html">
	    <table>
		    <tr>
			    <td>
				    <form:select path="name">
				    <form:options items="${catList}" itemValue="name" itemLabel="name"/>		
				    </form:select>
			    </td>
			    <td>
			    	<input type="submit" value="Favorite Feed" />
			    </td>
		    </tr>
	    </table>
    </form:form>
    
    
       
    
    
    
    <section id="divFooter">
<jsp:include page="/partials/footer.jsp"></jsp:include>
</section>

</div>
</body>
</html>