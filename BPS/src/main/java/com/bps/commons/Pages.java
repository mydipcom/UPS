package com.bps.commons;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: Uswop Middle Ware</p>
 *
 * <p>Description: 分页标记处理类</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: CampRay</p>
 *
 * @author Phills
 * @version 3.1
 */

public class Pages {

  private HttpServletRequest request = null;
  private String pageLink = "";
  //指向页号
  private int page = 1;
  //总记录数
  private int totals = 0;
  //每页显示记录数
  private int perpagenum = 10;
  //分页样式
  private int style = 0;
  //总页数
  private int allpage = 0;
  //当前页数
  private int cpage = 1;
  //起始记录数,方便得到从数据库中查询的起始记录位置
  private int srecords = 0;
  //分页处理后，返回到页面显示的链接型分页标记字符串
  private String webLinkStr = "";
  //分页处理后，返回到页面显示的跳转按钮型分页标记字符串
  private String webJumpStr = "";
  //分页处理后，返回到页面显示的分页记录内容字符串
  private String webInfoStr = "";
  //分页处理后，返回到页面显示的客户端脚本字符串
  private String webScriptStr = "";
  //分页标签文字数组
  private String[] pagesign = {
      "首页", "上一页", "下一页", "末页"};
  //按钮样式
  private String buttonStyle = "FONT-SIZE: 12px;BORDER-RIGHT: #555 1px solid; BORDER-TOP: #bbb 1px solid; BACKGROUND: #ece9d8; BORDER-LEFT: #bbb 1px solid; BORDER-BOTTOM: #555 1px solid; HEIGHT: 20px";
  //输入框样式
  private String inputStyle = "FONT-SIZE: 12px;BORDER-RIGHT: #555 1px solid; BORDER-TOP: #555 1px solid; BORDER-LEFT: #555 1px solid; BORDER-BOTTOM: #555 1px solid; HEIGHT: 20px";
  //分页查询请求的表单名
  private String formName = null;

  //分页样式静态常量:字符链接样式的{首页 上一页 下一页 末页}
  public static final int SYTLE_LINK = 0;
//  分页样式静态常量:字符链接样式的{首页 上一页 下一页 末页},{共 18 条 第 1/2 页}
  public static final int SYTLE_LINK_INFO = 1;
//  分页样式静态常量:字符链接样式的{首页 上一页 下一页 末页},{共 18 条 第 1/2 页},{转至[]}
  public static final int SYTLE_LINK_INFO_JUMP = 2;
//  分页样式静态常量:字符链接样式的{首页 上一页 下一页 末页},{共 18 条 第 1/2 页},{转至[]}
  public static final int SYTLE_LINK_INFO_SELECT = 3;

//  分页样式静态常量:命令以按钮样式的{首页 上一页 下一页 末页}
  public static final int SYTLE_BUTTON = 4;
//  分页样式静态常量:命令以按钮样式的{首页 上一页 下一页 末页},{共 18 条 第 1/2 页}
  public static final int SYTLE_BUTTON_INFO = 5;
//  分页样式静态常量:命令以按钮样式的{首页 上一页 下一页 末页},{共 18 条 第 1/2 页},{转至[]}
  public static final int SYTLE_BUTTON_INFO_JUMP = 6;
//  分页样式静态常量:命令以按钮样式的{首页 上一页 下一页 末页},{共 18 条 第 1/2 页},{转至[]}
  public static final int SYTLE_BUTTON_INFO_SELECT = 7;
  //  分页样式静态常量:图形样式的{ << < 1 > >> }
  public static final int SYTLE_ICO_LINK = 8;

  /*
   * 构造函数
   * @param request HttpServletRequest页面请求对象
   */
  public Pages(HttpServletRequest request) {
    this.request = request;
    String queryStr = request.getQueryString();
    StringBuffer queryURL = request.getRequestURL();
    if(queryStr!=null){
        if(queryStr.indexOf("page=") != -1){
            this.pageLink = queryURL.append("?" +
                                            queryStr.substring(0,
                queryStr.indexOf("page=") - 1)).toString();
        }
        else{
            this.pageLink = queryURL.append("?" + queryStr).toString();
        }
    }
    else{
        this.pageLink=queryURL.toString();
    }
    if (request.getParameter("page") != null &&
        !request.getParameter("page").trim().equals("")) {
      this.page = Integer.parseInt(request.getParameter("page"));
    }
    if (request.getParameter("t") != null &&
        !request.getParameter("t").trim().equals("")) {
      this.totals = Integer.parseInt(request.getParameter("t"));
    }

  }

