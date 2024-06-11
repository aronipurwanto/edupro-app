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

    const formSelect2 = $('#main-modal').find('.select2');

    if (formSelect2.length) {
        formSelect2.wrap('<div class="position-relative"></div>');
        formSelect2
            .select2({
                placeholder: 'Pilih Poeple',
                dropdownParent: formSelect2.parent()
            });
    }

    // datatable teachers declaration
    var dt_teacher_table = $("#table-teacher"),
        statusObj = {
            0: {title: "Non Aktif"},
            1: {title: "Aktif"},
        },
        riwayatObj = {
            0: {title: "Non Aktif"},
            1: {title: "Aktif"},
        };

    if (dt_teacher_table.length > 0) {
        var ajaxUrl = $('#teacher-title').attr('href');
        var dt_teacher = dt_teacher_table.DataTable({
            ajax: ajaxUrl,
            columns: [
                {data: 'id'},
                {data: 'nomor'},
                {data: 'nama'},
                {data: 'nik'},
                {data: 'status'},
                {data: ''},
            ],
            columnDefs: [
                {
                    classname: 'control',
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
                        var $item = full['nomor'];
                        return'<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 2,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['nama'];
                        return'<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 3,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['nik'];
                        return'<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 4,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['status'];
                        return'<span>' + $item + '</span>';
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
            lengthMenu: [10, 20, 50, 70, 100]
        });

        dt_teacher.on('order.dt search.dt', function () {
            let i = 1;

            dt_teacher.cells(null, 0, {search: 'applied', order: 'applied'})
                .every(function (cell) {
                    this.data(i++)
                });
        }).draw();
    }

    // datatable student declaration
    var dt_student_table = $("#table-student"),
        statusObj = {
            0: {title: "Non Aktif"},
            1: {title: "Aktif"},
        },
        riwayatObj = {
            0: {title: "Non Aktif"},
            1: {title: "Aktif"},
        };

    if (dt_student_table.length > 0) {
        var ajaxUrl = $('#student-title').attr('href');
        var dt_student = dt_student_table.DataTable({
            ajax: ajaxUrl,
            columns: [
                {data: 'id'},
                {data: 'nama'},
                {data: 'nisn'},
                {data: 'gender'},
                {data: 'status'},
                {data: ''},
            ],
            columnDefs: [
                {
                    classname: 'control',
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
                        var $item = full['nama'];
                        return'<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 2,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['nisn'];
                        return'<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 3,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['gender'];
                        return'<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 4,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['status'];
                        return'<span>' + $item + '</span>';
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
            lengthMenu: [10, 20, 50, 70, 100]
        });

        dt_student.on('order.dt search.dt', function () {
            let i = 1;

            dt_student.cells(null, 0, {search: 'applied', order: 'applied'})
                .every(function (cell) {
                    this.data(i++)
                });
        }).draw();
    }


    $('.dataTables_length').addClass('mt-2 mt-sm-0 mt-md-3 me-2');
    $('.dt-buttons').addClass('d-flex flex-wrap');


    $("#btn-people-teacher").click(function (event){
        event.preventDefault();

        var url = $(this).attr('href');
        showModal(url, ' ');
    });

    // btn add click student
    $("#btn-people-student").click(function (event) {
        event.preventDefault();

        var url = $(this).attr('href');
        showModal(url, ' ');
    });

    // form submit student
    $('#main-modal').on('submit', '#form-people-student', function (e){
        e.preventDefault();
        var ajaxUrl = $(this).attr('action');
        const data = convertFormToJSON($(this));
        ajaxSubmit(ajaxUrl, data, dt_student);
    });

    getActiveMenu();
});