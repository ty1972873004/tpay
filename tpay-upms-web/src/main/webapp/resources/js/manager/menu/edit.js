$().ready(function () {
    $("#form").validate({
        submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
            tra.ajaxSubmit(form,{//验证新增是否成功
                type : "post",
                dataType : "json",
                success : function(data) {
                    if (data.code == 1) {
                        updateDialog.close();
                        $('#menuList').bootstrapTable('refresh');
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
        },
    });
    var icon = "<i class='fa fa-times-circle'></i> ";

    var url = basePath + '/menu/reslists';
    var data = CommnUtil.ajax(url, null,"json");
    if (data != null) {
        var h = "<option value='0'>------顶级目录------</option>";
        for ( var i = 0; i < data.length; i++) {
            h+="<option value='" + data[i].id + "'>"+ data[i].menuName + "</option>";
        }
        $("#parentId").html(h);
    } else {
        toastr.options = {
            "closeButton": true,
            "debug": false,
            "progressBar": true,
            "positionClass": "toast-top-center",
            "onclick": null,
            "showDuration": "400",
            "hideDuration": "1000",
            "timeOut": "7000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        };
        toastr['error']("获取菜单信息错误，请联系管理员！", "错误提示");
    }
});

function byMenu(id){
    var url = basePath + '/menu/reslists';
    var data = CommnUtil.ajax(url, null,"json");
    if (data != null) {
        var h = "<option value='0'>------顶级目录------</option>";
        for ( var i = 0; i < data.length; i++) {
            if(id+"" == (data[i].id)+""){
                h+="<option value='" + data[i].id + "' selected='selected'>"
                    + data[i].menuName + "</option>";
            }else{
                h+="<option value='" + data[i].id + "'>"+ data[i].menuName + "</option>";
            }
        }
        $("#parentId").html(h);
    } else {
        toastr.options = {
            "closeButton": true,
            "debug": false,
            "progressBar": true,
            "positionClass": "toast-top-center",
            "onclick": null,
            "showDuration": "400",
            "hideDuration": "1000",
            "timeOut": "7000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        };
        toastr['error']("获取菜单信息错误，请联系管理员！", "错误提示");
    }
}



//修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
$.validator.setDefaults({
    highlight: function (element) {
        $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
    },
    success: function (element) {
        element.closest('.form-group').removeClass('has-error').addClass('has-success');
    },
    errorElement: "span",
    errorPlacement: function (error, element) {
        if (element.is(":radio") || element.is(":checkbox")) {
            error.appendTo(element.parent().parent().parent());
        } else {
            error.appendTo(element.parent());
        }
    },
    errorClass: "help-block m-b-none",
    validClass: "help-block m-b-none"
});