  /*
   * 构造函数
   * @param request HttpServletRequest页面请求对象
   * @param perpagenum 每页要显示的记录数
   * @param style  分页样式，可以从本类的静态常量得到八种分页样式
   */
  public Pages(HttpServletRequest request, int perpagenum,
               int style) {
    this.request = request;
    String queryStr = request.getQueryString();
    StringBuffer queryURL = request.getRequestURL();
    if(queryStr!=null){
        if(queryStr.indexOf("page=") != -1){
            this.pageLink = queryURL.append("?" +
                                            queryStr.substring(0,
                queryStr.indexOf("page=") - 1)).toString();
        }
        else{
            this.pageLink = queryURL.append("?" + queryStr).toString();
        }
    }
    else{
        this.pageLink = queryURL.toString();
    }
    if (request.getParameter("page") != null &&
        !request.getParameter("page").trim().equals("")) {
      this.page = Integer.parseInt(request.getParameter("page"));
    }
    if (request.getParameter("t") != null &&
        !request.getParameter("t").trim().equals("")) {
      this.totals = Integer.parseInt(request.getParameter("t"));
    }
    this.perpagenum = perpagenum;
    this.style = style;
  }

  /*
   * 构造函数
   * @param request HttpServletRequest页面请求对象
   * @param totals 分页总记录数
   * @param perpagenum 每页要显示的记录数
   * @param style  分页样式，可以从本类的静态常量得到八种分页样式
   */
  public Pages(HttpServletRequest request, int totals, int perpagenum,
               int style) {
    this.request = request;
    String queryStr = request.getQueryString();
    StringBuffer queryURL = request.getRequestURL();
    if(queryStr!=null){
        if(queryStr.indexOf("page=") != -1){
            this.pageLink = queryURL.append("?" +
                                            queryStr.substring(0,
                queryStr.indexOf("page=") - 1)).toString();
        }
        else{
            this.pageLink = queryURL.append("?" + queryStr).toString();
        }
    }
    else{
        this.pageLink = queryURL.toString();
    }
    if (request.getParameter("page") != null &&
        !request.getParameter("page").trim().equals("")) {
      this.page = Integer.parseInt(request.getParameter("page"));
    }
    this.totals = totals;

    this.perpagenum = perpagenum;
    this.style = style;
  }

  /*
   * 构造函数
   * @param totals 分页总记录数
   * @param perpagenum 每页要显示的记录数
   * @param style  分页样式，可以从本类的静态常量得到八种分页样式
   * @description 因为没有传入HttpServletRequest对象，
   *   所以需要另外设置pageLink属性值，设置分页请求的URL
   *
   */
  public Pages(int totals, int perpagenum, int style) {
    this.totals = totals;
    this.perpagenum = perpagenum;
    this.style = style;
  }

  /*
   * 构造函数
   * @param page 当前页数
   * @param totals 分页总记录数
   * @param perpagenum 每页要显示的记录数
   * @param style  分页样式，可以从本类的静态常量得到八种分页样式
   * @description 因为没有传入HttpServletRequest对象，
   *   所以需要另外设置pageLink属性值，设置分页请求的URL
   *
   */
  public Pages(int page, int totals,
               int perpagenum, int style) {
    this.page = page;
    this.totals = totals;
    this.perpagenum = perpagenum;
    this.style = style;
  }

  public String getPageLink() {
    return this.pageLink;
  }

  public void setPageLink(String PageLink) {
    this.pageLink = PageLink;
  }

  public int getPage() {
    return this.page;
  }

  public void setPage(int aPage) {
    this.page = aPage;
  }

  public int getTotals() {
    return this.totals;
  }

  public void setTotals(int aTotals) {
    this.totals = aTotals;
  }

  public int getPerpagenum() {
    return this.perpagenum;
  }

  public void setPerpagenum(int perpagenum) {
    this.perpagenum = perpagenum;
  }

