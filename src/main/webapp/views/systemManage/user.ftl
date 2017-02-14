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
                    <h2>用户列表 </h2>
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
                                <label class="control-label col-md-1">用户名</label>
                                <div class="col-md-3">
                                    <input name="username" class="form-control" type="text">
                                </div>

                                <label class="control-label col-md-1">电话</label>
                                <div class="col-md-3">
                                    <input name="phone" class="form-control" type="text">
                                </div>

                                <label class="control-label col-md-1">状态</label>
                                <div class="col-md-3">
                                    <select id="status" name="status" class="form-control">
                                        <option value="">全部</option>
                                    </select>
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
                                    <a href="javascript:editRole();" class="btn btn-info">
                                        <i class="fa fa-group"></i> 角色分配</a>
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
                            <label class="control-label col-md-2">用户名</label>
                            <div class="col-md-4">
                                <input name="username" class="form-control" type="text">
                            </div>

                            <label class="control-label col-md-2">电话</label>
                            <div class="col-md-4">
                                <input name="phone" class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2">组织</label>
                            <div class="col-md-4">
                                <select id="organizationId" name="organizationId" class="form-control">
                                    <option value="">无</option>
                                </select>
                            </div>

                            <label class="control-label col-md-2">邮箱</label>
                            <div class="col-md-4">
                                <input name="email" class="form-control" type="text">
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

<div class="modal fade" id="roleModal" tabindex="-1" role="dialog" aria-labelledby="roleModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="roleModalLabel">
                    角色分配
                </h4>
            </div>
            <div class="modal-body" style="padding-top: 0;">
                <div class="row">
                    <form class="smart-form">
                        <input type="hidden" id="currentId">
                        <header id="currentTitle"></header>
                    </form>
                    <div class="col-sm-6 col-lg-6">
                        <h6>已分配</h6>
                        <div id="sign">
                        </div>
                    </div>

                    <div class="col-sm-6 col-lg-6">
                        <h6>未分配</h6>
                        <div id="unsign">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a href="javascript:void(0);" class="btn btn-primary" data-dismiss="modal">
                    <i class="fa fa-save"></i> 完成</a>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<@lib.javascriptDatatable/>
<script src="/app/js/mercury-Nestable.js"/>
<script src="/assets/js/plugin/jquery-nestable/jquery.nestable.min.js"/>

