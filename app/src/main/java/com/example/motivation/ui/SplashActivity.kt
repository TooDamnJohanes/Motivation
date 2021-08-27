package com.example.motivation.ui

import android.annotation.SuppressLint
import android.app.ProgressDialog.show
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.R
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*
import android.view.Gravity

import android.widget.TextView




@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity(), View.OnClickListener {

    // CRIO A VARIAVEL AQUI, COM O MESMO NOME MESMO, PARA PASSAR OS PARAMETROS
    // DE SHARE PREFERENCES
    // DOU UM LATEINIT NELE, PORQUE NESTE MOMENTO, NÃO TEMOS CONTEXTO PARA FAZER REFERENCIA
    private lateinit var mSharedPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // COMO AQUI JÁ TEMOS CONTEXTO, ENTÃO PODEMOS INICIAR A CLASSE
        mSharedPreferences = SecurityPreferences(this)

        if(supportActionBar != null) {
            supportActionBar!!.hide()
        }
        // TRATAMOS O EVENTO DE CLIQUE, COM ESSA FUNÇÃO. -> LEMBREI DISSO
        saveButton.setOnClickListener(this)
        verifyName()
    }

    // ESSA FUNÇÃO É CRIADA AUTOMATICAMENTE, POR CONTA DO CONTRATO QUE ASSINO AO PUXAR A CLASSE VIEW
    // ESQUECI DE MUDAR A VARIAVEL QUE VEM PADRÃO, PARA view: View
    override fun onClick(view: View) {
        // Crio a variável id, que recebe o valor de View.id, que vai ver qual evento aconteceu
        // ESQUECI DESSA CARALHA
        val id = view.id
        if(id == R.id.saveButton) {
            handleSave()
        }
    }

    private fun handleSave() {
        // PEGO A VARIAVEL QUE ESTÁ SENDO DIGITADA NO EditText E CONVERTO PARA STRINT -> Lembrei
        val name = editTextName.text.toString()

        // FAÇO A VALIDAÇÃO DESSA STRING
        if(name != ""){
            // AQUI EU PASSO A CHAVE E O VALOR QUE EU QUERO ADICIONAR NO MEU SHARED PREFERENCES
            mSharedPreferences.storeString(MotivationConstants.KEY.PERSON_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            // MOSTRA A NOTIFICAÇÃO, CASO ELE PREENCHA COM CARACTERE ERRADO -> LEMBREI
            Toast.makeText(this, "Centered\nPREENCHE A CARALHA DO SEU NOME, SEU MERDA!", Toast.LENGTH_LONG).show()
        }
    }

    private fun verifyName() {
       val name = mSharedPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        if (name != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}