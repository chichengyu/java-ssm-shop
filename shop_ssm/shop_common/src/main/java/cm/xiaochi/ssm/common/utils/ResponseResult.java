package cm.xiaochi.ssm.common.utils;

import cm.xiaochi.ssm.common.constart.ResponseStatusConstart;

public class ResponseResult {
    //状态码
    private int code;
    //消息
    private String message;
    //返回数据
    private Object data;

    public ResponseResult () {}

    public ResponseResult (int code,String message,Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功
    public static ResponseResult responseSuccess(){
        return new ResponseResult(ResponseStatusConstart.RESPONSE_STATUS_SUCCESS,"success",null);
    }

    public static ResponseResult responseSuccess(Object data){
        return new ResponseResult(ResponseStatusConstart.RESPONSE_STATUS_SUCCESS,"success",data);
    }

    public static ResponseResult responseSuccess(String message){
        return new ResponseResult(ResponseStatusConstart.RESPONSE_STATUS_SUCCESS,message,null);
    }
    // 失败
    public static ResponseResult responseError(){
        return new ResponseResult(ResponseStatusConstart.RESPONSE_STATUS_FAIL,"fail",null);
    }

    public static ResponseResult responseError(Object data){
        return new ResponseResult(ResponseStatusConstart.RESPONSE_STATUS_FAIL,"fail",data);
    }

    public static ResponseResult responseError(String message){
        return new ResponseResult(ResponseStatusConstart.RESPONSE_STATUS_FAIL,message,null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
