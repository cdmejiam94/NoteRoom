package com.preeliminatorylabs.noteroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.preeliminatorylabs.noteroom.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.preeliminatorylabs.noteroom.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.preeliminatorylabs.noteroom.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.preeliminatorylabs.noteroom.EXTRA_PRIORITY";

    private EditText edtTitle;
    private EditText edtDescription;
    private NumberPicker npkPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        edtTitle = findViewById(R.id.edtTitle);
        edtDescription = findViewById(R.id.edtDescription);
        npkPriority = findViewById(R.id.npkPriority);

        npkPriority.setMinValue(1);
        npkPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            edtTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            edtDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            npkPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }
    }

    private void saveNote(){
        String title = edtTitle.getText().toString();
        String description = edtDescription.getText().toString();
        int priority = npkPriority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Los campos son obligatorios",Toast.LENGTH_SHORT).show();
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY,priority);

        int id = getIntent().getIntExtra(EXTRA_ID, 1);
        if (id != -1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.btnSave:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
