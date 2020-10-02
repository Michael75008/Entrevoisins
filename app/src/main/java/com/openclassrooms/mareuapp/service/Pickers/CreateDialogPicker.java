package com.openclassrooms.mareuapp.service.Pickers;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class CreateDialogPicker implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    EditText mEditText;
    private int day;
    private int month;
    private int year;
    private Context mContext;

    public CreateDialogPicker(Context context, int editTextViewId) {
        Activity activity = (Activity) context;
        this.mEditText = (EditText) activity.findViewById(editTextViewId);
        this.mEditText.setOnClickListener(this);
        this.mContext = context;
    }

    /**
     * Date set up
     *
     * @param view
     * @param yearOfDate
     * @param monthOfYear
     * @param dayOfMonth
     */

    @Override
    public void onDateSet(DatePicker view, int yearOfDate, int monthOfYear, int dayOfMonth) {
        year = yearOfDate;
        month = monthOfYear;
        day = dayOfMonth;
        updateDate();
    }

    /**
     * Show calendar when clicked
     */

    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContext, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    /**
     * updates the date and turns it editable
     */

    private void updateDate() {
        mEditText.setText(new StringBuilder().append(day).append("/")
                .append(month + 1).append("/")
                .append(year));
    }
}