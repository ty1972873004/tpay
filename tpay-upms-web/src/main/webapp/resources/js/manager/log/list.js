$(document).ready(function() {
    //初始化Table
    var oTable = new TableInit();
    oTable.Init();
});

function doQuery(params){
    $('#sysLogList').bootstrapTable('refresh');    //刷新表格
}
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#sysLogList').bootstrapTable({
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
                title : '用户名',
                field : 'userName',
                align : 'center',
                valign : 'middle',
                sortable : true
            }, {
                title : '访问Uri',
                field : 'uri',
                align : 'center',
                valign : 'middle',
                sortable : true
            }, {
                title : '请求参数',
                field : 'parameter',
                align : 'center',
                valign : 'middle',
                sortable : true
            },{
                title : 'ip地址',
                field : 'ip',
                align : 'center',
                valign : 'middle',
                sortable : true
            },{
                title : '请求结果',
                field : 'result',
                align : 'center',
                valign : 'middle',
                formatter:function(value,row,index){
                    if(value !=null && value.length >15){
                        return value.substr(0,50);
                    }
                    return value;
                },
                sortable : true
            },{
                title : '开始时间',
                field : 'startTime',
                align : 'center',
                valign : 'middle',
                formatter:function(value,row,index){
                   return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                },
                sortable : true
            } ,{
                title : '结束时间',
                field : 'endTime',
                align : 'center',
                valign : 'middle',
                formatter:function(value,row,index){
                    return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                },
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
         };
        return temp;
    };
    return oTableInit;
};

