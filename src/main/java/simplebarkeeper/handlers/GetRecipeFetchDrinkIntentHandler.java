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
import simplebarkeeper.ListOfDrinks;
import simplebarkeeper.Slots;
import simplebarkeeper.States;

public class GetRecipeFetchDrinkIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		AttributesManager attributesManager = input.getAttributesManager();
		Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();
		return input.matches(intentName("GetRecipeIntent"))
				&& (sessionAttributes.get(States.GET_RECIPE_STATE_KEY).equals(States.GET_RECIPE_FETCH_DRINK));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		AttributesManager attributesManager = input.getAttributesManager();
		Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();

		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slots = intent.getSlots();

		Slot slot = slots.get(Slots.NAME_SLOT);

		String userInput = slot.getValue();

		ListOfDrinks drinkList = new ListOfDrinks();
		Drink drink = drinkList.getDrink(userInput);
		if(drink == null) {
			String speechText = "Diesen Drink kenne ich leider nicht, versuche es mit einem anderen Drink nochmal!";
			sessionAttributes.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_FETCH_DRINK);
			attributesManager.setSessionAttributes(sessionAttributes);
			return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
		}
		List<String> steps = drink.getSteps();
		StringBuilder sb = new StringBuilder();
		String speechText = sb.append("Hier das Rezept: ").append(steps.get(0)).toString();

		sessionAttributes.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_MENU);
		sessionAttributes.put(States.GET_RECIPE_DRINK_KEY, drinkList.getDrink(userInput));
		sessionAttributes.put(States.GET_RECIPE_STEP_COUNTER_KEY, 0);
		attributesManager.setSessionAttributes(sessionAttributes);

		return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
	}

}