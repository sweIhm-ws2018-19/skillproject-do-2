package simplebarkeeper;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import simplebarkeeper.handlers.CancelandStopIntentHandler;
import simplebarkeeper.handlers.FallbackIntentHandler;
import simplebarkeeper.handlers.GetDrinkChooseAlcoholIntentHandler;
import simplebarkeeper.handlers.GetDrinkChooseFlavourIntentHandler;
import simplebarkeeper.handlers.GetDrinkChooseIngredientIntentHandler;
import simplebarkeeper.handlers.GetDrinkChooseOptionIntentHandler;
import simplebarkeeper.handlers.GetDrinkIntentHandler;
import simplebarkeeper.handlers.GetDrinkRepromptFlavourIntentHandler;
import simplebarkeeper.handlers.GetDrinkRepromptIngredientIntentHandler;
import simplebarkeeper.handlers.GetDrinkRepromptRandomIntentHandler;
import simplebarkeeper.handlers.GetFavouriteIntentHandler;
import simplebarkeeper.handlers.GetRecipeFetchDrinkIntentHandler;
import simplebarkeeper.handlers.GetRecipeIntentHandler;
import simplebarkeeper.handlers.GetRecipeMenuIntentHandler;
import simplebarkeeper.handlers.HelpIntentHandler;
import simplebarkeeper.handlers.LaunchRequestHandler;
import simplebarkeeper.handlers.ListIngredientsIntentHandler;
import simplebarkeeper.handlers.SessionEndedRequestHandler;
import simplebarkeeper.handlers.SetFavouriteIntentHandler;
import simplebarkeeper.handlers.help.HelpGetFavouriteIntentHandler;
import simplebarkeeper.handlers.help.HelpGetRecipeIntentHandler;
import simplebarkeeper.handlers.help.HelpListIngredientsIntentHandler;
import simplebarkeeper.handlers.help.HelpSetFavouriteIntentHandler;

/**
 * Main Stream Handler for the "simple barkeeper" Alexa Skill. Here the Skill
 * itself is structured and built.
 * @author Robin Grellner, Xaver Siodlazek
 */
public class SimpleBarkeeperStreamHandler extends SkillStreamHandler {

    /*
     * Ctor for the SimpleBarkeeperStreamHandler.
     */
    public SimpleBarkeeperStreamHandler() {
        super(getSkill());
    }

    /**
     * Method that builds the Skill from all Intent and Request Handlers.
     * @return The built Skill, that includes all the needed Intent Handlers and
     *         Request Handlers.
     */
    private static Skill getSkill() {
        return Skills.standard().addRequestHandlers(
        		new GetRecipeMenuIntentHandler(),
                new CancelandStopIntentHandler(),
                new LaunchRequestHandler(),
                new FallbackIntentHandler(),
                new HelpIntentHandler(),
                new SessionEndedRequestHandler(),
                new ListIngredientsIntentHandler(),
                new HelpGetFavouriteIntentHandler(),
                new HelpGetRecipeIntentHandler(),
                new HelpListIngredientsIntentHandler(),
                new HelpSetFavouriteIntentHandler(),
                new SetFavouriteIntentHandler(),
                new GetFavouriteIntentHandler(),
                new GetDrinkIntentHandler(),
                new GetDrinkChooseAlcoholIntentHandler(),
                new GetDrinkChooseOptionIntentHandler(),
                new GetDrinkChooseFlavourIntentHandler(),
                new GetDrinkChooseIngredientIntentHandler(),
                new GetDrinkRepromptFlavourIntentHandler(),
                new GetDrinkRepromptIngredientIntentHandler(),
                new GetDrinkRepromptRandomIntentHandler(),
                new GetRecipeFetchDrinkIntentHandler(),
                new GetRecipeIntentHandler()
                )
        		.withTableName("simpleBarkeeperData")
                .withAutoCreateTable(true)
               // .withSkillId("amzn1.ask.skill.ea7c7457-8321-4b3c-bf43-9a5d0069cd90")
                .build();

    }

}
