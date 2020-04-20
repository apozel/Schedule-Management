package fr.isen.m1.schedule.utilities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import fr.isen.m1.schedule.converter.PositionConverter;


/**
 * Position
 */
@Entity
@Table(name = "gps_coordinates")
public class Position implements Serializable{


    /**
    *
    */
    private static final long serialVersionUID = 8662226952154552954L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gpsc")
    private Long id;
    @Column(name = "longitude")
    @Convert(converter = PositionConverter.class)
    private Double x;
    @Column(name = "latitude")
    @Convert(converter = PositionConverter.class)
    private Double y;
    @OneToOne(mappedBy = "lieuDeDepart")
    @JoinColumn(name = "id_doc")
    private Doctor idDoc;

    public Position() {

    }

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position(int x, int y) {
        this.x = (double) x;
        this.y = (double) y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


    @Override
    public String toString() {
        return "Position [x=" + x + ", y=" + y + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }}