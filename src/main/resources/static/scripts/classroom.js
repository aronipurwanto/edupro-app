$(function (){
    if (isDarkStyle) {
        borderColor = config.colors_dark.borderColor;
        bodyBg = config.colors_dark.bodyBg;
        headingColor = config.colors_dark.headingColor;
    } else {
        borderColor = config.colors.borderColor;
        bodyBg = config.colors.bodyBg;
        headingColor = config.colors.headingColor;
    }

    // first url
    var itemUrl = $('#classroom-item-url').attr('href');
    //first load
    loadPage(itemUrl,'#classroom-content');

    // btn add click
    $("#btn-add").click(function () {
        var url = $(this).attr('href');
        showModal(url, ' ');
    });

    // form submit
    $('#main-modal').on('submit', '#form-topic', function (e) {
        e.preventDefault();
        var ajaxUrl = $(this).attr('action');
        const  data = convertFormToJSON($(this));
        ajaxSubmit(ajaxUrl, data )
    });
});