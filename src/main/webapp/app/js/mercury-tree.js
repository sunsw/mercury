!function () {
    $.fn.tree = function () {
        return new Tree(this);
    };

    var Tree = function (element) {
        this.$element = $(element);
    };

    Tree.prototype.init = function (data, children, title) {
        this.$element.addClass("tree");
        this.$element.html(buildTree(data, children, title));

    };

    function buildTree(data, children, title) {
        var html = "";
        if (data && data.length > 0) {
            html += "<ul>";
            data.forEach(function (val, index) {
                html += '<li class="parent_li"><span class="tree-item" data-id="'
                    + val.id
                    + '"><i class="fa fa-lg ';
                if (val[children] && val[children].length > 0) {
                    html += 'fa-folder-open';
                } else {
                    html += 'fa-leaf';
                }
                html += '"></i> '
                    + val[title]
                    + '</span>'
                    + buildTree(val[children], children, title)
                    + '</li>';
            });
            html += "</ul>";
        }
        return html;
    }

}();