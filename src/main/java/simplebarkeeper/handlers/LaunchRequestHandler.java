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
     * (Also: the code in comments is to be implemented, and is, in theory, working code. Permission-wise there
     * are still problems, but that is to be fixed in one of the next sprints).
     */
    @Override
    public Optional<Response> handle(HandlerInput input) {
        String welcome = "Hallo. Wie kann ich dir behilflich sein?";
        String repromptMessage = "Falls du Hilfe brauchst, sag einfach 'Hilf mir'";

        return input.getResponseBuilder()
        		.withSimpleCard("Willkommen", welcome)
        		.withSpeech(welcome)
                .withReprompt(repromptMessage).build();
    }

}
