package forest.naga.java;

import org.aiwolf.client.lib.*;
import org.aiwolf.common.data.Agent;
import org.aiwolf.common.data.Player;
import org.aiwolf.common.net.GameInfo;
import org.aiwolf.common.net.GameSetting;
import org.aiwolf.sample.lib.AbstractSeer;
import org.aiwolf.sample.player.SampleSeer;

public class NagaSeer implements Player {
    GameInfo g ;

    @Override
    public String getName() {
        return "yama";
    }

    @Override
    public void update(GameInfo gameInfo) {
        g= gameInfo;
    }

    @Override
    public void initialize(GameInfo gameInfo, GameSetting gameSetting) {

    }

    @Override
    public void dayStart() {

    }

    @Override
    public String talk() {
        //return new Content(new AgreeContentBuilder(TalkType.TALK, 1,5)).getText();

        return g.getRole().toString();
    }

    @Override
    public String whisper() {
        return null;
    }

    @Override
    public Agent vote() {
        return null;
    }

    @Override
    public Agent attack() {
        return null;
    }

    @Override
    public Agent divine() {
        return null;
    }

    @Override
    public Agent guard() {
        return null;
    }

    @Override
    public void finish() {

    }
}
