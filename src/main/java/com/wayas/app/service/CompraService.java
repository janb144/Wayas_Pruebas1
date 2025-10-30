package com.wayas.app.service;

import com.wayas.app.model.Compra;
import com.wayas.app.model.Proveedor;
import com.wayas.app.model.Requerimiento;
import com.wayas.app.repository.ICompraRepository;
import com.wayas.app.repository.IProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired private ICompraRepository repoCompra;
    @Autowired private RequerimientoService reqService; 
    @Autowired private IProveedorRepository repoProv;

    public List<Compra> listarTodas() {
        return repoCompra.findAll();
    }

    public Compra obtenerPorId(Long id) {
        return repoCompra.findById(id).orElse(null);
    }

    @Transactional
    public Compra registrarCompra(Long idRequerimiento, LocalDate fechaCompra, Integer idProveedor,
                                  BigDecimal montoTotal, String nroFactura, String detalleTexto) {
        Requerimiento req = reqService.obtenerPorId(idRequerimiento);
        Optional<Proveedor> provOpt = repoProv.findById(idProveedor);

        if (req == null || provOpt.isEmpty()) {
            throw new IllegalArgumentException("Requerimiento o Proveedor no válido.");
        }
        if (!req.getEstado().equals("PENDIENTE") && !req.getEstado().equals("ENVIADO")) {
             throw new IllegalStateException("Solo se pueden registrar compras de requerimientos PENDIENTES o ENVIADOS.");
        }


        Compra compra = new Compra();
        compra.setRequerimiento(req);
        compra.setFechaCompra(fechaCompra);
        compra.setProveedor(provOpt.get());
        compra.setMontoTotal(montoTotal);
        compra.setNroFactura(nroFactura);
        compra.setDetalleInsumosComprados(detalleTexto);
        compra.setEstado("REGISTRADA");
        long count = repoCompra.count();
        compra.setCodigoCompra(String.format("COMP-%d-%04d", LocalDate.now().getYear(), count + 1));

        reqService.actualizarEstado(idRequerimiento, "COMPRADO");

        return repoCompra.save(compra);
    }

    @Transactional
    public Compra anularCompra(Long idCompra) {
        Compra compra = obtenerPorId(idCompra);
        if (compra != null && compra.getEstado().equals("REGISTRADA")) {
            compra.setEstado("ANULADA");
           
            return repoCompra.save(compra);
        }
        return null;
    }
}