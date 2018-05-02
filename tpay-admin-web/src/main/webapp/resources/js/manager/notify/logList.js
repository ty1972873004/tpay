$(document).ready(function() {
    //初始化Table
    var oTable = new TableInit();
    oTable.Init();
});

function doQuery(params){
    $('#notifyLogList').bootstrapTable('refresh');    //刷新表格
}
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#notifyLogList').bootstrapTable({
            url: 'findLogByPage',
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
                title : '通知消息ID',
                field : 'notifyId', // 字段
                align : 'center', // 对齐方式（左 中 右）
                valign : 'middle'
            }, {
                title : '请求地址',
                field : 'request',
                align : 'center',
                valign : 'middle',
                formatter:function(value,row,index){
                    if(value !=null && value.length >15){
                        return '<a title="'+value+'" href="javascript:void(0)" >'+value.substr(0,50)+'..</a>';
                    }
                    return value;
                },
            }, {
                title : 'http返回信息',
                field : 'response',
                align : 'center',
                valign : 'middle',
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
                title : 'HTTP状态',
                field : 'httpStatus',
                align : 'center',
                valign : 'middle'
            }, {
                title : '创建时间',
                field : 'createTime',
                align : 'center',
                valign : 'middle',
                formatter:function(value,row,index){
                   return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                },
                sortable : true
            } ],
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
            custNo : $("#custNo").val(),
            orderNo : $("#orderNo").val(),
            startTime : $("#start").val(),
            endTime : $("#end").val(),
         };
        return temp;
    };
    return oTableInit;
};

