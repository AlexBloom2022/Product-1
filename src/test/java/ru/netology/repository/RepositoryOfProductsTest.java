package ru.netology.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exeptions.NotFoundException;
import ru.netology.manager.Manager;
import ru.netology.repository.RepositoryOfProducts;


class RepositoryOfProductsTest {

    RepositoryOfProducts repository = new RepositoryOfProducts();
    Manager manager = new Manager(repository);
    Product phone1 = new Smartphone(1, "Galaxy", 100, "Samsung");
    Product phone2 = new Smartphone(2, "Iphone", 300, "Apple");
    Product book1 = new Book(3, "Revizor", 150, "Gogol");
    Product book2 = new Book(4, "The outcasts", 200, "Hugo");


    @Test
    void removeByNonExistingIdInRepo() {
        repository.save(phone1);
        repository.save(phone2);

        Assertions.assertThrows(NotFoundException.class, () -> repository.removeById(8));

    }

    @Test
    void removeByExistingIdInRepo() {
        repository.save(phone1);
        repository.save(phone2);
        repository.removeById(1);
        Product[] expected = {phone2};

        assertArrayEquals(expected, repository.findAll());
    }
}