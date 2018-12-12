package simplebarkeeper.handlers.help;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

/**
 * Class that Handles the Help Response for the GetFavourite function.
 * 
 * @author Robin Grellner
 *
 */
public class HelpGetFavouriteIntentHandler implements RequestHandler {
	/**
	 * Checks if the correct Handler is used.
	 */
	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("HelpGetFavouriteIntent"));
	}

	/**
	 * Outputs a response that should help the user on the regarding function.
	 */
	@Override
	public Optional<Response> handle(HandlerInput input) {
		StringBuilder outputText = new StringBuilder();
		outputText.append("Um zu erfahren welchen Drink du als Favoriten abgespeichert hast sage einfach: ").append(
				"Was ist mein Favorit?");
		return input.getResponseBuilder().withSpeech(outputText.toString()).withShouldEndSession(false).build();
	}

}
