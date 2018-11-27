package simplebarkeeper;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import simplebarkeeper.handlers.CancelandStopIntentHandler;
import simplebarkeeper.handlers.FallbackIntentHandler;
import simplebarkeeper.handlers.HelpIntentHandler;
import simplebarkeeper.handlers.LaunchRequestHandler;
import simplebarkeeper.handlers.ListIngredientsIntentHandler;
import simplebarkeeper.handlers.SessionEndedRequestHandler;

public class SimpleBarkeeperStreamHandler extends SkillStreamHandler {

    public SimpleBarkeeperStreamHandler() {
        super(getSkill());
    }

    private static Skill getSkill() {
        return Skills.standard().addRequestHandlers(
                new CancelandStopIntentHandler(),
                new LaunchRequestHandler(),
                new FallbackIntentHandler(),
                new HelpIntentHandler(),
                new SessionEndedRequestHandler(),
                new ListIngredientsIntentHandler())
                //.withTableName("simpleBarkeeperData")
                //.withAutoCreateTable(true)
                //.withSkillId("amzn1.ask.skill.ea7c7457-8321-4b3c-bf43-9a5d0069cd90")
                .build();

    }

}
