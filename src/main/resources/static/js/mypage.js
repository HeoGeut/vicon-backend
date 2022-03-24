jQuery(function ($) {
  // 작업완료 처리
  $('.chk_btn').click(function (e) {
    e.preventDefault();
    var key = $(this).attr('data-key');

    if ($(this).attr('class') != 'chk_btn act') {
      if (!confirm("작업완료를 선택하시겠습니까?")) return false;
      $.ajax({
        url: 'ajaxComplete', type: 'POST', dataType: 'text', data: key, success: function (_data) {
          if (_data == 1) {
            alert('작업 완료 처리 되었습니다. 리뷰로 더 많은 사람에게 보여주세요');
            location.reload();
          } else {
            alert("DB 오류. 다시 시도해주세요.");
          }
        }, error: function () {
          alert("DB 오류. 다시 시도해주세요!");
        },
      })

    } else {
      alert("작업완료된 결과는 바꿀 수 없습니다.");
    }
  });

  $('.contest_list .lst li .title .p1').click(function () {
    if ($(this).parents('li').attr('class') == 'act') {
      $(this).parents('li').removeClass('act');
    } else {
      $('.contest_list .lst li').removeClass('act');
      $(this).parents('li').addClass('act');
    }
  });

  // 별점 입력
  $('.starRev span').click(function () {
    var res = $(this).attr('target');
    $('input[name=star]').val(res);

    $(this).parent().children('span').removeClass('on');
    $(this).addClass('on').prevAll('span').addClass('on');

    return false;
  });

  $('.rv_smit').click(function () {
    var title = $('input[name=title]').val(),
      youtubeUrl = $('input[name=youtubeUrl]').val(),
      content = $('textarea[name=content]').val();

    if (title == '') {
      alert('제목을 입력해주세요.');
      title.focus();
      return false;
    }

    if (youtubeUrl == '') {
      alert('유튜브링크를 입력해주세요.');
      youtubeUrl.focus();
      return false;
    }

    if (content == '') {
      alert('내용을 입력해주세요.');
      content.focus();
      return false;
    }

    $(this).parents("form").submit();
  });

  // 제안서 팝업
  $(".pop_open").magnificPopup({
    mainClass: 'mfp-fade', showCloseBtn: true
  });

  // 채팅 팝업
  $(".chat_pop").magnificPopup({
    mainClass: 'mfp-fade', showCloseBtn: true
  });

  // 후기 팝업
  $(".wrt_btn").magnificPopup({
    mainClass: 'mfp-fade', showCloseBtn: true
  });

  // 제안서 채택하기
  $('.apply_chk').click(function (e) {
    e.preventDefault();
    var obj = $(this).attr('data-object');

    $.ajax({
      url: 'ajaxApplyConfirm', type: 'POST', dataType: 'text', data: obj, success: function (_data) {
        if (_data == 1) {
          alert('변경되었습니다!');
          location.reload();
        } else if (_data == 0) {
          alert("이미 모집인원이 꽉 찼습니다");
        } else {
          alert("DB 오류. 채택 실패.")
        }
      }, error: function (_data) {
        console.log(_data)
        alert("DB 오류. 채택 실패111.")
      },
    })
  });

  // 제안서보기
  $('.pop_open').click(function (e) {
    e.preventDefault();
    var obj = $(this).attr('data-object');
    var popup = $('#lpop');

    // $.post('ajxApply', {type: 'pop', id: obj}, function (_data) {
    //     if (_data) {
    //         var cont = "<button title='Close (Esc)' type='button' class='mfp-close'>×</button>" +
    //             "<p class='p1'><span>" + _data.memberId + "</span> 님의 제안서</p>" +
    //             "<div class='view_content'><p>채널 소개</p>" +
    //             "<div>" + _data.channelInfo + "</div>" +
    //             "</div>" +
    //             "<div class='view_content'><p>유사 성공 사례</p>" +
    //             "<div>" + _data.successStory + "</div>" +
    //             "</div>" +
    //             "<div class='view_content'><p>스토리보드</p>" +
    //             "<div>" + _data.storyboardText + "</div>" +
    //             "</div>";
    //
    //         if (_data.storyboardDraw) {
    //             cont += "<div class='view_content'><p>콘티 드로잉</p>" +
    //                 _data.storyboardDraw +
    //                 "</div>";
    //         }
    //
    //         cont += "<div class='view_content'><p>기대효과 및 추가 메리트 어필</p>" +
    //             "<div>" + _data.expectEffect + "</div>" +
    //             "</div>" +
    //             // "<div class='view_content'><p>파일 첨부</p>"+
    //             // "<div class='attach_bx'>"+_data.a_file+"</div>"+
    //             // "</div>"+
    //             // "<div class='view_content'><p>영상 링크</p>"+
    //             // "<div class='attach_bx'>"+_data.a_link+"</div>"+
    //             "<button type='button' class='pop_close_btn'>확인</button><script>$('.pop_close_btn').click(function(){$(this).parents('.layer_pop').find('.mfp-close').trigger('click');});</script></div>" +
    //             ""
    //
    //         popup.html(cont);
    //     }
    // }, 'json');

    $.ajax({
      url: 'ajaxApply', type: 'POST', data: obj, success: function (returnData) {
        var _data = JSON.parse(returnData);
        console.log(_data)
        console.log(_data.memberId)
        if (_data) {
          var cont = "<button title='Close (Esc)' type='button' class='mfp-close'>×</button>" + "<p class='p1'><span>" + _data.memberId + "</span> 님의 제안서</p>" + "<div class='view_content'><p>채널 소개</p>" + "<div>" + _data.channelInfo + "</div>" + "</div>" + "<div class='view_content'><p>유사 성공 사례</p>" + "<div>" + _data.successStory + "</div>" + "</div>" + "<div class='view_content'><p>스토리보드</p>" + "<div>" + _data.storyboardText + "</div>" + "</div>";

          if (_data.storyboardDraw) {
            cont += "<div class='view_content'><p>콘티 드로잉</p>" + _data.storyboardDraw + "</div>";
          }

          cont += "<div class='view_content'><p>기대효과 및 추가 메리트 어필</p>" + "<div>" + _data.expectEffect + "</div>" + "</div>" + // "<div class='view_content'><p>파일 첨부</p>"+
            // "<div class='attach_bx'>"+_data.a_file+"</div>"+
            // "</div>"+
            // "<div class='view_content'><p>영상 링크</p>"+
            // "<div class='attach_bx'>"+_data.a_link+"</div>"+
            "<button type='button' class='pop_close_btn'>확인</button><script>$('.pop_close_btn').click(function(){$(this).parents('.layer_pop').find('.mfp-close').trigger('click');});</script></div>" + ""

          popup.html(cont);
        }
      }, error: function () {
        console.log("error")
      },
    })
  });


  // 소통 창 열기
  $('.chat_pop').click(function () {
    var obj = $(this).attr('data-object');
    var popup = $('#chat_lpop .cmt_box_wrap');
    var key = $(this).attr('data-key');
    document.getElementById("addCmt_content").value = '';

    $.post('ajax.php', {type: 'chat', obj: obj, key: key}, function (_data) {
      if (_data) {
        var cont = "<button title='Close (Esc)' type='button' class='mfp-close'>×</button>" + // "<p class='p_name'><span>"+_data.mem_id+"</span> 님과의 소통</p>"+
          "<input type='hidden' name='addCmt_mem_idx' value='" + _data.mem_idx + "'/>" + "<input type='hidden' name='addCmt_a_idx' value='" + obj + "'/>" + "<div class='cmt_chat teacher_box'>" + "<div class='img_bx'>" + _data.mem_img + "</div>" + "<div class='cont_bx'>" + "<p class='p1'>" + _data.mem_id + "</p>" + "<p class='p2'>궁금한게 있다면 여기서 물어보세요.</p>" + "</div>" + "</div>" + _data.lst + "<div class='addCmt'></div>";
        popup.html(cont);
      }
    }, 'json');
  });

  $(".addCmt_btn").click(function () {
    // console.log(1);
    var mem_idx = $('input[name=addCmt_mem_idx]').val();
    var a_idx = $('input[name=addCmt_a_idx]').val();
    var content = $('textarea[name=addCmt_content]').val();

    if (content == '') {
      alert('내용을 입력해주세요.');
      return false;
    }

    $.ajax({
      url: '/main/mypage/addCmt_ajax.php', data: {
        mem_idx: mem_idx, a_idx: a_idx, content: content, type: 'insert'
      }, type: 'POST', dataType: 'html', success: function (result) {
        if (result == 'error') {
          alert('통신오류');
        } else {
          $('.addCmt').append(result);
          document.getElementById("addCmt_content").value = '';
        }
      }
    });
  });

  $('.cnt_txt').keyup(function (e) {
    var content = $(this).val();
    // console.log(content);
    var counter = $(this).parents('.txt_box').find('.counter');
    // console.log(counter);
    // counter.html("("+content.length+" / 2000자)");    //글자수 실시간 카운팅

    if (content.length > 199) {
      alert("최대 200자까지 입력 가능합니다.");
      $(this).val(content.substring(0, 199));
      // $('#counter').html("(2000 / 최대 2000자)");
    }
  });
});
