jQuery(function ($) {
    $(".img_upload_btn").click(function (e) {
        e.preventDefault();
        $(this).parents(".img_group").find(".img_upload_target").trigger("click");
    });

    $('#userId').keyup(function () {
        if (!(event.keyCode >= 37 && event.keyCode <= 40)) {
            var inputVal = $(this).val();
            $(this).val(inputVal.replace(/[^a-zA-Z0-9]/gi, ''));
        }

        // var inputVal = $(this).val();
        // $(this).val((inputVal.replace(/[^(\ㄱ-ㅎㅏ-ㅣ가-힣ㅣa-zA-Z0-9)]/gi,'')));
    });

    // $(".img_delete_btn").click(function(e){
    //     e.preventDefault();
    //     $(this).parents(".img_group").find(".img_upload_target").val("");
    //     $(this).parents(".img_group").find(".img_del").val("A");
    //     $(this).parents(".img_group").find("img").attr("src","/image/site/noimage.png");
    // });

    $(".img_upload_target").change(function () {
        var file = $(this)[0].files[0];
        var viewer = $(this).attr("data-viewer");
        displayAsImage(file, "img_upload_view" + viewer);
    });

    function displayAsImage(file, containerid) {
        if (typeof FileReader !== "undefined") {
            var container = $("." + containerid);
            var img = document.createElement("img");
            var reader;
            $("." + containerid).html("");
            container.append(img);
            reader = new FileReader();
            reader.onload = (function (theImg) {
                return function (evt) {
                    theImg.src = evt.target.result;
                };
            }(img));
            reader.readAsDataURL(file);
        }
    }

    // 아이디 중복체크
    $('#id_chk').click(function () {
        var mem_id = $('input[name=mem_id]').val();

        if (mem_id == "") {
            alert("아이디를 입력해 주세요.")
            $("input[name=mem_id]").focus();
            return false;
        }

        $.ajax({
            url: 'ajax',
            type: 'POST',
            dataType: 'text',
            data: mem_id,

            success: function (data) {
                if (data == 0) {
                    $("input[name=mem_id_chk]").val('1');
                    $('#id_chk').css('display', 'none');
                    $('#id_txt').css('display', 'block');
                } else {
                    alert("사용 불가능한 아이디입니다.");
                }
            },
            error: function () {

            }
        })
    });

    // 아이디 입력 여부에 따라 중복확인 버튼 활성화
    $("input[name=mem_id]").on("propertychange change keyup paste input", function () {
        if ($("input[name=mem_id]").val() == '') {
            $('#id_chk').css('display', 'block');
            $('#id_txt').css('display', 'none');
            $("input[name=mem_id_chk]").val('');
        }
    });

    // 비밀번호 체크 정규식
    function check_pwd(str) {
        var reg1 = /^[a-z0-9!~@#$%^&*()?+=\/]{8,16}$/;
        var reg2 = /[a-z]/g;
        var reg3 = /[0-9]/g;
        var reg4 = /[!~@#$%^&*()?+=\/]/g;
        return (reg1.test(str) && reg2.test(str) && reg3.test(str) && reg4.test(str));
    };

    // 비밀번호 확인
    $("input[name=mem_pw2]").on("propertychange change keyup paste input", function () {
        var mem_pw = $("input[name=mem_pw]").val();
        var mem_pw2 = $("input[name=mem_pw2]").val();

        if (mem_pw != mem_pw2) {
            $('#pw_chk2').text('비밀번호 불일치');
        } else {
            $('#pw_chk2').text('');
        }
    });

    // 비밀번호 정규식확인
    $("input[name=mem_pw]").on("propertychange change keyup paste input", function () {
        if (check_pwd($('input[name=mem_pw]').val()) == false) {
            $('#pw_chk').text('');
        }
    });

    $('#join_smit').click(function () {
        if ($("input[name=mem_id]").val() == "") {
            alert("아이디를 입력해 주세요.")
            $("input[name=mem_id]").focus();
            return false;
        }

        if ($("input[name=mem_id]").val().length < 5) {
            alert('아이디는 영문, 숫자 5~12자로 입력해주세요.');
            $("input[name=mem_id]").focus();
            return false;
        }

        if ($("input[name=mem_pw]").val() == "") {
            alert("비밀번호를 입력해 주세요.");
            $("input[name=mem_pw]").focus();
            return false;
        }

        if (check_pwd($('input[name=mem_pw]').val()) == false) {
            alert('비밀번호는 영어, 숫자, 특수기호를 넣어 8~16자로 입력해주세요.');
            $("input[name=mem_pw]").focus();
            $('#pw_chk').text('사용불가능');
            return false;
        }

        if ($("input[name=mem_pw]").val().length < 8) {
            alert('비밀번호는 영어, 숫자, 특수기호를 넣어 8~16자로 입력해주세요.');
            $("input[name=mem_pw]").focus();
            return false;
        }

        if ($("input[name=mem_pw2]").val() == "") {
            alert("비밀번호 확인을 입력해 주세요.");
            $("input[name=mem_pw2]").focus();
            return false;
        }

        if ($("input[name=mem_pw]").val() != $("input[name=mem_pw2]").val()) {
            alert("입력한 비밀번호가 일치하지 않습니다.");
            $("input[name=mem_pw2]").focus();
            return false;
        }

        if ($("input[name=mem_hp1]").val() == "" || $("input[name=mem_hp2]").val() == "" || $("input[name=mem_hp3]").val() == "") {
            alert("휴대폰번호를 입력해 주세요.")
            $("input[name=mem_hp]").focus();
            return false;
        }

        if ($("input[name=mem_email1]").val() == "" || $("input[name=mem_email2]").val() == "") {
            alert("이메일 주소를 입력해 주세요.");
            return false;
        }

        if ($("input[name=mem_id_chk]").val() == "" || $("input[name=mem_id_chk]").val() == "0") {
            alert("아이디 중복확인을 체크해주세요.");
            return false;
        }

        if ($("input[name=chk]").is(":checked") == false) {
            alert('개인정보 취급방침에 동의해주세요.');
            return false;
        }

        $(this).parents('form').submit();
    });

    // 비밀번호 찾기
    $('#find_smit2').click(function () {
        if ($("input[name=mem_id]").val() == "") {
            alert("아이디를 입력해 주세요.");
            $("input[name=mem_id]").focus();
            return false;
        }

        if ($("input[name=mem_hp2]").val() == "") {
            alert("연락처를 입력해 주세요.");
            $("input[name=mem_hp2]").focus();
            return false;
        }

        $(this).parents('form').submit();
    });
    $(".lpop").magnificPopup({
        mainClass: 'mfp-fade',
        showCloseBtn: true
    });


    // 회원정보수정
    $('#edit_smit').click(function () {
        // 비밀번호 입력시
        if ($("input[name=mem_pw2]").val() != "") {
            if (check_pwd($('input[name=mem_pw]').val()) == false) {
                alert('비밀번호는 영어, 숫자, 특수기호를 넣어 8~16자로 입력해주세요.');
                $("input[name=mem_pw]").focus();
                $('#pw_chk').text('사용불가능');
                return false;
            }

            if ($("input[name=mem_pw]").val().length < 8) {
                alert('비밀번호는 영어, 숫자, 특수기호를 넣어 8~16자로 입력해주세요.');
                $("input[name=mem_pw]").focus();
                return false;
            }

            if ($("input[name=mem_pw2]").val() == "") {
                alert("비밀번호 확인을 입력해 주세요.");
                $("input[name=mem_pw2]").focus();
                return false;
            }
        }

        if ($("input[name=mem_pw]").val() != $("input[name=mem_pw2]").val()) {
            alert("입력한 비밀번호가 일치하지 않습니다.");
            $("input[name=mem_pw2]").focus();
            return false;
        }

        if ($("input[name=mem_hp1]").val() == "" || $("input[name=mem_hp2]").val() == "" || $("input[name=mem_hp3]").val() == "") {
            alert("휴대폰번호를 입력해 주세요.")
            $("input[name=mem_hp]").focus();
            return false;
        }

        if ($("input[name=mem_email1]").val() == "" || $("input[name=mem_email2]").val() == "") {
            alert("이메일 주소를 입력해 주세요.");
            return false;
        }


        $(this).parents('form').submit();
    });
});
