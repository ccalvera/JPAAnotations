package com.dfch.apppizzeria.service;

import com.dfch.apppizzeria.persistence.entity.PizzaEntity;
import com.dfch.apppizzeria.persistence.repository.PizzaPagSortRepository;
import com.dfch.apppizzeria.persistence.repository.PizzaRepository;
import com.dfch.apppizzeria.service.dto.UpdatePizzaPriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaService {
    //private final JdbcTemplate jdbcTemplate;
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    //Usando JDBCTemplate se deben realizar querys nativos de SQL, lo cual se puede simplificar con el uso de JPA
    /*public List<PizzaEntity> getAll(){
        return this.jdbcTemplate.query("SELECT * FROM pizza WHERE available = 1", new BeanPropertyRowMapper<>(PizzaEntity.class));
    }*/

    public List<PizzaEntity> getAll() {
        return this.pizzaRepository.findAll();
    }

    public Page<PizzaEntity> getAllPages(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    public List<PizzaEntity> getAvailable() {
        //Devuelve el numero de pizzas veganas disponibles.
        System.out.println(this.pizzaRepository.countByVeganTrue());
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    @Transactional
    public void updatePrice(UpdatePizzaPriceDTO dto) {
        this.pizzaRepository.updatePrice(dto);
    }

    public PizzaEntity getByName(String name) {
        //Se usa mediante el uso de Optional, ya que puede que no exista la pizza con el nombre indicado.
        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("No se ha encontrado la pizza con nombre " + name));

        //Se usa solo retornando el PizzaEntity sin el Optional.
        //return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }

    public List<PizzaEntity> getWith(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public PizzaEntity getById(Integer id) {
        return this.pizzaRepository.findById(id).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return this.pizzaRepository.save(pizza);
    }

    public void delete(int idPizza) {
        this.pizzaRepository.deleteById(idPizza);
    }

    public boolean exist(int idPizza) {
        return this.pizzaRepository.existsById(idPizza);
    }

}
