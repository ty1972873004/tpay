var changeDatas = [];
var setting = {
    check: {
        enable: true,
        // 勾选关联父，取消关联子
        chkboxType: { "Y" : "p", "N" : "s" }
    },
    async: {
     enable: true,
     url: basePath +'/menu/role/' + roleId
     },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onCheck: function() {
            var zTree = $.fn.zTree.getZTreeObj("ztree")
            var changeNodes = zTree.getChangeCheckedNodes();
            changeDatas = [];
            for (var i = 0; i < changeNodes.length; i ++) {
                var changeData = {};
                changeData.id = changeNodes[i].id;
                changeData.checked = changeNodes[i].checked;
                changeDatas.push(changeData);
            }
        }
    }
};
function initTree() {
    $.fn.zTree.init($('#ztree'), setting);
}

function permissionSubmit() {
    $.ajax({
        type: 'post',
        url: basePath +'/role/permission/' + roleId,
        data: {datas: JSON.stringify(changeDatas), roleId: roleId},
        success: function(result) {
            if (result.code != 1) {
                if (result.data instanceof Array) {
                    $.each(result.data, function(index, value) {
                        $.confirm({
                            theme: 'dark',
                            animation: 'rotateX',
                            closeAnimation: 'rotateX',
                            title: false,
                            content: value.errorMsg,
                            buttons: {
                                confirm: {
                                    text: '确认',
                                    btnClass: 'waves-effect waves-button waves-light'
                                }
                            }
                        });
                    });
                } else {
                    $.confirm({
                        theme: 'dark',
                        animation: 'rotateX',
                        closeAnimation: 'rotateX',
                        title: false,
                        content: result.data.errorMsg,
                        buttons: {
                            confirm: {
                                text: '确认',
                                btnClass: 'waves-effect waves-button waves-light'
                            }
                        }
                    });
                }
            } else {
                permissionDialog.close();
                $('#roleList').bootstrapTable('refresh');
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
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