package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan on 16/02/2015.
 */
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ventaId;

    @Column (name = "fecha")
    private Date fecha;

    @Column (name = "valor_final")
    private BigDecimal valorFinal;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "venta")
    private List<Encomienda> encomiendas;

    public Venta(Long ventaId, Date fecha, BigDecimal valorFinal, List<Encomienda> encomiendas) {
        this.ventaId = ventaId;
        this.fecha = fecha;
        this.valorFinal = valorFinal;
        this.encomiendas = encomiendas;
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
}
