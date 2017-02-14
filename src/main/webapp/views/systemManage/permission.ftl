<!-- widget grid -->
<section id="widget-grid" class="">

    <!-- row -->
    <div class="row">
        <!-- NEW WIDGET START -->
        <article class="col-sm-12 col-md-12 col-lg-12">

            <!-- Widget ID (each widget will need unique ID)-->
            <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false">
                <header>
                    <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                    <h2>权限列表 </h2>
                </header>

                <!-- widget div-->
                <div>
                    <div class="jarviswidget-editbox">
                        <!-- This area used as dropdown edit box -->
                    </div>
                    <!-- end widget edit box -->

                    <form id="queryForm" action="" class="form-horizontal">
                        <fieldset>
                            <div class="form-group">
                                <label class="control-label col-md-1">权限名称</label>
                                <div class="col-md-3">
                                    <input name="permissionName" class="form-control" type="text">
                                </div>

                                <label class="control-label col-md-1">权限标识</label>
                                <div class="col-md-3">
                                    <input name="permissionSign" class="form-control" type="text">
                                </div>
                            </div>

                            <div class="form-group pull-right">
                                <div class="col-md-12">
                                    <a href="javascript:searchData();" class="btn btn-primary">
                                        <i class="fa fa-search"></i> 查询</a>
                                    <a href="javascript:openModel();" class="btn btn-success">
                                        <i class="fa fa-plus-circle"></i> 新增</a>
                                    <a href="javascript:editData();" class="btn btn-warning">
                                        <i class="fa fa-edit"></i> 修改</a>
                                    <a href="javascript:delData();" class="btn btn-danger">
                                        <i class="fa fa-minus-circle"></i> 删除</a>
                                </div>
                            </div>
                        </fieldset>
                    </form>

                    <!-- widget content -->
                    <div class="widget-body no-padding">

                        <table id="dtGrid" class="table table-striped table-bordered table-hover" width="100%">
                        </table>

                    </div>
                    <!-- end widget content -->

                </div>
                <!-- end widget div -->

            </div>
            <!-- end widget -->

        </article>
        <!-- WIDGET END -->

    </div>

    <!-- end row -->

</section>
<!-- end widget grid -->

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    编辑信息
                </h4>
            </div>
            <div class="modal-body">
                <form id="modelForm" class="form-horizontal">
                    <input name="id" type="hidden">
                    <fieldset>
                        <div class="form-group">
                            <label class="control-label col-md-2">权限名称</label>
                            <div class="col-md-4">
                                <input name="permissionName" class="form-control" type="text">
                            </div>

                            <label class="control-label col-md-2">权限标识</label>
                            <div class="col-md-4">
                                <input name="permissionSign" class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2">序号</label>
                            <div class="col-md-4">
                                <input name="sort" class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2">描述</label>
                            <div class="col-md-10">
                                <textarea rows="2" name="description" style="width: 100%;"></textarea>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
            <div class="modal-footer">
                <a href="javascript:saveData();" class="btn btn-primary">
                    <i class="fa fa-save"></i> 保存</a>
                <a href="javascript:void(0);" class="btn btn-danger" data-dismiss="modal">
                    <i class="fa fa-remove"></i> 取消</a>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<@lib.javascriptDatatable/>
