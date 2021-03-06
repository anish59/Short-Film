package in.helpingdevelop.shortfilm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.framgia.android.emulator.EmulatorDetector;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import in.helpingdevelop.shortfilm.viewModels.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginViewModel.LoginViewModelCallBack {

    private LoginViewModel viewModel;
    private android.widget.EditText edtEmail;
    private android.widget.EditText edtPassword;
    private android.widget.Button btnLogin;
    private TextView registrationBtn;
    private android.widget.ImageView imgMoviePoster;
    private TextView txtMovieTitle;
    private TextView movieGenres;
    private TextView txtMovieReleaseInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        viewModel = new LoginViewModel(this, this);

        if (!viewModel.isUserLoggedIn()) {
            init();
        }

    }

    private void init() {
        setContentView(R.layout.activity_login);
        this.registrationBtn = (TextView) findViewById(R.id.registrationBtn);
        this.btnLogin = (Button) findViewById(R.id.btnLogin);
        this.edtPassword = (EditText) findViewById(R.id.edtPassword);
        this.edtEmail = (EditText) findViewById(R.id.edtEmail);

//        View view = LayoutInflater.from(this).inflate(R.layout.movie_box_include, null);
        this.txtMovieReleaseInfo = (TextView) findViewById(R.id.txtMovieReleaseInfo);
        this.movieGenres = (TextView) findViewById(R.id.movieGenres);
        this.txtMovieTitle = (TextView) findViewById(R.id.txtMovieTitle);
        this.imgMoviePoster = (ImageView) findViewById(R.id.imgMoviePoster);

        txtMovieTitle.setText(getString(R.string.movie_title));

        movieGenres.setText(getString(R.string.movie_genre));

        txtMovieReleaseInfo.setText(getString(R.string.movie_release));


        btnLogin.setOnClickListener(this);
        registrationBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnLogin:
                viewModel.doLogin(edtEmail, edtPassword);
                break;
            case R.id.registrationBtn:
                startActivity(new Intent(this, SignUpActivity.class));
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
                                if (radioVersion().equals("")) {
                                    createDialog();
                                } else if (isEmulator) {
                                    createDialog();
                                } else {
                                    if (getCpuTemp() == 0.0) {
                                        Toast.makeText(LoginActivity.this,
                                                "This is an emulator.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                    }
                });
    }

    public void createDialog() {
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

    public String radioVersion() {
        return Build.getRadioVersion();
    }

    @Override
    public void onSuccess(String msg) {

    }

    @Override
    public void onError(String errMsg) {

    }
}
