package com.example.hotel.Controller;


import com.alibaba.fastjson.JSONObject;
import com.example.hotel.DTO.WXConst;
import com.example.hotel.DTO.cOrderDTO;
import com.example.hotel.model.*;
import com.example.hotel.service.*;
import com.example.hotel.tools.Util;
import org.apache.commons.codec.digest.DigestUtils;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.jdom2.Document;
import org.jdom2.Element;


import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;



import static com.example.hotel.util.DateUtil.*;


@RestController
public class CreateOrderController {


    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomOrderService roomOrderService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private GetCouponService getCouponService;

    //检查优惠卷
    public  Double getCoupon(int cid,String uid){
        if(cid != 0  && uid !=null && !uid.equals("")) {
            GetCouponKey key=new GetCouponKey();
            key.setCid(cid);
            key.setUid(uid);
            GetCoupon gc=getCouponService.selectByPrimaryKey(key);//获取用户领取的优惠卷信息
            Coupon coupon = couponService.selectByPrimaryKey(cid);
            //if(gc.getUseEndDate())
            if (coupon != null ) {
                Double amount = coupon.getAmount();
                return amount;
            }
        }
        return 0.0;
    }

    //创建订单
    @RequestMapping("/api/createorder")
    public cOrderDTO createOrder(RoomOrder roomOrder,HttpServletRequest request){

        try {
            System.out.println("roomname: " + roomOrder.getUname());
            System.out.println("uid: " + roomOrder.getUid());
            User user=userService.selectByPrimaryKey(roomOrder.getUid());
            //HttpServletRequest request
            if(roomOrder !=null && roomOrder.getUid()!=null && !roomOrder.equals("") &&user.getUid()!=null && !user.getUid().equals("")) {

            String uid = roomOrder.getUid();                 //"1234";
            String roomname = roomOrder.getRoomname();       //"阳光大床房";
            String ordertime = roomOrder.getOrdertime();     //"2019-06-20";
            String leavetime = roomOrder.getLeavetime();     //"2019-06-21";
            int days = roomOrder.getOrderday();             //2;
            int roomnumber = roomOrder.getRoomnumber();     //2;
            String username = roomOrder.getUname();         //"许文强";
            String uphone = roomOrder.getUphone();          //"18316102612";
            String arrivetime = roomOrder.getArrivetime();    //到达时间
            Room r = roomService.selectByPrimaryKey(roomname);//查询roomname的房间信息
            if (r != null) {
                int num = r.getRoomnumber(); //剩余房间数
                if (num > 0 && num - roomnumber >= 0) {
                    double amount = getCoupon(roomOrder.getCid(), uid);
                    double price = r.getRoomprice() * roomnumber * days - amount; //总价，算优惠卷
                    Date date=new Date();
                    String s=change_str3(date);
                    ordertime += s.substring(10);
                    leavetime += s.substring(10);
                    RoomOrder order = new RoomOrder();

                    order.setOrderid("100"+change_str2(date));
                    order.setUid(uid);
                    order.setRoomnumber(roomnumber);
                    order.setUname(username);
                    order.setUphone(uphone);
                    order.setRoomname(roomname);
                    order.setTotalprice(price);
                    order.setOrdertime(ordertime);
                    order.setLeavetime(leavetime);
                    order.setArrivetime(arrivetime);
                    order.setOrderday(days);

                    int a = roomService.updateByPrimaryKeyForReduce(roomname, roomnumber);//更新房间数
                    if (a != 1) {
                        System.out.println("所选房间剩余不足");
                        return null;
                    }
                    roomOrderService.insertSelective(order); //创建订单
                    RoomOrder rm = new RoomOrder();
                    rm.setOrdertime(ordertime);
                    rm.setRoomname(roomname);
                    rm.setUid(uid);
                    String orderid = roomOrderService.selectByRDU(rm);//订单id
                    JSONObject json=wxPay(uid,"100"+change_str2(date),request);//调用微信支付

                    cOrderDTO cd = new cOrderDTO(uid, orderid, 1, 0.0);

                    return cd;
                } else {
                    System.out.println("房间不足");
                    return null;
                }

            }
        }
        }catch(Exception e){
            System.out.println(e);

        }
        System.out.println("数据为空或用户不存在");
            return null;
    }



