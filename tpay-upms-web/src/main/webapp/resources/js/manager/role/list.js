$(document).ready(function() {
    //初始化Table
    var oTable = new TableInit();
    oTable.Init();
});
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#roleList').bootstrapTable({
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

            columns : [ {
                field : 'id',
                checkbox : true,
                align : 'center',
                valign : 'middle'
            }, {
                title : '角色名称',
                field : 'roleName', // 字段
                align : 'center', // 对齐方式（左 中 右）
                valign : 'middle', //
                sortable : true
            }, {
                title : '角色类型',
                field : 'roleType',
                align : 'center',
                valign : 'middle',
                sortable : true
            }, {
                title : '角色key',
                field : 'roleKey',
                align : 'center',
                valign : 'middle',
                sortable : true
            }, {
                title : '状态',
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
                },
                sortable : true
            }, {
                title : '创建时间',
                field : 'createTime',
                align : 'center',
                valign : 'middle',
                formatter:function(value,row,index){
                   return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                },
                sortable : true
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
        title: '新增角色',
        content: 'url:add',
        onContentReady: function () {
            //
        }
    });
}

// 编辑
var updateDialog;
function editAction(){
    var rows = $('#roleList').bootstrapTable('getSelections');
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
            title: '编辑角色',
            content: 'url:edit/' + rows[0].tid
        });
    }

}

// 删除
var delDialog;
function delAction() {
    var rows = $('#roleList').bootstrapTable('getSelections');
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
            content: '确认删除该角色吗？',
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
                            url:basePath+ '/role/delete/' + ids.join("-"),
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
                                    $('#roleList').bootstrapTable('refresh');
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

// 角色权限
var permissionDialog;
var roleId;
function permissionAction() {
    var rows = $('#roleList').bootstrapTable('getSelections');
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
        roleId = rows[0].tid;
        permissionDialog = $.dialog({
            animationSpeed: 300,
            title: '角色权限',
            content: 'url:permission/' + roleId,
            onContentReady: function () {
                initTree();
            }
        });
    }
}


