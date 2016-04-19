//convenience function that adds PrettyPrint to Pagedown and does setup so we can one-line it

function parseMarkdown(markdown){
	var converter =  new Markdown.getSanitizingConverter();
	converter.hooks.chain("postConversion", function (text) {
	    return text.replace(/<pre>/gi, "<pre class=prettyprint>");
	});
	var parsedHtml = converter.makeHtml(markdown);
	return parsedHtml;
}