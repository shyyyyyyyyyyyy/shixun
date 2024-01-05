$(() => {
    //获取焦点
    $("#username").focus();

    //登录按钮点击
    $("#login-btn").click(() => {
        login();
    });

    //回车点击
    $(document).keydown(function (e) {
        if (e.keyCode === 13) {
            login();
        }
    });

    //验证码点击事件
    $(".captcha").click(function () {
        $(this).attr("src", "/admin/captcha?seed=" + new Date().getTime());
    });
});

//登录函数
function login() {
    let username = $("#username").val();
    let password = $("#password").val();
    let captcha = $("#captcha").val();

    let passed = true;
    //前端校验
    if (username === "") {
        layui.use(function () {
            let layer = layui.layer;
            layer.msg("用户名不可为空");
        });
        passed = false;
    } else {
        let pattern = /^\w{6,12}$/;
        if (!pattern.test(username)) {
            layui.use(function () {
                layer.msg("用户名必须是6到12位之间");
            });
            passed = false;
        }
    }
    //密码校验

    if (passed) {
        let url = "/admin/login"
        $.ajax({
            url,
            method: "post",
            dataType: "json",
            data: {
                username,
                password,
                captcha
            },
            success(resp) {
                if (resp.success) {
                    location.href = "/admin";//跳转到主页
                } else {
                    layui.use(() => {
                        let layer = layui.layer;
                        layer.alert(resp.error || "用户名或密码不正确");
                    })
                }
            }
        });
    }
}