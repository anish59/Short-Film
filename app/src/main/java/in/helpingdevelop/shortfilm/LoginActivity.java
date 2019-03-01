package in.helpingdevelop.shortfilm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.framgia.android.emulator.EmulatorDetector;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_login);
//        checkWith(true);
        init();
    }

    private void init(){
        TextView movie_title = findViewById(R.id.movie_title);
        movie_title.setText(getString(R.string.movie_title));

        TextView movie_generes = findViewById(R.id.movie_genres);
        movie_generes.setText(getString(R.string.movie_genre));

        TextView movie_release_info = findViewById(R.id.movie_release_info);
        movie_release_info.setText(getString(R.string.movie_release));
        findViewById(R.id.loginBtn).setOnClickListener(this);
        findViewById(R.id.registrationBtn).setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.loginBtn:
                startActivity(new Intent(this, NavigationActivity.class));
                this.finish();
                break;
            case R.id.registrationBtn:
                startActivity(new Intent(this, SignupActivity.class));
                this.finish();
                break;
        }
    }

    private void checkWith(boolean telephony) {
        EmulatorDetector.with(LoginActivity.this)
                .setCheckTelephony(telephony)
                .addPackageName("com.bluestacks")
                .setDebug(true)
                .detect(new EmulatorDetector.OnEmulatorDetectorListener() {
                    @Override
                    public void onResult(final boolean isEmulator) {
                        LoginActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(radioVersion().equals("")){
                                    createDialog();
                                }
                                else if (isEmulator) {
                                    createDialog();
                                }
                                else {
                                    if(getCpuTemp()==0.0) {
                                        Toast.makeText(LoginActivity.this,
                                                "This is an emulator.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                    }
                });
    }

    public void createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("This app doesn't support emulators.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LoginActivity.this.finish();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    public float getCpuTemp() {
        Process process;
        try {
            process = Runtime.getRuntime().exec("cat sys/class/thermal/thermal_zone0/temp");
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            float temp = Float.parseFloat(line) / 1000.0f;
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public String radioVersion(){
        return Build.getRadioVersion();
    }
}
