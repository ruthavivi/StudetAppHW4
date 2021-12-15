package com.example.class3demo2.model;

import com.example.class3demo2.MyApplication;

import java.util.LinkedList;
import java.util.List;

public class Model {
    public static final Model instance = new Model();

    private Model(){
    }

    public interface DeleteStudentListener{
        void onComplete(Student student);
    }

    public void deleteStudent(Student student, DeleteStudentListener listener) {
        MyApplication.executorService.execute(()->{
            AppLocalDB.db.studentDao().delete(student);
            MyApplication.mainHandler.post(()->{
                listener.onComplete(student);
            });
        });
    }

    public interface GetAllStudentsListener{
        void onComplete(List<Student> data);
    }
    public void getAllStudents(GetAllStudentsListener listener){
        MyApplication.executorService.execute(()->{
            List<Student> data = AppLocalDB.db.studentDao().getAll();
            MyApplication.mainHandler.post(()->{
                listener.onComplete(data);
            });
        });
    }

    public interface AddStudentListener{
        void onComplete();
    }
    public void addStudent(Student student, AddStudentListener listener){
        MyApplication.executorService.execute(()->{
            AppLocalDB.db.studentDao().insertAll(student);
            MyApplication.mainHandler.post(()->{
                listener.onComplete();
            });
        });
    }

    public interface GetStudentByIdListener{
        void onComplete(Student student);
    }
    public void getStudentById(String studentId,GetStudentByIdListener listener) {
        MyApplication.executorService.execute(()->{
            Student student = AppLocalDB.db.studentDao().getStudentById(studentId);
            MyApplication.mainHandler.post(()->{
                listener.onComplete(student);
            });
        });
    }

    public interface UpdateStudentListener{
        void onComplete(Student student);
    }


    public void UpdateStudent(Student student, UpdateStudentListener listener) {
        MyApplication.executorService.execute(()->{
            AppLocalDB.db.studentDao().update(student);
            MyApplication.mainHandler.post(()->{
                listener.onComplete(student);
            });
        });
    }
}
