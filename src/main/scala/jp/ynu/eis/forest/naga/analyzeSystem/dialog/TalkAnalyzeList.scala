package jp.ynu.eis.forest.naga.analyzeSystem.dialog

import org.aiwolf.common.data.Talk
import org.aiwolf.common.net.GameInfo

import scala.collection.mutable

class TalkAnalyzeList {
  val talkList: mutable.MutableList[Talk] = mutable.MutableList.empty[Talk]
  val anaList  = new AnlyzeList
  //val DayList = mutable.MutableList.empty[Int]
  //val AgentList = mutable.MutableList.empty[Agent]
  def collecting(gameInfo: GameInfo): Unit={
    talkList += gameInfo.getTalkList.get(gameInfo.getTalkList.size() -1)
  }
}
