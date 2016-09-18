package com.xuxue.robot.scala.tuling

/**
  * Created by xuxue on 2016/9/17.
  */
object Type extends Enumeration{
  
  type ExecutorState = Value
  
  val TEXT=Value("100000")
  
  val HREF=Value("200000")
  
  val NEWS=Value("302000")
  
  val MENU=Value("308000")
  
  
  def main(args: Array[String]): Unit = {
    
    val a=Type.HREF
    println(a)
    
  }
  
}
