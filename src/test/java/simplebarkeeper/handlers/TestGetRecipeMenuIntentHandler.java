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

import simplebarkeeper.Drink;
import simplebarkeeper.ListOfDrinks;
import simplebarkeeper.States;

public class TestGetRecipeMenuIntentHandler {

	private GetRecipeMenuIntentHandler testHandler;

	@Before
	public void setup() {
		testHandler = new GetRecipeMenuIntentHandler();
	}

	@Test
	public void testCanHandle() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_MENU);
		final HandlerInput mockInput = TestUtil.mockHandlerInput(null, sessAtt, null, null);

		when(mockInput.matches(any())).thenReturn(true);
		assertTrue(testHandler.canHandle(mockInput));
	}
	
	@Test
	public void testCannotHandle() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_DEFAULT);
		final HandlerInput mockInput = TestUtil.mockHandlerInput(null, sessAtt, null, null);

		when(mockInput.matches(any())).thenReturn(true);
		assertFalse(testHandler.canHandle(mockInput));
	}


	@Test
	public void testHandleForward() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		ListOfDrinks drinkList = new ListOfDrinks();
		String nameOfDrink = "tequila sunrise";
		Drink drink = drinkList.getDrink(nameOfDrink);
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_MENU);
		sessAtt.put(States.GET_RECIPE_DRINK_KEY, drink);
		sessAtt.put(States.GET_RECIPE_STEP_COUNTER_KEY, 0);
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_DEFAULT);
		String answer = "weiter";
		final HandlerInput mockInput = TestUtil.mockHandlerInputRecipeMenu(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("2"));

		Map<String, Object> sessionAttributesAfterHandle = mockInput.getAttributesManager().getSessionAttributes();

		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_STATE_KEY).equals(States.GET_RECIPE_MENU));
	}

	@Test
	public void testHandleBackward() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		ListOfDrinks drinkList = new ListOfDrinks();
		String nameOfDrink = "tequila sunrise";
		Drink drink = drinkList.getDrink(nameOfDrink);
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_MENU);
		sessAtt.put(States.GET_RECIPE_DRINK_KEY, drink);
		sessAtt.put(States.GET_RECIPE_STEP_COUNTER_KEY, 1);
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_DEFAULT);
		String answer = "zurück";
		final HandlerInput mockInput = TestUtil.mockHandlerInputRecipeMenu(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("1"));

		Map<String, Object> sessionAttributesAfterHandle = mockInput.getAttributesManager().getSessionAttributes();

		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_STATE_KEY).equals(States.GET_RECIPE_MENU));

	}
	
	@Test
	public void testHandleAgain() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		ListOfDrinks drinkList = new ListOfDrinks();
		String nameOfDrink = "tequila sunrise";
		Drink drink = drinkList.getDrink(nameOfDrink);
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_MENU);
		sessAtt.put(States.GET_RECIPE_DRINK_KEY, drink);
		sessAtt.put(States.GET_RECIPE_STEP_COUNTER_KEY, 1);
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_DEFAULT);
		String answer = "nochmal";
		HandlerInput mockInput = TestUtil.mockHandlerInputRecipeMenu(answer, sessAtt, null, null);

		Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("2"));

		Map<String, Object> sessionAttributesAfterHandle = mockInput.getAttributesManager().getSessionAttributes();

		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_STATE_KEY).equals(States.GET_RECIPE_MENU));
		
		
		
		
		sessAtt = new HashMap<String, Object>();
		drinkList = new ListOfDrinks();
		nameOfDrink = "tequila sunrise";
		drink = drinkList.getDrink(nameOfDrink);
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_MENU);
		sessAtt.put(States.GET_RECIPE_DRINK_KEY, drink);
		sessAtt.put(States.GET_RECIPE_STEP_COUNTER_KEY, 1);
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_DEFAULT);
		answer = "nocheinmal";
		mockInput = TestUtil.mockHandlerInputRecipeMenu(answer, sessAtt, null, null);

		res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("2"));

		sessionAttributesAfterHandle = mockInput.getAttributesManager().getSessionAttributes();

		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_STATE_KEY).equals(States.GET_RECIPE_MENU));
		
		
		
		
		sessAtt = new HashMap<String, Object>();
		drinkList = new ListOfDrinks();
		nameOfDrink = "tequila sunrise";
		drink = drinkList.getDrink(nameOfDrink);
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_MENU);
		sessAtt.put(States.GET_RECIPE_DRINK_KEY, drink);
		sessAtt.put(States.GET_RECIPE_STEP_COUNTER_KEY, 1);
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_DEFAULT);
		answer = "wiederholen";
		mockInput = TestUtil.mockHandlerInputRecipeMenu(answer, sessAtt, null, null);

		res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("2"));

		sessionAttributesAfterHandle = mockInput.getAttributesManager().getSessionAttributes();

		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_STATE_KEY).equals(States.GET_RECIPE_MENU));
	}
	
	@Test 
	public void testHandleForwardCap() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		ListOfDrinks drinkList = new ListOfDrinks();
		String nameOfDrink = "tequila sunrise";
		Drink drink = drinkList.getDrink(nameOfDrink);
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_MENU);
		sessAtt.put(States.GET_RECIPE_DRINK_KEY, drink);
		sessAtt.put(States.GET_RECIPE_STEP_COUNTER_KEY, 2);
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_DEFAULT);
		String answer = "weiter";
		final HandlerInput mockInput = TestUtil.mockHandlerInputRecipeMenu(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("Cheers!"));

		Map<String, Object> sessionAttributesAfterHandle = mockInput.getAttributesManager().getSessionAttributes();

		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_STATE_KEY).equals(States.GET_RECIPE_DEFAULT));
		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_DRINK_KEY) == null);
		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_STEP_COUNTER_KEY).equals(0));

	}
	
	@Test
	public void testHandleBackwardCap() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		ListOfDrinks drinkList = new ListOfDrinks();
		String nameOfDrink = "tequila sunrise";
		Drink drink = drinkList.getDrink(nameOfDrink);
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_MENU);
		sessAtt.put(States.GET_RECIPE_DRINK_KEY, drink);
		sessAtt.put(States.GET_RECIPE_STEP_COUNTER_KEY, 0);
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_DEFAULT);
		String answer = "zurück";
		final HandlerInput mockInput = TestUtil.mockHandlerInputRecipeMenu(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("Du bist bereits beim ersten Schritt!"));

		Map<String, Object> sessionAttributesAfterHandle = mockInput.getAttributesManager().getSessionAttributes();

		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_STATE_KEY).equals(States.GET_RECIPE_MENU));
		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_DRINK_KEY).equals(drink));
		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_STEP_COUNTER_KEY).equals(0));
	}
	@Test
	public void testHandleNotUnderstood() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		ListOfDrinks drinkList = new ListOfDrinks();
		String nameOfDrink = "tequila sunrise";
		Drink drink = drinkList.getDrink(nameOfDrink);
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_MENU);
		sessAtt.put(States.GET_RECIPE_DRINK_KEY, drink);
		sessAtt.put(States.GET_RECIPE_STEP_COUNTER_KEY, 0);
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_DEFAULT);
		String answer = "kauderwelsch";
		final HandlerInput mockInput = TestUtil.mockHandlerInputRecipeMenu(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("Ich habe dich leider nicht verstanden."));

		Map<String, Object> sessionAttributesAfterHandle = mockInput.getAttributesManager().getSessionAttributes();

		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_STATE_KEY).equals(States.GET_RECIPE_MENU));
		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_DRINK_KEY).equals(drink));
		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_STEP_COUNTER_KEY).equals(0));
	}
	
	@Test
	public void testHandleAnswerNull() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		ListOfDrinks drinkList = new ListOfDrinks();
		String nameOfDrink = "tequila sunrise";
		Drink drink = drinkList.getDrink(nameOfDrink);
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_MENU);
		sessAtt.put(States.GET_RECIPE_DRINK_KEY, drink);
		sessAtt.put(States.GET_RECIPE_STEP_COUNTER_KEY, 0);
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_DEFAULT);
		String answer = null;
		final HandlerInput mockInput = TestUtil.mockHandlerInputRecipeMenu(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("Da ist wohl etwas falsch gelaufen"));

		Map<String, Object> sessionAttributesAfterHandle = mockInput.getAttributesManager().getSessionAttributes();

		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_STATE_KEY).equals(States.GET_RECIPE_MENU));
		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_DRINK_KEY).equals(drink));
		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_STEP_COUNTER_KEY).equals(0));
	}

	@Test
	public void testHandleStop() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		ListOfDrinks drinkList = new ListOfDrinks();
		String nameOfDrink = "tequila sunrise";
		Drink drink = drinkList.getDrink(nameOfDrink);
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_MENU);
		sessAtt.put(States.GET_RECIPE_DRINK_KEY, drink);
		sessAtt.put(States.GET_RECIPE_STEP_COUNTER_KEY, 0);
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_DEFAULT);
		String answer = "stop";
		HandlerInput mockInput = TestUtil.mockHandlerInputRecipeMenu(answer, sessAtt, null, null);

		Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("Okay, dann vielleicht ja später!"));

		Map<String, Object> sessionAttributesAfterHandle = mockInput.getAttributesManager().getSessionAttributes();

		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_STATE_KEY).equals(States.GET_RECIPE_DEFAULT));
		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_DRINK_KEY) == null);
		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_STEP_COUNTER_KEY).equals(0));
		
		
		
		sessAtt = new HashMap<String, Object>();
		drinkList = new ListOfDrinks();
		nameOfDrink = "tequila sunrise";
		drink = drinkList.getDrink(nameOfDrink);
		sessAtt.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_MENU);
		sessAtt.put(States.GET_RECIPE_DRINK_KEY, drink);
		sessAtt.put(States.GET_RECIPE_STEP_COUNTER_KEY, 0);
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_DEFAULT);
		answer = "stop";
		mockInput = TestUtil.mockHandlerInputRecipeMenu(answer, sessAtt, null, null);

		res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("Okay, dann vielleicht ja später!"));

		sessionAttributesAfterHandle = mockInput.getAttributesManager().getSessionAttributes();

		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_STATE_KEY).equals(States.GET_RECIPE_DEFAULT));
		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_DRINK_KEY) == null);
		assertTrue(sessionAttributesAfterHandle.get(States.GET_RECIPE_STEP_COUNTER_KEY).equals(0));
	}
	
}