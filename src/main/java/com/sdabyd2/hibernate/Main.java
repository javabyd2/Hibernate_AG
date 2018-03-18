package com.sdabyd2.hibernate;

import com.sdabyd2.hibernate.entity.BookEntity;
import com.sdabyd2.hibernate.entity.CategoriesEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();

        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    public static void main(String[] args) {

        // dodawanie nowego rekordu do bazy
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor("Jan Kowalski");
        bookEntity.setTitle("Programowanie dla dzieci");

        Transaction transaction = null; //wszystkie operacja w Hibernate to transakcje
        Session session = getSession();

        transaction = session.beginTransaction(); // zaczynam transakcję
        session.save(bookEntity);
        transaction.commit(); // zakańczam transakcję
        // ----- koniec dodawania rekordu

        // dodawanie kolejnego rekordu
        BookEntity bookEntity1 = new BookEntity();
        bookEntity1.setAuthor("Andrzej Nowak");
        bookEntity1.setTitle("Nauka biegania w weekend");
        bookEntity1.setPageCount(45);

        transaction = session.beginTransaction();
        session.save(bookEntity1);
        transaction.commit();
        // koniec dodwania kolejnego rekordu

        // dodawanie kilku rekordów podczas jednej transakcji
        BookEntity bookEntity2 = new BookEntity();
        bookEntity2.setAuthor("Jarosław Bąk");
        bookEntity2.setTitle("Pierdoły, pierdoły");

        BookEntity bookEntity3 = new BookEntity();
        bookEntity3.setAuthor("Bogusław Wielki");
        bookEntity3.setTitle("Mały Świat");

        transaction = session.beginTransaction();
        session.save(bookEntity2);
        session.save(bookEntity3);
        transaction.commit();
        // koniec dodawania kilku rekordów podczas jednej transakcji

        // dodawanie jeszcze większej ilości rekordów
        BookEntity bookEntity4 = new BookEntity();
        bookEntity4.setAuthor("Kazimierz Mikołajczyk");
        bookEntity4.setTitle("Boso przez Świat");
        bookEntity4.setPrice(BigDecimal.valueOf(45.55));

        BookEntity bookEntity5 = new BookEntity();
        bookEntity5.setAuthor("Grzegorz Strzemkowski");
        bookEntity5.setTitle("Wiem wszystko");

        BookEntity bookEntity6 = new BookEntity();
        bookEntity6.setAuthor("Magdalena Kozłowska");
        bookEntity6.setTitle("Płynę, płynę");

        BookEntity bookEntity7 = new BookEntity();
        bookEntity7.setAuthor("Bernard Drewek");
        bookEntity7.setTitle("Jestem siłaczem z Lipnik");

        transaction = session.beginTransaction();
        session.save(bookEntity4);
        session.save(bookEntity5);
        session.save(bookEntity6);
        session.save(bookEntity7);
        transaction.commit();
        // koniec dodawania jeszcze większej ilości rekordów

        // pobieranie danych z bazy pierwszy sposób
        List<BookEntity> bookEntityList = session.createCriteria(BookEntity.class).list();
        System.out.println(bookEntityList);
        for (BookEntity bookEntitylisa : bookEntityList) {
            System.out.println("Autor: " + bookEntitylisa.getAuthor());
        }
        // koniec pobierania danych z bazy

        // pobieranie danych z bazy za pomocą HQL
        List<BookEntity> bookEntityList1 = session.createQuery("FROM " + BookEntity.class.getName()).list();
        System.out.println(bookEntityList1);
        for (BookEntity item : bookEntityList1) {
            System.out.println("Autor: " + item.getAuthor() + "\n" + "Tytuł: " + item.getTitle() + "\n" +
                                    "Cena: " + item.getPrice() + "\n");
        }
        // koniec pobierania danych za pomocą HQL

        // --------------------- KATEGORIE --------------------------
        // ------ dodawanie kategorii ----------
        CategoriesEntity categoriesEntity1 = new CategoriesEntity();
        categoriesEntity1.setCategory_name("Fantastyka");

        CategoriesEntity categoriesEntity2 = new CategoriesEntity();
        categoriesEntity2.setCategory_name("Horror");

        CategoriesEntity categoriesEntity3 = new CategoriesEntity();
        categoriesEntity3.setCategory_name("Przygodowa");

        CategoriesEntity categoriesEntity4 = new CategoriesEntity();
        categoriesEntity4.setCategory_name("Flora i fauna");

        CategoriesEntity categoriesEntity5 = new CategoriesEntity();
        categoriesEntity5.setCategory_name("Poradniki");

        CategoriesEntity categoriesEntity6 = new CategoriesEntity();
        categoriesEntity6.setCategory_name("Komiksy");

        transaction = session.beginTransaction();
        session.save(categoriesEntity1);
        session.save(categoriesEntity2);
        session.save(categoriesEntity3);
        session.save(categoriesEntity4);
        session.save(categoriesEntity5);
        session.save(categoriesEntity6);
        transaction.commit();
        // ------ koniec dodawania kategorii ----------


        // --------- wyświetlanie kategorii -----------
        List<CategoriesEntity> categoriesEntityList = session.createQuery("FROM " + CategoriesEntity.class.getName()).list();
        for (CategoriesEntity item : categoriesEntityList) {
            System.out.println(item.getId_category() + " - " + item.getCategory_name());
        }


    }
}