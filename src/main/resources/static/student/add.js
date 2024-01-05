$(() => {
    //出生日期使用下拉选择组件
    layui.use(() => {
        let laydate = layui.laydate;
        laydate.render({
            elem: "#birthday"
        });
    });

    //提交按钮事件
    $("#submit-btn").click(() => {
        save(function (success) {
            if (success) {
                layui.use(() => {
                    let layer = layui.layer;
                    layer.msg("新增学生成功");
                });
            }
        });
    });

    //查询班级，添加到下拉
    initClassSelect();
});

//保存学生，cb回调函数
function save(cb) {
    let stuId = $("#stuId").val();
    let name = $("#name").val();
    let sex = $(":input[name=sex]:checked").val();
    let pinyin = $("#pinyin").val();
    let birthday = $("#birthday").val();
    let phone = $("#phone").val();
    let email = $("#email").val();
    let wechat = $("#wechat").val();
    let classId = $("#classId").val();//班级编号

    let url = "/api/v1/students"
    $.ajax({
        url,
        method: "post",
        dataType: "json",
        data: {
            stuId,
            name, sex, pinyin, birthday, phone, email, wechat,
            "classEntity.id": classId
        },
        success(resp) {
            if (typeof cb === "function") {
                cb(resp.success);
            }
        }
    });
}

//查询所有班级，初始化
function initClassSelect() {
    let url = "/api/v1/classes/names";
    $.ajax({
        url,
        method: "get",
        dataType: "json",
        success(resp) {
            //console.log(resp);
            $("#classId").empty();
            if (Array.isArray(resp)) {
                resp.forEach(it => {
                    $("#classId").append("<option value='" + it.id + "'>" + it.name + "</option>");
                });
            }
        }
    });
}