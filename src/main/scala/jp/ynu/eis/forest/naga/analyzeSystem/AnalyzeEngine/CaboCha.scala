package jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.ResultOfAnalyzeEngine.ResultOfCaboCha
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.{DialogManager, NeoTalk}
import org.aiwolf.common.data.Talk

import scala.collection.mutable
import scala.sys.process._
object CaboCha {

  def setResult(talk: Talk, resultofcabocha: ResultOfCaboCha): Unit ={
    var fact: String = talk.getText
    if(fact.size > 20){
      fact = "無理"
    }
    val out: String = s"echo $fact" #| "cabocha -f1" !!
    //echoを使ってcabocha


    val outsp: Array[String] = out.split("\n",-1)
    val rex = "\\*".r
    val noutsp = outsp.tail

    for(r <- noutsp){
      if(rex.findPrefixOf(r) != None){
        resultofcabocha.InsertData(resultofcabocha.wordData, resultofcabocha.word)
        resultofcabocha.InsertData(resultofcabocha.wordimData, resultofcabocha.wordim)
        resultofcabocha.word = mutable.MutableList.empty[String]
        resultofcabocha.wordim = mutable.MutableList.empty[String]

      }
      else {
        resultofcabocha.word += r.split("\t").head
        resultofcabocha.wordim += r.split("\t").last
      }
    }
    resultofcabocha.InsertData(resultofcabocha.wordData, resultofcabocha.word)
    resultofcabocha.InsertData(resultofcabocha.wordimData, resultofcabocha.wordim)
  }
}
