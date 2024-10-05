package com.mdw.code.model;

import jakarta.persistence.*;

@Entity
@Table(name="Categoria")
public class Categoria {

  @Id  @Column  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long categoria_id;

  @Column
  private String nombre;
  
  @Column
  private String descripcion;
  
  @Column(unique = true)
  private String sku;

  public Categoria() {
  }

  public Categoria(Long categoria_id, String nombre, String descripcion, String sku) {
    this.categoria_id = categoria_id;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.sku = sku;
  }


  public Long getCategoria_id() {
    return categoria_id;
  }

  public String getNombre() {
    return nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public String getSku() {
    return sku;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }
}
