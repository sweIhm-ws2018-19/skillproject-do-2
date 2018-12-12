package simplebarkeeper.handlers;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

public class TestHelpIntentHandler {

	private HelpIntentHandler testHandler;

	@Before
	public void setup() {
		testHandler = new HelpIntentHandler();
	}

	@Test
	public void testCanHandle() {
		final HandlerInput mockInput = Mockito.mock(HandlerInput.class);
		when(mockInput.matches(any())).thenReturn(true);
		assertTrue(testHandler.canHandle(mockInput));
	}

	@Test
	public void testHandle() {
		final HandlerInput mockInput = TestUtil.mockHandlerInput(null, null, null, null);
		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();

		StringBuilder sr = new StringBuilder();
		String responseMessage = sr
				.append("Ich freue mich Ihnen weiterhelfen zu dürfen. Um diesen Skill nutzen zu können, sollten sie ")
				.append("mit den Funktionalitäten des Skills vertraut sein. ")
				.append("Diese sind: Favorit hinzufügen, Favorit abfragen, Rezept vorlesen und Zutaten abfragen.")
				.append("Um ein Rezept zu einem Drink abzurufen, sagen sie Rezept vorlesen.")
				.append("Um ihren Favoriten abzurufen, sagen sie was ist mein Favorit.")
				.append("Um einene Drink als favoriten abzuspeichern, sagen sie, Mein Favorit ist Beispieldrink.")
				.append("Um die Zutaten eines Drinks abzufragen, sagen sie Was ist in einem Beispieldrink.").toString();

		assertTrue(response.getOutputSpeech().toString().contains(responseMessage));
	}
}
