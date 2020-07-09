package com.absolute.whatsappstickers.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import com.absolute.whatsappstickers.R;
import com.absolute.whatsappstickers.utils.FileUtils;
import com.absolute.whatsappstickers.utils.RequestPermissionsHelper;

public class RequestPermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_permission);
        FileUtils.initializeDirectories(this);
        if (RequestPermissionsHelper.verifyPermissions(this)) {
            startActivity(new Intent(this, MainActivity.class));
            this.finish();
        } else {
            RequestPermissionsHelper.requestPermissions(this);
        }
        findViewById(R.id.grant_permissions_button).setOnClickListener(v -> RequestPermissionsHelper.requestPermissions(this));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        FileUtils.initializeDirectories(this);
        if (RequestPermissionsHelper.verifyPermissions(this)) {//If the app has all the required permissions we pass to MainActivity to get started
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(this, "We need access to write and read files in your phone", Toast.LENGTH_SHORT).show();
        }
    }
}
