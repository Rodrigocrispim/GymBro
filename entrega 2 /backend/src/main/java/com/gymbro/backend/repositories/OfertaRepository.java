package com.gymbro.backend.repositories;


import com.gymbro.backend.models.Oferta; 
import com.gymbro.backend.dto.OfertaDTO; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// @Repository: Diz ao Spring que esta é uma classe que fala com a BD
@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer> {

  
    // Parece SQL, mas usamos os NOMES DAS CLASSES JAVA, não das tabelas SQL.

    @Query("SELECT new com.gymbro.backend.dto.OfertaDTO(" +
           "    o.oferta_id, " +
           "    o.titulo, " +
           "    o.descricao, " +
           "    u.nome_completo, " +     
           "    l.concelho, " +          
           "    n.nivel_nome, " +       
           "    t.nome " +              
           ") " +
           "FROM Oferta o " + 
           // Em baixo, usamos os NOMES DAS VARIÁVEIS de relação da nossa Oferta.java
           "JOIN o.utilizador u " + 
           "JOIN o.localizacao l " +
           "JOIN o.nivelTreino n " +
           "JOIN o.tipoTreino t " +
           "WHERE o.status_oferta = 'ABERTA'") 
    List<OfertaDTO> findOfertasAbertasDTO();

}
