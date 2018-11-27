package simpleBarkeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;

public class ListIngredientsIntentHandler implements RequestHandler{

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("ListIngredientsIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
//        Request request = input.getRequestEnvelope().getRequest();
//        IntentRequest intentRequest = (IntentRequest) request;
//        Intent intent = intentRequest.getIntent();
//        Map<String, Slot> slots = intent.getSlots();
//        Slot favoriteColorSlot = slots.get("Drink");
//        String drink = drink = favoriteColorSlot.getValue();
        
        
        return input.getResponseBuilder().withSpeech("Beehren sie uns bald wieder!")
                .withSimpleCard("Bar geschlossen!", "Beehren sie uns bald wieder!").build();
    }

}
