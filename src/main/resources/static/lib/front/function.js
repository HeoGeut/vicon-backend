function ssnCheck(_ssn1, _ssn2)
{
	var ssn1 = _ssn1;
		ssn2 = _ssn2;
		ssn  = ssn1+""+ssn2;
		arr_ssn = [];
		compare = [2,3,4,5,6,7,8,9,2,3,4,5];
		sum = 0;
	if (ssn1.match("[^0-9]")) { alert("주민등록번호는 숫자만 입력하셔야 합니다."); return false; }
	if (ssn2.match("[^0-9]")) { alert("주민등록번호는 숫자만 입력하셔야 합니다."); return false; }
	for (var i = 0; i<13; i++) { arr_ssn[i] = ssn.substring(i,i+1); }
	for (var i = 0; i<12; i++) { sum = sum + (arr_ssn[i] * compare[i]); }
	sum = (11-(sum%11))%10;
	if (sum!=arr_ssn[12]) { alert("올바른 주민등록 번호를 입력하여 주세요.");return false; }
	if (ssn.length != 13){ alert("올바른 주민등록 번호를 입력하여 주세요.");return false;}

    return true;
}

function checkPassport(_s)
{
    // alert(/^\d+$/.test($('#passportNumber').val()));       // 2 digit number validation
    // alert(/^[a-zA-Z]+$/.test($('#passportSeries').val()));  // 7 character validation

    var arr = new Array(8),
        sum = 0;
    arr[0] = _s.substring(0,1);
    arr[1] = _s.substring(1,2);
    arr[2] = parseFloat(_s.substring(2,3));
    arr[3] = parseFloat(_s.substring(3,4));
    arr[4] = parseFloat(_s.substring(4,5));
    arr[5] = parseFloat(_s.substring(5,6));
    arr[6] = parseFloat(_s.substring(6,7));
    arr[7] = parseFloat(_s.substring(7,8));
    arr[8] = parseFloat(_s.substring(8,9));

    if (_s.length != 9)
    {
        return false;
    }

    // 첫번째 자리는 영문.
    if (arr[0] < 'A' || arr[0] > 'Z' )
    {
        console.log(1);
        return false;
    }

    // 두번째 자리는 영문.
    else if (arr[1] < 'A' || arr[1] > 'Z')
    {
        console.log('2:'+arr[1]);
        return false;
    }

    sum =   arr[2] * 1000000 +
            arr[3] * 100000 +
            arr[4] * 10000 +
            arr[5] * 1000 +
            arr[6] * 100 +
            arr[7] * 10 +
            arr[8];
    if ( sum < 0 || sum > 9999999 )
    {
        console.log(sum);
        return false;
    }

    return true;

}

function checkEmail(email)
{
    var pattern = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;
    if(!pattern.test(email)){
        return false;
    }
    return true;
}


/**
 * 완성형 한글이 몇글자인지 확인. (가: 완성형한글 / ㄱ:미완성한글)
 * @param _text: string value
 */
function koreanAlphabetCheck(_text)
{
    var text = _text,
        text_cnt = 0,
        pattern = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;

    for (var i=0; i<text.length; i++)
    {
        text_cnt++;
        // console.info('------------------------');
        var chr = text.substr(i, 1);
        if (pattern.test(chr)==true)
        {
            // console.info('한글진입:', chr);
            chr = escape(chr);
            // console.log('escape:',chr);
            if (chr.charAt(1) == "u")
            {
                //  console.log('charAt:', chr.charAt(1));
                chr = chr.substr(2,  (chr.length - 1));
                // console.log(chr);
                if((chr < "AC00") || (chr > "D7A3"))
                {
                    // console.log('false - 1');
                    text_cnt--;
                }
            }
            else
            {
                // console.log('false - 2');
                text_cnt--;
            }
        }
        else
        {
            // console.info('그외 문자:', chr);
            text_cnt--;
        }
    }
    // console.log('han cnt:', text_cnt);
    return text_cnt;
}

