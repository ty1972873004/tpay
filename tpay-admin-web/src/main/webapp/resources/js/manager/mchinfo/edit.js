$().ready(function () {
    $("#form").validate({
                            submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
                                if($('#status').is(':checked')) {
                                    $("#mchStatus").val(1);
                                }else{
                                    $("#mchStatus").val(0);
                                }
                                tra.ajaxSubmit(form,{//验证新增是否成功
                                    type : "post",
                                    dataType : "json",
                                    success : function(data) {
                                        if (data.code == 1) {
                                            updateDialog.close();
                                            $('#mchInfoList').bootstrapTable('refresh');
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
var elem = document.querySelector('.js-switch');
var switchery = new Switchery(elem, {
    color: '#1AB394'
});