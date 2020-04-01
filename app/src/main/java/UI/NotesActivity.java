package UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trippalnner.R;

import java.util.ArrayList;

import Code.Database;
import POJOs.Note;

public class NotesActivity extends AppCompatActivity {

    private ListView list;
    private EditText editTextNote;
    private Button btnAdd;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private Database database;
    private String id;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        Intent receivedIntent = getIntent();
        id = receivedIntent.getStringExtra("nodeID");
        database = new Database();

        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);

        list = findViewById(R.id.listView);
        editTextNote = findViewById(R.id.editTextNote);
        btnAdd = findViewById(R.id.btnAdd);

        list.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String newItem = editTextNote.getText().toString();
                arrayList.add(newItem);
                adapter.notifyDataSetChanged();

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Note thisNote = new Note(id, newItem);
                        database.addNoteToDataBase(thisNote, id);

                    }
                }).start();

            }
        });


    }
}
