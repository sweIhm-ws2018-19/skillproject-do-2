package simplebarkeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
/**
 * Handler for both, the Cancel- and StopIntent. It let's Alexa reply "Beehren
 * sie uns bald Wieder" and shows a Card on the Screen of the App.
 * 
 * @author Robin Grellner
 *
 */
public class CancelandStopIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.StopIntent").or(intentName("AMAZON.CancelIntent")));
    }
    
    
    /**
   	 * {@inheritDoc}}
   	 * Method that implements the logic for the Cancel- and Stopintent. It simply
   	 * replies "Beehren sie uns bald wieder", showing the exact same thing on the
   	 * device Screen.
   	 */
    @Override
    public Optional<Response> handle(HandlerInput input) {

        return input.getResponseBuilder().withSpeech("Beehren sie uns bald wieder!")
                .withSimpleCard("Bar geschlossen!", "Beehren sie uns bald wieder!").build();
    }

}
