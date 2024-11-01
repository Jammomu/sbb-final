package com.mysite.sbbfinal.file.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mysite.sbbfinal.file.dto.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FileNotFoundException.class)
    public String handleFileNotFoundException(
            FileNotFoundException e,
            Model model,
            HttpServletRequest request) {

        log.error("파일 다운로드 오류: {}", e.getMessage());

        ErrorResponse errorResponse = ErrorResponse.of(
                "FILE_NOT_FOUND",
                e.getMessage(),
                request.getRequestURI()
        );
        model.addAttribute("error", errorResponse);

        return "error/error";  // error.html 템플릿을 사용
    }

//    @ExceptionHandler(IOException.class)
//    public String handleIOException(
//            IOException e,
//            Model model,
//            HttpServletRequest request) {
//
//        log.error("IO Exception: {}", e.getMessage(), e);
//
//        ErrorResponse error = ErrorResponse.of(
//                "FILE_IO_ERROR",
//                "파일 처리 중 오류가 발생했습니다.",
//                request.getRequestURI()
//        );
//        model.addAttribute("error", error);
//
//        return "error/error";
//    }
}
