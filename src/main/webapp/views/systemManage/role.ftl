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
                    <h2>角色列表 </h2>

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
                        <div class="dd" id="menuNestable">
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
                    <h2>角色详情 </h2>

                </header>

                <!-- widget div-->
                <div>

                    <!-- widget edit box -->
                    <div class="jarviswidget-editbox">
                        <!-- This area used as dropdown edit box -->

                    </div>
                    <!-- end widget edit box -->

                    <form id="queryForm" action="" class="form-horizontal">
                        <input type="hidden" name="id" id="currentId">
                        <fieldset>
                            <div class="form-group pull-right">
                                <div class="col-md-12">
                                    <a href="javascript:openModel();" class="btn btn-success">
                                        <i class="fa fa-plus-circle"></i> 新增</a>
                                    <a href="javascript:editData();" class="btn btn-warning">
                                        <i class="fa fa-edit"></i> 修改</a>
                                    <a href="javascript:delData();" class="btn btn-danger">
                                        <i class="fa fa-minus-circle"></i> 删除</a>
                                    <a href="javascript:editMenu();" class="btn btn-info">
                                        <i class="fa fa-sitemap"></i> 菜单分配</a>
                                    <a href="javascript:editPermission();" class="btn btn-info">
                                        <i class="fa fa-lock"></i> 权限分配</a>
                                </div>
                            </div>
                        </fieldset>
                    </form>

                    <!-- widget content -->
                    <div class="widget-body no-padding">

                        <form id="infoForm" class="smart-form">
                            <header>当前角色</header>

                            <fieldset>
                                <div class="row">
                                    <section class="col col-6">
                                        <label class="label">角色名称</label>
                                        <label class="input">
                                            <input type="text" name="roleName" id="roleName" readonly>
                                        </label>
                                    </section>
                                    <section class="col col-6">
                                        <label class="label">角色标识</label>
                                        <label class="input">
                                            <input type="text" name="roleSign" id="roleSign" readonly>
                                        </label>
                                    </section>
                                    <section class="col col-6">
                                        <label class="label">序号</label>
                                        <label class="input">
                                            <input type="text" name="sort" id="sort" readonly>
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
                        </form>
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
                            <label class="control-label col-md-2">角色名称</label>
                            <div class="col-md-4">
                                <input name="roleName" class="form-control" type="text">
                            </div>

                            <label class="control-label col-md-2">角色标识</label>
                            <div class="col-md-4">
                                <input name="roleSign" class="form-control" type="text">
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

<div class="modal fade" id="permissionModal" tabindex="-1" role="dialog" aria-labelledby="permissionModalLabel"
     aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="permissionModalLabel">
                    权限分配
                </h4>
            </div>
            <div class="modal-body" style="padding-top: 0;">
                <div class="row">
                    <form class="smart-form">
                        <header id="currentTitle"></header>
                    </form>
                    <div class="col-sm-6 col-lg-6">
                        <h6>已分配</h6>
                        <div id="signPermissions">
                        </div>
                    </div>

                    <div class="col-sm-6 col-lg-6">
                        <h6>未分配</h6>
                        <div id="unsignPermissions">
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

<div class="modal fade" id="menuModal" tabindex="-1" role="dialog" aria-labelledby="menuModalLabel"
     aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="menuModalLabel">
                    菜单分配
                </h4>
            </div>
            <div class="modal-body" style="padding-top: 0;">
                <div class="row">
                    <form class="smart-form">
                        <header id="currentTitle0"></header>
                    </form>
                    <div id="checkTree" class="col-sm-12 col-lg-12">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a href="javascript:saveMenu();" class="btn btn-primary">
                    <i class="fa fa-save"></i> 保存</a>
                <a href="javascript:void(0);" class="btn btn-danger" data-dismiss="modal">
                    <i class="fa fa-remove"></i> 取消</a>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<@lib.javascriptDatatable/>
<script src="/app/js/mercury-checkTree.js"/>
<script src="/app/js/mercury-Nestable.js"/>
<script src="/assets/js/plugin/jquery-nestable/jquery.nestable.min.js"/>

