package tn.iit.storemanagment.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineCategory extends Identifiable<Long> {
    private static final long serialVersionUID = 1L;

    @NotNull
    @NotEmpty
    @Size(min = 3)
    @Column(length = 128, nullable = false)
    private String name;

//    @OneToMany
//    private Medicine medecine;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    //private Collection<Medicine> medicines;
}
