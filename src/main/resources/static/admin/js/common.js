jQuery(function () {
    // img upload
    $(".img_upload_btn").click(function (e) {
        e.preventDefault();
        $(this).parents(".img_group").find(".img_upload_target").trigger("click");
    });

    $(".img_delete_btn").click(function (e) {
        e.preventDefault();
        $(this).parents(".img_group").find(".img_upload_target").val("");
        $(this).parents(".img_group").find(".img_del").val("A");
        $(this).parents(".img_group").find("img").attr("src", "/image/admin/noimage.png");
    });

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

    // summernote editor
    $('#summernote').summernote({
        lang: 'ko-KR',
        placeholder: '',
        height: 400,
        callbacks: {
            onImageUpload: function (files) {
                sendFile(files[0], $(this));
            },
            onEnter: function (e) {
                e.preventDefault();
            }
        }
    });

    function sendFile(file, editor) {
        var data = new FormData();
        data.append("uploadFile", file);
        $.ajax({
            data: data,
            type: "POST",
            url: "/lib/module/summernote/image_upload.php",
            enctype: 'multipart/form-data',
            cache: false,
            contentType: false,
            processData: false,
            success: function (e) {
                data = jQuery.parseJSON(e);

                if (data.status == "OK") {
                    editor.summernote('insertImage', data.url);
                } else {
                    alert(data.message);
                }
            },
            error: function (data) {
                console.log(data);
            }
        });
    }

    // cancel button
    $(".cancel").click(function (e) {
        e.preventDefault();
        location.href = '/admin/member';
    });

    // creator list
    $(".creator_list").click(function (e){
        e.preventDefault();
        location.href = '/admin/creator';
    });

    // list row
    $(".page_row").on("change", function () {
        $(this).parents("form").submit();
    });

    // state toggle
    $(".state_toggle").click(function (e) {
        e.preventDefault();
        var key = $(this).parents("tr").attr("target").toString();
        var value = $(this).attr("id").toString()
        var toggleData = {"contestId": key, "item": value}

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

    //list sort
    $(".sort").each(function () {
        var $sorting = $(this).attr("data-sorting");
        if ($sorting == "desc") $(this).append("<i class='fa fa-arrow-down marginl5' aria-hidden='true'></i>");
        else if ($sorting == "asc") $(this).append("<i class='fa fa-arrow-up marginl5' aria-hidden='true'></i>");
    });

    $(".sort").on("click", function () {
        console.log(1);
        var $sorter = $(this).attr("data-sorter");
        var $sorting = $(this).attr("data-sorting");

        if ($sorting == "" || $sorting == "asc") $print_sorting = "desc";
        else if ($sorting == "desc") $print_sorting = "asc";

        $(".standard_sorter").val($sorter);
        $(".standard_sorting").val($print_sorting);
        $(this).parents("form").submit();

    });


});
