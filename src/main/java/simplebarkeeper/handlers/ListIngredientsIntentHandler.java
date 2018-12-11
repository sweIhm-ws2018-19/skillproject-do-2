package simplebarkeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import simplebarkeeper.ListOfDrinks;
import simplebarkeeper.Slots;

/**
 * Handler for the ListIngredientsIntent. Here you receive a Drink from the
 * slots, and giving it to a ListOfDrinks. The output is a String, that lists
 * the Ingredients of the given Drink.
 * 
 * @author Robin Grellner, Felix Haala, Alex Heinritzi
 */
public class ListIngredientsIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("ListIngredientsIntent"));
	}

	/**
	 * {@inheritDoc} Method that handles how the Intent is handled. It receives a
	 * Request, containing the Name of a Drink and returns the Ingredients the
	 * specified Drink consists of.
	 */
	@Override
	public Optional<Response> handle(HandlerInput input) {
		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slots = intent.getSlots();
		Slot slot = slots.get(Slots.NAME_SLOT);
		String userInput = slot.getValue();

		ListOfDrinks drinkList = new ListOfDrinks();
		String speechText = drinkList.getIngredients(userInput);

		return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
	}

}
