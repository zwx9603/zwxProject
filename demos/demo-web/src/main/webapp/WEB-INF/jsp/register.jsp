<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
    <head>
    <meta charset="UTF-8">
    <title>注册</title>
    </head>
    <script src="${pageContext.request.contextPath}../js/jquery-3.6.0.js" type="text/javascript" charset="utf-8"></script>
    <style>
    body{
    background-image: url("${pageContext.request.contextPath}../picture/天空.jpg");
    background-repeat:repeat;
    background-size: 100%;
}
.securityCode {
    width: 60px;
}
input .button{
    width: 60px;
}
</style>
<body>
<div align="center" style="position: relative">
    <br>
    <h2>注册界面</h2>
    <br>
    <form method="get" action="/LoginSuccess">
    用户名: <input id="userName" type="text" name="username" placeholder="请输入用户名" required/><br><br>
密 码: <input id="passWord" type="password" name="password" placeholder="请输入密码" required/><br><br>
手机号: <input id="telePhone" type="text" name="telephone" placeholder="请输入手机号"/><br><br>
    验证码: <input id="securityCode" class="securityCode" type="text" name="securityCode"/>  <input id="securityCodeInfo" type="button" value="发送验证码" onclick="sendInfo()"/><br><br>
    <input type="button" value="提交" onclick="window.location.herf=''">
    </form>
    </div>
    </body>
    <script type="text/javascript">
    /* function securityCodeFunction(){

        //短信验证码倒计时
        var countdownHandler = function(){
            alert("进入方法了");
                var $button = $("#securityCodeInfo");
                var number = 60;
                var flag=1;
                var countdown = function(){
                    // debugger
                    if (number == 0) {
                        $button.attr("disabled",false);
                        $button.val("发送验证码");
                        $button.css({"background":"-webkit-gradient(linear, left top, right bottom, from(#e05b8d), to(#a94e97))","cursor":"pointer"});
                        number = 60;
                        flag=0;
                        return;
                    } else if( flag==1 ){
                        $button.attr("disabled",true);
                        $button.css({"background":"#a29e9e","cursor":"default"});
                        $button.val(number + "秒后重新获取");
                        number--;
                    }
                };
                setInterval(countdown,1000);
        }

            }
    } */

    //校验手机号格式
    function chackPhone(phone){
        re = /^1[3456789]\d{9}$/
        if (re.test(phone)) {
            return true;
        } else {
            return false;
        }
    }

//发送短信验证码
function sendInfo () {
    var userName = document.getElementById("userName").value
    var passWord = document.getElementById("passWord").value
    var telePhone = document.getElementById("telePhone").value
    if(userName == ''){
        alert('请输入用户名!');
        return;
    }
    if(passWord == ''){
        alert('请输入密码!');
        return;
    }
    if( telePhone == '' ) {
        alert('请输入手机号码!');
        return;
    }else if( chackPhone(telePhone)==false ){
        alert('请输入正确的手机号!');
        return;
    }
    alert("方法结束")
    $.ajax({


    })
    /* $.ajax({
        url: "localhost:8081/controller/sendSecurityCode",
        //async: true,
        type: 'POST',
            if(data.result=="success"){
                countdownHandler();
                $createTime.val(data1.sendData.createTime);
                $sendedVarCode.val(data1.sendData.sendedVarCode);
                return ;
            }else{
                console.error("验证码发送失败,请联系管理员!")
            }
    }); */
}
</script>
</html>
