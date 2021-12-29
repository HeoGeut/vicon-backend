$(document).on('keydown', 'input[inputmode=numeric]', function (event) {
    this.value = this.value.replace(/[^0-9]/g, '');   				// 입력값이 숫자가 아니면 공백
    this.value = this.value.replace(/,/g, '');          			// ,값 공백처리
    this.value = this.value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');  // 정규식을 이용해서 3자리 마다 , 추가
    this.value = this.value.replace(/(^0+)/, '');					// 첫번째 숫자 0제거
});

// 줄 수 제한
function limitLines(obj, e) {
    var numberOfLines = (obj.value.match(/\n/g) || []).length + 1;
    var maxRows = obj.rows;
    if (e.which == 13 && numberOfLines === maxRows) {
        return false;
    }
}


jQuery(function ($) {
    $('.cnt_txt').keyup(function (e) {
        var content = $(this).val();
        // console.log(content);
        var counter = $(this).parents('.txt_box').find('.counter');
        // console.log(counter);
        counter.html("(" + content.length + " / 2000자)");    //글자수 실시간 카운팅

        if (content.length > 1999) {
            alert("최대 2000자까지 입력 가능합니다.");
            $(this).val(content.substring(0, 1999));
            $('#counter').html("(2000 / 최대 2000자)");
        }
    });

    // 기업정보 팝업 열기
    $('.logo_box .pop').click(function (e) {
        e.preventDefault();
        // console.log(1);
        $('.info_pop').css('display', 'none');
        $(this).parents('.logo_box').find('.info_pop').css('display', 'block');
    });

    // 기업정보 팝업 닫기
    $('.logo_box .info_pop .pop_close').click(function () {
        $(this).parents('.info_pop').css('display', 'none');
    });

    $('.cont .form_control ul li').click(function () {
        if ($(this).attr('class') != 'act') {
            $(this).parents('ul').find('li').removeClass('act');
            $(this).addClass('act');
        }
    });

    // 파일 선택시 리스트 보이기
    $("input[type=file]").change(function () {
        var fileInput = document.getElementById("file_target");
        var files = fileInput.files;
        var file;
        var realfile = "";

        for (var i = 0; i < files.length; i++) {
            realfile += "<i class='far fa-file'></i> " + files[i].name + "<br/>";
        }

        $('#file_lst').html(realfile);
    });

    $('.c_style li').click(function () {
        var target = $(this).attr('target');
        $(this).parents('.form_control').find('.lst_val').val(target);
    });

    // 콘테스트 열기 스텝1
    $('#smit_step1').click(function (e) {
        e.preventDefault();
        if ($('input[name=c_category_type]').val() == '2') {
            if ($("input[name=c_category]").val() == "") {
                alert("비즈니스 카테고리를 입력해 주세요.")
                $("input[name=c_category]").focus();
                return false;
            }
        }

        if ($("input[name=c_title]").val() == "") {
            alert("제목을 입력해 주세요.")
            $("input[name=c_title]").focus();
            return false;
        }

        if ($("input[name=c_name]").val() == "") {
            alert("기업명 또는 제품명 입력해 주세요.")
            $("input[name=c_name]").focus();
            return false;
        }

        if ($("textarea[name=c_context]").val() == "") {
            alert("설명을 입력해 주세요.")
            $("textarea[name=c_context]").focus();
            return false;
        }

        $(this).parents('form').submit();
    });


    // 콘테스트 열기 스텝2
    $('#smit_step2').click(function (e) {
        e.preventDefault();
        if ($("input[name=c_reward]").val() == "") {
            alert("상금을 입력해 주세요.")
            $("input[name=c_reward]").focus();
            return false;
        }

        var reward = $('input[name=c_reward]').val();
        if ($('input[name=c_type]').val() == '1') {
            if (reward.replace(/,/g, '') < 300000) {
                alert('상금 최소액은 30만원입니다.');
                $("input[name=c_reward]").focus();
                return false;
            }
        } else {
            if (reward.replace(/,/g, '') < 1000000) {
                alert('상금 최소액은 100만원입니다.');
                $("input[name=c_reward]").focus();
                return false;
            }
        }

        if($("input[name=recruitNumber]").val()=="")
        {
            alert("인원을 입력해 주세요.")
            $("input[name=c_recruit]").focus();
            return false;
        }

        var recruit = $('input[name=recruitNumber]').val();
        if (recruit < 1) {
            alert("모집 최소인원은 1명입니다.")
            $("input[name=c_recruit]").focus();
            return false;
        }

        if ($('input[name=c_ad_chk]').val() == '1') {
            if ($("input[name=c_ad_price]").val() == "") {
                alert("광고 최소액을 입력해 주세요.")
                $("input[name=c_ad_price]").focus();
                return false;
            }

            if ($("input[name=c_ad_price]").val().replace(/,/g, '') < 50000) {
                alert('광고 최소액은 5만원입니다.');
                $("input[name=c_ad_price]").focus();
                return false;
            }
        }
        //
        if ($("input[name=c_deadline]").val() == "") {
            alert("모집 마감일을 선택해 주세요.")
            $("input[name=c_deadline]").focus();
            return false;
        }

        if ($("textarea[name=c_duedate]").val() == "") {
            alert("콘텐츠 제작 및 배포 완료일을 선택해 주세요.")
            $("textarea[name=c_duedate]").focus();
            return false;
        }

        $(this).parents('form').submit();
    });

    // 현금영수증
    $("input[name='c_receipts']").change(function () {
        var value = $(this).val();
        if (value == '3') {
            $('select[name=c_receipts_slt]').prop('disabled', true);
            $('select[name=c_receipts_slt]').addClass('price_act');
            $('input[name=c_receipts_number]').attr('readonly', true);
            $('input[name=c_receipts_number]').addClass('price_act');
        } else {
            $('select[name=c_receipts_slt]').prop('disabled', false);
            $('select[name=c_receipts_slt]').removeClass('price_act');
            $('input[name=c_receipts_number]').removeAttr('readonly');
            $('input[name=c_receipts_number]').removeClass('price_act');
        }
    });

    // 세금계산서
    $("input[name='c_tax_type']").change(function () {
        var value = $(this).val();
        if (value == '2') {
            $('input[name=c_tax_number]').attr('readonly', true);
            $('input[name=c_tax_number]').addClass('price_act');
        } else {
            $('input[name=c_tax_number]').removeAttr('readonly');
            $('input[name=c_tax_number]').removeClass('price_act');
        }
    });

    // 콘테스트 열기 스텝3
    $('#smit_step3').click(function () {
        $(this).parents('.wrap').find('form').submit();
    });

    // 상금입력
    $('.num_only').on('keyup', function () {
        var fees = $('input[name=c_fees]').val();
        var adPrice = $('input[name=c_ad_price]').val();
        var isVat;

        isVat = fees == 1 ? true : false;

        this.value = this.value.replace(/[^0-9]/g, '');   				// 입력값이 숫자가 아니면 공백
        this.value = this.value.replace(/,/g, '');          			// ,값 공백처리
        this.value = this.value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');  // 정규식을 이용해서 3자리 마다 , 추가
        this.value = this.value.replace(/(^0+)/, '');					// 첫번째 숫자 0제거
        !this.value ? this.value = 0 : '';

        calPrice(this.value, adPrice, isVat);
    });

    // 수수료 여부
    $('.isVat li').click(function () {
        var target = $(this).attr('target');
        var price = $('input[name=c_reward]').val();
        var adPrice = $('input[name=c_ad_price]').val();
        var isVat;

        isVat = target == 1 ? true : false;
        $(this).parents('.form_control').find('.lst_val').val(target);

        calPrice(price, adPrice, isVat);
    });

    // 광고비 집행 여부
    $('.isAdprice li').click(function () {
        var target = $(this).attr('target');
        var price = $('input[name=c_reward]').val();
        var adPrice = $('input[name=c_ad_price]').val();
        var fees = $('input[name=c_fees]').val();
        var isVat;

        isVat = fees == 1 ? true : false;
        $(this).parents('.form_control').find('.lst_val').val(target);

        if (target == 1) {
            $('input[name=c_ad_price]').removeAttr('readonly');
            $('input[name=c_ad_price]').removeClass('price_act');
        } else {
            $('input[name=c_ad_price]').val('0');
            $('input[name=c_ad_price]').attr('readonly', true);
            $('input[name=c_ad_price]').addClass('price_act');
        }

        calPrice(price, adPrice, isVat);
    });

    // 광고비 입력
    $('input[name=c_ad_price]').on('keyup', function () {
        var price = $('input[name=c_reward]').val();
        var fees = $('input[name=c_fees]').val();
        var isVat;

        isVat = fees == 1 ? true : false;

        this.value = this.value.replace(/[^0-9]/g, '');   				// 입력값이 숫자가 아니면 공백
        this.value = this.value.replace(/,/g, '');          			// ,값 공백처리
        this.value = this.value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');  // 정규식을 이용해서 3자리 마다 , 추가
        this.value = this.value.replace(/(^0+)/, '');					// 첫번째 숫자 0제거
        !this.value ? this.value = 0 : '';

        calPrice(price, this.value, isVat);
    });
});

