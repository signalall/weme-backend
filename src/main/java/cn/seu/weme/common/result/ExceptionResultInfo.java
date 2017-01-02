package cn.seu.weme.common.result;

/**
 * Created by LCN on 2016-12-17.
 */
public class ExceptionResultInfo extends Exception {
    // 系统统一使用的结果类，包括了提示信息类型和信息内容
    private ResultInfo resultInfo;

    public ExceptionResultInfo(ResultInfo resultInfo) {
        super(resultInfo.getMessage());
        this.resultInfo = resultInfo;
    }


    public ResultInfo getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(ResultInfo resultInfo) {
        this.resultInfo = resultInfo;
    }
}
