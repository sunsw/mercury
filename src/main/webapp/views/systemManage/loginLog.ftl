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
                    <h2>登录日志列表 </h2>
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
                            </div>

                            <div class="form-group pull-right">
                                <div class="col-md-12">
                                    <a href="javascript:searchData();" class="btn btn-primary">
                                        <i class="fa fa-search"></i> 查询</a>
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

        initDataTabel();
    });

    function initDataTabel() {
        table = $('#dtGrid').DataTable({
            serverSide: true,
            ajax: {
                url: "/login_log",
                data: $("#queryForm").serializeJson()
            },
            columns: [
                {
                    data: "userId",
                    title: "用户ID",
                    defaultContent: ""
                },
                {
                    data: "username",
                    title: "用户名",
                    defaultContent: ""
                },
                {
                    data: "ip",
                    title: "IP",
                    defaultContent: ""
                },
                {
                    data: "createTime",
                    title: "登录时间",
                    defaultContent: ""
                }
            ]
        });
    }

    function searchData() {
        table.settings()[0].ajax.data = $("#queryForm").serializeJson();
        table.ajax.reload();
    }

</script>
