$(() => {
    search();

    //给分页条添加事件
    $(".paginate>ul").on("click", "li", function () {
        let pageNo = $(".paginate").data("pageNo");//当前页码
        pageNo = parseInt(pageNo);
        let pages = $(".paginate").data("pages");//总页数
        pages = parseInt(pages);

        //事件已添加成功，判断用户点的是什么操作
        if ($(this).is(".first")) {//首页
            search(1);
        } else if ($(this).is(".last")) {//尾页
            search(pages);
        } else if ($(this).is(".prev")) {//上一页
            pageNo--;
            if (pageNo < 1) {
                pageNo = 1;
            }
            search(pageNo);
        } else if ($(this).is(".next")) {//下一页
            pageNo++;
            if (pageNo > pages) {
                pageNo = pages;
            }
            search(pageNo);
        } else {//数字页码
            let pageNo = parseInt($(this).text());
            search(pageNo);
        }
    });

    //给全选按钮添加事件
    $("#tbl #check-all").click(function () {
        let checked = $(this).prop("checked");
        $("#tbl tr>td:first-child>:checkbox").prop("checked", checked);
    });

    //点击行选中，与取消选中
    $("#tbl>tbody").on("click", "tr", function () {
        let $checkbox = $(this).children("td").first().children(":checkbox");
        let checked = $checkbox.prop("checked");
        $checkbox.prop("checked", !checked);
    });

    //点击复选框，停止向上冒泡，防止点击复选框时，触发行选中事件
    $("#tbl>tbody").on("click", "tr>td:first-child>:checkbox", (e) => {
        e.stopPropagation();//停止事件冒泡
    });

    //给查询按钮添加事件
    $(".action #search-btn").click(() => {
        search();
    });

    $(".action #reset-btn").click(() => {
        $(".search>form").trigger("reset");
    });

    //删除按钮事件
    $(".action #del-btn").click(() => {
        let checked = $("#tbl>tbody>tr>td:first-child>:checked");//所有选中的复选框
        let ids = [];//数组用于存储主键
        checked.each(function () {
            ids.push($(this).val());
        });

        layui.use(() => {
            let layer = layui.layer;
            if (ids.length === 0) {
                layer.msg("请选中您要删除的行");
            } else {
                layer.confirm("是否确认删除所有选中的行?", function () {
                   // console.log("cnm");
                    //如用户确认，则会执行此中代码
                    deleteByIds(ids);
                });
            }
        });
    });

    //新增按钮事件
    $(".action #add-btn").click(() => {
        //跳转到新页面
        //location.href = "/student/add";

        //弹出窗口
        layui.use(() => {
            let layer = layui.layer;
            layer.open({
                type: 2,//表示弹出iframe窗口
                title: "新增学生",
                area: ["500px", "550px"],
                content: "/document/add",
                btn: ["确定", "取消"],
                yes: function (index, layero, that) {//第1个按钮的回调，等同于btn1的回调
                    let win = layero.find('iframe')[0].contentWindow;
                    win.save((success) => {
                        if (success) {
                            layer.close(index);//关闭弹窗
                        } else {
                            layer.alert("新增文件失败");
                        }
                    });
                }
            });
        });
    });

    //修改按钮事件
    $(".action #edit-btn").click(() => {
        let checked = $("#tbl>tbody>tr>td:first-child>:checked");//所有选中的复选框
        let ids = [];//数组用于存储主键
        checked.each(function () {
            ids.push($(this).val());
        });

        layui.use(() => {
            if (ids.length === 0) {
                layer.msg("请选中您要修改的行");
            } else if (ids.length > 1) {
                layer.msg("您一次只能选中一行数据进行修改");
            } else {//只选中了一条记录
                let id = ids[0];//取出唯一选择主键
                //location.href = "/student/edit?id=" + id;
                layui.use(() => {
                    let layer = layui.layer;
                    layer.open({
                        type: 2,//弹出一个iframe窗口,
                        title: "修改学生",
                        area: ["500px", "550px"],
                        content: "/document/edit?id=" + id,
                        btn: ["确定", "取消"],
                        yes: function (index, layero, that) {
                            //向后台发出微服务请求，修改学生信息
                            //获取子页面的window对象
                            let win = layero.find("iframe")[0].contentWindow;
                            win.edit((success) => {
                                if (success) {
                                    layer.close(index);//关闭窗口
                                    layer.msg("修改文件信息成功");
                                    search();
                                } else {
                                    layer.alert("修改文件信息失败");
                                }
                            });
                        }
                    });
                });
            }
        });
    });

    // //查询所有班级，放到下拉选择框里
    // initClassSelect();


});

