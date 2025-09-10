package com.dajava.api.domain.event.es.scheduler.vaildation.htmlparser.state;

import com.dajava.api.domain.event.es.scheduler.vaildation.htmlparser.HtmlNode;
import com.dajava.api.domain.event.es.scheduler.vaildation.htmlparser.HtmlParserContext;

public class TextNodeState implements ParserState {
	@Override
	public void handle(HtmlParserContext context) {
		StringBuilder text = new StringBuilder();
		while (context.hasNext() && context.peek() != '<') {
			text.append(context.consume());
		}

		String content = text.toString().trim();
		if (!content.isEmpty()) {
			HtmlNode node = new HtmlNode();
			node.textContent = content;
			context.nodeStack.peek().children.add(node);
		}

		context.currentState = new InitState();
	}
}