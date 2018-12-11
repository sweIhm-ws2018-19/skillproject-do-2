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
		StringBuilder sr = new StringBuilder();
		String speechText = sr
				.append("Ich freue mich Ihnen weiterhelfen zu dürfen. Um diesen Skill nutzen zu können, sollten Sie ")
				.append("mit den Funktionalitäten des Skills vertraut sein.")
				.append("Diese sind: Favorit hinzufügen, Favorit abfragen, Rezept vorlesen und Zutaten abfragen.")
				.append("Um ein Rezept zu einem Drink abzurufen, sagen sie Rezept vorlesen.")
				.append("Um ihren Favoriten abzurufen, sagen sie was ist mein Favorit.")
				.append("Um einene Drink als favoriten abzuspeichern, sagen sie, Mein Favorit ist Beispieldrink.")
				.append("Um die Zutaten eines Drinks abzufragen, sagen sie Was ist in einem Beispieldrink.")
				.toString();

		Map<String, Object> attributes = input.getAttributesManager().getSessionAttributes();
		attributes.put("HelpState", "HelpStateWait");
		input.getAttributesManager().setSessionAttributes(attributes);

		return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
	}

}
