package com.wayas.app.repository;
import com.wayas.app.model.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IInsumoRepository extends JpaRepository<Insumo, Integer> {
    List<Insumo> findByDescripcionContainingIgnoreCase(String terminoBusqueda);

    @Query("SELECT i FROM Insumo i WHERE i.stockActual <= i.stockMinimo AND i.estado = 'activo'")
    List<Insumo> findInsumosBajoMinimo();
}