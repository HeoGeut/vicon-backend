<?php
header('X-UA-Compatible: IE=edge');
define('ROOT_PATH', $_SERVER['DOCUMENT_ROOT']."/");
require_once(ROOT_PATH."config/global_config.php");
require_once(ROOT_PATH."lib/DB.php");
require_once(ROOT_PATH."lib/function.php");
require_once(ROOT_PATH."lib/class/Analytics.class.php");

//http://alvarotrigo.com/fullPage/
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><?=MAIN_TITLE?></title>
    <?include(ROOT_PATH."config/metadata.html")?>
    <?include(ROOT_PATH."/config/total_include.php")?>
	
	<script type="text/javascript" src="jquery.slimscroll.min.js"></script>
	<script type="text/javascript" src="jquery.fullPage.min.js"></script>
	<link href="jquery.fullPage.css" rel="stylesheet">
</head>
<body>
	
<div id="fullpage">
	<div class="section " id="section0"><h1>fullPage.js</h1></div>
	<div class="section" id="section1">
	    <div class="slide" id="slide1"><h1>Slide Backgrounds</h1></div>
	    <div class="slide" id="slide2"><h1>Totally customizable</h1></div>
	</div>
	<div class="section" id="section2"><h1>Lovely images <br />for a lovely page</h1></div>
	<div class="section" id="section3"><h1>One Image = One thousand words</h1></div>
</div>
	
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$('#fullpage').fullpage({
			verticalCentered: false
		});
	});
</script>
<style>

/* Style for our header texts
* --------------------------------------- */
h1{
	font-size: 5em;
	font-family: arial,helvetica;
	color: #fff;
	margin:0;
	padding:0;
}

/* Centered texts in each section
* --------------------------------------- */
.section{
	text-align:center;
}


/* Backgrounds will cover all the section
* --------------------------------------- */
#section0,
#section1,
#section2,
#section3,
#section4{
	background-size: cover;
}

/* Defining each section background and styles
* --------------------------------------- */
#section0{
	background-image: url(/lib/plugin/demo_img/test1.jpg);
	padding: 30% 0 0 0;
}
#section2{
	background-image: url(/lib/plugin/demo_img/test2.jpg);
	padding: 6% 0 0 0;
}
#section3{
	background-image: url(/lib/plugin/demo_img/test3.jpg);
	padding: 6% 0 0 0;
}
#section3 h1{
	color: #000;
}


/*Adding background for the slides
* --------------------------------------- */
#slide1{
	background-image: url(/lib/plugin/demo_img/test4.jpg);
	padding: 6% 0 0 0;
}
#slide2{
	background-image: url(/lib/plugin/demo_img/test1.jpg);
	padding: 6% 0 0 0;
}


/* Bottom menu
* --------------------------------------- */
#infoMenu li a {
	color: #fff;
}
</style>

</html>
