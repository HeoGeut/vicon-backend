<?php 
define('ROOT_PATH', $_SERVER['DOCUMENT_ROOT']."/");
require_once(ROOT_PATH."admin/proc.php");
include $_SERVER['DOCUMENT_ROOT']."/lib/class/zip.class.php";

$query = $_POST['query'];

$zip = new ZipCode();
$res = $zip->search($query);

if (gettype($res)=="array")
{
    
    foreach ($res as $key)
    {
        //echo $key[zip1]."-".$key[zip1]." ".$key[addr]."<br/>";
        $HTML .= "<tr>";
        $HTML .= "<td class='txt_center'>{$key[zip1]}-{$key[zip2]}</td>";
        $HTML .= "<td class='txt_left'>{$key[addr]}</td>";
        $HTML .= "<td class='txt_center'><span class='button blue'><button type='button' class='select_addr' target='$key[zip1]|$key[zip2]|$key[addr2]'>선택</button></span></td>";
        $HTML .= "</tr>";
    }   
}
else
{
    $HTML = "<tr><td colspan='3'>{$res}</td></tr>";
}

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<?include(ROOT_PATH."main/metadata.html")?>
<title><?=MAIN_TITLE?></title>
<?include(ROOT_PATH."main/include.php")?>
<style>
html{}
body{background:none; font-size:11px;}
input{border:1px solid #DDD}
.tbl_lst01 {margin:0 2px;}
.tbl_lst01.srch {border-top:2px solid #999; margin-top:20px;}
.tbl_lst01.srch table{width:100%}
.tbl_lst01.srch th { margin:0 auto; height:30px; border-bottom:1px solid #DDDDDD; text-align:center; }
.tbl_lst01.srch td {padding:4px 4px 7px 4px; border-bottom:1px solid #DDDDDD}

.form_table3 {margin:2px;font-size:12px;font-family:Tahoma, Geneva, sans-serif; border:1px solid #CCC; background:#efefef}
.form_table3 input,textarea,select,button {font-size:12px;color:#a1a1a1; background-color:#FFF; padding:3px; }
.form_table3 table {border:0;border-top:0px solid #999}
.form_table3 th {height:24px;padding:0 0 0 20px;order:0;vertical-align:middle;font-weight:bold;color:#444;text-align:left;}
.form_table3 td {padding:0;border:0;vertical-align:middle;font-weight:normal;color:#a1a1a1}
.data_view{overflow-y:scroll; height:300px;}
.txt_center{}

.pop_body{margin:0;padding:0;}
</style>
</head>
<body>

<div class="pop_body">
    <div class="title"><h3>우편번호 찾기</h3></div>
    <div class="form_table3">
        <form name="frm" action="<?=$_SERVER['PHP_SELF']?>" method="post">
        <table border="0" cellspacing="0" width="100%">
        <colgroup>
        <col width="80" /><col/>
        </colgroup>
        <tr>
        <th>지역명</th>
        <td class='txt_left'>
        <input type="text" name="query" class="text" />
        <span class="button"><button type="submit">검색</button></span>
        </td>
        </tr>
        </table>
        </form>
    </div>

    <div class="tbl_lst01 srch" style="padding-bottom:0;">
        <table border="0" cellspacing="0">
        <colgroup>
        <col width="80" /><col /><col width="60" />
        </colgroup>
        <tr>
        <th>우편번호</th>
        <th class='txt_left'>주소</th>
        <th>선택</th>
        </tr>
        </table>
    </div>
</div>

<div class="tbl_lst01 srch" style="border-top:none; margin-top:0;padding-top:0;">

    <table border="0" cellspacing="0">
    <colgroup>
    <col width="80" /><col /><col width="60" />
    </colgroup>
    <?=$HTML?>
    </table>
</div>



<div id="url_copy"></div>
<script type="text/javascript">
jQuery(function($){
    $(".select_addr").click(function(){
        var $addr = $(this).attr("target");
        $addr = $addr.split("|");
        
        try
        {
            $(".zip1", opener.document).val($addr[0]);
            $(".zip2", opener.document).val($addr[1]);
            $(".addr", opener.document).val($addr[2]);
        }
        catch (err)
        {
            alert(err);
        }
        
        self.close();
    });
});
</script>


</body>
</html>
