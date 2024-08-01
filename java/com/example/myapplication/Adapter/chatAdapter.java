package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.messagesModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class chatAdapter extends RecyclerView.Adapter {
    ArrayList<messagesModel> messagesModels;
    Context context;
    int Sender_View_Type=1;
    int Reciver_View_type=2;


    public chatAdapter(ArrayList<messagesModel> messagesModels, Context context) {
        this.messagesModels = messagesModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      if(viewType==Sender_View_Type){
View view = LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
return  new SenderViewHolder(view);
      }
      else {
          View view = LayoutInflater.from(context).inflate(R.layout.sample_reciver,parent,false);
          return  new RecieverViewHolder(view);
      }
    }

    @Override
    public int getItemViewType(int position) {
        if(messagesModels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())){

            return  Sender_View_Type;
        }
        else {
            return Reciver_View_type;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        messagesModel messagesModel = messagesModels.get(position);
        if(holder.getClass() == SenderViewHolder.class){
            ((SenderViewHolder)holder).senderMsg.setText(messagesModel.getMessage());
        }
else{
            ((RecieverViewHolder)holder).recieverMsg.setText(messagesModel.getMessage());
}
        }



    @Override
    public int getItemCount() {
        return messagesModels.size();
    }

    public class RecieverViewHolder extends RecyclerView.ViewHolder{
TextView recieverMsg ,recievertime;
        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);

        recieverMsg = itemView.findViewById(R.id.recivertext);
        recievertime = itemView.findViewById(R.id.revivertime);
        }
    }
    public class SenderViewHolder extends  RecyclerView.ViewHolder{
TextView senderMsg,senderTime;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMsg = itemView.findViewById(R.id.senderText);
            senderTime = itemView.findViewById(R.id.senderTime);

        }


    }
}
