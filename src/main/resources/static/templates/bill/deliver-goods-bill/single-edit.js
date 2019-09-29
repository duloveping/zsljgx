layui.use(['layer', 'form', 'laydate'], function(){
    var layer = layui.layer;
    var laydate = layui.laydate;
    var form = layui.form;
    var $ = layui.$;

    laydate.render({ elem: '#produceDate'});

    form.on('submit(singleSubmitForm)', function(data){
        var field = data.field;
        var po = new DeliverGoodsBill();
        po.id = field.id;
        po.providerCode = field.providerCode;
        po.providerName = field.providerName;
        po.materielCode = field.materielCode;
        po.materielName = field.materielName;
        po.materielSpec = field.materielSpec;
        po.amount = field.amount;
        po.unit = field.unit;
        po.batchCode = field.batchCode;
        po.produceDate = field.produceDate;
        po.workShiftName = field.workShiftName;

        $("#saveButton").prop("disabled", true);

        var indexLoad = layer.load();

        $.ajax({
            type: "post",
            url: "/bill/deliver-goods-bill/single-save",
            cache: false,
            data: JSON.stringify(po),
            dataType: "json",
            contentType : 'application/json;charset=utf-8',
            success: function (res) {
                $("#saveButton").prop("disabled", false);
                layer.close(indexLoad);
                if (res.status) {
                    document.getElementById("singleForm").reset();
                    window.open("/bill/deliver-goods-bill/multi-print?ids=" + res.id);
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