$.extend($.fn.dataTable.defaults, {
    dom: "<'dt-toolbar'<'col-xs-12 col-sm-6'f>>" +
    "tr" +
    "<'dt-toolbar-footer'<'col-sm-2 col-xs-12 hidden-xs'l><'col-sm-4 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
    searching: false,
    processing: true,
    ordering: false,
    destroy: true,
    sScrollX: true,
    pagingType: "full_numbers",
    language: {
        processing: "数据加载中...",
        loadingRecords: "数据加载中...",
        lengthMenu: "每页 _MENU_ 条",
        info: "从 _START_ 到 _END_ /共 _TOTAL_ 条记录",
        zeroRecords: "没有找到记录",
        emptyTable: "无记录",
        infoEmpty: "无记录",
        infoFiltered: "",
        paginate: {
            first: "首页",
            previous: "前一页",
            next: "后一页",
            last: "尾页"
        },
        aria: {
            sortAscending: ": 升序",
            sortDescending: ": 降序"
        }
    }
});