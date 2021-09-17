jQuery(function($){
    //new WOW().init();

    var UserAgent = navigator.userAgent;
    if(UserAgent.match(/iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson/i) != null || UserAgent.match(/LG|SAMSUNG|Samsung/) != null) {
        //mobile

    }else{
        //pc

    }

    $('.sec3 .bx').slick({
        centerMode: true,
        centerPadding: '0',
        slidesToShow: 3,
        accessibility: false
    });

    $('.sec3 .m_bx').slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        accessibility: false,
        autoplay:true,
        dots:true,
        prevArrow:false,
        nextArrow:false,
        speed: 2000
    });
});
