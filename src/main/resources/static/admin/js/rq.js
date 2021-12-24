jQuery(function(){
    //del
	$(".del").click(function(e){
		e.preventDefault();
		if (!confirm("정말 삭제 하시겠습니까?")) return false;

		var key = $(this).parents("tr").attr("target");
		$.ajax({
			url: "qna/ajax",
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
		})
	});

	// state toggle
	$(".state_toggle").click(function (e) {
		e.preventDefault();
		var key = $(this).parents("tr").attr("target").toString();
		var value = $(this).attr("id").toString()
		var toggleData = {"id": key, "item": value}

		$.ajax({
			url: "qna/toggleAjax",
			type: "POST",
			data: toggleData,

			success: function (data) {
				if (data == 1) {
					location.reload();
				} else {
					alert("DB 오류입니다. 다시 시도해 주세요.");
				}
			},
			error: function () {
				alert("변경 실패.");
			}
		});
	});

	// cancel button
	$(".cancel").click(function (e) {
		e.preventDefault();
		location.href = '/admin/qna';
	});
});
