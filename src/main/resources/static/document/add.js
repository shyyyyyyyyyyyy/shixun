$(() => {

    //提交按钮事件
    $("#submit-btn").click(() => {
        save(function (success) {
            if (success) {
                layui.use(() => {
                    let layer = layui.layer;
                    layer.msg("新增文档成功");
                });
            }
        });
    });

    // //查询班级，添加到下拉
    // initClassSelect();
});

//保存学生，cb回调函数
function save(cb) {
    let title = $("#title").val();
    let content = $("#content").val();
    let createdBy = $("#createdBy").val();

    let url = "/api/v1/documents"
    $.ajax({
        url,
        method: "post",
        dataType: "json",
        data: {
            title,
            content,
            createdBy
        },
        success(resp) {
            if (typeof cb === "function") {
                cb(resp.success);
            }
        }
    });
}

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
//             if (Array.isArray(resp)) {
//                 resp.forEach(it => {
//                     $("#classId").append("<option value='" + it.id + "'>" + it.name + "</option>");
//                 });
//             }
//         }
//     });
// }