package cqut.icode.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author tq
 * @date 2019/12/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {
    private HttpStatus status;
    private String error;
    private String message;
    private String detail;
    private String path;

    public static final class ApiErrorResponseBuilder {
        private HttpStatus status;
        private String error;
        private String message;
        private String detail;
        private String path;

        public ApiErrorResponseBuilder() {
        }

        public static ApiErrorResponseBuilder anApiErrorResponse() {
            return new ApiErrorResponseBuilder();
        }

        public ApiErrorResponseBuilder withStatus(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ApiErrorResponseBuilder withError(String error) {
            this.error = error;
            return this;
        }

        public ApiErrorResponseBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ApiErrorResponseBuilder withDetails(String detail) {
            this.detail = detail;
            return this;
        }

        public ApiErrorResponseBuilder withPath(String path) {
            this.path = path;
            return this;
        }

        public ApiErrorResponse build() {
            ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
            apiErrorResponse.status = this.status;
            apiErrorResponse.error = this.error;
            apiErrorResponse.message = this.message;
            apiErrorResponse.detail = this.detail;
            apiErrorResponse.path = this.path;

            return apiErrorResponse;
        }
    }
}
