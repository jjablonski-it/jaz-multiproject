package pl.edu.pjwstk.jazapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jazapi.service.CrudService;
import pl.edu.pjwstk.jazapi.service.Identifiable;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class CrudController<T extends Identifiable, R extends Identifiable> {
    private final CrudService<T> service;

    public CrudController(CrudService<T> service) {
        this.service = service;
    }

    public ResponseEntity<CollectionModel<EntityModel<R>>> getAll() {
        return getAll(4, 0, "asc,id");
    }

    @GetMapping()
    public ResponseEntity<CollectionModel<EntityModel<R>>> getAll(@RequestParam(defaultValue = "4") int size,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "asc,id") String sort) {
        try {
            var items = service.getAll(size, page, sort);
            var itemsWithLinks = items.stream()
                    .map(obj -> transformToDTO().apply(obj))
                    .map(obj -> addLinksForItem().apply(obj))
                    .collect(Collectors.toList());

            var payload = addLinksForCollection().apply(itemsWithLinks);

            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<R>> getById(@PathVariable("id") Long id) {
        try {
            EntityModel<R> payload = addLinksForItem().apply(transformToDTO().apply(service.getById(id)));

            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody T t) {
        try {
            service.createOrUpdate(t);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody T t) {
        try {
            service.createOrUpdate(t);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public abstract Function<T, R> transformToDTO();

    public abstract Function<R, EntityModel<R>> addLinksForItem();

    public abstract Function<Collection<EntityModel<R>>, CollectionModel<EntityModel<R>>> addLinksForCollection();
}
