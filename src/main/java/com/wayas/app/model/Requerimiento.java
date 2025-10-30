package com.wayas.app.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_requerimiento")
public class Requerimiento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 20) private String codigoRequerimiento; 
    private LocalDate fechaGeneracion;
    private LocalDate fechaRequerida; 
    private String estado; 

    @OneToMany(mappedBy = "requerimiento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RequerimientoDetalle> detalles = new ArrayList<>();

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoRequerimiento() {
		return codigoRequerimiento;
	}

	public void setCodigoRequerimiento(String codigoRequerimiento) {
		this.codigoRequerimiento = codigoRequerimiento;
	}

	public LocalDate getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(LocalDate fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public LocalDate getFechaRequerida() {
		return fechaRequerida;
	}

	public void setFechaRequerida(LocalDate fechaRequerida) {
		this.fechaRequerida = fechaRequerida;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<RequerimientoDetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<RequerimientoDetalle> detalles) {
		this.detalles = detalles;
	}

	public void addDetalle(RequerimientoDetalle detalle) {
        detalles.add(detalle);
        detalle.setRequerimiento(this);
    }

}