package tn.iit.storemanagment.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medicine extends Identifiable<Long> {
    private static final long serialVersionUID = 1L;


    @NotNull
    @NotEmpty
    @Size(min = 3)
    @Column(length = 128, nullable = false)
    private String name;


    @Column( nullable = false)
    private float price;
    @ManyToOne
    private  MedicineCategory medicineCategory;

}
