layui.use(['layer', 'element', 'form', 'table', 'laytpl'], function(){
    var $ = layui.$;
    $("#printButton").on("click", function () {
        $("#dataTable").jqprint();
    });
});