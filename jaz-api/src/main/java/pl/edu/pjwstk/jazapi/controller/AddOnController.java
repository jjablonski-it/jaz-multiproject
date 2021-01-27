package pl.edu.pjwstk.jazapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jazapi.dto.AddOnDTO;
import pl.edu.pjwstk.jazapi.model.AddOn;
import pl.edu.pjwstk.jazapi.service.AddOnService;

import java.util.Collection;
import java.util.function.Function;

@RestController
@RequestMapping("/addons")
public class AddOnController extends CrudController<AddOn, AddOnDTO> {

    public AddOnController(AddOnService service) {
        super(service);
    }

    @Override
    public Function<AddOn, AddOnDTO> transformToDTO() {
        return null;
    }

    @Override
    public Function<AddOnDTO, EntityModel<AddOnDTO>> addLinksForItem() {
        return null;
    }

    @Override
    public Function<Collection<EntityModel<AddOnDTO>>, CollectionModel<EntityModel<AddOnDTO>>> addLinksForCollection() {
        return null;
    }
}

