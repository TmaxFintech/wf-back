package tmaxfintech.wf.util.response;

import lombok.Builder;

@Builder
public class DefaultResponse<T> {

    private int statusCode;
    private String responseMessage;
    private T data;

    public DefaultResponse(final int statusCode, final String responseMessage) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
        this.data = null;
    }

    public static<T> DefaultResponse<T> response(final int statusCode, final String responseMessage) {
        return response(statusCode, responseMessage, null);
    }

    public static<T> DefaultResponse<T> response(final int statusCode, final String responseMessage, final T data) {
        return DefaultResponse.<T>builder()
                .data(data)
                .statusCode(statusCode)
                .responseMessage(responseMessage)
                .build();
    }

    public DefaultResponse(int statusCode, String responseMessage, T data) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public T getData() {
        return data;
    }
}
