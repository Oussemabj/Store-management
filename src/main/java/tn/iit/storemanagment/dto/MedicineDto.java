package tn.iit.storemanagment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MedicineDto extends IdentifiableDto<Long>{
    private static final long serialVersionUID = 1L;
    private String name;
    private String price;
    private Long categoryId;
}
