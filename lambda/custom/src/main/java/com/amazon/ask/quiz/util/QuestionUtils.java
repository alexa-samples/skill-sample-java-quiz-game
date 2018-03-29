package com.amazon.ask.quiz.util;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.quiz.model.Attributes;
import com.amazon.ask.quiz.model.StateProperty;
import com.amazon.ask.quiz.model.State;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static com.amazon.ask.quiz.model.Constants.START_QUIZ_MESSAGE;
import static com.amazon.ask.quiz.model.Constants.STATES;

public class QuestionUtils {

    private static final Random RANDOM = new Random();

    public static Optional<Response> generateQuestion(HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();

        int counter = (int)sessionAttributes.get(Attributes.COUNTER_KEY);

        if (counter == 0) {
            sessionAttributes.put(Attributes.RESPONSE_KEY, START_QUIZ_MESSAGE + " ");
        }

        counter++;
        State state = getRandomState();
        StateProperty stateProperty = getRandomProperty();

        sessionAttributes.put(Attributes.QUIZ_ITEM_KEY, state);
        sessionAttributes.put(Attributes.QUIZ_PROPERTY_KEY, stateProperty);
        sessionAttributes.put(Attributes.COUNTER_KEY, counter);

        String question = getQuestionText(counter, stateProperty, state);
        String speech = sessionAttributes.get(Attributes.RESPONSE_KEY) + question;

        return input.getResponseBuilder()
                .withSpeech(speech)
                .withReprompt(question)
                .withShouldEndSession(false)
                .build();
    }

    public static String getQuestionText(int counter, StateProperty stateProperty, State state) {
        return "Here is your " + counter + "th question.  What is the " + stateProperty.getValue() + " of "  + state.getName() + "?";
    }

    public static Optional<State> getState(Map<String, Slot> slots) {
        for (Slot slot : slots.values()) {
            String value = slot.getValue();
            for (StateProperty stateProperty : StateProperty.values()) {
                Optional<State> state = STATES.stream()
                        .filter(s -> getPropertyOfState(stateProperty, s).equals(value))
                        .findAny();
                if (state.isPresent()) {
                    return state;
                }
            }
        }
        return Optional.empty();
    }

    public static String getPropertyOfState(StateProperty stateProperty, State state) {
        switch (stateProperty) {
            case NAME:
                return state.getName();
            case ABBREVIATION:
                return state.getAbbreviation();
            case CAPITAL:
                return state.getCapital();
            case STATEHOOD_YEAR:
                return state.getStatehoodYear();
            case STATEHOOD_ORDER:
                return state.getStatehoodOrder();
        }
        throw new IllegalStateException("Invalid stateProperty");
    }

    private static State getRandomState() {
        return STATES.get(RANDOM.nextInt(STATES.size()));
    }

    private static StateProperty getRandomProperty() {
        return StateProperty.values()[RANDOM.nextInt(StateProperty.values().length -1) + 1];
    }

}
