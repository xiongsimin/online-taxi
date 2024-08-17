package kim.aries.apipassenger.enums;

public enum EnumBizException {
    VERIFICATION_CODE_ERR(100001, "验证码校验失败"),

    ;
    private int code;
    private String message;

    EnumBizException(int code, String message) {
        this.code = code;
        this.message = message;
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
}
