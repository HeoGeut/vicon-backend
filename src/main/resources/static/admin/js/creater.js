jQuery(function(){
	$("#smit").click(function(){
        $(this).parents("form").submit()
    });

    //del
	$(".del").click(function(e){
		e.preventDefault();
	    if (!confirm("정말 삭제 하시겠습니까?")) return false;

	    var key = $(this).parents("tr").attr("target");
		$.ajax({
			url: "member/ajax",
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

});
