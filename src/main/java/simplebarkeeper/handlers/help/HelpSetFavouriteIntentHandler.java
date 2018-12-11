package simplebarkeeper.handlers.help;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

/**
 * Class that Handles the Help Response for the SetFavourite function.
 * 
 * @author Robin Grellner
 *
 */
public class HelpSetFavouriteIntentHandler implements RequestHandler {
	/**
	 * Checks if the correct Handler is used.
	 */
	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("SetFavouriteIntent"));
	}

	/**
	 * Outputs a response that should help the user on the regarding function.
	 */
	@Override
	public Optional<Response> handle(HandlerInput input) {
		StringBuilder outputText = new StringBuilder();
		outputText.append("Um einen Drink als Favoriten abzuspeichern, sag einfach: ").append(
				"Speicher bitte den Drink BeispielDrink als Favoriten ab!");
		return input.getResponseBuilder().withSpeech(outputText.toString()).withShouldEndSession(false).build();
	}

}
