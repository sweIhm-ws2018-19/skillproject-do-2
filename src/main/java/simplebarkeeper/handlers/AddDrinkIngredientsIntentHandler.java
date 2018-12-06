package simplebarkeeper.handlers;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class AddDrinkIngredientsIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return false;
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        return input.getResponseBuilder().withSpeech("").withShouldEndSession(false).build();
    }
}
