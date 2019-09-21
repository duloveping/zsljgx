layui.use(['layer', 'element', 'form', 'table', 'laytpl'], function(){
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var laytpl = layui.laytpl;

    var data = {providerCode: ""};

    //监听提交
    form.on('submit(multiSubmitCreate)', function(data){
        layer.alert(JSON.stringify(data.field), {
            title: '最终的提交信息'
        })
        return false;
    });

    $("#dataListTable").on("click", ".layui-icon-add-1, .layui-icon-delete", function () {
        var obj = $(this);
        var method = obj.attr("lay-event");
        if (method == "add") {
            var list = new Array();
            list.push(new QRCodeObject());

            var data = {list: list};
            var dataRowTpl = document.getElementById("dataRowTpl").innerHTML;
            laytpl(dataRowTpl).render(data, function(html){
                $("#dataListTable").append(html);
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
});

function QRCodeObject() {};

QRCodeObject.prototype = {
    id: "",
    providerCode: "",
    providerName: "",
    materielCode: "",
    materielName: "",
    batchCode: "",
    produceDate: ""
}