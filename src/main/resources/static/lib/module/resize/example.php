<?php
#
# Image Resize Sample File
# J.S.B
# Create : 2010년 6월 29일 화요일
#
# 대상 파일 : jpg, gif, png
# 예외 : gif 파일이 움직이는 애미메이션일 경우 첫 번째 프레임 이미지만 저장이 
#        된다. 즉, 리사이징이 될 경우 동적인 이미지에서 정적인 이미지로 카피.
#
# resizeToWidth(x)  : 가로 기준 세로 백분율 리사이즈
# resizeToHeight(y) : 세로 기준 가로 백분율 리사이즈
# scale(percent)    : 전체 이미지 퍼센트
# resize(x,y)       : 일반적인 리사이즈
#
# window path
#   $path = str_replace("/","\\",str_replace("sample.php", "", $_SERVER[SCRIPT_FILENAME]));
# linux path
#   $path = str_replace("sample.php", "", $_SERVER[SCRIPT_FILENAME]);
#

if ($_FILES['files'])
{
    $path = str_replace("/","\\",str_replace(basename(__FILE__), "", $_SERVER[SCRIPT_FILENAME]));

    $filename = $_FILES["files"]["name"];
    $tmp_file = $_FILES['files']['tmp_name'];

    # get extension
    $extension = explode(".", $filename);
    $extension = $extension[sizeof($extension)-1];

    # save file name
    $copyFileName = time().".".$extension;

    # 현제 디렉토리에 사본 파일 만들기
    if (!copy($tmp_file,$path.$copyFileName)) continue;

    # image resize
    include "resize.class.php";
    $img = new img_resize();
    $img->load($copyFileName);
    $img->scale(50);
    $img->save("resize_".$copyFileName);

    # resize image open
    if (fopen($path."resize_".$copyFileName, "r"))
    {
        echo "<img src=\"resize_$copyFileName\"/><br/>";
        echo "resize_".$copyFileName;
    }
    else continue;
}
else
{
?>
    <form name="frm" action="example.php" method="post" enctype="multipart/form-data">
    <input type="file" name="files"/>
    <input type="submit"/>
    </form>
<?
}    
?>