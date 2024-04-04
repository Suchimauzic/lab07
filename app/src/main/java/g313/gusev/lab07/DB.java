package g313.gusev.lab07;

import static android.app.ProgressDialog.show;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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

    public boolean insert(String key, String value) {
        String sql = "INSERT INTO MyText VALUES('" + key + "', '" + value + "');";
        SQLiteDatabase db = getWritableDatabase();
        if (select(key) != "") {
            return false;
        } else {
            db.execSQL(sql);
            return true;
        }
    }

    public String select(String key) {
        String sql = "SELECT MyValue FROM MyText WHERE MyKey = '" + key + "';";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery(sql, null);

        if (cur.moveToFirst() == true) {
            return cur.getString(0);
        }

        return "";
    }

    public boolean update(String key, String value) {
        String sql = "UPDATE MyText SET MyValue = '" + value + "' WHERE MyKey = '" + key + "';";
        SQLiteDatabase db = getWritableDatabase();
        if (select(key) != "") {
            db.execSQL(sql);
            return true;
        } else
            return false;
    }

    public boolean delete(String key) {
        String sql = "DELETE FROM MyText WHERE MyKey = '" + key + "';";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cur = db.rawQuery(sql, null);

        if (select(key) != "") {
            if (cur.moveToFirst() == true) {
                db.execSQL(sql);
            }
            return true;
        } else
            return false;
    }

}
