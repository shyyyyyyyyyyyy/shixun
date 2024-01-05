$(() => {
    if (error) {//如果有错误信息，则提示
        layui.use(() => {
            let layer = layui.layer;
            layer.alert(error);
        });
    }

    // //对性别进行数据回显，对班级进行回显
    // if (stu) {
    //     $(":input[name=sex][value=" + stu.sex + "]").prop("checked", true);
    //     $("#classId").data("classId", stu.classEntity.id);//缓存班级编号
    // }
    //
    // //出生日期使用下拉选择组件
    // layui.use(() => {
    //     let laydate = layui.laydate;
    //     laydate.render({
    //         elem: "#birthday"
    //     });
    // });

    // //查询班级，添加到下拉
    // initClassSelect();
});

//修改学生，cb回调函数
function edit(cb) {
    // let documentId = doc.document_id;//主键
    let title = $("#title").val();
    let content = $("#content").val();
    let createdBy = $("#createdBy").val();

    let url = "/api/v1/documents"
    $.ajax({
        url,
        method: "patch",//部分修改
        dataType: "json",
        data: {
            title,
            content, createdBy
        },
        success(resp) {
            if (typeof cb === "function") {
                cb(resp.success);//回调
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
//
//                 //数据回显
//                 let classId = $("#classId").data("classId");
//                 if (classId) {
//                     $("#classId").val(classId);
//                 }
//             }
//         }
//     });
// }