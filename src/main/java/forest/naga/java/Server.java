package forest.naga.java;

import org.aiwolf.common.net.GameSetting;
import org.aiwolf.server.AIWolfGame;
import org.aiwolf.server.net.TcpipServer;

import java.io.IOException;
import java.util.Random;

public class Server implements Runnable {
    @Override
    public void run() {
        GameSetting gameSetting = GameSetting.getDefaultGame(5);
        gameSetting.setValidateUtterance(false);
        gameSetting.setTalkOnFirstDay(true);
        gameSetting.setTimeLimit(5000);
        TcpipServer is = new TcpipServer(10000,5,gameSetting );

        try {
            is.waitForConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AIWolfGame game = new AIWolfGame(gameSetting, is);

        for(int i = 0; i < 1; i++) {
            game.setRand(new Random(i));
            //game.setGameLogger(new FileGameLogger(new File("log/" + (new Date()).getTime() + ".txt")));
            game.start();
        }
    }
}
