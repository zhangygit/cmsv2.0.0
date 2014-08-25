function cheisNull() {
    if (document.getElementById("resumeName").value == "") {
        alert("请填写您的姓名！");
        document.getElementById("resumeName").focus();
        return false;
    }
    if (document.getElementById("birthDate").value == "") {
        alert("请填写您的出生年月！");
        document.getElementById("birthDate").focus();
        return false;
    }
    if (document.getElementById("recode").value == "") {
        alert("请填写您的最高学历！");
        document.getElementById("recode").focus();
        return false;
    }
    if (document.getElementById("major").value == "") {
        alert("请填写您的所学专业！");
        document.getElementById("major").focus();
        return false;
    }
    if (document.getElementById("persist").value == "") {
        alert("请填写您的获得时间！");
        document.getElementById("persist").focus();
        return false;
    }
    if (document.getElementById("phone").value == "") {
        alert("请填写您的联系电话！");
        document.getElementById("phone").focus();
        return false;
    }
    return true;
}

function checkNull() {
    if (document.getElementById("name").value == "") {
        alert("请填写您的姓名！");
        document.getElementById("name").focus();
        return false;
    }
    if (document.getElementById("comp").value == "") {
        alert("请填写您的公司名称！");
        document.getElementById("comp").focus();
        return false;
    }
    if (document.getElementById("tel").value == "") {
        alert("请填写您的电话！");
        document.getElementById("tel").focus();
        return false;
    }
    if (document.getElementById("mail").value != "") {
        var strReg = "";
        var r;
        var strText = document.getElementById("mail").value;
        strReg = /^\w+((-\w+)|(\.\w+))*\@{1}\w+\.{1}\w{2,4}(\.{0,1}\w{2}){0,1}/ig;
        r = strText.search(strReg);
        if (r == -1) {
            alert("邮箱格式错误!");
            document.getElementById("mail").focus();
            return false;
        }         
    }
    return true;
}

function checkMarkNull() {
    if (document.getElementById("name").value == "") {
        alert("请填写您的姓名！");
        document.getElementById("name").focus();
        return false;
    }
    if (document.getElementById("tel").value == "") {
        alert("请填写您的联系电话！");
        document.getElementById("tel").focus();
        return false;
    }
    if (document.getElementById("comqq").value == "") {
        alert("请填写您的联系QQ！");
        document.getElementById("comqq").focus();
        return false;
    }
    if (document.getElementById("ownsite").value == "") {
        alert("请填写您的网站！");
        document.getElementById("ownsite").focus();
        return false;
    }
    if (document.getElementById("source").value == "") {
        alert("请填写您的疑问！");
        document.getElementById("source").focus();
        return false;
    }
    return true;
}