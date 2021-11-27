<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>获取两坐标之间的距离</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}../js/jquery-3.6.0.js"></script>
<body>
    <form>
        起点坐标X:<input id="qjingdu_x" value="" placeholder="起点坐标的经度">
        起点坐标Y:<input id="qweidu_x" value="" placeholder="起点坐标的纬度"><br>
        终点坐标X:<input id="zjingdu_x" value="" placeholder="终点坐标的经度">
        终点坐标Y:<input id="zweidu_y" value="" placeholder="终点坐标的纬度"><br>
        距离:<input id="distance" value="" placeholder="两点之间的距离"><br>
        <input id="commintId" type="submit" value="提交" ><br>
    </form>
</body>
<script type="text/javascript">
    $("#commintId").click(function () {
        alert("进入方法了")
        var qx = $("#qjingdu_x").val()
        alert(qx)
        var qy = $("#qweidu_x").val()
        var zx = $("#zjingdu_x").val()
        var zy = $("#zweidu_y").val()
        alert(zy)
        $.ajax({
            type:"post",
            url:"localhost:8280/distanceController/getDistance",
            data:{qx:qx,qy:qy,zx:zx,zy:zy},
            dataType:"json",
            success:function (e) {
                alert("数据返回成功，进入的方法")
                if(e != null){
                    $("#distance").value() == e;
                }
            }
        })
    });

</script>
</html>