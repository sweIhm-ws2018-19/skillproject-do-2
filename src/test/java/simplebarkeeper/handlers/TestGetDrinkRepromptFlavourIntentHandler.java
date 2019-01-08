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

public class TestGetDrinkRepromptFlavourIntentHandler {

	private GetDrinkRepromptFlavourIntentHandler testHandler;

	@Before
	public void setup() {
		testHandler = new GetDrinkRepromptFlavourIntentHandler();
	}

	@Test
	public void testCanHandle() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_REPROMPT_FLAVOUR);

		final HandlerInput mockInput = TestUtil.mockHandlerInput(null, sessAtt, null, null);

		when(mockInput.matches(any())).thenReturn(true);
		assertTrue(testHandler.canHandle(mockInput));
	}
	
	@Test
	public void testCannotHandle() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_DEFAULT);

		final HandlerInput mockInput = TestUtil.mockHandlerInput(null, sessAtt, null, null);

		when(mockInput.matches(any())).thenReturn(true);
		assertFalse(testHandler.canHandle(mockInput));
	}

	@Test
	public void testHandleYes() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_DRINK_REPROMPT_FLAVOUR);
		sessAtt.put(States.GET_DRINK_FLAVOUR_KEY, "SWEET");
		sessAtt.put(States.GET_DRINK_CONTAINS_ALCOHOL_KEY, "false");
		String answer = "ja";
		final HandlerInput mockInput = TestUtil.mockHandlerInputChooseAlcohol(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("Dein Barkeeper empfiehlt dir:"));

		Map<String, Object> sessionAttributesAfterHandle = mockInput.getAttributesManager().getSessionAttributes();
		assertTrue(sessionAttributesAfterHandle.get(States.GET_DRINK_STATE_KEY).equals(States.GET_DRINK_REPROMPT_FLAVOUR));
	}
	
	@Test
	public void testHandleNo() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_DRINK_REPROMPT_FLAVOUR);
		sessAtt.put(States.GET_DRINK_FLAVOUR_KEY, "BITTER");
		sessAtt.put(States.GET_DRINK_CONTAINS_ALCOHOL_KEY, "false");
		String answer = "nein";
		final HandlerInput mockInput = TestUtil.mockHandlerInputChooseAlcohol(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("Na dann bis zum nächsten Mal!"));

		Map<String, Object> sessionAttributesAfterHandle = mockInput.getAttributesManager().getSessionAttributes();
		assertTrue(sessionAttributesAfterHandle.get(States.GET_DRINK_STATE_KEY).equals(States.GET_DRINK_DEFAULT));
	}
	@Test
	public void testHandleNotUnderstood() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_DRINK_REPROMPT_FLAVOUR);
		sessAtt.put(States.GET_DRINK_FLAVOUR_KEY, "BITTER");
		sessAtt.put(States.GET_DRINK_CONTAINS_ALCOHOL_KEY, "false");
		String answer = "kauderwelsch";
		final HandlerInput mockInput = TestUtil.mockHandlerInputChooseAlcohol(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("Tut mir leid, ich habe dich nicht verstanden! Möchtest du noch einen weiteren Drink?"));

		Map<String, Object> sessionAttributesAfterHandle = mockInput.getAttributesManager().getSessionAttributes();
		assertTrue(sessionAttributesAfterHandle.get(States.GET_DRINK_STATE_KEY).equals(States.GET_DRINK_REPROMPT_FLAVOUR));
	}
	
	@Test
	public void testHandleAnswerNull() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_DRINK_REPROMPT_FLAVOUR);
		sessAtt.put(States.GET_DRINK_FLAVOUR_KEY, "BITTER");
		sessAtt.put(States.GET_DRINK_CONTAINS_ALCOHOL_KEY, "false");
		String answer = null;
		final HandlerInput mockInput = TestUtil.mockHandlerInputChooseAlcohol(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("Tut mir leid, ich habe dich nicht verstanden! Möchtest du noch einen weiteren Drink?"));

		Map<String, Object> sessionAttributesAfterHandle = mockInput.getAttributesManager().getSessionAttributes();
		assertTrue(sessionAttributesAfterHandle.get(States.GET_DRINK_STATE_KEY).equals(States.GET_DRINK_REPROMPT_FLAVOUR));
	}
	
	
	

}