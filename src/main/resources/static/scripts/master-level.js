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

    const formSelect2 = $('#main-modal').find('.select2');
    // Select2 (Country)
    if (formSelect2.length) {
        formSelect2.wrap('<div class="position-relative"></div>');
        formSelect2
            .select2({
                placeholder: 'Pilih Lembaga',
                dropdownParent: formSelect2.parent()
            });
    }

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

    if (dt_level_table.length > 0) {
        var ajaxUrl = $('#level-title').attr('href');
        var dt_level = dt_level_table.DataTable({
            ajax: ajaxUrl,
            columns: [
                {data: 'id'},
                {data: 'namaLembaga'},
                {data: 'kode'},
                {data: 'nama'},
                {data: 'noUrut'},
                {data: 'status'},
                {data: ' '}
            ],
            columnDefs: [
                {
                    className: 'control',
                    searchable: false,
                    orderable: false,
                    responsivePriority: 2,
                    targets: 0,
                },
                {
                    targets: 1,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['namaLembaga'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 2,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['kode'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 3,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['nama'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 4,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['noUrut'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 5,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
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
                        var editUrl = ajaxUrl.replace('data', 'edit') + '/' + id;
                        var deleteUrl = ajaxUrl.replace('data', 'delete') + '/' + id;
                        return (
                            '<div class="d-inline-block text-nowrap">' +
                            '<button class="btn btn-xs btn-primary btn-edit" href="' + editUrl + '"><i class="ti ti-edit"></i> Edit</button> &nbsp;' +
                            '<button class="btn btn-xs btn-danger btn-delete" href="' + deleteUrl + '"><i class="ti ti-trash"></i></button>' +
                            '</div>'
                        );
                    }
                }
            ],
            lengthMenu: [5, 10, 20, 50, 70, 100]
        });

        dt_level.on('order.dt search.dt', function () {
            let i = 1;

            dt_level.cells(null, 0, {search: 'applied', order: 'applied'})
                .every(function (cell) {
                    this.data(i++);
                });
        }).draw();

    }


    $('.dataTables_length').addClass('mt-2 mt-sm-0 mt-md-3 me-2');
    $('.dt-buttons').addClass('d-flex flex-wrap');

    // btn add click
    $("#btn-add").click(function () {
        var url = $(this).attr('href');
        showModal(url, '');
    });

    // from submit
    $('#main-modal').on('submit', '#form-level', function (e) {
        e.preventDefault();
        var ajaxUrl = $(this).attr('action');
        const data = convertFormToJSON($(this));
        ajaxSubmit(ajaxUrl, data, dt_level)
    });

    // edit data
    $("#table-level").on('click', '.btn-edit', function (){
        var url = $(this).attr('href');
        showModal(url, '')
    })

    // delete data
    $("#table-level").on('click', '.btn-delete', function () {
        var url = $(this).attr('href');
        showModal(url, ' ');
    });

    getActiveMenu();
});

