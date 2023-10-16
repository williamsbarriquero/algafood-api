package br.com.williamsbarriquero.algafood.domain.model;

import br.com.williamsbarriquero.algafood.core.validation.Groups;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Cozinha {

    @NotNull(groups = Groups.CozinhaId.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "cozinha")
    @ToString.Exclude
    private List<Restaurante> restaurantes = new ArrayList<>();

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Cozinha cozinha = (Cozinha) o;
        return Objects.equals(getId(), cozinha.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
