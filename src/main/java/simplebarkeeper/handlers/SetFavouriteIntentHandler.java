package simplebarkeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import simplebarkeeper.ListOfDrinks;
import simplebarkeeper.Slots;

public class SetFavouriteIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("SetFavouriteIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		ListOfDrinks drinkList = new ListOfDrinks();
		AttributesManager attributesManager = input.getAttributesManager();
		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slots = intent.getSlots();

		Slot slot = slots.get(Slots.FAVOURITE_SLOT);

		String userInput = slot.getValue();
		Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
		String response = "Dieser Drink ist mir leider nicht bekannt.";
		if (drinkList.containsDrink(userInput)) {
			persistentAttributes.put(Slots.FAVOURITE_SLOT, userInput);
			attributesManager.setPersistentAttributes(persistentAttributes);
			attributesManager.savePersistentAttributes();

			StringBuilder sb = new StringBuilder();
			response = sb.append("Der Drink ").append(userInput).append(" wurde als dein neuer Lieblingsdrink gespeichert").toString();
		}

		return input.getResponseBuilder().withSpeech(response).withShouldEndSession(false).build();
	}
}
