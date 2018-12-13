package simplebarkeeper.handlers.help;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

/**
 * Class that Handles the Help Response for the ListIngredients function.
 * 
 * @author Robin Grellner
 *
 */
public class HelpListIngredientsIntentHandler implements RequestHandler {
	/**
	 * Checks if the correct Handler is used.
	 */
	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("HelpListIngredientsIntent"));
	}

	/**
	 * Outputs a response that should help the user on the regarding function.
	 */
	@Override
	public Optional<Response> handle(HandlerInput input) {
		StringBuilder outputText = new StringBuilder();
		outputText.append("Um die Zutaten eines Drinks zu erfahren, sage einfach: ").append(
				"Gib mir die Zutaten von ... und dann den Namen des Drinks zu dem du die Zutaten haben möchtest");
		return input.getResponseBuilder().withSpeech(outputText.toString()).withShouldEndSession(false).build();
	}

}