  public int getStyle() {
    return this.style;
  }

  public void setStyle(int aStyle) {
    this.style = aStyle;
  }

  public void setPagesign(String[] apagesign) {
    this.pagesign = apagesign;
  }

  public HttpServletRequest getRequest() {
    return request;
  }

  public void setRequest(HttpServletRequest request) {
    this.request = request;
  }

  public String getButtonStyle() {
    return buttonStyle;
  }

  public void setButtonStyle(String buttonStyle) {
    this.buttonStyle = buttonStyle;
  }

  public String getInputStyle() {
    return buttonStyle;
  }

  public void setInputStyle(String buttonStyle) {
    this.buttonStyle = buttonStyle;
  }

  public int getSrecords() {
    return this.srecords;
  }

  public String getWebInfoStr() {

    return webInfoStr;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }

  public void setWebScriptStr(String webScriptStr) {

    this.webScriptStr = webScriptStr;
  }

  public String getFormName() {
    return formName;
  }

  public String getWebScriptStr() {

    return webScriptStr;
  }

  public String getWebJumpStr() {
    return webJumpStr;
  }

  public String getWebLinkStr() {
    return webLinkStr;
  }

  /**
   * 执行分页命令
   */
  public void doPageBreak() {
    if (this.perpagenum == 0) {
      this.allpage = 0;
    }
    else {
      this.allpage = (int) Math.ceil( (this.totals + this.perpagenum - 1) /
                                     this.perpagenum);
    }
    int intPage = this.page;
    //如果指向页数比总页数大(只有在总页数为0时),设置当前页为1
    if (intPage > this.allpage) { // pages == 0
      this.cpage = 1;
    }
    else {
      this.cpage = intPage;
    }
    this.srecords = (this.cpage - 1) * this.perpagenum;

    if (this.pageLink.indexOf("?") == -1) {
      this.pageLink = this.pageLink + "?";
    }
    else {
      if (!this.pageLink.endsWith("&")) {
        this.pageLink = this.pageLink + "&";
      }
    }

    getPageBreakStr();
  }

  private void getPageBreakStr() {

    //分页样式一:链接字符串样式的{首页 上一页 下一页 末页}
    if (this.style == 0) {
      this.webLinkStr = getPageBreakLinkString();
      return;
    }
    //分页样式二:链接字符串样式的{首页 上一页 下一页 末页},{共 18 条 第 1/2 页}
    if (this.style == 1) {
      this.webLinkStr = getPageBreakLinkString();
      this.webInfoStr = getPageBreakInfoString();
      return;
    }
    //分页样式三:链接字符串样式的{首页 上一页 下一页 末页},{ 共 18 条 第 1/2 页},{转至[]}
    if (this.style == 2) {
      this.webLinkStr = getPageBreakLinkString();
      this.webInfoStr = getPageBreakInfoString();
      this.webJumpStr = getPageBreakJumpButtonString();
      return;
    }
//      分页样式四:链接字符串样式的{首页 上一页 下一页 末页},{ 共 18 条 第 1/2 页},{[]选择页号下拉列表}
    if (this.style == 3) {
      this.webLinkStr = getPageBreakLinkString();
      this.webInfoStr = getPageBreakInfoString();
      this.webJumpStr = getPageBreakSelectButtonString();
      return;
    }
    //分页样式五:按钮样式的{首页 上一页 下一页 末页}
    if (this.style == 4) {
      this.webLinkStr = getPageBreakButtonString();
      return;
    }
    //分页样式六:按钮样式的{首页 上一页 下一页 末页},{ 共 18 条 第 1/2 页}
    if (this.style == 5) {
      this.webLinkStr = getPageBreakButtonString();
      this.webInfoStr = getPageBreakInfoString();
      return;
    }
    //分页样式七:按钮样式的{首页 上一页 下一页 末页},{ 共 18 条 第 1/2 页},{转至[]}
    if (this.style == 6) {
      this.webLinkStr = getPageBreakButtonString();
      this.webInfoStr = getPageBreakInfoString();
      this.webJumpStr = getPageBreakJumpButtonString();
      return;
    }
    //分页样式八:按钮样式的{首页 上一页 下一页 末页},{ 共 18 条 第 1/2 页},{[]选择页号下拉列表}
    if (this.style == 7) {
      this.webLinkStr = getPageBreakButtonString();
      this.webInfoStr = getPageBreakInfoString();
      this.webJumpStr = getPageBreakSelectButtonString();
      return;
    }
    //分页样式九:图形样式的{ << < 1 > >> }
    if (this.style == 8) {
      this.webInfoStr = getPageBreakInfoString();
      this.webLinkStr = getPageBreakIcoString();
      return;
    }


  }

