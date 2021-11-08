jQuery(function($){
	// 기업정보 팝업 열기
	$('.pop').click(function(e){
		e.preventDefault();
		$(this).parents('.company').find('.info_pop').css('display','block');
	});

	// 기업정보 팝업 닫기
	$('.info_pop .pop_close').click(function(){
		$(this).parents('.info_pop').css('display','none');
	});
});
