package com.mdw.code.controller;

import com.mdw.code.dto.ProductoDTO;
import com.mdw.code.model.Categoria;
import com.mdw.code.model.Producto;
import com.mdw.code.service.CategoriaService;
import com.mdw.code.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController
// @RequestMapping(path= "api/v1/producto")
@Controller
public class ProductoController {

  @Autowired
  private ProductoService productoService;
  @Autowired
  private CategoriaService categoriaService;

  @GetMapping("")
  public String index() {
    return "static/index";
  }

  @GetMapping("/inicio")
  public String inicio() {
    return "inicio";
  }

  @GetMapping("/productos")
  public String listarProductosFiltrados(@RequestParam(value = "categorias", required = false) List<Long> categoriasSeleccionadas, Model model) {

    List<Producto> productos;

    if (categoriasSeleccionadas != null && !categoriasSeleccionadas.isEmpty()) {
      productos = productoService.consultarProductosByCategoriasIn(categoriasSeleccionadas);
      System.out.println(productos.size());

    } else {
      productos = productoService.consultarProductos(); // Si no hay filtros, mostrar todos los productos
    }

    List<Categoria> categorias = categoriaService.consultarCategorias();

    model.addAttribute("productos", productos);
    model.addAttribute("categorias", categorias);

    return "productos";
  }

  @PostMapping("/guardarProducto")
  public Producto guardarProducto(@RequestBody ProductoDTO productoDTO) {
    Producto producto = new Producto();
    producto = productoDTO.obtenerProducto(productoDTO);
    producto.setCategoria(categoriaService.encontrarCategoriaPorId(productoDTO.getCategoria_id()));
    return productoService.guardarProducto(producto);
  }

  @GetMapping("/obtenerProductos")
  public List<Producto> obtenerProducto() {
    return productoService.consultarProductos();
  }

  @GetMapping("/encontrarProductoPorId/{producto_id}")
  public Producto encontrarProductoPoriD(@PathVariable Long producto_id) {
    return productoService.encontrarProductoPorID(producto_id);
  }

  @GetMapping("/encontrarProductoPorSku/{sku}")
  public Producto encontrarProductoPorSku(@PathVariable String sku) {
    return productoService.encontrarProductoPorSku(sku);
  }

  @GetMapping("/encontrarProductosPorCategoria/{nombre_categoria}")
  public List<Producto> encontrarProductosPorCategoria(@PathVariable String nombre_categoria) {
    return productoService.encontrarProductosPorCategoria(nombre_categoria);
  }

  @PutMapping("/actualizarProducto")
  public Producto actualizarProducto(@RequestBody ProductoDTO productoDTO) {
    Producto producto = new Producto();
    producto = productoDTO.obtenerProducto(productoDTO);
    producto.setCategoria(categoriaService.encontrarCategoriaPorId(productoDTO.getCategoria_id()));
    return productoService.actualizarProducto(producto);
  }

  @DeleteMapping("/eliminarProducto/{producto_id}")
  public boolean eliminarProducto(@PathVariable Long producto_id) {
    return productoService.eliminarProducto(producto_id);
  }

}
