/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.dev.sophia.fastAndFurious.domain.repository;

import br.dev.sophia.fastAndFurious.domain.model.Pedido;
import br.dev.sophia.fastAndFurious.domain.model.StatusPedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


    public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    
        List<Pedido> findByStatus(StatusPedido status);
    }
