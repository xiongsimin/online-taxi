package kim.aries.internalcommon.enums;

public enum EnumCommonResponseCode {
    SUCCESS(1, "success"),
    FAIL(0, "fail"),
    ;
    private int code;
    private String name;

    EnumCommonResponseCode(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
