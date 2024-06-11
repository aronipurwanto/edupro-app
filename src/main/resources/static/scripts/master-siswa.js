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

    // datatable declaration
    var dt_siswa_table = $("#table-siswa"),
        statusObj = {
            0: {title: "Non Aktif"},
            1: {title: "Aktif"},
        },
        riwayatObj = {
            0: {title: "Non Aktif"},
            1: {title: "Aktif"},
        };

    if(dt_siswa_table.length > 0) {
        var ajaxUrl = $('#siswa-title').attr('href');
        var dt_siswa = dt_siswa_table.DataTable({
            ajax: ajaxUrl,
            columns: [
                { data: 'id' },
                { data: 'nisn' },
                { data: 'nama' },
                { data: 'kotaTempatLahir' },
                { data: 'tanggalLahir' },
                { data: 'gender' },
                { data: 'agama' },
                { data: 'golDarah' },
                { data: 'noTelp' },
                { data: 'email' },
                { data: 'status' },
                { data: '' }
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
                        var $item = full['nisn'];
                        return '<span>'+$item +'</span>';
                    }
                },
                {
                    targets: 2,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['nama'];
                        return '<span>'+$item +'</span>';
                    }
                },
                {
                    targets: 3,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['kotaTempatLahir'];
                        return '<span>'+$item +'</span>';
                    }
                },
                {
                    targets: 4,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['tanggalLahir'];
                        return '<span>'+$item +'</span>';
                    }
                },{
                    targets: 5,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['gender'];
                        return '<span>'+$item +'</span>';
                    }
                },{
                    targets: 6,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['agama'];
                        return '<span>'+$item +'</span>';
                    }
                },{
                    targets: 7,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['golDarah'];
                        return '<span>'+$item +'</span>';
                    }
                },{
                    targets: 8,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['noTelp'];
                        return '<span>'+$item +'</span>';
                    }
                },{
                    targets: 9,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['email'];
                        return '<span>'+$item +'</span>';
                    }
                },{
                    targets: 10,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['status'];
                        return '<span>'+$item +'</span>';
                    }
                },
                {
                    targets: -1,
                    title: 'Actions',
                    searchable: false,
                    orderable: false,
                    render: function (data, type, full, meta) {
                        var id = full['id'];
                        var editUrl = ajaxUrl.replace('data','edit') +'/'+ id;
                        var deleteUrl = ajaxUrl.replace('data','delete')+'/'+ id;
                        return (
                            '<div class="d-inline-block text-nowrap">' +
                            '<button class="btn btn-xs btn-primary btn-edit" href="'+ editUrl +'"><i class="ti ti-edit"></i> Edit</button> &nbsp;' +
                            '<button class="btn btn-xs btn-danger btn-delete" href="'+ deleteUrl +'"><i class="ti ti-trash"></i></button>' +
                            '</div>'
                        );
                    }
                }
            ],
            lengthMenu: [5, 10, 20, 50, 70, 100]
        });

        dt_siswa.on('order.dt search.dt', function (){
            let i = 1;

            dt_siswa.cells(null, 0, {search: 'applied', order: 'applied'})
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
    });

    // form submit
    $('#main-modal').on('submit', '#form-siswa', function (e){
        e.preventDefault();
        var ajaxUrl = $(this).attr('action');
        const data = convertFormToJSON($(this));
        ajaxSubmit(ajaxUrl, data, dt_siswa);
    });

    // edit data
    $("#table-siswa").on('click', '.btn-edit', function (){
        var url = $(this).attr('href');
        showModal(url, ' ');
    });

    // delete data
    $("#table-siswa").on('click', '.btn-delete', function (){
        var url = $(this).attr('href');
        showModal(url, ' ');
    });

    getActiveMenu();
});
