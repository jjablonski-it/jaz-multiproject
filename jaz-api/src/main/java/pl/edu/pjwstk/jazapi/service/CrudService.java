package pl.edu.pjwstk.jazapi.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class CrudService<T extends DbEntity> {
    JpaRepository<T, Long> repository;

    public CrudService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    public List<T> getAll(int size, int page, String sort) {
        Pageable paging = buildPaging(size, page, sort);
        Iterable<T> items = repository.findAll(paging);
        var itemList = new ArrayList<T>();

        items.forEach(itemList::add);

        return itemList;
    }

    private Pageable buildPaging(int size, int page, String sort) {
        Sort.Direction direction = Sort.Direction.ASC;
        String[] sortStringArray = sort.split(",");
        if (sortStringArray.length == 0)
            sortStringArray = new String[]{sort};

        String firstKey = sortStringArray[0].toLowerCase();
        String[] sortProperties = sortStringArray;

        if (firstKey.equals("asc")) {
            sortProperties = Arrays.stream(sortStringArray).skip(1).toArray(String[]::new);
        } else if (firstKey.equals("desc")) {
            direction = Sort.Direction.DESC;
            sortProperties = Arrays.stream(sortStringArray).skip(1).toArray(String[]::new);
        }

        return PageRequest.of(page, size, Sort.by(direction, sortProperties));
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
