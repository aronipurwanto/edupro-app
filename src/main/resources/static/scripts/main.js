function showModal(url, title){
    $.ajax({
        url: url,
        method: 'GET',
        dataType: 'html',
        success: function (result){
            $('#main-modal').find('.modal-content').html(result);
            var $dialog = $('#main-modal').find('.modal-dialog');
            if(title === 'small'){
                $dialog.addClass('modal-sm');
            }

            if(title === 'large'){
                $dialog.addClass('modal-lg');
            }

            if(title === 'extra-large'){
                $dialog.addClass('modal-xl');
            }

            $('#main-modal').modal('show');
        }
    });
}

function convertFormToJSON(form) {
    return $(form)
        .serializeArray()
        .reduce(function (json, { name, value }) {
            json[name] = value;
            return json;
        }, {});
}

function ajaxSubmit(url, data, dataTable = null){
    var token = $("meta[name='_csrf']").attr("content");
    console.log(data);
    $.ajax({
        url: url,
        type: 'POST',
        data: data,
        dataType: 'html',
        headers: {
            'X-CSRF-TOKEN' : token
        },
        //contentType: 'application/json',
        success: function (result){
            $('#main-modal').find('.modal-content').html(result);

            var error = $('#main-modal').find(".errors").length;
            if(error == 0) {
                $('#main-modal').modal('hide');

                if(dataTable !== null){
                    dataTable.ajax.reload();
                }
            }
        }
    });
}