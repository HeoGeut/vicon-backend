<?php
#
# Image Resize class
# J.S.B
# Create    : 2010년 6월 29일 화요일
# release   : 2010년 7월 12일 월요일 (update 내용은 release.php 참조)
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
ini_set('memory_limit', -1);
class img_resize
{
    var $image;
    var $image_type;
    var $image_width;
    # 이미지 불러오기
    function load($filename)
    {
        $image_info = getimagesize($filename);
        $this->image_type = $image_info[2];
        $this->image_width= $image_info[0];
        
        if( $this->image_type == IMAGETYPE_JPEG )
        {
            $this->rotate($filename);
        }
        elseif( $this->image_type == IMAGETYPE_GIF )
        {
            $this->image = imagecreatefromgif($filename);
        }
        elseif( $this->image_type == IMAGETYPE_PNG )
        {
            $this->image = imagecreatefrompng($filename);
        }
        /*
        elseif( $this->image_type == IMAGETYPE_BMP )
        {
            include "bmp.class.php";
            $this->image = imagecreatefrombmp($filename);
        }
        */
    }

    function rotate($filename)
    {
        $exifData = exif_read_data($filename);
        
        switch ($exifData['Orientation']) 
        {
            case 6: $degree = 270; break;
            case 8: $degree = 90;  break;
            case 3: $degree = 180; break;
        }
        
        if ($degree)
        {
            $source = imagecreatefromjpeg($filename);
            $this->image = imagerotate ($source , $degree, 0);
        }
        else 
        {
            $this->image = imagecreatefromjpeg($filename);
        }
    }

    # 이미지별 저장
    function save($filename, $image_type=IMAGETYPE_JPEG, $compression=100, $permissions=null)
    {
        if ( $image_type == IMAGETYPE_JPEG )
        {
            imagejpeg($this->image, $filename, $compression);
        }
        elseif ( $image_type == IMAGETYPE_GIF )
        {
            imagegif($this->image, $filename);
        }
        elseif ( $image_type == IMAGETYPE_PNG )
        {
            imagepng($this->image, $filename);
        }
        elseif ( $image_type == IMAGETYPE_BMP )
        {
            imagepng($this->image, $filename);
        }
        if ( $permissions != null )
        {
            chmod($filename, $permissions);
        }
    }

    # 이미지 정보 출력
    function output($image_type=IMAGETYPE_JPEG)
    {
        if ( $image_type == IMAGETYPE_JPEG )
        {
            imagejpeg($this->image);
        }
        elseif ( $image_type == IMAGETYPE_GIF )
        {
            imagegif($this->image);
        }
        elseif ( $image_type == IMAGETYPE_PNG )
        {
            imagepng($this->image);
        }
        elseif ( $image_type == IMAGETYPE_BMP )
        {
            imagepng($this->image);
        }

    }

    # 원본 이미지의 가로세로 사이즈를 얻어온다.
    function getWidth()
    {
        return imagesx($this->image);
    }
    function getHeight()
    {
        return imagesy($this->image);
    }

    # 리사이징할 사이즈
    # 세로기준 백분율
    function resizeToHeight($height)
    {
        $ratio = $height / $this->getHeight();
        $width = $this->getWidth() * $ratio;
        $this->resize($width,$height);
    }

    # 가로기준 백분율
    function resizeToWidth($width)
    {
        $ratio = $width / $this->getWidth();
        $height = $this->getheight() * $ratio;
        $this->resize($width,$height);
    }

    # 백분율
    function scale($scale)
    {
        $width = $this->getWidth() * $scale/100;
        $height= $this->getHeight() * $scale/100;
        $this->resize($width,$height);
    }

    # 일반 리사이즈
    function resize($width,$height)
    {
        $new_image = imagecreatetruecolor($width, $height);
        imagecopyresampled($new_image, $this->image, 0, 0, 0, 0, $width, $height, $this->getWidth(), $this->getHeight());
        $this->image = $new_image;
    }
}
?>

