/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.60
 * Generated at: 2021-03-11 03:29:14 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.view.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class daum_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>다음 API HELLO</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("도로명 주소 검색중!\r\n");
      out.write("<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->\r\n");
      out.write("<div id=\"layer\" style=\"display:block;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;\">\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script src=\"http://dmaps.daum.net/map_js_init/postcode.v2.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("window.addEventListener(\"message\", onReceivedPostMessage, false);\r\n");
      out.write("\r\n");
      out.write("function onReceivedPostMessage(event){\r\n");
      out.write("     //..ex deconstruct event into action & params\r\n");
      out.write("     var action = event.data.action;\r\n");
      out.write("     var params = event.data.params;\r\n");
      out.write("\r\n");
      out.write("     console.log(\"onReceivedPostMessage \"+event);\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function onReceivedActivityMessageViaJavascriptInterface(json){\r\n");
      out.write("     //..ex deconstruct data into action & params\r\n");
      out.write("     var data = JSON.parse(json);\r\n");
      out.write("     var action = data.action;\r\n");
      out.write("     var params = data.params;\r\n");
      out.write("       console.log(\"onReceivedActivityMessageViaJavascriptInterface \"+event);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    // 우편번호 찾기 화면을 넣을 element\r\n");
      out.write("    var element_layer = document.getElementById('layer');\r\n");
      out.write("\r\n");
      out.write("    function sample2_execDaumPostcode() {\r\n");
      out.write("        new daum.Postcode({\r\n");
      out.write("            oncomplete: function(data) {\r\n");
      out.write("\t\t\t\r\n");
      out.write("                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.\r\n");
      out.write("\r\n");
      out.write("                // 각 주소의 노출 규칙에 따라 주소를 조합한다.\r\n");
      out.write("                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.\r\n");
      out.write("                var fullAddr = data.address; // 최종 주소 변수\r\n");
      out.write("                var extraAddr = ''; // 조합형 주소 변수\r\n");
      out.write("\r\n");
      out.write("                // 기본 주소가 도로명 타입일때 조합한다.\r\n");
      out.write("                if(data.addressType === 'R'){\r\n");
      out.write("                    //법정동명이 있을 경우 추가한다.\r\n");
      out.write("                    if(data.bname !== ''){\r\n");
      out.write("                        extraAddr += data.bname;\r\n");
      out.write("                    }\r\n");
      out.write("                    // 건물명이 있을 경우 추가한다.\r\n");
      out.write("                    if(data.buildingName !== ''){\r\n");
      out.write("                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);\r\n");
      out.write("                    }\r\n");
      out.write("                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.\r\n");
      out.write("                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');\r\n");
      out.write("                }\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tvar fullRoadAddr = data.roadAddress; // 도로명 주소 변수\r\n");
      out.write("                var extraRoadAddr = ''; // 도로명 조합형 주소 변수\r\n");
      out.write("\r\n");
      out.write("                // 법정동명이 있을 경우 추가한다. (법정리는 제외)\r\n");
      out.write("                // 법정동의 경우 마지막 문자가 \"동/로/가\"로 끝난다.\r\n");
      out.write("                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){\r\n");
      out.write("                    extraRoadAddr += data.bname;\r\n");
      out.write("                }\r\n");
      out.write("                // 건물명이 있고, 공동주택일 경우 추가한다.\r\n");
      out.write("                if(data.buildingName !== '' && data.apartment === 'Y'){\r\n");
      out.write("                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);\r\n");
      out.write("                }\r\n");
      out.write("                // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.\r\n");
      out.write("                if(extraRoadAddr !== ''){\r\n");
      out.write("                    extraRoadAddr = ' (' + extraRoadAddr + ')';\r\n");
      out.write("                }\r\n");
      out.write("                // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.\r\n");
      out.write("                if(fullRoadAddr !== ''){\r\n");
      out.write("                    fullRoadAddr += extraRoadAddr;\r\n");
      out.write("                }\t\t\t\t\r\n");
      out.write("\r\n");
      out.write("\t\t\t\twindow.Android.processDATA(data.zonecode + \", \" + fullRoadAddr);\r\n");
      out.write("            },\r\n");
      out.write("            width : '100%',\r\n");
      out.write("            height : '100%'\r\n");
      out.write("        }).embed(element_layer);\r\n");
      out.write("\r\n");
      out.write("        // iframe을 넣은 element를 보이게 한다.\r\n");
      out.write("        element_layer.style.display = 'block';\r\n");
      out.write("\r\n");
      out.write("        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.\r\n");
      out.write("        initLayerPosition();\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는\r\n");
      out.write("    // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,\r\n");
      out.write("    // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.\r\n");
      out.write("    function initLayerPosition(){\r\n");
      out.write("        var width = (window.innerWidth || document.documentElement.clientWidth); //우편번호서비스가 들어갈 element의 width\r\n");
      out.write("        var height = (window.innerHeight || document.documentElement.clientHeight); //우편번호서비스가 들어갈 element의 height\r\n");
      out.write("        var borderWidth = 5; //샘플에서 사용하는 border의 두께\r\n");
      out.write("\r\n");
      out.write("        // 위에서 선언한 값들을 실제 element에 넣는다.\r\n");
      out.write("        element_layer.style.width = width + 'px';\r\n");
      out.write("        element_layer.style.height = height + 'px';\r\n");
      out.write("        element_layer.style.border = borderWidth + 'px solid';\r\n");
      out.write("        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.\r\n");
      out.write("        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';\r\n");
      out.write("        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';\r\n");
      out.write("    }\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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
