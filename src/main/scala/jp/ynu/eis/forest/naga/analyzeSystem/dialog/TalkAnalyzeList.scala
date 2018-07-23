package jp.ynu.eis.forest.naga.analyzeSystem.dialog

import jp.ynu.eis.forest.naga.analyzeSystem.dialog.AnalyzeList.AnlyzeList
import org.aiwolf.common.data.Talk
import org.aiwolf.common.net.GameInfo

import scala.collection.mutable

class TalkAnalyzeList {
  val talkList: mutable.MutableList[Talk] = mutable.MutableList.empty[Talk]
  val anaList  = new AnlyzeList
  //val DayList = mutable.MutableList.empty[Int]
  //val AgentList = mutable.MutableList.empty[Agent]

  def collecting(gameInfo: GameInfo): Unit={
    if(gameInfo.getTalkList.isEmpty){
    }else{
      talkList += gameInfo.getTalkList.get(gameInfo.getTalkList.size() -1)
    }
  }
}
