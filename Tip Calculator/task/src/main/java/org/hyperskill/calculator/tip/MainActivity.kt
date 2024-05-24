package org.hyperskill.calculator.tip

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.text.TextUtils
import java.math.BigDecimal

import androidx.appcompat.app.AppCompatActivity
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var edittext=findViewById<EditText>(R.id.edit_text)
        var seekbar=findViewById<SeekBar>(R.id.seek_bar)
        var billvalue=findViewById<TextView>(R.id.bill_value_tv)
        var tipperc=findViewById<TextView>(R.id.tip_percent_tv)
        var tipamount=findViewById<TextView>(R.id.tip_amount_tv)
        /*if (TextUtils.isEmpty(edittext.text.toString())) {
            //Toast.makeText(this, "Empty field not allowed!", Toast.LENGTH_SHORT).show()
            tipperc.setText("")
        } else {
            tipperc.setText("Tip: 15%")
        }*/
        edittext.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // this function is called before text is edited
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // this function is called when text is edited
                //toastMsg("text is edited and onTextChangedListener is called.")
                if (TextUtils.isEmpty(s.toString())) {
                    //Toast.makeText(this, "Empty field not allowed!", Toast.LENGTH_SHORT).show()
                    billvalue.setText("")
                    tipperc.text=""
                    tipamount.text=""
                } else {
                    //Toast.makeText(this, "Proceed..", Toast.LENGTH_SHORT).show()
                    var value=s.toString()
                    var value1=value.toBigDecimal()
                    value1=value1.setScale(2,RoundingMode.DOWN)
                    value=value1.toString()



                     if(value=="0.00"){
                         billvalue.setText("")
                         tipperc.text=""
                         tipamount.text=""
                     }
                    else {
                         billvalue.setText("Bill Value: \$" + value)
                         val tippercent=seekbar.progress
                         tipperc.text="Tip: ${tippercent}%"
                         var help1=edittext.text
                         //var ans=(value.toBigDecimal()*tippercent.toBigDecimal()*BigDecimal(0.01))

                         //var ans1=ans.setScale(2,RoundingMode.UNNECESSARY).toDouble()
                         tipamount.text="Tip Amount: \$${String.format("%.2f",help1.toString().toDouble()*tippercent.toString().toDouble()*0.01)}"
                     }


                }

                /*var value=edittext.text.toString()
                var value1=value.toBigDecimal()
                value1=value1.setScale(2,RoundingMode.DOWN)
                value=value1.toString()





                billvalue.setText("Bill Value: \$"+value)*/

            }

            override fun afterTextChanged(s: Editable) {
                // this function is called after text is edited

            }

        })


        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar, progress: Int, fromUser: Boolean) {
                // we get in parameters SeekBar in case we add this listener to more that one SeekBar
                // fromUser give us information if change was initiated by user or programmatically

                // we set text in our TextView to the progress value we got
                //progressTv.text = progress.toString()
                if (TextUtils.isEmpty(edittext.text.toString())) {
                    //Toast.makeText(this, "Empty field not allowed!", Toast.LENGTH_SHORT).show()
                    tipperc.setText("")
                    tipamount.text=""
                } else {
                    tipperc.setText("Tip: " + progress.toString() + "%")
                    var help1=edittext.text
                    //var ans=(progress.toString().toBigDecimal()*help1.toString().toBigDecimal()*BigDecimal(0.01))
                    //var ans1=ans.setScale(2,RoundingMode.UNNECESSARY)

                    tipamount.text="Tip Amount: \$${String.format("%.2f",progress.toString().toDouble()*help1.toString().toDouble()*0.01)}"
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                //println("Tracking started!")
                //tipperc.setText("Tip:" + 15 + "%")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                //println("Tracking stopped!")
            }

        })
    }
}