package cn.seu.weme.common.result;


/**
 * Created by LCN on 2016-12-17.
 */
public class ResultUtil {

    public static ResultInfo createFail(String message) {
        return new ResultInfo(ResultInfo.TYPE_RESULT_FAIL, message);
    }




    public static ResultInfo createPermissionError(String message) {
        return new ResultInfo(ResultInfo.TYPE_LOGIN_ERROR, message);
    }


    /**
     * 创建成功提示结果
     */
    public static ResultInfo createSuccess(String message) {
        return new ResultInfo(ResultInfo.TYPE_RESULT_SUCCESS, message);
    }


    /**
     * 创建成功提示结果
     */
    public static ResultInfo createSuccess(String message, Object data) {
        return new ResultInfo(ResultInfo.TYPE_RESULT_SUCCESS, message, data);
    }


    /**
     * 抛出异常
     *
     * @param resultInfo
     * @throws ExceptionResultInfo
     */
    public static void throwExcepion(ResultInfo resultInfo) throws ExceptionResultInfo {
        throw new ExceptionResultInfo(resultInfo);
    }


    /**
     * 抛出异常
     *
     * @param message
     * @throws ExceptionResultInfo
     */
    public static void throwExcepion(String message) throws ExceptionResultInfo {
        throw new ExceptionResultInfo(ResultUtil.createFail(message));
    }


    /**
     * 创建提交结果信息
     *
     * @param resultInfo
     * @return
     */
    public static SubmitResultInfo createSubmitResult(ResultInfo resultInfo) {
        return new SubmitResultInfo(resultInfo);
    }
}
