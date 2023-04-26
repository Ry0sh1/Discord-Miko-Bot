package de.Ryoshi.Miko;

import de.Ryoshi.Miko.manage.LiteSQL;

public class SQLManager {

    public static void onCreate(){

        LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS cookies(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, cookie INTEGER, relationship INTEGER, lastflirt INT, lastflip INT)");

    }

}
