package com.example.myapplicationfactures

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val data = ArrayList<String>()
    private val data1 = ArrayList<String>()
    private val data2 = ArrayList<String>()
    private val data3 = ArrayList<String>()






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pnom = findViewById(R.id.ed1) as EditText
        val prix = findViewById(R.id.ed2) as EditText
        val qty = findViewById(R.id.ed3) as EditText

        val subtotal = findViewById(R.id.ed4) as EditText
        val paiment = findViewById(R.id.ed5) as EditText
        val balance = findViewById(R.id.ed6) as EditText

       val b1 = findViewById(R.id.btn1) as Button
        val b2 = findViewById(R.id.btn2) as Button

        b2.setOnClickListener {
        val sub =Integer.parseInt(subtotal.getText().toString())
            val payment =Integer.parseInt( paiment.getText().toString())
            val bal =payment-sub
            balance.setText(bal.toString())

        }


     b1.setOnClickListener{
         val prodnom = pnom.text.toString()
         val priix = prix.text.toString().toInt()
         val qtyy = qty.text.toString().toInt()

        val total = priix * qtyy
         data.add(prodnom)
         data.add(priix.toString())
         data.add(qtyy.toString())
         data.add(total.toString())

         val table = findViewById(R.id.tb1) as TableLayout

         val row = TableRow (this)
         val t1 = TextView(this)
         val t2 = TextView(this)
         val t3 = TextView (this)
         val t4 = TextView (this)

         var tot: String
         var sum = 0
         for (i in data.indices){
             val pa = data[i]
             val prc = data1[i]
             val qt = data2[i]
             val to = data3[i]
             t1.text = pa
             t2.text = prc
             t3.text = qt
             t4.text = to
              sum = sum + Integer.parseInt(data3[i])
         }
         row.addView(t1)
         row.addView(t2)
         row.addView(t3)
         row.addView(t4)
         table.addView(row)




         subtotal.setText(sum.toString())
         pnom.setText("")
         prix.setText("")
         qty.setText("")
        pnom.requestFocus()














     }






    }




}