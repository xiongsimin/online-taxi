package kim.aries.internalcommon.dto;

import kim.aries.internalcommon.enums.EnumCommonResponseCode;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CommonResponseResultDto<T> {
    private int code;
    private String message;
    private T data;

    public static <T> CommonResponseResultDto<T> success(T data) {
        return new CommonResponseResultDto<T>().setCode(EnumCommonResponseCode.SUCCESS.getCode())
                .setMessage(EnumCommonResponseCode.SUCCESS.getName()).setData(data);
    }

    public static <T> CommonResponseResultDto<T> fail(T data) {
        return new CommonResponseResultDto<T>().setCode(EnumCommonResponseCode.FAIL.getCode())
                .setMessage(EnumCommonResponseCode.FAIL.getName()).setData(data);
    }

    public static <T> CommonResponseResultDto<T> fail(int code, String message) {
        return new CommonResponseResultDto<T>().setCode(code).setMessage(message);
    }

    public static <T> CommonResponseResultDto<T> fail(int code, String message, T data) {
        return new CommonResponseResultDto<T>().setCode(code).setMessage(message).setData(data);
    }
}
