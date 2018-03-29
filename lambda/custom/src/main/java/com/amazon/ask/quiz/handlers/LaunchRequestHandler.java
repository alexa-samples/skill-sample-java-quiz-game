package com.amazon.ask.quiz.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.quiz.model.Attributes;
import com.amazon.ask.quiz.model.Constants;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;

public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        sessionAttributes.put(Attributes.STATE_KEY, Attributes.START_STATE);
        return input.getResponseBuilder()
                .withSpeech(Constants.WELCOME_MESSAGE)
                .withReprompt(Constants.HELP_MESSAGE)
                .withShouldEndSession(false)
                .build();
    }

}
