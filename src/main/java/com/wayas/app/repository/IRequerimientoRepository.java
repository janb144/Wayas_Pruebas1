package com.wayas.app.repository;
import com.wayas.app.model.Requerimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IRequerimientoRepository extends JpaRepository<Requerimiento, Long> {
    List<Requerimiento> findByEstado(String estado);
    // Puedes añadir más métodos de búsqueda si necesitas, ej: findByFechaGeneracionBetween(...)
}