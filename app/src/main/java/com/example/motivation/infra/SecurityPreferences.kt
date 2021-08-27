package com.example.motivation.infra

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {
    // NESSA VARIAVEL, EU PEGO O SHARED PREFERENCE, PASSANDO O NOME DO MEU CONTEXTO, OU DO MEU
    // OBJETO, FALANDO O MODO A QUAL EU DESEJO QUE ELE SEJA ABERTO, NO MODO PRIVADO

    private val mSharedPreferences =
        context.getSharedPreferences("motivation", Context.MODE_PRIVATE)

    fun storeString(key: String, value: String) {
        // EDITO O VALOR DE UMA SHARED PREFERENCE, BEM PARECIDO COM O QUE FAZEMOS COM DICIONÁRIOS
        mSharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String): String {
        // AO INVOCAR ESSA FUNÇÃO, EU ESTOU PEGANDO EM UM SHARED PREFERENCE, O VALOR QUE ESTÁ
        // ARMAZENADO DENTRO DA MINHA CHAVE, QUE RECEBO POR PARAMETRO
        return mSharedPreferences.getString(key, "") ?: ""
    }
}