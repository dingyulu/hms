<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <input type="hidden" name="id" value="${(nurse.id)!}">
        <input type="hidden" name="pname" value="${(nurse.pname)!}">
        <input type="hidden" name="designOne" value="${(nurse.designOne)!}">
        <#--设置营销人员的ID-->
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="userName" id="userName" value="${(nurse.userName)!}"
                   placeholder="请输入姓名">
        </div>
    </div>
    <#--<div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">真实姓名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="trueName"
                   id="trueName" value="${(nurse.trueName)!}" placeholder="请输入真实姓名">
        </div>
    </div>-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">身份证号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="idNumber"
                   lay-verify="required" value="${(nurse.idNumber)!}" placeholder="请输入身份证号">
        </div>
    </div>
<#--    <div class="layui-form-item layui-row layui-col-xs12">-->
<#--        <label class="layui-form-label">入院工作时间</label>-->
<#--        <div class="layui-input-block">-->
<#--            <input type="datetime-local" class="layui-input"-->
<#--                   name="admittedDate" value="${(nurse.admittedDate)!}" id="admittedDate" placeholder="请输入入院工作时间">-->
<#--        </div>-->
<#--    </div>-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">联系方式</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="phone" value="${(nurse.phone)!}" id="phone" placeholder="请输入联系方式">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="email" value="${(nurse.email)!}"
                   placeholder="请输入邮箱">
        </div>
    </div>
  <#--  <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">看护病人</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="designOne" value="${(nurse.designOne)!}"
                   placeholder="请输入看护病人">
        </div>
    </div>-->
    <#--<div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">机会描述</label>
        <div class="layui-input-block">
        <textarea placeholder="请输入机会描述信息" name="description" class="layui-textarea">${(saleChance.description)!}</textarea>
        </div>
    </div>-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label" >看护病人</label>
        <div class="layui-input-block">
            <select name="designOne" id="designOne" >
                <option value="">请选择</option>
            </select>
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateNurse">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript"
        src="${ctx}/js/nurse/add_update.js"></script>
</body>
</html>