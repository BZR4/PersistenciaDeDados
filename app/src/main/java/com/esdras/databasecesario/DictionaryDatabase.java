package com.esdras.databasecesario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DictionaryDatabase extends SQLiteOpenHelper {

    //  Declaracao de atributos da entidade utilizada
    private static final String DATABASE_NAME = "dictionary.db";
    private static final String TABLE_DICTIONARY = "dictionary";
    private static final String FIELD_WORD = "word";
    private static final String FIELD_DEFINITION = "definition";
    private static final int DATABASE_VERSION = 1;

    public DictionaryDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Método responsável pela criação da tabela.
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_DICTIONARY+"(_id integer primary key, "+FIELD_WORD+" TEXT,"+FIELD_DEFINITION+" TEXT);");
    }

    /**
     * Método responsável pela atualização de versão do banco de dados.
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Método responsável por verificar se o registro ja existe na base de dados,
     * se e o id já existir, o registro será atualizao, caso contrário atualizado.
     * @param word
     * @param definition
     */
    public void saveRecord(String word, String definition){
        long id = findWordID(word);
        if (id>0) {
            updateRecord(id, word,definition);
        } else {
            addRecord(word,definition);
        }
    }


    /**
     * Método responsável por adicionar um novo registro na base de dados
     * @param word - String palavra
     * @param definition - String Definição
     * @return
     */
    public long addRecord(String word, String definition) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_WORD, word);
        values.put(FIELD_DEFINITION, definition);
        return db.insert(TABLE_DICTIONARY, null, values);
    }

    /**
     * Método responsável por atualizar um novo registro existente.
     * @param id - long código
     * @param word - String palavra
     * @param definition - String Definição
     * @return
     */
    public int updateRecord(long id, String word, String definition) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id", id);
        values.put(FIELD_WORD, word);
        values.put(FIELD_DEFINITION, definition);
        return db.update(TABLE_DICTIONARY, values, "_id = ?", new String[]{String.valueOf(id)});
    }

    /**
     * Método responsável por remover registro do banco de dados.
     * @param id - long código
     * @return - int código removido
     */
    public int deleteRecord(long id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_DICTIONARY, "_id = ?", new String[]{String.valueOf(id)});
    }

    /**
     * Método responsável por validar existencia registro no banco de dados.
     * @param word - String palavra
     * @return - long status de retorno
     */
    public long findWordID(String word) {
        long returnVal = -1;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id FROM " + TABLE_DICTIONARY + " WHERE " + FIELD_WORD + " = ?", new String[]{word});
        Log.i("findWordID","getCount()="+cursor.getCount());
        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            returnVal = cursor.getInt(0);
        }
        return returnVal;
    }

    /**
     * Método responsável por obter uma definicao de palavra no banco de dados.
     * @param id
     * @return
     */
    public String getDefinition(long id) {
        String returnVal = "";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT definition FROM " + TABLE_DICTIONARY + " WHERE _id = ?", new String[]{String.valueOf(id)});
        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            returnVal = cursor.getString(0);
        }
        return returnVal;
    }

    /**
     * Método responsável por obter uma lista de definições do banco de dados.
     * @return
     */
    public Cursor getWordList() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT _id, " + FIELD_WORD + " FROM " + TABLE_DICTIONARY + " ORDER BY " + FIELD_WORD + " ASC";
        return db.rawQuery(query, null);
    }
}
