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

/**
 * Handler for the HelpIntent. It gives the user a bunch of information he might
 * need if needed.
 * 
 * @author Xaver Siodlazek
 *
 */
public class HelpWaitIntentHandler implements RequestHandler {
	public static final String HELP_SLOT = "Help";
	
	@Override
	public boolean canHandle(HandlerInput input) {
		Map<String, Object> attributes = input.getAttributesManager().getSessionAttributes();
		return input.matches(intentName("AMAZON.HelpIntent")) && attributes.get("HelpState").equals("HelpStateWait");
	}

	/**
	 * {@inheritDoc}} Method that implements the logic on how the HelpIntent has to
	 * be handled. Here, it simply returns a TextOutput.
	 */
	@Override
	public Optional<Response> handle(HandlerInput input) {
		Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        Slot requestedHelpSlot = slots.get(HELP_SLOT);
        String requestedHelp = requestedHelpSlot.getValue();
        Map<String, Object> attributes = input.getAttributesManager().getSessionAttributes();
        switch(requestedHelp){
        	case "abfragen":
        		attributes.put("HelpState", "HelpStateAbfragen");
        		input.getAttributesManager().setSessionAttributes(attributes);
        		break;
        		
        	case "löschen":
        		attributes.put("HelpState", "HelpStateLoeschen");
        		input.getAttributesManager().setSessionAttributes(attributes);
        		break;
        	
        	case "eingeben":
        		attributes.put("HelpState", "HelpStateEingeben");
        		input.getAttributesManager().setSessionAttributes(attributes);
        		break;
        		
        	case "nein":
        		attributes.put("HelpState", "HelpStateDefault");
        		input.getAttributesManager().setSessionAttributes(attributes);
        		break;
        		
        }
        return input.getResponseBuilder().withShouldEndSession(false).build(); //keine Ausgabe
	}

}
