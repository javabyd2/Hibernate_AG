package com.sdabyd2.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "kategorie")
public class CategoriesEntity {
    @Column(name = "category", length = 55)
    private String category_name;
    @Column(name = "category_description", length = 300)
    private String category_description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private int id_category;

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_description() {
        return category_description;
    }

    public void setCategory_description(String category_description) {
        this.category_description = category_description;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }
}