// 금액 계산
function calPrice(price, adPrice, isVat) {
    // 등록비
    var fee = 100000;
    var strVal;
    var numVal;

    // 상금
    if (price != 0) {
        strVal = price;
        numVal = price.replace(/,/g, '');
    } else {
        strVal = 0;
        numVal = 0;
    }

    // 수수료 부담
    $('#price3').html(0); // default
    if (isVat == true) {
        var adVat = numVal * 0.2; // 수수료 20%
        numVal = +numVal + adVat; // 상금 + 수수료
        $('#price3').html(String(adVat).replace(/\B(?=(\d{3})+(?!\d))/g, ",")); // 수수료
    }

    // 광고비 집행
    if (adPrice) {
        var numAdPrice = adPrice.replace(/,/g, '');
        numVal = Number(numVal) + Number(numAdPrice); // 상금 + 광고비
        $('#price2').html(adPrice); // 광고비
    }

    // 등록비 + 상금
    var numTotal = +numVal + fee;

    // 등록비 + 상금의 VAT (10%)
    var strVat = String(Math.floor(numTotal * 0.1)).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    var numVat = strVat.replace(/,/g, '');

    // 합계
    var totalAmt = Number(numTotal) + Number(numVat);
    var strTotalAmt = String(totalAmt).replace(/\B(?=(\d{3})+(?!\d))/g, ",");

    $('#total_price').val(totalAmt);
    $('#price1').html(strVal); // 총상금
    $('#price4').html(strVat); // 부가세
    $('#price5').html(strTotalAmt); // 총합계
}
