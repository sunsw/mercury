!function () {
    $.fn.Nestable = function () {
        return new Nestable(this);
    };

    var Nestable = function (element) {
        this.$element = $(element);
    };

    Nestable.prototype.init = function (data, title, subTitle) {
        this.$element.addClass("dd");
        this.$element.html(buildNestable(data, title, subTitle));
    }

    function buildNestable(data, title, subTitle) {
        var html = '<div class="dd-empty"></div>';
        if (data && data.length > 0) {
            html = '<ol class="dd-list">';
            data.forEach(function (val, index) {
                html += ('<li class="dd-item" data-id="')
                    + (val.id)
                    + ('"><div class="dd-handle">')
                    + (val[title])
                    + (' <span>- ')
                    + (val[subTitle])
                    + ('</span></div></li>');
            });
            html += '</ol>';
        }
        return html;
    }

}();