    //判断支付是否成功
    @RequestMapping("/api/judgeorder")
    public String JudgeOrder( cOrderDTO Dt){



        if(Dt !=null){
            User u=userService.selectByPrimaryKey(Dt.getUid());
            RoomOrder re=roomOrderService.selectByOrderid(Dt.getOrderid());

            if( Dt.getUid().equals(re.getUid()) &&  re.getOrderstatus()==1 && re.getUid().equals(u.getUid())) { //uid相同且订单状态为1,and uid存在
                RoomOrder roomOrder=new RoomOrder();
                roomOrder.setUid(Dt.getUid());
                roomOrder.setOrderid(re.getOrderid());
                roomOrder.setRoomname(re.getRoomname());
                if (Dt.getStatus() == 2) {  //支付成功

                    if (Dt.getBalance() != 0.0 && u.getUbalance() -Dt.getBalance() >=0) {//余额
                        userService.updateByPrimaryKeyForBalance(u.getUid(),Dt.getBalance());//更新余额，减操作
                    }
                    //设定优惠卷生效
                    Byte x=1;
                    GetCoupon key=new GetCoupon();
                    key.setUid(re.getUid());
                    key.setCid(re.getCid());
                    key.setStatus(x);  //优惠卷设定为1，标记为已使用
                    getCouponService.updateByPrimaryKeySelective(key);//优惠券生效



                    roomOrder.setOrderstatus(2);
                    roomOrderService.updateByPrimaryKeySelective(roomOrder);//更改订单为2 成功
                    return "订单成功。";

                } else {//数据回滚
                    roomService.updateByPrimaryKeyForNum(re.getRoomname(),re.getRoomnumber());
                    roomOrder.setOrderstatus(3);
                    roomOrderService.updateByPrimaryKeySelective(roomOrder);//更改订单为3，作废
                    return "订单失败。";
                }


            }
        }
        return "订单失败。";
    }

    //获取当前时间
    public static String DateToString() {
        String dateStr = "hhh";

        try{
            java.util.Date  date = new Date();
            SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateStr = DF.format(date);

        } catch (Exception e) {
            System.out.println("Date error");
        }
        return dateStr;
    }


    public static JSONObject wxPay(String openid,String orderid,HttpServletRequest request){
        JSONObject json = new JSONObject();
        try{
            //生成的随机字符串
            String nonce_str = Util.getRandomStringByLength(32);
            //商品名称
            String body = new String(WXConst.title.getBytes("ISO-8859-1"),"UTF-8");
            //获取本机的ip地址
            String spbill_create_ip = Util.getIpAddr(request);
            String orderNo = orderid;
            String money = "1";//支付金额，单位：分，这边需要转成字符串类型，否则后面的签名会失败

            Map<String, String> packageParams = new HashMap<String, String>();
            packageParams.put("appid", WXConst.appId);
            packageParams.put("mch_id", WXConst.mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("body", body);
            packageParams.put("out_trade_no", orderNo);//商户订单号
            packageParams.put("total_fee", money);
            packageParams.put("spbill_create_ip", spbill_create_ip);
            packageParams.put("notify_url", WXConst.notify_url);
            packageParams.put("trade_type", WXConst.TRADETYPE);
            packageParams.put("openid", openid);


            // 除去数组中的空值和签名参数
            packageParams = CreateOrderController.paraFilter(packageParams);
            String prestr = CreateOrderController.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串


            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            String mysign = CreateOrderController.sign(prestr, WXConst.key, "utf-8").toUpperCase();
           // logger.info("=======================第一次签名：" + mysign + "=====================");


            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            String xml = "<xml version='1.0' encoding='gbk'>" + "<appid>" + WXConst.appId + "</appid>"
                    + "<body><![CDATA[" + body + "]]></body>"
                    + "<mch_id>" + WXConst.mch_id + "</mch_id>"
                    + "<nonce_str>" + nonce_str + "</nonce_str>"
                    + "<notify_url>" + WXConst.notify_url + "</notify_url>"
                    + "<openid>" + openid + "</openid>"
                    + "<out_trade_no>" + orderNo + "</out_trade_no>"
                    + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
                    + "<total_fee>" + money + "</total_fee>"
                    + "<trade_type>" + WXConst.TRADETYPE + "</trade_type>"
                    + "<sign>" + mysign + "</sign>"
                    + "</xml>";


            System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);


            //调用统一下单接口，并接受返回的结果
            String result = CreateOrderController.httpRequest(WXConst.pay_url, "POST", xml);


            System.out.println("调试模式_统一下单接口 返回XML数据：" + result);


            // 将解析结果存储在HashMap中
            Map map = CreateOrderController.doXMLParse(result);


            String return_code = (String) map.get("return_code");//返回状态码


            //返回给移动端需要的参数
            Map<String, Object> response = new HashMap<String, Object>();
            if(return_code == "SUCCESS" || return_code.equals(return_code)){
                // 业务结果
                String prepay_id = (String) map.get("prepay_id");//返回的预付单信息
                response.put("nonceStr", nonce_str);
                response.put("package", "prepay_id=" + prepay_id);
                Long timeStamp = System.currentTimeMillis() / 1000;
                response.put("timeStamp", timeStamp + "");//这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误


                String stringSignTemp = "appId=" + WXConst.appId + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id+ "&signType=" + WXConst.SIGNTYPE + "&timeStamp=" + timeStamp;
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                String paySign = CreateOrderController.sign(stringSignTemp, WXConst.key, "utf-8").toUpperCase();
               // logger.info("=======================第二次签名：" + paySign + "=====================");


                response.put("paySign", paySign);


                //更新订单信息
                //业务逻辑代码
            }


            response.put("appid", WXConst.appId);
            json.put("errMsg", "OK");
            //json.setSuccess(true);
            json.put("data", response);
            //json.setData(response);
        }catch(Exception e){
            e.printStackTrace();
            json.put("errMsg", "Failed");
            //json.setSuccess(false);
            //json.setMsg("发起失败");
        }
        return json;
    }

