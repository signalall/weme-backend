package cn.seu.weme.common.utils;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import net.sf.json.JSONObject;

/**
 * Created by LCN on 2017-1-3.
 */
public class SmsUtils {

    private final static String URL = "http://gw.api.taobao.com/router/rest";
    //成为开发者，创建应用后系统自动生成
    private final static String APPKEY = "23319113";
    private final static String SECRET = "19400b4f2c9da8cb8659d2b82ea8c4ba";
    private final static String SIGNNAME = "WEME唯觅";
    private final static String TEMPLATECODE1 = "SMS_5375426";
    private final static String TEMPLATECODE2 = "SMS_5375424";


    public static boolean sendSmsCodeByType(String phone, String code, int type) {

        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setSmsType("normal");
        req.setSmsFreeSignName(SIGNNAME);
        String paraString = "{\"code\":\"" + code + "\",\"product\":\"WEME\"}";
        req.setSmsParamString(paraString);
        req.setRecNum(phone);
        switch (type) {
            case 1:
                req.setSmsTemplateCode(TEMPLATECODE1);
                break;
            case 2:
                req.setSmsTemplateCode(TEMPLATECODE2);
                break;
            default:
                return false;
        }
        try {
            AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
            JSONObject jsonObject = JSONObject.fromObject(rsp.getBody());
            jsonObject.getJSONObject("alibaba_aliqin_fc_sms_num_send_response").getJSONObject("result").getBoolean("success");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
