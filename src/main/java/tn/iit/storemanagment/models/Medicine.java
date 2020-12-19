package tn.iit.storemanagment.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medicine extends Identifiable<Long> {
    private static final long serialVersionUID = 1L;


    @Column(length = 128, nullable = false)
    private String name;
    @Column( nullable = false)
    private float price;

    @JoinColumn(name = "category",referencedColumnName = "id")
    @ManyToOne(optional = false)
    private  MedicineCategory category;

}
