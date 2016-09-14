package com.xuxue.robot.scala.conf

/**
  * Created by liuwei on 2016/9/14.
  */
class WeiXinConfig (val appId:String,val appSecret:String,val Token:String,val encodingAesKey:String)

object WeiXinConfig{

  def apply(fileName:String): WeiXinConfig = {
    Pro
  }

}
