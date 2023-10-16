package br.com.williamsbarriquero.algafood.api.assembler;

import br.com.williamsbarriquero.algafood.api.model.CidadeModel;
import br.com.williamsbarriquero.algafood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeModelAssembler {

    private final ModelMapper modelMapper;

    public CidadeModelAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CidadeModel toModel(Cidade cidade) {
        return modelMapper.map(cidade, CidadeModel.class);
    }

    public List<CidadeModel> toCollectionModel(List<Cidade> cidades) {
        return cidades.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}