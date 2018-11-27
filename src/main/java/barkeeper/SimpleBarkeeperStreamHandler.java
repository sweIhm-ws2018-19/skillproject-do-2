package main.java.barkeeper;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import main.java.handlers.CancelAndStopIntentHandler;
import main.java.handlers.FallbackIntentHandler;
import main.java.handlers.HelpIntentHandler;
import main.java.handlers.LaunchRequestHandler;
import main.java.handlers.SessionEndedRequestHandler;

public class SimpleBarkeeperStreamHandler extends SkillStreamHandler {

    public SimpleBarkeeperStreamHandler(Skill skill) {
        super(getSkill());
    }

    private static Skill getSkill() {
        return Skills.standard().addRequestHandlers(
                new CancelAndStopIntentHandler(),
                new LaunchRequestHandler(),
                new FallbackIntentHandler(),
                new HelpIntentHandler(),
                new SessionEndedRequestHandler())
                .withTableName("simpleBarkeeperData")
                .withAutoCreateTable(true)
                .withSkillId("amzn1.ask.skill.75db6f07-d3d6-4266-8200-904377eb1117")
                .build();

    }

}
