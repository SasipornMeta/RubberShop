package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.CustomerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.OwnerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.GetAllValueFromServer;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyAlert;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyConstant;

/**
 * Created by sasiporn on 2/7/2018 AD.
 */

public class AuthenFragment extends Fragment {

    private String urlCategoryString, userString, passwordString;
    private MyConstant myConstant;
    private RadioButton ownerRadioButton, coutomerRadioButton;
    private String[] loginStrings;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Setup Constant
        setupConstant();

//        Register Controller
        registerController();                                           // มาจากการกด option+command+m

//        Category Controller
        categoryController();                                           // มาจากการกด option+command+m

//        Login Conller
        loginConller();                                                 // มาจากการกด option+command+m


    }   // main method

    private void loginConller() {
        Button button = getView().findViewById(R.id.btnlogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText userEditText = getView().findViewById(R.id.edtIdStore);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                if (userString.isEmpty() || passwordString.isEmpty()) {
//                    Have Space
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.normalDialog(getString(R.string.title_have_space)
                    ,getString(R.string.message_have_space));

                } else {
//                    NO Space
                    try {

                        GetAllValueFromServer getAllValueFromServer = new GetAllValueFromServer(getActivity());
                        getAllValueFromServer.execute(urlCategoryString);
                        boolean userAboolean = true;
                        boolean passwordAboolean = true;

                        String jsonString = getAllValueFromServer.get();
                        showLog("JSON ==>" + jsonString);

                        JSONArray jsonArray = new JSONArray(jsonString);
                        for (int i=0; i<jsonArray.length();i+=1) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            if (ownerRadioButton.isChecked()) {

//                            Choose Owner
                                if (userString.equals(jsonObject.getString("o_idshop"))) {
//                                    User True
                                    userAboolean = false;
                                    passwordAboolean = !(passwordString.equals(jsonObject.getString("o_password")));

                                    loginStrings = new String[4];
                                    loginStrings[0] = jsonObject.getString("o_id");
                                    loginStrings[1] = jsonObject.getString("o_shopname");
                                    loginStrings[2] = jsonObject.getString("o_idshop");
                                    loginStrings[3] = jsonObject.getString("o_password");

                                }   // if
                            } else {

//                            Choose Customer
                                if (userString.equals(jsonObject.getString("c_user"))) {
                                    userAboolean = false;
                                    passwordAboolean = !(passwordString.equals(jsonObject.getString("c_password")));
                                    loginStrings = new String[7];
                                    loginStrings[0] =jsonObject.getString("c_id");
                                    loginStrings[1] =jsonObject.getString("c_name");
                                    loginStrings[2] =jsonObject.getString("c_lname");
                                    loginStrings[3] =jsonObject.getString("c_address");
                                    loginStrings[4] =jsonObject.getString("c_tel");
                                    loginStrings[5] =jsonObject.getString("c_user");
                                    loginStrings[6] =jsonObject.getString("c_password");
                                }

                            }   // if
                        }   // for

                        if (userAboolean) {

//                            User False
                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.normalDialog("รหัสผู้ใช้ผิดพลาด",
                                    "กรุณากรอกรหัสผู้ใช้ให้ถูกต้อง !");

                        } else if (passwordAboolean) {

//                            Password False
                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.normalDialog("รหัสผ่านผิดพลาด",
                                    "กรุณากรอกรหัสผ่านให้ถูกต้อง !");

                        } else {

//                            Authen True
                            if (ownerRadioButton.isChecked()) {
//                                Owner Choose
                                Intent intent = new Intent(getActivity(), OwnerActivity.class);
                                intent.putExtra("Login", loginStrings);
                                getActivity().startActivity(intent);
                                getActivity().finish();


                            } else {
//                                Customer Choose
                                Intent intent = new Intent(getActivity(), CustomerActivity.class);
                                intent.putExtra("Login", loginStrings);
                                getActivity().startActivity(intent);
                                getActivity().finish();


                            }


                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }   // if

            }   //onClick
        });


    }

    private void setupConstant() {
        myConstant = new MyConstant();
        urlCategoryString = myConstant.getUrlGetAllOwner();

        ownerRadioButton = getView().findViewById(R.id.radOwner);
        coutomerRadioButton = getView().findViewById(R.id.radCustomers);
    }

    private void categoryController() {
        RadioGroup radioGroup = getView().findViewById(R.id.ragCategory);   //  เสร็จบรรทัดนี้ คุมทั้งบรรทัด กด option+command+m
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {

                    case R.id.radOwner:
                        urlCategoryString = myConstant.getUrlGetAllOwner();
                        break;

                    case R.id.radCustomers:
                        urlCategoryString = myConstant.getUrlGetAllCustomer();
                        break;
                }

                showLog("urlCategory ==> " +urlCategoryString);

            }
        });



    }

    private void showLog(String messageString) {
        Log.d("8FebV1", messageString);
    }

    private void registerController() {
        Button button = getView().findViewById(R.id.btnRegister);           //  เสร็จบรรทัดนี้ คุมทั้งบรรทัด กด option+command+m
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_authen, container, false);
        return view;
    }
}
