package com.example.myapplication.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.GridView;
import android.widget.AdapterView;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ImageAdapter;

import java.util.ArrayList;
public class ImgActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private GridView imageGridView;
    private ArrayList<Uri> imageUriList;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);

        imageGridView = findViewById(R.id.imageGridView);

        imageUriList = new ArrayList<>();
        ImageAdapter imageAdapter = new ImageAdapter(this, imageUriList);
        imageGridView.setAdapter(imageAdapter);

        imageGridView.setOnItemClickListener((adapterView, view, position, id) -> {
            selectedImageUri = imageUriList.get(position);
            showDeleteDialog();
        });

        findViewById(R.id.selectImageButton).setOnClickListener(view -> openGallery());
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    private void deleteSelectedImage() {
        if (selectedImageUri != null) {
            imageUriList.remove(selectedImageUri);
            ((ImageAdapter) imageGridView.getAdapter()).notifyDataSetChanged();
            selectedImageUri = null;
        }
    }

    private void showDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Image");
        builder.setMessage("Are you sure you want to delete this image?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            deleteSelectedImage();
            dialog.dismiss();
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri newImageUri = data.getData();
            imageUriList.add(newImageUri);
            ((ImageAdapter) imageGridView.getAdapter()).notifyDataSetChanged();
        }
    }
}