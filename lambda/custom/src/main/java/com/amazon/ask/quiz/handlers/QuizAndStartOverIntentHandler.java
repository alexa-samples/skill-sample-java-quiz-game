package com.amazon.ask.quiz.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.quiz.model.Attributes;
import com.amazon.ask.quiz.util.QuestionUtils;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;

public class QuizAndStartOverIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("QuizIntent").and(sessionAttribute(Attributes.STATE_KEY, Attributes.QUIZ_STATE).negate()))
                || input.matches(intentName("AMAZON.StartOverIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        sessionAttributes.put(Attributes.STATE_KEY, Attributes.QUIZ_STATE);
        sessionAttributes.put(Attributes.RESPONSE_KEY, "");
        sessionAttributes.put(Attributes.COUNTER_KEY, 0);
        sessionAttributes.put(Attributes.QUIZ_SCORE_KEY, 0);

        return QuestionUtils.generateQuestion(input);
    }

}
