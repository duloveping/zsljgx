layui.use(['element', 'laydate', 'table'], function(){
    var laydate = layui.laydate;
    var table = layui.table;
    var $ = layui.$;

    laydate.render({ elem: '#startProduceDate'});
    laydate.render({ elem: '#endProduceDate'});

    table.render({
        elem: '#data-table-list',
        url: '/bill/deliver-goods-bill/list',
        request: {pageName: 'pageNum', limitName: 'pageSize'},
        parseData: function(res){
            return {code: res.status ? 0 : 1, msg: res.info, count: res.total, data: res.datas};
        },
        page: true,
        method: 'post',
        height: 'full-240',
        cellMinWidth: 80,
        limit: 30,
        toolbar: '#topToolbar',
        cols: [[{
            type: 'checkbox', fixed: 'left'
        },{
            title: '供应商编码',
            width: 150,
            templet: function (d) {
                return d.providerCode;
            }
        }, {
            title: '供应商名称',
            templet: function (d) {
                return d.providerName;
            }
        }, {
            title: '料号',
            width: 150,
            templet: function (d) {
                return d.materielCode;
            }
        }, {
            title: '品名',
            templet: function (d) {
                return d.materielName;
            }
        }, {
            title: '批次号',
            width: 150,
            templet: function (d) {
                return d.batchCode;
            }
        }, {
            title: '生产日期',
            width: 120,
            templet: function (d) {
                return moment(d.produceDate).format('YYYY-MM-DD');
            }
        }, {
            title: '生产班次',
            width: 150,
            templet: function (d) {
                return d.workShiftName;
            }
        }, {
            title: '操作',
            toolbar: '#toolbar',
            width: 170,
        }
        ]]
    });

    //头工具栏事件
    table.on('toolbar(topToolbar)', function(obj){
        var ids = new Array();
        var checkStatus = table.checkStatus(obj.config.id);

        layui.each(checkStatus.data, function(index, value){
            ids.push(value.id);
        });

       if (ids.length > 0) {
           if (obj.event == 'print') {
               document.location.href = "/bill/deliver-goods-bill/multi-print?ids=" + ids.join(",");
           } else if (obj.event == 'delete') {
           }
       } else {
           layer.alert("请选择要操作的数据");
       }
    });

    //监听行工具事件
    table.on('tool(topToolbar)', function(obj){
        var data = obj.data;
    });



    var active = {
        reload: function () {
            table.reload('data-table-list', {
                request: {pageName: 'pageNum', limitName: 'pageSize'},
                page: { curr: 1 },
                where: {
                    providerCode: $("#providerCode").val(),
                    providerName: $("#providerName").val(),
                    materielCode: $("#materielCode").val(),
                    materielName: $("#materielName").val(),
                    startProduceDate: $("#startProduceDate").val(),
                    endProduceDate: $("#endProduceDate").val(),
                    workShiftName: $("#workShiftName").val(),
                    batchCode: $("#batchCode").val()
                }
            });
        }
    };

    $('#searchButton').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});