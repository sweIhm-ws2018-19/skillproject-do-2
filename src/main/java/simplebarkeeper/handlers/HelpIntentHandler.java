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
 * @author Robin Grellner, Xaver Siodlazek
 *
 */
public class HelpIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		Map<String, Object> attributes = input.getAttributesManager().getSessionAttributes();
		return input.matches(intentName("AMAZON.HelpIntent")) && attributes.get("HelpState").equals("HelpStateDefault");
	}

	/**
	 * {@inheritDoc}} Method that implements the logic on how the HelpIntent has to
	 * be handled. Here, it simply returns a TextOutput.
	 */
	@Override
	public Optional<Response> handle(HandlerInput input) {
		String outputText = "Ich freue mich Ihnen weiterhelfen zu d�rfen. Um diesen Skill nutzen zu k�nnen, sollten Sie "
				+ "mit den Funktionalit�ten des Skills vertraut sein."
				+ "Diese sind: Drink eingeben, Drink l�schen, und Zutaten abfragen."
				+ "Um einen neuen Drink einzugeben sagen Sie im Hauptmen� Drink eingeben."
				+ "Um einen Drink zu l�schen, sagen Sie Drink l�schen."
				+ "Um die Zutaten eines Drinks abzufragen, sagen Sie beispielsweise Was ist in einem Radler";
		
		
		Map<String, Object> attributes = input.getAttributesManager().getSessionAttributes();
		attributes.put("HelpState", "HelpStateWait");
		input.getAttributesManager().setSessionAttributes(attributes);
		
		return input.getResponseBuilder().withSpeech(outputText).withShouldEndSession(false).build();
	}

}
