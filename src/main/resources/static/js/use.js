jQuery(function($){
    $('.faq_wrap .q').click(function(){
        if($(this).parents('li').hasClass("act"))
        {
            $(this).parents('li').removeClass("act");
            $(this).parents('li').find('.a').slideUp(200);
        }
        else
        {
            $('.faq_wrap li').removeClass("act");
            $(this).parents('li').addClass("act");
            $('.faq_wrap .a').slideUp(200);
            $(this).parents("li").find(".a").slideDown(200);
        }
    });

    // 삭제
	$(".del").click(function(e){
		e.preventDefault();
	    if (!confirm("정말 삭제 하시겠습니까?")) return false;

	    var $key = $(this).attr("data-key");
	    $.post("ajax.php",{type:"del",key:$key}, function(data){
	        if (data==true)
	        {
                alert('삭제되었습니다.');
	            location.href = '/main/use/04.php';
	        }
	        else
	        {
	            alert("삭제 실패.");
	        }
	    });
	});

    $(".smit").click(function(){
        if($("input[name=rq_title]").val()=="")
        {
            alert("제목을 입력해 주세요.")
            $("input[name=rq_title]").focus();
            return false;
        }

        if($("input[name=rq_content]").val()=="")
        {
            alert("문의 내용을 입력해 주세요.")
            $("input[name=rq_content]").focus();
            return false;
        }

        $(this).parents('form').submit();
	});

    $('.info_wrap .nav_lst p').click(function(){
        var target = $(this).attr('target');

        if($(this).attr('class')=='trans03 act')
        {
            $(this).removeClass('act');
        }else {
            $('.info_wrap .nav_lst p').removeClass('act');
            $(this).addClass('act');
        }

        $('.info_wrap .box').css('display','none');
        $('#'+target).css('display','block');
    });
    $('.info_wrap .nav_lst p:nth-child(1)').trigger('click');

    $('.info_wrap .box ul li .step_title').click(function(){
        $(this).parents('li').toggleClass('act');
    });
});
