jQuery(function () {
    $("#smit").click(function () {
        $(this).parents("form").submit()
    });
    //del
    $(".del").click(function (e) {
        e.preventDefault();
        if (!confirm("정말 삭제 하시겠습니까?")) return false;

        // var key = $(this).parents("tr").attr("target");
        var key = $(this).parents("tr").attr("target");
        console.log(key)
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
        })
    });


    $(".mail_btn").click(function (e) {
        e.preventDefault();
        var $key = $(this).parents("tr").attr("target");
        // console.log(1);
        $.post("ajax.php", {type: "mail", key: $key}, function (data) {
            alert('회원 이메일 주소로 메일이 발송되었습니다.');
        });
    });

    $(".mail_btn2").click(function (e) {
        e.preventDefault();
        var $key = $('input[name=key]').val();
        // console.log($key);
        $.post("ajax.php", {type: "mail", key: $key}, function (data) {
            alert('회원 이메일 주소로 메일이 발송되었습니다.');
        });
    });

})
;
