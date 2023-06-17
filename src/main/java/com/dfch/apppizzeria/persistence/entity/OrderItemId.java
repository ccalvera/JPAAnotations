package com.dfch.apppizzeria.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//Implementa Serializable para que pueda ser incrustada dentro de otro entity
public class OrderItemId implements Serializable {
    //Es importante que los tipos de datos sean los mismos que los de la clase OrderItemEntity
    private Integer idOrder;
    private Integer idItem;

    //Funcio generada mediante el Wizard de IntelliJ
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemId that)) return false;
        return Objects.equals(idOrder, that.idOrder) && Objects.equals(idItem, that.idItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idItem);
    }
}

