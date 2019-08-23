/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Employee;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author willi
 */
public class EmployeeDTO {
    
    private Long id;
    private String name;
    private String address;

    public EmployeeDTO(Employee e) {
        id = e.getId();
        name = e.getName();
        address = e.getAddress();
    }
    
    
    
}
