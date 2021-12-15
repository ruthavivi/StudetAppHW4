package com.example.class3demo2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.class3demo2.model.Model;
import com.example.class3demo2.model.Student;

import java.util.List;


public class EditStudentFragment extends Fragment {

    EditText EditName;
    EditText EditId;
    CheckBox cb;
    View view;
    ProgressBar progressbar;
    Button saveBtn;
    Button cancelBtn;
    Student student;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_edit_student, container, false);

        EditName = view.findViewById(R.id.se_name_tv);
        EditId = view.findViewById(R.id.se_id_tv);
        //cb = view.findViewById(R.id.main_cb);
        //progressbar = view.findViewById(R.id.main_progressbar);
        //progressbar.setVisibility(View.GONE);

        String studentId_ = EditStudentFragmentArgs.fromBundle(getArguments()).getStudentId();
        Model.instance.getStudentById(studentId_,(student -> {
            updateDisplay(student);
        }));
        if (student != null)
        {
            EditName.setText(student.getName());
            EditId.setText(student.getId());
        }



        saveBtn = view.findViewById(R.id.edit_save_btn);
        cancelBtn = view.findViewById(R.id.edit_cancel_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameE = EditName.getText().toString();
                String idE = EditId.getText().toString();
                student.setName(nameE);
                student.setId(idE);

                Model.instance.UpdateStudent(student,(student->{
                    updateDisplay(student);
                }));


                Navigation.findNavController(v).navigate(R.id.action_editStudentFragment_to_studentsListFragment);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Navigation.findNavController(view).navigate(R.id.action_editStudentFragment_to_studentsListFragment);
            }
        });

        Button DeleteBtn = view.findViewById(R.id.edit_delete_btn);
        DeleteBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Model.instance.deleteStudent(student,(student->{
                    updateDisplay(student);
                }));

                Navigation.findNavController(view).navigate(R.id.action_editStudentFragment_to_studentsListFragment);
            }
        });


        return view;

    }

    private void updateDisplay(Student student) {
        this.student=student;
    }


}
