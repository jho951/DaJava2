package com.dajava.application.event.scheduler.htmlparser.state;

import com.dajava.application.event.scheduler.htmlparser.HtmlParserContext;

public interface ParserState {
	void handle(HtmlParserContext context);
}
