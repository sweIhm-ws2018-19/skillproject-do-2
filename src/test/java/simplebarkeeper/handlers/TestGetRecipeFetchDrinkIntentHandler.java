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

public class TestGetRecipeFetchDrinkIntentHandler {

	private GetRecipeFetchDrinkIntentHandler testHandler;

	@Before
	public void setup() {
		testHandler = new GetRecipeFetchDrinkIntentHandler();
	}

	@Test
	public void testCanHandle() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_FETCH_DRINK);
		final HandlerInput mockInput = TestUtil.mockHandlerInput(null, sessAtt, null, null);

		when(mockInput.matches(any())).thenReturn(true);
		assertTrue(testHandler.canHandle(mockInput));
	}

	@Test
	public void testHandleKnownDrink() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_DEFAULT);
		String answer = "tequila sunrise";
		final HandlerInput mockInput = TestUtil.mockHandlerInput(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("Hier das Rezept:"));
		
		Map<String, Object> sessionAttributesAfterHandle = mockInput.getAttributesManager().getSessionAttributes();
		
		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_STATE_KEY).equals(States.GET_RECIPE_MENU));
	}
	
	@Test
	public void testHandleUnknownDrink() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_DEFAULT);
		String answer = "Katachanga";
		final HandlerInput mockInput = TestUtil.mockHandlerInput(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("Diesen Drink kenne ich leider nicht, versuche es mit einem anderen Drink nochmal!"));
		
		Map<String, Object> sessionAttributesAfterHandle = mockInput.getAttributesManager().getSessionAttributes();
		
		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_STATE_KEY).equals(States.GET_RECIPE_FETCH_DRINK));
		
		answer = "tequila sunrise";
		final HandlerInput mockInputAfter = TestUtil.mockHandlerInput(answer, sessAtt, null, null);
		final Optional<Response> resAfter = testHandler.handle(mockInputAfter);

		assertTrue(resAfter.isPresent());
		final Response responseAfter = resAfter.get();
		assertTrue(responseAfter.getOutputSpeech().toString().contains("Hier das Rezept:"));
		
	}

}