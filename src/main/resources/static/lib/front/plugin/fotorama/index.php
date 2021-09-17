<?php
header('X-UA-Compatible: IE=edge');
define('ROOT_PATH', $_SERVER['DOCUMENT_ROOT']."/");
require_once(ROOT_PATH."config/global_config.php");
require_once(ROOT_PATH."lib/DB.php");
require_once(ROOT_PATH."lib/function.php");
require_once(ROOT_PATH."lib/class/Analytics.class.php");

//http://fotorama.io/
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><?=MAIN_TITLE?></title>
    <?include(ROOT_PATH."config/metadata.html")?>
    <?include(ROOT_PATH."/config/total_include.php")?>
	<link href="fotorama.css" rel="stylesheet">
	<script src="fotorama.js"></script>
</head>
<body>
	<div class="fotorama" data-width="700" data-ratio="700/467" data-max-width="100%">
	  	<img src="/lib/plugin/demo_img/test1.jpg">
	  	<img src="/lib/plugin/demo_img/test2.jpg">
	  	<img src="/lib/plugin/demo_img/test3.jpg">
	  	<img src="/lib/plugin/demo_img/test4.jpg">
	</div>
</body>
</html>
