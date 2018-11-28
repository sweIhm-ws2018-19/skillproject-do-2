package simplebarkeeper.handlers;

import static com.amazon.ask.request.Predicates.requestType;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.SessionEndedRequest;
/**
 * Handler for the SessionEndedReqeust. It is only called if the
 * Session was ended.  Any cleanup logic is implemented here. 
 * @author Robin Grellner, Xaver Siodlazek
 *
 */
public class SessionEndedRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(SessionEndedRequest.class));
    }

    /**
     * {@inheritDoc}
     * Method That implements the cleanup logic.
     */
    @Override
    public Optional<Response> handle(HandlerInput input) {
        return input.getResponseBuilder().build();
    }

}
