jQuery(function(){
	$("#smit").click(function(){
        $(this).parents("form").submit()
    });

    //del
	$(".del").click(function(e){
		var key = $(this).parents("tr").attr("target");
		$.ajax({
			url: "faq/ajax",
			type: "POST",
			dataType: 'text',
			data: key,

			success: function (data) {
				if (data == 1) {
					alert("삭제에 성공하였습니다.")
					location.reload();
				} else {
					alert("삭제 실패.");
				}
			},
			error: function () {
				alert("삭제 실패.");
			}
		});
	});

	// cancel button
	$(".cancel").click(function (e) {
		e.preventDefault();
		location.href = '/admin/faq';
	});

});
