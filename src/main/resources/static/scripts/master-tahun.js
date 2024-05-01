$(function () {
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
    // Select2 (Kurikulum)
    if (formSelect2.length) {
        formSelect2.wrap('<div class="position-relative"></div>');
        formSelect2
            .select2({
                placeholder: 'Pilih Kurikulum',
                dropdownParent: formSelect2.parent()
            });
    }

    // data declaration
    var dt_tahun_table = $("#table-tahun"),
        statusObj = {
            0: {title: "Non Aktif"},
            1: {title: "AKtif"},
        },
        riwayatObj = {
            0: {title: "Non Aktif"},
            1: {title: "Aktif"},
        };

    // datatable declaration
    if (dt_tahun_table.length > 0) {

        var ajaxUrl = $('#ta-title').attr('href');
        var dt_table = dt_tahun_table.DataTable({
            ajax: ajaxUrl,
            columns: [
                { data: 'id'},
                { data: 'nama'},
                { data: 'kodeKurikulum'},
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
                    render: function (data, type, full, meta) {
                        var $item = full['id'];
                        return '<span>'+$item +'</span>';
                    }
                },
                {
                    targets: 1,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['nama'];
                        return '<span>' + $item + '</span>';
                    }
                },{
                    targets: 2,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['kodeKurikulum'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 3,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['status'];
                        return '<span>' + $item + '</span>'
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
                        return(
                            '<div class="d-inline-block text-nowrap">' +
                            '<button class="btn btn-xs btn-primary btn-edit" href="' + editUrl + '"><i class="ti ti-edit"></i> Edit </button> &nbsp;' +
                            '<button class="btn btn-xs btn-danger btn-delete" href="' + deleteUrl + '"><i class="ti ti-trash"></i> </button>' +
                            '</div>'
                        )
                    }
                }
            ],
            lengthMenu: [5, 10, 20, 50, 70, 100]
        });

        dt_table.on('order.dt search.dt', function () {
            let i = 1;

            dt_table.cells(null, 0, { search: 'applied', order: 'applied' })
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
            showModal(url, ' ');
        });

        // form submit
        $('#main-modal').on('submit', '#form-tahun', function (e) {
            e.preventDefault();
            var ajaxUrl = $(this).attr('action');
            const data = convertFormToJSON($(this));
            ajaxSubmit(ajaxUrl, data, dt_table)
        });

        // edit data
        $("#table-tahun").on('click', '.btn-edit', function () {
            var url = $(this).attr('href');
            showModal(url, ' ');
        });

        // delete data
        $("#table-tahun").on('click', '.btn-delete', function () {
            var url = $(this).attr('href');
            showModal(url, ' ');
        });
});