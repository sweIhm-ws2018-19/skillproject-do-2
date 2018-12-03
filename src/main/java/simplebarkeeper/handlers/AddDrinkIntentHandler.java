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

import simplebarkeeper.ListOfDrinks;

public class RemoveDrinkIntentHandler implements RequestHandler {
    public static final String DRINK_KEY = "DRINK";
    public static final String DRINK_SLOT = "Drink";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("RemoveDrinkIntentHandler"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        Slot requestedDrinkSlot = slots.get(DRINK_SLOT);
        String requestedDrink = requestedDrinkSlot.getValue();

        ListOfDrinks drinkList = new ListOfDrinks();
        
        StringBuilder sb = new StringBuilder();
        
        if(drinkList.containsDrink(requestedDrink)) {
            sb.append(requestedDrink).append(" ist leider schon vorhanden");
        } else {
            AttributesManager attributesManager = input.getAttributesManager();
            Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();
            sessionAttributes.put("name", requestedDrink);
            attributesManager.setSessionAttributes(sessionAttributes);
            
        sb.append(requestedDrink).append("Name wurde angelegt");
        drinkList.saveListAsJson();
        }
        
        String speechText = sb.toString();
        return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
    }

}
