function roleSubmit() {
    $.ajax({
        type: 'post',
        url: basePath+'/user/role/' + roleUserId,
        data: $('#roleForm').serialize(),
        beforeSend: function() {

        },
        success: function(data) {
            if (data.code == 1) {
                roleDialog.close();
                $('#userList').bootstrapTable('refresh');
            } else {
                $.confirm({
                    theme: 'dark',
                    animation: 'rotateX',
                    closeAnimation: 'rotateX',
                    title: false,
                    content: result.data.msg,
                    buttons: {
                        confirm: {
                            text: '确认',
                            btnClass: 'waves-effect waves-button waves-light'
                        }
                    }
                });
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