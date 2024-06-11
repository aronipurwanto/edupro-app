$(document).ready(function (){
    if (isDarkStyle){
        borderColor = config.colors_dark.borderColor;
        bodyBg = config.colors_dark.bodyBg;
        headingColor = config.colors_dark.headingColor;
    } else {
        borderColor = config.colors_dark.borderColor;
        bodyBg = config.colors.bodyBg;
        headingColor = config.colors.headingColor;
    }

    const formSelect2 = $('#main-modal').find('.select2');
    // Select (Mapel)
    if (formSelect2.length){
        formSelect2.wrap( '<div class="position-relative"></div>');
        formSelect2
            .select2({
                placeholder : 'Pilih Mapel',
                dropdownParent: formSelect2.parent()
            });
    }

    // datatable declaration
    var dt_course_table = $("#table-course"),
        statusObj = {
            0: {title: "Non Aktif"},
            1: {title: "Aktif"},
        },
        riwayatObj = {
            0: {title: "Non Aktif"},
            1: {title: "Aktif"}
        };

    if (dt_course_table.length > 0) {

        var ajaxUrl = $('#course-title').attr('href');
        var dt_table = dt_course_table.DataTable({
            ajax: ajaxUrl,
            columns : [
                { data: 'id'},
                { data: 'name'},
                { data: 'shortName'},
                { data: 'shown'},
                { data: 'startDate'},
                { data: 'endDate'},
                { data: 'summary'},
                { data: 'imageId'},
                { data: 'format'},
                { data: 'hiddenSection'},
                { data: 'layout'},
                { data: 'completionTracking'},
                { data: 'kodeMapel'},
                { data: 'kodeLevel'},
                { data: 'status'},
                { data: ' '}
            ],
            columnDefs: [
                {
                    className: 'control',
                    searchable: false,
                    orderable: false,
                    responsivePriority: 2,
                    targets: 0,
                    render: function (data, type, full, meta){
                        var $item = full['id'];
                        return '<span>'+ $item + '</span>';
                    }
                },
                {
                    targets: 1,
                    searchable: true,
                    orderable: true,
                    render: function (data, type, full, meta){
                        var $item = full['name'];
                        return '<span>'+ $item + '</span>';
                    }
                },
                {
                    targets: 2,
                    searchable: true,
                    orderable: true,
                    render: function (data, type, full, meta){
                        var $item = full['shortName'];
                        return '<span>'+ $item + '</span>';
                    }
                },
                {
                    targets: 3,
                    searchable: true,
                    orderable: true,
                    render: function (data, type, full, meta){
                        var $item = full['shown'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 4,
                    searchable: true,
                    orderable: true,
                    render: function (data, type, full, meta){
                        var $item = full['startDate'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 5,
                    searchable: true,
                    orderable: true,
                    render: function (data, type, full, meta){
                        var $item = full['endDate'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 6,
                    searchable: true,
                    orderable: true,
                    render: function (data, type, full, meta){
                        var $item = full['summary'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 7,
                    searchable: true,
                    orderable: true,
                    render: function (data, type, full, meta){
                        var $item = full['imageId'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 8,
                    searchable: true,
                    orderable: true,
                    render: function (data, type, full, meta){
                        var $item = full['format'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 9,
                    searchable: true,
                    orderable: true,
                    render: function (data, type, full, meta){
                        var $item = full['hiddenSection'];
                        return '<span>' +$item + '</span>';
                    }
                },
                {
                    targets: 10,
                    searchable: true,
                    orderable: true,
                    render: function (data, type, full, meta){
                        var $item = full['layout'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 11,
                    searchable: true,
                    orderable: true,
                    render: function (data, type, full, meta){
                        var $item = full['completionTracking'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 12,
                    searchable: true,
                    orderable: true,
                    render: function (data, type, full, meta){
                        var $item = full['kodeMapel'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 13,
                    searchable: true,
                    orderable: true,
                    render: function (data, type, full, meta){
                        var $item = full['kodeLevel'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 14,
                    searchable: true,
                    orderable: true,
                    render: function (data, type, full, meta){
                        var $item = full['status'];
                        return '<span>' + $item + '</span>';
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
                        var deleteUrl = ajaxUrl.replace('data','delete') + '/' + id ;
                        return (
                            '<div class="d-inline-block text-nowrap">'+
                            '<button class="btn btn-xs btn-primary btn-edit" href="'+ editUrl + '"><i class="ti ti-edit"></i> Edit </button> &nbsp;' +
                            '<button class="btn btn-xs btn-danger btn-delete" href="'+ deleteUrl + '"><i class="ti ti-trash"></i></button>' +
                            '</div>'
                        );
                    }
                }
            ],
            lengthMenu: [5, 10, 20, 50, 70, 100]
        });

        dt_table.on('order.dt search.dt', function (){
            let i = 1;

            dt_table.cells(null,0, {search: 'applied', order: 'applied'})
                .every(function (cell){
                    this.data(i++);
                });
        }).draw();
    }

    $('.dataTables_length').addClass('mt-2 mt-sm-0 mt-md-3 me-2');
    $('.dt-button').addClass('d-flex flex-wrap');

    // btn add click
    $("#btn-add").click(function (){
        var url = $(this).attr('href');
        showModal(url, ' ');
    })

    // form submit
    $('#main-modal').on('submit', '#form-course', function (e){
        e.preventDefault();
        var ajaxUrl = $(this).attr('action');
        const data = convertFormToJSON($(this));
        ajaxSubmit(ajaxUrl, data, dt_table);
    });

    // edit data
    $("#table-course").on('click', '.btn-edit', function (){
        var url = $(this).attr('href');
        showModal(url, ' ');
    });

    // delete data
    $("#table-course").on('click', '.btn-delete', function (){
        var url = $(this).attr('href');
        showModal(url, ' ');
    });

    getActiveMenu();
})