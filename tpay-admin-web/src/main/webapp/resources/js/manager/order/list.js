$(document).ready(function() {
    //初始化Table
    var oTable = new TableInit();
    oTable.Init();

});
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#orderList').bootstrapTable({
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
                title : '订单号',
                field : 'payOrderNo', // 字段
                align : 'center', // 对齐方式（左 中 右）
                valign : 'middle' //
            }, {
                title : '订单类型',
                field : 'orderType', // 字段
                align : 'center', // 对齐方式（左 中 右）
                valign : 'middle' //
            }, {
                title : '订单金额',
                field : 'amount', // 字段
                align : 'center', // 对齐方式（左 中 右）
                valign : 'middle'
            }, {
                title : '金额类型',
                field : 'currency', // 字段
                align : 'center', // 对齐方式（左 中 右）
                valign : 'middle'
            },{
                title : '支付状态',
                field : 'payStatus',
                align : 'center',
                valign : 'middle',
                formatter:function(value,row,index){
                    if (value == null || value == undefined) {
                        return "-";
                    } else if (value=='00') {
                        return "订单生成";
                    } else if(value=='01'){
                        return "支付中";
                    }else if(value=='02'){
                        return "支付成功";
                    }else if(value=='03'){
                        return "处理完成";
                    }
                }
            }, {
                title : '通知地址',
                field : 'notifyUrl',
                align : 'center',
                valign : 'middle',
                formatter:function(value,row,index){
                    if(value !=null && value.length >15){
                        return '<a title="'+value+'" href="javascript:void(0)" >'+value.substr(0,50)+'..</a>';
                    }
                    return value;
                }
            },  {
                title : '最后一次通知时间',
                field : 'lastNotifyTime',
                align : 'center',
                valign : 'middle',
                formatter:function(value,row,index){
                    return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                }
            },{
                title : '支付成功时间',
                field : 'paySuccessTime',
                align : 'center',
                valign : 'middle',
                formatter:function(value,row,index){
                    return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                }
            } , {
                title: '操作',
                field: 'action',
                align: 'center',
                formatter: 'actionFormatter',
                clickToSelect: false
                }],
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
// 格式化操作按钮
function actionFormatter(value, row, index) {
    return [
        '<a class="agile-detail" href="javascript:;" onclick="detailAction('+row.tid+')" data-toggle="tooltip" title="ViewDetail"><i class="	glyphicon glyphicon-eye-open"></i></a>　'
    ].join('');
}


// 查看详情
var detailDialog;
function detailAction(tid){
        detailDialog = $.dialog({
                                    animationSpeed: 300,
                                    title: '订单详情',
                                    content: 'url:detail/' + tid
                                });

}
