package com.wayas.app.service;

import com.wayas.app.model.Insumo;
import com.wayas.app.model.Requerimiento;
import com.wayas.app.model.RequerimientoDetalle;
import com.wayas.app.repository.IInsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class InventarioService {

    @Autowired private IInsumoRepository repoInsumo;
    @Autowired private RequerimientoService reqService;

    @Transactional
    public boolean ingresarRequerimientoAInventario(Long idRequerimiento) {
        Requerimiento req = reqService.obtenerPorId(idRequerimiento);

        if (req == null || !req.getEstado().equals("COMPRADO")) {
            return false; 
        }

        for (RequerimientoDetalle detalle : req.getDetalles()) {
            Insumo insumo = detalle.getInsumo();
            BigDecimal cantidadIngresada = detalle.getCantidad();

            if (insumo != null && cantidadIngresada != null) {
                BigDecimal stockNuevo = insumo.getStockActual().add(cantidadIngresada);
                insumo.setStockActual(stockNuevo);
                repoInsumo.save(insumo); 
            }
        }

        
        reqService.actualizarEstado(idRequerimiento, "INGRESADO");
        return true;
    }

    @Transactional
    public Insumo actualizarStockPorConteo(Integer idInsumo, BigDecimal conteoFisico) {
         Insumo insumo = repoInsumo.findById(idInsumo).orElse(null);
         if (insumo != null && conteoFisico != null && conteoFisico.compareTo(BigDecimal.ZERO) >= 0) {
             insumo.setStockActual(conteoFisico);
             return repoInsumo.save(insumo);
         }
         return null;
     }
}