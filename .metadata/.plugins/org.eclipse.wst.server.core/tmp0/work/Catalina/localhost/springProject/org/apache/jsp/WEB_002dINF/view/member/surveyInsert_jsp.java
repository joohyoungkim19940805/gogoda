/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.60
 * Generated at: 2021-03-05 09:47:24 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.view.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class surveyInsert_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<script src=\"//code.jquery.com/jquery-1.11.1.min.js\"></script>\r\n");
      out.write("<title>취향 설문 조사</title>\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/include/css/main.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/include/css/survey.css\" />\r\n");
      out.write("\r\n");
      out.write("<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">\r\n");
      out.write("<link href=\"https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("//체크박스 체크 확인하기 \r\n");
      out.write("\r\n");
      out.write("$(document).ready(function(){\r\n");
      out.write("\talert(\">>>\"+mnum);\r\n");
      out.write("\t$(\"#I\").click(function(){\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar positivefood=$(\"input[name='positivefood']:checked\").val();\r\n");
      out.write("\t\tconsole.log(\"gSize >>> : \"+ $(\"input[name='positivefood']:checked\").val());\r\n");
      out.write("\r\n");
      out.write("\t\tvar negativefood=$(\"input[name='negativefood']:checked\").val();\r\n");
      out.write("\t\tconsole.log(\"gSize >>> : \"+ $(\"input[name='negativefood']:checked\").val());\r\n");
      out.write("\r\n");
      out.write("\t\tvar obj = $(\"[name='movietaste']\");\r\n");
      out.write("\t\tconsole.log(\"obj>>\"+obj);\r\n");
      out.write("\t\tvar chkArray = new Array(); // 배열 선언\r\n");
      out.write("\t\t$('input:checkbox[name=movietaste]:checked').each(function () { // 체크된 체크박스의 value 값을 가지고 온다.\r\n");
      out.write("\t \t\t chkArray.push(this.value);\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar flag = \"\";\r\n");
      out.write("\t\tflag = aaa();\r\n");
      out.write("\t\tconsole.log(flag);\r\n");
      out.write("\r\n");
      out.write("\t\tif(flag.length>0){\r\n");
      out.write("\t\t\t$('#memForm').attr('action', 'surveyInsert.ggd?positivefood='+positivefood+'&negativefood='+negativefood+'&movietaste='+flag);\r\n");
      out.write("\t\t\t$('#memForm').attr('method', 'POST');\r\n");
      out.write("\t\t\t$('#memForm').attr('enctype', 'multipart/form-data');\r\n");
      out.write("\t\t\t$('#memForm').submit();\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\tfunction aaa(){\r\n");
      out.write("\t\tvar flag = false;\r\n");
      out.write("\t\tvar values = document.getElementsByName(\"movietaste\");\r\n");
      out.write("\t\talert(values.length);\r\n");
      out.write("\t\tvar count = 0;\r\n");
      out.write("\t\tvar movietaste=\"\";\r\n");
      out.write("\t\tfor(var i=0; i<values.length; i++){\r\n");
      out.write("\t\t\tif(values[i].checked){\r\n");
      out.write("\t\t\t\tmovietaste+=values[i].value+\",\";\r\n");
      out.write("\t\t\t\tcount++;\r\n");
      out.write("\t\t\t}\t\r\n");
      out.write("\t\t\t//return flag;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(count < 2){\r\n");
      out.write("\t\t\talert(\"2개 이상 선택해야 합니다.\");\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\talert(count + \" 개가 선택되었습니다.\");\r\n");
      out.write("\t\t\tflag = true;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn movietaste;\r\n");
      out.write("\t}\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<!-- Navbar (sit on top) -->\r\n");
      out.write("<div class=\"w3-top\">\r\n");
      out.write("\t<div class=\"w3-bar w3-white w3-wide w3-padding w3-card\">\r\n");
      out.write("\t\t<a href=\"../../springProject/emotion/mainpage.ggd\">\r\n");
      out.write("\t\t\t<img src=\"/springProject/logo/GOGODA-logo.png\" style=\"width:12%; height:12%\">\r\n");
      out.write("\t\t</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div><br><br><br><br><br>\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"suveycontainer\">\r\n");
      out.write("\t\t<section>\r\n");
      out.write("\t\t\t<h1 class=\"entry-title\">\r\n");
      out.write("\t\t\t\t<span><b>설문 조사</b></span>\r\n");
      out.write("\t\t\t</h1>\r\n");
      out.write("\t\t\t<hr>\r\n");
      out.write("\t\t\t<div class=\"survey\">\r\n");
      out.write("\t\t\t<form name=\"memForm\" id=\"memForm\" enctype=\"multipart/form-data\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<h3>1. 아침부터 기분이 좋은 날! 이럴 때 어떤 음식을 먹고 싶나요?</h3>\r\n");
      out.write("\t\t\t\t<label class=\"container\">1. 든든하게 배를 채울 음식 \r\n");
      out.write("\t\t\t\t\t<input name=\"positivefood\" id=\"positivefood\" type=\"radio\" value=\"1\">\r\n");
      out.write("\t\t\t\t\t<span class=\"checkmark\"></span>\r\n");
      out.write("\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t<label class=\"container\">2. 맵거나 짠 자극적인 음식 \r\n");
      out.write("\t\t\t\t\t<input name=\"positivefood\" id=\"positivefood\" type=\"radio\" value=\"2\">\r\n");
      out.write("\t\t\t\t\t<span class=\"checkmark\"></span>\r\n");
      out.write("\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t<label class=\"container\">3. 단짠이 잘 어우러진 음식 \r\n");
      out.write("\t\t\t\t\t<input name=\"positivefood\" id=\"positivefood\" type=\"radio\" value=\"3\">\r\n");
      out.write("\t\t\t\t\t<span class=\"checkmark\"></span>\r\n");
      out.write("\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t<label class=\"container\">4. 술 안주로 적절한 음식\r\n");
      out.write("\t\t\t\t\t<input name=\"positivefood\" id=\"positivefood\" type=\"radio\" value=\"4\">\r\n");
      out.write("\t\t\t\t\t<span class=\"checkmark\"></span>\r\n");
      out.write("\t\t\t\t</label><br>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<h3>2. 속상한 일들로 기분이 울쩍한 날... 어떤 음식을 먹고 싶나요?</h3>\r\n");
      out.write("\t\t\t\t<label class=\"container\">1. 든든하게 배를 채울 음식 \r\n");
      out.write("\t\t\t\t\t<input name=\"negativefood\" id=\"negativefood\" type=\"radio\" value=\"1\">\r\n");
      out.write("\t\t\t\t\t<span class=\"checkmark\"></span>\r\n");
      out.write("\t\t\t\t</label> <label class=\"container\">2. 맵거나 짠 자극적인 음식 \r\n");
      out.write("\t\t\t\t\t<input name=\"negativefood\" id=\"negativefood\" type=\"radio\" value=\"2\">\r\n");
      out.write("\t\t\t\t\t<span class=\"checkmark\"></span>\r\n");
      out.write("\t\t\t\t</label> <label class=\"container\">3. 단짠이 잘 어우러진 음식 \r\n");
      out.write("\t\t\t\t\t<input name=\"negativefood\" id=\"negativefood\" type=\"radio\" value=\"3\">\r\n");
      out.write("\t\t\t\t\t<span class=\"checkmark\"></span>\r\n");
      out.write("\t\t\t\t</label> <label class=\"container\">4. 술 안주로 적절한 음식\r\n");
      out.write("\t\t\t\t\t<input name=\"negativefood\" id=\"negativefood\" type=\"radio\" value=\"4\">\r\n");
      out.write("\t\t\t\t\t<span class=\"checkmark\"></span>\r\n");
      out.write("\t\t\t\t</label><br>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<h3>3. 어떤 장르의 영화를 좋아하나요?</h3>\r\n");
      out.write("\t\t\t\t<label class=\"container2\"> \r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" id=\"movietaste\" name=\"movietaste\" value=\"drama\" />\r\n");
      out.write("\t\t\t\t\t1: 드라마 <span class=\"checkmark2\"></span>\r\n");
      out.write("\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t<label class=\"container2\"> \r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" id=\"movietaste\" name=\"movietaste\" value=\"war\" />\r\n");
      out.write("\t\t\t\t\t2: 전쟁 <span class=\"checkmark2\"></span>\r\n");
      out.write("\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t<label class=\"container2\"> \r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" id=\"movietaste\" name=\"movietaste\" value=\"action\" />\r\n");
      out.write("\t\t\t\t\t3: 액션 <span class=\"checkmark2\"></span>\r\n");
      out.write("\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t<label class=\"container2\"> \r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" id=\"movietaste\" name=\"movietaste\" value=\"family\" />\r\n");
      out.write("\t\t\t\t\t4: 가족 <span class=\"checkmark2\"></span>\r\n");
      out.write("\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t<label class=\"container2\"> \r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" id=\"movietaste\" name=\"movietaste\" value=\"adult\" />\r\n");
      out.write("\t\t\t\t\t5: 성인물(에로) <span class=\"checkmark2\"></span>\r\n");
      out.write("\t\t\t\t</label><br>\r\n");
      out.write("\t\t\t\t<label class=\"container2\"> \r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" id=\"movietaste\" name=\"movietaste\" value=\"crime\" />\r\n");
      out.write("\t\t\t\t\t6: 범죄 <span class=\"checkmark2\"></span>\r\n");
      out.write("\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t<label class=\"container2\"> \r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" id=\"movietaste\" name=\"movietaste\" value=\"mystery\" />\r\n");
      out.write("\t\t\t\t\t7: 미스터리 <span class=\"checkmark2\"></span>\r\n");
      out.write("\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t<label class=\"container2\"> \r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" id=\"movietaste\" name=\"movietaste\" value=\"animation\" />\r\n");
      out.write("\t\t\t\t\t8: 애니메이션 <span class=\"checkmark2\"></span>\r\n");
      out.write("\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t<label class=\"container2\"> \r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" id=\"movietaste\" name=\"movietaste\" value=\"comedy\" />\r\n");
      out.write("\t\t\t\t\t9: 코미디 <span class=\"checkmark2\"></span>\r\n");
      out.write("\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t<label class=\"container2\"> \r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" id=\"movietaste\" name=\"movietaste\" value=\"horror\" />\r\n");
      out.write("\t\t\t\t\t10: 공포(호러) <span class=\"checkmark2\"></span>\r\n");
      out.write("\t\t\t\t</label><br>\r\n");
      out.write("\t\t\t\t<label class=\"container2\"> \r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" id=\"movietaste\" name=\"movietaste\" value=\"romance\" />\r\n");
      out.write("\t\t\t\t\t11: 멜로/로맨스 <span class=\"checkmark2\"></span>\r\n");
      out.write("\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t<label class=\"container2\"> \r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" id=\"movietaste\" name=\"movietaste\" value=\"SF\" />\r\n");
      out.write("\t\t\t\t\t12: SF <span class=\"checkmark2\"></span>\r\n");
      out.write("\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t<label class=\"container2\"> \r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" id=\"movietaste\" name=\"movietaste\" value=\"documentary\" />\r\n");
      out.write("\t\t\t\t\t13: 다큐멘터리 <span class=\"checkmark2\"></span>\r\n");
      out.write("\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t<label class=\"container2\"> \r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" id=\"movietaste\" name=\"movietaste\" value=\"thriller\" />\r\n");
      out.write("\t\t\t\t\t14: 스릴러 <span class=\"checkmark2\"></span>\r\n");
      out.write("\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t<label class=\"container2\"> \r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" id=\"movietaste\" name=\"movietaste\" value=\"musical\" />\r\n");
      out.write("\t\t\t\t\t15: 뮤지컬 <span class=\"checkmark2\"></span>\r\n");
      out.write("\t\t\t\t</label><br>\r\n");
      out.write("\t\t\t\t<label class=\"container2\"> \r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" id=\"movietaste\" name=\"movietaste\" value=\"opera\" />\r\n");
      out.write("\t\t\t\t\t16: 공연 <span class=\"checkmark2\"></span>\r\n");
      out.write("\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t<label class=\"container2\"> \r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" id=\"movietaste\" name=\"movietaste\" value=\"fantasy\" />\r\n");
      out.write("\t\t\t\t\t17: 판타지 <span class=\"checkmark2\"></span>\r\n");
      out.write("\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t<label class=\"container2\"> \r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" id=\"movietaste\" name=\"movietaste\" value=\"adventure\" />\r\n");
      out.write("\t\t\t\t\t18: 어드벤처 <span class=\"checkmark2\"></span>\r\n");
      out.write("\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t<label class=\"container2\"> \r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" id=\"movietaste\" name=\"movietaste\" value=\"historical\" />\r\n");
      out.write("\t\t\t\t\t19: 사극 <span class=\"checkmark2\"></span>\r\n");
      out.write("\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t<label class=\"container2\"> \r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" id=\"movietaste\" name=\"movietaste\" value=\"western\" />\r\n");
      out.write("\t\t\t\t\t20: 서부극(웨스턴) <span class=\"checkmark2\"></span>\r\n");
      out.write("\t\t\t\t</label>\r\n");
      out.write("\t\t\t\t<br><br>\r\n");
      out.write("\t\t\t\t<div class=\"button\">\r\n");
      out.write("\t\t\t\t\t<input type=\"button\" id=\"I\" value=\"제출\">\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</section>\r\n");
      out.write("\t</div>\r\n");
      out.write("<!-- Footer -->\r\n");
      out.write("<footer class=\"w3-center w3-black w3-padding-16\">\r\n");
      out.write("  <p>Powered by <a href=\"../../springProject/emotion/mainpage.ggd\" title=\"GOGODA\" target=\"_blank\" class=\"w3-hover-text-green\">GOGODA</a></p>\r\n");
      out.write("</footer>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
