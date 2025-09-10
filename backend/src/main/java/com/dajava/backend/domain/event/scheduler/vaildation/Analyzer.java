package com.dajava.api.domain.event.scheduler.vaildation;

import java.util.List;

import com.dajava.api.domain.event.entity.SessionData;

public interface Analyzer<T> {
	List<T> analyze(SessionData sessionData);

}
