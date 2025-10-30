package com.wayas.app.service;

import com.wayas.app.model.Insumo;
import com.wayas.app.model.Proveedor;
import com.wayas.app.model.Requerimiento;
import com.wayas.app.model.RequerimientoDetalle;
import com.wayas.app.repository.IInsumoRepository;
import com.wayas.app.repository.IProveedorRepository;
import com.wayas.app.repository.IRequerimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class RequerimientoService {

    @Autowired private IRequerimientoRepository repoReq;
    @Autowired private IInsumoRepository repoInsumo;
    @Autowired private IProveedorRepository repoProv;

    public List<Requerimiento> listarTodos() {
        return repoReq.findAll();
    }

    public List<Requerimiento> listarPorEstado(String estado) {
        return repoReq.findByEstado(estado);
    }

    public Requerimiento obtenerPorId(Long id) {
        return repoReq.findById(id).orElse(null);
    }

    @Transactional
    public Requerimiento crearRequerimiento(List<Long> insumoIds, List<BigDecimal> cantidades, List<Integer> proveedorIds) {
        Requerimiento req = new Requerimiento();
        req.setFechaGeneracion(LocalDate.now());
        req.setEstado("PENDIENTE");
       
        long count = repoReq.count();
        req.setCodigoRequerimiento(String.format("REQ-%d-%04d", LocalDate.now().getYear(), count + 1));

        if (insumoIds != null) {
            for (int i = 0; i < insumoIds.size(); i++) {
                Long idInsumo = insumoIds.get(i);
                BigDecimal cant = (i < cantidades.size()) ? cantidades.get(i) : BigDecimal.ZERO;
                Integer idProv = (i < proveedorIds.size()) ? proveedorIds.get(i) : null;

                Optional<Insumo> insumoOpt = repoInsumo.findById(idInsumo.intValue());
                Optional<Proveedor> provOpt = (idProv != null) ? repoProv.findById(idProv) : Optional.empty();

                if (insumoOpt.isPresent() && cant.compareTo(BigDecimal.ZERO) > 0) {
                    RequerimientoDetalle detalle = new RequerimientoDetalle();
                    detalle.setInsumo(insumoOpt.get());
                    detalle.setCantidad(cant);
                    provOpt.ifPresent(detalle::setProveedor);
                    req.addDetalle(detalle);
                }
            }
        }

        return repoReq.save(req);
    }

    @Transactional
    public Requerimiento actualizarEstado(Long id, String nuevoEstado) {
        Requerimiento req = obtenerPorId(id);
        if (req != null) {
            req.setEstado(nuevoEstado);
            return repoReq.save(req);
        }
        return null;
    }

    public void eliminar(Long id) {
         repoReq.deleteById(id);
     }
}