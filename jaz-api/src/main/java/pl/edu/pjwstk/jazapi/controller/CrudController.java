package pl.edu.pjwstk.jazapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jazapi.service.CrudService;
import pl.edu.pjwstk.jazapi.service.DbEntity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class CrudController<T extends DbEntity> {
    private final CrudService<T> service;

    public CrudController(CrudService<T> service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Map<String, Object>>> getAll(@RequestParam(defaultValue = "4") int size,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "asc,id") String sort) {
        try {
            List<T> all = service.getAll(size, page, sort);
            List<Map<String, Object>> payload = all.stream()
                    .map(obj -> transformToDTO().apply(obj))
                    .collect(Collectors.toList());

            Map<String, Object> pagingInfo = new LinkedHashMap<>();
            pagingInfo.put("pageSize", size);
            pagingInfo.put("page", page);
            pagingInfo.put("sort", sort);
            payload.add(pagingInfo);

            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable("id") Long id) {
        try {
            T obj = service.getById(id);
            Map<String, Object> payload = transformToDTO().apply(obj);

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

    public abstract Function<T, Map<String, Object>> transformToDTO();
}
