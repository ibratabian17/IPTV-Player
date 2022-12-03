package org.prjktla.iptv.ui.activitys.fileselect;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.prjktla.iptv.R;
import org.prjktla.iptv.database.IPTvRealm;
import org.prjktla.iptv.databinding.ActivityFirstBinding;
import org.prjktla.iptv.filereader.FileReader;
import org.prjktla.iptv.ui.activitys.main.MainActivity;
import org.prjktla.iptv.utils.Helper;

import java.util.List;

import timber.log.Timber;

public class FileSelectActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int IPTV_READ_DOC_PERM = 123;
    private ActivityFirstBinding binding;
    private ActivityResultLauncher<String> selectFileLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        binding = ActivityFirstBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initialize();
    }

    private void initialize() {
        if (new IPTvRealm().allChannelCount() > 0) {
            startActivity(new Intent(FileSelectActivity.this, MainActivity.class));
            finish();
        } else {
            binding.selectActLinear.setVisibility(View.VISIBLE);
            binding.selectBtn.setOnClickListener(this);
            selectFileLauncher = registerForActivityResult(
                    new ActivityResultContracts.GetContent(),
                    result -> {
                        if (result != null) {
                            Timber.e("Result %s", result);
                            //Result uri file
                            new FileReader(FileSelectActivity.this, result).readFile();
                        } else {
                            Timber.e("Nothing Select");
                        }
                    });
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                            String permissions[], int[] grantResults) {
        switch (requestCode) {
        case 1: {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                 selectFileLauncher.launch(Helper.FILE_MIME_TYPE);         
            } else {
                Toast.makeText(FileSelectActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
            }
                return;
            }

             // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void readFileTask() {
        if(android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.TIRAMISU){
         ActivityCompat.requestPermissions(FileSelectActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        } else {
            ActivityCompat.requestPermissions(FileSelectActivity.this,
                    new String[]{Manifest.permission.READ_MEDIA_VIDEO},
                    1);
        }
    }

    public void onClick(View view) {
        if (view.getId() == binding.selectBtn.getId()) {
            readFileTask();
        }
    }
}