/**
 * input[type=text]의 text에 있는 커서 이동 및 원하는 만큼 드래그하기.
 * @author Dongkkase (http://eyecandyzero.tistory.com)
 * @param _start: start cursor[Type-int]
 * @param _end: end cursor[Type-int]
 * @example Driectory /lib/demo/func.cursor/
 */
$.fn.selectRange = function(_start, _end){
    return this.each(function(){
        if (this.setSelectionRange)
        {
            this.focus();
            this.setSelectionRange(_start, _end);
        }
        else if (this.createTextRange)
        {
            var range = this.createTextRange();
            range.collapse(true);
            range.moveEnd('character', _end);
            range.moveStart('character', _start);
            range.select();
        }
    });
};
/**/


/**
 * 앵커태그에 명시된 주소로 팝업창 열기.
 * @author Dongkkase (http://eyecandyzero.tistory.com)
 * @param width: int
 * @param height: int
 * @param titlebar: [true/false]
 * @param status: [true/false]
 * @param resizable: [true/false]
 * @param toolbar: [true/false]
 * @param scrollbars: [true/false]
 * @param menubar: [true/false]
 * @example Driectory /lib/demo/func.popup/
 */
jQuery.fn.popup = function(options) {
    var defaults = {
        width: screen.width/2,
        height: screen.height/2,
        titlebar: true,
        status: true,
        resizable: true,
        toolbar: true,
        scrollbars: true,
        menubar: true
    };
    var options = jQuery.extend(defaults, options);

    Boolean.prototype.setProperty = function() {
        if (this == true) { return "yes"; } else { return "no"; }
    };

    return this.each( function() {
        jQuery(this).click( function() {

            var data = $(this).attr('data-object');
            if (data!=undefined)
            {
                options = jQuery.extend(options, eval("({"+data+"})"));
                //console.log(options);
            }

            var target = this.target;
            var href = this.href;
            var posY = (parseInt(screen.height/2)) - (parseInt(options.height/2));
            var posX = (parseInt(screen.width/2)) - (parseInt(options.width/2));
            var win = window.open(href, target, 'titlebar=' + options.titlebar.setProperty() + ', screenX='+ posX +', screenY='+ posY +', left='+ posX +', top='+ posY +', status=' + options.status.setProperty() + ', resizable=' + options.resizable.setProperty() + ', toolbar=' + options.toolbar.setProperty() + ', scrollbars=' + options.scrollbars.setProperty() + ', menubar=' + options.menubar.setProperty() + ', width='+ options.width +', height='+ options.height);
            win.focus();
            return false;
        });
    });
};
/**/


