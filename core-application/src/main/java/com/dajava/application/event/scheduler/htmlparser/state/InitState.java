package com.dajava.application.event.scheduler.htmlparser.state;

import com.dajava.application.event.scheduler.htmlparser.HtmlParserContext;

public class InitState implements ParserState {
	@Override
	public void handle(HtmlParserContext context) {
		context.skipWhitespace();

		if (!context.hasNext()) return;

		if (context.peek() == '<') {
			context.currentState = new TagOpenState();
		} else {
			context.currentState = new TextNodeState();
		}
	}
}
