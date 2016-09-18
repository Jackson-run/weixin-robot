package com.xuxue.robot.scala.conf

import java.io.{File, FileInputStream}
import java.util.Properties

/**
  * Created by xuxue on 2016/9/17.
  */
class TuLingConfig(val key:String,val url:String)

object TuLingConfig{
  
  def apply(): TuLingConfig = {
    new TuLingConfig("0bfa17ee2f9cb0bdd92bbe1371dc447b","http://www.tuling123.com/openapi/api")
  }
  
  def main(args: Array[String]): Unit = {
    println(TuLingConfig().key)
  }
}
