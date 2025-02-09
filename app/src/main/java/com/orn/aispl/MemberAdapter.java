package com.orn.aispl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {

    private List<Member> memberList;

    public MemberAdapter(List<Member> memberList) {
        this.memberList = memberList;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_item, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        Member member = memberList.get(position);
        holder.tvMemberName.setText("Member Name: " + member.getName());
        holder.tvMobileNo.setText("Mobile No.: " + member.getMobileNo());
        holder.tvMemberRole.setText("Role: " + member.getRole());
        holder.tvSubscriptionAmt.setText("Subscription Amt.: " + member.getSubscriptionAmt());
        holder.tvLoanAmount.setText("Loan Amount: " + member.getLoanAmt());
        holder.tvDepositAmount.setText("Deposit Amount: " + member.getDepositAmt());
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    static class MemberViewHolder extends RecyclerView.ViewHolder {
        TextView tvMemberName, tvMobileNo, tvMemberRole, tvSubscriptionAmt, tvLoanAmount, tvDepositAmount;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMemberName = itemView.findViewById(R.id.tvMemberName);
            tvMobileNo = itemView.findViewById(R.id.tvMobileNo);
            tvMemberRole = itemView.findViewById(R.id.tvMemberRole);
            tvSubscriptionAmt = itemView.findViewById(R.id.tvSubscriptionAmt);
            tvLoanAmount = itemView.findViewById(R.id.tvLoanAmount);
            tvDepositAmount = itemView.findViewById(R.id.tvDepositAmount);
        }
    }
}

