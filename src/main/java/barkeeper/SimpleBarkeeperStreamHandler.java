package main.java.barkeeper;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import main.java.handlers.CancelAndStopIntentHandler;
import main.java.handlers.LaunchRequestHandler;

public class SimpleBarkeeperStreamHandler extends SkillStreamHandler{

	public SimpleBarkeeperStreamHandler(Skill skill) {		
		super(getSkill());
	}
	
	private static Skill getSkill() {
		return Skills.standard().addRequestHandlers(
				new CancelAndStopIntentHandler(),
				new LaunchRequestHandler()
				).build();
		
	}
	
	

}
