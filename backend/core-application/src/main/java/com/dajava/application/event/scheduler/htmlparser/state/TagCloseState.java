package com.dajava.application.event.scheduler.htmlparser.state;

import com.dajava.application.event.scheduler.htmlparser.HtmlParserContext;

public class TagCloseState implements ParserState {
	@Override
	public void handle(HtmlParserContext context) {
		context.match("</" + context.currentNode.tagName + ">");
		context.nodeStack.pop();
		context.currentState = new InitState();
	}
}