  //得到链接型分页标记字符串
  private String getPageBreakLinkString() {

    StringBuffer sb = new StringBuffer();
    sb.append("\n<SCRIPT language=javascript>\n");
    sb.append("<!--\n");
    sb.append("function movepage(pageno){\n");
    sb.append("document.all(\"" + this.formName + "\").action=\"");
    sb.append(this.pageLink +"page=\"+pageno+\"&t=" + this.totals + "\";\n");
    sb.append("document.all(\"" + this.formName + "\").submit();\n");
    sb.append("}\n");
    sb.append("-->\n");
    sb.append("</SCRIPT>\n");


    String enableFlag = cpage > 1 ? "" : "disabled";
    sb.append("<a " + enableFlag + " "+formScript(1)+">");
    sb.append(this.pagesign[0]);
    sb.append("</a> <a " + enableFlag + " "+formScript(cpage - 1)+">");
    sb.append(pagesign[1]);
    sb.append("</a> ");

    enableFlag = cpage < allpage ? "" : "disabled";
    sb.append("<a " + enableFlag + " "+formScript(cpage + 1)+">");
    sb.append(pagesign[2]);
    sb.append("</a> <a " + enableFlag + " "+formScript(this.allpage)+">");
    sb.append(pagesign[3]);
    sb.append("</a> ");

    return sb.toString();

  }

  //得到按钮型分页标记字符串
  private String getPageBreakButtonString() {
    StringBuffer sb = new StringBuffer();
    //分页按钮，并得到按钮是否能用的标志
    String enableFlag = cpage > 1 ? "" : "disabled";
    sb.append("<INPUT style=\"" + buttonStyle + "\" " + enableFlag);
    sb.append(" onclick=\"movePage('first')\" type=button value=\"");
    sb.append(pagesign[0] + "\" name=first> ");
    sb.append("<INPUT style=\"" + buttonStyle + "\" " + enableFlag +
              " onclick=\"movePage('prev')\" type=button value=\"" +
              pagesign[1] + "\" name=prev> ");
    enableFlag = cpage < allpage ? "" : "disabled";
    sb.append("<INPUT style=\"" + buttonStyle + "\" " + enableFlag +
              " onclick=\"movePage('next')\" type=button value=\"" +
              pagesign[2] + "\" name=next> ");
    sb.append("<INPUT style=\"" + buttonStyle + "\" " + enableFlag +
              " onclick=\"movePage('last')\" type=button value=\"" +
              pagesign[3] + "\" name=last> ");

    sb.append("\n<SCRIPT language=javascript>\n");
    sb.append("<!--\n");
    sb.append("var currActionsUrl=\"" + this.pageLink + "\";\n");
    sb.append("function movePage(page){\n");
    sb.append("var pageno=1;\n");
    sb.append("var CurrentPage =" + this.cpage + ";\n");
    sb.append("var TotalPages=" + this.allpage + ";\n");
    sb.append("if(page==\"first\"){\n");
    sb.append("pageno=1;\n");
    sb.append("}else if(page==\"last\"){\n");
    sb.append("pageno=TotalPages;\n");
    sb.append("}else if(page==\"prev\"){\n");
    sb.append("pageno=CurrentPage-1;\n");
    sb.append("if(pageno<1)pageno=1;\n");
    sb.append("}else if(page==\"next\"){\n");
    sb.append("pageno=CurrentPage+1;\n");
    sb.append("if(pageno>TotalPages)pageno=TotalPages;\n");
    sb.append("}\n");
    //不通过表单提交分页请求,而是用请求字符串提交
    if (this.formName == null || this.formName.trim().equals("")) {
      sb.append("location.href=\"" + this.pageLink +
                "page=\"+pageno+\"&t=" + this.totals + "\";\n");
    }
    else { //通过表单提交分页请求,用在表单内容较多的情况
      sb.append("document.all(\"" + this.formName + "\").action=\"" +
                this.pageLink +
                "page=\"+pageno+\"&t=" + this.totals + "\";\n");
      sb.append("document.all(\"" + this.formName + "\").submit();");
    }
    sb.append("}\n");
    sb.append("-->\n");
    sb.append("</SCRIPT>\n");

    return sb.toString();
  }

