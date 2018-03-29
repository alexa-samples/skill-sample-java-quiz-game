package com.amazon.ask.quiz.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.quiz.model.Attributes;
import com.amazon.ask.quiz.model.StateProperty;
import com.amazon.ask.quiz.model.State;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.quiz.util.QuestionUtils.getQuestionText;
import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;

public class RepeatIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.RepeatIntent").and(sessionAttribute(Attributes.STATE_KEY, Attributes.QUIZ_STATE)));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        int counter = (int) sessionAttributes.get(Attributes.COUNTER_KEY);
        StateProperty stateProperty = (StateProperty) sessionAttributes.get(Attributes.QUIZ_PROPERTY_KEY);
        State state = (State) sessionAttributes.get(Attributes.QUIZ_ITEM_KEY);

        String question = getQuestionText(counter, stateProperty, state);
        return input.getResponseBuilder()
                .withSpeech(question)
                .withReprompt(question)
                .withShouldEndSession(false)
                .build();
    }

}
