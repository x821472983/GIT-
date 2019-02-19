var requestUtilParams = {
    action: {rsa: "rsa/generateRSAJsKey"},
    /**
     * @title:<h3> ajax默认请求参数 <h3>
     * @author: Enzo
     * @date: 2018-8-16 16:06
     * @params
     * @return
     **/
    ajaxDefaultSetting: {
        type: "post",
        async: true,
        dataType: "json"
    },
   
    /**
     * xhr公用请求
     * @param action
     * @param formData
     * @param settings
     * @returns {*}
     */
    xhr: function (action, formData, settings) {
        if (settings == null) {
            settings = {};
        }
        $.each(requestUtilParams.ajaxDefaultSetting, function (k, v) {
            if (settings[k] == null) {
                settings[k] = v;
            }
        });
        var thanXhr = $.ajax({
            url: action, type: settings.type,
            data: formData,
            dataType: settings.dataType,
            async: settings.async,
            success: function (res) {
                if (res.resultCode > 0) {
                    alert(res.message);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                requestUtilParams.ajaxError(XMLHttpRequest, textStatus, errorThrown);
            }
        });
        return thanXhr;
    },
    /**
     * 文件上传ajax请求
     *
     * @param formNode
     * @param resultNext
     */
    uploaderFile: function (formNode) {
        var formData = new FormData($(formNode)[0]);
        if ($(formNode).attr("enctype") == null) {
            $(formNode).attr("enctype", "multipart/form-data");
        }
        var xhr = $.ajax({
            url: $(formNode).attr("action"),
            type: "post",
            data: formData,
            contentType: false,
            processData: false,
            dataType: "json",
            success: function (res) {
                if (res.resultCode > 0) {
                    alert(res.message);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                requestUtilParams.ajaxError(XMLHttpRequest, textStatus, errorThrown);
                parent.$("iframe[src='/views/system/user/userList.jsp']").prop("src", '/views/system/user/userList.jsp');
                parent.layui.element.tabDelete('demo', parent.$(".layui-tab-title .layui-this").attr("lay-id"));
            }
        });
        return xhr;
    },
   
    /**
     * rsa加密
     * @param str
     * @returns {*}
     */
    rsaEncryption: function (str) {
        var encrypedValue = str;
        $.ajax({
            url: requestUtilParams.action.rsa,
            async: false,
            success: function (res) {
                //加密模
                var Modulus = res.split(';')[0];
                //公钥指数
                var public_exponent = res.split(';')[1];
                //通过模和公钥参数获取公钥
                var key = new RSAUtils.getKeyPair(public_exponent, "", Modulus);
                //颠倒密码的顺序，要不然后解密后会发现密码顺序是反的
                var reversedPwd = str.split("").reverse().join("");
                //对密码进行加密传输
                var encrypedPwd = RSAUtils.encryptedString(key, reversedPwd);
                encrypedValue = encrypedPwd;
            }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                requestUtilParams.ajaxError(XMLHttpRequest, textStatus, errorThrown);
            }
        })

        return encrypedValue;
    },
    /**
     * ajax请求失败返回错误信息
     * @param XMLHttpRequest 对象
     * @param textStatus 错误信息
     * @param errorThrown （可选）捕获的异常对象
     */
    ajaxError: function (XMLHttpRequest, textStatus, errorThrown) {
        var jsonError = XMLHttpRequest.responseJSON;
        if (jsonError != null) {
            alert(jsonError.message);
        } else {
            console.log(XMLHttpRequest);
            alert("未知错误");
        }
    }
}