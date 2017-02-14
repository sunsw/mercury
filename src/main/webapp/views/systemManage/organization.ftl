<!-- widget grid -->
<section id="widget-grid" class="">

    <!-- row -->
    <div class="row">
        <!-- NEW WIDGET START -->
        <article class="col-sm-12 col-md-12 col-lg-4">

            <!-- Widget ID (each widget will need unique ID)-->
            <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false">
                <!-- widget options:
                usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

                data-widget-colorbutton="false"
                data-widget-editbutton="false"
                data-widget-togglebutton="false"
                data-widget-deletebutton="false"
                data-widget-fullscreenbutton="false"
                data-widget-custombutton="false"
                data-widget-collapsed="true"
                data-widget-sortable="false"

                -->
                <header>
                    <span class="widget-icon"> <i class="fa fa-sitemap"></i> </span>
                    <h2>组织列表 </h2>

                </header>

                <!-- widget div-->
                <div>

                    <!-- widget edit box -->
                    <div class="jarviswidget-editbox">
                        <!-- This area used as dropdown edit box -->

                    </div>
                    <!-- end widget edit box -->

                    <!-- widget content -->
                    <div class="widget-body">

                        <div id="tree" class="smart-form">
                        </div>

                    </div>
                    <!-- end widget content -->

                </div>
                <!-- end widget div -->

            </div>
            <!-- end widget -->

        </article>
        <!-- WIDGET END -->

        <!-- NEW WIDGET START -->
        <article class="col-sm-12 col-md-12 col-lg-8">

            <!-- Widget ID (each widget will need unique ID)-->
            <div class="jarviswidget jarviswidget-color-darken" id="wid-id-1" data-widget-editbutton="false">
                <!-- widget options:
                usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

                data-widget-colorbutton="false"
                data-widget-editbutton="false"
                data-widget-togglebutton="false"
                data-widget-deletebutton="false"
                data-widget-fullscreenbutton="false"
                data-widget-custombutton="false"
                data-widget-collapsed="true"
                data-widget-sortable="false"

                -->
                <header>
                    <span class="widget-icon"> <i class="fa fa-file-text"></i> </span>
                    <h2>组织详情 </h2>

                </header>

                <!-- widget div-->
                <div>

                    <!-- widget edit box -->
                    <div class="jarviswidget-editbox">
                        <!-- This area used as dropdown edit box -->

                    </div>
                    <!-- end widget edit box -->

                    <form id="queryForm" action="" class="form-horizontal">
                        <input type="hidden" name="parentId" id="currentId" value="0">
                        <fieldset>
                            <div class="form-group pull-right">
                                <div class="col-md-12">
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

                        <form id="infoForm" class="smart-form">
                            <header>当前组织</header>

                            <fieldset>
                                <div class="row">
                                    <section class="col col-6">
                                        <label class="label">组织名称</label>
                                        <label class="input">
                                            <input type="text" name="orgName" id="orgName" readonly>
                                        </label>
                                    </section>
                                    <section class="col col-6">
                                        <label class="label">组织编码</label>
                                        <label class="input">
                                            <input type="text" name="orgCode" id="orgCode" readonly>
                                        </label>
                                    </section>
                                    <section class="col col-6">
                                        <label class="label">组织级别</label>
                                        <label class="input">
                                            <input type="text" name="level" id="level" readonly>
                                        </label>
                                    </section>
                                    <section class="col col-6">
                                        <label class="label">序号</label>
                                        <label class="input">
                                            <input type="text" name="sort" id="sort" readonly>
                                        </label>
                                    </section>
                                    <section class="col col-6">
                                        <label class="label">状态</label>
                                        <label class="select">
                                            <select id="status0" name="status" disabled>
                                            </select>
                                            <i></i>
                                        </label>
                                    </section>
                                </div>
                                <section>
                                    <label class="label">描述</label>
                                    <label class="textarea">
                                        <textarea rows="2" name="description" id="description" readonly></textarea>
                                    </label>
                                </section>
                            </fieldset>
                            <header>子组织</header>
                        </form>

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
                    <input name="parentId" id="parentId" type="hidden">
                    <fieldset>
                        <div class="form-group">
                            <label class="control-label col-md-2">组织名称</label>
                            <div class="col-md-4">
                                <input name="orgName" class="form-control" type="text">
                            </div>

                            <label class="control-label col-md-2">组织编码</label>
                            <div class="col-md-4">
                                <input name="orgCode" class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2">组织级别</label>
                            <div class="col-md-4">
                                <input name="level" class="form-control" type="text">
                            </div>

                            <label class="control-label col-md-2">序号</label>
                            <div class="col-md-4">
                                <input name="sort" class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2">状态</label>
                            <div class="col-md-4">
                                <select id="status" name="status" class="form-control">
                                </select>
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
<script src="/app/js/mercury-tree.js"/>

