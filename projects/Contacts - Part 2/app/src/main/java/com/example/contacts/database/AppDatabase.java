package com.example.contacts.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.contacts.models.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDao getContactDao();
}

//@Database(entities = {Todo.class}, version = 1)
//public abstract class AppDatabase extends RoomDatabase {
//    public abstract TodoDao getTodoDao();
