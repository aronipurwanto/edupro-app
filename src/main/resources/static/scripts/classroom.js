$(document).ready(function (){
    if (isDarkStyle) {
        borderColor = config.colors_dark.borderColor;
        bodyBg = config.colors_dark.bodyBg;
        headingColor = config.colors_dark.headingColor;
    } else {
        borderColor = config.colors.borderColor;
        bodyBg = config.colors.bodyBg;
        headingColor = config.colors.headingColor;
    }

    // load page
    // first url
    var itemUrl = $('#classroom-item-url').attr('href');
    //first load
    loadPage(itemUrl,'#classroom-content');

    // form submit
    $('#main-modal').on('submit', '#form-topic', function (e) {
        e.preventDefault();
        var ajaxUrl = $(this).attr('action');
        const  data = convertFormToJSON($(this));
        ajaxSubmit(ajaxUrl, data )
    });


    // create
    $('#btn-classroom-new').click(function (event){
        event.preventDefault();
        var ajaxUrl = $(this).attr('href');
        showModal(ajaxUrl);
    });

    $('#main-modal').on('submit', '#form-course', function (e) {
        e.preventDefault();
        var ajaxUrl = $(this).attr('action');
        const  data = convertFormToJSON($(this));
        ajaxSubmit(ajaxUrl, data )
    });

    getActiveMenu();
});