package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.motivation.R
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.repository.Mock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // CRIO A VARIAVEL AQUI, COM O MESMO NOME MESMO, PARA PASSAR OS PARAMETROS
    // DE SHARE PREFERENCES
    // DOU UM LATEINIT NELE, PORQUE NESTE MOMENTO, NÃO TEMOS CONTEXTO PARA FAZER REFERENCIA
    private lateinit var mSharedPreferences: SecurityPreferences

    private var mPhraseFilter: Int = MotivationConstants.PHRASEFILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // COMO AQUI JÁ TEMOS CONTEXTO, ENTÃO PODEMOS INICIAR A CLASSE
        mSharedPreferences = SecurityPreferences(this)
        // COLOCO QUE O TEXT NAME, DA TELA PRINCIPAL DO APP, RECEBE UM SHARED PREFERENCE GUARDADO
        // PEGO O VALOR DELE, COM UM GET, PASSANDO UMA CHAVE, A QUAL EU CRIEI UMA CONSTANTE
        val recebe: String = mSharedPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        textName.text = "Olá $recebe"

        if(supportActionBar != null) {
            supportActionBar!!.hide()
        }

        handleNewPhrase()

        buttonNewPhrase.setOnClickListener(this)
        imageAll.setOnClickListener(this)
        imageSad.setOnClickListener(this)
        imageMorning.setOnClickListener(this)


    }

    override fun onClick(view: View) {
        val id = view.id
        val listFilter = listOf(R.id.imageAll, R.id.imageSad, R.id.imageMorning)

        if(id == R.id.buttonNewPhrase)
        {
            handleNewPhrase()
        } else if(id in listFilter) {
            handleFilter(id)
        }
    }

    private fun handleFilter(id: Int) {
        imageAll.setColorFilter(resources.getColor(R.color.white))
        imageSad.setColorFilter(resources.getColor(R.color.white))
        imageMorning.setColorFilter(resources.getColor(R.color.white))

        when(id) {
            R.id.imageAll -> {
                imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.ALL
            }
            R.id.imageSad -> {
                imageSad.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.HAPPY
            }
            R.id.imageMorning -> {
                imageMorning.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.MORNING
            }
        }
    }

    private fun handleNewPhrase() {
        textPhrases.text = Mock().getPhrase(mPhraseFilter)
    }
}