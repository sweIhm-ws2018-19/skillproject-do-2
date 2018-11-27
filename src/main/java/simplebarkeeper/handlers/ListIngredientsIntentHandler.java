package simplebarkeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import simplebarkeeper.ListOfDrinks;

public class ListIngredientsIntentHandler implements RequestHandler {
    public static final String DRINK_KEY = "DRINK";
    public static final String DRINK_SLOT = "Drink";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("ListIngredientsIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        ListOfDrinks drinkList = new ListOfDrinks();
        String request = (String) input.getAttributesManager().getSessionAttributes().get(DRINK_KEY);
        String speechText = drinkList.getDrinkByName(request).listIngredients();

        return input.getResponseBuilder().withSpeech(speechText).build();
    }

}
