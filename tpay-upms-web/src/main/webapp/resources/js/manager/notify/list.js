$(document).ready(function() {
    //初始化Table
    var oTable = new TableInit();
    oTable.Init();
});

function doQuery(params){
    $('#notifyList').bootstrapTable('refresh');    //刷新表格
}
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#notifyList').bootstrapTable({
            url: 'findByPage',
            method: 'post',                      //请求方式（*）
            queryParams: oTableInit.queryParams,//传递参数（*）
            contentType: "application/x-www-form-urlencoded", //解决POST提交问题
            queryParamsType : "undefined",
            striped: true,
            minimumCountColumns: 2,
            //clickToSelect: true,
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
                title : '通知地址',
                field : 'url', // 字段
                align : 'center', // 对齐方式（左 中 右）
                valign : 'middle', //
                formatter:function(value,row,index){
                    if(value !=null && value.length >15){
                        return '<a title="'+value+'" href="javascript:void(0)" >'+value.substr(0,50)+'..</a>';
                    }
                    return value;
                },
                sortable : true
            }, {
                title : '已通知次数',
                field : 'notifyTimes',
                align : 'center',
                valign : 'middle',
                sortable : true
            }, {
                title : '最大通知次数',
                field : 'limitNotifyTimes',
                align : 'center',
                valign : 'middle',
                sortable : true
            }, {
                title : '用户编号',
                field : 'custNo',
                align : 'center',
                valign : 'middle',
                sortable : true
            },{
                title : '订单号',
                field : 'orderNo',
                align : 'center',
                valign : 'middle',
                sortable : true
            },{
                title : '通知消息类型',
                field : 'notifyType',
                align : 'center',
                valign : 'middle',
                formatter:function(value,row,index){
                    if (value == null || value == undefined) {
                        return "-";
                    } else if (value=='1') {
                        return "见证宝消息通知";
                    } else if(value=='0'){
                        return "冻结";
                    }
                },
                sortable : true
            },{
                title : '状态',
                field : 'status',
                align : 'center',
                valign : 'middle',
                formatter:function(value,row,index){
                    if (value == null || value == undefined) {
                        return "-";
                    } else if (value=='100') {
                        return "通知成功";
                    } else if(value=='101'){
                        return "通知失败";
                    }else if(value=='102'){
                        return "通知记录已创建";
                    }else if(value=='200'){
                        return "http请求响应成功";
                    }else if(value=='201'){
                        return "http请求响应失败";
                    }
                },
                sortable : true
            }, {
                title : '创建时间',
                field : 'lastNotifyTime',
                align : 'center',
                valign : 'middle',
                formatter:function(value,row,index){
                   return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                },
                sortable : true
            }  , {
                field: 'operate',
                title: '操作',
                width: '80px',
                events: operateEvents1,
                formatter: operateFormatter,
                sortable : true
            }  ],
            responseHandler : function(res) {
                return {
                    total : res.total,
                    rows : res.records
                };
            },
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageNow : params.pageNumber,
            pageSize : params.pageSize,
            custNo : $("#custNo").val(),
            orderNo : $("#orderNo").val(),
         };
        return temp;
    };
    return oTableInit;
};




// 删除
var resendDialog;
function reSendAction() {
    var rows = $('#notifyList').bootstrapTable('getSelections');
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
        resendDialog = $.confirm({
            type: 'red',
            animationSpeed: 300,
            title: false,
            content: '确认消息重发吗？',
            buttons: {
                confirm: {
                    text: '确认',
                    btnClass: 'waves-effect waves-button',
                    action: function () {
                        $.ajax({
                            type: 'post',
                            url:basePath+ '/notify/resend/' + rows[0].tid,
                            success: function (result) {
                                if (result.code != 1) {
                                    $.confirm({
                                        theme: 'dark',
                                        animation: 'rotateX',
                                        closeAnimation: 'rotateX',
                                        title: false,
                                        content: result.data,
                                        buttons: {
                                            confirm: {
                                                text: '确认',
                                                btnClass: 'waves-effect waves-button waves-light'
                                            }
                                        }
                                    });

                                } else {
                                    resendDialog.close();
                                    $('#notifyList').bootstrapTable('refresh');
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