<script type="text/javascript">
    pageSetUp();

    var table, orgStatus;

    $(function () {
        $("#modelForm").keydown(function (e) {
            var e = e || event,
                    keycode = e.which || e.keyCode;
            if (keycode == 13) {
                saveData();
                e.preventDefault();
            }
        });

        if (!window.sessionStorage.getItem("orgStatus")) {
            $.ajax({
                url: "/item/orgStatus",
                dataType: "json",
                async: false,
                success: function (data) {
                    if (data.code == "100000") {
                        window.sessionStorage.setItem("orgStatus", JSON.stringify(data.data));
                        orgStatus = data.data;
                    }
                }
            });
        }
        if (!orgStatus) {
            orgStatus = JSON.parse(window.sessionStorage.getItem("orgStatus"));
        }
        $.each(orgStatus, function (key, value) {
            $("#status0").append("<option value='" + key + "'>" + value + "</option>");
            $("#status").append("<option value='" + key + "'>" + value + "</option>");
        });

        initTree();
        loadInfo();
        initDataTabel();

        $('#modelForm').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            excluded: [':disabled'],
            fields: {
                orgName: {
                    group: '.col-md-4',
                    validators: {
                        notEmpty: {
                            message: '组织名称不能为空'
                        }
                    }
                },
                orgCode: {
                    group: '.col-md-4',
                    validators: {
                        notEmpty: {
                            message: '组织编码不能为空'
                        }
                    }
                },
                sort: {
                    group: '.col-md-4',
                    validators: {
                        notEmpty: {
                            message: '序号不能为空'
                        },
                        digits: {
                            message: '请输入有效的序号'
                        }
                    }
                },
                status: {
                    group: '.col-md-4',
                    validators: {
                        notEmpty: {
                            message: '状态不能为空'
                        }
                    }
                }
            }
        });

    });

    function initTree() {
        $.get({
            url: "/organization",
            dataType: "json",
            success: function (data) {
                if (data.code == "100000") {
                    $("#tree").tree().init(data.data, "subOrganizations", "orgName");
                    var item = $('#tree').find("span.tree-item");
                    item.on("click", function (a) {
                        var id = $(this).attr("data-id");
                        clickTree(id);
                        a.stopPropagation();
                    });
                }
            }
        });
    }

    function clickTree(treeId) {
        $("#currentId").val(treeId);
        if (!treeId) {
            $.smallBox({
                title: "提示信息",
                content: "<i class='fa fa-info-circle'></i> <i>请选择一条记录...</i>",
                iconSmall: "fa fa-warning fa-2x fadeInRight animated",
                timeout: 4000
            });
        } else {
            loadInfo();
            searchData();
        }
    }

    function loadInfo() {
        var treeId = $("#currentId").val();
        $("#infoForm").clear();
        $.ajax({
            url: "/organization/" + treeId,
            dataType: "json",
            success: function (data) {
                if (data.code == "100000") {
                    $("#infoForm").load(data.data);
                }
            }
        });
    }

    function initDataTabel() {
        table = $('#dtGrid').DataTable({
            serverSide: true,
            ajax: {
                url: "/organization/sub_organizations",
                data: $("#queryForm").serializeJson()
            },
            columns: [
                {
                    data: "orgName",
                    title: "组织名称"
                },
                {
                    data: "orgCode",
                    title: "组织链接",
                    defaultContent: ""
                },
                {
                    data: "level",
                    title: "组织级别",
                    defaultContent: ""
                },
                {
                    data: "sort",
                    title: "序号",
                    defaultContent: ""
                },
                {
                    data: "status",
                    title: "状态",
                    render: function (data, type, row, meta) {
                        if (data) {
                            if (orgStatus[data]) {
                                return orgStatus[data];
                            } else {
                                return data;
                            }
                        }
                        return "";
                    }
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
        $("#parentId").val($("#currentId").val());
        $('#myModal').modal('show');
    }

    function saveData() {
        var validator = $('#modelForm').data("bootstrapValidator");
        validator.validate();
        if (!validator.isValid()) {
            return;
        }
        $.post({
            url: "/organization/save",
            data: $("#modelForm").serializeJson(),
            dataType: "json",
            success: function (data) {
                if (data.code == "100000") {
                    $.smallBox({
                        title: "提示信息",
                        content: "<i class='fa fa-info-circle'></i> <i>操作成功...</i>",
                        color: "#659265",
                        iconSmall: "fa fa-check fa-2x fadeInRight animated",
                        timeout: 2000
                    });
                    initTree();
                    searchData();
                    window.sessionStorage.removeItem("organization");
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
                $("#myModal").modal("hide");
            }
        });
    }

    function editData() {
        var treeId = $("#currentId").val();
        if (!treeId) {
            $.smallBox({
                title: "提示信息",
                content: "<i class='fa fa-info-circle'></i> <i>请选择一条记录...</i>",
                iconSmall: "fa fa-warning fa-2x fadeInRight animated",
                timeout: 4000
            });
        } else {
            $("#modelForm").clear();
            $.ajax({
                url: "/organization/" + treeId,
                dataType: "json",
                success: function (data) {
                    if (data.code == "100000") {
                        $("#modelForm").clear();
                        $('#modelForm').data("bootstrapValidator").resetForm();
                        $("#myModal").modal("show");
                        $("#modelForm").load(data.data);
                    }
                }
            });
        }
    }

    function delData() {
        var treeId = $("#currentId").val();
        if (!treeId) {
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
                        url: "/organization/del",
                        data: {id: treeId},
                        dataType: "json",
                        success: function (data) {
                            if (data.code == "100000") {
                                $.smallBox({
                                    title: "提示信息",
                                    content: "<i class='fa fa-info-circle'></i> <i>操作成功...</i>",
                                    color: "#659265",
                                    iconSmall: "fa fa-check fa-2x fadeInRight animated",
                                    timeout: 2000
                                });
                                initTree();
                                loadInfo();
                                searchData();
                                window.sessionStorage.removeItem("organization");
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
