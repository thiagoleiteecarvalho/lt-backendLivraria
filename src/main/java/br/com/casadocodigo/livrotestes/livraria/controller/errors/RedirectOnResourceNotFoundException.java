package br.com.casadocodigo.livrotestes.livraria.controller.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller resposável por capturar erros páginas não encontradas.
 * 
 * @author Thiago Leite e Fred Viana
 */
@ControllerAdvice
public class RedirectOnResourceNotFoundException {

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public Object handleStaticResourceNotFound(final NoHandlerFoundException ex, HttpServletRequest req, RedirectAttributes redirectAttributes) {
        if (req.getRequestURI().startsWith("/"))
            return this.getApiResourceNotFoundBody(ex, req);
        else {
            redirectAttributes.addFlashAttribute("errorMessage", "Página não encontrada");
            return "redirect:/index.html";
        }
    }

    private ResponseEntity<String> getApiResourceNotFoundBody(NoHandlerFoundException ex, HttpServletRequest req) {
        return new ResponseEntity<>("Not Found !!", HttpStatus.NOT_FOUND);
    }
}