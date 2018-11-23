package main.java.handlers;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class CancelAndStopIntentHandler implements RequestHandler{

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("StopIntent").or(intentName("CancelIntent")));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		
		return input.getResponseBuilder().withSpeech("Beehren sie uns bald wieder!").withSimpleCard("Bar geschlossen!", "Beehren sie uns bald wieder!").build();
	}

}
