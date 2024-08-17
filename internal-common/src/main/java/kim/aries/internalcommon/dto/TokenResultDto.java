package kim.aries.internalcommon.dto;

public class TokenResultDto {
    /**
     * 手机号
     */
    private String phone;
    /**
     * 身份 {@link kim.aries.internalcommon.enums.EnumUserIdentity}
     */
    private String identity;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
