package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.RepositoryOfProducts;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    RepositoryOfProducts repository = new RepositoryOfProducts();
    Manager manager = new Manager(repository);

    Product book1 = new Book(3, "The fate of man", 300, "Sholohov");
    Product book2 = new Book(1, "Anna Karenina", 110, "Tolstoy");
    Product book3 = new Book(5, "Karamazovs", 220, "Dostoevsky");
    Product book4 = new Book(3, "The fate of man", 300, "Sholohov");
    Product phone1 = new Smartphone(9, "Galaxy", 60000, "Samsung");
    Product phone2 = new Smartphone(42, "Iphone", 10000, "Apple");

    @Test
    void searchByEmptyTitle() {

        Product[] expected = {};

        Product[] actual = manager.searchBy(null);

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByNonExistingProduct() {

        manager.addItem(book4);
        manager.addItem(book2);

        Product[] expected = {};
        Product[] actual = manager.searchBy("Yasna");

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByTwinProduct() {

        manager.addItem(phone1);
        manager.addItem(book1);
        manager.addItem(book4);


        Product[] expected = {book1, book4};
        Product[] actual = manager.searchBy("The fate of man");


        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByTitle() {

        manager.addItem(phone2);
        manager.addItem(book3);


        Product[] expected = {book3};
        Product[] actual = manager.searchBy("Karamazovs");

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByEmpty() {

        Product[] expected = {};

        Product[] actual = manager.getAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByDoubleAddedItem() {
        manager.addItem(book1);
        manager.addItem(book1);
        Product[] expected = {book1, book1};
        Product[] actual = manager.getAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByModel() {
        manager.addItem(phone1);
        manager.addItem(book2);

        Product[] expected = {phone1};
        Product[] actual = manager.searchBy("Samsung");

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByName() {
        manager.addItem(phone2);
        manager.addItem(phone1);


        Product[] expected = {phone2};
        Product[] actual = manager.searchBy("Iphone");

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAuthor() {
        manager.addItem(book2);
        manager.addItem(book3);
        manager.addItem(phone1);
        Product[] expected = {book3};
        Product[] actual = manager.searchBy("Dostoevsky");

        assertArrayEquals(expected, actual);
    }

    @Test
    void findAllTest() {
        repository.save(book1);

        Product[] expected = {book1};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);

    }

    @Test
    void removeByIdTest() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(book4);
        repository.removeById(5);

        Product[] expected = {book1, book2, book4};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void findByIdTest() {
        repository.save(book1);
        repository.save(book2);
        repository.save(phone1);
        repository.save(phone2);

        Product[] expected = {phone1};
        Product[] actual = {repository.findById(9)};

        assertArrayEquals(expected, actual);
    }

    @Test
    void findByIdNullTest() {
        repository.save(book1);
        repository.save(book2);
        repository.save(phone1);
        repository.save(phone2);

        Product[] expected = {null};
        Product[] actual = {repository.findById(54)};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveByIdManagerTest() {
        manager.addItem(book2);
        manager.removeById(1);

        Product[] expected = {};
        Product[] actual = manager.getAll();

        assertArrayEquals(expected, actual);
    }

}