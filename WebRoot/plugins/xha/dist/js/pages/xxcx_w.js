// 表格数据
// check:  -1待提交 0待审核 1已通过 2已驳回
var data = [{
        "index": 1,
        "check": 0,
        "uid": "12345678912345678",
        "name": "测试名称测试名称测试名称测试名称",
        "office": "测试部门",
        "sdate": "2018-01-01",
        "edate": "2018-05-01",
        "jbr": "***",
        "jswz": "测试位置测试位置测试位置",
        "jsdw": "测试单位测试单位",
        "zzjgdm": "000000000",
        "fwbh": "154_7896255",
        "ydxzmc": "测试测试测试",
        "ydmj": "12345 公顷",
        "hhbl": "0.20",
        "rjl": "5.41",
        "kzgd": "236 米",
        "ldl": "12.31%",
        "jzmd": "56.13%",
        "tcws": "600 个",
        "hmcsyq": "海绵城市要求要求要求要求"
    },
    {
        "index": 2,
        "check": 1,
        "uid": "12345678123456789",
        "name": "测试名称测试名称测试名称",
        "office": "测试部门",
        "sdate": "2018-01-01",
        "edate": "2018-06-01",
        "jbr": "***",
        "jswz": "测试位置测试位置测试位置",
        "jsdw": "测试单位测试单位",
        "zzjgdm": "000000000",
        "fwbh": "154_7896255",
        "ydxzmc": "测试测试测试",
        "ydmj": "12345 公顷",
        "hhbl": "0.20",
        "rjl": "5.41",
        "kzgd": "236 米",
        "ldl": "12.31%",
        "jzmd": "56.13%",
        "tcws": "600 个",
        "hmcsyq": "海绵城市要求要求要求要求"
    }, {
        "index": 3,
        "check": 1,
        "uid": "00000678912345678",
        "name": "测试名称测试名称",
        "office": "测试部门",
        "sdate": "2018-01-01",
        "edate": "2018-07-01",
        "jbr": "***",
        "jswz": "测试位置测试位置测试位置",
        "jsdw": "测试单位测试单位",
        "zzjgdm": "000000000",
        "fwbh": "154_7896255",
        "ydxzmc": "测试测试测试",
        "ydmj": "12345 公顷",
        "hhbl": "0.20",
        "rjl": "5.41",
        "kzgd": "236 米",
        "ldl": "12.31%",
        "jzmd": "56.13%",
        "tcws": "600 个",
        "hmcsyq": "海绵城市要求要求要求要求"
    }, {
        "index": 4,
        "check": 2,
        "uid": "12345678912345678",
        "name": "测试名称测试名称测试名称",
        "office": "测试部门",
        "sdate": "2018-01-01",
        "edate": "2018-04-01",
        "jbr": "***",
        "jswz": "测试位置测试位置测试位置",
        "jsdw": "测试单位测试单位",
        "zzjgdm": "000000000",
        "fwbh": "154_7896255",
        "ydxzmc": "测试测试测试",
        "ydmj": "12345 公顷",
        "hhbl": "0.20",
        "rjl": "5.41",
        "kzgd": "236 米",
        "ldl": "12.31%",
        "jzmd": "56.13%",
        "tcws": "600 个",
        "hmcsyq": "海绵城市要求要求要求要求"
    }, {
        "index": 5,
        "check": 0,
        "uid": "12345678912345678",
        "name": "测试名称测试名称",
        "office": "测试部门",
        "sdate": "2018-01-01",
        "edate": "2018-07-01",
        "jbr": "***",
        "jswz": "测试位置测试位置测试位置",
        "jsdw": "测试单位测试单位",
        "zzjgdm": "000000000",
        "fwbh": "154_7896255",
        "ydxzmc": "测试测试测试",
        "ydmj": "12345 公顷",
        "hhbl": "0.20",
        "rjl": "5.41",
        "kzgd": "236 米",
        "ldl": "12.31%",
        "jzmd": "56.13%",
        "tcws": "600 个",
        "hmcsyq": "海绵城市要求要求要求要求"
    }, {
        "index": 6,
        "check": 0,
        "uid": "12345678912345678",
        "name": "测试名称测试名称",
        "office": "测试部门",
        "sdate": "2018-01-01",
        "edate": "2018-07-01",
        "jbr": "***",
        "jswz": "测试位置测试位置测试位置",
        "jsdw": "测试单位测试单位",
        "zzjgdm": "000000000",
        "fwbh": "154_7896255",
        "ydxzmc": "测试测试测试",
        "ydmj": "12345 公顷",
        "hhbl": "0.20",
        "rjl": "5.41",
        "kzgd": "236 米",
        "ldl": "12.31%",
        "jzmd": "56.13%",
        "tcws": "600 个",
        "hmcsyq": "海绵城市要求要求要求要求"
    }, {
        "index": 7,
        "check": 2,
        "uid": "12345678912345678",
        "name": "测试名称测试名称",
        "office": "测试部门",
        "sdate": "2018-01-01",
        "edate": "2018-07-01",
        "jbr": "***",
        "jswz": "测试位置测试位置测试位置",
        "jsdw": "测试单位测试单位",
        "zzjgdm": "000000000",
        "fwbh": "154_7896255",
        "ydxzmc": "测试测试测试",
        "ydmj": "12345 公顷",
        "hhbl": "0.20",
        "rjl": "5.41",
        "kzgd": "236 米",
        "ldl": "12.31%",
        "jzmd": "56.13%",
        "tcws": "600 个",
        "hmcsyq": "海绵城市要求要求要求要求"
    }
];

