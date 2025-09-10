package com.dajava.api.domain.event.es.scheduler.vaildation.htmlparser.state;

import com.dajava.api.domain.event.es.scheduler.vaildation.htmlparser.HtmlParserContext;

public interface ParserState {
	void handle(HtmlParserContext context);
}
