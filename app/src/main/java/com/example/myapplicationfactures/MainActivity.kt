package com.example.myapplicationfactures

import android.widget.*
import androidx.appcompat.app.AppCompatActivity


import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream

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


    class MainActivity : AppCompatActivity() {


        lateinit var generatePDFBtn: Button


        var pageHeight = 1120
        var pageWidth = 792


        lateinit var bmp: Bitmap
        lateinit var scaledbmp: Bitmap


        var PERMISSION_CODE = 101

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)


            generatePDFBtn = findViewById(R.id.idBtnGeneratePdf)


            bmp = BitmapFactory.decodeResource(resources, R.drawable.android)
            scaledbmp = Bitmap.createScaledBitmap(bmp, 140, 140, false)


            if (checkPermissions()) {

                Toast.makeText(this, "Permissions Granted..", Toast.LENGTH_SHORT).show()
            } else {

                requestPermission()
            }


            generatePDFBtn.setOnClickListener {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {

                    generatePDF()
                }
            }
        }


        @RequiresApi(Build.VERSION_CODES.KITKAT)
        fun generatePDF() {

            var pdfDocument: PdfDocument = PdfDocument()


            var paint: Paint = Paint()
            var title: Paint = Paint()


            var myPageInfo: PdfDocument.PageInfo? =
                PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()


            var myPage: PdfDocument.Page = pdfDocument.startPage(myPageInfo)


            var canvas: Canvas = myPage.canvas


            canvas.drawBitmap(scaledbmp, 56F, 40F, paint)


            title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL))


            title.textSize = 15F


            title.setColor(ContextCompat.getColor(this, R.color.purple_200))


            canvas.drawText("A portal for IT professionals.", 209F, 100F, title)
            canvas.drawText("Geeks for Geeks", 209F, 80F, title)
            title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL))
            title.setColor(ContextCompat.getColor(this, R.color.purple_200))
            title.textSize = 15F


            title.textAlign = Paint.Align.CENTER
            canvas.drawText("This is sample document which we have created.", 396F, 560F, title)


            pdfDocument.finishPage(myPage)


            val file: File = File(Environment.getExternalStorageDirectory(), "GFG.pdf")

            try {

                pdfDocument.writeTo(FileOutputStream(file))


                Toast.makeText(applicationContext, "PDF file generated..", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {

                e.printStackTrace()


                Toast.makeText(applicationContext, "Fail to generate PDF file..", Toast.LENGTH_SHORT)
                    .show()
            }

            pdfDocument.close()
        }

        fun checkPermissions(): Boolean {

            var writeStoragePermission = ContextCompat.checkSelfPermission(
                applicationContext,
                WRITE_EXTERNAL_STORAGE
            )


            var readStoragePermission = ContextCompat.checkSelfPermission(
                applicationContext,
                READ_EXTERNAL_STORAGE
            )


            return writeStoragePermission == PackageManager.PERMISSION_GRANTED
                    && readStoragePermission == PackageManager.PERMISSION_GRANTED
        }


        fun requestPermission() {


            ActivityCompat.requestPermissions(
                this,
                arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE), PERMISSION_CODE
            )
        }


        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)


            if (requestCode == PERMISSION_CODE) {


                if (grantResults.size > 0) {


                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1]
                        == PackageManager.PERMISSION_GRANTED) {


                        Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show()

                    } else {


                        Toast.makeText(this, "Permission Denied..", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }
    }

    }