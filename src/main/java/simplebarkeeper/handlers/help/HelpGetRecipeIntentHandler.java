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
public class HelpGetRecipeIntentHandler implements RequestHandler {
	
	/**
	 * Checks if the correct Handler is used.
	 */
	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("HelpGetRecipeIntent"));
	}

	/**
	 * Outputs a response that should help the user on the regarding function.
	 */
	@Override
	public Optional<Response> handle(HandlerInput input) {
		StringBuilder outputText = new StringBuilder();
		outputText.append("Um das Rezept eines Drinks abzurufen, sag einfach: ")
				.append("Wie lautet das Rezept eines BeispielDrinks?")
				.append("Um zum nächsten Schritt zu gelangen, sage einfach weiter.")
				.append(" Um zum vorigen Schritt zu gelangen, sage zurück. Um einen schritt zu wiederholen, sag: nochmal");
		return input.getResponseBuilder().withSpeech(outputText.toString()).withShouldEndSession(false).build();
	}

}
