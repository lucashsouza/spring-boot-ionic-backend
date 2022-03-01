package br.com.lucashsouza.cursomc.services.validation;

import br.com.lucashsouza.cursomc.domain.Cliente;
import br.com.lucashsouza.cursomc.domain.enums.TipoCliente;
import br.com.lucashsouza.cursomc.dto.ClienteNewDTO;
import br.com.lucashsouza.cursomc.resources.exception.FieldMessage;
import br.com.lucashsouza.cursomc.services.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {


    @Override
    public void initialize(ClienteInsert annotation) {

    }

    @Override
    public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (objDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) &&
            !BR.isValidCPF(objDTO.getCpfOuCnpj())
        ) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (objDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) &&
                !BR.isValidCNPJ(objDTO.getCpfOuCnpj())
        ) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        for (FieldMessage fieldMessage : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(fieldMessage.getMessage()).addPropertyNode(fieldMessage.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
