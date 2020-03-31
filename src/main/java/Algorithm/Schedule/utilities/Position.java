package Algorithm.Schedule.utilities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import Algorithm.Marchant.Moteur.Noeud;
import Algorithm.Schedule.converter.PositionConverter;

/**
 * Position
 */
public class Position {

    
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
    private Docteur idDoc;

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

    public boolean equalsPosition(Noeud no) {
        if (no.getX() == this.getX() && no.getY() == this.getY()) {
            return true;
        }
        return false;
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