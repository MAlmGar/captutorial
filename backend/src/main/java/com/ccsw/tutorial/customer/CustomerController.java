package com.ccsw.tutorial.customer;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorial.customer.model.Customer;
import com.ccsw.tutorial.customer.model.CustomerDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author ccsw
 * 
 */
@Tag(name = "Customer", description = "API of Customer")
@RequestMapping(value = "/customer")
@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    DozerBeanMapper mapper;

    /**
     * Método para recuperar todos los {@link Customer}
     * 
     * @return {@link List} de {@link CustomerDto}
     */

    @Operation(summary = "Find", description = "Method that returns a list of Customers")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<CustomerDto> findAll() {

        List<Customer> customers = this.customerService.findAll();

        // * return this.categoryService.findAll() - Esto es lo que había antes

        return customers.stream().map(e -> mapper.map(e, CustomerDto.class)).collect(Collectors.toList());
    }

    /**
     * Método para crear o actualizar un {@link Customer}
     * 
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */

    @Operation(summary = "Save or Update", description = "Method that saves or updates a Customer")
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody CustomerDto dto)
            throws Exception {

        this.customerService.save(id, dto);
    }

    /**
     * Método para borrar un {@link Customer}
     * 
     * @param id PK de la entidad
     */
    @Operation(summary = "Delete", description = "Method that deletes a Customer")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {

        this.customerService.delete(id);
    }
}
