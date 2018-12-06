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

import simplebarkeeper.Attributes;
import simplebarkeeper.Slots;
import simplebarkeeper.States;

public class AddDrinkContainsAlcoholIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();
        return input.matches(intentName("AddDrinkIntent"))
                && sessionAttributes.get(States.STATE_KEY).equals(States.ADD_DRINK_CONTAINS_ALCOHOL);
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();

        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        Slot slot = slots.get(Slots.CONTAINS_ALCOHOL_SLOT);
        String userInput = slot.getValue();

        sessionAttributes.put(Attributes.NEW_CONTAINS_ALCOHOL_KEY, userInput);
        sessionAttributes.put(States.STATE_KEY, States.ADD_DRINK_INGREDIENTS);
        attributesManager.setSessionAttributes(sessionAttributes);

        StringBuilder sb = new StringBuilder();
        String speechText = sb.append("Ich habe ").append(userInput).append(" verstanden?")
                .toString();

        return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
    }
}
