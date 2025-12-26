package com.dajava.application.event.scheduler.htmlparser.state;

import com.dajava.application.event.scheduler.htmlparser.HtmlParserContext;

public class TagBodyState implements ParserState {
	@Override
	public void handle(HtmlParserContext context) {
		if (context.html.startsWith("</" + context.currentNode.tagName, context.index)) {
			context.currentState = new TagCloseState();
		} else {
			context.currentState = new InitState(); // 파싱 계속 진행
		}
	}
}
