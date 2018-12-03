package simplebarkeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import simplebarkeeper.Ingredient;
import simplebarkeeper.ListOfDrinks;

public class AddDrinkIntentHandler implements RequestHandler {
    public static final String NAME_KEY = "NEW_DRINK_NAME";
    public static final String FLAVOUR_KEY = "NEW_DRINK_FLAVOUR";
    public static final String DAYTIME_KEY = "NEW_DRINK_DAYTIME";
    public static final String CONTAINS_ALCOHOL_KEY = "NEW_DRINK_CONTAINS_ALCOHOL";
    public static final String INGREDIENTS_KEY = "NEW_DRINK_INGREDIENTS";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AddDrinkIntentHandler"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        ListOfDrinks drinkList = new ListOfDrinks();

        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();

        String newDrinkName = (String) sessionAttributes.get(NAME_KEY);
        String newDrinkFlavour = (String) sessionAttributes.get(FLAVOUR_KEY);
        String newDrinkDaytime = (String) sessionAttributes.get(DAYTIME_KEY);
        String newDrinkContainsAlcohol = (String) sessionAttributes.get(CONTAINS_ALCOHOL_KEY);

        @SuppressWarnings("unchecked")
        List<Ingredient> newDrinkIngredients = (List<Ingredient>) sessionAttributes.get(INGREDIENTS_KEY);

        String speechText = drinkList.addAndReplaceDrink(newDrinkName, newDrinkFlavour, newDrinkDaytime,
            newDrinkContainsAlcohol, newDrinkIngredients).toString();

        sessionAttributes.remove(NAME_KEY);
        sessionAttributes.remove(FLAVOUR_KEY);
        sessionAttributes.remove(DAYTIME_KEY);
        sessionAttributes.remove(CONTAINS_ALCOHOL_KEY);
        sessionAttributes.remove(INGREDIENTS_KEY);

        attributesManager.setSessionAttributes(sessionAttributes);

        return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
    }

}
