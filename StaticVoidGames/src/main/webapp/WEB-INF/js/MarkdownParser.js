function parseMarkdown(){
	var converter =  new Markdown.getSanitizingConverter();
	$( ".markdown" ).each(function() {
		var markdown = $(this).text();		
		var parsedHtml = converter.makeHtml(markdown);		
		$(this).html(parsedHtml);
	});
}
$(parseMarkdown);