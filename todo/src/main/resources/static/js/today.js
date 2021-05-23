$(function() {

    $('.arrow-left').click(function() {
        console.log('arrow-leftクリックされました！');
        $('.localNavigation').toggleClass('isActive');
    })

    $('.trash').click(function() {
        console.log('trashクリックされました！');
        $('#delForm').submit();
    })



    $('.important-mark').on('click', function(event) {
        console.log('importantクリックされました！');
        $(this).toggleClass('isActive');
        console.log(this)
    });

    $('.finish').click(function() {
        console.log('finishクリックされました！');
        $(this).children('input[name="finish-check"]').prop('checked', true);
    })


    $("#add-task").keydown(function(event) {
        if (event.key === "Enter") {
            console.log("Enterキーが押されました");
            $('#createForm').submit();
        }
    });

    // テキストボックスにフォーカス時、フォームの背景色を変化
    $('#add-task').focusin(function(e) {
            console.log('フォーカスされました。');
            $('.add-task').toggleClass('isActive');
            $('.icon').toggleClass('isActive');
            console.log(this)

        })
        .focusout(function(e) {
            console.log('フォーカスが外れました。');
            $('.add-task').toggleClass('isActive');
            $('.icon').toggleClass('isActive');
            console.log(this)
        });
});