$(document).ready(function() {
    //初始化Table
    var oTable = new TableInit();
    oTable.Init();

});
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#channelList').bootstrapTable({
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
                title : '渠道ID',
                field : 'channelId', // 字段
                align : 'center', // 对齐方式（左 中 右）
                valign : 'middle' //
            }, {
                title : '渠道名称',
                field : 'channelName', // 字段
                align : 'center', // 对齐方式（左 中 右）
                valign : 'middle' ,
                formatter:function(value,row,index){
                    if (value == null || value == undefined) {
                        return "-";
                    } else if (value=='WX') {
                        return "微信";
                    } else if(value=='ALIPAY'){
                        return "支付宝";
                    }
                }
            }, {
                title : '商户ID',
                field : 'mchId', // 字段
                align : 'center', // 对齐方式（左 中 右）
                valign : 'middle',
            }, {
                title : '渠道商户ID',
                field : 'channelMchId', // 字段
                align : 'center', // 对齐方式（左 中 右）
                valign : 'middle',
            },{
                title : '渠道状态',
                field : 'channelStatus',
                align : 'center',
                valign : 'middle',
                formatter:function(value,row,index){
                    if (value == null || value == undefined) {
                        return "-";
                    } else if (value=='1') {
                        return "启用";
                    } else if(value=='0'){
                        return "停用";
                    }
                }
            },{
                title : '创建时间',
                field : 'createTime',
                align : 'center',
                valign : 'middle',
                formatter:function(value,row,index){
                    return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                }
            }, {
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
        '<a class="agile-detail"  href="javascript:;"   onclick="detailAction('+row.tid+')" data-toggle="tooltip" title="ViewDetail"><i class="glyphicon glyphicon-eye-open"></i></a>　'
    ].join('');
}


// 查看详情
var detailDialog;
function detailAction(tid){
        detailDialog = $.dialog({
                                    animationSpeed: 300,
                                    title: '支付渠道详情',
                                    //content: 'url:detail/' + rows[0].tid
                                    content: 'url:detail/' + tid
                                });
}

// 新增
var createDialog;
function addAction() {
    createDialog = $.dialog({
                                animationSpeed: 300,
                                title: '新增商户',
                                content: 'url:add',
                                onContentReady: function () {
                                    //
                                }
                            });
}

// 编辑
var updateDialog;
function editAction(){
    var rows = $('#channelList').bootstrapTable('getSelections');
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
                                    title: '编辑渠道',
                                    content: 'url:edit/' + rows[0].tid
                                });
    }

}


// 删除
var delDialog;
function delAction() {
    var rows = $('#channelList').bootstrapTable('getSelections');
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
                                  title: '系统提示',
                                  content: '确认删除该渠道吗？',
                                  icon: 'fa fa-warning',
                                  type: 'red',
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
                                                         url:basePath+ '/channel/delete/' + ids.join("-"),
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
                                                                 $('#channelList').bootstrapTable('refresh');
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