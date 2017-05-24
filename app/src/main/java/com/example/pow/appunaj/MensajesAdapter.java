package com.example.pow.appunaj;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pow on 23/05/17.
 */

public class MensajesAdapter extends RecyclerView.Adapter<MensajesAdapter.MensajesAdapterViewHolder> {
    private ArrayList<String> mensajes;

    @Override
    public MensajesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int idLayout = R.layout.mensaje;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean attach = false;

        View view = inflater.inflate(idLayout,parent, attach);
        return new MensajesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MensajesAdapterViewHolder holder, int position) {
        String mensaje = mensajes.get(position);
        holder.mensaje.setText(mensaje);

    }

    @Override
    public int getItemCount() {
        if (null == mensajes) return 0;
        return mensajes.size();
    }

    public void agregarMensaje(String mensaje){
        mensajes.add(mensaje);
        notifyDataSetChanged();
    }

    public class MensajesAdapterViewHolder extends RecyclerView.ViewHolder{
        public final TextView mensaje;
        public MensajesAdapterViewHolder(View view){
            super(view);
            mensaje = (TextView) view.findViewById(R.id.mensajeEnRV);
        }
    }

}
