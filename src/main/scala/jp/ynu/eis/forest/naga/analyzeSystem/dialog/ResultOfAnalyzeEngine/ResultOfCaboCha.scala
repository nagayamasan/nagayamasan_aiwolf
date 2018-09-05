package jp.ynu.eis.forest.naga.analyzeSystem.dialog.ResultOfAnalyzeEngine

import scala.collection.mutable

class ResultOfCaboCha {
  //[[私, が], [占い師, です]]みたいにしたい
  var word = mutable.MutableList.empty[String]
  var wordim = mutable.MutableList.empty[String]
  val wordData  = mutable.MutableList.empty[mutable.MutableList[String]]
  val wordimData = mutable.MutableList.empty[mutable.MutableList[String]]

  def InsertData(doubleList : mutable.MutableList[mutable.MutableList[String]] , singleList : mutable.MutableList[String]) = {
    if(singleList.nonEmpty){
      doubleList += singleList
    }
    else{
      println("can't connect")
    }

  }
}