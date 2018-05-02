$().ready(function () {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#form").validate({
        submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
            tra.ajaxSubmit(form,{//验证新增是否成功
                type : "post",
                dataType : "json",
                success : function(data) {
                    if (data.code == 1) {
                        createDialog.close();
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
        },
        rules : {
            loginName : {
                required : true,
                remote : { // 异步验证是否存在
                    type : "POST",
                    url : 'isExist',
                    data : {
                        name : function() {
                            return $("#loginName").val();
                        }
                    }
                }
            },
            password: {
                required: true,
                minlength: 5
            },
            repassword: {
                required: true,
                minlength: 5,
                equalTo: "#password"
            },
            email: {
                required: true,
                email: true
            },
        },
        messages : {
            "loginName" : {
                required : "请输入账号",
                remote : "该账号已经存在"
            },
            password: {
                required: icon + "请输入您的密码",
                minlength: icon + "密码必须5个字符以上"
            },
            repassword: {
                required: icon + "请再次输入密码",
                minlength: icon + "密码必须5个字符以上",
                equalTo: icon + "两次输入的密码不一致"
            },
        }
    });
});

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
