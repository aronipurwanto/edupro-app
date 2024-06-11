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
    $("#btn-add").click(function () {
        var url = $(this).attr('href');
        showModal(url, ' ')
    });

    // form submit
    $('#main-modal').on('submit', '#form-level', function () {
        var ajaxUrl = $(this).attr('action');
        $.ajax({
            url: ajaxUrl,
            method: 'POST',
            dataType: 'JSON',
            success: function () {
                $('#main-modal').modal('show');
            }
        });
    });

    // edit data
    $("#table-level").on('click','.btn-edit', function () {
        var url = $(this).attr('href');
        showModal(url, ' ');
    });

    // delete data
    $("#table-level").on('click','.btn-delete', function () {
        var url = $(this).attr('href');
        showModal(url, ' ')
    });

    // datatable declaration
    var dt_level_table = $("#table-level"),
        statusObj = {
            0: {title: "Non Aktif"},
            1: {title: "Aktif"},
        },
        riwayatObj = {
            0: {title: "Non Aktif"},
            1: {title: "Aktif"},
        };

    if (dt_level_table.length) {
        var ajaxUrl = $('#level-title').attr('href');
        var dt_level = dt_level_table.DataTable({
            ajax: ajaxUrl,
            columns: [
                { data: 'id'},
                { data: 'bulan'},
                { data: 'tp'},
                { data: 'detail'},
                {data: ' '}
            ],
            columnDefs: [
                {
                    className: 'control',
                    searchable: false,
                    orderable: false,
                    responsivePriority: 2,
                    targets: 0,
                    render: function (data, type, full, meta) {
                        var $item = full['id'];
                        return '<span>'+ $item + '</span>';
                    }
                },
                {
                    targets: 1,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['bulan'];
                        return '<span>'+ $item +'</span>';
                    }
                },
                {
                    targets: 2,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['tp'];
                        return '<span>'+ $item +'</span>';
                    }
                },
                {
                    targets: 3,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['detail'];
                        return'<span>'+ $item +'</span>';
                    }
                },
                {
                    targets: -1,
                    title: 'Actions',
                    searchable: false,
                    orderable: false,
                    render: function (data, type, full, meta) {
                        var id = full['id'];
                        var editUrl = ajaxUrl.replace('data','edit') + '/' + id;
                        var deleteUrl = ajaxUrl.replace('data','delete') + '/' + id;
                        return (
                                '<div class="d-inline-block text-nowrap">'+
                                '<button class="btn btn-xs btn-primary btn-edit" href="'+ editUrl +'"><i class="ti ti-edit"></i>Edit</button> &nbsp;' +
                                '<button class="btn btn-xs btn-danger btn-delete" href="'+ deleteUrl +'"><i class="ti ti-trash"></i></button>' +
                                '</div>'
                        )
                    }
                }
            ],
            lengthMenu: [5, 10, 20, 50, 70, 100]
        });

        $('.dataTables_length').addClass('mt-2 mt-sm-0 mt-md-3 me-2');
        $('.dt-buttons').addClass('d-flex flex-wrap');
    }

    getActiveMenu();
});