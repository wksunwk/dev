!
function(t, e) {
    function i() {
        var e, i = [];
        return s && (t(p.ADS_LOC_SELECTOR).each(function() {
            var o = t(this),
            a = parseInt(o.attr("data-ads-url-type"), 10) || 0;
            e = {
                node: o.get(0),
                locID: o.attr("id"),
                adsameID: s + c + o.attr("data-ads-order"),
                adsameType: a
            },
            i.push(e)
        }), i.sort(function(t, e) {
            return t.adsameID > e.adsameID
        })),
        i
    }
    function o() {
        function e() {
            t("head").append(['<style type="text/css">', ".open-vip-tips-pop{border:2px solid #76c7f0; background:#f1f9fd; width:496px; position:relative; *zoom:1;}", ".open-vip-tips-pop .hd{height:30px; position:relative; *zoom:1;}", ".open-vip-tips-pop .hd .i-close{position:absolute; width:25px; height:25px; top:3px; right:3px; background:url(http://static.tianyaui.com/global/vip/web/static/images/ovtp-icons.png) 0 0 no-repeat;}", ".open-vip-tips-pop .bd{padding:35px 40px 45px; text-align:center;}", ".open-vip-tips-pop .bd .i-crown{display:block; margin:0 auto; width:55px; height:48px; background:url(http://static.tianyaui.com/global/vip/web/static/images/ovtp-icons.png) 0 -42px no-repeat;}", '.open-vip-tips-pop .bd .text{font:18px/24px "微软雅黑"; color:#1983b8; margin-bottom:6px;}', ".open-vip-tips-pop .bd .text a{text-decoration:underline; color:#e17a14;}", '.open-vip-tips-pop .bd .ignore{font:12px/24px "宋体"; color:#999;}', ".open-vip-tips-pop .bd .ignore label{vertical-align:middle;}", ".open-vip-tips-pop .bd .ignore label .nolonger-show{vertical-align:middle; margin:-2px 4px 0 0;}", '.open-vip-tips-pop .bd .open-btn{display:inline-block; width:190px; height:34px; font:20px/34px "微软雅黑"; color:#fff; background:#ee8e30; text-decoration:none; border-radius:5px; *display:inline; *zoom:1;}', ".open-vip-tips-pop .bd .open-btn:hover{background:#e17a14;}", ".open-vip-tips-pop .ad-logo{width:29px; height:16px; position:absolute; left:0; bottom:0;}", "</style>"].join("")),
            TY.loader("TY.ui.popV2",
            function() {
                new TY.ui.pop({
                    esc: !1,
                    isShowHead: !1,
                    isShowButton: !1,
                    isShowBorder: !1,
                    isPadding: !1,
                    modZindex: 1e3,
                    body: ['<div class="open-vip-tips-pop">', '<div class="hd"><a class="i-close closeBtn" href="javascript:void(0);" title="关闭"></a></div>', '<div class="bd">', '<div class="mb10"><a class="i-crown" href="http://www.tianya.cn/vip/" target="_blank"></a></div>', '<p class="text">98%的天涯er，都爱用�?</p>', '<p class="ignore"><label><input type="checkbox" class="nolonger-show" />下次不再提示</label></p>', '<div class="mt10"><a href="http://www.tianya.cn/vip/" target="_blank" class="open-btn">弢�通会�?</a></div>', "</div>", '<div class="ad-logo"><img src="http://801.tianya.cn/2015/leftbottom.png" /></div>', "</div>"].join(""),
                    render: function() {
                        t(document).data("OpenVipTipsPop_Showed", !0),
                        t(".open-vip-tips-pop .i-close").bind("click",
                        function() {
                            TY.io.storage.set(i, {
                                ignore: t(".nolonger-show").prop("checked"),
                                time: (new Date).getTime()
                            })
                        }),
                        t(".open-vip-tips-pop .open-btn").bind("click",
                        function() {
                            "undefined" != typeof _hmt && _hmt.push(["_trackEvent", "屏蔽广告弢�通VIP引导浮层—��开通会员点�?", "click"])
                        })
                    }
                })
            })
        }
        var i = "OpenVipTipsPop_State",
        o = 12096e5,
        a = 2592e6;
        t(document).delegate("#tyRMswclsbtn, #ty_msg_hd_close", "click",
        function() {
            if (t(document).data("OpenVipTipsPop_Showed") !== !0) {
                var n = TY.io.storage.get(i),
                p = (new Date).getTime(); (!n || !n.ignore && p > n.time + o || n.ignore && p > n.time + a) && e()
            }
        })
    }
    function a() {
        t(p.ADS_LOC_SELECTOR).delegate("a", "click",
        function() {
            var e = t(this),
            i = e.attr("href");
            return ! i || /javascript:|^#$/.test(i) ? !1 : void t.getScript("http://stat.tianya.cn/newAccess.jsp?" + t.param({
                p: "ad",
                clickurl: i,
                guid: __global.getCookie("__guid"),
                other: s + c + e.closest(p.ADS_LOC_SELECTOR).attr("data-ads-order")
            }))
        })
    }
    var n, p = {
        ADS_LOC_SELECTOR: ".ads-loc-holder",
        ADSAME_HOST: "http://dol.tianya.cn"
    },
    s = e.itemId,
    c = e.pageType,
    d = [],
    r = {},
    l = ("bbs.tianya.cn" === location.host.toLowerCase(), -1),
    h = 0,
    u = {
        c: [],
        la: [],
        ac: []
    };
    t.extend(e, {
        qNode: function(t) {
            return r["_" + t] ? r["_" + t].node: null
        }
    });
    var g = function() {
        function t(t, e, i, o) {
            this.url = t,
            this.containerObj = "string" == typeof e ? document.getElementById(e) : e,
            this.init = i ||
            function() {},
            this.callback = o ||
            function() {}
        }
        var e = [];
        return t.prototype = {
            startLoad: function() {
                var t = document.createElement("script"),
                e = this;
                try {
                    e.init.apply()
                } catch(i) {}
                t.readyState ? t.onreadystatechange = function() { ("loaded" == t.readyState || "complete" == t.readyState) && (t.onreadystatechange = null, e.finished())
                }: (t.onload = function() {
                    e.finished()
                },
                t.onerror = function() {
                    e.finished()
                }),
                t.src = e.url,
                t.type = "text/javascript",
                t.charset = "gb2312",
                document.getElementsByTagName("head")[0].appendChild(t)
            },
            finished: function() {
                try {
                    this.callback.apply()
                } catch(t) {}
                TY && TY.util ? TY.util.console(this.url) : "undefined" != typeof console && console.log(this.url)
            }
        },
        {
            add: function(i) {
                return i && e.push(new t(i.url, i.container, i.init, i.callback)),
                this
            },
            execute: function() {
                for (var t; t = e.shift();) t.startLoad()
            }
        }
    } ();
    if (s) {
        for (d = i(), l = d.length, h = 0; l > h; h++) switch (n = d[h], r["_" + n.adsameID] = n, n.adsameType) {
        case 0:
            u.c.push(n.adsameID);
            break;
        case 1:
            u.la.push(n.adsameID);
            break;
        case 2:
            u.ac.push(n.adsameID)
        }
        if (0 != l) {
            if (0 != u.c.length && g.add({
                url: p.ADSAME_HOST + "/s?z=tianya&c=" + u.c.join(",")
            }), 0 != u.la.length) for (h = 0; h < u.la.length; h++) g.add({
                url: p.ADSAME_HOST + "/s?z=tianya&la=" + u.la[h]
            });
            if (0 != u.ac.length) for (h = 0; h < u.ac.length; h++) g.add({
                url: p.ADSAME_HOST + "/a?z=tianya&c=" + u.ac[h]
            });
            g.execute(),
            window.bbsGlobal && o(),
            a()
        }
    }
} (jQuery, window.adsGlobal);