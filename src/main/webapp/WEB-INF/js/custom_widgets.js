(function ($) {
    var contextPath = "";
    var category;
    $.extend({
        initContextPath: function (ctx) {
            contextPath = ctx;
        },
        loadCategory: function (url,param, callback, responseType) {
            var settings = initSettings({url: url, type: "GET", dataType: responseType, data: param, success: callback});
            $.ajax(settings);
        }
    });

    $.fn.extend({
        wrapCategorySelect: function (settings) {
            var $target = $(this);
//            if(category == null){
                $.loadCategory(contextPath+"data/"+settings.type+"/getEntityProperty","", function (data) {

                    category = data;
                    _wrapSelect($target, category, settings);
                });
//            }else{
//                _wrapSelect($target, category, settings);
//            }

        }, wrapCategoryCompleted: function (settings) {
            var $target = $(this);
            settings = $.extend({label: "label", value: "value", root: "list", renderItem: ""}, settings);
            _wrapAutoCompleted($target, settings, function (request, response) {
                $.loadCategory(settings.label + "=" + request.term + "&removed=0", function (data) {
                    response(data[settings.root]);
                });
            });
        }
    })

    function _wrapAutoCompleted(target, settings, callback) {

        target.autocomplete({
            source: callback,
            minLength: 2,
            select: function (event, ui) {
                target.val(ui.item[settings.label]);
                return false;
            },
            open: function () {
                $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
            },
            close: function () {
                $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
            }
        }).data("autocomplete")._renderItem = function (ul, item) {
            var html = "";
            if (settings.renderItem)
                html = settings.renderItem(ul, item);
            else {
                html = "<a>" + item[settings.label] + "</a>";
            }
            return $("<li>").data("item.autocomplete", item)
                .append(html)
                .appendTo(ul);
        };
    }


    function _wrapSelect(target, json, settings) {

        settings = $.extend({defaultLabel: "请选择", defaultValue: "", label: "label", value: "value", root:'result.fieldProperties', selectVal: ""}, settings);

        var list = getJsonValByProperty(json, settings.root,settings.name);
        target.find("option").remove();
        var html = '<option value=' + settings.defaultValue + '>' + settings.defaultLabel + '</option>';

        for (var key in list.option) {
            if(list.option[key] != '请选择')
            html += '<option value="' + key + '">' + list.option[key] + '</option>';
        }
//        $.each(list.option, function (i, o) {
//            var label = getJsonValByProperty(o, settings.label);
//            var value = getJsonValByProperty(o, settings.value);
//        });
        target.html(html);
        target.find("option[value=" + settings.selectVal + "]").prop("selected", true);
        target.trigger("completed");
    }

    function getJsonValByProperty(json, prop,name) {
        if(prop.indexOf(".") != -1){
            var props = prop.split(".");
            for(var i = 0 ; i<props.length; i++){
                json = getJsonValByProperty(json,props[i],name);
            }
            return json;
        }else{
            for (var key in json) {
                if (key == prop)
                {
                    if(json[key].constructor==Array){
                        for(var i =0 ;i<json[key].length;i++){
                            if(json[key][i].path == name){
                                return json[key][i];
                            }
                        }
                    }else{
                        return json[key];
                    }
                }

            }
        }
    }

    function initSettings(settings) {
        settings.dataType = settings.dataType || "json";
        if (settings.data && settings.data.length > 0)
            settings.data = "format=" + settings.dataType + "&" + settings.data;
        else
            settings.data = "format=json";

        if (settings.type == "DELETE") {
            settings.type = "POST";
            settings.data += "&_method=delete";
        }
        else if (settings.type == "PUT") {
            settings.data += "&_method=put";
            settings.type = "POST";
        }

        return settings;
    }

})(jQuery);