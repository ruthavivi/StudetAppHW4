package com.example.class3demo2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.class3demo2.model.Model;
import com.example.class3demo2.model.Student;

public class StudentDetailsFragment extends Fragment {
    Student student;
    TextView nameTv;
    TextView idTv;
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_details, container, false);


        nameTv = view.findViewById(R.id.studentdetails_name_tv);
        idTv = view.findViewById(R.id.studentdetails_id_tv);
        progressBar = view.findViewById(R.id.studentdetails_progressbar);
        progressBar.setVisibility(View.VISIBLE);

        String studentId = StudentDetailsFragmentArgs.fromBundle(getArguments()).getStudentId();
        Model.instance.getStudentById(studentId, (student)->{
            updateDisplay(student);
        });

        if (student != null){
            updateDisplay(student);
        }

        Button editBtn = view.findViewById(R.id.EditBtnSI);
        editBtn.setOnClickListener
                (Navigation.createNavigateOnClickListener
                        (StudentDetailsFragmentDirections.actionStudentDetailsFragmentToEditStudentFragment(studentId)));

        return view;
    }

    private void updateDisplay(Student student) {
        this.student = student;
        nameTv.setText(student.getName());
        idTv.setText(student.getId());
        progressBar.setVisibility(View.GONE);
    }
}