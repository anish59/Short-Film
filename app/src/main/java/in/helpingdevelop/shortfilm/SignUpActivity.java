package in.helpingdevelop.shortfilm;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import in.helpingdevelop.shortfilm.viewModels.SignUpViewModel;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, SignUpViewModel.SignUpViewModelCallBack {

    private android.widget.EditText edtFullName;
    private android.widget.EditText edtEmail;
    private android.widget.EditText edtPassword;
    private android.widget.Button btnSignUp;
    private TextView btnLogin;


    private android.widget.ImageView imgMoviePoster;
    private TextView txtMovieTitle;
    private TextView movieGenres;
    private TextView txtMovieReleaseInfo;

    private SignUpViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
//        checkWith(true);

        init();
    }

    private void init() {

        setContentView(R.layout.activity_signup);
        this.btnSignUp = (Button) findViewById(R.id.btnSignUp);
        this.edtPassword = (EditText) findViewById(R.id.edtPassword);
        this.edtEmail = (EditText) findViewById(R.id.edtEmail);
        this.edtFullName = (EditText) findViewById(R.id.edtFullName);

        //        View view = LayoutInflater.from(this).inflate(R.layout.movie_box_include, null);
        this.txtMovieReleaseInfo = (TextView) findViewById(R.id.txtMovieReleaseInfo);
        this.movieGenres = (TextView) findViewById(R.id.movieGenres);
        this.txtMovieTitle = (TextView) findViewById(R.id.txtMovieTitle);
        this.imgMoviePoster = (ImageView) findViewById(R.id.imgMoviePoster);

        txtMovieTitle.setText(getString(R.string.movie_title));

        movieGenres.setText(getString(R.string.movie_genre));

        txtMovieReleaseInfo.setText(getString(R.string.movie_release));

        btnSignUp.setOnClickListener(this);

        viewModel = new SignUpViewModel(this, this);
    }

    private void checkWith(boolean telephony) {
        EmulatorDetector.with(SignUpActivity.this)
                .setCheckTelephony(telephony)
                .addPackageName("com.bluestacks")
                .setDebug(true)
                .detect(new EmulatorDetector.OnEmulatorDetectorListener() {
                    @Override
                    public void onResult(final boolean isEmulator) {
                        SignUpActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (radioVersion().equals("")) {
                                    createDialog();
                                } else if (isEmulator) {
                                    createDialog();
                                } else {
                                    if (getCpuTemp() == 0.0) {
                                        Toast.makeText(SignUpActivity.this,
                                                "This is an emulator.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                    }
                });
    }

    public void createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
        builder.setMessage("This app doesn't support emulators.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SignUpActivity.this.finish();
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
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {

            case R.id.btnSignUp:
                viewModel.doSignUp(edtFullName, edtEmail, edtPassword);
                break;
        }
    }

    @Override
    public void onSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onError(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();

    }
}
