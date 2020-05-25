package com.example.pc9.cargarbotones;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by JULIOCz
 */
public class csvread {

    //atributos
    MainActivity m;
    Context context;
    int idcvs=R.raw.listatest;
    public csvread(MainActivity m, Context context)
    {this.m=m;
        this.context=context;

    }

    ///Asignar id del archivo en el raw
    public void setIdCVS(int idraw)
    {
        idcvs=idraw;
    }
    ////leer columna en LOG
    public void leercolumna(int column)
    {
        if(verificarcvs())
        {
            Log.d("log.i",String.valueOf(getlistcolumna(column)));
        }
    }
    ////leer linea en LOG
    public void leerlinea(int linea)
    {
        if(verificarcvs())
        {
            Log.d("log.i",String.valueOf(getlistlinea(linea)));
        }
    }
    //OBTENER Celdas de una Columna
    public ArrayList <String>getlistcolumna(int columnna)
    {
        Log.d("log.i","getListcolumna");
        ArrayList <String>list=new ArrayList<>();
        BufferedReader filer=new BufferedReader((new InputStreamReader(context.getResources().openRawResource(idcvs))));
        String linea;
        try {
            while((linea=filer.readLine())!=null)
            {Log.d("log.i",Slipgetcolumna(linea,columnna));
                list.add(Slipgetcolumna(linea,columnna));
            }
        } catch (Exception d) {
            Log.d("log.i","ERROR EN METODO GETLISTCOLUMNA");
        }
        if(list==null)
            Log.d("log.i","COLUMNA VACIA!");


        return list;
    }
    //OBTENER Celdas de una Linea
    public ArrayList <String>getlistlinea(int lineal)
    {
        Log.d("log.i","getListLinea");
        ArrayList <String>list=new ArrayList<>();

        BufferedReader filer=new BufferedReader((new InputStreamReader(context.getResources().openRawResource(idcvs))));
        String linea;

        //contador 1
        int i=0;

        try {
            while((linea=filer.readLine())!=null)
            {   if(i==lineal)
            {

                return Slipgetlinea(linea);
            }
                i++;
            }
        } catch (Exception d) {
            Log.d("log.i","ERROR EN METODO GETLISTLINEACOLUMNA");
        }
        return list;
    }
    private String Slipgetcolumna(String line,int column)
    {
        String []r=line.split(";");
        return r[column];
    }
    private ArrayList <String>Slipgetlinea(String line)
    {   ArrayList <String>list=new ArrayList<>();
        String []r=line.split(";");
        for(int i=0;i<r.length;i++)
            list.add(r[i]);
        return list;
    }
    ////VERIFICAR SI EXISTE EL CVS
    public Boolean verificarcvs()
    {   try {
        InputStream file = context.getResources().openRawResource(idcvs);
        Log.d("log.i","ARCHIVO ENCONTRADO");
        return true;
    }
    catch (Exception d)
    {
        Log.d("log.i","ARCHIVO NO ENCONTRADO");
        return false;
    }
    }
    ////OBTENER NUMERO DE COLUMNAS
    public int getColumnsindex()
    {
        ArrayList <String>list=new ArrayList<>();
        BufferedReader filer=new BufferedReader((new InputStreamReader(context.getResources().openRawResource(idcvs))));

        try {
            return Slipgetlinea(filer.readLine()).size();
        }

        catch (Exception d) {
            Log.d("log.i","ERROR EN METODO GETLISTLINEACOLUMNA");
        }
        return -1;
    }
    //OBTENER NUMERO DE LINEAS
    public int getLinesindex()
    {

        BufferedReader filer=new BufferedReader((new InputStreamReader(context.getResources().openRawResource(idcvs))));
        String linea;

        int i=0;
        try {
            while((linea=filer.readLine())!=null)
            {i++;
            }
        } catch (Exception d) {
            Log.d("log.i","ERROR EN METODO GETLISTCOLUMNA");
        }
        return i;
    }

    public String GetCellexcelformat(char column,int line)
    { if(column>=97 && column<=122)
        column-=97;
        else if(column>=65 && column<=90)
            column-=65;
            else {
        Log.d("log.i", "Columna No encontrada a-z A-Z");
        return null;
                }
        line-=1;
        if(column>getColumnsindex())
        {   Log.d("log.i", "COLUMNA  No encontrada");
            return null;
        }
        if(line>getLinesindex())
        {   Log.d("log.i", "LINEA  No encontrada");
            return null;
        }
        return getlistlinea(line).get(column);
    }
    public String GetCell(int column,int line)
    {   if(line>getLinesindex())
    {   Log.d("log.i", "LINEA  No encontrada");
        return null;}
        if(column>getColumnsindex())
        {   Log.d("log.i", "COLUMNA  No encontrada");
            return null;}

        return getlistlinea(line).get(column);
    }
    public TableLayout getTablelayout()
    {
        //creo tablelayout
        TableLayout table = new TableLayout(context);
        table.setStretchAllColumns(true);
        int num_lineas=getLinesindex();
        int num_columns=getColumnsindex();

        //creo numeros de rows lineas
        TableRow[] tableRow = new TableRow[num_lineas];
        //linea x linea
        for(int i=0;i<num_lineas;i++)
        {
            //inicialiso la linea
            tableRow[i] = new TableRow(context);
            tableRow[i].setGravity(Gravity.CENTER);
            ArrayList <String>listline=new ArrayList<>();
            //obtengo array de la linea
            listline=getlistlinea(i);

            //Agrego el array list a la linea table row i
            for(int j=0;j<listline.size();j++) {
                TextView celda = new TextView(context);
                celda.setGravity(Gravity.LEFT);
                celda.setText(listline.get(j));
                tableRow[i].addView(celda);
                }
            //agrego la linea tablerow al Tablelayout
            table.addView(tableRow[i]);
        }
        return table;

    }

}

