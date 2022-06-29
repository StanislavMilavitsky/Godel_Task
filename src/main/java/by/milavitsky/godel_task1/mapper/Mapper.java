package by.milavitsky.godel_task1.mapper;

public interface Mapper <T, K> {

    T toDto(K k);

    K fromDto (T t);
}