<script type="text/javascript">
    pageSetUp();

    $(function () {
        $("#modelForm").keydown(function (e) {
            var e = e || event,
                    keycode = e.which || e.keyCode;
            if (keycode == 13) {
                saveData();
                e.preventDefault();
            }
        });

        initRoleList();

        $('#modelForm').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            excluded: [':disabled'],
            fields: {
                roleName: {
                    group: '.col-md-4',
                    validators: {
                        notEmpty: {
                            message: '角色名称不能为空'
                        }
                    }
                },
                roleSign: {
                    group: '.col-md-4',
                    validators: {
                        notEmpty: {
                            message: '角色标识不能为空'
                        }
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

        $.ajax({
            url: "/menu",
            dataType: "json",
            success: function (data) {
                if (data.code == "100000") {
                    $("#checkTree").checkTree().init(data.data, "subMenus", "menuTitle");
                }
            }
        });

    });

    var currentTitle = "";
    function initRoleList() {
        $.ajax({
            url: "/role",
            dataType: "json",
            success: function (data) {
                if (data.code == "100000") {
                    $('#menuNestable').Nestable().init(data.data, "roleName", "roleSign");
                    var item = $('#menuNestable').find("li.dd-item");
                    item.on("click", function (a) {
                        currentTitle = this.innerText;
                        var id = $(this).attr("data-id");
                        $("#currentId").val(id);
                        loadInfo();
                        a.stopPropagation();
                    });
                }
            }
        });
    }

    function loadInfo() {
        var id = $("#currentId").val();
        $("#infoForm").clear();
        $.ajax({
            url: "/role/" + id,
            dataType: "json",
            success: function (data) {
                if (data.code == "100000") {
                    $("#infoForm").load(data.data);
                }
            }
        });
    }

    var oldSign = new Set();
    function editPermission() {
        var id = $("#currentId").val();
        if (!id) {
            $.smallBox({
                title: "提示信息",
                content: "<i class='fa fa-info-circle'></i> <i>请选择一条记录...</i>",
                iconSmall: "fa fa-warning fa-2x fadeInRight animated",
                timeout: 4000
            });
            return;
        }
        $.ajax({
            url: "/permission/sign/",
            data: {roleId: id},
            dataType: "json",
            success: function (data) {
                if (data.code == "100000") {
                    $('#signPermissions').Nestable().init(data.data, "permissionName", "permissionSign");
                    $('#signPermissions').nestable({group: 1}).on("change", reSign);
                    var array = $('#signPermissions').nestable('serialize');
                    oldSign = getSetValues(array);
                }
            }
        });

        $.ajax({
            url: "/permission/unsign/",
            data: {roleId: id},
            dataType: "json",
            success: function (data) {
                if (data.code == "100000") {
                    $('#unsignPermissions').Nestable().init(data.data, "permissionName", "permissionSign");
                    $('#unsignPermissions').nestable({group: 1});
                }
            }
        });
        $("#currentTitle").html(currentTitle);
        $("#permissionModal").modal("show");
    }

    function reSign() {
        var array = $('#signPermissions').nestable('serialize');
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
                delRolePermission(id, val);
            });
        }
        if (newSign.size > 0) {//需要新增的
            newSign.forEach(function (val, index) {
                addRolePermission(id, val);
            });
        }
        oldSign = getSetValues(array);
    }

    function addRolePermission(roleId, permissionId) {
        $.post({
            url: "/role_permission/save/",
            dataType: "json",
            data: {roleId: roleId, permissionId: permissionId},
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

    function delRolePermission(roleId, permissionId) {
        $.post({
            url: "/role_permission/del/",
            dataType: "json",
            data: {roleId: roleId, permissionId: permissionId},
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
            url: "/role/save",
            data: $("#modelForm").serializeJson(),
            dataType: "json",
            success: function (data) {
                $('#myModal').modal('hide');
                if (data.code == "100000") {
                    $.smallBox({
                        title: "提示信息",
                        content: "<i class='fa fa-info-circle'></i> <i>操作成功...</i>",
                        color: "#659265",
                        iconSmall: "fa fa-check fa-2x fadeInRight animated",
                        timeout: 4000
                    });
                    initRoleList();
                } else {
                    $.smallBox({
                        title: "提示信息",
                        content: "<i class='fa fa-clock-o'></i> <i>操作失败...【" + data.message + "】</i>",
                        color: "#C46A69",
                        iconSmall: "fa fa-times fa-2x fadeInRight animated",
                        timeout: 4000
                    }, function () {
                        $("#modelForm").clear();
                        validator.resetForm();
                    });
                }
            }
        });
    }

    function editData() {
        var id = $("#currentId").val();
        if (!id) {
            $.smallBox({
                title: "提示信息",
                content: "<i class='fa fa-info-circle'></i> <i>请选择一条记录...</i>",
                iconSmall: "fa fa-warning fa-2x fadeInRight animated",
                timeout: 4000
            });
        } else {
            $("#modelForm").clear();
            $.ajax({
                url: "/role/" + id,
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
        var id = $("#currentId").val();
        if (!id) {
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
                        url: "/role/del",
                        data: {id: id},
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
                                initRoleList();
                                $("#infoForm").clear();
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

    function editMenu() {
        var id = $("#currentId").val();
        if (!id) {
            $.smallBox({
                title: "提示信息",
                content: "<i class='fa fa-info-circle'></i> <i>请选择一条记录...</i>",
                iconSmall: "fa fa-warning fa-2x fadeInRight animated",
                timeout: 4000
            });
            return;
        }
        $("#checkTree").checkTree().uncheckedAll();
        $.ajax({
            url: "/menu/sign/",
            data: {roleId: id},
            dataType: "json",
            success: function (data) {
                if (data.code == "100000") {
                    $("#checkTree").checkTree().setChecked(data.data[0]);
                }
            }
        });
        $("#currentTitle0").html(currentTitle);
        $("#menuModal").modal("show");
    }

    function saveMenu() {
        var id = $("#currentId").val();
        var menus = $("#checkTree").checkTree().getChecked();
        $.post({
            url: "/role_menu/reset",
            dataType: "json",
            data: {roleId: id, menuIds: menus.join(",")},
            success: function (data) {
                if (data.code == "100000") {
                    $.smallBox({
                        title: "提示信息",
                        content: "<i class='fa fa-info-circle'></i> <i>操作成功...</i>",
                        color: "#659265",
                        iconSmall: "fa fa-check fa-2x fadeInRight animated",
                        timeout: 4000
                    });
                    $("#menuModal").modal("hide");
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
