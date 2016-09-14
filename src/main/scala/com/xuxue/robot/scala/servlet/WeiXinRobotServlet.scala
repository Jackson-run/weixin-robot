package com.xuxue.robot.scala.servlet

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

import com.xuxue.robot.scala.tuling.TuLingRobot
import com.xuxue.robot.scala.util.StreamReader

import scala.util.{Failure, Success, Try}
import scala.xml.{Node, XML}

/**
  * Created by liuwei on 2016/9/11.
  */
class WeiXinRobotServlet extends HttpServlet {


  val robot = new TuLingRobot

  override def doGet(req: HttpServletRequest,
                     resp: HttpServletResponse): Unit = {
    val out = resp.getWriter
    val option = Try {
      val sig = req.getParameter("signature")
      val timestamp = req.getParameter("timestamp")
      val nonce = req.getParameter("nonce")
      val echoS = req.getParameter("echostr")
      println(echoS)
      echoS
    }
    option match {
      case Success(v) => {
        out.println(v)
        println("write " + v);
      };
      case Failure(e) => println("failed")
    }
    out.close()
    out.flush()
  }

  override def doPost(request: HttpServletRequest,
                      response: HttpServletResponse): Unit = {
    println("recive post")
    val stream = request.getInputStream
    println(StreamReader.toUTF8String(stream))
    /*val xml=XML.load(stream);
    replay(null,response,xml)
    println(xml)*/
  }


  def replay(msg: String, response: HttpServletResponse, reciveXml: Node): Unit = {

    response.setContentType("text/html charset=\"utf-8\"");
    response.setCharacterEncoding("utf-8")
    val xml =
      <xml>
        <ToUserName>
          {(reciveXml \\ "FromUserName").text}
        </ToUserName>
        <FromUserName>
          {(reciveXml \\ "ToUserName").text}
        </FromUserName>
        <CreateTime>
          {System.currentTimeMillis() / 1000}
        </CreateTime>
        <MsgType>text</MsgType>
        <Content>
          {(reciveXml \\ "Content").text}
        </Content>
      </xml>
    val w = response.getWriter

    println(xml.toString())
    w.write(xml.toString())
    w.flush()
    w.close()
  }

}


object WeiXinRobotServlet {
  val TOKEN = "xuxuerobot"
}
