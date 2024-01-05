$(() => {
    //左侧功能导航菜单点击事件
    $(".nav").on("click", ">ul>li>a", function (e) {
        e.preventDefault();//阻止超链接的默认事件

        //设置当前项为选中项
        $(".nav>ul>li>a").removeClass("active");
        $(this).addClass("active");

        let title = $(this).text();//标题
        let url = $(this).attr("href");//地址
        let id = $(this).parent().attr("id");//标识

        layui.use(["element"], function () {
            let element = layui.element;

            let $tab = $(".layui-tab-title>li[lay-id=" + id + "]");
            if ($tab.length > 0) {//若选项卡已存在
                element.tabChange("tabs", id); //切换到选项卡
            } else { //新增一个选项卡
                element.tabAdd("tabs", {
                    id,
                    title,
                    content: "<iframe src='" + url + "'></iframe>",
                    change: true //是否添加完毕后即自动切换
                });
            }
        });
    });

    //注销按钮事件
    $("#logout-btn").click(() => {
        location.href = "/admin/logout";
    });
})