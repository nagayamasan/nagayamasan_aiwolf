package jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.AnalyzeList._
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import us.feliscat.text.{StringOption, StringSome}
import us.feliscat.text.analyzer.mor.mecab.IpadicMecab

import scala.collection.mutable

object MeCab {
  def getResult(dm: DialogManager): Unit ={
    val stop: StringSome = StringSome(dm.taList.talkList.last.getText)
    val res = IpadicMecab.analyze(stop)
    res.foreach{
      f =>
        dm.taList.anaList.resm.wordData += f.split("\t").head
        dm.taList.anaList.resm.wordimData += f.split("\t").last

    }
  }
}
