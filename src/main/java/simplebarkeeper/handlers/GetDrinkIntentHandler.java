package simplebarkeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import simplebarkeeper.States;

public class GetDrinkIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		AttributesManager attributesManager = input.getAttributesManager();
		Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();
		return input.matches(intentName("GetDrinkIntent"))
				&& (sessionAttributes.get(States.GET_DRINK_STATE_KEY).equals(States.GET_DRINK_DEFAULT));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String speechText = "Enthält dein Drink Alkohol?";
		
		AttributesManager attributesManager = input.getAttributesManager();
		Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();
		sessionAttributes.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_CONTAINS_ALCOHOL);
		attributesManager.setSessionAttributes(sessionAttributes);
		
		return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
	}

}
