package simplebarkeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

/**
 * Handler for the FallbackIntent. If something is misunderstood or not available,
 * it informs the user of a bad request.
 * @author Robin Grellner
 *
 */
public class FallbackIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.FallbackIntent"));
    }

    
    /**
   	 * {@inheritDoc}}
   	 * Method that implements the logic of the FallbackIntent. 
   	 * It provides the User with a message about the failure of
   	 * trying to do something he should not do.
   	 */
    @Override
    public Optional<Response> handle(HandlerInput input) {
        String outputText = "Tut mir leid, ich habe dich nicht verstanden. Bei Fragen steht dir die Hilfe gerne zur Verfügung";
        return input.getResponseBuilder().withSpeech(outputText).withSimpleCard("Barabend", outputText)
                .withReprompt(outputText).build();
    }
}