/**
* Cookie
* @author Dongkkase (http://eyecandyzero.tistory.com)
*
* 주어진 키값과 기타 옵션의 매개변수를 사용하여 쿠키를 만든다.
* @example $.cookie('the_cookie', 'the_value');
* @desc Set the value of a cookie.
* @example $.cookie('the_cookie', 'the_value', { expires: 7, path: '/', domain: 'jquery.com', secure: true });
* @desc Create a cookie with all available options.
* @example $.cookie('the_cookie', 'the_value');
* @desc Create a session cookie.
* @example $.cookie('the_cookie', null);
* @desc 값으로 null을 전달하여 쿠키를 삭제한다. 쿠키가 설정 될 때 사용 된 것과 동일한 도메인을 사용할 필요가 있는지 유의.
*
* @param String key The key of the cookie.
* @param String value The value of the cookie.
* @param Object options An object literal containing key/value pairs to provide optional cookie attributes.
* @option Number|Date expires Either an integer specifying the expiration date from now on in days or a Date object.
* 음수(과거 날짜 등)를 지정한 경우 cookie가 삭제 된다.
* null로 설정하거나 생략하면, 쿠키는 브라우저가 종료될때 유지 되지 않는다.
*
* @option String path The value of the path atribute of the cookie (default: path of page that created the cookie).
* @option String domain The value of the domain attribute of the cookie (default: domain of page that created the cookie).
* @option Boolean secure If true, the secure attribute of the cookie will be set and the cookie transmission will
* require a secure protocol (like HTTPS).
* @type undefined
*
* 만들어진 쿠키의 값을 가지고 온다.
* @example $.cookie('the_cookie');
* @desc Get the value of a cookie.
*
* @param String key The key of the cookie.
* @return The value of the cookie.
* @type String
* @example Driectory /lib/demo/func.cookie/
*/
(function (factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as anonymous module.
        define(['jquery'], factory);
    } else {
        // Browser globals.
        factory(jQuery);
    }
}(function ($) {

    var pluses = /\+/g;

    function raw(s) {
        return s;
    }

    function decoded(s) {
        return decodeURIComponent(s.replace(pluses, ' '));
    }

    function converted(s) {
        if (s.indexOf('"') === 0) {
            // This is a quoted cookie as according to RFC2068, unescape
            s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
        }
        try {
            return config.json ? JSON.parse(s) : s;
        } catch(er) {}
    }

    var config = $.cookie = function (key, value, options) {

        // write
        if (value !== undefined) {
            options = $.extend({}, config.defaults, options);

            if (typeof options.expires === 'number') {
                var days = options.expires, t = options.expires = new Date();
                t.setDate(t.getDate() + days);
            }

            value = config.json ? JSON.stringify(value) : String(value);

            return (document.cookie = [
                config.raw ? key : encodeURIComponent(key),
                '=',
                config.raw ? value : encodeURIComponent(value),
                options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
                options.path    ? '; path=' + options.path : '',
                options.domain  ? '; domain=' + options.domain : '',
                options.secure  ? '; secure' : ''
            ].join(''));
        }

        // read
        var decode = config.raw ? raw : decoded;
        var cookies = document.cookie.split('; ');
        var result = key ? undefined : {};
        for (var i = 0, l = cookies.length; i < l; i++) {
            var parts = cookies[i].split('=');
            var name = decode(parts.shift());
            var cookie = decode(parts.join('='));

            if (key && key === name) {
                result = converted(cookie);
                break;
            }

            if (!key) {
                result[name] = converted(cookie);
            }
        }

        return result;
    };

    config.defaults = {};

    $.removeCookie = function (key, options) {
        if ($.cookie(key) !== undefined) {
            $.cookie(key, '', $.extend(options, { expires: -1 }));
            return true;
        }
        return false;
    };

}));
/**/


/**
 * 앵커태그에 명시된 주소로 팝업창 열기.
 * @author Dongkkase (http://eyecandyzero.tistory.com)
 * @func removeText: 숫자제외 텍스트 제거.
 * @func numberFormat: php number_format() 과 동일 효과.
 * @func onlyNumber: 위의 모든 기능 포함.
 * @example Driectory /lib/demo/func.popup/
 */
(function ($) {
    $.fn.removeText = function(_v){
        //console.log("removeText: 숫자 제거 합니다.");
        if (typeof(_v)==="undefined")
        {
            $(this).each(function(){
                this.value = this.value.replace(/[^0-9]/g,'');
            });
        }
        else
        {
            return _v.replace(/[^0-9]/g,'');
        }
    };


    $.fn.numberFormat = function(_v){
        this.proc = function(_v){
            var tmp = '',
                number = '',
                cutlen = 3,
                comma = ','
                i = 0,
                len = _v.length,
                mod = (len % cutlen),
                k = cutlen - mod;

            for (i; i < len; i++)
            {
                number = number + _v.charAt(i);
                if (i < len - 1)
                {
                    k++;
                    if ((k % cutlen) == 0)
                    {
                        number = number + comma;
                        k = 0;
                    }
                }
            }
            return number;
        };

        var proc = this.proc;
        if (typeof(_v)==="undefined")
        {
            $(this).each(function(){
                this.value = proc($(this).removeText(this.value));
            });
        }
        else
        {
            return proc(_v);
        }
    };

    $.fn.onlyNumber = function (p) {
        $(this).each(function(i) {
            $(this).attr({'style':'text-align:right'});

            this.value = $(this).removeText(this.value);
            this.value = $(this).numberFormat(this.value);

            $(this).bind('keypress keyup',function(e){
                // value check.
                this.value = $(this).removeText(this.value);
                this.value = $(this).numberFormat(this.value);
            });
        });
    };

})(jQuery);
/**/
