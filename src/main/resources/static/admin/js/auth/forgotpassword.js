jQuery(function($){
    
    $("input[name=mem_mail]").on("keyup keydown keypress focusout",function(){
        var email = $(this).val();
        var re = /[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}/igm;
    
        if (re.test(email)) {
            $('#mail_chk_msg').hide();
        } else {
            $('#mail_chk_msg').text("이메일 형식이 아닙니다.").css({'color':'red'}).show();
        }
    });
    
});


