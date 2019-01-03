package simplebarkeeper.handlers;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import simplebarkeeper.States;

public class TestGetRecipeIntentHandler {

	private GetRecipeIntentHandler testHandler;

	@Before
	public void setup() {
		testHandler = new GetRecipeIntentHandler();
	}

	@Test
	public void testCanHandle() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_DEFAULT);

		final HandlerInput mockInput = TestUtil.mockHandlerInput(null, sessAtt, null, null);

		when(mockInput.matches(any())).thenReturn(true);
		assertTrue(testHandler.canHandle(mockInput));
	}

	@Test
	public void testHandle() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_DEFAULT);
		final HandlerInput mockInput = TestUtil.mockHandlerInput(null, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("Zu welchem Drink möchtest du das Rezept haben?"));
		
		Map<String, Object> sessionAttributesAfterHandle = mockInput.getAttributesManager().getSessionAttributes();
		
		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_STATE_KEY).equals(States.GET_RECIPE_FETCH_DRINK));
	}
}
