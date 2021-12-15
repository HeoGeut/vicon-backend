jQuery(function($){
    
    $("input[name=mem_id]").on("keyup keydown keypress focusout",function(){
        
        if (IsUserID($(this).val())==false)
        {
            $("#id_chk_msg").text("아이디는 영문,숫자 조합으로만 사용할 수 있습니다.").css({'color':'red'});
        }
        else
        {
            $("#id_chk_msg").text("");
        }
         
        
        if ($(this).val().length == 30)
        {
            return false;
        }
        
        if ($(this).val().length == 0)
        {
            $("#create_url").text("ID.ljsystem.co.kr");
        }
        else
        {
            $("#create_url").text($(this).val()+".ljsystem.co.kr");
        }
            
        // /create_url
    });
    
    
    $("input[name=mem_mail]").on("keyup keydown keypress focusout",function(){
        var email = $(this).val();
        var re = /[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}/igm;
    
        if (re.test(email)) {
            $('#mail_chk_msg').hide();
        } else {
            $('#mail_chk_msg').text("이메일 형식이 아닙니다.").css({'color':'red'}).show();
        }
    });
    
    
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
    
    $(".tac_bx1 button.show").click(function(){
        $(".tac_bx1").hide('fast',function(){
            $(".tac_bx2").show({
            },1000);
        });
    });
    $(".tac_bx2 button.cncl").click(function(){
        $(".tac_bx2").hide('slow',function(){
            $(".tac_bx1").show({});
        });
    });
    
    $("#frm").submit(function(e){
        if ($("input[name=tac1]").is(":checked")==false)
        {
            alert('약관확인 버튼을 누르신뒤 이용약관 해주세요.');
            return false;
        }
        if ($("input[name=tac2]").is(":checked")==false)
        {
            alert('약관확인 버튼을 누르신뒤 개인정보취급방침 약관동의 해주세요.');
            return false;
        }
        $(this).submit();
    });
});


function IsUserID(strUserID)
{
    var nIndex;
    var chrCurrent;
    var ascChrCurrent;
    var strInvalid;
    var bReturn;

    bReturn = true;
    
    // for the length of the string...
    for ( nIndex = 0; nIndex < strUserID.length; nIndex++)
    {
        // check each character
        ascChrCurrent = strUserID.charAt(nIndex);
        // UserID should be alphanumeric
        if ( (ascChrCurrent >= '0' && ascChrCurrent <= '9'    ) || (ascChrCurrent >= 'a' && ascChrCurrent <= 'z') || (ascChrCurrent >= 'A' && ascChrCurrent <= 'Z') )
        {
            bReturn = true;    
        }
        else
        {
            bReturn = false;
            break;
        }
    }
    
    if ( bReturn && ( ( strUserID.length < 4) || ( strUserID.length > 12 ) ) )
    {
        bReturn = false;
    }
    return bReturn;
}
