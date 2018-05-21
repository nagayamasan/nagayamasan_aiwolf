package jp.ynu.eis.forest.naga

import org.aiwolf.common.net.GameSetting
import org.aiwolf.server.AIWolfGame
import org.aiwolf.server.net.TcpipServer
import java.io.IOException
import java.util.Random


class Server extends Runnable {
  override def run(): Unit = {
    val gameSetting = GameSetting.getDefaultGame(5)
    gameSetting.setValidateUtterance(false)
    gameSetting.setTalkOnFirstDay(true)
    gameSetting.setTimeLimit(5000)
    val is = new Nothing(10000, 5, gameSetting)
    try
      is.waitForConnection
    catch {
      case e: IOException =>
        e.printStackTrace()
    }
    val game = new Nothing(gameSetting, is)
    var i = 0
    while ( {
      i < 1
    }) {
      game.setRand(new Random(i))
      //game.setGameLogger(new FileGameLogger(new File("log/" + (new Date()).getTime() + ".txt")));
      game.start

      {
        i += 1; i - 1
      }
    }
  }
}