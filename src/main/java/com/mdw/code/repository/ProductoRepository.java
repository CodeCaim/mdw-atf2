package com.mdw.code.repository;

import com.mdw.code.model.Categoria;
import com.mdw.code.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

  Producto findBySku(String sku);
  
  List<Producto> findByCategoria(Categoria categoria);

  // Busca productos que pertenezcan a las categorías seleccionadas
  @Query("SELECT p FROM Producto p JOIN p.categorias c WHERE c.id IN :categorias")
  List<Producto> findByCategoriasIn(@Param("categorias") List<Long> categorias);
}
