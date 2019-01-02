package simplebarkeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.time.LocalTime;
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
import simplebarkeeper.States;

public class GetDrinkChooseFlavourIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		AttributesManager attributesManager = input.getAttributesManager();
		Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();
		return input.matches(intentName("GetDrinkIntent"))
				&& (sessionAttributes.get(States.GET_DRINK_STATE_KEY).equals(States.GET_DRINK_CHOOSE_FLAVOUR));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		ListOfDrinks drinkList = new ListOfDrinks();
		AttributesManager attributesManager = input.getAttributesManager();
		Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();

		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slots = intent.getSlots();

		Slot slot = slots.get(Slots.FLAVOURS_SLOT);

		String userInput = slot.getValue();
		if(userInput == null) {
			String speechText = "Tut mir leid, ich habe dich nicht verstanden!";
			sessionAttributes.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_DEFAULT);
			attributesManager.setSessionAttributes(sessionAttributes);

			return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
		}
		userInput = userInput.toUpperCase();
		if (userInput.contains("SÜSS")) {
			userInput = "SWEET";
		} else if (userInput.contains("SAUER")) {
			userInput = "SOUR";
		} else if (userInput.contains("BITTER")) {
			userInput = "BITTER";
		}

		sessionAttributes.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_REPROMPT_FLAVOUR);
		sessionAttributes.put(States.GET_DRINK_FLAVOUR_KEY, userInput);
		attributesManager.setSessionAttributes(sessionAttributes);
		String containsAlcohol = (String) sessionAttributes.get(States.GET_DRINK_CONTAINS_ALCOHOL_KEY);
		StringBuilder sb = new StringBuilder();
		String speechText = sb.append(drinkList.getRandomDrinkByFlavour(userInput, containsAlcohol, LocalTime.now()))
				.append(". Möchtest du noch einen Vorschlag?").toString();

		return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
	}

}