  //得到分页记录显示标记字符串
  private String getPageBreakInfoString() {
    int _cpage = 0;
    if (this.allpage == 0) {
      _cpage = 0;
    }
    else {
      _cpage = cpage;
    }

    StringBuffer sb = new StringBuffer();
    sb.append("共" + String.valueOf(this.totals) + "条记录");
    sb.append("，第" + String.valueOf(_cpage) + "/" +
              String.valueOf(this.allpage) + "页");
    return sb.toString();

  }

  //得到按钮型分页跳转标记字符串
  private String getPageBreakJumpButtonString() {
    StringBuffer sb = new StringBuffer();

    String enableFlag = allpage > 1 ? "" : "disabled";
    sb.append("<INPUT style=\"" + buttonStyle + "\" " + enableFlag +
              " type=button value=转至 onclick=\"goPage(document.all('_goto').value)\">");
    sb.append("<INPUT style=\"" + inputStyle + "\" " + enableFlag +
              " onkeydown=\"returnPage(this);\" type=text size=2 name=_goto>");
    //客户端跳转脚本函数
    sb.append("\n<script>\n");
    sb.append("<!--\n");

    sb.append("function goPage(page){\n");
    sb.append("pageno = parseInt(page,10);\n");
    sb.append("if(isNaN(pageno)){\n");
    sb.append("alert(\"您要转至的页面不能为空且必须为数字！\");\n");
    sb.append("document.all(\"_goto\").value=\"\";\n");
    sb.append(
        "document.all(\"_goto\").style.background=\"#ddd\";\n");
    sb.append("document.all(\"_goto\").focus();\n");
    sb.append("return false;\n");
    sb.append("}\n");

    sb.append("var TotalPages=" + this.allpage + ";\n");
    sb.append("if(pageno>TotalPages || pageno<1){\n");
    sb.append("alert('The forworded page out of the scope.');\n");
    sb.append("document.all(\"_goto\").value=\"\";\n");
    sb.append(
        "document.all(\"_goto\").style.background=\"#ddd\";\n");
    sb.append("document.all(\"_goto\").focus();\n");
    sb.append("return false;\n");
    sb.append("}\n");

    //如果设置了formName属性值，那么表单提交分页请求,否则通过请求字符串提交
    if (this.formName == null || this.formName.trim().equals("")) {
      sb.append("location=\"" + this.pageLink);
      sb.append("page=\"+pageno+\"&t=" + this.totals + "\";\n}\n");
    }
    else {
      sb.append("document.all(\"" + this.formName + "\").action=\"");
      sb.append(this.pageLink + "page=\"+pageno+\"&t=" + this.totals + "\";");
      sb.append("\ndocument.all(\"" + this.formName + "\").submit();}\n");

    }

    sb.append("function returnPage(el) {\n");
    sb.append("document.all(\"_goto\").style.background=\"#fff\";\n");
    sb.append("if(window.event.keyCode==13) {\n");
    sb.append("goPage(el.value);\n");
    sb.append("return false;\n");
    sb.append("}}\n");

    sb.append("-->\n");
    sb.append("</SCRIPT>\n");

    return sb.toString();
  }

//  得到下拉列表型分页跳转标记字符串
  private String getPageBreakSelectButtonString() {
    StringBuffer sb = new StringBuffer();

    String enableFlag = allpage > 1 ? "" : "disabled";
    sb.append("<select name=\"p_select\" " + enableFlag + " style=\"" +
              inputStyle + "\" onChange=\"goPage(this.value);\">\n");
    for (int i = 1; i <= this.allpage; i++) {
      String selected = this.page == i ? "selected" : "";
      sb.append("<option value=" + i + " " + selected + ">" + i + "</option>\n");
    }
    sb.append("</select>\n");
    //客户端跳转脚本函数
    sb.append("\n<script>\n");
    sb.append("<!--\n");
    sb.append("function goPage(page){\n");
    sb.append("pageno = parseInt(page,10);\n");
    //不通过表单提交分页请求,而是用请求字符串提交
    if (this.formName == null || this.formName.trim().equals("")) {
      sb.append("location=\"" + this.pageLink);
      sb.append("page=\"+pageno+\"&t=" + this.totals + "\";\n}\n");
    }
    else {
      sb.append("document.all(\"" + this.formName + "\").action=\"");
      sb.append(this.pageLink + "page=\"+pageno+\"&t=" + this.totals + "\";");
      sb.append("\ndocument.all(\"" + this.formName + "\").submit();}\n");

    }
    sb.append("-->\n");
    sb.append("</SCRIPT>\n");

    return sb.toString();
  }

