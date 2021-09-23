package com.example.contacts.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// Table = "Contacts"
//----------------------------------
// id | name | phone_number | email |
//-----------------------------------
// 1  |      | false        |       |

@Entity
public class Contact implements Serializable {
    // from class example
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "phone_number")
    public String phoneNumber;

    @ColumnInfo(name = "email")
    public String email;
}

// Table = "todo"
//-----------------------------
// id | contents | is_complete|
//-----------------------------
// 1  | do it    | false      |

//@Entity
//public class Todo {
//    // from class example
//    @PrimaryKey(autoGenerate = true)
//    public int id;
//
//    @ColumnInfo(name = "contents")
//    public String contents;
//
//    @ColumnInfo(name = "is_complete")
//    public boolean isComplete;
//
//}


