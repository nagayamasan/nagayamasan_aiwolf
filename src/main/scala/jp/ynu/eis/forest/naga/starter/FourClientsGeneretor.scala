package jp.ynu.eis.forest.naga.starter

import java.util

import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.OpponentDetective
import org.aiwolf.common.net.TcpipClient

object FourClientsGeneretor {

  def main(args: Array[String]): Unit = {
    OpponentDetective.inputTextResources()
    val nl = new util.ArrayList[NagaPlayer]
    val cl = new util.ArrayList[TcpipClient]
    var i = 0
    while (i < 4) {
      nl.add(new NagaPlayer)
      //hostをいじる
      cl.add(new TcpipClient("localhost", 10000))
      cl.get(i).connect(nl.get(i))

      i += 1


    }
  }
}
