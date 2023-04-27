package com.ccsw.tutorial.customer;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.tutorial.customer.model.Customer;
import com.ccsw.tutorial.customer.model.CustomerDto;

/**
 * @author ccsw
 *
 */
public interface CustomerService {

    /**
     * Recupera un {@link Customer} a través de su ID
     *
     * @param id PK de la entidad
     * @return {@link Customer}
     */
    Customer get(Long id);

    /**
     * Método para recuperar un listado paginado de {@link Customer}
     *
     * @param dto dto de búsqueda
     * @return {@link Page} de {@link Customer}
     */
    List<Customer> findAll();

    /**
     * Método para crear o actualizar un {@link Customer}
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, CustomerDto dto) throws Exception;

    /**
     * Método para crear o actualizar un {@link Customer}
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;

}