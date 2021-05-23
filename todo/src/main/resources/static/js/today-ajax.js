$(document).ready(function() {


    /////////////////////////////////////////////
    //                      取得系                       //
    /////////////////////////////////////////////
    // 各タスクの詳細取得Ajax
    $('.task').click(function() {
        console.log('taskクリックされました！');
        var taskId = this.getAttribute("id");
        var jsonString = {
            task_id: taskId
        }
        var formData = JSON.stringify(jsonString);
        $.ajax({
            type: 'POST',
            url: '/getAjaxTask',
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
            console.log(data[0].task_id);
            console.log(data[0].task_name);
            console.log(data[0].task_end_datetime);
            console.log(data[0].task_today_flag);
            console.log(data[0].task_memo);
            console.log(data[0].task_created_time);

            $('textarea[name="side-task"]').val(data[0].task_name);
            $('#hiddenTaskData').val(data[0].task_name);
            $('#hiddenTaskId').val(data[0].task_id);
            $('#hiddenDelTaskId').val(data[0].task_id);

            if (data[0].task_end_datetime === undefined) {
                $('#limitData').text("期限日の追加");
            } else {
                $('#limitData').text("期限 : " + data[0].task_end_datetime);
            }

            if (data[0].task_today_flag == 0) {
                $('#todayFlagData').text("今日の予定に追加");
            } else {
                $('#todayFlagData').text("今日の予定に追加されました");
            }

            $('textarea[name="memo"]').val(data[0].task_memo);
            $('#hiddenMemoData').val(data[0].task_memo);

            $('#createdTime').text(data[0].task_created_time + " に作成済み");
        }).fail(function(data) {
            //返らなかったときの処理
            console.log("error");
        });
    })

    /////////////////////////////////////////////
    //                      更新系                       //
    /////////////////////////////////////////////

    // タスク内容更新
    $('#taskData').focusout(function(e) {
        console.log("タスク内容のフォーカス外れました。");
        console.log($('textarea[name="side-task"]').val());
        var beforeTaskData = $('#hiddenTaskData').val();
        var afterTaskData = $('textarea[name="side-task"]').val();

        if (beforeTaskData != afterTaskData) {
            console.log("値が変更された場合");

            var taskId = $('#hiddenTaskId').val();
            var jsonString = {
                task_id: taskId,
                task_name: afterTaskData
            }
            var formData = JSON.stringify(jsonString);
            console.log(formData);
            $.ajax({
                type: 'POST',
                url: '/updateAjaxTask',
                data: formData,
                contentType: 'application/json',
                datatype: 'json',
                scriptCharset: 'utf-8'
            }).done(function(data) {
                console.log(data);
                $('#task_' + taskId).text("");
                $('#task_' + taskId).text(afterTaskData);
            }).fail(function(data) {
                //返らなかったときの処理
                console.log("error");
            });
        }
    })

    // メモ更新
    $('#memoData').focusout(function(e) {
        console.log("メモのフォーカス外れました。");
        console.log($('textarea[name="memo"]').val());
        var beforeMemoData = $('#hiddenMemoData').val();
        var afterMemoData = $('textarea[name="memo"]').val();

        if (beforeMemoData != afterMemoData) {
            console.log("メモの値が変更された場合");

            var taskId = $('#hiddenTaskId').val();
            var jsonString = {
                task_id: taskId,
                task_memo: afterMemoData
            }
            var formData = JSON.stringify(jsonString);
            console.log(formData);
            $.ajax({
                type: 'POST',
                url: '/updateAjaxMemo',
                data: formData,
                contentType: 'application/json',
                datatype: 'json',
                scriptCharset: 'utf-8'
            }).done(function(data) {
                console.log(data);
            }).fail(function(data) {
                //返らなかったときの処理
                console.log("error");
            });
        }
    })




});