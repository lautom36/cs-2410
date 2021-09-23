package com.example.contacts.database;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.contacts.models.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contact")
    List<Contact> getAllContacts();

    // maybe works?
    @Query("SELECT * FROM contact WHERE id=:contactId")
    Contact getContactFromId(int contactId);

    @Insert
    void insert(Contact contact);

    @Update
    void update(Contact contact);

    @Query("SELECT COUNT(id) From contact")
    int getDataCount();

    @Delete
    void delete(Contact contact);
}
