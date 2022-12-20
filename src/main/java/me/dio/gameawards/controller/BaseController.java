package me.dio.gameawards.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import me.dio.gameawards.service.exception.BusinessException;
import me.dio.gameawards.service.exception.NoContentException;

@RequestMapping("/api")
public abstract class BaseController {

	@ExceptionHandler(NoContentException.class)
	public ResponseEntity<Void> handlerNoContentException(NoContentException exception) {
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorDTO> handlerBusinessException(BusinessException exception) {
		ErrorDTO error = new ErrorDTO(exception.getMessage());
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ErrorDTO> handlerUnexpectedException(Throwable exception) {
		exception.printStackTrace();
		ErrorDTO error = new ErrorDTO("Erro Inesperado!");
		return ResponseEntity.internalServerError().body(error);
	}

}
