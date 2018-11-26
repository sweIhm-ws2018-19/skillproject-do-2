package main.java.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;


public class HelpIntentHandler implements RequestHandler{

	@Override
	public boolean canHandle(HandlerInput input) {
		
		return input.matches(intentName("AMAZON.HelpIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String outputText = "Hier steht Hilfe";
		return input.getResponseBuilder()
				.withSpeech(outputText)
				.build();
	}

}
