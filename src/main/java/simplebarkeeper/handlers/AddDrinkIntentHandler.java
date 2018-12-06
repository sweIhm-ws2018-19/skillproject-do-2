package simplebarkeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import simplebarkeeper.States;

public class AddDrinkIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();
        return input.matches(intentName("AddDrinkIntent"))
                && sessionAttributes.get(States.STATE_KEY).equals(States.DEFAULT);
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();
        sessionAttributes.put(States.STATE_KEY, States.ADD_DRINK_NAME);
        attributesManager.setSessionAttributes(sessionAttributes);

        String speechText = "Wie heißt der Drink?";

//            String newDrinkName = (String) sessionAttributes.get(Attributes.NEW_NAME_KEY);
//            String newDrinkFlavour = (String) sessionAttributes.get(Attributes.NEW_FLAVOUR_KEY);
//            String newDrinkDaytime = (String) sessionAttributes.get(Attributes.NEW_DAYTIME_KEY);
//            String newDrinkContainsAlcohol = (String) sessionAttributes.get(Attributes.NEW_CONTAINS_ALCOHOL_KEY);
//
//            @SuppressWarnings("unchecked")
//            List<Ingredient> newDrinkIngredients = (List<Ingredient>) sessionAttributes
//                    .get(Attributes.NEW_INGREDIENTS_KEY);
//
//            ListOfDrinks drinkList = new ListOfDrinks();
//
//            speechText = drinkList.addAndReplaceDrink(newDrinkName, newDrinkFlavour, newDrinkDaytime,
//                    newDrinkContainsAlcohol, newDrinkIngredients).toString();
//
//            drinkList.saveListAsJson();
//
//            sessionAttributes.remove(Attributes.NEW_NAME_KEY);
//            sessionAttributes.remove(Attributes.NEW_FLAVOUR_KEY);
//            sessionAttributes.remove(Attributes.NEW_DAYTIME_KEY);
//            sessionAttributes.remove(Attributes.NEW_CONTAINS_ALCOHOL_KEY);
//            sessionAttributes.remove(Attributes.NEW_INGREDIENTS_KEY);
//        }

        return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
    }

}
