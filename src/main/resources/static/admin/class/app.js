// document.domain=location.host;
var App = (function($, _){

    /* s:Private Area ****************************************************** */
    var user  = "",
        check_flag = false,
        check_chat_time = 0,
        chat_dir,
        chat_user,
        dataObject = "",
        start_notifi = false;
    /* e:Private Area ****************************************************** */


    var construct = function(){
        $(window).load(function(){
            //alert(1);
        });
    }



    // Iframe Resize
    var resizeHeight = function(){
        $(window).bind("load", function(){
            $(".resizeHeight").each(function(){
                var the_height = $(this).get(0).contentWindow.document.body.scrollHeight;
                $(this).get(0).height = the_height + 30;
                //$(this).css({"line-height":(the_height + 30)+"px"})
            })
        });
    }

    return {
        init : function(){
            construct();
            resizeHeight();
            //newChatCheck();
        }
    }

})(jQuery, _);

window.load = App.init();
