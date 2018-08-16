package jp.ynu.eis.forest.naga.starter

import java.io.IOException
import java.util.Random
import org.aiwolf.common.net.GameSetting
import org.aiwolf.server.AIWolfGame
import org.aiwolf.server.net.TcpipServer

object Server{
  def main(args: Array[String]): Unit = {
    val serv = new Thread(new Server)
    serv.start()
    serv.join(1000)


  }
}

class Server extends Runnable {
  override def run(): Unit = {
    val gameSetting = GameSetting.getDefaultGame(5)
    gameSetting.setValidateUtterance(false)
    gameSetting.setTalkOnFirstDay(true)
    gameSetting.setTimeLimit(5000)
    val is = new TcpipServer(10000, 5, gameSetting)
    try
      is.waitForConnection
    catch {
      case e: IOException =>
        e.printStackTrace()
    }
    val game = new AIWolfGame(gameSetting, is)
    var i = 0
      for(i <- 0 until 5) {

        game.setRand(new Random(i))
        //game.setGameLogger(new FileGameLogger(new File("log/" + (new Date()).getTime() + ".txt")));
        game.start
      }
    while(!game.isGameFinished){
      //Do Nothing
    }
    game.finish()
    is.close()


  }
}