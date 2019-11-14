package cm.xiaochi.ssm.common.constart;

public interface ResponseStatusConstart {
    /**
     * 响应状态码，1表示成功
     */
    public static final int RESPONSE_STATUS_SUCCESS=1;

    /**
     * 响应状态码，0表示失败
     */
    public static final int RESPONSE_STATUS_FAIL=0;

    /**
     * 响应状态码，2表示没有权限
     */
    public static final int RESPONSE_STATUS_NO_PERMISSION=2;
}
