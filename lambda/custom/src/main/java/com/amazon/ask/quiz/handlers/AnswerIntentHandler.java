package com.amazon.ask.quiz.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.quiz.model.Attributes;
import com.amazon.ask.quiz.model.Constants;
import com.amazon.ask.quiz.model.StateProperty;
import com.amazon.ask.quiz.model.State;
import com.amazon.ask.quiz.util.QuestionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static com.amazon.ask.quiz.util.QuestionUtils.getPropertyOfState;
import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;

public class AnswerIntentHandler implements RequestHandler {

    private static final Random RANDOM = new Random();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AnswerIntent").and(sessionAttribute(Attributes.STATE_KEY, Attributes.QUIZ_STATE)));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();

        String responseText;
        String speechOutput;

        Map<String, String> quizItem = (LinkedHashMap<String, String>)sessionAttributes.get(Attributes.QUIZ_ITEM_KEY);
        State state = MAPPER.convertValue(quizItem, State.class);

        StateProperty stateProperty = StateProperty.valueOf((String) sessionAttributes.get(Attributes.QUIZ_PROPERTY_KEY));
        int counter = (int) sessionAttributes.get(Attributes.COUNTER_KEY);
        int quizScore = (int) sessionAttributes.get(Attributes.QUIZ_SCORE_KEY);

        IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
        boolean correct = compareSlots(intentRequest.getIntent().getSlots(), getPropertyOfState(stateProperty, state));

        if (correct) {
            quizScore++;
            responseText = getSpeechCon(true);
            sessionAttributes.put(Attributes.QUIZ_SCORE_KEY, quizScore);
        } else {
            responseText = getSpeechCon(false);
        }

        responseText += getAnswerText(stateProperty, state);

        if (counter < 10) {
            responseText += "Your current score is " + quizScore + " out of " + counter + ". ";
            sessionAttributes.put(Attributes.RESPONSE_KEY, responseText);
            return QuestionUtils.generateQuestion(input);
        } else {
            responseText += "Your final score is " + quizScore + " out of " + counter + ". ";
            speechOutput = responseText + " " + Constants.EXIT_SKILL_MESSAGE;
            return input.getResponseBuilder()
                    .withSpeech(speechOutput)
                    .withShouldEndSession(true)
                    .build();
        }
    }

    private String getAnswerText(StateProperty stateProperty, State state) {
        switch(stateProperty) {
            case ABBREVIATION:
                return "The " + stateProperty.getValue() + " of " + state.getName() + " is <say-as interpret-as='spell-out'>" + getPropertyOfState(stateProperty, state) + "</say-as>. ";
            default:
                return "The " + stateProperty.getValue() + " of " + state.getName() + " is " + getPropertyOfState(stateProperty, state) + ". ";
        }
    }

    private String getSpeechCon(boolean correct) {
        if (correct) {
            return "<say-as interpret-as='interjection'>" + getRandomItem(Constants.CORRECT_RESPONSES) + "! </say-as><break strength='strong'/>";
        } else {
            return "<say-as interpret-as='interjection'>" + getRandomItem(Constants.INCORRECT_RESPONSES) + " </say-as><break strength='strong'/>";
        }
    }

    private <T> T getRandomItem(List<T> list) {
        return list.get(RANDOM.nextInt(list.size()));
    }

    private boolean compareSlots(Map<String, Slot> slots, String correctAnswer) {
        for (Slot slot : slots.values()) {
            if (slot.getValue() != null && slot.getValue().toLowerCase().equals(correctAnswer.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
