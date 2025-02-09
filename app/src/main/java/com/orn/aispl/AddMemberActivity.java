package com.orn.aispl;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.orn.aispl.databinding.ActivityAddMemberBinding;

public class AddMemberActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private ActivityAddMemberBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMemberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = openOrCreateDatabase("MemberDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Members(" +
                "Name TEXT, " +
                "Mobile TEXT, " +
                "Role TEXT, " +
                "Subscription REAL, " +
                "Loan REAL, " +
                "Deposit REAL, " +
                "Gender TEXT, " +
                "DOB TEXT, " +
                "DOJ TEXT, " +
                "MaritalStatus TEXT, " +
                "DOM TEXT, " +
                "Caste TEXT, " +
                "Religion TEXT, " +
                "Category TEXT, " +
                "Aadhar TEXT)");

        binding.submitButton.setOnClickListener(v -> saveMemberDetails());
    }

    private void saveMemberDetails() {
        String name = binding.nameInput.getText().toString().trim();
        String mobile = binding.mobileNumberInput.getText().toString().trim();
        String subscription = binding.subscriptionFeeInput.getText().toString().trim();
        String deposit = binding.depositAmountInput.getText().toString().trim();
        String loan = binding.loanAmountInput.getText().toString().trim();
        String dob = binding.dobInput.getText().toString().trim();
        String doj = binding.dateOfJoiningInput.getText().toString().trim();
        String dom = binding.dateOfMarriageInput.getText().toString().trim();
        String caste = binding.casteInput.getText().toString().trim();
        String religion = binding.religionInput.getText().toString().trim();
        String category = binding.categoryInput.getText().toString().trim();
        String aadhar = binding.aadharInput.getText().toString().trim();

        int selectedRoleId = binding.roleGroup.getCheckedRadioButtonId();
        int selectedGenderId = binding.genderGroup.getCheckedRadioButtonId();
        int selectedMaritalStatusId = binding.maritalStatusGroup.getCheckedRadioButtonId();

        if (name.isEmpty()) {
            binding.nameInput.setError("Name is required");
            binding.nameInput.requestFocus();
            return;
        }

        if (mobile.isEmpty() || !mobile.matches("\\d{10}")) {
            binding.mobileNumberInput.setError("Enter a valid 10-digit mobile number");
            binding.mobileNumberInput.requestFocus();
            return;
        }

        if (aadhar.isEmpty() || !aadhar.matches("\\d{12}")) {
            binding.aadharInput.setError("Enter a valid 12-digit Aadhar number");
            binding.aadharInput.requestFocus();
            return;
        }

        if (!subscription.isEmpty() && !subscription.matches("\\d+")) {
            binding.subscriptionFeeInput.setError("Enter a valid amount");
            binding.subscriptionFeeInput.requestFocus();
            return;
        }

        if (!deposit.isEmpty() && !deposit.matches("\\d+")) {
            binding.depositAmountInput.setError("Enter a valid amount");
            binding.depositAmountInput.requestFocus();
            return;
        }
        if (!loan.isEmpty() && !loan.matches("\\d+")) {
            binding.loanAmountInput.setError("Enter a valid amount");
            binding.loanAmountInput.requestFocus();
            return;
        }

        if (dob.isEmpty()) {
            binding.dobInput.setError("Date of Birth is required");
            binding.dobInput.requestFocus();
            return;
        }

        if (doj.isEmpty()) {
            binding.dateOfJoiningInput.setError("Date of Joining is required");
            binding.dateOfJoiningInput.requestFocus();
            return;
        }

        if (caste.isEmpty()) {
            binding.casteInput.setError("Caste is required");
            binding.casteInput.requestFocus();
            return;
        }
        if (religion.isEmpty()) {
            binding.religionInput.setError("Religion is required");
            binding.religionInput.requestFocus();
            return;
        }
        if (category.isEmpty()) {
            binding.categoryInput.setError("Category is required");
            binding.categoryInput.requestFocus();
            return;
        }

        if (selectedRoleId == -1) {
            Toast.makeText(this, "Please select a Role", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selectedGenderId == -1) {
            Toast.makeText(this, "Please select Gender", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selectedMaritalStatusId == -1) {
            Toast.makeText(this, "Please select Marital Status", Toast.LENGTH_SHORT).show();
            return;
        }

        String role = ((RadioButton) findViewById(selectedRoleId)).getText().toString();
        String gender = ((RadioButton) findViewById(selectedGenderId)).getText().toString();
        String maritalStatus = ((RadioButton) findViewById(selectedMaritalStatusId)).getText().toString();

        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Mobile", mobile);
        values.put("Role", role);
        values.put("Subscription", subscription);
        values.put("Loan", loan);
        values.put("Deposit", deposit);
        values.put("Gender", gender);
        values.put("DOB", dob);
        values.put("DOJ", doj);
        values.put("MaritalStatus", maritalStatus);
        values.put("DOM", dom);
        values.put("Caste", caste);
        values.put("Religion", religion);
        values.put("Category", category);
        values.put("Aadhar", aadhar);

        long result = db.insert("Members", null, values);

        if (result != -1) {
            Toast.makeText(this, "Member Added Successfully", Toast.LENGTH_SHORT).show();
            clearFields();
        } else {
            Toast.makeText(this, "Error Adding Member", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        binding.nameInput.setText("");
        binding.mobileNumberInput.setText("");
        binding.subscriptionFeeInput.setText("");
        binding.depositAmountInput.setText("");
        binding.loanAmountInput.setText("");
        binding.dobInput.setText("");
        binding.dateOfJoiningInput.setText("");
        binding.dateOfMarriageInput.setText("");
        binding.casteInput.setText("");
        binding.religionInput.setText("");
        binding.categoryInput.setText("");
        binding.aadharInput.setText("");
        binding.roleGroup.clearCheck();
        binding.genderGroup.clearCheck();
        binding.maritalStatusGroup.clearCheck();
    }
}
