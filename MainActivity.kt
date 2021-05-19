package com.rogerio.calculadorasimples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import com.rogerio.calculadorasimples.constants.*
import com.rogerio.calculadorasimples.model.CalcVisor
import com.rogerio.calculadorasimples.model.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    // Iniciando o ViewModel

    private val model: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Lista de botões da calculadora

        val listButton = listOf<View>(
            btnNum0, btnNum1, btnNum2, btnNum3,
            btnNum4, btnNum5, btnNum6, btnNum7,
            btnNum8, btnNum9, btnPonto, btnAC,
            btnLimpa, btnMais, btnMenos, btnMultiplica,
            btnDivide,btnIgual, btnSinal
        )
        // Seta o nome dos botões de acordo com as constantes pela função

        for (x in listButton) {
            if (x is Button){
                x.text = getBtnConstant(x).toString()
            }
        }
        // Setando função de clique para todos os botões

        for (x in listButton) {
            x.click()
        }
        // Setando viewmodel para receber as Strings
        // de cálculo e números

        model.getNumbers().observe(this, Observer<CalcVisor> {
            tvNumbers.text = it.number
            tvCalc.text = it.calc
        })
    }
    // Pega o valor da constante do botão

    private fun getBtnConstant(any: Any): Any? {
        return when(any) {
            btnNum0 -> N0
            btnNum1 -> N1
            btnNum2 -> N2
            btnNum3 -> N3
            btnNum4 -> N4
            btnNum5 -> N5
            btnNum6 -> N6
            btnNum7 -> N7
            btnNum8 -> N8
            btnNum9 -> N9
            btnPonto -> POINT
            btnSinal -> SIGNAL
            btnLimpa-> BACK
            btnAC -> AC
            btnMais -> PLUS
            btnMenos -> MINUS
            btnMultiplica -> MULTIPLY
            btnDivide -> DIVIDE
            btnIgual -> EQUAL
            else -> null
        }
    }
    // Inserindo o Listener no Botão

    private fun View.click() {
        this.setOnClickListener {
            send(it)
        }
    }
    // Função que envia o botão clicado

    private fun send(v: View) {
        // Seta o valor da variável c de acordo com a tecla clicada

        val c = getBtnConstant(v)
        if (c != null) {
            model.setClick(c)
        }
    }
}
