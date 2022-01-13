layui.use(['table', 'layer'], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    /**
     * 护士列表展示
     */
    var tableIns = table.render({
        elem: '#nurse', // 表格绑定的ID
        url: ctx + '/nurse/list', // 访问数据的地址
        cellMinWidth: 95,
        page: true, // 开启分页
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        toolbar: "#toolbarDemo",
        id: "saleChanceListTable",
        cols: [[
            {type: "checkbox", fixed: "center"},
            {field: "id", title: '编号', fixed: "true"},
            {field: 'userName', title: '姓名', align: "center"},
            /*{field: 'trueName', title: '真实姓名', align: 'center'},*/
            {field: 'idNumber', title: '身份证号', align: 'center'},
            {field: 'admittedDate', title: '入院工作时间', align: 'center'},
            {field: 'phone', title: '联系方式', align: 'center'},
            {field: 'email', title: '邮箱', align: 'center'},
            {field: 'pname', title: '看护病人', align: 'center'},
            {
                field: 'state', title: '分配状态', align: 'center', templet: function (d) {
                    return formatterState(d.state);
                }
            },
            {
                title: '操作',
                templet: '#saleChanceListBar', fixed: "right", align: "center", minWidth: 150
            }
        ]]
    });

    /**
     * 格式化分配状态
     * 0 - 未分配
     * 1 - 已分配
     * 其他 - 未知
     * @param state
     * @returns {string}
     */
    function formatterState(state) {
        if (state == 0) {
            return "<div style='color: yellow'>未分配</div>";
        } else if (state == 1) {
            return "<div style='color: green'>已分配</div>";
        } else {
            return "<div style='color: red'>未知</div>";
        }
    }

   /* /!**
     * 格式化开发状态
     * 0 - 未开发
     * 1 - 开发中
     * 2 - 开发成功
     * 3 - 开发失败
     * @param value
     * @returns {string}
     *!/
    function formatterDevResult(value) {
        if (value == 0) {
            return "<div style='color: yellow'>未开发</div>";
        } else if (value == 1) {
            return "<div style='color: #00FF00;'>开发中</div>";
        } else if (value == 2) {
            return "<div style='color: #00B83F'>开发成功</div>";
        } else if (value == 3) {
            return "<div style='color: red'>开发失败</div>";
        } else {
            return "<div style='color: #af0000'>未知</div>"
        }
    }*/

    /**
     * 绑定搜索按钮的点击事件
     */
    $(".search_btn").click(function () {
        table.reload('saleChanceListTable', {
            where: { //设定异步数据接口的额外参数，任意设
                userName: $("input[name='userName']").val(),
                idNumber: $("input[name='idNumber']").val()
            }
            , page: {
                curr: 1 // 重新从第 1 页开始
            }
        }); // 只重载数据
    });

    //头部工具栏 监听事件
    table.on('toolbar(saleChances)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'add':
                // 点击添加按钮，打开添加营销机会的对话框
                openAddOrUpdateSaleChanceDialog();
                break;
            case 'del':
                //layer.msg('删除');
                deleteSaleChance(checkStatus.data);
                break;
        }
    });

    function deleteSaleChance(data) {
        if (data.length == 0) {
            layer.msg("请选择删除的数据");
            return;
        }
        layer.confirm("您确定要删除选中的记录吗？",{
            btn:["确认","取消"],
        },function (index) {
            // 关闭确认框
            layer.close(index);
        //收集数据
        var ids = [];
        //遍历
        for (var x in data) {
            ids.push(data[x].id);
        }
        //发送ajax
        $.ajax({
            type: "post",
            url: ctx + "/nurse/dels",
            data: {"ids": ids.toString()},
            dataType: "json",
            success: function (result) {
                if (result.code == 200) {
                    layer.msg("删除OK", {icon: 5});
                    tableIns.reload();
                } else {
                    layer.msg(result.msg);
                }
            }
        });
    });
    }
    /**
     * 添加修改
     * @param saleChanceId
     */
    function openAddOrUpdateSaleChanceDialog(id) {
        var title = "<h3>护士管理 - 信息添加<h3>";
        var url = ctx + "/nurse/addOrUpdateSaleChancePage";

        if (id) {
            title = "<h3>护士管理 - 信息修改<h3>";
            url = url + "?id=" + id;
        }
        /**弹出层
         *
         */
        layui.layer.open({
            title: title,
            content: url,
            type: 2,
            area: ["500px", "620px"],
            maxmin: true
        });
    }

    //工具条事件
    table.on('tool(saleChances)', function (obj)
    { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

        if (layEvent === 'del') { //删除
            /*layer.confirm('真的删除行么', function (index) {
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index);
                //向服务端发送删除指令
            });*/
            layer.confirm("您确定要删除选中的记录吗？",{
                btn:["确认","取消"],
            },function (index) {
                // 关闭确认框
                layer.close(index);
                //发送ajax
                $.ajax({
                    type: "post",
                    url: ctx + "/nurse/dels",
                    data: {"ids":data.id},
                    dataType: "json",
                    success: function (result) {
                        if (result.code == 200) {
                            layer.msg("删除OK", {icon: 5});
                            tableIns.reload();
                        } else {
                            layer.msg(result.msg);
                        }
                    }
                });
            });
        } else if (layEvent === 'edit') { //编辑
            //do something
            //同步更新缓存对应的值
            //layer.msg("编辑");
            openAddOrUpdateSaleChanceDialog(data.id)
        }
    });

});