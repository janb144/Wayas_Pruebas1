package com.wayas.app.model;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_proveedor")
public class Proveedor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProv;
    @Column(nullable = false, length = 100) private String razonSocial;
    private String direccion;
    private String contacto;
    private String email;

    
    private int puntajePuntualidad = 3;
    private int puntajeCalidad = 3;
    private int puntajeDisponibilidad = 3;
	public Integer getIdProv() {
		return idProv;
	}
	public void setIdProv(Integer idProv) {
		this.idProv = idProv;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPuntajePuntualidad() {
		return puntajePuntualidad;
	}
	public void setPuntajePuntualidad(int puntajePuntualidad) {
		this.puntajePuntualidad = puntajePuntualidad;
	}
	public int getPuntajeCalidad() {
		return puntajeCalidad;
	}
	public void setPuntajeCalidad(int puntajeCalidad) {
		this.puntajeCalidad = puntajeCalidad;
	}
	public int getPuntajeDisponibilidad() {
		return puntajeDisponibilidad;
	}
	public void setPuntajeDisponibilidad(int puntajeDisponibilidad) {
		this.puntajeDisponibilidad = puntajeDisponibilidad;
	}
    
    
}