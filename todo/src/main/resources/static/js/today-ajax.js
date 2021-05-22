$(document).ready(function() {
    $('.task').click(function() {
        console.log('taskクリックされました！');
        var taskId = this.getAttribute("id");
        var jsonString = {
            task_id: taskId,
        }
        var formData = JSON.stringify(jsonString);
        $.ajax({
            type: 'POST',
            url: '/ajax',
            data: JSON.stringify(jsonString),
            // data: "1",
            contentType: 'application/json',
            datatype: 'json',
            scriptCharset: 'utf-8'
        }).done(function(data) {
            //返ってきたときの処理
            console.log(data);
            $('.localNavigation').toggleClass('isActive');
            console.log(data[0]);
            console.log(data[0].task_name);
            console.log(data[0].task_end_datetime);
            console.log(data[0].task_today_flag);
            console.log(data[0].task_memo);

            $('textarea[name="side-task"]').val(data[0].task_name);

            if (data[0].task_end_datetime === undefined) {
                $('#limitData').text("期限日の追加");
            } else {
                $('#limitData').text(data[0].task_end_datetime);
            }
            $('#tadayFlagData').text(data[0].task_end_datetime);
            if (data[0].task_today_flag == 0) {
                $('#tadayFlagData').text("今日の予定に追加");
            } else {
                $('#tadayFlagData').text("今日の予定に追加されました");
            }
            $('textarea[name="memo"]').val(data[0].task_memo);





        }).fail(function(data) {
            //返らなかったときの処理
            console.log("error");
        });
    })
});