package simplebarkeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import simplebarkeeper.Slots;

public class GetFavouriteIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("GetFavouriteIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		AttributesManager attributesManager = input.getAttributesManager();
		StringBuilder sb = new StringBuilder();
		String speechText;
		
		Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
		if(!persistentAttributes.containsKey(Slots.FAVOURITE_SLOT)) {
			speechText = "Es ist noch kein Favorit abgespeichert";
			return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
		}
		String drinkName = (String) persistentAttributes.get(Slots.FAVOURITE_SLOT);
		
		speechText = sb.append("Dein Lieblingsdrink ist ").append(drinkName).toString();
		
		return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
		
	}
}
