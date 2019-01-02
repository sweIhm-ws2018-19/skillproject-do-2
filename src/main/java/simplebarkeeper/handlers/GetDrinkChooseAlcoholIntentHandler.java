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

import simplebarkeeper.Slots;
import simplebarkeeper.States;

public class GetDrinkChooseAlcoholIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		AttributesManager attributesManager = input.getAttributesManager();
		Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();
		return input.matches(intentName("GetDrinkIntent"))
				&& (sessionAttributes.get(States.GET_DRINK_STATE_KEY).equals(States.GET_DRINK_CONTAINS_ALCOHOL));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		AttributesManager attributesManager = input.getAttributesManager();
		Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();

		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slots = intent.getSlots();

		Slot slot = slots.get(Slots.GET_DRINK_CONTAINS_ALCOHOL_SLOT);

		String userInput = slot.getValue();
		if(userInput == null) {
			String speechText = "Tut mir leid, ich habe dich nicht verstanden!";
			sessionAttributes.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_DEFAULT);
			attributesManager.setSessionAttributes(sessionAttributes);

			return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
		}
		userInput = userInput.toLowerCase();

		String speechText = "Ich habe dich leider nicht verstanden.";
		if (userInput.equals("ja") || userInput.equals("der drink enthält alkohol")) {
			speechText = "Willst du einen Drink nach Geschmack, Zufall oder Zutat?";
			sessionAttributes.put(States.GET_DRINK_CONTAINS_ALCOHOL_KEY, "true");
		} else if (userInput.equals("nein") || userInput.equals("der drink enthält keinen alkohol")) {
			speechText = "Willst du einen Drink nach Geschmack, Zufall oder Zutat?";
			sessionAttributes.put(States.GET_DRINK_CONTAINS_ALCOHOL_KEY, "false");
		} else if (userInput.equals("stop") || userInput.equals("abbrechen")) {
			speechText = "Ok, vielleicht ja später!";
			sessionAttributes.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_DEFAULT);
			attributesManager.setSessionAttributes(sessionAttributes);

			return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
		}

		sessionAttributes.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_CHOOSE_OPTION);
		attributesManager.setSessionAttributes(sessionAttributes);

		return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
	}

}