package g313.gusev.lab07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
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
        if (!TextUtils.isEmpty(key)) {
            if (db.insert(key, value))
                Toast.makeText(this, "Данные успешно введены", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Такой ключ уже существует", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Нельзя ввести пустой ключ", Toast.LENGTH_SHORT).show();
    }

    public void btnSelectClick(View v) {
        String key = textId.getText().toString();
        if (!TextUtils.isEmpty(key)) {
            String value = db.select(key);
            if (!TextUtils.isEmpty(value))
                textValue.setText(value);
            else
                textValue.setText("(!) not found");
        } else
            Toast.makeText(this, "Нельзя ввести пустой ключ", Toast.LENGTH_SHORT).show();
    }

    public void btnUpdateClick(View v) {
        String key = textId.getText().toString();
        String value = textValue.getText().toString();
        if (!TextUtils.isEmpty(key)) {
            if (db.update(key, value))
                Toast.makeText(this, "Заметка была успешно обновлена!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Поля по такому ключу не было найдено", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Нельзя ввести пустой ключ", Toast.LENGTH_SHORT).show();
    }

    public void btnDeleteClick(View v) {
        String key = textId.getText().toString();
        if (!TextUtils.isEmpty(key)) {
            if (db.delete(key))
                Toast.makeText(this, "Заметка успешно удалена", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Такой заметки не существует!", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Нельзя ввести пустой ключ", Toast.LENGTH_SHORT).show();


    }
}