package org.mestresolutions.tasks.domain.tasks;

import org.hibernate.validator.constraints.Length;

public record TaskInputDto( @Length(min = 10, max = 60, message = "Descrição deve ter entre 10 e 60 caracteres") String descricao) {
  
}
