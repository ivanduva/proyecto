package model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

/**
 * Created by Ivan on 05/04/2015.
 */
@Entity
@Table(name = "franja_horaria")
public class FranjaHoraria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id_franja_horaria")
    private Long franjaId;

    @Column (name = "dia")
    private Date dia;

    @Column (name = "hora_fin")
    private Time horaFin;

    @Column (name = "hora_inicio")
    private Time horaInicio;

    public FranjaHoraria(Long franjaId, Date dia, Time horaFin, Time horaInicio) {
        this.franjaId = franjaId;
        this.dia = dia;
        this.horaFin = horaFin;
        this.horaInicio = horaInicio;
    }

    public Long getFranjaId() {
        return franjaId;
    }

    public void setFranjaId(Long franjaId) {
        this.franjaId = franjaId;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }
}