    /*
     * 签名字符串
     * @param text需要签名的字符串
     * @param key 密钥
     * @param input_charset编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String input_charset) {
        text = text + "&key=" + key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }
    /*
     * 签名字符串
     * @param text需要签名的字符串
     * @param sign 签名结果
     * @param key密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key, String input_charset) {
        text = text + key;
        String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
        if (mysign.equals(sign)) {
            return true;
        } else {
            return false;
        }
    }
    /*
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    public static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }
    /*
     * 生成6位或10位随机数 param codeLength(多少位)
     * @return
     */
    public static String createCode(int codeLength) {
        String code = "";
        for (int i = 0; i < codeLength; i++) {
            code += (int) (Math.random() * 9);
        }
        return code;
    }
    @SuppressWarnings("unused")
    private static boolean isValidChar(char ch) {
        if ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z'))
            return true;
        if ((ch >= 0x4e00 && ch <= 0x7fff) || (ch >= 0x8000 && ch <= 0x952f))
            return true;// 简体中文汉字编码
        return false;
    }

    /*
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<String, String>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                    || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }
    /*
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }
    /*
     *
     * @param requestUrl请求地址
     * @param requestMethod请求方法
     * @param outputStr参数
     */
    public static String httpRequest(String requestUrl,String requestMethod,String outputStr){
        // 创建SSLContext
        StringBuffer buffer = null;
        try{
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(requestMethod);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            //往服务器端写内容
            if(null !=outputStr){
                OutputStream os=conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }
            // 读取服务器端返回的内容
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            buffer = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static String urlEncodeUTF8(String source){
        String result=source;
        try {
            result=java.net.URLEncoder.encode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    /*
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     * @param strxml
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    public static Map doXMLParse(String strxml) throws Exception {
        if(null == strxml || "".equals(strxml)) {
            return null;
        }

        Map m = new HashMap();
        InputStream in = String2Inputstream(strxml);
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while(it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if(children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }

            m.put(k, v);
        }

//关闭流
        in.close();

        return m;
    }
    /*
     * 获取子结点的xml
     * @param children
     * @return String
     */
    public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if(!children.isEmpty()) {
            Iterator it = children.iterator();
            while(it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if(!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }
    public static InputStream String2Inputstream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

    public static void wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notityXml = sb.toString();
        String resXml = "";
        System.out.println("接收到的报文：" + notityXml);


        Map map = CreateOrderController.doXMLParse(notityXml);


        String returnCode = (String) map.get("return_code");
        if("SUCCESS".equals(returnCode)){
            //验证签名是否正确
            if(CreateOrderController.verify(CreateOrderController.createLinkString(map), (String)map.get("sign"), WXConst.key, "utf-8")){
                /**此处添加自己的业务逻辑代码start**/




                /**此处添加自己的业务逻辑代码end**/


                //通知微信服务器已经支付成功
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            }
        }else{
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        System.out.println(resXml);
        System.out.println("微信支付回调数据结束");


        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }





}
