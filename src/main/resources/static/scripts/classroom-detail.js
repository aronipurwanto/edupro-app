$(document).ready(function () {
    if (isDarkStyle) {
        borderColor = config.colors_dark.borderColor;
        bodyBg = config.colors_dark.bodyBg;
        headingColor = config.colors_dark.headingColor;
    } else {
        borderColor = config.colors.borderColor;
        bodyBg = config.colors.bodyBg;
        headingColor = config.colors.headingColor;
    }

    // btn add click
    $("#btn-topic-add").click(function (event) {
        event.preventDefault();

        var url = $(this).attr('href');
        showModal(url, ' ');
    });

    $("#btn-people-add").click(function (event) {
        event.preventDefault();

        var url = $(this).attr('href');
        showModal(url, 'large');
    });

    $("#btn-page-grade").click(function (event) {
        event.preventDefault();

        var url = $(this).attr('href');
        showModal(url, ' ');
    });

    $("#btn-add-material").click(function (event) {
        event.preventDefault();

        var url = $(this).attr('href');
        showModal(url, 'extra-large');
    });
});