//向后台发出ajax请求，并获取响应数据内容。查询所有学生信息
function search(pageNo = 1, pageSize = 15) {
    //取出所有查询条件
    let title = $(".search #title").val();
    let content = $(".search #content").val();
    let createdBy = $(".search #createdBy").val();

    let url = "/api/v1/documents";
    $.ajax({
        url,
        method: "get",
        dataType: "json",
        data: {
            pageNo,
            pageSize,
            title, createdBy, content
            // "classEntity.id": classId
        },
        success(resp) {//回调函数
            //将数据渲染到页面上，执行dom操作   DOM：Document Object Model
            if (resp.success) {
                let data = resp.students;//学生信息数组
                let $tbody = $("#tbl>tbody");
                $tbody.empty();//把上次的数据内容清空

                data.forEach(it => {//it即遍历过程中的一个学生对象
                    let $tr = $("<tr>");//创建一个tr对象
                    $tr.append("<td><input type='checkbox' value='" + it.documentId + "'></td>");
                    $tr.append("<td>" + it.documentId + "</td>");
                    $tr.append("<td>" + it.title + "</td>");
                    $tr.append("<td>" + it.content + "</td>");
                    $tr.append("<td>" + it.createdBy + "</td>");

                    // /*班级名称*/
                    // $tr.append("<td>" + it.classEntity.name + "</td>");
                    // //$tr.append("<td>aaaa</td>");
                    //
                    // $tr.append("<td>" + it.phone + "</td>");
                    // $tr.append("<td>" + it.email + "</td>");
                    // $tr.append("<td>" + it.wechat + "</td>");

                    $tbody.append($tr);
                });

                //将分页信息缓存到页面上
                $(".paginate").data("pages", resp.pi.pages);
                $(".paginate").data("pageNo", resp.pi.pageNum);

                //将数字页码进行替换
                $(".paginate>ul>li:not(.fixed)").remove();
                for (let i = resp.pi.navigateFirstPage; i <= resp.pi.navigateLastPage; i++) {
                    let $li = $("<li><a href='javascript:void(0)'>" + i + "</a></li>");
                    if (i === resp.pi.pageNum) {
                        $li.addClass("current");
                    }
                    $(".paginate>ul>li.next").before($li);
                }
            }
        }
    });
}

//根据学生主键批量进行删除
function deleteByIds(ids) {
    console.log("nnn");
    //使用ajax发出请求，请求后台的微服务
    let url = "/api/v1/documents";
    console.log("ggg");
    $.ajax({
        url,
        method: "delete",
        dataType: "json",
        traditional: true,
        data: {
            ids
        },
        success(resp) {
            console.log("cao");
            search();//刷新表格
            layui.use(() => {
                console.log("yyy");
                let layer = layui.layer;
                layer.msg("删除操作成功，共删除" + resp.rows + "行");
            });
        }
    });
}
//
// //查询所有班级，初始化
// function initClassSelect() {
//     let url = "/api/v1/classes/names";
//     $.ajax({
//         url,
//         method: "get",
//         dataType: "json",
//         success(resp) {
//             //console.log(resp);
//             $("#classId").empty();
//             $("#classId").append("<option value=''>不限</option>");
//             if (Array.isArray(resp)) {
//                 resp.forEach(it => {
//                     $("#classId").append("<option value='" + it.id + "'>" + it.name + "</option>");
//                 });
//             }
//         }
//     });
// }