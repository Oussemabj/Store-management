package tn.iit.storemanagment.services;


import tn.iit.storemanagment.dto.IdentifiableDto;
import tn.iit.storemanagment.models.Identifiable;

import java.io.Serializable;
import java.util.List;

public interface CommonService<ENTITY extends Identifiable<PK>, DTO extends IdentifiableDto<PK>, PK extends Serializable> {

    /**
     * Get the entity by ID.
     *
     * @param id the entity ID.
     * @return the result encapsulated in a DTO.
     */
    DTO getById(PK id);

    /**
     * Get the entity by ID with fetches.
     *
     * @param id      the entity ID.
     * @param fetches the  other entities to fetches.
     * @return the result encapsulated in a DTO.
     */
    DTO getByIdWithFetches(PK id, String... fetches);

    /**
     * Get all available entities in the database.
     *
     * @return the List of entities encapsulated in DTO.
     */
    List<DTO> getAll();

    /**
     * Delete the given entity by ID.
     *
     * @param id the entity ID.
     */
    void deleteById(PK id);

}
