package kim.aries.apipassenger.dto;

public class VerificationCodeDto {
    /**
     * 乘客手机号
     */
    private String passengerPhone;

    /**
     * 验证码
     */
    private String verificationCode;

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
