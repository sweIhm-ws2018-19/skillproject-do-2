package main.java.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class FallbackIntentHandler implements RequestHandler{

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AMAZON.FallbackIntent"));
	}
	
	@Override
	public Optional<Response> handle(HandlerInput input){
		String outputText = "Tut mir leid, ich habe dich nicht verstanden. Sage einfach Hilfe für mehr Informationen.";
		return input.getResponseBuilder()
				.withSpeech(outputText)
				.withSimpleCard("Barabend", outputText)
				.withReprompt(outputText)
				.build();
	}
}
