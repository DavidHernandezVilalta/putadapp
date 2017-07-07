package com.davidhernandezvilaltagmail.projecte1.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.davidhernandezvilaltagmail.projecte1.R;

import java.util.ArrayList;

/**
 * Created by David Hdez on 07/07/2017.
 */

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.AdapterViewHolder>{

    ArrayList<Contract> contactos;

    MyCustomAdapter(){
        contactos = new ArrayList<>();
        contactos.add(new Contract(0,"Benito Camela","123456789"));
        contactos.add(new Contract(0,"Alberto Carlos Huevos","123456789"));
        contactos.add(new Contract(1,"Lola Mento","123456789"));
        contactos.add(new Contract(0,"Aitor Tilla","123456789"));
        contactos.add(new Contract(0,"Elvis Teck","123456789"));
        contactos.add(new Contract(1,"Débora Dora","123456789"));
        contactos.add(new Contract(0,"Borja Món de York","123456789"));
        contactos.add(new Contract(1,"Encarna Vales","123456789"));
        contactos.add(new Contract(0,"Enrique Cido","123456789"));
        contactos.add(new Contract(0,"Francisco Jones","123456789"));
        contactos.add(new Contract(1,"Estela Gartija","123456789"));
        contactos.add(new Contract(0,"Andrés Trozado","123456789"));
        contactos.add(new Contract(0,"Carmelo Cotón","123456789"));
        contactos.add(new Contract(0,"Alberto Mate","123456789"));
        contactos.add(new Contract(0,"Chema Pamundi","123456789"));
        contactos.add(new Contract(0,"Armando Adistancia","123456789"));
        contactos.add(new Contract(1,"Helena Nito Del Bosque","123456789"));
        contactos.add(new Contract(0,"Unai Nomás","123456789"));
        contactos.add(new Contract(1,"Ester Colero","123456789"));
        contactos.add(new Contract(0,"Marcos Corrón","123456789"));
    }


    @Override
    public MyCustomAdapter.AdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //Instancia un layout XML en la correspondiente vista.
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        //Inflamos en la vista el layout para cada elemento
        View view = inflater.inflate(R.layout.rowlayout, viewGroup, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyCustomAdapter.AdapterViewHolder adapterViewholder, int position) {
        int iconLayout = contactos.get(position).getIcon();
        //Dependiendo del entero se asignará una imagen u otra
        Log.v("entro", "guatafac");
        switch (iconLayout){
            case 0:
                //male
                adapterViewholder.icon.setImageDrawable(adapterViewholder.v.getResources().getDrawable(R.drawable.ic_exit));
                break;
            case 1:
                //female
                adapterViewholder.icon.setImageDrawable(adapterViewholder.v.getResources().getDrawable(R.drawable.ic_exit2));
                break;
        }
        adapterViewholder.name.setText(contactos.get(position).getName());
        adapterViewholder.phone.setText(contactos.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        //Debemos retornar el tamaño de todos los elementos contenidos en el viewholder
        //Por defecto es return 0 --> No se mostrará nada.
        return contactos.size();
    }



    //Definimos una clase viewholder que funciona como adapter para
    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        /*
        *  Mantener una referencia a los elementos de nuestro ListView mientras el usuario realiza
        *  scrolling en nuestra aplicación. Así que cada vez que obtenemos la vista de un item,
        *  evitamos las frecuentes llamadas a findViewById, la cuál se realizaría únicamente la primera vez y el resto
        *  llamaríamos a la referencia en el ViewHolder, ahorrándonos procesamiento.
        */

        public ImageView icon;
        public TextView name;
        public TextView phone;
        public View v;
        public AdapterViewHolder(View itemView) {
            super(itemView);
            this.v = itemView;
            this.icon = (ImageView) itemView.findViewById(R.id.icon);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.phone = (TextView) itemView.findViewById(R.id.phone);
        }
    }
}

