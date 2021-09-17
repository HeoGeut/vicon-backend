function getCookie(name) {
	var cookies = document.cookie.split(";");
	for (var i = 0; i < cookies.length; i++) {
		if (cookies[i].indexOf("=") == -1) {
			if (name == cookies[i])
				return "";
		} else {
			var crumb = cookies[i].split("=");
			if (name == crumb[0].trim())
				return unescape(crumb[1].trim());
		}
	}
};

var desktopModeTF = getCookie("DesktopMode");
var Scale = getCookie("DesktopModeScale");
var defWidth = 1300;
if (desktopModeTF == "true") {
	document.write('<meta name="viewport" content="width='+defWidth+', user-scalable=yes, initial-scale='+Scale+'">');
} else {
	document.write('<meta name="viewport" content="width=device-width, user-scalable=no,  maximum-scale=1, initial-scale=1.0">');
}

function desktopMode() {
	if(getCookie("DesktopMode") == "true"){
		setModeCookie(false);

	}else{
		setModeCookie(true);
		window.scrollTo(0, 0);
	}
	location.reload();

}
function setModeCookie(switchOn){
	var now = new Date();
	var time = now.getTime();
	time += 3600 * 1000;
	now.setTime(time);
	document.cookie ='DesktopMode='+switchOn +'; expires=' + now.toUTCString() ;
	if(switchOn){
		document.cookie = "DesktopModeScale=" + $('html').width() / defWidth +'; expires=' + now.toUTCString() ;;
	}
}

jQuery(function($){
    new WOW().init();

    $('a[href=""]').click(function(e){
        e.preventDefault();
        alert('준비중입니다.');
    });

	$('.snb_wrap ul li a').each(function(){
		var title = $(this).text();
		if(title == $('.sub_key').text())
		{
			$(this).addClass('act');
		}
	});

	$('.rnb>ul>li').click(function(){
		var target = $(this).attr('target');
		// console.log(target);
		location.href='/main/contest/view.php?key='+target;
	});

	$('.filter_box .chk_box').click(function(){
		// var sort = $(this).find('input').attr('name');
		// var value = $(this).find('input').attr('value');

		$(this).parents("form").submit();
	});

    var UserAgent = navigator.userAgent;
    if(UserAgent.match(/iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson/i) != null || UserAgent.match(/LG|SAMSUNG|Samsung/) != null) {
        //mobile
        $('#open-button').on("click",function(){
            if($(this).attr('class')=='act'){
                $(this).removeClass('act');
                $(".menu_wrap").removeClass('act');
                $(".roof").fadeOut(300);

            }else{
                $(this).addClass('act');
                $(".menu_wrap").addClass('act');
                $(".roof").fadeIn(300);
            }
        });

        $(".roof").on("click",function() {
            $("#open-button").trigger("click");
        });

        $('.header_wrap .menu_wrap>ul>li>a').on('click',function(e){
            e.preventDefault();
            if($(this).parents('li').attr('class')=='sub act')
            {
                $(this).parents('li').removeClass('act');
                $(this).find('.sub_menu').slideUp();

            }else {
                $('.header_wrap .menu_wrap>ul>li').removeClass('act');
                $(this).parents('li').addClass('act');
                $('.header_wrap .menu_wrap>ul>li .sub_menu').slideUp();
                $(this).parents('li').find('.sub_menu').slideDown();
            }
        });
        $('.m_view').css('display','block !important');

    }else{

        $('.m_view').css('display','none');

        $('.header_wrap .menu_bar>div').hover(function(){
            var target = $(this).attr('class');

            $('.menu_wrap ul').find('.'+target).addClass('act');
            $('.menu_wrap ul li').addClass('nact');
        },function(){
            $('.menu_wrap ul li').removeClass('act');
            $('.menu_wrap ul li').removeClass('nact');
        });

        $('.menu_wrap ul li').hover(function(){
            var target = $(this).attr('class');
            $('.menu_bar').find('.'+target).addClass('act2');
        },function(){
            $(this).removeClass('nact');
            $('.menu_bar>div').removeClass('act2');
        });

        $('.header_wrap .menu_wrap').hover(function(){
            $('.menu_bar').css('display','block');
        },function(){
            $('.menu_bar').css('display','none');
        });

        $('.menu_bar').hover(function(){
            $('.menu_bar').css('display','block');
        },function(){
            $('.menu_bar').css('display','none');
        });
    }
});
