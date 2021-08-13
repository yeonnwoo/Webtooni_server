package com.webtooni.webtooniverse.domain.user.security.tokenException;

import com.webtooni.webtooniverse.domain.user.security.tokenException.CommonResult;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    public enum CommonResponse {
        SUCCESS(0, "성공하였습니다.");

        int code;
        String msg;

        CommonResponse(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
    // 실패 결과만 처리하는 메소드
    public CommonResult getFailResult(int code, String msg) {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
