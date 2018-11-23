package main.java.handlers;

import static com.amazon.ask.request.Predicates.requestType;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

public class LaunchRequestHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(requestType(LaunchRequest.class));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String welcome = "Hallo. Wie kann ich dir behilflich sein?";
		String repromptMessage = "Falls du Hilfe brauchst, sag einfach 'Hilf mir'";

		return input.getResponseBuilder().withSimpleCard("Willkommen", welcome).withSpeech(welcome)
				.withReprompt(repromptMessage).build();
	}

}
