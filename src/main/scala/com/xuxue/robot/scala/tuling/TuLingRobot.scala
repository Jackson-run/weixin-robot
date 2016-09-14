package com.xuxue.robot.scala.tuling

import java.io.{BufferedInputStream, ByteArrayInputStream, ByteArrayOutputStream}

import org.apache.http.impl.client.HttpClients
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.BasicHttpEntity
import com.xuxue.robot.scala.util.Loan.use
import com.xuxue.robot.scala.util.StreamReader
import org.apache.http.client.config.RequestConfig
import com.xuxue.robot.scala.util.Gs
/**
  * Created by liuwei on 2016/9/11.
  */
class TuLingRobot {

  val client=HttpClients.custom()
                            .setMaxConnTotal(200).build()

  def getAnswler(question:Question):String={

      val serverResponse=postTuLingServer(question);
    serverResponse
  }


  def postTuLingServer(question: Question):String={
    val json=question.toString
    val post=new HttpPost(TuLingRobot.url)
    val entry=new BasicHttpEntity
    entry.setContent(new ByteArrayInputStream(json.getBytes()))

    post.setConfig(RequestConfig.custom().setConnectTimeout(5000)
      .setSocketTimeout(5000)
      .setConnectionRequestTimeout(5000)
      .build())
    post.setEntity(entry)
    use(client.execute(post)){
      res=>
        val json=StreamReader.toUTF8String(res.getEntity.getContent);
        json
    }
  }


  def matchAnswler(json:String):

}


case class Question(key:String,info:String,loc:String,userID:String){
  override def toString=Gs.G.toJson(this)
}

abstract class Answer()

case class TextAnswer(code:String,text:String) extends Answer(){
}

case class URLAnswer(code:String,text:String,url:Array[String]) extends Answer()

case class News(article:String,source:String,icon:String,detailurl:String)

case class NewsAnswler(code:String,text:String,list:Array[News])extends Answer()

object TuLingRobot{

  val url="http://www.tuling123.com/openapi/api"

  //{"code":100000,"text":"我不会说英语的啦，你还是说中文吧。"}


  def main(args: Array[String]): Unit = {
    val textAnswerPatter="{\"code\":100000,".r;

  }

}
