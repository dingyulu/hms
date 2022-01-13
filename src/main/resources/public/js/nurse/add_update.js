layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    form.on("submit(addOrUpdateNurse)", function (obj) {
        console.log(obj.field.id)
        //判断是添加还是修改，id==null,添加 id！=null修改
        var url = ctx+"/nurse/save"
        //判断当前的页面的隐藏域有没有id，有id做修改没id做添加
        console.log($("input[name='id']").val());
        if ($("input[name='id']").val()) {
           var url = ctx + "/nurse/update"
            $.ajax({
                type:"post",
                url: url,
                data: obj.field,
                dataType: "json",
                success: function (data) {
                    if (data.code == 200) {
                        layer.msg("修改成功");
                        //刷新页面
                        window.parent.location.reload();
                    } else {
                        layer.msg(data.msg, {icon: 6});
                    }
                }
            });
        }else {
            $.ajax({
                type:"post",
                url: url,
                data: obj.field,
                dataType: "json",
                success: function (data) {
                    if (data.code == 200) {
                        layer.msg("添加ok");
                        //刷新页面
                        window.parent.location.reload();
                    } else {
                        layer.msg(data.msg, {icon: 6});
                    }
                }
            });
        }

        return false;
    });

    $("#closeBtn").click(function () {
        //假设这是iframe页
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    })
    /**
     * 加载下拉框
     */
    var designOne = $("input[name='designOne']").val();
    $.post(ctx + "/nurse/allpatient",function (data) {
        console.log(designOne)
        console.log(data)
        console.log(data.length)
        // 如果是修改操作，判断当前修改记录的指派人的值
        for (var i = 0; i < data.length; i++) {
// 当前修改记录的指派人的值 与 循环到的值 相等，下拉框则选中
            console.log(data[i].user_name);
            if (designOne==data[i].id) {
                $("#designOne").append('<option value="'+data[i].id+'"selected>'+data[i].user_name+'</option>');
            } else {
                $("#designOne").append('<option value="'+data[i].id+'">'+data[i].user_name+'</option>');
            }
        }
        // 重新渲染下拉框内容
        layui.form.render("select");
    });
});