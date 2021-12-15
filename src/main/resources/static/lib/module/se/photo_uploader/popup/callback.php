<?php
header('Content-Type: text/html; charset=UTF-8');
define('ROOT_PATH', $_SERVER['DOCUMENT_ROOT']."/");
require_once(ROOT_PATH."lib/function.php");

//pre($_GET);
?>
<script type="text/javascript">
    // alert("callback");
    // document.domain 설정
    //try { document.domain = "petnpet.cres.pe.kr"; } catch(e) {}
    
    // execute callback script
    var sUrl = document.location.search.substr(1);
    if (sUrl != "blank") {
        var oParameter = {}; // query array

        sUrl.replace(/([^=]+)=([^&]*)(&|$)/g, function(){
            oParameter[arguments[1]] = arguments[2];
            return "";
        });
        
        if ((oParameter.errstr || '').length) { // on error
            (parent.jindo.FileUploader._oCallback[oParameter.callback_func+'_error'])(oParameter);
        } else {
            (parent.jindo.FileUploader._oCallback[oParameter.callback_func+'_success'])(oParameter);
       }
    }
</script>