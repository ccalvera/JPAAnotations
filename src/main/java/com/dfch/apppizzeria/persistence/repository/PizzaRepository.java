package com.dfch.apppizzeria.persistence.repository;

import com.dfch.apppizzeria.persistence.entity.PizzaEntity;
import com.dfch.apppizzeria.service.dto.UpdatePizzaPriceDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    //QueryMethod Available = True y OrderByPrice
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

    //QueryMethod Available = True AND NameIgnoreCase
    Optional<PizzaEntity> findAllByAvailableTrueAndNameIgnoreCase(String name);

    //A diferencia del anterior, este devuelve el primer resultado.
    PizzaEntity findFirstByAvailableTrueAndNameIgnoreCase(String name);

    // Filtra los 3 primeros resultados por precio.
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(Double price);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    int countByVeganTrue();

    @Query(value =
            "UPDATE pizza " +
                    "SET price = :#{#newPizzaPrice.newPrice} " +
                    "WHERE id_pizza = :#{#newPizzaPrice.pizzaId}", nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newPizzaPrice") UpdatePizzaPriceDTO newPizzaPrice);
}
