package simplebarkeeper.handlers;

import static org.junit.Assert.assertFalse;
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

public class TestGetDrinkChooseIngredientHandler {

	private GetDrinkChooseIngredientIntentHandler testHandler;

	@Before
	public void setup() {
		testHandler = new GetDrinkChooseIngredientIntentHandler();
	}

	@Test
	public void testCanHandle() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_CHOOSE_INGREDIENT);

		final HandlerInput mockInput = TestUtil.mockHandlerInputChooseAlcohol(null, sessAtt, null, null);

		when(mockInput.matches(any())).thenReturn(true);
		assertTrue(testHandler.canHandle(mockInput));
	}
	
	@Test
	public void testCannotHandle() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_DEFAULT);

		final HandlerInput mockInput = TestUtil.mockHandlerInputChooseAlcohol(null, sessAtt, null, null);

		when(mockInput.matches(any())).thenReturn(true);
		assertFalse(testHandler.canHandle(mockInput));
	}

	@Test
	public void testHandleKnownIngredient() {
		String answer = "cola";
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_DRINK_CONTAINS_ALCOHOL_KEY, "false");
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_CHOOSE_INGREDIENT);
		final HandlerInput mockInput = TestUtil.mockHandlerInputChooseIngredient(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.toString().contains("Dein Barkeeper empfiehlt dir:"));
	}


	@Test
	public void testHandleUnknownIngredient() {
		String answer = "Katachanga";
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_DRINK_CONTAINS_ALCOHOL_KEY, "true");
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_CHOOSE_INGREDIENT);
		final HandlerInput mockInput = TestUtil.mockHandlerInputChooseIngredient(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.toString().contains("kein Drink bekannt"));
	}

	@Test
	public void testHandleAnswerNull() {
		String answer = null;
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_CONTAINS_ALCOHOL);
		final HandlerInput mockInput = TestUtil.mockHandlerInputChooseIngredient(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("Tut mir leid, ich habe dich nicht verstanden!"));

	}
	
}
