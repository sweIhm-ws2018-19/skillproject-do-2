package simplebarkeeper.handlers;

import static com.amazon.ask.request.Predicates.requestType;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import simplebarkeeper.States;

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
        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();
        sessionAttributes.put(States.STATE_KEY, States.DEFAULT);
        attributesManager.setSessionAttributes(sessionAttributes);
        
        String welcome = "Hallo. Wie kann ich dir behilflich sein?";
        String repromptMessage = "Falls du Hilfe brauchst, sag einfach: hilf mir";
        Map<String, Object> attributes = input.getAttributesManager().getSessionAttributes();
        attributes.put("HelpState", "HelpStateDefault");
        return input.getResponseBuilder()
        		.withSpeech(welcome)
                .withReprompt(repromptMessage).build();
    }

}
