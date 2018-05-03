package com.luizfelipe.cursomc.services.validation;

import com.luizfelipe.cursomc.domain.Cliente;
import com.luizfelipe.cursomc.domain.enums.TipoCliente;
import com.luizfelipe.cursomc.dto.ClienteNewDTO;
import com.luizfelipe.cursomc.repositories.ClienteRepository;
import com.luizfelipe.cursomc.resources.exception.FieldMessage;
import com.luizfelipe.cursomc.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClientInsert clientInsert) {

    }

    @Override
    public boolean isValid(ClienteNewDTO clienteNewDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (clienteNewDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(clienteNewDTO.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "Cpf inválido"));
        }

        if (clienteNewDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(clienteNewDTO.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "Cnpj inválido"));
        }

        Cliente aux = clienteRepository.findByEmail(clienteNewDTO.getEmail());
        if (aux != null){
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
