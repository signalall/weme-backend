package cn.seu.weme.common.result;

/**
 * 系统提示信息封装类
 * Created by LCN on 2016-12-17.
 */

public class ResultInfo {
    public static final int TYPE_RESULT_FAIL = 0;//失败
    public static final int TYPE_RESULT_SUCCESS = 1;//成功
    public static final int TYPE_RESULT_WARN = 2;//警告
    public static final int TYPE_RESULT_INFO = 3;//提示信息
    public static final int TYPE_LOGIN_ERROR = 4;//权限错误
    /**
     * 消息提示类型
     */
    private int type;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 提交后得到到业务数据信息从而返回给页面
     */
    private Object data = null;


    public ResultInfo() {

    }

    public ResultInfo(final int type, String message) {
        this.type = type;
        this.message = message;
    }


    public ResultInfo(final int type, String message, Object data) {
        this.type = type;
        this.message = message;
        this.data = data;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isSuccess() {
        if (this.type == TYPE_RESULT_SUCCESS) {
            return true;
        }
        return false;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
