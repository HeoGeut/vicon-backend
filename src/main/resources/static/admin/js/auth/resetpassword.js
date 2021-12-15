jQuery(function($){
    
    $("input[name=mem_pw]").on("keyup keydown keypress focusout",function(){
        //isValidFormPassword($(this).val());
    });
    $("input[name=mem_pw2]").on("keyup keydown keypress focusout",function(){
        if ($("input[name=mem_pw]").val() != $(this).val())
        {
            $("#pw2_chk_msg").text("비밀번호가 일치하지 않습니다.").css({'color':'red'});
        }
        else
        {
            $("#pw2_chk_msg").text("");
        }
        //isValidFormPassword($(this).val());
    });
});
