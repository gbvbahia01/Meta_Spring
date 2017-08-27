/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.volvo.spring.repositories;
import br.com.guilherme.bahia.volvo.spring.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Guilherme
 */
public interface DepartmentRepositorie extends JpaRepository<Department, Integer>{
}
