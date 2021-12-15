<?php

/*
 * 랜덤 문자열 생성(인수 : 길이, 타입)
 * 지정된 타입의 문자열로 지정된 길이의 랜덤 문자열을 반환한다.
 * 타입 0 : 영문 대소문자(A-Z,a-z), 숫자(0-9)
 * 타입 1 : 영문 대문자(A-Z), 숫자(0-9)
 * 타입 2 : 영문 소문자(a-z), 숫자(0-9)
 * 타입 3 : 영문 대문자(A-Z)
 * 타입 4 : 영문 소문자(a-z)
 * 타입 5 : 숫자(0-9)
 * 디폴트 : false 반환.
*/
function rand_str($length, $type)
{
    switch($type){
        case 0:
            $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890';
            break;
        case 1:
            $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890';
            break;
        case 2:
            $chars = 'abcdefghijklmnopqrstuvwxyz1234567890';
            break;
        case 3:
            $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
            break;
        case 4:
            $chars = 'abcdefghijklmnopqrstuvwxyz';
            break;
        case 5:
            $chars = '1234567890';
            break;
        default:
            return false;
    }
    $chars_length = (strlen($chars) - 1);
    $string = '';
    for ($i = 0; $i < $length; $i = strlen($string)){
        $string .= $chars{rand(0, $chars_length)};
    }
    return $string;
}


// 설정
$uploads_dir = '/upload';
$allowed_ext = array('jpg','jpeg','png','gif');

// 오류 확인
if( !isset($_FILES['uploadFile']['error']) ) {
	echo json_encode( array(
		'status' => 'error',
		'message' => 'Not found file.'
	));
	exit;
}
$error = $_FILES['uploadFile']['error'];
if( $error != UPLOAD_ERR_OK ) {
	switch( $error ) {
		case UPLOAD_ERR_INI_SIZE:
		case UPLOAD_ERR_FORM_SIZE:
			$message = "File is too big. ($error)";
			break;
		case UPLOAD_ERR_NO_FILE:
			$message = "Not found file. ($error)";
			break;
		default:
			$message = "Upload Error. ($error)";
	}
	echo json_encode( array(
		'status' => 'error',
		'message' => $message
	));
	exit;
}

// 변수 정리
$name = $_FILES['uploadFile']['name'];
$tmp_name = $_FILES['uploadFile']['tmp_name'];
$filename_ext = strtolower(array_pop(explode('.',$name)));
$file_name = date("YmdHis")."_".rand_str(5, 4).".".$filename_ext;

// 확장자 확인
if( !in_array($filename_ext, $allowed_ext) ) {
	echo json_encode( array(
		'status' => 'error',
		'message' => 'Useless filename.'
	));
	exit;
}

$path = '/data/summernote/';
$uploadDir = str_replace("\\", "/", $_SERVER['DOCUMENT_ROOT']).$path;
if(!is_dir($uploadDir)){
	mkdir($uploadDir, 0777);
}

$file_name = date("YmdHis")."_".rand_str(5, 4).".".$filename_ext;

$newPath = $uploadDir.$file_name;

@move_uploaded_file($tmp_name, $newPath);

// 파일 정보 출력
echo json_encode( array(
	'status' => 'OK',
	'name' => $name,
	'ext' => $filename_ext,
	'type' => $_FILES['uploadFile']['type'],
	'size' => $_FILES['uploadFile']['size'],
	'url' => $path.$file_name
));
?>
