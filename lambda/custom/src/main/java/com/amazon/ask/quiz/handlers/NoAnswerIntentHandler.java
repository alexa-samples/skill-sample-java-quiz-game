package com.amazon.ask.quiz.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.ui.Image;
import com.amazon.ask.quiz.model.Attributes;
import com.amazon.ask.quiz.model.Constants;
import com.amazon.ask.quiz.model.State;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.quiz.util.QuestionUtils.getState;
import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;

public class NoAnswerIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AnswerIntent").and(sessionAttribute(Attributes.STATE_KEY, Attributes.QUIZ_STATE).negate()));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        sessionAttributes.put(Attributes.STATE_KEY, Attributes.START_STATE);

        IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
        Optional<State> state = getState(intentRequest.getIntent().getSlots());

        if (state.isPresent()) {
            if (Constants.USE_CARDS_FLAG) {
                Image image = Image.builder()
                        .withSmallImageUrl(getSmallImage(state.get()))
                        .withSmallImageUrl(getLargeImage(state.get()))
                        .build();
                return input.getResponseBuilder()
                        .withSpeech(getSpeechDescription(state.get()))
                        .withReprompt(Constants.REPROMPT_MESSAGE)
                        .withStandardCard(state.get().getName(), getTextDescription(state.get()), image)
                        .withShouldEndSession(false)
                        .build();

            } else {
                return input.getResponseBuilder()
                        .withSpeech(getSpeechDescription(state.get()))
                        .withReprompt(Constants.REPROMPT_MESSAGE)
                        .withShouldEndSession(false)
                        .build();
            }
        } else {
            String unknownAnswerText = "I'm sorry. That is not something I know very much about in this skill. " + Constants.HELP_MESSAGE;
            return input.getResponseBuilder()
                    .withSpeech(unknownAnswerText)
                    .withReprompt(unknownAnswerText)
                    .withShouldEndSession(false)
                    .build();
        }
    }

    private String getTextDescription(State state) {
        return "Abbreviation: " + state.getAbbreviation() + "\n"
                + "Capital: " + state.getCapital() + "\n"
                + "Statehood Year: " + state.getStatehoodYear() + "\n"
                + "Statehood Order: " + state.getStatehoodOrder();
    }

    private String getSpeechDescription(State state) {
        return state.getName() + " is the " + state.getStatehoodOrder() + "th state, admitted to the Union in "
                + state.getStatehoodYear() + ".  The capital of " + state.getStatehoodOrder() + " is " + state.getCapital()
                + ", and the abbreviation for " + state.getName() + " is <break strength='strong'/><say-as interpret-as='spell-out'>"
                + state.getAbbreviation() + "</say-as>.  I've added " + state.getName() + " to your Alexa app.  Which other state or capital would you like to know about?";
    }

    private String getSmallImage(State state) {
        return "https://m.media-amazon.com/images/G/01/mobile-apps/dex/alexa/alexa-skills-kit/tutorials/quiz-game/state_flag/720x400/" + state.getAbbreviation() + "._TTH_.png";
    }

    private String getLargeImage(State state) {
        return "https://m.media-amazon.com/images/G/01/mobile-apps/dex/alexa/alexa-skills-kit/tutorials/quiz-game/state_flag/1200x800/" + state.getAbbreviation() + "._TTH_.png";
    }

}
