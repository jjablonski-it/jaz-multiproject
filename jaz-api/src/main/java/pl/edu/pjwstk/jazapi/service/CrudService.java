package pl.edu.pjwstk.jazapi.service;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class CrudService<T extends DbEntity> {
    CrudRepository<T, Long> repository;

    public CrudService(CrudRepository<T, Long> repository) {
        this.repository = repository;
    }

    public List<T> getAll() {
        Iterable<T> items = repository.findAll();
        var itemList = new ArrayList<T>();

        items.forEach(itemList::add);

        return itemList;
    }

    public T getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        Optional<T> item = repository.findById(id);

        if (item.isPresent()) {
            repository.delete(item.orElseThrow());
        }
    }

    public abstract T createOrUpdate(T updateEntity);
}
