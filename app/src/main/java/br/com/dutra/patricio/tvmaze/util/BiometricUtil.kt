package br.com.dutra.patricio.tvmaze.util

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat

class BiometricUtil {

    private lateinit var biometricPrompt: BiometricPrompt

    fun startBiometricPrompt(act: AppCompatActivity, success: () -> Unit) {

        val executor = ContextCompat.getMainExecutor(act)

        val callback = object: BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(act,"$errorCode :: $errString",Toast.LENGTH_LONG).show()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(act,"Authentication failed for an unknown reason",Toast.LENGTH_LONG).show()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                success()
            }
        }

        biometricPrompt = BiometricPrompt(act,executor,callback)
    }

    fun authenticate(context: Context){
        val biometricManager = BiometricManager.from(context)
        if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS){
            biometricPrompt.authenticate(getPromptInfo())
        }else{
            Toast.makeText(context,"Não possui sensor biometrico", Toast.LENGTH_LONG).show()
        }
    }

    private fun getPromptInfo(): BiometricPrompt.PromptInfo {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Tv Manze")
            .setSubtitle("Insira sua digital para acessar o aplicativo!")
            .setDescription("Este aplicativo usa autenticação biometrica")
            .setDeviceCredentialAllowed(true)
            .build()
        return promptInfo
    }

}