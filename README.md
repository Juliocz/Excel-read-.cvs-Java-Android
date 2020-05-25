# Lector de archivos .CSV en Java/android
Codigos de Java Android.

Buenas en este proyecto les presento una clase que codifique en la cual le servira para poder leer archivos .csv de Androd con Java.
Pueden modificar la clase a su gusto, por si quieren darle otros metodos, no borren el autor original en este caso JulioCz
# ¿Que tiene este Codigo?
La clase csvread.java les ayudara a leer archivos .csv guardados con Excel
Tiene metodos funcionales que les ayudara a Obtener los datos del archivos asi como sus celdas
# Tambien es posible obtener directamente un Objeto TableLayout con la clase Csv Read
```java
public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TableLayout container = (TableLayout) findViewById(R.id.caja_table);

        csvread micvs = new csvread(this, this);
        container.addView(micvs.getTablelayout());

    }
}
```
![][demo-gif]
# Antes de leer cualquier .Cvs deben asignar el Id de su Archivo de Recursos
Ej:
```java
        
        micvs.setIdCVS(R.raw.misdatoscvs);
```

# Metodos de clase .csvread.java

Obteniendo String de una celda Contando de 0 a ....
```java
        String celda;
        celda=micvs.GetCell(0,0);
```
Obteniendo String de una celda Formato Excell Indicando Columna y Fila
```java
        String celda;
        celda=micvs.GetCellexcelformat('a',1);
```
Obteniendo una lista de todos los Elementos de la columna 0
```java
 ArrayList <String> columna=new ArrayList<String>();
        micvs.getlistcolumna(0);
```
Obteniendo una lista de todos los Elementos de la linea 0
```java
 ArrayList <String> linea=new ArrayList<String>();
        micvs.getlistlinea(0);
```
Hay mas metodos como obtener el tamaño de columnas, y lineas, y demas, pero la prueban.

# TESTEO DE LECTURA
Asi tambien pueden testar la lectura de su archivo .cvs
Los resultados se mostraran en la consola Log.
Solo deben llamar a los metodos :
```java
micvs.leerlinea(0)
```
![][test-png]

[demo-gif]: https://s7.gifyu.com/images/csvtable.gif

[test-png]: url=https://ibb.co/9t1kh7K][img]https://i.ibb.co/YZ1FXxn/cf.png
