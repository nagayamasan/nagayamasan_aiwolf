package jp.ynu.eis.forest.naga.analyzeSystem.dialog

import java.util

import org.aiwolf.common.data.{Agent, Role, Talk}
import org.aiwolf.common.net.GameInfo
import sun.invoke.empty.Empty

import scala.collection.mutable
import scala.collection.JavaConverters._

class DialogManager{

  val gameInfoList = mutable.MutableList.empty[GameInfo]
  val taList = new TalkAnalyzeList
  val seerList = mutable.MutableList.empty[Agent]
  val wolfList = mutable.MutableList.empty[Agent]
  val possList = mutable.MutableList.empty[Agent]

  def agentListChange(javalist: util.List[Agent]): mutable.MutableList[Agent]={
    val mutableList = mutable.MutableList.empty[Agent]
    javalist.forEach{
      element =>
        mutableList += element
    }
    mutableList
  }
  def roleListChange(javalist: util.List[Role]): mutable.MutableList[Role]= {
    val mutableList = mutable.MutableList.empty[Role]
    javalist.forEach {
      element =>
        mutableList += element
    }
    mutableList
  }
}

