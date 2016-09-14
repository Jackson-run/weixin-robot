package com.xuxue.robot.scala

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

/**
  * Created by liuwei on 2016/9/11.
  */
class TestServlet extends HttpServlet{

  override def doGet(req: HttpServletRequest,
                     resp: HttpServletResponse): Unit = {
    val out=resp.getWriter
    val xml = <html>
      <head>hello</head>
      <body>this is out</body>
    </html>;
    out.println(xml.toString)
    out.flush()
    out.close()
  }

}
