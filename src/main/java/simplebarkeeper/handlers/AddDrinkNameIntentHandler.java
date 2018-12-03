package simplebarkeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.model.services.monetization.InSkillProductsResponse;
import com.fasterxml.jackson.core.format.InputAccessor;

public class AddDrinkNameIntentHandler implements RequestHandler {
    public static final String NAME_KEY = "NEW_DRINK_NAME";
    public static final String NAME_SLOT = "newDrinkName";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AddDrinkNameIntentHandler"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        
        Handl

        Slot requestedDrinkSlot = slots.get(NAME_SLOT);
        String newDrinkName = requestedDrinkSlot.getValue();

        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();

        sessionAttributes.put(NAME_KEY, newDrinkName);
        attributesManager.setSessionAttributes(sessionAttributes);

        StringBuilder sb = new StringBuilder();
        String speechText = sb.append("Ich habe ").append(newDrinkName).append(" verstanden").toString();

        return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
    }

}
