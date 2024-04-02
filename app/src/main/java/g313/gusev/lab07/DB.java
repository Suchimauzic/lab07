package g313.gusev.lab07;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {

    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE MyText (MyKey TEXT, MyValue TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String key, String value) {
        String sql = "INSERT INTO MyText VALUES('" + key + "', '" + value + "');";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public String select(String key) {
        String sql = "SELECT MyValue FROM MyText WHERE MyKey = '" + key + "';";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery(sql, null);

        if (cur.moveToFirst() == true)
            return cur.getString(0);

        return "(!) not found";
    }

    public void update(String key, String value) {
        String sql = "UPDATE MyText SET MyValue = '" + value + "' WHERE MyKey = '" + key + "';";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public void delete(String key) {
        String sql = "DELETE FROM MyText WHERE MyKey = '" + key + "';";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cur = db.rawQuery(sql, null);

        if (cur.moveToFirst() == true)
            db.execSQL(sql);
    }

}
