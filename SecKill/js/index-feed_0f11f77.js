var SUIJI = function(e) {
    function t() {
        var t = e(I.selector),
        s = parseInt(I.width || 0, 10),
        d = ["<div " + (s > 0 ? 'style="width:' + s + 'px"': "") + ' class="' + x + '">', '<div class="hd">', I.label ? '<span class="suiji-label">' + I.label + "</span>": '<span class="suiji-icon">写随�?</span>', '<span class="arrow"></span>', "</div>", '<div class="bd normal-wrap">', '<div class="initText">', "输入想说的内�?...", "</div>", "</div>", '<div class="edit-wrap"></div>', "</div>"].join("");
        t.html(d),
        "edit" == I.status && (a(t), t.find(".normal-wrap").hide()),
        i(t)
    }
    function a(t) {
        var a = t,
        t = t.find(".edit-wrap"),
        i = I.items || "topic,friend,image,video",
        s = ['<div class="bd">', '<div class="sels">', -1 != i.indexOf("topic") ? '<a href="#" class="topic">话题</a>': "", -1 != i.indexOf("friend") ? '<a href="#" class="friend">朋友</a>': "", -1 != i.indexOf("image") ? '<a href="#" class="image">图片</a>': "", -1 != i.indexOf("video") ? '<a href="#" class="video">视频</a>': "", "</div>", '<div class="title"><textarea name="" id="suiji_title" class="title-area" init-value="点击设置标题">点击设置标题</textarea><div class="line"></div></div>', '<div class="content">', '<textarea name="" id="suiji_text" class="text-area" PlaceHolder="只能输入140个字"></textarea>', "</div>", '<div class="pics">', "</div>", '<div class="tags-wrap"><div class="tags-list"><input type="text" init-value="输入话题，空格确�?" id="tag-ipt"/></div>', '<div class="sel-tags"></div></div>', "</div>", '<div class="ft">', '<input type="button"  class="cancle" value="�? �?"/>', '<input type="button" class="submit" value="�? �?"/>', '<span class="num" style="display:none;">' + I.contentLimit + "�?</span>", "</div>"].join("");
        t.html(s),
        TY.loader("TY.util.userAction",
        function() {}),
        k = e(I.contentSelector),
        titleArea = e(I.titleSelector),
        I.obj = a.find("." + x),
        _ = t.find(I.numSelector),
        F = t.find(I.picsSelector),
        L = t.find(".submit"),
        N = t.find(".cancle"),
        j = t.find(".tags-wrap"),
        C = t.find(".sel-tags"),
        b()
    }
    function i(e) {
        var t = e.find(".normal-wrap"),
        i = e.find(".edit-wrap");
        e.find(".suiji-icon,.initText").click(function() {
            "none" == t.css("display") ? (i.hide(), t.show()) : d() && (0 == e.find("textarea").size() && a(e), i.show(), k.focus(), t.hide())
        })
    }
    function s() {
        k.val(""),
        S.empty(),
        F.html("");
        var e = I.obj;
        e.find(".normal-wrap").show(),
        e.find(".edit-wrap").hide(),
        Y.delAll()
    }
    function d() {
        return __global.isOnline() ? !0 : (TY.loginAction(), !1)
    }
    function n(e, t) {
        function a(a) {
            var i = e.val();
            a ? i == t && e.val("") : "" == i && e.val(t)
        }
        e.focus(function() {
            a(!0)
        }).blur(function() {
            a(!1)
        }),
        e.val(t),
        this.getVal = function() {
            return val = e.val(),
            val == t ? "": val
        },
        this.empty = function() {
            e.val(t)
        }
    }
    function l(e) {
        function t() {
            e.css({
                overflowY: "hidden"
            }),
            e.height(n),
            e.css({
                overflowY: "scroll"
            });
            var t = e.get(0).scrollHeight;
            l && t >= l ? (c || (e.css({
                width: i - 2 * s - d + "px"
            }), c = !0), e.height(l)) : (t = n > t ? n: t, e.height(t), c && (e.css({
                width: i - 2 * s + d + "px",
                padding: "0px " + s + "px"
            }), c = !1))
        }
        var a = e.parent(),
        i = a.width(),
        s = 15,
        d = 16,
        n = e.height(),
        l = I.maxHeight,
        c = !1;
        e.val(""),
        e.css({
            width: i - 2 * s + d + "px",
            padding: "0px " + s + "px"
        }),
        e.bind("paste cut keydown keyup focus blur", t)
    }
    function c(e, t) {
        var a = ['<div class="suiji-pop">', '<div class="pop-hd"><h3 class="title">提示</h3><div class="closeBtn"></div></div>', '<div class="pop-bd">' + A[e] + "</div>", '<div class="btnArea"><input type="button" value="确定" class="yes" /></div>', "</div>"].join("");
        TY.loader("TY.ui.popV2",
        function() {
            new TY.ui.popV2({
                isShowHead: !1,
                isShowCloseIco: !1,
                isShowButton: !1,
                isShowBorder: !1,
                isPadding: !1,
                type: "alert",
                body: a,
                width: "335px",
                height: "180px",
                yesHandler: function() {
                    t && t()
                },
                skinClass: "suiji-pop"
            })
        })
    }
    function r(e, t, a) {
        var i = {
            el: k,
            type: t || "success",
            msg: e || "发布成功",
            position: "midCenter",
            time: 3e3,
            callback: function() {
                a && a()
            }
        };
        "undefined" == typeof TY.ui.tips ? TY.loader("Qing.ui.tips",
        function() {
            new TY.ui.tips(i)
        }) : new TY.ui.tips(i)
    }
    function o(e) {
        for (var t = F,
        a = [], i = 0, s = e.length; s > i; i++) a.push('<a href="#" title="' + (e[i].name || "") + '"><img src="' + e[i].small + '" m="' + e[i].mid + '" b="' + e[i].big + '"/><em>×</em></a>');
        t.append(a.join(""))
    }
    function p() {
        var t = F;
        t.delegate("em", "click",
        function(t) {
            var a = e(t.target).parent();
            return c("delM",
            function() {
                a.remove()
            }),
            !1
        })
    }
    function m(e) {
        var t, a, i, s = k,
        d = TY.util.cursorPoint.get(s);
        a = s.val().substring(0, d.end),
        t = s.val().substring(d.end),
        i = "\n" + e.flashUrl + "\n" + e.title + "\n",
        s.val(a + i + t)
    }
    function u(e) {
        var t = J(e),
        a = e.getCNlen(),
        i = 0;
        if (t) {
            var s = $(t),
            d = B(e),
            n = 0;
            d && d.length > 0 && (n = 32 * d.length),
            i = a - s + n
        } else i = a;
        return i
    }
    function v(e, t, a) {
        e.bind("keydown keyup mouseup blur",
        function() {
            var i = Math.ceil((2 * t - u(e.val())) / 2) + "�?";
            a.html(i.replace("-", "超出"))
        })
    }
    function f(t) {
        var a, i, s, d, n, t = t || {},
        l = [];
        return a = u(t["params.title"] = S.getVal()),
        2 * I.titleLimit < a ? (c("maxT"), !1) : (i = u(t["params.body"] = k.val()), s = (d = F.find("img")).size(), I.picLimit < s ? (c("maxM"), !1) : (s && (d.each(function(t, a) {
            a = e(a),
            l.push({
                sUrl: a.attr("src"),
                lUrl: a.attr("b"),
                mUrl: a.attr("m")
            })
        }), t["params.image"] = TY.util.json(l)), 0 == i && 0 == s ? (c("empty"), !1) : (n = j.find(".tags-list span"), l = [], n.each(function(t, a) {
            l.push(e(a).text())
        }), l.length && (t["params.tag"] = TY.util.json(l)), "#新建话题#" == t.body ? (c("changeTopic"), !1) : !0)))
    }
    function h() {
        var t = {
            "params.appId": "qing",
            "params.sourceName": "天涯随记",
            "params.useraction": e("#user_action").val()
        },
        a = {},
        i = "http://www.tianya.cn/api/tw?method=tweet.ice.insert";
        d() && f(t) !== !1 && (L.attr({
            disabled: !0
        }).addClass("submitting").val("发布�?"), a = {
            url: i + "&_v=" + (new Date).getTime(),
            data: t,
            dataType: "json",
            headers: {
                Connection: "close"
            },
            type: "post",
            cache: !1,
            success: function(a) {
                1 == a.success ? (r(a.message || A.subSuc, "success"), setTimeout(function() {
                    s()
                },
                3e3), e.isFunction(I.submitCbk) && I.submitCbk(t)) : -15 == a.code ? TY.loader("TY.ui.nav",
                function() {
                    TY.checkLoginUser(3)
                }) : /未激�?/g.test(a.message) ? r(A.subErr_active.replace("{msg}", a.message), "warn") : r(a.message || A.subErr, "warn"),
                L.removeAttr("disabled").removeClass("submitting").val("发布")
            },
            failure: function() {
                L.removeAttr("disabled"),
                TY.util.console("suiji post failure")
            }
        },
        U ? new TY.util.proxy({
            name: "tianyacnProxy",
            path: "http://www.tianya.cn/proxy.html",
            times: 10
        },
        "ajax", a) : e.ajax(a))
    }
    function g() {
        if (d()) {
            var t = {
                "params.page": 1,
                "params.pageSize": I.tagsNum
            },
            a = "http://www.tianya.cn/api/tw?method=topic.ice.getUserTag",
            i = {
                url: a + "&_v=" + (new Date).getTime(),
                data: t,
                dataType: "json",
                headers: {
                    Connection: "close"
                },
                type: "post",
                cache: !1,
                success: function(e) {
                    if (M = !0, e && 1 == e.success && 1 == e.code && e.data && e.data.items) {
                        for (var t = e.data.items,
                        a = [], i = 0, s = t.length; s > i; i++) a.push("<span>" + t[i] + "</span>");
                        C.html(a.join("")),
                        setTimeout(function() {
                            C.slideDown()
                        },
                        0)
                    }
                },
                failure: function() {
                    M = !1,
                    TY.util.console("getUserTag failure")
                }
            };
            C.find("span").size() ? "none" == C.css("display") && C.slideDown() : M || (M = !0, U ? new TY.util.proxy({
                name: "tianyacnProxy",
                path: "http://www.tianya.cn/proxy.html",
                times: 10
            },
            "ajax", i) : e.ajax(i))
        }
    }
    function w(e, t) {
        return t = t || /(^\s+)|(\s+$)|<|>|\"|\'/g,
        e.replace(t, "")
    }
    function T() {
        function t(e) {
            for (var t = 0,
            a = c.length; a > t; t++) if (c[t] == e) return ! 0;
            return ! 1
        }
        function a(a) {
            if (p = !0, a = a || o.getVal(), a = w(a).replace(/#/g, ""), l >= d) return r(A.MaxTag),
            void(p = !1);
            if (a) {
                if (t(a)) return s.val(""),
                r(A.hadTag),
                void(p = !1);
                c.push(a),
                e('<span i="' + l + '" >' + a + "</span> ").insertBefore(s),
                s.val(""),
                l++
            }
            p = !1
        }
        function i() {
            l = 0,
            c = [],
            e(".tags-list span").remove(),
            I.label && a(I.label)
        }
        var s = j.find("input"),
        d = I.tagsNum,
        l = 0,
        c = [],
        o = new n(s, s.attr("init-value")),
        p = !1;
        s.bind("keydown",
        function(e) { (32 === e.keyCode || 13 === e.keyCode) && (p || a())
        }).bind("blur",
        function() {
            a()
        });
        var m = !1;
        j.click(function() {
            s.focus(),
            m = !0,
            g()
        }),
        j.delegate(".tags-list span", "click",
        function() {
            var t = e(this),
            a = t.attr("s");
            c.splice(a, 1),
            t.remove(),
            l--
        }).hover(function() {
            m && C.show()
        },
        function() {
            C.slideUp(),
            m = !1
        }),
        C.delegate("span", "click",
        function() {
            var t = e(this);
            return a(t.html()),
            !1
        }),
        I.label && a(I.label),
        this.add = a,
        this.delAll = i
    }
    function b() {
        var t = I.obj,
        a = t.find(".sels a");
        a.each(function(t, a) {
            var i = a.className;
            i && P[i] && (a = e(a), a.bind("click",
            function() {
                return P[i](a, I.plugins[i]),
                !1
            }))
        }),
        p();
        var i = e(I.titleSelector);
        new l(i),
        new l(k),
        S = new n(i, i.attr("init-value")),
        k.focus(d),
        v(k, I.contentLimit, _),
        L.click(h).hover(function() {
            e(this).addClass("submit-hover")
        },
        function() {
            e(this).removeClass("submit-hover")
        }),
        N.click(function() {
            S.getVal() || u(k.val()) || F.find("img").size() ? c("cancle", s) : s()
        }),
        Y = new T
    }
    function y(a) {
        e.extend(I, a),
        t()
    }
    var I = {
        obj: null,
        titleSelector: "#suiji_title",
        contentSelector: "#suiji_text",
        picsSelector: ".pics",
        numSelector: ".num",
        contentLimit: 500,
        titleLimit: 20,
        picLimit: 9,
        plugins: {},
        submitCbk: function() {},
        maxHeight: 470,
        tagsNum: 5
    },
    x = "suiji-report",
    k = null,
    j = null,
    C = null,
    S = null,
    _ = null,
    L = null,
    N = null,
    Y = null,
    U = "www.tianya.cn" == location.host ? !1 : !0,
    P = {
        friend: function(e, t) {
            TY.loader("TY.ui.friendV2",
            function() {
                t = new TY.ui.friend({
                    textArea: k,
                    top: 0,
                    left: 0,
                    isClickHide: !0,
                    el: e
                })
            })
        },
        topic: function(e, t) {
            TY.loader("TY.ui.topicV2",
            function() {
                t = new TY.ui.Topic({
                    el: e,
                    textArea: k,
                    top: 26,
                    left: 0
                })
            })
        },
        image: function(e, t) {
            TY.loader("TY.ui.photoV3",
            function() {
                TY.util.cursorPoint.bindCursorPoint(I.obj.find("textarea").get(0)),
                t = new TY.ui.photo({
                    el: e,
                    noPreview: !0,
                    isSelHD: !0,
                    uploadPic: {
                        callback: o
                    },
                    newSociPic: {
                        callback: o
                    },
                    picNum: I.picLimit,
                    top: 28,
                    left: -20
                })
            })
        },
        video: function(e, t) {
            TY.loader("TY.ui.videoV2",
            function() {
                t = new TY.ui.video({
                    el: e,
                    textarea: k,
                    callback: m,
                    top: 2,
                    left: -20
                })
            })
        }
    },
    A = {
        delR: "亲，你真的要<em>删除</em>你的随记吗？",
        delM: "亲，你真的要<em>删除</em>这张图片吗？",
        dropR: "亲，你真的要<em>放弃编辑</em>图片吗？",
        maxM: "亲，朢�多可以上�?<em>" + I.picLimit + "</em>张图片！",
        maxT: "亲，标题超过字数限制(" + I.titleLimit + "�?)�?,精炼下标题哦.",
        maxC: "亲，内容超过字数限制(" + I.contentLimit + "�?)�?,精炼下内容哦.",
        empty: "亲，不写点什么吗�?",
        cancle: "亲，你真的要取消这次编辑吗？",
        changeTopic: "亲，请修改一下话�?:)",
        subSuc: "发布成功�?",
        subErr: "发布失败�?",
        subErr_active: "<p><span>抱歉，未濢�活用户不可发布随记！请点�?:</span><a href='http://passport.tianya.cn/identity/open.do'>立即濢��?</a></p>",
        MaxTag: "亲，朢�多可以添�?<em>" + I.tagsNum + "</em>个话题！",
        hadTag: "亲，您已经添加了该话�?"
    },
    F = null,
    $ = function(e) {
        if ("undefined" == typeof e) return 0;
        var t = e.match(/[^\x00-\x80]/g);
        return e.length + (t ? t.length: 0)
    },
    J = function(e) {
        var t = e.match(/https?:\/\/[a-zA-Z0-9]+(\.[a-zA-Z0-9]+)+([-_A-Z0-9a-z\$\.\+\!\*\/,:;@&=\?\~\#\%]*)*/gi);
        return t ? t.join("") : null
    },
    B = function(e) {
        var t = e.match(/https?:\/\/[a-zA-Z0-9]+(\.[a-zA-Z0-9]+)+([-_A-Z0-9a-z\$\.\+\!\*\/,:;@&=\?\~\#\%]*)*/gi);
        return t
    },
    M = !1;
    return {
        init: y
    }
} (TY); !
function(e) {
    SuiJi_template = {
        FeedNavTpl: ['<div class="sj-feed-nav clearfix">', "<% if showFilter %>", '<div class="feed-nav-filter">', '<a class="filter-state" href="javascript:void(0);">', '<span class="state-text">网友动��?</span>', '<i class="state-icon"></i>', "</a>", '<div class="filter-option">', '<a href="javascript:void(0);" class="option-item selected" data-filter=\'{"filterTwitter":1,"filterQing":1,"filterBbs":1,"filterBlog":1}\'>网友动��?</a>', '<a href="javascript:void(0);" class="option-item" data-filter=\'{"filterQing":1}\'>随记</a>', '<a href="javascript:void(0);" class="option-item" data-filter=\'{"filterBbs":1}\'>论坛</a>', '<a href="javascript:void(0);" class="option-item" data-filter=\'{"filterBlog":1}\'>博客</a>', '<a href="javascript:void(0);" class="option-item" data-filter=\'{"filterOther":1}\'>其他</a>', "</div>", "</div>", "<% /if %>", '<div class="feed-nav-oper">', "<% if showOperNew %>", '<a class="oper-new curr" href="javascript:void(0);" title="朢��?" data-type="1">朢��?</a>', '<span class="split-line"></span>', "<% /if %>", "<% if showOperHot %>", '<a class="oper-hot" href="javascript:void(0);" title="热门" data-type="2">热门</a>', '<span class="split-line"></span>', "<% /if %>", "<% if showOperInterest %>", '<a class="oper-interest" href="javascript:void(0);" title="兴趣订阅">兴趣订阅</a>', '<span class="split-line"></span>', "<% /if %>", '<a class="oper-refresh" href="javascript:void(0);" title="刷新"></a>', "</div>", "</div>"].join(""),
        FeedContentTpl: ['<div class="sj-feed-content"></div>'].join(""),
        FeedLoadTpl: ['<div class="sj-feed-load">查看更多</div>'].join(""),
        FeedPageTpl: ['<div class="sj-feed-page clearfix"></div>'].join(""),
        FeedItemTpl: ["<% if share %>", '<div class="feed-content-item" data-id="<% id %>" data-uid="<% userId %>" data-uname="<% userName %>" data-app="<% appId %>" data-shareid="<% share.id %>" data-shareuid="<% share.userId %>" data-shareuname="<% share.userName || "" %>">', "<% else %>", '<div class="feed-content-forward">', "<% /if %>", '<div class="f-i-hd">', '<div class="hd-pic">', '<a href="http://www.tianya.cn/<% userId %>"><img src="http://tx.tianyaui.com/logo/small/<% userId %>" title="<% userName %>" alt="<% userName %>" /></a>', "</div>", '<div class="hd-info">', '<div class="info-name">', '<a href="http://www.tianya.cn/<% userId %>" uid="<% userId %>" class="js-vip-check" title="<% userName %>"><% userName %></a>', "</div>", '<div class="info-from">', '<span><a href="<% getSourceLink(id, userId, url) %>" target="_blank"><% renderPostTime(postTime) %></a></span><span><% from %></span>', "</div>", "</div>", "</div>", '<div class="f-i-bd">', '<div class="bd-text"><% include("SjTextTpl") %></div>', "<% if media && media.image && media.image.length %>", '<div class="bd-media"><% include("SjPictureTpl") %></div>', "<% /if %>", "<% if share && share.id %>", '<div class="bd-quote"><% share.id === -1 ? "原文内容已被删除" : include("FeedItemTpl", share) %></div>', "<% /if %>", '<div class="bd-operate sj-operate">', '<div class="handle">', "<% if share && userId != currentUserId() && checkPower() %>", '<input class="ad-delete-ck" type="checkbox" value="<% id %>,<% userId %>" />', '<a class="ad-delete" href="javascript:void(0);" title="删除" data-delkey="<% id %>,<% userId %>">删除</a>', '<span class="split-line">|</span>', "<% /if %>", "<% if share && userId == currentUserId() %>", '<a class="op-delete" href="javascript:void(0);" title="删除" data-id="<% id %>" data-uid="<% userId %>">删除</a>', '<span class="split-line">|</span>', "<% /if %>", '<a class="op-collect" href="javascript:void(0);" title="收藏" data-id="<% id %>" data-uid="<% userId %>" data-app="<% appId %>" data-key="<% userId %>_<% id %>">收藏</a>', '<span class="split-line">|</span>', '<a class="op-forward" <% if share %>href="javascript:void(0);"<% else %>href="<% getSourceLink(id, userId, url) %>" target="_blank"<% /if %> title="转发" data-id="<% id %>" data-uid="<% userId %>" data-count="<% shareCount %>">转发<span><% shareCount ? "(" + shareCount + ")" : "" %></span></a>', '<span class="split-line">|</span>', '<a class="op-comment" <% if share %>href="javascript:void(0);"<% else %>href="<% getSourceLink(id, userId, url) %>" target="_blank"<% /if %> title="评论" data-id="<% id %>" data-uid="<% userId %>" data-app="<% appId %>" data-count="<% replyCount %>">评论<span><% replyCount ? "(" + replyCount + ")" : "" %></span></a>', '<span class="split-line">|</span>', '<a class="op-praise" href="javascript:void(0);" title="�?" data-id="<% id %>" data-uid="<% userId %>" data-key="<% userId %>_<% id %>" data-count="<% likeCount %>"><i class="praise-icon"></i><span><% likeCount ? "(" + likeCount + ")" : "" %></span></a>', "</div>", "<% if media && media.tag && media.tag.length %>", '<div class="info">', '<div class="tags">', "<% each media.tag %>", '<a href="http://www.tianya.cn/t/tag/<% media.tag[$index] %>" title="<% media.tag[$index] %>" target="_blank"><% media.tag[$index] %></a>', "<% /each %>", "</div>", "</div>", "<% /if %>", "</div>", "<% if share %>", '<div class="bd-comment sj-comment" data-type="comment">', '<div class="comment-wrapper">', '<div class="comment-title">', '<div class="cm-prompt">还可输入<span>140</span>�?</div><div class="cm-title">评论</div>', "</div>", '<div class="comment-area">', '<textarea class="cm-textarea"></textarea><input class="cm-button" type="button" title="发布" value="发布" />', "</div>", '<div class="comment-toolbar"><label><input class="cm-checkbox" type="checkbox" />同时转发</label></div>', "</div>", '<div class="comment-list" data-morelink="<% getSourceLink(id, userId, url) %>"></div>', "</div>", '<div class="bd-comment sj-comment" data-type="forward">', '<div class="comment-wrapper">', '<div class="comment-title">', '<div class="cm-prompt">还可输入<span>140</span>�?</div><div class="cm-title">转发</div>', "</div>", '<div class="comment-area">', '<textarea class="cm-textarea" data-text="<% share.id ? encodeText(" || @" + userName + ":" + oTitle) : "" %>"></textarea><input class="cm-button" type="button" title="发布" value="发布" />', "</div>", "</div>", '<div class="comment-list" data-morelink="<% getSourceLink(id, userId, url) %>"></div>', "</div>", "<% /if %>", "</div>", "</div>"].join(""),
        FeedAdminOperationTpl: ['<div class="sj-feed-admin clearfix">', '<span class="multi-delete">', '<label><input type="checkbox" class="ad-delete-ckall" />全��?</label>', '<input type="button" class="ad-delete-btn" value="删除" />', "</span>", "</div>"].join(""),
        SjTextTpl: ['<% if appId === "twitter" %>', '<div class="article">', "<% title %>", "</div>", '<% else if appId === "qing" %>', "<% if title %>", '<h4 class="title"><% title %></h4>', "<% /if %>", '<div class="article">', "<% if removeHtmlTags(body).getCNlen() > 280 %>", '<div class="article-cut">', '<% renderSuijiContent(body) %>&nbsp;<a class="at-toggle" href="javascript:void(0);" title="展开">展开<i class="toggle-icon open"></i></a>', "</div>", '<div class="article-total">', '<% replaceHtml(body, "&lt;br&nbsp;/&gt;", "<br />") %>&nbsp;<a class="at-toggle" href="javascript:void(0);" title="收起">收起<i class="toggle-icon close"></i></a>', "</div>", "<% else %>", '<% replaceHtml(body, "&lt;br&nbsp;/&gt;", "<br />") %>', "<% /if %>", "</div>", '<% else if appId === "bbs" || appId === "blog" || appId === "groups" %>', '<h4 class="title"><% title %></h4>', '<div class="article"><% body && replaceHtml(removeHtmlTags(body), "&lt;br&nbsp;/&gt;", "<br />") %>...<a class="at-link" href="<% url %>" target="_blank" title="查看原文">查看原文&gt;&gt;</a></div>', "<% if mediaFlag && 1024 ==1024 %>", "<% each media.tvideo %>", '<span videoid="<% media.tvideo[$index].videoId %>" v="<% media.tvideo[$index].videoUrl %>" class="short-video" style="width: <%media.tvideo[$index].width >=480 ? 480 :media.tvideo[$index].width %>px; height: <% media.tvideo[$index].height ||480 %>px;">', '<img original="<% media.tvideo[$index].thumbUrl %>" style="width:100%;height:100%;" src="<% media.tvideo[$index].thumbUrl %>"><span class="play-btn"></span><div class="video"></div>', "</span>", "<% /each %>", "<% /if %>", "<% else %>", '<div class="article">', "<% title %>", "</div>", "<% /if %>"].join(""),
        SjPictureTpl: ['<div class="picture">', '<div class="picture-preview">', '<ul class="preview-list <% media.image.length === 1 ? "single" : "multiple" %> clearfix">', "<% limitLength(media.image, 9) %>", "<% each media.image %>", '<li><img class="big-cursor" src="<% media.image.length === 1 ? media.image[$index].mUrl : media.image[$index].sUrl %>" data-surl="<% media.image[$index].sUrl %>" data-murl="<% media.image[$index].mUrl %>" data-lurl="<% media.image[$index].lUrl %>" data-idx="<% $index %>" /><% checkGif(media.image[$index].sUrl) %></li>', "<% /each %>", "</ul>", "</div>", '<div class="picture-view"></div>', "</div>"].join(""),
        SjPictureViewTpl: ['<div class="view-oper"><a class="retract" href="javascript:void(0);" title="收起">收起</a><span class="split-line">|</span><a class="original" href="<% lurl %>" target="_blank" title="查看原图">查看原图</a></div>', '<div class="view-show">', '<% include("SjPictureViewShowTpl") %>', "</div>", "<% if len > 1 %>", '<div class="view-choose" data-len="<% len %>">', "<% each picList %>", '<a class="choose-item <% $index == idx ? "curr" : "" %>" href="javascript:void(0);"><img src="<% picList[$index].attr("src") %>" data-surl="<% picList[$index].data("surl") %>" data-murl="<% picList[$index].data("murl") %>" data-lurl="<% picList[$index].data("lurl") %>" data-idx="<% $index %>" /></a>', "<% /each %>", "</div>", "<% /if %>"].join(""),
        SjPictureViewShowTpl: ['<div class="view-show-img">', '<img class="small-cursor" src="<% murl %>" />', "</div>", "<% if len > 1 %>", '<% if idx != 0 %><div class="view-show-prev" title="上一�?"></div><% /if %>', '<% if idx != len - 1 %><div class="view-show-next" title="下一�?"></div><% /if %>', "<% /if %>"].join(""),
        SjCommentItemTpl: ['<div class="comment-item" data-id="<% id %>">', '<div class="cm-pic"><a href="http://www.tianya.cn/<% userId %>"><img src="http://tx.tianyaui.com/logo/small/<% userId %>" title="<% userName %>" alt="<% userName %>" /></a></div>', '<div class="cm-info">', '<p class="info-name"><a href="http://www.tianya.cn/<% userId %>" title="<% userName %>"><% userName %></a></p>', '<div class="info-content"><% word %><span class="time">(<% renderPostTime(time) %>)</span></div>', "<% if toUid %>", '<p class="info-oper">', "<% if userId != currentUserId() && checkPower() %>", '<a class="cm-delete-ad" href="javascript:void(0);" title="删除" data-id="<% id %>" data-uid="<% userId %>" data-touid="<% toUid %>">删除</a>', "<% /if %>", "<% if userId == currentUserId() || actUserId == currentUserId() %>", '<a class="cm-delete" href="javascript:void(0);" title="删除" data-id="<% id %>" data-uid="<% userId %>" data-touid="<% toUid %>">删除</a>', "<% /if %>", '<a class="cm-reply" href="javascript:void(0);" data-uid="<% userId %>" data-uname="<% userName %>" title="回复">回复</a>', "</p>", "<% /if %>", "</div>", "</div>"].join(""),
        SjCommentReplyAreaTpl: ['<div class="info-reply">', '<textarea class="re-textarea" maxlength="140" data-uid="<% uid %>" data-uname="<% uname %>"><% text %></textarea>', '<input class="re-button" type="button" title="回复" value="回复" />', "</div>"].join(""),
        ReadDetailTpl: ['<div class="sj-main-nav">', "<h2>随记</h2>", "</div>", '<div class="sj-main-content" data-id="<% id %>" data-uid="<% userId %>" data-uname="<% userName %>" data-app="<% appId %>" data-shareid="<% share.id %>" data-shareuid="<% share.userId %>" data-shareuname="<% share.userName || "" %>">', '<div class="m-c-hd">', '<% if appId === "qing" && title %>', '<h1 class="art-title"><% title %></h1>', "<% /if %>", '<p class="art-info"><% renderPostTime(postTime) %>&#12288;&#12288;<% from %></p>', "</div>", '<div class="m-c-bd">', '<div class="art-content">', '<% if appId === "qing" %><% replaceHtml(body, "&lt;br&nbsp;/&gt;", "<br />") %><% /if %>', '<% if appId === "twitter" %><% title %><% /if %>', "<% each media.image %>", '<img class="content-pic" src="<% media.image[$index].lUrl %>" />', "<% /each %>", "</div>", "<% if share && share.id %>", '<div class="art-quote"><% share.id === -1 ? "原文内容已被删除" : include("FeedItemTpl", share) %></div>', "<% /if %>", '<div class="art-operate sj-operate">', '<div class="handle">', "<% if userId != currentUserId() && checkPower() %>", '<a class="ad-delete" href="javascript:void(0);" title="删除" data-delkey="<% id %>,<% userId %>">删除</a>', '<span class="split-line">|</span>', "<% /if %>", "<% if userId == currentUserId() %>", '<a class="op-delete" href="javascript:void(0);" title="删除" data-id="<% id %>" data-uid="<% userId %>">删除</a>', '<span class="split-line">|</span>', "<% /if %>", '<a class="op-collect" href="javascript:void(0);" title="收藏" data-id="<% id %>" data-uid="<% userId %>" data-app="<% appId %>" data-key="<% userId %>_<% id %>">收藏</a>', '<span class="split-line">|</span>', '<a class="op-forward" <% if share %>href="javascript:void(0);"<% else %>href="<% getSourceLink(id, userId, url) %>" target="_blank"<% /if %> title="转发" data-id="<% id %>" data-uid="<% userId %>" data-count="<% shareCount %>">转发<span><% shareCount ? "(" + shareCount + ")" : "" %></span></a>', '<span class="split-line">|</span>', '<a class="op-comment" <% if share %>href="javascript:void(0);"<% else %>href="<% getSourceLink(id, userId, url) %>" target="_blank"<% /if %> title="评论" data-id="<% id %>" data-uid="<% userId %>" data-app="<% appId %>" data-count="<% replyCount %>">评论<span><% replyCount ? "(" + replyCount + ")" : "" %></span></a>', '<span class="split-line">|</span>', '<a class="op-praise" href="javascript:void(0);" title="�?" data-id="<% id %>" data-uid="<% userId %>" data-key="<% userId %>_<% id %>" data-count="<% likeCount %>"><i class="praise-icon"></i><span><% likeCount ? "(" + likeCount + ")" : "" %></span></a>', "</div>", "<% if media && media.tag && media.tag.length %>", '<div class="info">', '<div class="tags">', "<% each media.tag %>", '<a href="http://www.tianya.cn/t/tag/<% media.tag[$index] %>" title="<% media.tag[$index] %>" target="_blank"><% media.tag[$index] %></a>', "<% /each %>", "</div>", "</div>", "<% /if %>", "</div>", '<div class="art-comment sj-comment" data-type="comment" style="display:block;">', '<div class="comment-wrapper">', '<div class="comment-title">', '<div class="cm-prompt">还可输入<span>140</span>�?</div><div class="cm-title">评论</div>', "</div>", '<div class="comment-area">', '<textarea class="cm-textarea"></textarea><input class="cm-button" type="button" title="发布" value="发布" />', "</div>", '<div class="comment-toolbar"><label><input class="cm-checkbox" type="checkbox" />同时转发</label></div>', "</div>", '<div class="comment-list"></div>', '<div class="comment-page clearfix"></div>', "</div>", '<div class="art-comment sj-comment" data-type="forward">', '<div class="comment-wrapper">', '<div class="comment-title">', '<div class="cm-prompt">还可输入<span>140</span>�?</div><div class="cm-title">转发</div>', "</div>", '<div class="comment-area">', '<textarea class="cm-textarea" data-text="<% share.id ? encodeText(" || @" + userName + ":" + oTitle) : "" %>"></textarea><input class="cm-button" type="button" title="发布" value="发布" />', "</div>", "</div>", '<div class="comment-list"></div>', '<div class="comment-page clearfix"></div>', "</div>", "</div>", "</div>"].join(""),
        SelfNavTpl: ['<div class="suiji-self-nav">', '<ul class="self-nav-tab clearfix">', '<li class="tab-item <% flag === "my_suiji" ? "on" : "" %>"><a href="http://www.tianya.cn/t/post">我的随记</a></li>', '<li class="tab-item <% flag === "my_reply" ? "on" : "" %>"><a href="http://www.tianya.cn/t/myReply">我的评论</a></li>', '<li class="tab-item <% flag === "reply_me" ? "on" : "" %>"><a href="http://www.tianya.cn/t/reply">评论我的</a></li>', '<li class="tab-item <% flag === "at_me" ? "on" : "" %>"><a href="http://www.tianya.cn/t/atme">提到我的</a></li>', '<li class="tab-item <% flag === "my_collect" ? "on" : "" %>"><a href="http://www.tianya.cn/t/like">我的收藏</a></li>', '<li class="tab-item <% flag === "praise_me" ? "on" : "" %>"><a href="http://www.tianya.cn/t/praise">收到的顶</a></li>', "</ul>", "</div>"].join(""),
        MyReplyTpl: ['<div class="feed-content-item" data-id="<% tweet.id %>" data-uid="<% tweet.userId %>" data-shareid="<% tweet.share ? tweet.share.id : "" %>" data-shareuid="<% tweet.share ? tweet.share.userId : "" %>">', '<div class="f-i-bd">', '<div class="bd-text">', '<div class="article"><% word %></div>', "</div>", '<div class="bd-quote">', "<% if tweet.hotCount === -1 && tweet.likeCount === -1 && tweet.hotCount === -1 %>", "<% tweet.title %>", "<% else %>", '<div class="feed-content-reply">', '<div class="f-i-hd">', '<div class="hd-pic">', '<a href="http://www.tianya.cn/<% tweet.userId %>"><img src="http://tx.tianyaui.com/logo/small/<% tweet.userId %>" title="<% tweet.userName %>" alt="<% tweet.userName %>" /></a>', "</div>", '<div class="hd-info">', '<div class="info-name">', '<a href="http://www.tianya.cn/<% tweet.userId %>" uid="<% tweet.userId %>" class="js-vip-check" title="<% tweet.userName %>"><% tweet.userName %></a>', "</div>", '<div class="info-from">', '<span><a href="<% getSourceLink(tweet.id, tweet.userId, tweet.url) %>" target="_blank"><% renderPostTime(tweet.postTime) %></a></span><span><% tweet.from %></span>', "</div>", "</div>", "</div>", '<div class="f-i-bd">', '<div class="bd-text"><% include("SjTextTpl", tweet) %></div>', "<% if tweet.media && tweet.media.image && tweet.media.image.length %>", '<div class="bd-media"><% include("SjPictureTpl", tweet) %></div>', "<% /if %>", '<div class="bd-operate sj-operate">', '<div class="handle">', '<a class="op-collect" href="javascript:void(0);" title="收藏" data-id="<% tweet.id %>" data-uid="<% tweet.userId %>" data-app="<% tweet.appId %>" data-key="<% tweet.userId %>_<% tweet.id %>">收藏</a>', '<span class="split-line">|</span>', '<a class="op-forward" href="<% getSourceLink(tweet.id, tweet.userId, tweet.url) %>" target="_blank" title="转发" data-id="<% tweet.id %>" data-uid="<% tweet.userId %>" data-count="<% tweet.shareCount %>">转发<span><% tweet.shareCount ? "(" + tweet.shareCount + ")" : "" %></span></a>', '<span class="split-line">|</span>', '<a class="op-comment" href="<% getSourceLink(tweet.id, tweet.userId, tweet.url) %>" target="_blank" title="评论" data-id="<% tweet.id %>" data-uid="<% tweet.userId %>" data-app="<% tweet.appId %>" data-count="<% tweet.replyCount %>">评论<span><% tweet.replyCount ? "(" + tweet.replyCount + ")" : "" %></span></a>', '<span class="split-line">|</span>', '<a class="op-praise" href="javascript:void(0);" title="�?" data-id="<% tweet.id %>" data-uid="<% tweet.userId %>" data-key="<% tweet.userId %>_<% tweet.id %>" data-count="<% tweet.likeCount %>"><i class="praise-icon"></i><span><% tweet.likeCount ? "(" + tweet.likeCount + ")" : "" %></span></a>', "</div>", "<% if tweet.media && tweet.media.tag && tweet.media.tag.length %>", '<div class="info">', '<div class="tags">', "<% each tweet.media.tag %>", '<a href="http://www.tianya.cn/t/tag/<% tweet.media.tag[$index] %>" title="<% tweet.media.tag[$index] %>" target="_blank"><% tweet.media.tag[$index] %></a>', "<% /each %>", "</div>", "</div>", "<% /if %>", "</div>", "</div>", "</div>", "<% /if %>", "</div>", '<div class="bd-operate sj-operate">', '<div class="handle">', '<a class="cm-delete" href="javascript:void(0);" title="删除" data-id="<% id %>" data-touid="<% toUid %>">删除</a>', "</div>", '<div class="info">', '<div class="from">', "<span><% renderPostTime(time) %></span>&nbsp;&nbsp;<span><% from %></span>", "</div>", "</div>", "</div>", "</div>", "</div>"].join(""),
        ReplyMeTpl: ['<div class="feed-content-item" data-id="<% tweet.id %>" data-uid="<% tweet.userId %>" data-uname="<% tweet.userName %>" data-app="<% tweet.appId %>" data-shareid="<% tweet.share ? tweet.share.id : "" %>" data-shareuid="<% tweet.share ? tweet.share.userId : "" %>" data-shareuname="<% tweet.share ? tweet.share.userName : "" %>">', '<div class="f-i-hd">', '<div class="hd-pic">', '<a href="http://www.tianya.cn/<% userId %>"><img src="http://tx.tianyaui.com/logo/small/<% userId %>" title="<% userName %>" alt="<% userName %>" /></a>', "</div>", '<div class="hd-info">', '<div class="info-name">', '<a href="http://www.tianya.cn/<% userId %>" uid="<% userId %>" class="js-vip-check" title="<% userName %>"><% userName %></a>', "</div>", '<div class="info-from">', "<span><% renderPostTime(time) %></span><span><% from %></span>", "</div>", "</div>", "</div>", '<div class="f-i-bd">', '<div class="bd-text">', '<div class="article"><% word %></div>', "</div>", '<div class="bd-quote">', "<% if tweet.hotCount === -1 && tweet.likeCount === -1 && tweet.hotCount === -1 %>", "<% tweet.title %>", "<% else %>", '<div class="feed-content-reply">', '<div class="f-i-hd">', '<div class="hd-pic">', '<a href="http://www.tianya.cn/<% tweet.userId %>"><img src="http://tx.tianyaui.com/logo/small/<% tweet.userId %>" title="<% tweet.userName %>" alt="<% tweet.userName %>" /></a>', "</div>", '<div class="hd-info">', '<div class="info-name">', '<a href="http://www.tianya.cn/<% tweet.userId %>" uid="<% tweet.userId %>" class="js-vip-check" title="<% tweet.userName %>"><% tweet.userName %></a>', "</div>", '<div class="info-from">', '<span><a href="<% getSourceLink(tweet.id, tweet.userId, tweet.url) %>" target="_blank"><% renderPostTime(tweet.postTime) %></a></span><span><% tweet.from %></span>', "</div>", "</div>", "</div>", '<div class="f-i-bd">', '<div class="bd-text"><% include("SjTextTpl", tweet) %></div>', "<% if tweet.media && tweet.media.image && tweet.media.image.length %>", '<div class="bd-media"><% include("SjPictureTpl", tweet) %></div>', "<% /if %>", '<div class="bd-operate sj-operate">', '<div class="handle">', '<a class="op-collect" href="javascript:void(0);" title="收藏" data-id="<% tweet.id %>" data-uid="<% tweet.userId %>" data-app="<% tweet.appId %>" data-key="<% tweet.userId %>_<% tweet.id %>">收藏</a>', '<span class="split-line">|</span>', '<a class="op-forward" href="<% getSourceLink(tweet.id, tweet.userId, tweet.url) %>" target="_blank" title="转发" data-id="<% tweet.id %>" data-uid="<% tweet.userId %>" data-count="<% tweet.shareCount %>">转发<span><% tweet.shareCount ? "(" + tweet.shareCount + ")" : "" %></span></a>', '<span class="split-line">|</span>', '<a class="op-comment" href="<% getSourceLink(tweet.id, tweet.userId, tweet.url) %>" target="_blank" title="评论" data-id="<% tweet.id %>" data-uid="<% tweet.userId %>" data-app="<% tweet.appId %>" data-count="<% tweet.replyCount %>">评论<span><% tweet.replyCount ? "(" + tweet.replyCount + ")" : "" %></span></a>', '<span class="split-line">|</span>', '<a class="op-praise" href="javascript:void(0);" title="�?" data-id="<% tweet.id %>" data-uid="<% tweet.userId %>" data-key="<% tweet.userId %>_<% tweet.id %>" data-count="<% tweet.likeCount %>"><i class="praise-icon"></i><span><% tweet.likeCount ? "(" + tweet.likeCount + ")" : "" %></span></a>', "</div>", "<% if tweet.media && tweet.media.tag && tweet.media.tag.length %>", '<div class="info">', '<div class="tags">', "<% each tweet.media.tag %>", '<a href="http://www.tianya.cn/t/tag/<% tweet.media.tag[$index] %>" title="<% tweet.media.tag[$index] %>" target="_blank"><% tweet.media.tag[$index] %></a>', "<% /each %>", "</div>", "</div>", "<% /if %>", "</div>", "</div>", "</div>", "<% /if %>", "</div>", '<div class="bd-operate sj-operate">', '<div class="handle">', '<a class="cm-delete" href="javascript:void(0);" title="删除" data-id="<% id %>" data-uid="<% userId %>" data-touid="<% toUid %>">删除</a>', '<span class="split-line">|</span>', '<a class="cm-reply" href="javascript:void(0);" data-uid="<% userId %>" data-uname="<% userName %>" title="回复">回复</a>', "</div>", "</div>", '<div class="bd-comment sj-comment" data-type="comment">', '<div class="comment-wrapper">', '<div class="comment-area">', '<textarea class="cm-textarea" maxlength="140"></textarea>', '<input class="cm-button" type="button" title="回复" value="回复" />', "</div>", "</div>", "</div>", "</div>", "</div>"].join(""),
        PraiseMeTpl: ['<div class="feed-content-item">', '<div class="f-i-hd">', '<div class="hd-pic">', '<a href="http://www.tianya.cn/<% userId %>"><img src="http://tx.tianyaui.com/logo/small/<% userId %>" title="<% userName %>" alt="<% userName %>" /></a>', "</div>", '<div class="hd-info">', '<div class="info-name">', '<a href="http://www.tianya.cn/<% userId %>" uid="<% userId %>" class="js-vip-check" title="<% userName %>"><% userName %></a>', "</div>", '<div class="info-from">', "<span><% renderPostTime(creatTime) %></span><span><% sourceName %></span>", "</div>", "</div>", "</div>", '<div class="f-i-bd">', '<div class="bd-text">', '<div class="article">顶了我的随记<i class="at-praised-icon"></i></div>', "</div>", '<div class="bd-quote">', '<div class="feed-content-praise">', '<div class="f-i-bd">', '<div class="bd-text"><% include("SjTextTpl", tweet) %></div>', "<% if tweet.media && tweet.media.image && tweet.media.image.length %>", '<div class="bd-media"><% include("SjPictureTpl", tweet) %></div>', "<% /if %>", '<div class="bd-operate sj-operate">', '<div class="handle">', '<a class="op-collect" href="javascript:void(0);" title="收藏" data-id="<% tweet.id %>" data-uid="<% tweet.userId %>" data-app="<% tweet.appId %>" data-key="<% tweet.userId %>_<% tweet.id %>">收藏</a>', '<span class="split-line">|</span>', '<a class="op-forward" href="<% getSourceLink(tweet.id, tweet.userId, tweet.url) %>" target="_blank" title="转发" data-id="<% tweet.id %>" data-uid="<% tweet.userId %>" data-count="<% tweet.shareCount %>">转发<span><% tweet.shareCount ? "(" + tweet.shareCount + ")" : "" %></span></a>', '<span class="split-line">|</span>', '<a class="op-comment" href="<% getSourceLink(tweet.id, tweet.userId, tweet.url) %>" target="_blank" title="评论" data-id="<% tweet.id %>" data-uid="<% tweet.userId %>" data-app="<% tweet.appId %>" data-count="<% tweet.replyCount %>">评论<span><% tweet.replyCount ? "(" + tweet.replyCount + ")" : "" %></span></a>', '<span class="split-line">|</span>', '<a class="op-praise" href="javascript:void(0);" title="�?" data-id="<% tweet.id %>" data-uid="<% tweet.userId %>" data-key="<% tweet.userId %>_<% tweet.id %>" data-count="<% tweet.likeCount %>"><i class="praise-icon"></i><span><% tweet.likeCount ? "(" + tweet.likeCount + ")" : "" %></span></a>', "</div>", "<% if tweet.media && tweet.media.tag && tweet.media.tag.length %>", '<div class="info">', '<div class="tags">', "<% each tweet.media.tag %>", '<a href="http://www.tianya.cn/t/tag/<% tweet.media.tag[$index] %>" title="<% tweet.media.tag[$index] %>" target="_blank"><% tweet.media.tag[$index] %></a>', "<% /each %>", "</div>", "</div>", "<% /if %>", "</div>", "</div>", "</div>", "</div>", "</div>", "</div>"].join(""),
        TimeLineNavTpl: ['<div class="sj-timeline-nav clearfix">', '<ul class="timeline-nav-tab clearfix">', '<li class="tab-item on" data-filter=\'{"filterTwitter":1,"filterQing":1,"filterBbs":1,"filterBlog":1,"filterOwn":1}\'><a href="javascript:void(0);">全部</a></li>', '<li class="tab-item" data-filter=\'{"filterTwitter":1,"filterQing":1}\'><a href="javascript:void(0);">随记</a></li>', '<li class="tab-item" data-filter=\'{"filterBbs":1}\'><a href="javascript:void(0);">论坛</a></li>', '<li class="tab-item" data-filter=\'{"filterBlog":1}\'><a href="javascript:void(0);">博客</a></li>', "</ul>", "</div>"].join(""),
        TimeLineContentTpl: ['<div class="sj-timeline-content">', '<div class="timeline-feed"></div>', "</div>"].join(""),
        TimeLineItemTpl: ['<% timeDivision(postTime, "year") %>', '<div class="feed-content-item" data-id="<% id %>" data-uid="<% userId %>" data-uname="<% userName %>" data-app="<% appId %>" data-shareid="<% share.id %>" data-shareuid="<% share.userId %>" data-shareuname="<% share.userName || "" %>">', '<% timeDivision(postTime, "day") %>', '<div class="f-i-hd">', '<div class="hd-title">', '<h4 class="title">', '<% if (appId === "qing" || appId === "bbs" || appId === "blog") && title %><% title %><% /if %>', "</h4>", '<span><a href="<% getSourceLink(id, userId, url) %>" target="_blank"><% renderPostTime(postTime) %></a></span>', "</div>", "</div>", '<div class="f-i-bd">', '<div class="bd-text">', '<% if appId === "twitter" %>', '<div class="article">', "<% title %>", "</div>", '<% else if appId === "qing" %>', '<div class="article">', "<% if removeHtmlTags(body).getCNlen() > 280 %>", '<div class="article-cut">', '<%  renderSuijiContent(body) %>&nbsp;<a class="at-toggle" href="javascript:void(0);" title="展开">展开<i class="toggle-icon open"></i></a>', "</div>", '<div class="article-total">', '<% replaceHtml(body, "&lt;br&nbsp;/&gt;", "<br />") %>&nbsp;<a class="at-toggle" href="javascript:void(0);" title="收起">收起<i class="toggle-icon close"></i></a>', "</div>", "<% else %>", '<% replaceHtml(body, "&lt;br&nbsp;/&gt;", "<br />") %>', "<% /if %>", "</div>", '<% else if appId === "bbs" || appId === "blog" %>', '<div class="article"><% body && replaceHtml(removeHtmlTags(body), "&lt;br&nbsp;/&gt;", "<br />") %>...<a class="at-link" href="<% url %>" target="_blank" title="查看原文">查看原文&gt;&gt;</a></div>', "<% if mediaFlag && 1024 ==1024 %>", "<% each media.tvideo %>", '<span videoid="<% media.tvideo[$index].videoId %>" v="<% media.tvideo[$index].videoUrl %>" class="short-video" style="width: <%media.tvideo[$index].width >=480 ? 480 :media.tvideo[$index].width %>px; height: <% media.tvideo[$index].height ||480 %>px;">', '<img original="<% media.tvideo[$index].thumbUrl %>" style="width:100%;height:100%;" src="<% media.tvideo[$index].thumbUrl %>"><span class="play-btn"></span><div class="video"></div>', "</span>", "<% /each %>", "<% /if %>", "<% else %>", '<div class="article">', "<% title %>", "</div>", "<% /if %>", "</div>", "<% if media && media.image && media.image.length %>", '<div class="bd-media"><% include("SjPictureTpl") %></div>', "<% /if %>", "<% if share && share.id %>", '<div class="bd-quote"><% share.id === -1 ? "原文内容已被删除" : include("FeedItemTpl", share) %></div>', "<% /if %>", '<div class="bd-operate sj-operate">', '<div class="handle">', "<% if share && userId != currentUserId() && checkPower() %>", '<input class="ad-delete-ck" type="checkbox" value="<% id %>,<% userId %>" />', '<a class="ad-delete" href="javascript:void(0);" title="删除" data-delkey="<% id %>,<% userId %>">删除</a>', '<span class="split-line">|</span>', "<% /if %>", "<% if share && userId == currentUserId() %>", '<a class="op-delete" href="javascript:void(0);" title="删除" data-id="<% id %>" data-uid="<% userId %>">删除</a>', '<span class="split-line">|</span>', "<% /if %>", '<a class="op-collect" href="javascript:void(0);" title="收藏" data-id="<% id %>" data-uid="<% userId %>" data-app="<% appId %>" data-key="<% userId %>_<% id %>">收藏</a>', '<span class="split-line">|</span>', '<a class="op-forward" <% if share %>href="javascript:void(0);"<% else %>href="<% getSourceLink(id, userId, url) %>" target="_blank"<% /if %> title="转发" data-id="<% id %>" data-uid="<% userId %>" data-count="<% shareCount %>">转发<span><% shareCount ? "(" + shareCount + ")" : "" %></span></a>', '<span class="split-line">|</span>', '<a class="op-comment" <% if share %>href="javascript:void(0);"<% else %>href="<% getSourceLink(id, userId, url) %>" target="_blank"<% /if %> title="评论" data-id="<% id %>" data-uid="<% userId %>" data-app="<% appId %>" data-count="<% replyCount %>">评论<span><% replyCount ? "(" + replyCount + ")" : "" %></span></a>', '<span class="split-line">|</span>', '<a class="op-praise" href="javascript:void(0);" title="�?" data-id="<% id %>" data-uid="<% userId %>" data-key="<% userId %>_<% id %>" data-count="<% likeCount %>"><i class="praise-icon"></i><span><% likeCount ? "(" + likeCount + ")" : "" %></span></a>', "</div>", '<div class="info">', '<div class="from"><% from %></div>', "</div>", "</div>", "<% if share %>", '<div class="bd-comment sj-comment" data-type="comment">', '<div class="comment-wrapper">', '<div class="comment-title">', '<div class="cm-prompt">还可输入<span>140</span>�?</div><div class="cm-title">评论</div>', "</div>", '<div class="comment-area">', '<textarea class="cm-textarea"></textarea><input class="cm-button" type="button" title="发布" value="发布" />', "</div>", '<div class="comment-toolbar"><label><input class="cm-checkbox" type="checkbox" />同时转发</label></div>', "</div>", '<div class="comment-list" data-morelink="<% getSourceLink(id, userId, url) %>"></div>', "</div>", '<div class="bd-comment sj-comment" data-type="forward">', '<div class="comment-wrapper">', '<div class="comment-title">', '<div class="cm-prompt">还可输入<span>140</span>�?</div><div class="cm-title">转发</div>', "</div>", '<div class="comment-area">', '<textarea class="cm-textarea" data-text="<% share.id ? encodeText(" || @" + userName + ":" + oTitle) : "" %>"></textarea><input class="cm-button" type="button" title="发布" value="发布" />', "</div>", "</div>", '<div class="comment-list" data-morelink="<% getSourceLink(id, userId, url) %>"></div>', "</div>", "<% /if %>", "</div>", "</div>"].join(""),
        TimeLineRegItemTpl: ['<% timeDivision(regTime.substring(0, 10), "year") %>', '<div class="feed-content-item">', '<% timeDivision(regTime.substring(0, 10), "day") %>', '<div class="f-i-custom">', '<div class="user-register-date">', "<h2>在天涯出生了�?</h2>", "<p>（注册于<% regTime.substring(5, 7) %>�?<% regTime.substring(8, 10) %>�?<% regTime.substring(11, 16) %>�?</p>", "</div>", "</div>", "</div>"].join(""),
        InterestTagTpl: ['<div class="sj-interest-tags">', '<div class="tags-list">', "<% if rssList && rssList.length %>", '<p class="list-title">选择兴趣，我们会给您推荐相关的优质内容��?</p>', '<div class="list-content hot cf">', "<% each rssList %>", '<a class="tag <% rssList[$index].isSubscribe == 1 ? "focused" : "" %>" href="javascript:void(0);" data-id="<% rssList[$index].userId %>"><% rssList[$index].labelName %></a>', "<% /each %>", "</div>", "<% /if %>", "<% if recommendList && recommendList.length %>", '<p class="list-title">我们推荐您关�?</p>', '<div class="list-content recommend cf">', "<% each recommendList %>", '<a class="tag <% recommendList[$index].isSubscribe == 1 ? "focused" : "" %>" href="javascript:void(0);" data-id="<% recommendList[$index].userId %>"><% recommendList[$index].labelName %></a>', "<% /each %>", "</div>", "<% /if %>", "</div>", '<div class="tags-oper">', '<a href="javascript:void(0);" class="tags-oper-btn slide-up-btn">收起 &uarr;</a>', "</div>", "</div>"].join(""),
        UserFeedItemTpl: ["<% if share %>", '<div class="feed-content-item" data-id="<% id %>" data-uid="<% userId %>" data-uname="<% userName %>" data-app="<% appId %>" data-shareid="<% share.id %>" data-shareuid="<% share.userId %>" data-shareuname="<% share.userName || "" %>">', "<% else %>", '<div class="feed-content-forward">', '<div class="f-i-hd">', '<div class="hd-pic">', '<a href="http://www.tianya.cn/<% userId %>"><img src="http://tx.tianyaui.com/logo/small/<% userId %>" title="<% userName %>" alt="<% userName %>" /></a>', "</div>", '<div class="hd-info">', '<div class="info-name">', '<a href="http://www.tianya.cn/<% userId %>" uid="<% tweet.userId %>" class="js-vip-check" title="<% userName %>"><% userName %></a>', "</div>", '<div class="info-from">', '<span><a href="<% getSourceLink(id, userId, url) %>" target="_blank"><% renderPostTime(postTime) %></a></span><span><% from %></span>', "</div>", "</div>", "</div>", "<% /if %>", '<div class="f-i-bd">', '<div class="bd-text"><% include("SjTextTpl") %></div>', "<% if media && media.image && media.image.length %>", '<div class="bd-media"><% include("SjPictureTpl") %></div>', "<% /if %>", "<% if share && share.id %>", '<div class="bd-quote"><% share.id === -1 ? "原文内容已被删除" : include("FeedItemTpl", share) %></div>', "<% /if %>", '<div class="bd-operate sj-operate">', '<div class="handle">', "<% if share && userId != currentUserId() && checkPower() %>", '<input class="ad-delete-ck" type="checkbox" value="<% id %>,<% userId %>" />', '<a class="ad-delete" href="javascript:void(0);" title="删除" data-delkey="<% id %>,<% userId %>">删除</a>', '<span class="split-line">|</span>', "<% /if %>", "<% if share && userId == currentUserId() %>", '<a class="op-delete" href="javascript:void(0);" title="删除" data-id="<% id %>" data-uid="<% userId %>">删除</a>', '<span class="split-line">|</span>', "<% /if %>", '<a class="op-collect" href="javascript:void(0);" title="收藏" data-id="<% id %>" data-uid="<% userId %>" data-app="<% appId %>" data-key="<% userId %>_<% id %>">收藏</a>', '<span class="split-line">|</span>', '<a class="op-forward" <% if share %>href="javascript:void(0);"<% else %>href="<% getSourceLink(id, userId, url) %>" target="_blank"<% /if %> title="转发" data-id="<% id %>" data-uid="<% userId %>" data-count="<% shareCount %>">转发<span><% shareCount ? "(" + shareCount + ")" : "" %></span></a>', '<span class="split-line">|</span>', '<a class="op-comment" <% if share %>href="javascript:void(0);"<% else %>href="<% getSourceLink(id, userId, url) %>" target="_blank"<% /if %> title="评论" data-id="<% id %>" data-uid="<% userId %>" data-app="<% appId %>" data-count="<% replyCount %>">评论<span><% replyCount ? "(" + replyCount + ")" : "" %></span></a>', '<span class="split-line">|</span>', '<a class="op-praise" href="javascript:void(0);" title="�?" data-id="<% id %>" data-uid="<% userId %>" data-key="<% userId %>_<% id %>" data-count="<% likeCount %>"><i class="praise-icon"></i><span><% likeCount ? "(" + likeCount + ")" : "" %></span></a>', "</div>", '<div class="info">', '<div class="from">', '<span><a href="<% getSourceLink(id, userId, url) %>" target="_blank"><% renderPostTime(postTime) %></a></span>〢�<% from %>', "</div>", "</div>", "</div>", "<% if share %>", '<div class="bd-comment sj-comment" data-type="comment">', '<div class="comment-wrapper">', '<div class="comment-title">', '<div class="cm-prompt">还可输入<span>140</span>�?</div><div class="cm-title">评论</div>', "</div>", '<div class="comment-area">', '<textarea class="cm-textarea"></textarea><input class="cm-button" type="button" title="发布" value="发布" />', "</div>", '<div class="comment-toolbar"><label><input class="cm-checkbox" type="checkbox" />同时转发</label></div>', "</div>", '<div class="comment-list" data-morelink="<% getSourceLink(id, userId, url) %>"></div>', "</div>", '<div class="bd-comment sj-comment" data-type="forward">', '<div class="comment-wrapper">', '<div class="comment-title">', '<div class="cm-prompt">还可输入<span>140</span>�?</div><div class="cm-title">转发</div>', "</div>", '<div class="comment-area">', '<textarea class="cm-textarea" data-text="<% share.id ? encodeText(" || @" + userName + ":" + oTitle) : "" %>"></textarea><input class="cm-button" type="button" title="发布" value="发布" />', "</div>", "</div>", '<div class="comment-list" data-morelink="<% getSourceLink(id, userId, url) %>"></div>', "</div>", "<% /if %>", "</div>", "</div>"].join(""),
        compile: function() {
            this.FeedNavTpl = TY.view.artTemplate.compile("FeedNavTpl", this.FeedNavTpl),
            this.FeedContentTpl = TY.view.artTemplate.compile("FeedContentTpl", this.FeedContentTpl),
            this.FeedLoadTpl = TY.view.artTemplate.compile("FeedLoadTpl", this.FeedLoadTpl),
            this.FeedPageTpl = TY.view.artTemplate.compile("FeedPageTpl", this.FeedPageTpl),
            this.FeedItemTpl = TY.view.artTemplate.compile("FeedItemTpl", this.FeedItemTpl),
            this.FeedAdminOperationTpl = TY.view.artTemplate.compile("FeedAdminOperationTpl", this.FeedAdminOperationTpl),
            this.SjTextTpl = TY.view.artTemplate.compile("SjTextTpl", this.SjTextTpl),
            this.SjPictureTpl = TY.view.artTemplate.compile("SjPictureTpl", this.SjPictureTpl),
            this.SjPictureViewTpl = TY.view.artTemplate.compile("SjPictureViewTpl", this.SjPictureViewTpl),
            this.SjPictureViewShowTpl = TY.view.artTemplate.compile("SjPictureViewShowTpl", this.SjPictureViewShowTpl),
            this.SjCommentItemTpl = TY.view.artTemplate.compile("SjCommentItemTpl", this.SjCommentItemTpl),
            this.SjCommentReplyAreaTpl = TY.view.artTemplate.compile("SjCommentReplyAreaTpl", this.SjCommentReplyAreaTpl),
            this.ReadDetailTpl = TY.view.artTemplate.compile("ReadDetailTpl", this.ReadDetailTpl),
            this.SelfNavTpl = TY.view.artTemplate.compile("SelfNavTpl", this.SelfNavTpl),
            this.MyReplyTpl = TY.view.artTemplate.compile("MyReplyTpl", this.MyReplyTpl),
            this.ReplyMeTpl = TY.view.artTemplate.compile("ReplyMeTpl", this.ReplyMeTpl),
            this.PraiseMeTpl = TY.view.artTemplate.compile("PraiseMeTpl", this.PraiseMeTpl),
            this.TimeLineNavTpl = TY.view.artTemplate.compile("TimeLineNavTpl", this.TimeLineNavTpl),
            this.TimeLineContentTpl = TY.view.artTemplate.compile("TimeLineContentTpl", this.TimeLineContentTpl),
            this.TimeLineItemTpl = TY.view.artTemplate.compile("TimeLineItemTpl", this.TimeLineItemTpl),
            this.TimeLineRegItemTpl = TY.view.artTemplate.compile("TimeLineRegItemTpl", this.TimeLineRegItemTpl),
            this.InterestTagTpl = TY.view.artTemplate.compile("InterestTagTpl", this.InterestTagTpl),
            this.UserFeedItemTpl = TY.view.artTemplate.compile("UserFeedItemTpl", this.UserFeedItemTpl)
        },
        helper: function() {
            TY.view.artTemplate.helper("renderSuijiContent",
            function(e) {
                for (var t = e.split("&lt;br&nbsp;/&gt;"), a = [], i = 0, s = 280, d = 0; d < t.length; d++) {
                    if (i += t[d].getCNlen(), !(s > i)) {
                        a.push(t[d].subStringCn(t[d].getCNlen() - (i - s)));
                        break
                    }
                    a.push(t[d])
                }
                return a.join("<br />")
            }),
            TY.view.artTemplate.helper("replaceHtml",
            function(e, t, a) {
                return e.replace(new RegExp(t, "g"), a)
            }),
            TY.view.artTemplate.helper("currentUserId",
            function() {
                return __global.getUserId()
            }),
            TY.view.artTemplate.helper("renderPostTime",
            function(e) {
                var t = new Date(e),
                a = new Date,
                i = new Date(a.getFullYear(), a.getMonth(), a.getDate()).getTime(),
                s = i - e,
                d = 864e5,
                n = (t.getHours() < 10 ? "0" + t.getHours() : t.getHours()) + ":" + (t.getMinutes() < 10 ? "0" + t.getMinutes() : t.getMinutes()),
                l = "";
                return l = 0 >= s ? "今天&nbsp;": d > s ? "昨天&nbsp;": 2 * d > s ? "前天&nbsp;": a.getFullYear() === t.getFullYear() ? t.getMonth() + 1 + "�?" + t.getDate() + "�?&nbsp;": t.getFullYear() + "�?" + (t.getMonth() + 1) + "�?" + t.getDate() + "�?&nbsp;",
                l += n
            }),
            TY.view.artTemplate.helper("encodeText",
            function(e) {
                return encodeURIComponent(e)
            }),
            TY.view.artTemplate.helper("getSourceLink",
            function(e, t, a) {
                var i = "http://www.tianya.cn/" + t + "/t/" + e;
                return a && (i = a),
                i
            }),
            TY.view.artTemplate.helper("removeHtmlTags",
            function(e) {
                return e.replace(/<[^>].*?>/g, "")
            }),
            TY.view.artTemplate.helper("limitLength",
            function(e, t) {
                e.length > t ? e.length = t: void 0
            }),
            TY.view.artTemplate.helper("checkPower",
            function() {
                var e = !1;
                return 1 == window.isTwitterDeletePower && (e = !0),
                e
            }),
            TY.view.artTemplate.helper("timeDivision",
            function(t, a) {
                var i = window.Time_Line.timeDivisionList,
                s = "",
                d = "";
                s = /^\s*(\d{4})-(\d\d)-(\d\d)\s*$/.exec(t) && e.browser.msie && e.browser.version < 9 ? t.substring(0, 10) : new Date(t).format("yyyy-mm-dd");
                var n = s.substring(0, 4),
                l = s.substring(5, 7),
                c = s.substring(8, 10),
                r = {
                    "01": "丢�",
                    "02": "�?",
                    "03": "�?",
                    "04": "�?",
                    "05": "�?",
                    "06": "�?",
                    "07": "�?",
                    "08": "�?",
                    "09": "�?",
                    10 : "�?",
                    11 : "十一",
                    12 : "十二"
                };
                switch (a) {
                case "year":
                    i[n] || (i[n] = [], d = '<div class="timeline-date">' + n + "</div>");
                    break;
                case "day":
                    -1 === e.inArray(l + "-" + c, i[n]) && (i[n].push(l + "-" + c), d = '<div class="f-i-extra"><div class="extra-arrow"><div class="arrow-border"></div><div class="arrow-background"></div></div><div class="extra-date"><strong>' + c + "</strong><span>" + r[l] + "�?</span></div></div>")
                }
                return d
            }),
            TY.view.artTemplate.helper("checkGif",
            function(e) {
                var t = "";
                return /.gif$/.test(e) && (t = '<i class="gif"></i>'),
                t
            })
        },
        init: function() {
            TY(document).data("SuiJi_template.init") || (TY(document).data("SuiJi_template.init", !0), TY.view.artTemplate.isEscape = !1, TY.view.artTemplate.openTag = "<%", TY.view.artTemplate.closeTag = "%>", this.compile(), this.helper())
        }
    },
    SuiJi_interface = {
        urls: {
            delItem: "http://www.tianya.cn/api/tw?method=tweet.ice.deleteTweet",
            delItemByAdmin: "http://www.tianya.cn/api/tw?method=tweet.ice.deleteTweetById",
            getCollectIds: "http://www.tianya.cn/api/tw?method=userlike.ice.selectbiaohong",
            addCollect: "http://www.tianya.cn/api/tw?method=userlike.ice.add",
            delCollect: "http://www.tianya.cn/api/tw?method=userlike.ice.delete",
            getPraiseIds: "http://www.tianya.cn/api/tw?method=userapprove.ice.selectbiaohong",
            addPraise: "http://www.tianya.cn/api/tw?method=userapprove.ice.add",
            delPraise: "http://www.tianya.cn/api/tw?method=userapprove.ice.cancel",
            getCommentList: "http://www.tianya.cn/api/tw?method=retweet.ice.selectRetweet",
            addComment: "http://www.tianya.cn/api/tw?method=retweet.ice.insert",
            delComment: "http://www.tianya.cn/api/tw?method=retweet.ice.deleteMoreRetweet",
            delCommentByAdmin: "http://www.tianya.cn/api/tw?method=retweet.ice.deleteRetweetByAdmin",
            getForwardList: "http://www.tianya.cn/api/tw?method=note.ice.selectShareNote",
            forwardItem: "http://www.tianya.cn/api/tw?method=tweet.ice.share",
            getVIipUser: "http://userlogo.tianya.cn/adsp/user/getVIipUser.jsp"
        },
        getCollectIds: function(t) {
            e.getJSON(this.urls.getCollectIds,
            function(e) {
                e && 1 == e.success ? t(e.data) : t(!1, e)
            })
        },
        doCollect: function(t, a) {
            e.post(t.url, {
                "params.activityId": t.id,
                "params.activityUserId": t.uid,
                "params.appId": t.app,
                "params.sourceName": t.sourceName,
                "params.sourceLink": t.sourceLink
            },
            function(e) {
                e && 1 == e.success ? a(e.data) : a(!1, e)
            },
            "json")
        },
        getPraiseIds: function(t) {
            e.getJSON(this.urls.getPraiseIds,
            function(e) {
                e && 1 == e.success ? t(e.data) : t(!1, e)
            })
        },
        doPraise: function(t, a) {
            e.post(t.url, {
                "params.activityId": t.id,
                "params.activityUserId": t.uid,
                "params.sourceName": t.sourceName,
                "params.sourceLink": t.sourceLink
            },
            function(e) {
                e && 1 == e.success ? a(e.data) : a(!1, e)
            },
            "json")
        },
        deleteItem: function(t, a) {
            e.post(this.urls.delItem, {
                "params.id": t.id,
                "params.delUserId": t.uid
            },
            function(e) {
                e && 1 == e.success ? a(e.data) : a(!1, e)
            },
            "json")
        },
        deleteItemByAdmin: function(t, a) {
            e.post(this.urls.delItemByAdmin, {
                "params.tw": t.ids
            },
            function(e) {
                e && 1 == e.success ? a(e.data) : a(!1, e)
            },
            "json")
        },
        getCommentList: function(t, a) {
            e.getJSON(this.urls.getCommentList, {
                "params.actId": t.id,
                "params.actUserId": t.uid,
                "params.appId": t.app,
                "params.pageSize": t.ps,
                "params.pageNo": t.pn
            },
            function(i) {
                i && 1 == i.success ? (a(i.data), ("blog" == t.app || "bbs" == t.app) && e(".cm-delete").hide()) : a(!1, i)
            })
        },
        doComment: function(t, a) {
            e.post(this.urls.addComment, {
                "params.actId": t.id,
                "params.actUserId": t.uid,
                "params.actUserName": t.uname,
                "params.receivedUserId": t.rUid,
                "params.receivedUserName": t.rUname,
                "params.content": t.content,
                "params.appId": t.app,
                "params.shareActId": t.sId,
                "params.shareActUserId": t.sUid,
                "params.shareActUserName": t.sUname,
                "params.sourceName": t.sourceName,
                "params.sourceLink": t.sourceLink,
                "params.type": t.type
            },
            function(e) {
                e && 1 == e.success ? a(e.data) : -15 == e.code ? TY.loader("TY.ui.nav",
                function() {
                    TY.checkLoginUser(3)
                }) : a(!1, e)
            },
            "json")
        },
        deleteComment: function(t, a) {
            e.post(this.urls.delComment, {
                "params.comment": t.comment
            },
            function(e) {
                e && 1 == e.success ? a(e.data) : a(!1, e)
            },
            "json")
        },
        deleteCommentByAdmin: function(t, a) {
            e.post(this.urls.delCommentByAdmin, {
                "params.comment": t.comment
            },
            function(e) {
                e && 1 == e.success ? a(e.data) : a(!1, e)
            },
            "json")
        },
        getForwardList: function(t, a) {
            e.getJSON(this.urls.getForwardList, {
                "params.id": t.id,
                "params.userId": t.uid,
                "params.pageSize": t.ps,
                "params.pageNo": t.pn
            },
            function(e) {
                e && 1 == e.success ? a(e.data) : a(!1, e)
            })
        },
        doForward: function(t, a) {
            e.post(this.urls.forwardItem, {
                "params.body": t.content,
                "params.appId": t.app,
                "params.sharedId": t.sId,
                "params.sharedUserId": t.sUid,
                "params.parentId": t.pId,
                "params.parentUserId": t.pUid,
                "params.sourceName": t.sourceName,
                "params.sourceLink": t.sourceLink,
                "params.type": t.type
            },
            function(e) {
                e && 1 == e.success ? a(e.data) : -15 == e.code ? TY.loader("TY.ui.nav",
                function() {
                    TY.checkLoginUser(3)
                }) : a(!1, e)
            },
            "json")
        },
        checkVipStatus: function() {
            function t(t, a) {
                for (var i = "",
                s = 0,
                d = a.length; d > s; s++) {
                    var n = a[s];
                    switch (n.type) {
                    case "1":
                        i += '<a class="identity-icon identity-icon-1" title="' + (n.desc || "天涯牛人认证") + '"></a>';
                        break;
                    case "3":
                        i += '<a class="identity-icon identity-icon-3" title="' + (n.desc || "企业认证用户") + '" href="http://www.tianya.cn/honor/enterprise" target="_blank"></a>';
                        break;
                    case "8":
                        i += '<a class="identity-icon identity-icon-8" title="' + (n.desc || "天涯旅游卡认证用�?") + '" href="http://ka.tianya.cn?from=bbs" target="_blank"></a>';
                        break;
                    case "11":
                        i += '<a class="identity-icon identity-icon-11" title="' + (n.desc || "天涯VIP会员") + '" href="http://www.tianya.cn/vip/" target="_blank"></a>';
                        break;
                    case "12-1":
                        i += '<a class="identity-icon identity-icon-12-1" title="' + (n.desc || "天涯朢�具号召力牛人-铜牌") + '"></a>';
                        break;
                    case "12-2":
                        i += '<a class="identity-icon identity-icon-12-2" title="' + (n.desc || "天涯朢�具号召力牛人-银牌") + '"></a>';
                        break;
                    case "12-3":
                        i += '<a class="identity-icon identity-icon-12-3" title="' + (n.desc || "天涯朢�具号召力牛人-金牌") + '"></a>';
                        break;
                    case "14":
                        i += '<a class="identity-icon identity-icon-14" title="' + (n.desc || "天涯文学签约写手") + '"></a>';
                        break;
                    case "17-1":
                        i += '<a class="identity-icon identity-icon-17-1" title="' + (n.desc || "现货财主丢�级会�?") + '" href="http://www.jiaoyijie.cn/tempPage/index/20160718" target="_blank"></a>';
                        break;
                    case "17-2":
                        i += '<a class="identity-icon identity-icon-17-2" title="' + (n.desc || "现货财主二级会员") + '" href="http://www.jiaoyijie.cn/tempPage/index/20160718" target="_blank"></a>';
                        break;
                    case "19":
                        i += '<a class="identity-icon identity-icon-19" title="' + (n.desc || "天涯众筹筹资�?") + '" href="http://zc.tianya.cn" target="_blank"></a>'
                    }
                    var l = n.type.split("-");
                    "18" == l[0] && (i += '<a class="identity-icon identity-icon-18" title="' + (n.desc || "天涯用户等级") + '" href="http://shang.tianya.cn/jsp/v2/knight.html" target="_blank">Lv&nbsp;' + l[1] + "</a>")
                }
                var c = e('.js-vip-check[uid="' + t + '"]');
                c.each(function() {
                    var t = e(this);
                    t.hasClass("checked") || (t.after(i), t.addClass("checked"))
                })
            }
            var a = e(".js-vip-check"),
            i = [];
            0 != a.length && (a.each(function() {
                var t = e(this);
                t.hasClass("checked") || i.push(t.attr("uid"))
            }), e.ajax({
                url: this.urls.getVIipUser + "?userid=" + i.join(","),
                type: "get",
                dataType: "jsonp",
                jsonp: "jsonpcallback",
                success: function(a) {
                    a && e.each(a,
                    function(e, a) {
                        t(e, a)
                    })
                }
            }))
        }
    },
    SuiJi_operation = {
        defaultConfig: {
            container: null,
            collectIds: null,
            praiseIds: null,
            appId: "qing",
            sourceName: "天涯随记",
            sourceLink: "http://www.tianya.cn/t/",
            feedPs: 20,
            feedCommentPs: 20,
            autoCreateFeedItem: !0,
            hideMoreComments: !0
        },
        changeCollectBtnState: function() {
            function t() {
                e(".op-collect").each(function() {
                    a.config.collectIds[e(this).data("key")] ? e(this).text("取消收藏").attr({
                        "class": "op-collect collected",
                        title: "取消收藏"
                    }) : e(this).text("收藏").attr({
                        "class": "op-collect collect",
                        title: "收藏"
                    })
                })
            }
            var a = this;
            this.config.collectIds ? t() : SuiJi_interface.getCollectIds(function(e) {
                a.config.collectIds = e,
                t()
            })
        },
        changePraiseBtnState: function() {
            function t() {
                e(".op-praise").each(function() {
                    e(this).attr(a.config.praiseIds[e(this).data("key")] ? {
                        "class": "op-praise praised",
                        title: "取消�?"
                    }: {
                        "class": "op-praise praise",
                        title: "�?"
                    })
                })
            }
            var a = this;
            a.config.praiseIds ? t() : SuiJi_interface.getPraiseIds(function(e) {
                a.config.praiseIds = e,
                t()
            })
        },
        createCommentItems: function(e, t, a) {
            for (var i = [], s = a.find(".comment-list"), d = 0; d < t.length; d++) i.push(SuiJi_template.SjCommentItemTpl(t[d]));
            s.html(i.join("")),
            this.config.hideMoreComments && t.length >= this.config.feedCommentPs && s.append('<div class="more-comment"><a href="' + s.data("morelink") + '" target="_blank" title="查看全部">查看全部&gt;&gt;</a></div>')
        },
        toggleArticleText: function(e) {
            e.closest(".article").find(".article-cut, .article-total").toggle()
        },
        toggleCommentBox: function(e) {
            var t = this,
            a = e.closest(".sj-operate").siblings(".sj-comment[data-type='comment']"),
            i = a.find(".comment-list");
            a.size() && (a.toggle().siblings(".sj-comment").hide(), "block" == a.css("display") && TY.util.cursorPoint.toTextAt(a.find(".cm-textarea")[0], {
                start: 0,
                end: 0
            }), !e.data("count") && e.data({
                loaded: !0,
                loading: !1
            }), e.data("loaded") || e.data("loading") || (i.html('<p class="tc">正在读取数据</p>'), e.data("loading", !0), SuiJi_interface.getCommentList({
                id: e.data("id"),
                uid: e.data("uid"),
                app: e.data("app"),
                ps: t.config.feedCommentPs,
                pn: 1
            },
            function(s) {
                s ? (s.items.length ? t.createCommentItems(s.total, s.items, a) : i.html(""), e.data({
                    loaded: !0,
                    loading: !1
                })) : e.data({
                    loaded: !1,
                    loading: !1
                })
            })))
        },
        toggleForwardBox: function(t) {
            var a = this,
            i = t.closest(".sj-operate").siblings(".sj-comment[data-type='forward']"),
            s = i.find(".comment-list");
            i.size() && (i.toggle().siblings(".sj-comment").hide(), "block" == i.css("display") && (i.find(".cm-textarea").val(function() {
                return decodeURIComponent(e(this).data("text"))
            }).trigger("change"), TY.util.cursorPoint.toTextAt(i.find(".cm-textarea")[0], {
                start: 0,
                end: 0
            })), !t.data("count") && t.data({
                loaded: !0,
                loading: !1
            }), t.data("loaded") || t.data("loading") || (s.html('<p class="tc">正在读取数据</p>'), t.data("loading", !0), SuiJi_interface.getForwardList({
                id: t.data("id"),
                uid: t.data("uid"),
                ps: a.config.feedCommentPs,
                pn: 1
            },
            function(e) {
                e && e.items.length ? a.createCommentItems(e.total, e.items, i) : s.html(""),
                t.data({
                    loaded: !0,
                    loading: !1
                })
            })))
        },
        collectAction: function(e) {
            var t = this;
            SuiJi_interface.doCollect({
                url: e.hasClass("collect") ? SuiJi_interface.urls.addCollect: SuiJi_interface.urls.delCollect,
                id: e.data("id"),
                uid: e.data("uid"),
                app: e.data("app"),
                sourceName: t.config.sourceName,
                sourceLink: t.config.sourceLink
            },
            function(a) {
                a && (t.config.collectIds && (e.hasClass("collect") ? t.config.collectIds[e.data("key")] = (new Date).getTime() : delete t.config.collectIds[e.data("key")]), t.changeCollectBtnState())
            })
        },
        praiseAction: function(t) {
            var a = this,
            i = t.data("count");
            SuiJi_interface.doPraise({
                url: t.hasClass("praise") ? SuiJi_interface.urls.addPraise: SuiJi_interface.urls.delPraise,
                id: t.data("id"),
                uid: t.data("uid"),
                sourceName: a.config.sourceName,
                sourceLink: a.config.sourceLink
            },
            function(s, d) {
                s ? (a.config.praiseIds && (t.hasClass("praise") ? a.config.praiseIds[t.data("key")] = (new Date).getTime() : delete a.config.praiseIds[t.data("key")]), t.hasClass("praise") ? i++:i--, e(".op-praise[data-id='" + t.data("id") + "']").data("count", i).find("span").text(i > 0 ? "(" + i + ")": ""), a.changePraiseBtnState()) : TY_Common_util.showTips("warn", d.message || "评论失败", 2e3)
            })
        },
        commentAction: function(t) {
            var a = this,
            i = t.siblings(".cm-textarea"),
            s = t.closest(".sj-comment"),
            d = s.closest(".feed-content-item"),
            n = s.find(".cm-checkbox"),
            l = s.siblings(".sj-operate").find(".op-comment").data("count"),
            c = s.siblings(".sj-operate").find(".op-forward").data("count");
            i.data("overflow") || t.data("loading") || (t.data("loading", !0), SuiJi_interface.doComment({
                content: i.val().trim(),
                id: d.data("id"),
                uid: d.data("uid"),
                uname: d.data("uname"),
                sId: d.data("shareid"),
                sUid: d.data("shareuid"),
                sUname: d.data("shareuname"),
                rUid: d.data("uid"),
                rUname: d.data("uname"),
                app: d.data("app"),
                sourceName: a.config.sourceName,
                sourceLink: a.config.sourceLink,
                type: n.prop("checked") ? 3 : 1
            },
            function(r, o) {
                r ? (TY_Common_util.showTips("success", "评论成功", 2e3), n.prop("checked") && SuiJi_interface.doForward({
                    content: i.val().trim(),
                    sId: d.data("shareid") || d.data("id"),
                    sUid: d.data("shareuid") || d.data("uid"),
                    pId: d.data("shareid") ? d.data("id") : 0,
                    pUid: d.data("shareuid") ? d.data("uid") : 0,
                    app: a.config.appId,
                    sourceName: a.config.sourceName,
                    sourceLink: a.config.sourceLink,
                    type: 3
                },
                function(t) {
                    t && (e(".op-forward[data-id='" + d.data("id") + "']").data("count", ++c).find("span").text("(" + c + ")"), e(".op-forward[data-id='" + t.share.id + "']").data("count", t.share.shareCount).find("span").text("(" + t.share.shareCount + ")"), a.config.autoCreateFeedItem && a.config.container.prepend(SuiJi_template.FeedItemTpl(t)), a.config.hideMoreComments && s.siblings(".sj-comment").find(".comment-list").prepend(SuiJi_template.SjCommentItemTpl({
                        id: t.id,
                        userId: t.userId,
                        userName: t.userName,
                        word: t.body,
                        time: t.postTime
                    })))
                }), i.val("").trigger("change"), e(".op-comment[data-id='" + d.data("id") + "']").data("count", ++l).find("span").text("(" + l + ")"), a.config.hideMoreComments && s.find(".comment-list").prepend(SuiJi_template.SjCommentItemTpl(r))) : TY_Common_util.showTips("warn", o.message || "评论失败", 2e3),
                t.data("loading", !1)
            }))
        },
        forwardAction: function(t) {
            var a = this,
            i = t.siblings(".cm-textarea"),
            s = t.closest(".sj-comment"),
            d = s.closest(".feed-content-item"),
            n = s.siblings(".sj-operate").find(".op-forward").data("count");
            i.data("overflow") || t.data("loading") || (t.data("loading", !0), SuiJi_interface.doForward({
                content: i.val().trim(),
                sId: d.data("shareid") || d.data("id"),
                sUid: d.data("shareuid") || d.data("uid"),
                pId: d.data("shareid") ? d.data("id") : 0,
                pUid: d.data("shareuid") ? d.data("uid") : 0,
                app: a.config.appId,
                sourceName: a.config.sourceName,
                sourceLink: a.config.sourceLink,
                type: 2
            },
            function(i, l) {
                i ? (TY_Common_util.showTips("success", "转发成功", 2e3), s.hide(), e(".op-forward[data-id='" + d.data("id") + "']").data("count", ++n).find("span").text("(" + n + ")"), e(".op-forward[data-id='" + i.share.id + "']").data("count", i.share.shareCount).find("span").text("(" + i.share.shareCount + ")"), a.config.autoCreateFeedItem && a.config.container.prepend(SuiJi_template.FeedItemTpl(i)), a.config.hideMoreComments && s.find(".comment-list").prepend(SuiJi_template.SjCommentItemTpl({
                    id: i.id,
                    userId: i.userId,
                    userName: i.userName,
                    word: i.body,
                    time: i.postTime
                }))) : TY_Common_util.showTips("warn", l.message || "转发失败", 2e3),
                t.data("loading", !1)
            }))
        },
        toggleReplyAreaBox: function(e) {
            var t = e.closest(".comment-item"),
            a = t.find(".info-reply");
            a.size() ? a.remove() : (t.find(".cm-info").append(SuiJi_template.SjCommentReplyAreaTpl({
                uid: e.data("uid"),
                uname: e.data("uname"),
                text: "回复 @" + e.data("uname") + ":"
            })), TY.util.cursorPoint.toTextEnd(t.find(".re-textarea")[0]))
        },
        replyAction: function(t) {
            var a = this,
            i = t.siblings(".re-textarea"),
            s = t.closest(".sj-comment"),
            d = s.closest(".feed-content-item"),
            n = s.siblings(".sj-operate").find(".op-comment").data("count");
            t.data("loading") || (t.data("loading", !0), SuiJi_interface.doComment({
                content: i.val().trim(),
                id: d.data("id"),
                uid: d.data("uid"),
                uname: d.data("uname"),
                sId: d.data("shareid"),
                sUid: d.data("shareuid"),
                sUname: d.data("shareuname"),
                rUid: i.data("uid"),
                rUname: i.data("uname"),
                app: d.data("app"),
                sourceName: a.config.sourceName,
                sourceLink: a.config.sourceLink,
                type: 1
            },
            function(l, c) {
                l ? (TY_Common_util.showTips("success", "回复成功", 2e3), e(".op-comment[data-id='" + d.data("id") + "']").data("count", ++n).find("span").text("(" + n + ")"), a.config.hideMoreComments && s.find(".comment-list").prepend(SuiJi_template.SjCommentItemTpl(l)), i.closest(".info-reply").remove()) : TY_Common_util.showTips("warn", c.message || "回复失败", 2e3),
                t.data("loading", !1)
            }))
        },
        deleteItemAction: function(e) {
            var t = e.closest(".feed-content-item");
            new TY.ui.pop({
                body: "确认删除这条随记吗？",
                type: "confirm",
                yesHandler: function() {
                    SuiJi_interface.deleteItem({
                        id: e.data("id"),
                        uid: e.data("uid")
                    },
                    function(e, a) {
                        e ? t.slideUp("normal",
                        function() {
                            t.remove()
                        }) : TY_Common_util.showTips("warn", a.message || "删除随记失败", 2e3)
                    })
                }
            })
        },
        deleteItemByAdminAction: function(e) {
            new TY.ui.pop({
                body: "确认删除随记吗？",
                type: "confirm",
                yesHandler: function() {
                    SuiJi_interface.deleteItemByAdmin({
                        ids: e.join("|")
                    },
                    function(e, t) {
                        e ? window.location.reload() : TY_Common_util.showTips("warn", t.error || "删除随记失败", 2e3)
                    })
                }
            })
        },
        deleteCommentAction: function(t) {
            var a = t.closest(".comment-item"),
            i = a.closest(".feed-content-item"),
            s = a.closest(".sj-comment").siblings(".sj-operate").find(".op-comment").data("count");
            SuiJi_interface.deleteComment({
                comment: [t.data("id"), t.data("uid"), i.data("id"), i.data("uid"), t.data("touid"), i.data("shareid") || i.data("id"), i.data("shareuid") || i.data("uid")].join(",")
            },
            function(t, d) {
                t ? (a.slideUp("normal",
                function() {
                    a.remove()
                }), e(".op-comment[data-id='" + i.data("id") + "']").data("count", --s).find("span").text(s > 0 ? "(" + s + ")": "")) : TY_Common_util.showTips("warn", d.message || "删除评论失败", 2e3)
            })
        },
        deleteCommentByAdminAction: function(e) {
            var t = e.closest(".comment-item"),
            a = t.closest(".feed-content-item");
            SuiJi_interface.deleteCommentByAdmin({
                comment: [e.data("id"), e.data("uid"), a.data("id"), a.data("uid"), e.data("touid"), a.data("shareid") || a.data("id"), a.data("shareuid") || a.data("uid")].join(",")
            },
            function(e, t) {
                e ? window.location.reload() : TY_Common_util.showTips("warn", t.message || "删除评论失败", 2e3)
            })
        },
        wordLimit: function(e) {
            var t = 140,
            a = e.val().getCNlen(),
            i = (2 * t - a) / 2,
            s = i >= 0 ? Math.ceil(i) : Math.floor(i),
            d = e.closest(".comment-wrapper").find(".cm-prompt");
            0 > s ? (s = 0 - s, d.html("已超�?<span>" + s + "</span>�?"), e.data("overflow", !0)) : (d.html("还可输入<span>" + s + "</span>�?"), e.data("overflow", !1))
        },
        loadImage: function(e, t) {
            var a = new Image;
            return a.src = e,
            a.complete ? void t(a) : void(a.onload = function() {
                t(a)
            })
        },
        bindEvents: function() {
            var t = this,
            a = "video-stop",
            i = "short-video",
            s = "loading";
            this.config.container.delegate(".at-toggle", "click",
            function() {
                t.toggleArticleText(e(this))
            }).delegate(".op-collect", "click",
            function() {
                t.collectAction(e(this))
            }).delegate(".op-praise", "click",
            function() {
                t.praiseAction(e(this))
            }).delegate(".op-delete", "click",
            function() {
                t.deleteItemAction(e(this))
            }).delegate(".ad-delete", "click",
            function() {
                var a = [];
                a.push(e(this).data("delkey")),
                t.deleteItemByAdminAction(a)
            }).delegate(".op-comment", "click",
            function() {
                t.toggleCommentBox(e(this))
            }).delegate(".op-forward", "click",
            function() {
                t.toggleForwardBox(e(this))
            }).delegate(".cm-reply", "click",
            function() {
                t.toggleReplyAreaBox(e(this))
            }).delegate(".cm-delete", "click",
            function() {
                t.deleteCommentAction(e(this))
            }).delegate(".cm-delete-ad", "click",
            function() {
                t.deleteCommentByAdminAction(e(this))
            }).delegate(".sj-comment[data-type='comment'] .cm-button", "click",
            function() {
                t.commentAction(e(this))
            }).delegate(".sj-comment[data-type='forward'] .cm-button", "click",
            function() {
                t.forwardAction(e(this))
            }).delegate(".re-button", "click",
            function() {
                t.replyAction(e(this))
            }).delegate(".cm-textarea", "keyup change",
            function() {
                t.wordLimit(e(this))
            }).delegate(".short-video .play-btn", "click",
            function() {
                var t = e(this),
                d = t.closest("." + i);
                if (e(".feed-content-item .video-embed").remove(), !e.browser.msie || "7.0" != e.browser.version && "8.0" != e.browser.version && "6.0" != e.browser.version && "9.0" != e.browser.version) {
                    var n = d.find("video");
                    n.size() ? (n[0].play(), d.closest("." + i).removeClass(a)) : (d.addClass(s).find(".video").html('<video class="index_video" x-webkit-airplay="allow" webkit-playsinline="" loop="loop" autoplay="autoplay" x5-video-player-type="h5" x5-video-player-fullscreen="true" playsinline="true" src="' + d.attr("v") + '" style="width:100%; height: 100%; "></video>'), n = d.find("video"), n[0].play())
                } else d.append('<embed class="video-embed" type="application/x-shockwave-flash" allowscriptaccess="always" quality="high" wmode="transparent" allowfullscreen="true" src="http://static.tianyaui.com/global/ty/resources/swf/player.swf?BeginSwf=&amp;EndSwf=&amp;vcastr_file=' + d.attr("v") + '&amp;IsAutoPlay=1&amp;IsShowBar=0&amp;IsContinue=1" width="100%" align="middle" height="100%">')
            }).delegate("." + i + " video", "click",
            function() {
                this.pause(e(this)),
                e(this).closest("." + i).addClass(a)
            }),
            this.config.container.delegate(".big-cursor", "click",
            function() {
                var a = e(this),
                i = a.closest(".picture-preview"),
                s = i.siblings(".picture-view"),
                d = i.find(".big-cursor"),
                n = [];
                d.each(function() {
                    n.push(e(this))
                }),
                i.data("loading") || (i.data("loading", !0), a.after('<img class="loading" src="http://static.tianyaui.com/global/tw/images/loading1.gif" />'), t.loadImage(a.data("murl"),
                function(e) {
                    a.siblings(".loading").remove(),
                    i.data("loading", !1).hide(),
                    s.html(SuiJi_template.SjPictureViewTpl({
                        lurl: a.data("lurl"),
                        murl: e.src,
                        picList: n,
                        len: n.length,
                        idx: a.data("idx")
                    })).show()
                }))
            }).delegate(".small-cursor, .retract", "click",
            function() {
                var t = e(this).closest(".picture");
                t.find(".picture-preview").show(),
                t.find(".picture-view").hide().html("")
            }).delegate(".choose-item", "click",
            function() {
                var a = e(this),
                i = a.find("img"),
                s = a.closest(".view-choose"),
                d = s.closest(".picture-view");
                s.data("loading") || (s.data("loading", !0), d.find(".view-show-img").append('<img class="loading" src="http://static.tianyaui.com/global/tw/images/loading1.gif" />'), a.addClass("curr").siblings().removeClass("curr"), t.loadImage(i.data("murl"),
                function(e) {
                    d.find(".view-show").html(SuiJi_template.SjPictureViewShowTpl({
                        murl: e.src,
                        len: s.data("len"),
                        idx: i.data("idx")
                    })),
                    d.find(".original").attr("href", i.data("lurl")),
                    s.data("loading", !1)
                }))
            }).delegate(".view-show-next", "click",
            function() {
                var t = e(this).closest(".picture-view");
                t.find(".view-choose .curr").next().trigger("click")
            }).delegate(".view-show-prev", "click",
            function() {
                var t = e(this).closest(".picture-view");
                t.find(".view-choose .curr").prev().trigger("click")
            })
        },
        init: function(t) {
            this.config = e.extend({},
            this.defaultConfig, t),
            this.bindEvents()
        }
    },
    window.Index_Feed = {
        defaultConfig: {
            actionUrl: "http://www.tianya.cn/api/tw?method=tweet.ice.getFeed",
            hideTimeout: null,
            scrollCount: 2,
            lastTime: "",
            xhr: null,
            feedPs: 20,
            feedCommentPs: 10
        },
        init: function(t) {
            var a = this;
            TY.loader("TY.view.artTemplate", "TY.util.cursorPoint",
            function() {
                a.config = e.extend({},
                a.defaultConfig, t),
                SuiJi_template.init(),
                a.createHtml(),
                SuiJi_operation.init({
                    container: e(".sj-feed-content")
                }),
                a.getIndexFeedAction(),
                a.getInterestTag(),
                a.bindEvents()
            })
        },
        createHtml: function() {
            var e = [];
            e.push(SuiJi_template.FeedNavTpl({
                showFilter: !0,
                showOperInterest: !0
            })),
            e.push(SuiJi_template.FeedContentTpl()),
            e.push(SuiJi_template.FeedLoadTpl()),
            this.config.container.append(e.join(""))
        },
        resetFeedState: function() {
            this.config.lastTime = "",
            this.config.scrollCount = 2,
            e(".sj-feed-content").empty()
        },
        getIndexFeedAction: function() {
            var t = this,
            a = e(".sj-feed-load"),
            i = {
                "params.pageSize": this.config.feedPs,
                "params.pageNo": 1,
                "params.page": 1,
                "params.filterGroups": 1,
                "params.lastTime": this.config.lastTime
            },
            s = e(".feed-nav-filter .selected").data("filter");
            for (var d in s) i["params." + d] = s[d];
            a.data("loading", !0).html("正在读取数据"),
            t.config.xhr && t.config.xhr.abort(),
            this.config.xhr = e.getJSON(this.config.actionUrl + "&" + e.param(i),
            function(e) {
                if (e && 1 == e.success) {
                    var i = e.data.items,
                    s = e.data.total;
                    if (!i || !i.length) return t.config.scrollCount = 0,
                    void a.html("没有更多数据�?");
                    if (t.createFeedItems(i), t.config.lastTime = i[i.length - 1].postTime, SuiJi_operation.changeCollectBtnState(), SuiJi_operation.changePraiseBtnState(), s < t.config.feedPs) return t.config.scrollCount = 0,
                    void a.html("没有更多数据�?")
                }
                a.data("loading", !1).html("查看更多")
            })
        },
        createFeedItems: function(t) {
            for (var a = [], i = 0; i < t.length; i++) a.push(SuiJi_template.FeedItemTpl(t[i]));
            e(".sj-feed-content").append(a.join("")),
            SuiJi_interface.checkVipStatus()
        },
        getInterestTag: function() {
            function t() {
                var t = "";
                i && i.abort(),
                i = e.getJSON("http://www.tianya.cn/api/tw?method=subscribe.ice.findCurrentUserSubscribe",
                function(a) {
                    a && 1 == a.success && (t = SuiJi_template.InterestTagTpl(a.data), e(t).insertBefore(".sj-feed-content").slideDown("slow"))
                })
            }
            function a(t) {
                var a = "";
                a = t.hasClass("focused") ? "http://www.tianya.cn/api/tw?method=following.ice.delete": "http://www.tianya.cn/api/tw?method=following.ice.insert",
                e.post(a, {
                    "params.followingUserId": t.data("id")
                },
                function(a) {
                    a && 1 == a.success ? (e(".sj-interest-tags .tag[data-id='" + t.data("id").toString() + "']").toggleClass("focused"), e(".oper-refresh").trigger("click")) : -15 == a.code && TY.loader("TY.ui.nav",
                    function() {
                        TY.checkLoginUser(3)
                    })
                },
                "json")
            }
            var i = null,
            s = !1;
            e.getJSON("http://www.tianya.cn/api/tw?method=subscribe.ice.findIsSubscribe",
            function(e) {
                e && 1 == e.success && (0 == e.code ? t() : s = !0)
            }),
            e(".oper-interest").bind("click",
            function() {
                s || e.getJSON("http://www.tianya.cn/api/tw?method=subscribe.ice.setHadSubscribe",
                function(e) {
                    e && 1 == e.success && (s = !0)
                }),
                e(".sj-interest-tags").size() ? e(".sj-interest-tags").slideToggle("slow") : t()
            }),
            e("#suiji_feed").delegate(".sj-interest-tags .slide-up-btn", "click",
            function() {
                s || e.getJSON("http://www.tianya.cn/api/tw?method=subscribe.ice.setHadSubscribe",
                function(e) {
                    e && 1 == e.success && (s = !0)
                }),
                e(".sj-interest-tags").slideUp("slow")
            }).delegate(".sj-interest-tags .tag", "click",
            function() {
                a(e(this))
            })
        },
        bindEvents: function() {
            var t = this;
            e(".sj-feed-load").bind("click",
            function() {
                e(this).data("loading") || t.getIndexFeedAction()
            }),
            e(window).bind("scroll",
            function() {
                if (!e(".sj-feed-load").data("loading") && t.config.scrollCount) {
                    var a = e(window).height(),
                    i = e(document).height(),
                    s = e(document).scrollTop();
                    i > a && a / 2 > i - a - s && (t.config.scrollCount--, t.getIndexFeedAction())
                }
            }),
            e(".filter-state").bind("mouseenter",
            function() {
                t.config.hideTimeout ? clearTimeout(t.config.hideTimeout) : (e(".filter-state").addClass("filter-option-open"), e(".filter-option").show())
            }).bind("mouseleave",
            function() {
                t.config.hideTimeout = setTimeout(function() {
                    e(".filter-state").removeClass("filter-option-open"),
                    e(".filter-option").hide(),
                    t.config.hideTimeout = null
                },
                100)
            }),
            e(".filter-option").bind("mouseenter",
            function() {
                t.config.hideTimeout && clearTimeout(t.config.hideTimeout)
            }).bind("mouseleave",
            function() {
                t.config.hideTimeout = setTimeout(function() {
                    e(".filter-state").removeClass("filter-option-open"),
                    e(".filter-option").hide(),
                    t.config.hideTimeout = null
                },
                100)
            }),
            e(".option-item").bind("click",
            function() {
                e(this).hasClass("selected") || (e(this).addClass("selected").siblings().removeClass("selected"), e(".filter-state .state-text").text(e(this).text()), t.resetFeedState(), t.getIndexFeedAction())
            }),
            e(".oper-refresh").bind("click",
            function() {
                t.resetFeedState(),
                t.getIndexFeedAction()
            })
        }
    }
} (jQuery);