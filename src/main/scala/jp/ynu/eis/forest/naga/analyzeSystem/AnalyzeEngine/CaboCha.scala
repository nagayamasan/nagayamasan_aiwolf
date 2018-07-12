package jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.AnalyzeList.ResultOfCaboCha
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager

import scala.collection.mutable
import scala.sys.process._
object CaboCha {

      def getResult(dm:DialogManager): Unit ={
        val fact: String = dm.taList.talkList.last.getText
        val out: String = s"echo $fact" #| "cabocha -f1" !!

        val outsp: Array[String] = out.split("\n",-1)
        val rex = "*".r
        val noutsp = outsp.tail

        for(r <- noutsp){
          if(rex.findPrefixOf(r) == None){
            dm.taList.anaList.resc.word += r.split("\t").head
            dm.taList.anaList.resc.wordim += r.split("\t").last
          }
          else{
            dm.taList.anaList.resc.InsertData(dm.taList.anaList.resc.wordData, dm.taList.anaList.resc.word)
            dm.taList.anaList.resc.InsertData(dm.taList.anaList.resc.wordimData, dm.taList.anaList.resc.wordim)

          }

        }

 }


}
