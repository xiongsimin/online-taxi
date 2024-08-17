package kim.aries.internalcommon.enums;

public enum EnumUserIdentity {
    PASSENGER("0", "乘客"),
    DRIVER("1", "司机"),
    ;
    private String code;
    private String name;

    EnumUserIdentity(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
