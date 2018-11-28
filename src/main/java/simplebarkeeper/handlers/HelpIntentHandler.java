package simplebarkeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

/**
* Handler for the HelpIntent. It gives the user a bunch of information he might need
* if needed.
* @author Robin Grellner
*
*/
public class HelpIntentHandler implements RequestHandler{

	@Override
	public boolean canHandle(HandlerInput input) {
		
		return input.matches(intentName("AMAZON.HelpIntent"));
	}
	
	 /**
	 * {@inheritDoc}}
	 * Method that implements the logic on how the HelpIntent has to be 
	 * handled. Here, it simply returns a TextOutput.
	 */
	@Override
	public Optional<Response> handle(HandlerInput input) {
		String outputText = "Hier steht Hilfe";
		return input.getResponseBuilder()
				.withSpeech(outputText)
				.withShouldEndSession(false)
				.build();
	}

}
