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

function loadPage(url, element){
    $.ajax({
        url: url,
        method: 'GET',
        dataType: 'html',
        success: function (result){
            $(element).html(result);
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

var token = $("meta[name='_csrf']").attr("content");
function ajaxSubmit(url, data, dataTable = null){
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
            var invalid = $('#main-modal').find(".invalid-feedback").length;
            if(error == 0 && invalid == 0) {
                $('#main-modal').modal('hide');

                if(dataTable !== null){
                    dataTable.ajax.reload();
                }
            }
        }
    });
}

function getActiveMenu(){
    var currentUrl = window.location.pathname;

    $('.menu-item .menu-link').each(function (){
        var href = $(this).attr('href');
        if (currentUrl.includes(href)) {
            $(this).closest('.menu-item').addClass('active');
        }
    });
}