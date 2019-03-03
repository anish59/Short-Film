package in.helpingdevelop.shortfilm.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Calendar;

import in.helpingdevelop.shortfilm.R;
import in.helpingdevelop.shortfilm.helper.DateFormatterUtils;
import in.helpingdevelop.shortfilm.helper.PrefUtils;
import in.helpingdevelop.shortfilm.model.UserProfile;


public class MyProfileFragment extends Fragment implements View.OnClickListener {

    private android.widget.EditText edtFullName;
    private android.widget.EditText edtMobile;
    private android.widget.TextView txtDob;
    private android.widget.TextView txtEmailId;
    private android.widget.TextView txtBookedCount;
    private android.widget.ScrollView scrollView;
    private android.widget.Button btnUpdate;
    private boolean isEditMode = false;
    private Calendar pickerSelectedDate = Calendar.getInstance();
    private String selectedDob = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        this.btnUpdate = (Button) view.findViewById(R.id.btnUpdate);
        this.scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        this.txtBookedCount = (TextView) view.findViewById(R.id.txtBookedCount);
        this.txtEmailId = (TextView) view.findViewById(R.id.txtEmailId);
        this.txtDob = (TextView) view.findViewById(R.id.txtDob);
        this.edtMobile = (EditText) view.findViewById(R.id.edtMobile);
        this.edtFullName = (EditText) view.findViewById(R.id.edtFullName);

        init();
        registerListeners();
        return view;

    }

    private void registerListeners() {
        txtDob.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

    private void init() {
        setData();
    }

    private void setData() {
        UserProfile userProfile = PrefUtils.getUser(getContext());
        edtFullName.setText(userProfile.getName());
        edtMobile.setText(userProfile.getMobile());
        txtDob.setText(userProfile.getDob());
        txtEmailId.setText(userProfile.getEmail());
        txtBookedCount.setText(userProfile.getTicket_bookd());


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onClick(View view) {
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);


        switch (view.getId()) {
            case R.id.txtDob:
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        selectedDob = DateFormatterUtils.parseDate(year + "-" + month + "-" + date, DateFormatterUtils.ymdFormat, DateFormatterUtils.dd_MMMM_YYYY);
                        txtDob.setText(selectedDob);
                        pickerSelectedDate = DateFormatterUtils.stringToCalendar(selectedDob, DateFormatterUtils.dd_MMMM_YYYY);
                    }
                }, pickerSelectedDate.get(Calendar.YEAR), pickerSelectedDate.get(Calendar.MONTH), pickerSelectedDate.get(Calendar.DAY_OF_MONTH));

                dialog.getDatePicker().setMaxDate(tomorrow.getTimeInMillis());
                dialog.show();
                break;
            case R.id.btnUpdate:

                break;
        }

    }
}
