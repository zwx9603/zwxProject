<%@ page import="org.jasig.cas.client.authentication.AttributePrincipal" %>
<%@ page import="org.jasig.cas.client.util.AbstractCasFilter" %>
<%@ page import="org.jasig.cas.client.validation.Assertion" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<%
    ServletContext servletContext = request.getSession().getServletContext();
    String appUrl = servletContext.getInitParameter("appWebUrl");
    String account = null;
    String language = null;
    String password=null;
    AttributePrincipal principal = null;
    Assertion assertion = (Assertion) request.getSession(true).getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
    if (assertion != null) {
        principal = assertion.getPrincipal();
        Map<String, String> userInfo = principal.getAttributes();
        account = userInfo.get("XTMC");
        language = userInfo.get("YY");
        password=userInfo.get("MM");
    }
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>sss</title>

</head>
<body>

<script type="text/javascript">
    var appWebUrl = '<%=appUrl%>';
    var lang = '<%=language%>';
    var user = '<%=account%>';
    var password = '<%=password%>';
    //直接跳转到前端（单）页面地址
    // window.location.href = "http://192.168.7.122:28001/index.html?user="+user+"&lang="+lang+"&r=0.5826527188441633&r=0.19661860151659494";
    // alert(appWebUrl + "?user=" + user + "&lang=" + lang + "&r=0.5628029981601781");
    window.location.href = appWebUrl + "?user=" + user + "&lang=" + lang +"&password="+password+ "&r=0.5628029981601781";

    function getQueryString(name) {
        var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    }

</script>


</body>
</html>