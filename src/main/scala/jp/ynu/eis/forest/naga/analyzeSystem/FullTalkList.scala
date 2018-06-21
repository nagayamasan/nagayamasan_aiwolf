package jp.ynu.eis.forest.naga.analyzeSystem

import org.aiwolf.common.data._
import org.aiwolf.common.net.GameInfo

import scala.collection.mutable

class FullTalkList {
  val TalkList: mutable.MutableList[Talk] = mutable.MutableList.empty[Talk]
  //val DayList = mutable.MutableList.empty[Int]
  //val AgentList = mutable.MutableList.empty[Agent]
  def collecting(gameInfo: GameInfo): Unit={
    TalkList += gameInfo.getTalkList.get(gameInfo.getTalkList.size() -1)
  }
}
