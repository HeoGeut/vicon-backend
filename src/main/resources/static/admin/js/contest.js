jQuery(function () {
    $("#smit").click(function () {
        $(this).parents("form").submit()
    });
    // 제안서 팝업
    $(".pop_open").magnificPopup({
        mainClass: 'mfp-fade',
        showCloseBtn: true
    });

    // 제안서보기
    $('.pop_open').click(function (e) {
        e.preventDefault();
        var obj = $(this).attr('data-object');
        var popup = $('#lpop');

        $.post('/admin/contest/applyJax', {type: 'pop', obj: obj}, function (data) {
            if (data) {
                popup.html(
                    "<button title='Close (Esc)' type='button' class='mfp-close'>×</button>" +
                    "<p class='p1'><span>" + data.username + "</span> 님의 제안서</p>" +
                    "<div class='view_content'><p>채널 소개</p>" +
                    "<div>" + data.channelInfo + "</div>" +
                    "</div>" +
                    "<div class='view_content'><p>유사 성공 사례</p>" +
                    "<div>" + data.successStory + "</div>" +
                    "</div>" +
                    "<div class='view_content'><p>스토리보드</p>" +
                    "<div>" + data.storyboardText + "</div>" +
                    "</div>" +
                    "<div class='view_content'><p>콘티 드로잉</p>" +
                    data.storyboardDraw +
                    "</div>" +
                    "<div class='view_content'><p>기대효과 및 추가 메리트 어필</p>" +
                    "<div>" + data.expectEffect + "</div>" +
                    "</div>" +
                    "<div class='view_content'><p>파일 첨부</p>" +
                    "<div class='attach_bx'>" + data.files + "</div>" +
                    "</div>" +
                    "<div class='view_content'><p>영상 링크</p>" +
                    "<div class='attach_bx'>" + data.links + "</div>" +
                    "</div>" +
                    ""
                );
            }
        }, 'json');
    });

    //del
    $(".del").click(function (e) {
        e.preventDefault();
        if (!confirm("정말 삭제 하시겠습니까?")) return false;

        var key = $(this).parents("tr").attr("target");
        $.ajax({
            url: "contest/ajax",
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

    // cancel button
    $(".cancel").click(function (e) {
        e.preventDefault();
        location.href = '/admin/contest';
    });

    // state toggle
    $(".state_toggle").click(function (e) {
        e.preventDefault();
        var key = $(this).parents("tr").attr("target").toString();
        var value = $(this).attr("id").toString()
        var toggleData = {"id": key, "item": value}

        $.ajax({
            url: "contest/toggleAjax",
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
});
