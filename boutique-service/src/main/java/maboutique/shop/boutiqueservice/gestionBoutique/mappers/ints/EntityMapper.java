package maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints;

import java.util.List;

public interface EntityMapper<E, D>{

    D toDto(E entity);

    E toEntity(D dto);

    List<D> toDto(List<E> entities);

    List<E> toEntity(List<D> dtos);
}