<script type="text/javascript">
    pageSetUp();
    var table;

    $(function () {
        $("#queryForm").keydown(function (e) {
            var e = e || event,
                    keycode = e.which || e.keyCode;
            if (keycode == 13) {
                searchData();
                e.preventDefault();
            }
        });

        $("#modelForm").keydown(function (e) {
            var e = e || event,
                    keycode = e.which || e.keyCode;
            if (keycode == 13) {
                saveData();
                e.preventDefault();
            }
        });

        initDataTabel();

        $('#modelForm').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            excluded: [':disabled'],
            fields: {
                permissionName: {
                    group: '.col-md-4',
                    validators: {
                        notEmpty: {
                            message: '权限名称不能为空'
                        }
                    }
                },
                permissionSign: {
                    group: '.col-md-4',
                    validators: {
                        notEmpty: {
                            message: '权限标识不能为空'
                        },

                    }
                },
                sort: {
                    group: '.col-md-4',
                    validators: {
                        digits: {
                            message: '请输入有效的序号'
                        }
                    }
                }
            }
        });

    });

    function initDataTabel() {
        table = $('#dtGrid').DataTable({
            serverSide: true,
            ajax: {
                url: "/permission",
                data: $("#queryForm").serializeJson()
            },
            columns: [
                {
                    data: null,
                    title: "选择",
                    class: "text-center",
                    render: function (data, type, row, meta) {
                        return "<input type='checkbox' value='" + row.id + "'>";
                    }
                },
                {
                    data: "permissionName",
                    title: "权限名称"
                },
                {
                    data: "permissionSign",
                    title: "权限标识"
                },
                {
                    data: "sort",
                    title: "序号",
                    defaultContent: ""
                },
                {
                    data: "description",
                    title: "描述",
                    defaultContent: ""
                }
            ]
        });
    }

    function searchData() {
        table.settings()[0].ajax.data = $("#queryForm").serializeJson();
        table.ajax.reload();
    }

    function openModel() {
        $("#modelForm").clear();
        $('#modelForm').data("bootstrapValidator").resetForm();
        $('#myModal').modal('show');
    }

    function saveData() {
        var validator = $('#modelForm').data("bootstrapValidator");
        validator.validate();
        if (!validator.isValid()) {
            return;
        }
        $.post({
            url: "/permission/save",
            data: $("#modelForm").serializeJson(),
            dataType: "json",
            success: function (data) {
                if (data.code == "100000") {
                    $.smallBox({
                        title: "提示信息",
                        content: "<i class='fa fa-info-circle'></i> <i>操作成功...</i>",
                        color: "#659265",
                        iconSmall: "fa fa-check fa-2x fadeInRight animated",
                        timeout: 4000
                    });
                    searchData();
                } else {
                    $.smallBox({
                        title: "提示信息",
                        content: "<i class='fa fa-clock-o'></i> <i>操作失败...【" + data.message + "】</i>",
                        color: "#C46A69",
                        iconSmall: "fa fa-times fa-2x fadeInRight animated",
                        timeout: 4000
                    });
                }
                $("#modelForm").clear();
                validator.resetForm();
                $('#myModal').modal('hide');
            }
        });
    }

    function editData() {
        var selected = $("#dtGrid").find(":checked");
        if (selected.length != 1) {
            $.smallBox({
                title: "提示信息",
                content: "<i class='fa fa-info-circle'></i> <i>请选择一条记录...</i>",
                iconSmall: "fa fa-warning fa-2x fadeInRight animated",
                timeout: 4000
            });
        } else {
            $("#modelForm").clear();
            $.ajax({
                url: "/permission/" + selected.val(),
                dataType: "json",
                success: function (data) {
                    if (data.code == "100000") {
                        $('#modelForm').data("bootstrapValidator").resetForm();
                        $("#myModal").modal("show");
                        $("#modelForm").load(data.data);
                    }
                }
            });
        }
    }

    function delData() {
        var arr = [];
        var selected = $("#dtGrid").find(":checked").each(function () {
            arr.push($(this).val());
        });
        var ids = arr.join(",");
        if (ids == "") {
            $.smallBox({
                title: "提示信息",
                content: "<i class='fa fa-info-circle'></i> <i>请至少选择一条记录...</i>",
                iconSmall: "fa fa-warning fa-2x fadeInRight animated",
                timeout: 4000
            });
        } else {
            $.SmartMessageBox({
                title: "警告信息",
                content: "确定删除选择的记录？",
                buttons: '[取消][确定]'
            }, function (ButtonPressed) {
                if (ButtonPressed === "确定") {
                    $.post({
                        url: "/permission/del",
                        data: {ids: ids},
                        dataType: "json",
                        success: function (data) {
                            if (data.code == "100000") {
                                $.smallBox({
                                    title: "提示信息",
                                    content: "<i class='fa fa-info-circle'></i> <i>操作成功...</i>",
                                    color: "#659265",
                                    iconSmall: "fa fa-check fa-2x fadeInRight animated",
                                    timeout: 4000
                                });
                                searchData();
                            } else {
                                $.smallBox({
                                    title: "提示信息",
                                    content: "<i class='fa fa-clock-o'></i> <i>操作失败...【" + data.message + "】</i>",
                                    color: "#C46A69",
                                    iconSmall: "fa fa-times fa-2x fadeInRight animated",
                                    timeout: 4000
                                });
                            }
                        }
                    });
                }
            });
        }
    }

</script>
