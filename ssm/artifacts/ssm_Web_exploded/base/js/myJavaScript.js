/**
 * 毫秒时间转化为"年/月/日 时:分:秒"格式
 * @param time 毫秒数
 * @returns {string} 年/月/日 时:分:秒
 */
function formatTime(time) {
    if (time == null) return "";
    var t = new Date(time);
    var y = t.getFullYear();
    var m = t.getMonth() + 1;
    var d = t.getDate();
    var h = t.getHours();
    var mn = t.getMinutes();
    var sec = t.getSeconds();
    return y + "-" + (m > 9 ? m : "0" + m) + "-" + (d > 9 ? d : "0" + d) + " " + (h > 9 ? h : "0" + h)
        + ":" + (mn > 9 ? mn : "0" + mn) + ":" + (sec > 9 ? sec : "0" + sec);
}

/**
 * 过滤空字符串
 * @param str
 * @returns {string}
 */
function filterNull(str) {
    return str == null ? "" : str;
}


/**
 * 全选框
 * 全选按钮class为allPick,子选框按钮class为subPick
 */
function initAllPick() {
    $(".allPick").click(function () {
        $(".subPick").prop("checked", $(this).prop("checked"));
    });
}


/**
 * 初始化页码
 *
 * html结构:
 *
 <div class="layui-box layui-laypage"></div>
 <br>跳转至<input type="text" id="gotoPage">
 <button id="gotoBt">GO</button>
 *
 * @param page_info = {
            "cur_page": 1, //当前页码(必要)
            "total_page": 44, //总页码(必要)
            "page_size": 10, //页面大小
            "total_num": 112 //数据总量
        };
 */
function initPages(page_info, callback) {

    //如果页码过多，当前选中页前后页码的显示数量
    var leftAndRightNum = 2;
    //当页码数大于maxPage时开始分页
    var maxPage = 10;

    //获取容器
    var pageHolder = $(".layui-laypage");
    //判断页码总数是否过多
    var len = page_info.total_page > maxPage ? 2 * leftAndRightNum + 2 : page_info.total_page - 1;
    //循环添加相应数量的页码按钮
    for (var i = 1; i <= len; ++i) {
        pageHolder.append("<a class='laypage-num' href='javascript:;'>" + i + "</a>");
    }
    pageHolder.append("<a class='laypage-num' href='javascript:;'>" + page_info.total_page + "</a>");//添加最后一页
    $(".laypage-num:first").after("<a class='laypage-omit hidden' href='javascript:;'>...</a>");//左省略号
    $(".laypage-num:last").before("<a class='laypage-omit hidden' href='javascript:;'>...</a>");//右省略号
    pageHolder.append("<a class=\"layui-laypage-prev\" href='javascript:;'>上一页</a>" +
        "<a class=\"layui-laypage-next\" href='javascript:;'>下一页</a>");

    changePageNum(page_info.cur_page);//默认值

    /**
     * 改变页码数
     */
    function changePageNum(pageNum) {
        //回调函数
        if (callback != null)
            callback(pageNum);

        //删掉所有的
        $(".laypage-num").siblings(".laypage-num").removeClass("layui-laypage-curr");
        //当页面数过多时
        if (page_info.total_page > maxPage) {
            //改变的页码位于中间
            if (pageNum > 2 * leftAndRightNum && pageNum <= page_info.total_page - 2 * leftAndRightNum) {
                $(".laypage-omit").removeClass("hidden");
                var pageList = $(".laypage-num");
                for (var i = 1; i <= 1 + 2 * leftAndRightNum; ++i) {
                    pageList.eq(i).text(pageNum - leftAndRightNum + i - 1);
                }
                pageList.eq(leftAndRightNum + 1).addClass("layui-laypage-curr");//当前添加
            }
            //改变的页码位于左侧
            else if (pageNum <= 2 * leftAndRightNum) {
                $(".laypage-omit").eq(0).addClass("hidden");
                $(".laypage-omit").eq(1).removeClass("hidden");
                var pageList = $(".laypage-num");
                for (var i = 1; i <= 1 + 2 * leftAndRightNum; ++i) {
                    pageList.eq(i).text(i + 1);
                }
                pageList.eq(pageNum - 1).addClass("layui-laypage-curr");//当前添加
            }
            //改变的页码位于右侧
            else if (pageNum > page_info.total_page - 2 * leftAndRightNum) {
                $(".laypage-omit").eq(0).removeClass("hidden");
                $(".laypage-omit").eq(1).addClass("hidden");
                var pageList = $(".laypage-num");
                for (var i = 1; i <= 1 + 2 * leftAndRightNum; ++i) {
                    pageList.eq(i).text(page_info.total_page - 2 * leftAndRightNum - 2 + i);
                }
                pageList.eq(pageNum - page_info.total_page + 2 + 2 * leftAndRightNum).addClass("layui-laypage-curr");//当前添加
            }
        }
        //如果页码数较少
        else {
            $(".laypage-num").eq(pageNum - 1).addClass("layui-laypage-curr");//当前添加
        }
    }

    /**
     * 下一页
     */
    $(".layui-laypage>a.layui-laypage-next").click(function () {
        var currPage = parseInt($(".layui-laypage-curr").text());
        if (currPage != page_info.total_page)
            changePageNum(currPage + 1);
    });

    /**
     * 上一页
     */
    $(".layui-laypage>a.layui-laypage-prev").click(function () {
        var currPage = parseInt($(".layui-laypage-curr").text());
        if (currPage != 1)
            changePageNum(currPage - 1);
    });

    /**
     * 各页点击
     */
    $(".layui-laypage>a.laypage-num").click(function () {
        changePageNum($(this).text());
    });

    /**
     * 跳转至的页码
     */
    $("#gotoBt").click(function () {
        var pageNum = parseInt($("#gotoPage").val());
        if (pageNum < 1 || pageNum > page_info.total_page) {
            alert("请输入有效页码");
            return;
        }
        changePageNum(pageNum);
    });
}
