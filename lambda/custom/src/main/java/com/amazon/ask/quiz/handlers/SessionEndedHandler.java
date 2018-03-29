package com.amazon.ask.quiz.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.SessionEndedRequest;
import org.slf4j.Logger;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;
import static org.slf4j.LoggerFactory.getLogger;

public class SessionEndedHandler implements RequestHandler {

    private static Logger LOG = getLogger(SessionEndedHandler.class);

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(SessionEndedRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        SessionEndedRequest sessionEndedRequest = (SessionEndedRequest) input.getRequestEnvelope().getRequest();
        LOG.debug("Session ended with reason: " + sessionEndedRequest.getReason().toString());
        return Optional.empty();
    }

}
