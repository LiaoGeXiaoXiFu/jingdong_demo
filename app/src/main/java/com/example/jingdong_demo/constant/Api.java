package com.example.jingdong_demo.constant;

/**
 *
 * ━━━━━━草泥马神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃ 神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 *
 * ━━━━━━当善良的人摘下面具.你连下跪的机会都没有━━━━━━
 */

public class Api {
    public static final String MYAPI = "https://www.zhaoapi.cn/";
    /**
     * 登录
     */
    public static final String LOGIN = MYAPI + "user/login";
    /**
     * 注册
     */
    public static final String REG = MYAPI + "user/reg";
    /**
     * 上传头像
     */
    public static final String UPLOAD = MYAPI + "file/upload";
    /**
     * 获取用户信息
     */
    public static final String USERINFO = MYAPI + "user/getUserInfo";
    /**
     * 首页广告
     */
    public static final String GETAD = MYAPI + "ad/getAd";
    /**
     * 商品分类接口
     */
    public static final String GETCATAGORY = MYAPI + "product/getCatagory";
    /**
     * 商品子分类接口
     */
    public static final String GETPRODUCTCATAGORY = MYAPI + "product/getProductCatagory";
    /**
     * 商品详情
     */
    public static final String GETPRODUCTDETAIL = MYAPI + "product/getProductDetail";
    /**
     * 当前子分类下的商品列表
     * https://www.zhaoapi.cn/product/getProducts
     */
    public static final String GETPRODUCTS = MYAPI + "product/getProducts";
    /**
     * 根据关键词搜索商品
     * https://www.zhaoapi.cn/product/searchproducts?keywords=笔记本&page=1
     */
    public static final String SEARCHPRODUCTS = MYAPI + "product/searchProducts";
    /**
     * 查询购物车
     * https://www.zhaoapi.cn/product/getCarts
     */
    public static final String GETCARTS = MYAPI + "product/getCarts";
    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_TEL = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
}
