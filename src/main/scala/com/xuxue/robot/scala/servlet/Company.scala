package com.xuxue.robot.scala.servlet

import java.io.PrintWriter
import java.sql.{Connection, DriverManager}
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

import com.xuxue.robot.scala.util.Loan.use

/**
  * Created by xuxue on 16-11-15.
  */
class Company extends HttpServlet{

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    resp.setCharacterEncoding("utf-8")
    val id=req.getParameter("id").toInt;
    val x=use(Company.connection.prepareStatement("select company_name,company_province from company" +
      " where company_id=?")){
      pre=>
        pre.setInt(1,id);
        use(pre.executeQuery()){
          set=>
            if(set.next()){
              val name=set.getString("company_name");
              val province=set.getString("company_province");
              val p=resp.getWriter
              p.println("{\"name\":\""+name+"\",\"province\":\""+province+"\"}")
              p.flush()
              p.close()
            }else{
              val p=resp.getWriter
              p.println("{\"name\":\"\",\"province\":\"\"}")
              p.flush()
              p.close()
            }
        }
    }



  }


}

object Company{

  val connection:Connection={
    Class.forName("com.mysql.jdbc.Driver");
    DriverManager.
      getConnection("jdbc:mysql://139.129.9.43:3306/company?&characterEncoding=UTF-8","xuxue","qwer")
  }
}