  //得到图形分页标记字符串(样式8)
  private String getPageBreakIcoString() {
    StringBuffer sb = new StringBuffer();

    sb.append("\n<SCRIPT language=javascript>\n");
    sb.append("<!--\n");
    sb.append("function movepage(pageno){\n");
    sb.append("document.all(\"" + this.formName + "\").action=\"");
    sb.append(this.pageLink +"page=\"+pageno+\"&t=" + this.totals + "\";\n");
    sb.append("document.all(\"" + this.formName + "\").submit();\n");
    sb.append("}\n");
    sb.append("-->\n");
    sb.append("</SCRIPT>\n");


    int cp = cpage > 1 ? cpage - 1 : 1;
    sb.append("<a "+formScript(1)+">");
    sb.append("<img border=0 src=\"images/arrow_first.gif\">");
    sb.append("</a> <a  "+formScript(cp)+">");
    sb.append("<img border=0 src=\"images/arrow_previous.gif\" >");
    sb.append("</a> ");

    sb.append(cpage+"/" +this.allpage);

    cp = cpage < allpage?cpage + 1 : allpage;
    sb.append(" <a  "+formScript(cp)+">");
    sb.append("<img border=0 src=\"images/arrow_next.gif\" >");
    sb.append("</a> <a  "+formScript(this.allpage)+">");
    sb.append("<img border=0 src=\"images/arrow_last.gif\" >");
    sb.append("</a> ");

    return sb.toString();

  }

  /**
   * 根据是否通过表单提交,得到分页脚本
   * @param pageno int
   * @return String
   */
  private String formScript(int pageno){
    String astr="";
      //不通过表单提交分页请求,而是用请求字符串提交
    if (this.formName == null || this.formName.trim().equals("")) {
      astr="href='"+this.pageLink+"page="+pageno+"&t="+this.totals+"'";
    }
    else { //通过表单提交分页请求,用在表单内容较多的情况
        astr="href='#' onclick='movepage("+pageno+")'";
    }
    return astr;
  }

}


//使用方法
/**在Jsp页面上加入下面的代码
  <logic:present name="pages" scope="request">
    <bean:write name="pages" property="webInfoStr" scope="request" filter="false" />
    <bean:write name="pages" property="webLinkStr" scope="request" filter="false"/>
    <bean:write name="pages" property="webJumpStr" scope="request" filter="false"/>
  </logic:present>
 */

/**在Action中加入下面的代码
 *
 *
 // 定义分页类
 Pages pages = null;
 //定义保存Bean对象的集合
 List resultList;
 // 根据请求参数是否为空，判断分页页面是不是第一次打开
 if (request.getParameter("page") == null)
 {
   // 调用业务处理类，查询每一页的查询结果集
    resultList=查询每一页记录集
 }
 else
 {
    // 从请求参数中取出要转向的页号
   int pageNo = Integer.parseInt(request.getParameter("page"));
   // 调用业务处理类，查询第pageNo页的查询结果集
   resultList=查询第pageNo页记录集
 }

  // 得到总记录数
  int total = 查询所有的记录数
  // 初始化分页处理类，参数为：request对象，总记录数，每页显示数，分页样式
  pages = new Pages(request, total, 10, Pages.SYTLE_BUTTON_INFO_JUMP);
  // 执行分页命令
  pages.doPageBreak();

  // 把BEAN集合保存在request对象中
  request.setAttribute("queryList", resultList);
  request.setAttribute("pages", pages);

 */
