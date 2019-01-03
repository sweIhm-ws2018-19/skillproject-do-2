package simplebarkeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.List;
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

import simplebarkeeper.Drink;
import simplebarkeeper.Slots;
import simplebarkeeper.States;

public class GetRecipeMenuIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		AttributesManager attributesManager = input.getAttributesManager();
		Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();
		return input.matches(intentName("GetRecipeIntent"))
				&& (sessionAttributes.get(States.GET_RECIPE_STATE_KEY).equals(States.GET_RECIPE_MENU));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		AttributesManager attributesManager = input.getAttributesManager();
		Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();
		String speechText = "Da ist wohl etwas falsch gelaufen";
		int counter = (int) sessionAttributes.get(States.GET_RECIPE_STEP_COUNTER_KEY);
		Drink drink = (Drink) sessionAttributes.get(States.GET_RECIPE_DRINK_KEY);
		List<String> steps = drink.getSteps();

		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slots = intent.getSlots();

		Slot slot = slots.get(Slots.GET_RECIPE_MENU_SLOT);

		String userInput = slot.getValue();

		if (userInput == null) {
			speechText = "Ich habe dich leider nicht verstanden.";
			return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
		}

		if (userInput.contains("weiter")) {
			counter = counter + 1;
			if (counter >= steps.size()) {
				speechText = "Cheers!";
				sessionAttributes.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_DEFAULT);
				sessionAttributes.put(States.GET_RECIPE_DRINK_KEY, null);
				sessionAttributes.put(States.GET_RECIPE_STEP_COUNTER_KEY, 0);
				attributesManager.setSessionAttributes(sessionAttributes);
				return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
			}
			speechText = steps.get(counter);

			sessionAttributes.put(States.GET_RECIPE_STEP_COUNTER_KEY, counter);
			attributesManager.setSessionAttributes(sessionAttributes);
		} else if (userInput.contains("zurück")) {
			if (counter == 0) {
				speechText = "Du bist bereits beim ersten Schritt!";
				return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
			}
			counter = counter - 1;
			speechText = steps.get(counter);

			sessionAttributes.put(States.GET_RECIPE_STEP_COUNTER_KEY, counter);
			attributesManager.setSessionAttributes(sessionAttributes);
		} else if (userInput.contains("nochmal") || userInput.contains("nocheinmal")
				|| userInput.contains("wiederholen")) {
			speechText = steps.get(counter);
		} else if (userInput.contains("stop") || userInput.contains("abbrechen")) {
			speechText = "Okay, dann vielleicht ja später!";
			sessionAttributes.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_DEFAULT);
			sessionAttributes.put(States.GET_RECIPE_DRINK_KEY, null);
			sessionAttributes.put(States.GET_RECIPE_STEP_COUNTER_KEY, 0);
			attributesManager.setSessionAttributes(sessionAttributes);
			return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
		}

		return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
	}

}