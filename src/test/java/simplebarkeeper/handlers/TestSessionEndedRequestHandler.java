package simplebarkeeper.handlers;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

public class TestSessionEndedRequestHandler {

	private SessionEndedRequestHandler testHandler;

	@Before
	public void setup() {
		testHandler = new SessionEndedRequestHandler();
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
		assertNull(response.getOutputSpeech());
	}
}
