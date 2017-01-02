package cn.seu.weme.common.result;

/**
 * Created by LCN on 2016-12-17.
 */
public class SubmitResultInfo {
    private ResultInfo resultInfo;

    public SubmitResultInfo(ResultInfo resultInfo) {
        this.resultInfo = resultInfo;
    }

    public ResultInfo getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(ResultInfo resultInfo) {
        this.resultInfo = resultInfo;
    }
}
