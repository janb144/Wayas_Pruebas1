package com.wayas.app.service;
import com.wayas.app.model.Proveedor;
import com.wayas.app.repository.IProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProveedorService {
    @Autowired private IProveedorRepository repoProv;

    public List<Proveedor> listarTodos() { return repoProv.findAll(); }
    public Proveedor guardar(Proveedor p) { return repoProv.save(p); }
    public Proveedor obtenerPorId(Integer id) { return repoProv.findById(id).orElse(null); }
    public void eliminar(Integer id) { repoProv.deleteById(id); }
}