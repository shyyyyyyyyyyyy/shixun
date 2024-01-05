$(() => {
    if (error) {//如果有错误信息，则提示
        layui.use(() => {
            let layer = layui.layer;
            layer.alert(error);
        });
    }

});

//修改学生，cb回调函数
function edit(cb) {
    let documentId = doc.documentId;//主键
    let title = $("#title").val();
    let content = $("#content").val();
    let createdBy = $("#createdBy").val();

    let url = "/api/v1/documents"
    $.ajax({
        url,
        method: "patch",//部分修改
        dataType: "json",
        data: {
            documentId,
            title,
            content,
            createdBy
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