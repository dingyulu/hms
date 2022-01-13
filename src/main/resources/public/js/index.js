layui.use(['form','jquery','jquery_cookie','layer'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    //监听提交
    form.on('submit(login)', function(data){
        //获取前端输入的数据
        var field = data.field;

        if(field.username=='undefined' || field.username==''){
            layer.msg("用户名不能为空");
            return ;
        }
        if(field.password=='undefined' || field.password==''){
            layer.msg("密码不能为空");
            return ;
        }
        //发送ajax请求
        $.ajax({
            type:"post",
            url:ctx+"/user/login",
            dataType:"json",
            data:{
                "userName":field.username,
                "userPwd":field.password
            },
            success:function(result){
                //ResultInfo
                if(result.code==200){
                    //成功
                    // layer.msg("登录成功",{icon:5});
                    //跳转主页面
                    // window.location.href=ctx+"/main";
                    layer.msg("登陆成功",function(){
                        $.cookie("userIdStr",result.result.userIdStr);
                        $.cookie("userName",result.result.userName);
                        $.cookie("trueName",result.result.trueName);

                        //判断是否选中了记住我选项，实现7天免登录
                        if($("input[type='checkbox']").is(":checked")){
                            $.cookie("userIdStr",result.result.userIdStr,{expires:7});
                            $.cookie("userName",result.result.userName,{expires:7});
                            $.cookie("trueName",result.result.trueName,{expires:7});
                        }



                        //跳转主页面
                        window.location.href=ctx+"/main";
                    })

                }else{
                    //失败
                    layer.msg(result.msg);
                }
            }

        })
        //取消默认行为
        return false;
    });
    
    
});