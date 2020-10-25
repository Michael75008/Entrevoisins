package com.openclassrooms.mareuapp.service.Pickers;


import androidx.fragment.app.DialogFragment;


public class CreateDialogPicker extends DialogFragment {
    /**
     @BindView(R.id.date_filter) TextInputEditText mDateFilter;
     @BindView(R.id.room_filter) AutoCompleteTextView mRoomFilter;

     private List<String> mRooms;
     private Calendar mDate;

     private OnButtonClickedListener mCallBack;

     @NonNull
     @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
     super.onCreateDialog(savedInstanceState);
     AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
     LayoutInflater inflater = getActivity().getLayoutInflater();
     View view = inflater.inflate(R.layout.filter, null);
     ButterKnife.bind(this, view);

     mRoomFilter.setAdapter(new ArrayAdapter<>(
     Objects.requireNonNull(getContext()),
     R.layout.room_filter_item,
     mRooms
     ));

     builder.setView(view);
     builder.setTitle("Choix du filtre");

     builder.setPositiveButton(android.R.string.ok, ((dialogInterface, i) -> {
     mCallBack.onButtonClicked(mDate, mRoomFilter.getEditableText().toString(), false);
     }));

     builder.setNegativeButton(android.R.string.cancel, ((dialogInterface, i) -> dialogInterface.dismiss()));

     builder.setNeutralButton("Reset", ((dialogInterface, i) -> mCallBack.onButtonClicked(mDate, mRoomFilter.getEditableText().toString(), true));

     return builder.create();
     }

     public interface OnButtonClickedListener {
     void onButtonClicked(Calendar date, String room, boolean reset);
     }
     private void createCallbackToParentActivity() {
     mCallBack = (OnButtonClickedListener) getActivity();
     }

     @OnClick(R.id.date_filter) void displayDatePicker() {
     Calendar calendar = Calendar.getInstance();
     DatePickerDialog mDatePickerDialog;

     mDatePickerDialog = new DatePickerDialog(Objects.requireNonNull(getContext()),
     (view, year, month, dayOfMonth) -> {
     Calendar cal = Calendar.getInstance();
     cal.set(year, month, dayOfMonth);
     mDateFilter.setText(DateFormat.getDateFormat(getContext()).format(cal.getTime()));
     mDate = cal;
     },
     calendar.get(Calendar.YEAR),
     calendar.get(Calendar.MONTH),
     calendar.get(Calendar.DAY_OF_MONTH));

     mDatePickerDialog.show();
     }

     @OnTouch(R.id.room_filter) boolean onTouch(View v, MotionEvent event) {
     if(event.getAction() == MotionEvent.ACTION_DOWN) {
     mRoomFilter.showDropDown();
     return true;
     }

     return (event.getAction() == MotionEvent.ACTION_UP);
     }

     @Override public void onAttach(Context context) {
     super.onAttach(context);

     createCallbackToParentActivity();
     }

     */
}