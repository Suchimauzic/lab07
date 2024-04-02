package g313.gusev.lab07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText textId;
    EditText textValue;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textId = findViewById(R.id.editKey);
        textValue = findViewById(R.id.editValue);

        db = new DB(this, "mytext.db", null, 1);
    }

    public void btnInsertClick(View v) {
        String key = textId.getText().toString();
        String value = textValue.getText().toString();
        if (key != "")
            db.insert(key, value);
        else
            Toast.makeText(this, "Нельзя ввести пустой ключ", Toast.LENGTH_SHORT).show();
    }

    public void btnSelectClick(View v) {
        String key = textId.getText().toString();
        if (key != "") {
            String value = db.select(key);
            textValue.setText(value);
        }
        else
            Toast.makeText(this, "Нельзя ввести пустой ключ", Toast.LENGTH_SHORT).show();
    }

    public void btnUpdateClick(View v) {
        String key = textId.getText().toString();
        String value = textValue.getText().toString();
        if (key != "") {
            db.update(key, value);
        }
        else
            Toast.makeText(this, "Нельзя ввести пустой ключ", Toast.LENGTH_SHORT).show();
    }

    public void btnDeleteClick(View v) {
        String key = textId.getText().toString();
        if (key != "")
            db.delete(key);
        else
            Toast.makeText(this, "Нельзя ввести пустой ключ", Toast.LENGTH_SHORT).show();
    }
}