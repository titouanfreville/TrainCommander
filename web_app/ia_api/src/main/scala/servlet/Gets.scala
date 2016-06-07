package servlet

import javax.servlet.http._

/**
  * Created by titouanfreville on 06/06/16.
  */
class Gets extends HttpServlet {
  def doGet(request: HttpServlet, response: HttpServletResponse): Unit = {
    response.setContentType("text/html")
    response.setCharacterEncoding("UTF-8")
    response.getWriter.write("""<h1>Hellow, world :</h1>""")
  }
}
