!function () {
    $.fn.checkTree = function () {
        return new CheckTree(this);
    };

    var CheckTree = function (element) {
        this.$element = $(element);
    };

    CheckTree.prototype.init = function (data, children, title) {
        this.$element.addClass("tree");
        this.$element.html(buildTree(data, children, title));

        this.$element.find("ul").attr("role", "tree").find("ul").attr("role", "group");
        var mytreebranch = this.$element.find("li:has(ul)").attr("role", "treeitem").find(" > span");
        mytreebranch.on("click", function (a) {
            var b = $(this).parent("li.parent_li").find(" > ul > li");
            b.is(":visible") ? b.hide("fast") : b.show("fast");
            a.stopPropagation();
        });
        var myselect = this.$element.find("li").find(" > span > i");
        myselect.on("click", function (a) {
            //所有儿孙
            var b = $(this).parent().parent("li.parent_li").find("ul > li > span > i");
            $(this).hasClass("fa-check-square")
                ? ($(this).addClass("fa-square-o").removeClass("fa-check-square").removeClass("fa-square"),
                    b.addClass("fa-square-o").removeClass("fa-check-square").removeClass("fa-square"))
                : ($(this).addClass("fa-check-square").removeClass("fa-square-o").removeClass("fa-square"),
                    b.addClass("fa-check-square").removeClass("fa-square-o").removeClass("fa-square"));

            checkParent($(this));
            a.stopPropagation();
        });
    };

    CheckTree.prototype.getChecked = function () {
        var data = new Array();
        this.$element.find("span:has(i.fa-check-square, i.fa-square)").each(function () {
            data.push($(this).attr("id"));
        });
        return data;
    };

    CheckTree.prototype.setChecked = function (items) {
        if (!items) {
            return;
        }
        var $this = this;
        if (!items.subMenus || items.subMenus.length <= 0) {
            $("#" + items.id).find(" > i.fa-square-o").trigger("click");
        } else {
            $.each(items.subMenus, function (i, item) {
                $this.setChecked(item);
            });
        }
    };

    CheckTree.prototype.uncheckedAll = function () {
        this.$element.find("i.fa-check-square, i.fa-square").each(function () {
            $(this).addClass("fa-square-o").removeClass("fa-check-square").removeClass("fa-square");
        });
    };

    function buildTree(data, children, title) {
        var html = "";
        if (data && data.length > 0) {
            html += "<ul>";
            data.forEach(function (val, index) {
                html += '<li class="parent_li"><span id="'
                    + val.id
                    + '"><i class="fa fa-lg fa-square-o"></i> '
                    + val[title]
                    + '</span>'
                    + buildTree(val[children], children, title)
                    + '</li>';
            });
            html += "</ul>";
        }
        return html;
    }

    function checkParent(obj) {
        var all = !obj.hasClass("fa-square") && obj.hasClass("fa-check-square");
        var none = !obj.hasClass("fa-square") && !obj.hasClass("fa-check-square");
        //所有兄弟
        obj.parent().parent("li").siblings().each(function () {
            all = all && !$(this).find(" > span > i").hasClass("fa-square") && $(this).find(" > span > i").hasClass("fa-check-square");
            none = none && !$(this).find(" > span > i").hasClass("fa-square") && !$(this).find(" > span > i").hasClass("fa-check-square");
        });
        var p = obj.parent().parent().parent().parent("li.parent_li");
        if (p.length > 0) {
            if (all) {
                p.find(" > span > i").addClass("fa-check-square").removeClass("fa-square-o").removeClass("fa-square");
            } else if (none) {
                p.find(" > span > i").addClass("fa-square-o").removeClass("fa-check-square").removeClass("fa-square");
            } else {
                p.find(" > span > i").addClass("fa-square").removeClass("fa-check-square").removeClass("fa-square-o");
            }
            checkParent(p.find(" > span > i"));
        }
    }

}();