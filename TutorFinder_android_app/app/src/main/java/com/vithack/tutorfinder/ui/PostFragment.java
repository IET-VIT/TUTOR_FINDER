package com.vithack.tutorfinder.ui;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vithack.tutorfinder.LoginActivity;
import com.vithack.tutorfinder.MainActivity;
import com.vithack.tutorfinder.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.vithack.tutorfinder.MainActivity.ID;
import static com.vithack.tutorfinder.MainActivity.POSITION;

public class PostFragment extends Fragment implements DatePickerDialog.OnDateSetListener{

    @BindView(R.id.post_teacher_error) TextView post_teacher_error;
    @BindView(R.id.parentConstraintLyt) ConstraintLayout parentConstraintLyt;
    @BindView(R.id.salarySpinner) Spinner salarySpinner;
    @BindView(R.id.deadline_text) TextView deadline_text;
    @BindView(R.id.subject_linear_one) LinearLayout subject_linear_one;
    @BindView(R.id.subject_linear_two) LinearLayout subject_linear_two;
    @BindView(R.id.class_linear_one) LinearLayout class_linear_one;
    @BindView(R.id.class_linear_two) LinearLayout class_linear_two;
    @BindView(R.id.medium_linear) LinearLayout medium_linear;
    @BindView(R.id.location_text) EditText location_text;
    @BindView(R.id.university_text) EditText university_text;
    @BindView(R.id.post_form_btn) Button post_form_btn;

    private String subjects, classes, medium, salary, location, university, deadline;

    String URL = "https://abc-institutions.000webhostapp.com/post_form.php";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_post, container, false);

        ButterKnife.bind(this,root);

        if(POSITION.equals("teacher")){
            post_teacher_error.setVisibility(View.VISIBLE);
            parentConstraintLyt.setVisibility(View.GONE);
        } else {
            String[] items = new String[]{"None", "1000-2000", "2000-5000", "5000-10000", "10000-15000", "15000-25000"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, items);
            salarySpinner.setAdapter(adapter);

            deadline_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog datePicker = new DatePickerDialog(getContext(),PostFragment.this,
                            Calendar.getInstance().get(Calendar.YEAR),
                            Calendar.getInstance().get(Calendar.MONTH),
                            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                    );
                    datePicker.show();
                }
            });

            post_form_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    subjects = obtainCheckTextLayout(subject_linear_one);
                    subjects += obtainCheckTextLayout(subject_linear_two);
                    classes = obtainCheckTextLayout(class_linear_one);
                    classes += obtainCheckTextLayout(class_linear_two);
                    medium = obtainCheckTextLayout(medium_linear);

                    salary = salarySpinner.getSelectedItem().toString();
                    location = location_text.getText().toString();
                    university = university_text.getText().toString();
                    deadline = deadline_text.getText().toString();

                    if (subjects.isEmpty()) {
                        Toast.makeText(getContext(), "Please select your subjects", Toast.LENGTH_SHORT).show();
                    } else if (classes.isEmpty()) {
                        Toast.makeText(getContext(), "Please select your classes", Toast.LENGTH_SHORT).show();
                    } else if (medium.isEmpty()) {
                        Toast.makeText(getContext(), "Please select your medium language", Toast.LENGTH_LONG).show();
                    } else if (university.isEmpty()) {
                        Toast.makeText(getContext(), "Please select your university", Toast.LENGTH_LONG).show();
                    } else if (deadline.isEmpty()) {
                        Toast.makeText(getContext(), "Please select your deadline date", Toast.LENGTH_SHORT).show();
                    } else if (location.isEmpty()){
                        Toast.makeText(getContext(), "Please select your location", Toast.LENGTH_SHORT).show();
                    } else {
                        subjects = subjects.substring(0, subjects.length() - 1);
                        classes = classes.substring(0, classes.length() - 1);
                        medium = medium.substring(0, medium.length() - 1);

                        final ProgressDialog progressDialog = new ProgressDialog(getContext());
                        progressDialog.setMessage("Please Wait...");

                        progressDialog.show();

                        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();

                                if (!response.equalsIgnoreCase("Post Added Successfully.")) {
                                    Log.e("Info:", response);
                                } else {
                                    Toast.makeText(getContext(), "Successfully Form Posted", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getContext(),MainActivity.class));
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("postby_id", ID);
                                params.put("subject", subjects);
                                params.put("class", classes);
                                params.put("medium", medium);
                                params.put("salary", salary);
                                params.put("location", location);
                                params.put("p_university", university);
                                params.put("deadline", deadline);
                                return params;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                        requestQueue.add(request);
                    }
                }
            });
        }

        return root;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = (month+1) + "/" + dayOfMonth + "/" + year;
        deadline_text.setText(date);
    }

    private String obtainCheckTextLayout(LinearLayout linearLayout){
        String string = "";
        int count = linearLayout.getChildCount();
        for (int i = 0; i < count; i++) {
            CheckBox child = (CheckBox) linearLayout.getChildAt(i);
            if (child instanceof CheckBox && child.isChecked()) {
                string += child.getText().toString() + ",";
            }
        }

        return string;
    }
}