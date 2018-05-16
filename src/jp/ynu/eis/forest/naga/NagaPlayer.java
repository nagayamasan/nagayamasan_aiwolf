package jp.ynu.eis.forest.naga;
import org.aiwolf.common.data.Agent;
import org.aiwolf.common.data.Player;
import org.aiwolf.common.data.Role;
import org.aiwolf.common.net.GameInfo;
import org.aiwolf.common.net.GameSetting;
import org.aiwolf.common.net.TcpipClient;
import java.util.ArrayList;

public class NagaPlayer implements Player {
    private Player player ;

    @Override
    public String getName() {
        return "yama";
    }

    @Override
    public void update(GameInfo gameInfo) {

        player.update(gameInfo);
    }

    @Override
    public void initialize(GameInfo gameInfo, GameSetting gameSetting) {
        player = new NagaSeer();
        if(gameInfo.getRole() == Role.SEER){
            player = new NagaSeer();
        }
    }

    @Override
    public void dayStart() {

    }

    @Override
    public String talk() {
        return player.talk();
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


    public static void main(String[] args ){
        System.out.println();

     new Thread(new Server()).start();


        ArrayList<NagaPlayer> nl = new ArrayList<>();
        ArrayList<TcpipClient> cl = new ArrayList<>();

        for(int i=0; i<5; i++){
            nl.add(new NagaPlayer());
            cl.add(new TcpipClient("localhost", 10000));
            cl.get(i).connect(nl.get(i));

        }



   }
}
