package tn.iit.storemanagment.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineCategory extends Identifiable<Long> {
    private static final long serialVersionUID = 1L;
    @Column(length = 128, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Collection<Medicine> medicines;
}