<script type="text/javascript">
    pageSetUp();
    var table, organization, userStatus;

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

        if (!window.sessionStorage.getItem("organization")) {
            $.ajax({
                url: "/organization/map",
                dataType: "json",
                async: false,
                success: function (data) {
                    if (data.code == "100000") {
                        window.sessionStorage.setItem("organization", JSON.stringify(data.data));
                        organization = data.data;
                    }
                }
            });
        }
        if (!organization) {
            organization = JSON.parse(window.sessionStorage.getItem("organization"));
        }
        initDataTabel();

        $.each(organization, function (key, value) {
            $("#organizationId").append("<option value='" + key + "'>" + value + "</option>");
        });

        if (!window.sessionStorage.getItem("userStatus")) {
            $.ajax({
                url: "/item/userStatus",
                dataType: "json",
                async: false,
                success: function (data) {
                    if (data.code == "100000") {
                        window.sessionStorage.setItem("userStatus", JSON.stringify(data.data));
                        userStatus = data.data;
                    }
                }
            });
        }
        if (!userStatus) {
            userStatus = JSON.parse(window.sessionStorage.getItem("userStatus"));
        }
        $.each(userStatus, function (key, value) {
            $("#status").append("<option value='" + key + "'>" + value + "</option>");
        });

        $('#modelForm').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            excluded: [':disabled'],
            fields: {
                username: {
                    group: '.col-md-4',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        }
                    }
                },
                phone: {
                    group: '.col-md-4',
                    validators: {
                        notEmpty: {
                            message: '电话不能为空'
                        },
                        digits: {
                            message: '请输入有效的电话'
                        }
                    }
                },
                email: {
                    group: '.col-md-4',
                    validators: {
                        notEmpty: {
                            message: '邮箱不能为空'
                        },
                        emailAddress: {
                            message: '请输入有效的邮箱'
                        }
                    }
                },
                organizationId: {
                    group: '.col-md-4',
                    validators: {
                        notEmpty: {
                            message: '组织不能为空'
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
                url: "/user",
                data: $("#queryForm").serializeJson()
            },
            columns: [
                {
                    data: null,
                    title: "选择",
                    class: "text-center",
                    render: function (data, type, row, meta) {
                        return "<input type='checkbox' data='" + row.username + "' value='" + row.id + "'>";
                    }
                },
                {
                    data: "username",
                    title: "用户名"
                },
                {
                    data: "organizationId",
                    title: "组织",
                    render: function (data, type, row, meta) {
                        if (null != data) {
                            if (organization[data]) {
                                return organization[data];
                            } else {
                                return data;
                            }
                        }
                        return "";
                    }
                },
                {
                    data: "phone",
                    title: "联系电话",
                    defaultContent: ""
                },
                {
                    data: "email",
                    title: "邮箱",
                    defaultContent: ""
                },
                {
                    data: "status",
                    title: "状态",
                    render: function (data, type, row, meta) {
                        if (null != data) {
                            if (userStatus[data]) {
                                return userStatus[data];
                            } else {
                                return data;
                            }
                        }
                        return "";
                    }
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
            url: "/user/save",
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
                url: "/user/" + selected.val(),
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
                        url: "/user/del",
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

    var oldSign = new Set();
    function editRole() {
        var selected = $("#dtGrid").find(":checked");
        if (selected.length != 1) {
            $.smallBox({
                title: "提示信息",
                content: "<i class='fa fa-info-circle'></i> <i>请选择一条记录...</i>",
                iconSmall: "fa fa-warning fa-2x fadeInRight animated",
                timeout: 4000
            });
        } else {
            $("#currentId").val(selected.val());
            $("#currentTitle").html(selected.attr("data"));
            $.ajax({
                url: "/role/sign/",
                data: {userId: selected.val()},
                dataType: "json",
                success: function (data) {
                    if (data.code == "100000") {
                        $('#sign').Nestable().init(data.data, "roleName", "roleSign");
                        $('#sign').nestable({group: 1}).on("change", reSign);
                        var array = $('#sign').nestable('serialize');
                        oldSign = getSetValues(array);
                    }
                }
            });
            $.ajax({
                url: "/role/unsign/",
                data: {userId: selected.val()},
                dataType: "json",
                success: function (data) {
                    if (data.code == "100000") {
                        $('#unsign').Nestable().init(data.data, "roleName", "roleSign");
                        $('#unsign').nestable({group: 1});
                    }
                }
            });
            $("#roleModal").modal("show");
        }
    }

    function reSign() {
        var array = $('#sign').nestable('serialize');
        var newSign = getSetValues(array);
        oldSign.forEach(function (val, index) {
            if (newSign.has(val)) {
                oldSign.delete(val);
                newSign.delete(val);
            }
        });

        var id = $("#currentId").val();
        if (oldSign.size > 0) {//需要删除的
            oldSign.forEach(function (val, index) {
                delUserRole(id, val);
            });
        }
        if (newSign.size > 0) {//需要新增的
            newSign.forEach(function (val, index) {
                addUserRole(id, val);
            });
        }
        oldSign = getSetValues(array);
    }

    function addUserRole(userId, roleId) {
        $.post({
            url: "/user_role/save/",
            dataType: "json",
            data: {userId: userId, roleId: roleId},
            success: function (data) {
                if (data.code == "100000") {
                    $.smallBox({
                        title: "提示信息",
                        content: "<i class='fa fa-info-circle'></i> <i>操作成功...</i>",
                        color: "#659265",
                        iconSmall: "fa fa-check fa-2x fadeInRight animated",
                        timeout: 4000
                    });
                } else {
                    $.smallBox({
                        title: "提示信息",
                        content: "<i class='fa fa-clock-o'></i> <i>操作失败...【" + data.message + "】</i>",
                        color: "#C46A69",
                        iconSmall: "fa fa-times fa-2x fadeInRight animated",
                        timeout: 2000
                    });
                }
            }
        });
    }

    function delUserRole(userId, roleId) {
        $.post({
            url: "/user_role/del/",
            dataType: "json",
            data: {userId: userId, roleId: roleId},
            success: function (data) {
                if (data.code == "100000") {
                    $.smallBox({
                        title: "提示信息",
                        content: "<i class='fa fa-info-circle'></i> <i>操作成功...</i>",
                        color: "#659265",
                        iconSmall: "fa fa-check fa-2x fadeInRight animated",
                        timeout: 4000
                    });
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
</script>
