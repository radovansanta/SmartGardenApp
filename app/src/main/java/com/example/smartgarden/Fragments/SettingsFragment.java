package com.example.smartgarden.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Messenger;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartgarden.Models.Log;
import com.example.smartgarden.R;
import com.example.smartgarden.ViewModels.SchedulerLogsViewModel;
import com.example.smartgarden.databinding.FragmentSettingsBinding;
import com.example.smartgarden.databinding.SwitchLogsBinding;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private ImageView gardenImageView;
    private EditText inputHousehold;
    private EditText inputAddress;
    private Button saveSettings;
    private MutableLiveData<String> GardenBitmapBytes = new MutableLiveData<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SchedulerLogsViewModel galleryViewModel =
                new ViewModelProvider(this).get(SchedulerLogsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        gardenImageView = root.findViewById(R.id.gardenImage);
        inputHousehold = root.findViewById(R.id.inputHousehold);
        inputAddress = root.findViewById(R.id.inputAddress);
        saveSettings = root.findViewById(R.id.saveSettingButton);

        GardenBitmapBytes.setValue("empty");
        //pickImage();


        SharedPreferences prefs = getContext().getSharedPreferences(
                "com.example.smartgarden", Context.MODE_PRIVATE);
        String imageInBytes = prefs.getString("profileIcon",new String());
        String householdSaved = prefs.getString("Household",new String());
        String addressSaved = prefs.getString("Address",new String());

        inputHousehold.setText(householdSaved);
        inputAddress.setText(addressSaved);

        byte[] imageAsBytes = Base64.decode(imageInBytes.getBytes(), Base64.DEFAULT);
        gardenImageView.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));

        gardenImageView.setOnClickListener(click ->{
            pickImage();
        });

        saveSettings.setOnClickListener(click -> {
            prefs.edit().putString("Household", inputHousehold.getText().toString()).apply();
            prefs.edit().putString("Address", inputAddress.getText().toString()).apply();
            Toast.makeText(root.getContext(), "Settings changed! Refresh the app", Toast.LENGTH_SHORT).show();
        });

        return root;
    }



    private static final int PICK_PHOTO_FOR_AVATAR = 0;
    private static final int TAKE_PHOTO = 1;

    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return;
            }
            try {
                InputStream inputStream = getContext().getContentResolver().openInputStream(data.getData());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...

            //(imageView.setImageURI(data.getData().normalizeScheme());
            SharedPreferences prefs = getContext().getSharedPreferences(
                    "com.example.smartgarden", Context.MODE_PRIVATE);

            Uri imageUri = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();

            String encoded = Base64.encodeToString(b, Base64.DEFAULT);
            System.out.println(encoded);

            prefs.edit().putString("profileIcon", encoded).apply();
            gardenImageView.setImageURI(data.getData());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}