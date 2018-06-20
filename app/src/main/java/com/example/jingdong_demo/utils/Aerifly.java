package com.example.jingdong_demo.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.jingdong_demo.constant.Api;


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

public class Aerifly {

    //手机号验证
    public static boolean isMobile(Context context, String tel) {
        if (TextUtils.isEmpty(tel)){
            Toast.makeText(context,"手机号不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if (tel.matches(Api.REGEX_TEL)) {

        } else {
            Toast.makeText(context,"手机号不合法",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //密码验证
    public static boolean isPassword(Context context,String pwd) {
        if (TextUtils.isEmpty(pwd)){
            Toast.makeText(context,"密码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if (pwd.matches(Api.REGEX_PASSWORD)) {

        }else {
            Toast.makeText(context,"密码必须在6-20位并且不包含特殊字符",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
