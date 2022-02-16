package es.travelworld.registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.material.textview.MaterialTextView;

import es.travelworld.registro.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ActivityMainBinding binding;
    private String[] ages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(binding.getRoot());
        populateAgeEditText();
        setListeners();
    }

    private void setListeners() {
        binding.avatarImg.setOnClickListener(this);
        binding.viewConditions.setOnClickListener(this);
        binding.btnJoin.setOnClickListener(this);
        binding.inputAge.setOnItemClickListener(this);

        binding.inputName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.inputLayoutName.setErrorEnabled(false);
                for (int j = 0; j < charSequence.length(); j++) {
                    switch(charSequence.charAt(j)){
                        case '!':
                            binding.inputLayoutName.setError(getString(R.string.input_layput_name_error));
                        case '@':
                            binding.inputLayoutName.setError(getString(R.string.input_layput_name_error));
                    }
                }
                validateForm();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.inputLastname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.inputLayoutLastname.setErrorEnabled(false);
                for (int j = 0; j < charSequence.length(); j++) {
                    switch(charSequence.charAt(j)){
                        case '!':
                            binding.inputLayoutLastname.setError(getString(R.string.input_layput_name_error));
                        case '@':
                            binding.inputLayoutLastname.setError(getString(R.string.input_layput_name_error));
                    }
                }
                validateForm();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void validateForm() {
        binding.btnJoin.setEnabled(false);
        boolean nameValidation = false;
        boolean lastnameValidation = false;
        boolean ageValidation = false;

        if(!binding.inputName.getText().toString().equals("") && !binding.inputLayoutName.isErrorEnabled()){
            nameValidation = true;
        }
        if(!binding.inputLastname.getText().toString().equals("") && !binding.inputLayoutLastname.isErrorEnabled()){
            lastnameValidation = true;
        }
        if(!binding.inputLayoutAge.isErrorEnabled()){
            ageValidation = true;
        }

        if(nameValidation && lastnameValidation && ageValidation){
            binding.btnJoin.setEnabled(true);
        }
    }

    private void populateAgeEditText() {
        ages = getResources().getStringArray(R.array.ages);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_item, ages);
        binding.inputAge.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if (view.equals(binding.avatarImg)) {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
        } else if (view.equals(binding.viewConditions)) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developers.google.com/ml-kit/terms"));
            startActivity(intent);
        } else if(view.equals(binding.btnJoin)){
            Toast.makeText(this,R.string.todo,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MaterialTextView materialTextView = (MaterialTextView) view;
        binding.inputLayoutAge.setErrorEnabled(false);
        if(materialTextView.getText() != ages[ages.length-1]){
            binding.inputLayoutAge.setError(getString(R.string.input_layout_age_error));
        }
        validateForm();
    }
}
