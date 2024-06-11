$(document).ready(function (){
    // first url
    var defaultUrl = $('.dashboard-menu:eq(0)').attr('href');
    //first load
    loadPage(defaultUrl,'#dashboard-content');

    $(".dashboard-menu").click(function (e){
        e.preventDefault()
        var url = $(this).attr('href');
        loadPage(url, '#dashboard-content');
    });

    getActiveMenu();
});