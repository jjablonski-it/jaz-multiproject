package pl.edu.pjwstk.jazapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jazapi.model.AddOn;
import pl.edu.pjwstk.jazapi.service.AddOnService;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@RestController
@RequestMapping("/addons")
public class AddOnController extends CrudController<AddOn> {

    public AddOnController(AddOnService service) {
        super(service);
    }

    public Function<AddOn, Map<String, Object>> transformToDTO() {
        return addOn -> {
            var payload = new LinkedHashMap<String, Object>();
            payload.put("id", addOn.getId());
            payload.put("name", addOn.getName());
            payload.put("price", addOn.getPrice());
            payload.put("car", addOn.getCar().getId());

            return payload;
        };
    }
}

