$(document).ready(function() {
    //初始化Table
    var oTable = new TableInit();
    oTable.Init();

});
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#userList').bootstrapTable({
            url: 'findByPage',
            method: 'post',                      //请求方式（*）
            queryParams: oTableInit.queryParams,//传递参数（*）
            contentType: "application/x-www-form-urlencoded", //解决POST提交问题
            queryParamsType : "undefined",
            striped: true,
            search: true,
            showRefresh: true,
            showColumns: true,
            minimumCountColumns: 2,
            clickToSelect: true,
            pagination: true,
            paginationLoop: false,
            sidePagination: 'server',
            silentSort: false,
            smartDisplay: false,
            escape: true,
            searchOnEnterKey: true,
            idField: 'tid',
            maintainSelected: true,
            toolbar: '#toolbar',
            sortOrder: 'asc',     //排序方式
            sortName:'sort',

            columns : [ {
                field : 'id',
                checkbox : true,
                align : 'center',
                valign : 'middle'
            }, {
                title : '用户账号',
                field : 'loginName', // 字段
                align : 'center', // 对齐方式（左 中 右）
                valign : 'middle' //
            }, {
                title : '用户昵称',
                field : 'userName', // 字段
                align : 'center', // 对齐方式（左 中 右）
                valign : 'middle' //
            }, {
                title : '用户姓名',
                field : 'realName', // 字段
                align : 'center', // 对齐方式（左 中 右）
                valign : 'middle' //
            }, {
                title : '用户类型',
                field : 'userType',
                align : 'center',
                valign : 'middle',
                formatter:function(value,row,index){
                    if (value == null || value == undefined) {
                        return "-";
                    } else if (value=='1') {
                        return "总管理员";
                    } else if(value=='2'){
                        return "普通管理员";
                    }else if(value=='3'){
                        return "普通用户";
                    }
                }
            }, {
                title : '注册类型',
                field : 'registerType',
                align : 'center',
                valign : 'middle',
                formatter:function(value,row,index){
                    if (value == null || value == undefined) {
                        return "-";
                    } else if (value=='1') {
                        return "系统添加";
                    } else if(value=='2'){
                        return "手机注册";
                    }else if(value=='3'){
                        return "第三方";
                    }
                }
            }, {
                title : '手机号码',
                field : 'phone',
                align : 'center',
                valign : 'middle',
            },  {
                title : '邮箱',
                field : 'email',
                align : 'center',
                valign : 'middle',
                sortable : true
            },{
                title : '用户状态',
                field : 'status',
                align : 'center',
                valign : 'middle',
                formatter:function(value,row,index){
                    if (value == null || value == undefined) {
                        return "-";
                    } else if (value=='1') {
                        return "正常";
                    } else if(value=='0'){
                        return "冻结";
                    }
                }
            }, {
                title : '最后登录时间',
                field : 'loginTime',
                align : 'center',
                valign : 'middle',
                formatter:function(value,row,index){
                    return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                }
            }  ],
            responseHandler : function(res) {
                return {
                    total : res.totalCount,
                    rows : res.result
                };
            },
        });
    };
    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageNow : params.pageNumber,
            pageSize : params.pageSize,
        };
        return temp;
    };
    return oTableInit;
};


// 新增
var createDialog;
function addAction() {
    createDialog = $.dialog({
        animationSpeed: 300,
        title: '新增用户',
        content: 'url:add',
        onContentReady: function () {
            //
        }
    });
}

// 编辑
var updateDialog;
function editAction(){
    var rows = $('#userList').bootstrapTable('getSelections');
    if (rows.length != 1) {
        $.confirm({
            title: false,
            content: '请选择一条记录！',
            autoClose: 'cancel|3000',
            backgroundDismiss: true,
            buttons: {
                cancel: {
                    text: '取消',
                    btnClass: 'waves-effect waves-button'
                }
            }
        });
    }else{
        updateDialog = $.dialog({
            animationSpeed: 300,
            title: '编辑用户',
            content: 'url:edit/' + rows[0].tid
        });
    }

}

// 删除
var delDialog;
function delAction() {
    var rows = $('#userList').bootstrapTable('getSelections');
    if (rows.length == 0) {
        $.confirm({
            title: false,
            content: '请至少选择一条记录！',
            autoClose: 'cancel|3000',
            backgroundDismiss: true,
            buttons: {
                cancel: {
                    text: '取消',
                    btnClass: 'waves-effect waves-button'
                }
            }
        });
    } else {
        delDialog = $.confirm({
            type: 'red',
            animationSpeed: 300,
            title: false,
            content: '确认删除该用户吗？',
            buttons: {
                confirm: {
                    text: '确认',
                    btnClass: 'waves-effect waves-button',
                    action: function () {
                        var ids = new Array();
                        for (var i in rows) {
                            ids.push(rows[i].tid);
                        }
                        $.ajax({
                            type: 'get',
                            url:basePath+ '/user/delete/' + ids.join("-"),
                            success: function (result) {
                                if (result.code != 1) {
                                    $.confirm({
                                        theme: 'dark',
                                        animation: 'rotateX',
                                        closeAnimation: 'rotateX',
                                        title: false,
                                        content: result.msg,
                                        buttons: {
                                            confirm: {
                                                text: '确认',
                                                btnClass: 'waves-effect waves-button waves-light'
                                            }
                                        }
                                    });

                                } else {
                                    delDialog.close();
                                    $('#userList').bootstrapTable('refresh');
                                }
                            }
                        });

                    }
                },
                cancel: {
                    text: '取消',
                    btnClass: 'waves-effect waves-button'
                }
            }
        });
    }
}

// 用户角色
var roleDialog;
var roleUserId;
function roleAction() {
    var rows = $('#userList').bootstrapTable('getSelections');
    if (rows.length != 1) {
        $.confirm({
            title: false,
            content: '请选择一条记录！',
            autoClose: 'cancel|3000',
            backgroundDismiss: true,
            buttons: {
                cancel: {
                    text: '取消',
                    btnClass: 'waves-effect waves-button'
                }
            }
        });
    } else {
        roleUserId = rows[0].tid;
        roleDialog = $.dialog({
            animationSpeed: 300,
            title: '用户角色',
            content: 'url:role/' + roleUserId,
            onContentReady: function () {
                $('#roleId').select2({
                    placeholder: '请选择用户角色',
                    allowClear: true
                });
            }
        });
    }
}