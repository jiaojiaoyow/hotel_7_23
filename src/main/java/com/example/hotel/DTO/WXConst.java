package com.example.hotel.DTO;

public class WXConst {

    //微信小程序appid
    public static String appId = "wx7ef227238cff0fe0";
    //微信小程序appsecret
    public static String appSecret = "f46ecb9014738dd62359b22408e86126";
    //微信支付主体
    public static String title = "order_room";
    public static String orderNo = "";
    //微信商户号
    public static String mch_id="1281111001";
    //微信支付的商户密钥
    public static final String key = "yljd2019082119880607abcdef103637";
    //获取微信Openid的请求地址
    public static String WxGetOpenIdUrl = "https://wx.gdcpo.cn/api/backPay";
    //支付成功后的服务器回调url
//    public static final String notify_url="https://wx.gdcpo.cn/api/backPay";
    //签名方式
    public static final String SIGNTYPE = "MD5";
    //交易类型
    public static final String TRADETYPE = "JSAPI";
    //微信统一下单接口地址
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";  //正式
    //public static final String pay_url="https://api.mch.weixin.qq.com/sandboxnew/pay/micropay"; //测试



}
