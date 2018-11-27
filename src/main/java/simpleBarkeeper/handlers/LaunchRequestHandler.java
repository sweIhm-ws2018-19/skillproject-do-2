package simpleBarkeeper.handlers;

import static com.amazon.ask.request.Predicates.requestType;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import simpleBarkeeper.ListOfDrinks;

public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String welcome = "Hallo. Wie kann ich dir behilflich sein?";
        String repromptMessage = "Falls du Hilfe brauchst, sag einfach 'Hilf mir'";
        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();

        if (!persistentAttributes.containsKey("firstStart")) {
            persistentAttributes.put("drinkList", new ListOfDrinks());
            persistentAttributes.put("firstStart", false);
            attributesManager.setPersistentAttributes(persistentAttributes);
            attributesManager.savePersistentAttributes();
        }

        return input.getResponseBuilder()
        		.withSimpleCard("Willkommen", welcome)
        		.withSpeech(welcome)
                .withReprompt(repromptMessage).build();
    }

}
