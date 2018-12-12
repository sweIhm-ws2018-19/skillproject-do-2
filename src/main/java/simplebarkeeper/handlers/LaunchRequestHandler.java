package simplebarkeeper.handlers;

import static com.amazon.ask.request.Predicates.requestType;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

/**
 * Class that handles the Launch Request. It also implements any first time initialization
 * of DataBases. Furthermore it welcomes the User.
 * @author Robin Grellner
 *
 */
public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    /**
     * {@inheritDoc}}
     * The Method handles everything that needs to happen at the Launch of the Skill
     * and welcomes the user.
     */
    @Override
    public Optional<Response> handle(HandlerInput input) {
        
        String welcome = "Dein Barkeeper heißt dich herzlich Wilkommen! Wie kann ich dir behilflich sein?";
        String repromptMessage = "Falls du Hilfe brauchen solltest, sag einfach: Hilf mir";
      
        return input.getResponseBuilder()
        		.withSpeech(welcome)
                .withReprompt(repromptMessage).build();
    }

}
