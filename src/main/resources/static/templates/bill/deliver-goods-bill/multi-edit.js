var rowIndex = 1;

layui.use(['layer', 'form', 'laydate', 'laytpl'], function(){
    var layer = layui.layer;
    var laydate = layui.laydate;
    var form = layui.form;
    var laytpl = layui.laytpl;
    var $ = layui.$;

    laydate.render({ elem: '#multiProduceDate0'});

    $("#dataListTable").on("click", ".layui-icon-add-1, .layui-icon-delete", function (a) {
        var obj = $(this);
        var method = obj.attr("lay-event");
        if (method == "add") {
            var list = new Array();
            list.push(new DeliverGoodsBill());

            var data = {list: list};
            var dataRowTpl = document.getElementById("dataRowTpl").innerHTML;
            laytpl(dataRowTpl).render(data, function(html){
                $("#dataListTable").append(html);
                laydate.render({ elem: '#multiProduceDate' + rowIndex});
                rowIndex++;
            });
        } else if (method == "delete") {
            var rowNum = obj.parent().parent().prevAll().length;
            if (0 == rowNum) {
                layer.alert("不可以删除最后一行！");
            } else {
                layer.confirm('您确认要删除吗？', {icon: 3, title:'提示'}, function(index){
                    obj.parent().parent().remove();
                    layer.close(index);
                });
            }
        }
    });


    form.on('submit(multiSubmitForm)', function(data){
        var datas = new Array();
        var multiProviderCodes = $("input[name=multiProviderCode]");
        var multiProviderNames = $("input[name=multiProviderName]");
        var multiMaterielCodes = $("input[name=multiMaterielCode]");
        var multiMaterielNames = $("input[name=multiMaterielName]");
        var multiMaterielSpecs = $("input[name=multiMaterielSpec]");
        var multiAmounts = $("input[name=multiAmount]");
        var multiUnits = $("input[name=multiUnit]");
        var multiBatchCodes = $("input[name=multiBatchCode]");
        var multiProduceDates = $("input[name=multiProduceDate]");
        var multiWorkShiftNames = $("input[name=multiWorkShiftName]");

        if (null != multiProviderCodes && multiProviderCodes.length > 0) {
            layui.each(multiProviderCodes, function(index, item){
                var po = new DeliverGoodsBill();
                po.providerCode = $.trim(item.value);
                po.providerName = $.trim(multiProviderNames[index].value);
                po.materielCode = $.trim(multiMaterielCodes[index].value);
                po.materielName = $.trim(multiMaterielNames[index].value);
                po.materielSpec = $.trim(multiMaterielSpecs[index].value);
                po.amount = $.trim(multiAmounts[index].value);
                po.unit = $.trim(multiUnits[index].value);
                po.batchCode = $.trim(multiBatchCodes[index].value);
                po.produceDate = $.trim(multiProduceDates[index].value);
                po.workShiftName = $.trim(multiWorkShiftNames[index].value);
                datas.push(po);
            });
        }

        var obj = {billList: datas};
        var indexLoad = layer.load();
        $("#saveButton").prop("disabled", true);

        $.ajax({
            type: "post",
            url: "/bill/deliver-goods-bill/multi-save",
            cache: false,
            data: JSON.stringify(obj),
            dataType: "json",
            contentType : 'application/json;charset=utf-8',
            success: function (res) {
                $("#saveButton").prop("disabled", false);
                layer.close(indexLoad);
                if (res.status) {
                    document.getElementById("multiForm").reset();
                    var ids = res.ids;
                    window.open("/bill/deliver-goods-bill/multi-print?ids=" + ids.join(","));
                }
            },
            error: function (XmlHttpRequest, textStatus, errorThrown) {
                $("#saveButton").prop("disabled", false);
                layer.close(indexLoad);
                layer.alert('数据保存失败！' + XmlHttpRequest.status);
            }
        });
        return false;
    });
});