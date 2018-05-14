package com.luizfelipe.cursomc.services.validation;

import com.luizfelipe.cursomc.domain.Cliente;
import com.luizfelipe.cursomc.domain.enums.TipoCliente;
import com.luizfelipe.cursomc.dto.ClienteDTO;
import com.luizfelipe.cursomc.dto.ClienteNewDTO;
import com.luizfelipe.cursomc.repositories.ClienteRepository;
import com.luizfelipe.cursomc.resources.exception.FieldMessage;
import com.luizfelipe.cursomc.services.validation.utils.BR;
import com.sun.net.httpserver.HttpServer;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClientUpdate clientUpdate) {

    }

    @Override
    public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));


        List<FieldMessage> list = new ArrayList<>();


        Cliente aux = clienteRepository.findByEmail(clienteDTO.getEmail());
        if (aux != null && !aux.getId().equals(uriId)){
            list.add(new FieldMessage("email", "Email já existe"));
        }

        // Inclua os testes aqui, inserindo erros na lista
        for (FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFiledName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