// 详情子表格
var childdata = ["经办人", "建设位置", "建设单位", "组织机构代码", "发文编号", "用地性质名称", "用地面积", "混合比例", "容积率", "控制高度", "绿地率", "建筑密度", "停车位数", "海绵城市要求"];
var childdataname = ["jbr", "jswz", "jsdw", "zzjgdm", "fwbh", "ydxzmc", "ydmj", "hhbl", "rjl", "kzgd", "ldl", "jzmd", "tcws", "hmcsyq"];

// 时间轴
var $timeline;
var timelineindex;

$(function() {
    // 选择框
    $('.select2').select2({
        language: "zh-CN"
    });

    // 时间选择
    $('#datepicker-1').datepicker({
        autoclose: true
    });
    $('#datepicker-2').datepicker({
        autoclose: true
    });

    // 表格
    var parentTable = $('#search-table').DataTable({
        // 基础设定
        paging: true,
        ordering: true,
        lengthChange: false,
        pageLength: 5,
        searching: false,
        info: true,
        autoWidth: false,
        // 汉化
        language: {
            sProcessing: "处理中...",
            sLengthMenu: "显示 _MENU_ 项结果",
            sZeroRecords: "没有匹配结果",
            sInfo: "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            sInfoEmpty: "显示第 0 至 0 项结果，共 0 项",
            sInfoFiltered: "(由 _MAX_ 项结果过滤)",
            sInfoPostFix: "",
            sSearch: "搜索:",
            sUrl: "",
            sEmptyTable: "表中数据为空",
            sLoadingRecords: "载入中...",
            sInfoThousands: ",",
            oPaginate: {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页"
            },
            oAria: {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        },
        // 加载数据
        data: data,
        // 设定列属性
        columns: [{
                "width": 30,
                "orderable": true,
                "data": "index",
                "targets": 2,
                "render": function(data, type, row) {
                    var index = data;
                    if (row.check == 0) {
                        var html = "<i class='fa fa-circle text-yellow table-check'></i>";
                    } else
                    if (row.check == 1) {
                        var html = "<i class='fa fa-circle text-success table-check'></i>";
                    } else {
                        var html = "<i class='fa fa-circle text-red table-check'></i>";
                    }
                    html += index;
                    return html;
                }
            },
            {
                "width": 30,
                "orderable": true,
                "data": "uid"
            },
            {
                "data": "name"
            },
            {
                "data": "office"
            },
            {
                "data": "sdate"
            },
            {
                "data": "edate"
            },
            {
                "width": 150,
                "orderable": false,
                "data": null,
                "targets": 2,
                "render": function(data, type, row) {
                    // 记录index
                    var index = row.index;
                    // 设定按钮
                    var html = "<span class='badge table-badge bg-blue table-info' id='" + index + "'>&nbsp;<i class='fa fa-info'></i>&nbsp;详情</span>";
                    html += "<span class='badge table-badge bg-purple table-time' id='" + index + "'><i class='fa fa-clock-o'></i>&nbsp;时间轴</span>";
                    return html;
                }
            }
        ],
        "rowCallback": function(row, data) {
            if (data.check == 2) {
                $(row).css("background-color", "rgba(221,75,57,0.1)");
            }
        }
    });

    // 详情子表格
    $(".table tbody").on("click", ".table-info", function() {
        var id = $(this).attr("id") - 1;
        if ($(this).hasClass("bg-blue")) {
            $(this).removeClass("bg-blue").addClass("bg-disabled");
            $("#search-details").show();
        } else {
            $(this).removeClass("bg-disabled").addClass("bg-blue");
            $("#search-details").hide();

        }
        // var row = parentTable.row($(this).closest("tr"));
        // if (row.child() == undefined) {
        //     $(this).removeClass("bg-blue").addClass("bg-disabled");
        //     var html = "<table id='example2' class='table table-condensed table-child'><thead><tr><th>序号</th><th>项目</th><th>数值</th><th class='table-child-middle'>备注</th><th>序号</th><th>项目</th><th>数值</th><th>备注</th></tr></thead><tbody>";
        //     for (var i = 0; i < 7; i++) {
        //         // 左
        //         var idx_l = i + 1;
        //         var name_l = childdataname[i];
        //         html += "<tr><td>" + idx_l + "</td>";
        //         html += "<td>" + childdata[i] + "</td>";
        //         html += "<td>" + data[id][name_l] + "</td>";
        //         html += "<td class='table-child-middle'>无</td>";
        //         // 右
        //         var idx_r = i + 8;
        //         var name_r = childdataname[i + 7];
        //         html += "<td>" + idx_r + "</td>";
        //         html += "<td>" + childdata[i + 7] + "</td>";
        //         html += "<td>" + data[id][name_r] + "</td>";
        //         html += "<td>无</td></tr>";
        //     }
        //     html += "</tbody></table>";
        //     var $table = $(html);
        //     $table.attr("id", "childTable_" + id);
        //     var childTable = $table.DataTable({
        //         // 基础设定
        //         paging: false,
        //         ordering: false,
        //         lengthChange: false,
        //         searching: false,
        //         info: false,
        //         autoWidth: false,
        //         columns: [{
        //                 "width": 30
        //             },
        //             null,
        //             null,
        //             null,
        //             {
        //                 "width": 30
        //             },
        //             null,
        //             null,
        //             null
        //         ]
        //     });
        //     row.child(childTable.table().container());
        //     row.child.show();
        // } else {
        //     $(this).removeClass("bg-disabled").addClass("bg-blue");
        //     row.child(false);
        // }
    });

    // 时间轴
    $(".table tbody").on("click", ".table-time", function() {
        var id = $(this).attr("id");
        var rowdata = data[id - 1];
        var title = rowdata.name;
        var edate = rowdata.edate;
        var check = rowdata.check;
        var idx = rnd(1, 4);
        if ($timeline == undefined && $timeline == null) {
            $(this).addClass("timeline-showed");
            $(this).removeClass("bg-purple").addClass("bg-disabled");
            setTimeline(title, idx, check, edate);
            $("#search-timeline").show();
            $timeline = $(this);
            timelineindex = id;
        } else {
            if (timelineindex == id) {
                if ($(this).hasClass("timeline-showed")) {
                    $(this).removeClass("timeline-showed");
                    $(this).removeClass("bg-disabled").addClass("bg-purple");
                    $("#search-timeline").hide();
                } else {
                    $(this).addClass("timeline-showed");
                    $(this).removeClass("bg-purple").addClass("bg-disabled");
                    $("#search-timeline").show();
                }
            } else {
                if ($timeline.hasClass("timeline-showed")) {
                    $timeline.removeClass("timeline-showed");
                    $timeline.removeClass("bg-disabled").addClass("bg-purple");
                }
                $(this).addClass("timeline-showed");
                $(this).removeClass("bg-purple").addClass("bg-disabled");
                setTimeline(title, idx, check, edate);
                $("#search-timeline").show();
                $timeline = $(this);
                timelineindex = id;
            }
        }
    });

    // 设定timeline
    function setTimeline(title, index, status, time) {
        $("#timeline-title").text(title);

        for (var i = 1; i < 5; i++) {
            var id = "#step-" + i;
            var idn = id + "-text";
            if (i < index) {
                $(id).removeClass().addClass("done");
                $(idn).empty().text("2018-01-13");
            } else if (i == index) {
                switch (status) {
                    case 0:
                        {
                            $(id).removeClass().addClass("selected");
                            $(idn).empty().text("待审核");
                            break;
                        }
                    case 1:
                        {
                            $(id).removeClass().addClass("done");
                            $(idn).empty().text(time);
                            break;
                        }
                    case 2:
                        {
                            $(id).removeClass().addClass("failed");
                            $(idn).empty().text(time);
                            break;
                        }
                    default:
                        {
                            $(id).removeClass().addClass("disabled");
                            $(idn).empty().text("未提交");
                        }
                }
            } else {
                $(id).removeClass().addClass("disabled");
                $(idn).empty().text("未提交");
            }
        }
    }

    // 随机整数
    function rnd(n, m) {
        var random = Math.floor(Math.random() * (m - n + 1) + n);
        return random;
    }
});