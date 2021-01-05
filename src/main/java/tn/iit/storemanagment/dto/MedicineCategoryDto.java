package tn.iit.storemanagment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MedicineCategoryDto  extends IdentifiableDto<Long>{
    private static final long serialVersionUID = 1L;
    private String name;

}
