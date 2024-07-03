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

    // data table declaration
    var dt_institution_table = $("#table-institution"),
        statusObj = {
            0: {title: "Non Aktif"},
            1: {title: "AKtif"},
        },
        riwayatObj = {
            0: {title: "Non Aktif"},
            1: {title: "Aktif"},
        };

        // datatable declaration
    if (dt_institution_table.length > 0) {
        var ajaxUrl = $('#institution-title').attr('href');
        var dt_institution = dt_institution_table.DataTable({
            ajax: ajaxUrl,
            columns: [
                { data: 'id'},
                { data: 'name'},
                { data: 'shortName'},
                { data: 'regNumber'},
                { data: 'code'},
                { data: 'expiredDate'},
                { data: 'levelCategory'},
                { data: 'headmaster'},
                { data: 'uniqueNumber'},
                { data: 'adminName'},
                { data: 'maxExamUser'},
                { data: 'maxLmsUser'},
                { data: 'diffServerTime'},
                { data: 'effectiveDays'},
                { data: 'startedDay'},
                { data: 'endDay'},
                { data: 'endEarly'},
                { data: 'endOfDay'},
                { data: 'provinceId'},
                { data: 'cityId'},
                { data: 'districtId'},
                { data: 'subDistrictId'},
                { data: 'address'},
                { data: 'postalCode'},
                { data: 'phoneNumber'},
                { data: 'faxNumber'},
                { data: 'website'},
                { data: 'email'},
                { data: 'letterHead'},
                { data: 'headOfSignature'},
                { data: 'serviceLogo'},
                { data: 'institutionLogo'},
                { data: 'stamp'},
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
                },
                {
                    targets: 1,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['name'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 2,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['shortName'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 3,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['regNumber'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 4,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['code'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 5,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['expiredDate'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 6,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['levelCategory'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 7,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['headmaster'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 8,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['uniqueNumber'];
                        return '<span>' + $item + '</span>';
                    }
                },{
                    targets: 9,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['adminName'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 10,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['maxExamUser'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 11,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['maxLmsUser'];
                        return '<span>' + $item + '</span>';
                    }
                },{
                    targets: 12,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['diffServerTime'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 13,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['effectiveDays'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 14,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['startedDay'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 15,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['endDay'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 16,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['endEarly'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 17,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['endOfDay'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 18,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['provinceId'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 19,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['cityId'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 20,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['districtId'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 21,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['subDistrictId'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 22,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['address'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 23,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['postalCode'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 24,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['phoneNumber'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 25,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['faxNumber'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 26,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['website'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 27,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['email'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 28,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['letterHead'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 29,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['headOfSignature'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 30,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['serviceLogo'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 31,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['institutionLogo'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 32,
                    searchable: true,
                    orderable: true,
                    render: (data, type, full, meta) => {
                        var $item = full['stamp'];
                        return '<span>' + $item + '</span>';
                    }
                },
                {
                    targets: 33,
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

        dt_institution.on('order.dt search.dt', function () {
            let i = 1;

            dt_institution.cells(null, 0, { search: 'applied', order: 'applied' })
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
        showModal(url, 'extra-large');
    });

    // form submit
    $('#main-modal').on('submit', '#form-institution', function (e) {
        e.preventDefault();
        var ajaxUrl = $(this).attr('action');
        const data = convertFormToJSON($(this));
        ajaxSubmit(ajaxUrl, data, dt_institution)
    });
    getActiveMenu();
});