package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan on 16/02/2015.
 */
@Entity
@Table (name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id_venta")
    private Long ventaId;

    @Column (name = "fecha")
    private Date fecha;

    @Column (name = "valor_final")
    private BigDecimal valorFinal;

    @OneToMany (cascade = CascadeType.ALL)
    private List<Encomienda> encomiendas;

    @ManyToOne (cascade = CascadeType.ALL)
    private Cliente cliente;

    public Venta(Long ventaId, Date fecha, BigDecimal valorFinal, List<Encomienda> encomiendas, Cliente cliente) {
        this.ventaId = ventaId;
        this.fecha = fecha;
        this.valorFinal = valorFinal;
        this.encomiendas = encomiendas;
        this.cliente = cliente;
    }

    public Long getVentaId() {
        return ventaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public List<Encomienda> getEncomiendas() {
        return encomiendas;
    }

    public void setVentaId(Long ventaId) {
        this.ventaId = ventaId;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public void setEncomiendas(List<Encomienda> encomiendas) {
        this.encomiendas = encomiendas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
