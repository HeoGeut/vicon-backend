$(function(){
    var agt = navigator.userAgent.toLowerCase();
    if (~agt.indexOf("msie 9.0") || ~agt.indexOf("msie 8.0") || ~agt.indexOf("msie 7.0") || ~agt.indexOf("msie 6.0")){
        $(".intra_login").css("opacity",1);
        $(".intra_login").css("display","none");
        $(".intra_login").fadeIn(1000);
    }else{
        $(".intra_login").addClass("animated zoomIn");
    }
    
    function intra_submit(){
        $("#auth_form").submit();
    }
    
    $(".auth_login_btn").click(function(){
        if($("#userid").val()=="" || $("#password").val()==""){
            alert("올바른 정보를 입력해 주세요.");
            return false;
        }else{
            setTimeout(intra_submit,700);
        }
        
        $(".intra_login").removeClass("zoomIn");
        $(".intra_login").addClass("rotateOut");
        $(".total_scale").addClass("go_submit");
    });
    
    $(window).keyup(function(e) {
        if (e.keyCode == 13){
            $(".auth_login_btn").trigger("click");
        };        
    });
    
});
