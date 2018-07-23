package jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis

import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import jp.ynu.eis.forest.naga.result.UtteranceResult
import org.aiwolf.common.net.GameInfo


case class WolfUA(dm: DialogManager) extends UtteranceAnalyzer {
  seerDetective
  possessedDitective
  override def getResult: UtteranceResult = {
    super.getResult
  }

  override def seerDetective: Unit = {
    super.seerDetective

  }

  def possessedDitective ={
    val possesedWord = "狂人"
    if(reCentTalklist != null){
      reCentTalklist.foreach(f => {
        if(f.getText.contains(possesedWord)){
          dm.possList += f.getAgent
        }
      })
    }

  }

}

