package com.dajava.api.domain.event.es.scheduler.vaildation.htmlparser.state;

import com.dajava.api.domain.event.es.scheduler.vaildation.htmlparser.HtmlParserContext;

public class TagCloseState implements ParserState {
	@Override
	public void handle(HtmlParserContext context) {
		context.match("</" + context.currentNode.tagName + ">");
		context.nodeStack.pop();
		context.currentState = new InitState();
	}
}