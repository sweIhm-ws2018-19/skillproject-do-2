package simplebarkeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

/**
 * Handler for the HelpIntent. It gives the user a bunch of information he might
 * need if needed.
 * 
 * @author Xaver Siodlazek
 *
 */
public class HelpDeleteIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		Map<String, Object> attributes = input.getAttributesManager().getSessionAttributes();
		return input.matches(intentName("AMAZON.HelpIntent")) && attributes.get("HelpState").equals("HelpStateLoeschen");
	}

	/**
	 * {@inheritDoc}} Method that implements the logic on how the HelpIntent has to
	 * be handled. Here, it simply returns a TextOutput.
	 */
	@Override
	public Optional<Response> handle(HandlerInput input) {
		String outputText = "Das Löschen eines Drinks muss über folgenden Befehl ausgeführt werden ...";
		Map<String, Object> attributes = input.getAttributesManager().getSessionAttributes();
		attributes.put("HelpState", "HelpStateDefault");
		input.getAttributesManager().setSessionAttributes(attributes);
		
		return input.getResponseBuilder().withSpeech(outputText).withShouldEndSession(false).build();
	}

}
