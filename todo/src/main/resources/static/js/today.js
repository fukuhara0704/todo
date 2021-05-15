$(function() {
    $('.task').click(function() {
        console.log('taskクリックされました！');
        $('.localNavigation').toggleClass('isActive');
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

    $('.task-td').click(function() {
        console.log('task-tdクリックされました！');
    })



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