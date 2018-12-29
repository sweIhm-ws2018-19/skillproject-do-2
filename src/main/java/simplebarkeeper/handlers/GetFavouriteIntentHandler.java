package simplebarkeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import simplebarkeeper.ListOfDrinks;

public class GetFavouriteIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("GetFavouriteIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		ListOfDrinks drinkList = new ListOfDrinks();

		String speechText = drinkList.getFavorite();

		return input.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
		
	}
}
