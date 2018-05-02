$(document).ready(function() {
    //初始化Table
    var oTable = new TableInit();
    oTable.Init();

});
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#sessionList').bootstrapTable({
            url: 'findByPage',
            striped: true,
            search: false,
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

            columns: [
                {field: 'ck', checkbox: true},
                {field: 'id', title: '编号', sortable: true, align: 'center'},
                {field: 'startTimestamp', title: '创建时间', sortable: true, align: 'center'},
                {field: 'lastAccessTime', title: '最后访问时间'},
                {field: 'expired', title: '是否过期', align: 'center'},
                {field: 'host', title: '访问者IP', align: 'center'},
                {field: 'userAgent', title: '用户标识', align: 'center'},
                {field: 'status', title: '状态', align: 'center', formatter: 'statusFormatter'}
            ]
        });
    };

    return oTableInit;
};
// 格式化状态
function statusFormatter(value, row, index) {
    if (value == 'ON_LINE') {
        return '<span class="label label-success">在线</span>';
    }
    if (value == 'OFF_LINE') {
        return '<span class="label label-default">离线</span>';
    }
    if (value == 'FORCE_LOGOUT') {
        return '<span class="label label-danger">踢离</span>';
    }
}


// 强制退出
var forceoutDialog;
function forceoutAction() {
    var rows =  $('#sessionList').bootstrapTable('getSelections');
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
    }else{
        forceoutDialog = $.confirm({
            type: 'red',
            animationSpeed: 300,
            title: false,
            content: '确认强制退出该会话吗？',
            buttons: {
                confirm: {
                    text: '确认',
                    btnClass: 'waves-effect waves-button',
                    action: function () {
                        var ids = new Array();
                        for (var i in rows) {
                            ids.push(rows[i].id);
                        }
                        $.ajax({
                            type: 'get',
                            url: basePath + '/session/forceout/' + ids.join(","),
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
                                    forceoutDialog.close();
                                    $('#sessionList').bootstrapTable('refresh');
                                }
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                $.confirm({
                                    theme: 'dark',
                                    animation: 'rotateX',
                                    closeAnimation: 'rotateX',
                                    title: false,
                                    content: textStatus,
                                    buttons: {
                                        confirm: {
                                            text: '确认',
                                            btnClass: 'waves-effect waves-button waves-light'
                                        }
                                    }
                                });
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